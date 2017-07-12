<?php

namespace App\Http\Controllers;

use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Http\Request;
use Validator;
use App\Manager;
use GeetestLib;
class ViewController extends Controller
{
	public function login(){
		return view('login');
	}
	public function showData3(Request $request){
		$remember_token = $_GET['token'];
      	if(!$this->check_token($remember_token)){
        	return $this->stdResponse('-3');
      	}
      	
     //   $manager = Manager::where('id',$id)->first();
        return view('Data3',['token'=>$remember_token,'name'=>$this->admin_name]);
    }
}

