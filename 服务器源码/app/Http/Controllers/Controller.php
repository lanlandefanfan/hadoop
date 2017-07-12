<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Http\Request;
use Validator;
use App\Manager;
class Controller extends BaseController
{
    use AuthorizesRequests, DispatchesJobs, ValidatesRequests;

    private $stdStatus = [
        1 => '成功',
        -1 => '表单错误',
        -2 => '身份信息错误',
        -3 => 'remember_token校验失败',
        -4 => '数据库操作失败',
        -5 => '记录不存在',
        -6 => '权限不足',
        -7 => '用户名或密码错误',
        -8 => '文件上传失败',
        -9=> '没有更多内容',
        -10=> '服务器错误',

    ];
    public $filterFail = false;
    public $backMeg;

    public $admin_email="";
    public $admin_name="";
    public $admin_permission="";
    public $admin_company="";
    public $admin_c_id="";
	public $admin_id="";

    public function stdResponse($code='',$result='')
    {
        $hashCode = ($code || $code === 1);
        return response()->json(
            ['code'=> $hashCode ? $code : -1,'status' => $this->filterFail? $this->backMeg:$this->stdStatus[$code],'data'=>$result]
        );
    }

    //验证 表单
    public function filter(Request $request,$arr)
    {
        $validator =  Validator::make($request->all(),$arr);
        if($validator->fails())
        {
            $this->backMeg = implode($validator->errors()->all(),',');
            $this->filterFail = true;
            return false;
        }
        return true;
    }

    //验证 user 权限
    public function check_token($remember_token)
    {
        try{
            $token= Manager::where('remember_token',$remember_token)
                ->where('token_expire','>',date('Y-m-d H:i:s'))
                ->first();
            if(count($token)>0){
                $this->admin_email =$token->email;
                $this->admin_name=$token->name;
                $this->admin_permission=$token->permission;
                $this->admin_company =$token->company;
                $this->admin_c_id =$token->c_id;
                $this->admin_id =$token->id;
                return true;
            } else {
                return false;
            }

        }catch (\Error $error){
            return false;
        }catch (\Exception $exception){
            return false;
        }


    }

}

