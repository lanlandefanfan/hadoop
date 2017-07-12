<?php
namespace App\Http\Controllers;
use Illuminate\Http\Request;
use Germey\Geetest\GeetestCaptcha;
class GeetestController extends Controller
{
	use GeetestCaptcha;
    public function postValidate(Request $request)
    {
        $this->validate($request, [
            'geetest_challenge' => 'geetest',
        ], [
            'geetest' => config('geetest.server_fail_alert')
        ]);
        return true;
    }
}

?>