
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>360DATA</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="{{URL::asset('static/css/generalAdmin.css')}}" type="text/css">
    <link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://momentjs.com/downloads/moment.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="http://cdn.sobekrepository.org/includes/jquery-rotate/2.2/jquery-rotate.min.js"></script>
    <script src="https://cdn.bootcss.com/echarts/3.6.2/echarts.js"></script>
    <script src="{{URL::asset('static/js/china.js')}}"></script>
    <script src="{{URL::asset('static/js/drawEcharts.js')}}"></script>
    <script src="{{URL::asset('static/js/huatu.js')}}"></script>
    <script src="{{URL::asset('static/js/generalAdmin.js')}}"></script>
    <script src="{{URL::asset('static/js/logic.js')}}"></script>

</head>

<body>
<div class="g_header">
    <div class="g_contentpanel">
        <img src="{{URL::asset('static/image/logo.png')}}" alt="logo">
        <ul>
            <li><a href="#">主页</a></li>
            <li><a href="#">帮助</a></li>
            <li><a href="#">设置</a></li>
            <li><a href="#">退出</a></li>
        </ul>
    </div>
</div>
<div class="g_content">
    <div class="g_content_left">
        <div class="g_content_left g_up">
            <img src="">
            <p class="g_p g_p1">{{ $name }}</p>
            <p class="g_p g_p2">个人自我座右铭</p>
        </div>
        <div class="g_content_left g_down">
            <div class="g_p3">
                <p>项目选择</p>
                <img class="g_jt g_l1" id="g_l1" src="{{URL::asset('static/image/jiantou.png')}}">
            </div>
            <div class="g_ul1" >
           		<ol id='applist'>
                   <!--<li>项目大数据</li>
                    <li>项目RPG游戏</li>
                    <li>项目防盗</li>-->
                </ol>
            </div>
            <div class="g_shitu2">
                <div class="g_xiangmu">
                    <p id="xiangmu_name">项目名称</p>
                </div>
                <div class="g_shuju">
                    <div class="g_p4">
                        <p>数据分析</p>
                        <img class="g_jt g_l2" src="{{URL::asset('static/image/jiantou.png')}}">
                        <ul class="g_ul2">
                            <li class="g_ul2-1">关键数据</li>
                            <li class="g_ul2-2">新增设备</li>
                            <li class="g_ul2-3">新增用户</li>
                            <li class="g_ul2-4">活跃设备概况</li>
                            <li class="g_ul2-5">活跃玩家概况</li>
                            <li class="g_ul2-6">留存用户设备</li>
                            <li class="g_ul2-7">用户系统设备</li>
                        </ul>
                    </div>
                    <div class="g_p5">
                        <b>管理设置</b>
                        <img class="g_jt g_l3" src="{{URL::asset('static/image/jiantou.png')}}">
                        <ul class="g_ul3">
                            <li class="g_ul3-1">波动预警</li>
                            <li class="g_ul3-2">运营日志</li>
                        </ul>
                    </div>
                    <img class="g_fanhui" src="{{URL::asset('static/image/fanhui.png')}}">
                </div>
            </div>
        </div>
    </div>

    <div class="g_content_right">
        <div class="g_p_hide">
            <p>请选择你的项目</p>
        </div>

        <div class="g_content_right_header">
            <p>起始日期：</p>
            <div class="date">
                <div class="controls input-append date form_date" data-date=""
                     data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input id="date_start" size="16" type="text" value="" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
            </div>
            <p>结束日期：</p> 
            <div class="date">
                <div class="controls input-append date form_date" data-date=""
                     data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input id="date_end" size="16" type="text" value="" readonly>
                    <span class="add-on"><i class="icon-remove"></i></span>
                    <span class="add-on"><i class="icon-th"></i></span>
                </div>
                <button type="button" class="btn btn-default btn-xs" id="queding">确定</button>
            </div>
            <ul>
                <li><a id="last_day" href="#">昨天</a></li>
                <li><a id="7_day" href="#">近七天</a></li>
                <li><a id="30_day" href="#">近三十天</a></li>
                <li><a id="all_day" href="#">全部</a></li>
            </ul>
        </div>
        <div class="g_maincontent g_maincontent1_1" id="g_maincontent1_1">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-1-1">新增用户和设配</button>
                    <button type="button" class="btn btn-default g_ul2-1-2">活跃玩家和设备</button>
                </div>
            </div>
            <div class="main1_1 main1_1_1" id="main1_1_1"></div>
        </div>
        <div class="g_maincontent g_maincontent1_1" id="g_maincontent1_1">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-1-3">付费金额</button>
                    <button type="button" class="btn btn-default g_ul2-1-4">付费用户</button>
                    <button type="button" class="btn btn-default g_ul2-1-5">应用下载量</button>
                </div>
            </div>
            <div class="main1_1 main1_1_2" id="main1_1_2"></div>
        </div>

        <div class="g_maincontent g_maincontent1_2" id="g_maincontent1_2">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-2-1">新增设备数量</button>
                    <button type="button" class="btn btn-default g_ul2-2-2">首次使用时长</button>
                    <button type="button" class="btn btn-default g_ul2-2-3">设备品牌</button>
                </div>
            </div>
            <div class="main1_2 main1_2_1" id="main1_2_1"></div>
        </div>
        <div class="g_maincontent g_maincontent1_2" id="g_maincontent1_2">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-2-4">使用地区</button>
                </div>
            </div>
            <div class="main1_2 main1_2_2" id="main1_2_2"></div>
        </div>

        <div class="g_maincontent g_maincontent1_3" id="g_maincontent1_3">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-3-1">新增用户数量</button>
                    <button type="button" class="btn btn-default g_ul2-3-2">首次使用时长</button>
                </div>
            </div>
            <div class="main1_3 main1_3_1" id="main1_3_1"></div>
        </div>
        <div class="g_maincontent g_maincontent1_3" id="g_maincontent1_3">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-3-3">地区分布</button>
                </div>
            </div>
            <div class="main1_3 main1_3_2" id="main1_3_2"></div>
        </div>

        <div class="g_maincontent g_maincontent1_4" id="g_maincontent1_4">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-4-1">日活跃设备</button>
                    <button type="button" class="btn btn-default g_ul2-4-2">周活跃设备</button>
                    <button type="button" class="btn btn-default g_ul2-4-3">月活跃设备</button>
                </div>
            </div>
            <div class="main1_4 main1_4_1" id="main1_4_1"></div>
        </div>
        <div class="g_maincontent g_maincontent1_4" id="g_maincontent1_4">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-4-4">已用天数</button>
                </div>
            </div>
            <div class="main1_4 main1_4_2" id="main1_4_2"></div>
        </div>

        <div class="g_maincontent g_maincontent1_5" id="g_maincontent1_5">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-5-1">日活跃玩家</button>
                    <button type="button" class="btn btn-default g_ul2-5-2">周活跃玩家</button>
                    <button type="button" class="btn btn-default g_ul2-5-3">月活跃玩家</button>
                </div>
            </div>
            <div class="main1_5 main1_5_1" id="main1_5_1"></div>
        </div>
        <div class="g_maincontent g_maincontent1_5" id="g_maincontent1_5">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-5-4">已用天数</button>
                </div>
            </div>
            <div class="main1_5 main1_5_2" id="main1_5_2"></div>
        </div>

        <div class="g_maincontent g_maincontent1_6" id="g_maincontent1_6">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-6-1">设备留存</button>
                    <button type="button" class="btn btn-default g_ul2-6-2">用户留存</button>
                </div>
            </div>
            <div class="main1_6" id="main1_6"></div>
        </div>

        <div class="g_maincontent g_maincontent1_7" id="g_maincontent1_7">
            <div class="g_maincontent_header">
                <div class="btn-group" role="group" aria-label="...">
                    <button type="button" class="btn btn-default g_ul2-7-1">操作系统</button>
                    <button type="button" class="btn btn-default g_ul2-7-2">联网方式</button>
                    <button type="button" class="btn btn-default g_ul2-7-3">运营商</button>
                    <button type="button" class="btn btn-default g_ul2-7-4">设备型号</button>
                </div>
            </div>
            <div class="main1_7" id="main1_7"></div>
        </div>
    </div>
</div>

<div class="g_footer">
    <div class="g_contentpanel-footer">
        <div class="g_contentpanel1">
            <img src="{{URL::asset('static/image/logo_small.png')}}" alt="logo">
            <ul>
                <li><a href="#">关于我们</a></li>
                <li><a href="#">加入我们</a></li>
                <li><a href="#">友情链接</a></li>
                <li><a href="#">帮助中心</a></li>
                <li><a href="#">活动中心</a></li>
                <li><a href="#">联系我们</a></li>
                <li><a href="#">官方认证</a></li>
                <li><a href="#">广告服务</a></li>
                <li><a href="#">侵权申诉</a></li>
                <li><a href="#">用户反馈</a></li>
            </ul>
        </div>
        <div class="g_contentpanel2">
            <p>传送门</p>
            <ul>
                <li><a href="#">QQ邮箱</a></li>
                <li><a href="#">微信</a></li>
                <li><a href="#">微博</a></li>
                <li><a href="#">Facebook</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
<script>
    //var remember_token=
    $.ajax({
        url:"a/getlist",
        type:"GET",
        data:{
            "remember_token":   "{{$token}}",
        },
        success:function(data){
            console.log(data);
            var array=data.data;
           
            for(var i=0;i<array.length;i++){
                console.log(array[i].id+"---"+array[i].name);
				$("#applist").append("<li> " + array[i].name + " </li>"); 
            }
            //$(".g_ul1").innerHTML()
        }


    });
</script>
</html>
