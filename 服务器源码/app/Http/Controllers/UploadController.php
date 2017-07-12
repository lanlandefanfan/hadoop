<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use Storage;

class UploadController extends Controller {

    /*提供给后台存储 5 用户特征描述 接口*/
    public function upload(Request $request,$xx){
        if($request->hasFile('file')){
            $filecontent=$request->file('file');
            $filename=$request->filename;
            $bool=Storage::disk($xx)->put($filename,file_get_contents($filecontent));
            return $bool?$this->stdResponse('1'):$this->stdResponse('-8');
        }
        return $this->stdResponse('-1');
    }


}
