<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});
/*管理员相关用户数据交互接口*/
Route::group(['prefix'=>'a'],function(){
	Route::post('login','AdminController@login');
	Route::delete('logout','AdminController@logout');
	/*-------------------一级--------------------*/
	Route::get('alist','AdminController@getProject');
	Route::post('company','AdminController@createCompany');
	Route::post('add','AdminController@createApp');
	Route::get('ainfo/id/{id}','AdminController@getAppInfo');
	Route::post('edit','AdminController@updateApp');
	Route::post('create','AdminController@createAdmin');
	Route::post('permission/id/{id}','AdminController@setAdmin');
	Route::post('permission','AdminController@updateAdmin');
	Route::get('mlist','AdminController@getAdmin');
	Route::get('info/id/{id}','AdminController@getAdminInfo');
	Route::get('getlist','AdminController@get_app_list');
	Route::get('getapp','AdminController@get_app_info');
	
});
/* 获取数据接口 */
Route::group(['prefix'=>'d'],function(){
	Route::get('newuserdev','DataController@get_new_user_dev');
	Route::get('activeuserdev','DataController@get_Active_User_Device');
	Route::get('paymoney','DataController@get_pay_count');
	Route::get('payuser','DataController@get_pay_user');
	
	Route::get('newdev','DataController@get_new_dev');
	Route::get('devfirsttime','DataController@get_dev_first_time');
	Route::get('newuser','DataController@get_new_user');
	Route::get('userfirsttime','DataController@get_user_first_time');
	Route::get('download','DataController@get_downloaded_count');
	
	Route::get('dayactivedev','DataController@get_day_active_dev');
	Route::get('alreadyplaydev','DataController@already_play_dev');
	Route::get('dayactiveuser','DataController@get_day_active_user');
	Route::get('alreadyplayuser','DataController@already_play_user');
	
	Route::get('devicesurvive','DataController@get_survive_device');
	Route::get('usersurvive','DataController@get_survive_user');

    Route::get('app/{id}/{type}/{param}','DataController@getFeaturesData');
});

/*view 展示接口*/

Route::get('manager','ViewController@showData3');

Route::get('login','ViewController@login');
Route::get('mlist','AdminController@getAdmin');

Route::post('upload/{xx}','UploadController@upload');
//Route::get('/login_back',[ 'as' => 'login', 'uses' => 'LoginController@login']);


