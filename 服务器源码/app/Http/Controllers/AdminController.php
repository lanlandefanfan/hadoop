<?php

namespace App\Http\Controllers;
use App\Manager;
use App\Company;
use App\Program;
use App\M_app_permission;
use Illuminate\Auth;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Crypt;
use DB;
use Germey\Geetest\GeetestCaptcha;
use Validator;
class AdminController extends Controller{

	/*---------------------用户登录及注销-----------------------*/
    /*
      1.  登录
	   Url：/login  Method: POST
	      请求参数 ：
		email  email  ,
		password  string
	      返回参数 ：remember_token , name,  grade （grade相当于permission  ）
     */
    public function login(Request $request){

      $res=  Validator::make($request->all(),[
          'email'=>'required|filled|email',
          'password'=>'required|filled',
          'geetest_challenge' => 'geetest',
      ],[
          'geetest' => config('geetest.server_fail_alert')
      ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
       try{
            $admin=Manager::where('password',md5($request->input('password').'#'.$request->input('email')))
                ->where('email',$request->input('email'))->first();

            if(!count($admin)>0)  return $this->stdResponse('-2');

           if($admin->token_expire < date('Y-m-d H:i:s'))
            {
                $admin->remember_token = Crypt::encrypt($admin->name."&".time());
                $admin->token_expire = date('Y-m-d H:i:s',strtotime("+24 hour"));
                $admin->save();
            }
            $resdata=array('remember_token'=>$admin->remember_token,'name'=>$admin->name,'grade'=>$admin->permission);

        if($admin->permission==1){
            return $this->stdResponse('1',"一级界面");
        }elseif ($admin->permission==2){
            return $this->stdResponse('1',"二级界面");
        } else{
            return  redirect('manager?token='.$admin->remember_token);
        }

        }catch (\Exception $exception){
            return $this->stdResponse('-4 ' , $exception->getMessage());
        }catch(\Error $error){
            return $this->stdResponse('-10');
       }
    }
    /*
     * 2. 注销登录 
		URl：/logout      	METHOD:DELETE
		请求参数 ：
		remember_token
		返回参数 ：null
     */
    public function logout(Request $request){
    	if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
    }
        $manager = Manager::where('id',$this->admin_id)->first();
        DB::beginTransaction();
        try{
        	$manager->token_expire = '1970-01-01';
            $manager->save();
            DB::commit();
            return $this->stdResponse('1');
        }catch (\Exception $exception){
            DB::rollback();
            return $this->stdResponse('-4');
        }catch (\Error $error){
            DB::rollback();
            return $this->stdResponse('-4');
        }

    }
    //TODO
    
	/*---------------------用户登录及注销结束-----------------------*/
	
 	/*--------------------- 以下是一级管理员的接口 ---------------------*/
 	 
 	
    /* 1.（root）获取公司列表（分页）
		Url：/clist   Method:GET
		请求参数：
		Remember_token string
		Page   int  要求的页数
		Rows  int  要求每页的列表
		返回参数
		Company: id ,name ,describe
 	*/
    public function getCompany(Request $request){
    	if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
		if($this->admin_permission !=1) return $this->stdResponse('-6');
        $res=$this->filter($request,[
            'page'=>'required',
            'rows'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $com=Company::paginate($request->input('rows'));
        
        return $this->stdResponse('1',$com);
        
    }

    /*新增 （root）获取某公司下项目列表 (分页)
     	Url：/alist/company/{id}   Method:GET
		请求参数：
		Remember_token string
		Page   int  要求的页数
		Rows  int  要求每页的列表
		返回参数
		APP: id ,name ,describe
    */
    public function getProject(Request $request,$id){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        if($this->admin_permission !=1) return $this->stdResponse('-6');
        $res=$this->filter($request,[
            'page'=>'required',
            'rows'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $name=Company::find($id)->name;
        $apps=Program::where('company',$name)->get();

        return $this->stdResponse('1',json_encode($apps));
    }

    /*2.（root）添加公司与二级管理员
		Url：/company Method:Post
		请求参数：
		Remember_token,
		Company_name,
		Company_describe,
		Admin_name,
		Admin_email,
		Admin_password,
		返回参数：
	    1 成功 ，其他 失败；
    */
    public function createCompany(Request $request){

        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'company_name'=>'required|filled',
            'company_describe'=>'required',
            'admin_name'=>'required|filled',
            'admin_email'=>'required|filled|email',
            'admin_password'=>'required|min:6',
        ]);	
        if(!$res){
            return $this->stdResponse('-1');
        }

        if($this->admin_permission !=1) return $this->stdResponse('-6');
        DB::beginTransaction();
        try{
        	$md5password = md5($request->input('admin_password').'#'.$request->input('admin_email'));
            $admininfo=array('name'=>$request->admin_name,
                             'email'=>$request->admin_email,
                             'password'=>$md5password);
            $newadmin=Manager::create($admininfo);

            $cominfo=array('name'=>$request->input('company_name'),
                            'describe'=>$request->input('company_describe'),
                            'm_id'=>$newadmin->id);
            $newcompany=Company::create($cominfo);

            $newadmin->c_id=$newcompany->id;
            $newadmin->permission=2;
            $newadmin->save();

            DB::commit();
            return $this->stdResponse('1');
        }catch (\Exception $exception){
            DB::rollback();
            return $this->stdResponse('-4');
        }catch (\Error $error){
            DB::rollback();
            return $this->stdResponse('-4');
        }
    }
    
    /*  3 . (root)添加项目（app）
		Url:/add  Method :POST
		请求参数 ：
		Name   string     项目名字 ，
		Company  string   项目所属公司 ，  	
		Starttime  date 项目开始时间，
		Endtime  date 项目结束时间，
		Description  string 项目备注，
		返回参数 ：
		1成功 ， 其他 失败
   	 */
    public function createApp(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
		if($this->admin_permission !=1) return $this->stdResponse('-6');
        $res=$this->filter($request,[
            'name'=>'required',
            'company'=>'required',
            'starttime'=>'required',
            'endtime'=>'required',
            'description'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        DB::beginTransaction();
        try{
            $appinfo=array('name'=>$request->name,
                            'company'=>$request->company,
                            'description'=>$request->description,
                            'starttime'=>$request->starttime,
                            'endtime'=>$request->endtime,
                            'app_key'=>md5($request->name.'#'.time()),
                            );
                            
            $newapp=Program::create($appinfo);
            $newapp->save();
            DB::commit();
            return $this->stdResponse('1');
        }catch (\Exception $exception){
            DB::rollback();
            return $this->stdResponse('-4');
        }catch (\Error $error){
            DB::rollback();
            return $this->stdResponse('-4');
        }
    }
   
    /*  4.（root）获取项目app信息
		Url：/ainfo/id/{id}  method：GET
		请求参数：
		Remember_token,
		返回参数：
		app_id,
		App_name,
		Company,
		Starttime,
		Endtime,
		Describe.
	*/
    public function getAppInfo(Request $request,$id){
    //check admin and permission
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
		if($this->admin_permission !=1) return $this->stdResponse('-6');
        $app = Program::where('id',$id)->get();
        return $this->stdResponse('1',$app);
    }
    
    /*   5.（root）编辑更改app信息
		Url:/edit  Method:POST
		请求参数：
		Remember_token,
		App_id,
		Name(需判断是否有该字段),
		Endtime (需判断)，
		Description，
		返回参数：
		1 成功 ; 其他 失败
 	*/
    public function updateApp(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
		if($this->admin_permission !=1) return $this->stdResponse('-6');
		$res=$this->filter($request,[
            'app_id'=>'required',
            'name'=>'required',
            'endtime'=>'required',
            'description'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        DB::beginTransaction();
        try{
            $app = Program::where('id',$request->app_id)->first();
            $app->name = $request->name;
            $app->endtime = $request->endtime;
            $app->description = $request->description;
            $app->save();
            DB::commit();
            return $this->stdResponse('1');
        }catch (\Exception $exception){
            DB::rollback();
            return $this->stdResponse('-4');
        }catch (\Error $error){
            DB::rollback();
            return $this->stdResponse('-4');
        }
    }
    
    
 	/*---------------------------一级管理员的接口结束--------------------------*/
    
    
 	/*----------------------- 以下是二级管理员的接口---------------------------*/
    
    /* 1.  二级管理员创建三级管理员
		描述：企业级管理员创建其他管理员（三级管理员permission=3）
		Url：/create  METHOD:POST
		请求参数：
		remember_token string  用来验证当前管理员权限 ,  
		name  string   要创建的管理员名字, 
		email  email   要创建的管理员邮箱, 
		password string（至少6位） 要创建的管理员密码 , 
		(deleted)company string  要创建管理员的所属公司，
		返回参数：1（成功） 其他（失败）
	*/
    public function createAdmin(Request $request){
        //check admin and permission
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
		if($this->admin_permission !=2) return $this->stdResponse('-6');
        $res=$this->filter($request,[
            'email'=>'required|filled|email|unique:manager',
            'name'=>'required',
            'password'=>'required|min:6',
  //        'company'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $new=new Manager();
        $new->email=$request->email;
        $new->name =$request->name;
        $new->password=md5($request->input('password').'#'.$request->input('email'));
        $new->c_id=$this->admin_c_id;
        $new->permission=3;
        $new->save();
        return $this->stdResponse('1');

    }
    
	/* 2.设置三级管理员与app关联
		描述：root创建的二级管理员默认拥有所属企业下所有软件的所有权限，该接口是二级管理员设置其公司三级管理员权限。
		Url：/permission/id/{id}  method: POST
		请求参数：
		remember_token  string 用来验证当前管理员权限，
		app_id int  分配给管理员的项目id，  
		(deleted)permission  string   默认为0 
		返回参数：
		1 成功，其他 失败
 	*/
	public function setAdmin(Request $request, $id){
		if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        if($this->admin_permission !=2) return $this->stdResponse('-6');
        $res=$this->filter($request,[
			'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        
        DB::beginTransaction();
        try{
	        $app_key = Program::where('id',$request->app_id)->first()->app_key;
//	        $m_a_permission=M_app_permission::where('manager_id',$id)->where('app_key',$app_key);
//	        if($m_a_permission->count()==0){
	        	$m_a_permission = new M_app_permission();
	        	$m_a_permission->manager_id=$id;
	        	$m_a_permission->app_key=$app_key;
	        	$m_a_permission->permission=0;
	        	$m_a_permission->save();
	        	DB::commit();
//	        }else{
//	        	$m_a_permission=$m_a_permission->first();
//	        	$m_a_permission->permission=0;
//	        	$m_a_permission->save();
//	        	DB::commit();
//	        }
        }catch(Exception $ex){
        	DB::rollback();
        	return $this->stdResponse('-4');
        }catch(Error $err){
        	DB::rollback();
        	return $this->stdResponse('-4');
        }
        return $this->stdResponse('1');
	}
	/*3.设置三级管理员app权限
		Url：/permission method:POST
		请求参数：
		 	Remember_token ,
		Permission  string  用 ，隔开
		App_id ,
	    manager_id,
		返回参数：
		1 成功；其他 失败
	*/
	public function updateAdmin(Request $request){
		if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        if($this->admin_permission !=2) return $this->stdResponse('-6');
        $res=$this->filter($request,[
			'app_id'=>'required',
			'permission' => 'required',
			'manager_id' => 'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        //str转换到二进制存储
        $permission_array=explode(",",$request->permission);
		$Map = array(); //定义一个数组
		$Map['用户概况'] = 1;
		$Map['新增用户'] = 2;
		$Map['活跃用户'] = 4;
		$Map['用户流失状况'] = 8;
		$Map['用户特征描述'] = 16;
		$permission=0;
        foreach( $permission_array as $per_str){
        	$permission += $Map[$per_str];
        }
        DB::beginTransaction();
        try{
	        $app_key = Program::where('id',$request->app_id)->first()->app_key;
	        $m_a_permission=M_app_permission::where('manager_id',$request->manager_id)->where('app_key',$app_key)->first();
	        $m_a_permission->permission=$permission;
	        $m_a_permission->save();
	        DB::commit();
	        
        }catch(Exception $ex){
        	DB::rollback();
        	return $this->stdResponse('-4');
        }catch(Error $err){
        	DB::rollback();
        	return $this->stdResponse('-4');
        }
        return $this->stdResponse('1');
	}
	
	/* 4.（二级管理员）获取三级管理员列表（分页）
		Url :/mlist  method :GET
		请求参数 :
		remember_token  string
		page  int  要求的页数 
		rows  int  要求每页的列数
		返回参数：（同所属公司）
		Id ,name ,email,company
	*/
	public function getAdmin(Request $request){
        //check admin and permission
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        if($this->admin_permission !=2) return $this->stdResponse('-6');
        $res=$this->filter($request,[
            'page'=>'required',
            'rows'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        // $c_id=Company::where('name',$this->admin_company)->first()->id;
        $admin=Manager::where('permission','3')//->where('c_id',$c_id)
              			->simplePaginate($request->input('rows'));
        
        return $this->stdResponse('1',$admin);
    }
    
    /*5.（二级管理员）获取三级管理员信息
		Url：/info/id/{id}  method: GET
		请求参数：
		Remember_token
		返回参数： 	
		1. name,
		2. email,
		3. 所有管理的app,
		4. 对应的app_permission
	*/
    public function getAdminInfo(Request $request,$id){
    	if(!$this->check_token($request->input('remember_token'))){
			return $this->stdResponse('-3');
		}
		if($this->admin_permission !=2) return $this->stdResponse('-6');
		$mgr = Manager::where('id',$id)->first();
		$email= $mgr->email;
		$name= $mgr->name;
		
		$app = DB::select('select name,permission from m_app_permission left join app 
						on m_app_permission.app_key = app.app_key
						where manager_id = ? ',[$id]);
		$resdata=array('email'=>$email,'name'=>$mgr,'app'=>$app);
        return $this->stdResponse('1',json_encode($resdata));
    }
    
    
    /*------------------------二级管理员的接口结束----------------------*/
 	 
 	/*------------------------三级管理员的接口--------------------------*/
 	/*1.获取app列表
    	url：/getlist method:GET
    	请求参数：
    		remember_token 
    	返回参数：
    	    app：id , name
    */
 	public function get_app_list(Request $request){
 	    if(!$this->check_token($request->input('remember_token'))){
			return $this->stdResponse('-3');
		}
		if($this->admin_permission !=3) return $this->stdResponse('-6');
		$manager_id = $this->admin_id;
		$app = DB::select('select app.id,name from m_app_permission left join app 
						on m_app_permission.app_key = app.app_key
						where manager_id = ? ',[$manager_id]);
		return $this->stdResponse('1',$app);
 	}
 	
 	 /*2.获得app信息
    	url：/getapp  method:GET
    	请求参数：
    		remember_token ,
    		app_id,
    	返回参数：
    		name , m_a_permission,description
    */
 	 public function get_app_info(Request $request){
 	    if(!$this->check_token($request->input('remember_token'))){
			return $this->stdResponse('-3');
		}
		if($this->admin_permission !=3) return $this->stdResponse('-6');
		$res=$this->filter($request,[
			'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
		$manager_id = $this->admin_id;
		$app=Program::where('id',$request->app_id)->first();
		$m_a_permission = M_app_permission::where('app_key',$app->app_key)->where('manager_id',$manager_id);
		$resdata=array('name'=>$app->name,'description'=>$app->description,'m_a_permission'=>$m_a_permission);
        return $this->stdResponse('1',json_encode($resdata));
 	 }
 	 
 	 
 	 

}