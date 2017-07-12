<?php

namespace App\Http\Controllers;
use App\Manager;
use App\Company;
use App\Program;
use App\Active_user;
use App\M_app_permission;
use App\AppCriticalData;
use Illuminate\Auth;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Crypt;
use DB;
use Storage;
use function Sodium\add;

class DataController extends Controller{
	 /*
 	 * 数据展示部分
 	 */
 	 
 	 /* --------------------关键数据(用户概况)--------------------- */
 	 
 	 
 	 /* 
        1.新增玩家和设备 
        Url:/newuserdev Method:GET
        请求参数：
        Remember_token 	
        Starttime		查询起始日期
        Endtime 		查询结束日期
        App_id 		查询的app_id	
        (注意check三级管理员是否有查询该模块信息的权限)
        返回参数：（类似于）
        ‘new_user’ :{‘2017-06-28’: 24 , ’2017-06-29’:34, ‘2017-06-30’: 45, ‘2017-07-01’ : 23},
        ‘new_device’: {‘2017-06-28’: 26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56}
 	 */
 	 public function get_new_user_dev(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'starttime'=>'required',
            'endtime'=>'required',
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
		if( !(($permission>>0) & 1)) return $this->stdResponse('-6');
		
        $new_user = DB::select('select datetime,new_user from app_critical_data where app_key= ? and datetime <= ? and datetime >= ?'
                                                    ,[$app_key,$request->endtime,$request->starttime]);
        $new_device = DB::select('select datetime,new_device from app_critical_data where app_key= ? and datetime <= ? and datetime >= ?'
                                                    ,[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('new_user'=>$new_user,'new_device'=>$new_device);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
 	 
 	 /*
 	    2.活跃玩家和设备
 	    Url:/activeuserdev  Method:GET
        请求参数：
        Remember_token 
        Starttime  	查询起始日期
        Endtime		查询结束日期
        App_id   	查询的app_id	
        (注意check三级管理员是否有查询该模块信息的权限)
        返回参数：
        {
        ‘active_user’ :{‘2017-06-28’: 24 , ’2017-06-29’:34, ‘2017-06-30’: 45, ‘2017-07-01’ : 23},
          ‘active_device’: {‘2017-06-28’: 26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56}
        }
 	    */
 	 public function get_Active_User_Device(Request $request){
 	    if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'starttime'=>'required',
            'endtime'=>'required',
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
		if( !(($permission>>0) & 1)) return $this->stdResponse('-6');
		
        $active_user = DB::select('select datetime,active_user from app_critical_data where app_key= ? and datetime <= ? and datetime >= ?'
                                                    ,[$app_key,$request->endtime,$request->starttime]);
        $active_device = DB::select('select datetime,active_device from app_critical_data where app_key= ? and datetime <= ? and datetime >= ?'
                                                    ,[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('active_user'=>$active_user,'active_device'=>$active_device);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
 	 
 	 /* 
 	    3.付费金额 
     	Url:/paymoney  method:GET
        请求参数：
        Remember_token 
        Starttime  	查询起始日期
        Endtime		查询结束日期
        App_id   	查询的app_id	
        (注意check三级管理员是否有查询该模块信息的权限)
        返回参数：
          {
        ‘Money’: {‘2017-06-28’: 26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56}
        }
 	 */
 	 public function get_pay_count(Request $request){
 	    if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'starttime'=>'required',
            'endtime'=>'required',
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
		if( !(($permission>>0) & 1)) return $this->stdResponse('-6');
		$pay_money = DB::select('select datetime,pay_money from app_critical_data where app_key= ? and datetime <= ? and datetime >= ?'
                                                    ,[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('pay_money'=>$pay_money);
        return $this->stdResponse('1',json_encode($resdata));
		
		
 	 }
 	 
 	 /*
 	    4.付费用户
 	    Url:/payuser   Method:GET
        请求参数:
        Remember_token 
        Starttime  	查询起始日期
        Endtime		查询结束日期
        App_id   	查询的app_id	
        (注意check三级管理员是否有查询该模块信息的权限)
        返回参数：
          {
        ‘user’: {‘2017-06-28’: 26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56}
        }
 	 */
 	 public function get_pay_user(Request $request){
 	    if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'starttime'=>'required',
            'endtime'=>'required',
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
		if(!(($permission>>0) & 1)) return $this->stdResponse('-6');
		$pay_user_count = DB::select('select datetime,pay_user_count from app_critical_data where app_key= ? and datetime <= ? and datetime >= ?'
                                                    ,[$app_key,$request->endtime,$request->starttime]);
  	    $resdata = array('pay_user_count'=>$pay_user_count);
        return $this->stdResponse('1',json_encode($resdata));
        }
        
    
 	 /* ------------------关键数据(用户概况)结束------------------- */
 	 
 	 
 	/*----------------------------新增用户( 设备 )--------------------------*/
    
    /*1.新增设备
	    Url:/newdev Method:GET
        请求参数：
            Remember_token 	
            Starttime		查询起始日期
            Endtime 		查询结束日期
            App_id 		查询的app_id	
        (注意check三级管理员是否有查询该模块信息的权限)
        返回参数：（类似于）
            ‘new_device’: { ‘2017-06-28’:26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56 }*/
    public function get_new_dev(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'starttime'=>'required',
            'endtime'=>'required',
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
		if( !(($permission>>1) & 1)) return $this->stdResponse('-6');
		
        $new_device = DB::select('select datetime,new_device from app_critical_data where app_key= ? and datetime <= ? and datetime >= ?',[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('new_device'=>$new_device);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }    
 	 
 	 /*2.首次游戏时长
        Url:/devfirsttime  Method:GET
        请求参数 ：
        Remember_token
        App_id
        返回参数：
        {
        ‘1_4s’:x , ‘5_10s’:x ,’11_30s’ :x,
        ‘31_60s’:x,  ’1_3m’:x,  ’3_10m’:x,  ’10_30m’:x,  ’30_60m’:x,   	‘60_m’:x
        }
    */
    public function get_dev_first_time(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        $today = date('Y-m-d');
		if( !(($permission>>1) & 1)) return $this->stdResponse('-6');
		
        $dev_first_time = DB::select('select 1_4s,5_10s,11_30s,31_60s,1_3m,4_10m,11_30m,31_60m,60_m from new_device where app_key= ? and datetime = ?',[$app_key,$today]);
        $resdata = array('dev_first_time'=>$dev_first_time);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
 	 
 	 
 	/*----------------------------新增用户( 用户 )--------------------------*/
 	/*1.新增玩家
	Url:/newuser  Method:GET
    请求参数：
        Remember_token 	
        Starttime		查询起始日期
        Endtime 		查询结束日期
        App_id 	    	查询的app_id	
    (注意check三级管理员是否有查询该模块信息的权限)
    返回参数：（类似于）
    ‘new_user’: { ‘2017-06-28’:26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 	56 }*/
    public function get_new_user(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'starttime'=>'required',
            'endtime'=>'required',
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        } 
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
		if( !(($permission>>1) & 1)) return $this->stdResponse('-6');
		
        $new_user = DB::select('select datetime,new_user from app_critical_data where app_key= ? and datetime <= ? and datetime >= ?',[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('new_user'=>$new_user);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 } 
 	 
 	 /*3首次游戏时长
 	  	Url:/userfirsttime  Method:GET
        请求参数 ：
            Remember_token 
            App_id
        返回参数：
            {‘1_4s’:x , ‘5_10s’:x ,’11_30s’ :x, 
            ‘31_60s’:x,  ’1_3m’:x,  ’3_10m’:x,  ’10_30m’:x,  ’30_60m’:x,	‘60_m’:x}   */
    public function get_user_first_time(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        $today = date('Y-m-d');
		if( !(($permission>>1) & 1)) return $this->stdResponse('-6');
		
        $user_first_time = DB::select('select 1_4s,5_10s,11_30s,31_60s,1_3m,4_10m,11_30m,31_60m,60_m from new_user where app_key= ? and datetime = ?',[$app_key,$today]);
        $resdata = array('user_first_time'=>$user_first_time);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
 	 
 	 /*-----------------------------应用下载数据-------------------------------*/
 	 
 	 /*1.下载量
	Url:/download  method：GET
	请求数据：
		Remember_token
		App_id
	返回参数：
 		 ‘download’: { ‘2017-06-28’:26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56 }
    */
 	 public function get_downloaded_count(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
            'starttime'=>'required',
            'endtime'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        //$today = date('Y-m-d');
		if( !(($permission>>1) & 1)) return $this->stdResponse('-6');
		
        $downloaded_count = DB::select('select date,count from download where app_key= ? and date <= ? and date >=?',[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('downloaded_count'=>$downloaded_count);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
    
    /*--------------------------------活跃设备概况--------------------------------*/
 	/*1.日活
 	   	Url:/dayactivedev  Method:GET
        请求参数 ：
            Remember_token
            starttime
            endtime
            App_id
        返回参数：
            {
            ‘active_device’: {‘2017-06-28’: 26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56}}
    */
 	public function get_day_active_dev(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
            'starttime'=>'required',
            'endtime'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        //$today = date('Y-m-d');
		if( !(($permission>>2) & 1)) return $this->stdResponse('-6');
		
        $day_active_dev = DB::select('select datetime,active_device from app_critical_data where app_key= ? and datetime <= ? and datetime >=?',[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('day_active_dev'=>$day_active_dev);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
 	
 	/*2.周活（数据表还没建 和日活一样 表名改下就行）
        Url:/weekactivedev  Method:GET
        请求参数 ：
        Remember_token
        starttime
        endtime
        App_id
        返回参数：
        {
          ‘active_device’: {‘2017-06-28’: 26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56}
        }
 	 */
 	public function get_week_active_dev(Request $request){
 	     
 	 }
 	 
 	/* 4.已玩天数
    	  Url:/alreadyplaydev  Method:GET
            请求参数 ：
                Remember_token
                App_id
            返回参数：
                {‘1d’:x , ‘2_3d’:x ,’4_7d’ :x,‘8_14d’:x,  ’15_30d’:x,  ’31_90d’:x,  ’91_180d’:x,  ’181_365d’:x,   	‘365_d’:x}
    */
 	public function already_play_dev(Request $request){
 	    if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        if( !(($permission>>2) & 1)) return $this->stdResponse('-6');
        $today = date('Y-m-d');
        $already_play_dev = DB::select('select 1d,2_3d,4_7d,8_14d,15_30d,31_90d,91_180d,181_365d,365_d 
                                        from active_device 
                                        where app_key= ? and datetime = ?',[$app_key,$today]);
        return $this->stdResponse('1',$already_play_dev);
        
 	 }
 	 
 	 
 	 /*--------------------------------活跃玩家概况 --------------------------------*/ 
 	 
 	/*1.日活
 	   	Url:/dayactiveuser  Method:GET
        请求参数 ：
            Remember_token
            starttime
            endtime
            App_id
        返回参数：
            {‘active_user’: {‘2017-06-28’: 26 , ’2017-06-29’:21, ‘2017-06-30’: 34, ‘2017-07-01’ : 56}}
    */
 	public function get_day_active_user(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
            'starttime'=>'required',
            'endtime'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        //$today = date('Y-m-d');
		if( !(($permission>>2) & 1)) return $this->stdResponse('-6');
		
        $day_active_user = DB::select('select datetime,active_user from app_critical_data where app_key= ? and datetime <= ? and datetime >=?',[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('day_active_user'=>$day_active_user);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
 	 
 	 
 	/* 4.已玩天数
    	Url:/alreadyplayuser  Method:GET
        请求参数 ：
        Remember_token
        App_id
        返回参数：
        {
        ‘1d’:x , ‘2_3d’:x ,’4_7d’ :x,
        ‘8_14d’:x,  ’15_30d’:x,  ’31_90d’:x,  ’91_180d’:x,  ’181_365d’:x,   	‘365_d’:x
        }
        */
    public function already_play_user(Request $request){
 	    if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        if( !(($permission>>2) & 1)) return $this->stdResponse('-6');
        $today = date('Y-m-d');
        $already_play_user = DB::select('select 1d,2_3d,4_7d,8_14d,15_30d,31_90d,91_180d,181_365d,365_d 
                                        from active_user 
                                        where app_key= ? and datetime = ?',[$app_key,$today]);
        return $this->stdResponse('1',$already_play_user);
        
 	 } 	 
 	 /*--------------------------------用户流失状况 --------------------------------*/
 	 
 	/*1 .设备留存
		Url:/devicesurvive  Method:GET
        请求参数 ：
            Remember_token
            starttime
            endtime
            App_id
        返回参数：
            {‘date’:xx , ’new_device’:xx, ’1d’:xx, ‘2d’:xx ,’3d’:xx,  ‘4d’:xx,’5d’:xx,’6d’:xx,’7d’:xx,’14d’:xx,’30d’:xx}
    */
 	public function get_survive_device(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
            'starttime'=>'required',
            'endtime'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        //$today = date('Y-m-d');
		if( !(($permission>>3) & 1)) return $this->stdResponse('-6');
		
        $survive_device = DB::select('select date,today,1day,2day,3day,4day,5day,6day,7day,14day,30day from survive_device where app_key= ? and date <= ? and date >=?',[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('survive_device'=>$survive_device);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
 	 
 	 
 	 /*1.玩家留存 
      Url:/usersurvive  Method:GET
      请求参数 ：
            Remember_token
            starttime
            endtime
            App_id
    返回参数：
            {‘date’:xx , ’new_user:xx, ’1d’:xx, ‘2d’:xx ,’3d’:xx,  ‘4d’:xx,’5d’:xx,’6d’:xx,’7d’:xx,’14d’:xx,’30d’:xx}
    */
 	public function get_survive_user(Request $request){
        if(!$this->check_token($request->input('remember_token'))){
            return $this->stdResponse('-3');
        }
        $res=$this->filter($request,[
            'app_id'=>'required',
            'starttime'=>'required',
            'endtime'=>'required',
        ]);
        if(!$res){
            return $this->stdResponse('-1');
        }
        $app_key = Program::where('id',$request->app_id)->first()->app_key;
        $permission = M_app_permission::where('manager_id',$this->admin_id)->where('app_key',$app_key)->first()->permission;
        //$today = date('Y-m-d');
		if( !(($permission>>3) & 1)) return $this->stdResponse('-6');
		
        $survive_user = DB::select('select date,today,1day,2day,3day,4day,5day,6day,7day,14day,30day from survive_user where app_key= ? and date <= ? and date >=?',[$app_key,$request->endtime,$request->starttime]);
        $resdata = array('survive_user'=>$survive_user);
        return $this->stdResponse('1',json_encode($resdata));
        
 	 }
 	 
 	 
 	 /*type是设备和玩家：nd是新增设备、nu是新增玩家、ad是活跃设备、au活跃玩家、pd付费设备、pu付费玩家;
 	    param是参数，ci是联网方式，co是运营商、os是操作系统、pt是设备型号
 	   url:app/{id}/{type}/{param}  Method:GET
 	   请求参数：
 	        remember_token,
 	        starttime,
 	        endtime,
 	  //    count 显示数量，
 	    返回参数 ：
 	 */
    public function getFeaturesData(Request $request,$id,$type,$param){
         if(!$this->check_token($request->input('remember_token'))){
             return $this->stdResponse('-3');
         }
         $res=$this->filter($request,[
             'starttime'=>'required|date_format:Y-m-d',
             'endtime'=>'required|date_format:Y-m-d',
         ]);
         if(!$res){
             return $this->stdResponse('-1');
         }

        $app_key = Program::where('id',$id)->first()->app_key;

        /*遍历两个日期之间所有日期*/
        $start=strtotime($request->starttime);
        $end=strtotime($request->endtime);
        $date=array();
        while($start<=$end){
            $aaa=date('Y-m-d',$start);
            array_push($date,$aaa);
            $start=strtotime('+1 day',$start);
        }

        $collection=collect();
        for($i=0;$i<count($date);$i++){
            $filename=$date[$i]."_".$type."_".$param.".txt";
            $contents=json_decode(Storage::disk($type)->get($filename));
            $array=$contents->{"data"};
            for($j=0;$j< count($array);$j++) {
                if($array[$j]->{"app_key"}==$app_key){
            //       $collection->put($date[$i],$array[$j]->{"data"});
                    $collection->push($array[$j]->{"data"});
                }
            }

        }
        /*再次遍历加值*/
		$Map = array();
		foreach($collection as $arr){
			foreach($arr as $obj){
				if(!array_key_exists($obj->value,$Map))$Map[$obj->value]=0;
				$Map[$obj->value]+=intval($obj->count);
			}
		}

        return $Map;

    }

}