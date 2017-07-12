$(document).ready(function() {
	$(".g_ul1").attr("style", "display:none;");

    $(".g_p3").click(function() {
    	if($(".g_ul1").is(":hidden")){
    	   var val=1;
    	   $(".g_ul1").slideDown("");	
    	   $(".g_l1").rotate(90*val);	
        }else{
    	   $(".g_ul1").slideUp("");
    	   $(".g_l1").rotate(0);
        };
    });

    $(".g_shitu2").attr("style", "display:none;");

    // $(".g_ul1 ol li").click(function() {
    //     $(".g_shitu2").attr("style","display:block;");
    //     $(".g_p3,.g_ul1").attr("style","display:none;");
    //     $(".g_p_hide").attr("style","display:none;");
    //     $("#xiangmu_name").text($(this).text());
    //     $(".g_maincontent1_2").attr("style","display:none;");
    //     $(".g_maincontent1_3").attr("style","display:none;");
    //     $(".g_maincontent1_4").attr("style","display:none;");
    //     $(".g_maincontent1_5").attr("style","display:none;");
    //     $(".g_maincontent1_6").attr("style","display:none;");
    //     $(".g_maincontent1_7").attr("style","display:none;");
    //     $(".g_content_right_header").attr("style","display:block;");
    //     $(".g_maincontent1_1").attr("style","display:block;");
    //     $(".g_down").css("height",$(".g_content_right").height()-131);
    //     huatu1_1_1();
    //     huatu1_1_3();
    // });

    $(".g_ul1").on('click','ol li',function() {
        $(".g_shitu2").attr("style","display:block;");
        $(".g_p3,.g_ul1").attr("style","display:none;");
        $(".g_p_hide").attr("style","display:none;");
        $("#xiangmu_name").text($(this).text());
        $(".g_maincontent1_2").attr("style","display:none;");
        $(".g_maincontent1_3").attr("style","display:none;");
        $(".g_maincontent1_4").attr("style","display:none;");
        $(".g_maincontent1_5").attr("style","display:none;");
        $(".g_maincontent1_6").attr("style","display:none;");
        $(".g_maincontent1_7").attr("style","display:none;");
        $(".g_content_right_header").attr("style","display:block;");
        $(".g_maincontent1_1").attr("style","display:block;");
        $(".g_down").css("height",$(".g_content_right").height()-131);
        huatu1_1_1();
        huatu1_1_3();
    });

    $(".g_ul2").attr("style", "display:none;");

    $(".g_ul3").attr("style", "display:none;");

    $(".g_p4 p,.g_l2").click(function() {
        if($(".g_ul2").is(":hidden")){
    	   var val=1;
    	   $(".g_ul2").slideDown("");	
    	   $(".g_l2").rotate(90*val);	
        }else{
    	   $(".g_ul2").slideUp("");
    	   $(".g_l2").rotate(0);
        };
    });

    $(".g_p5 b,.g_l3").click(function() {
        if($(".g_ul3").is(":hidden")){
    	   var val=1;
    	   $(".g_ul3").slideDown("");	
    	   $(".g_l3").rotate(90*val);	
        }else{
    	   $(".g_ul3").slideUp("");
    	   $(".g_l3").rotate(0);
        };
    });

    $(".g_fanhui").click(function(){
        $(".g_shitu2").attr("style","display:none;");
        $(".g_p3,.g_ul1").attr("style","display:block;");
        $(".g_p_hide").attr("style","display:block;");
        $(".g_content_right_header").attr("style","display:none;");
        $(".g_maincontent1_1").attr("style","display:none;");
        $(".g_maincontent1_2").attr("style","display:none;");
        $(".g_maincontent1_3").attr("style","display:none;");
        $(".g_maincontent1_4").attr("style","display:none;");
        $(".g_maincontent1_5").attr("style","display:none;");
        $(".g_maincontent1_6").attr("style","display:none;");
        $(".g_maincontent1_7").attr("style","display:none;");
        $(".g_down").css("height",$(".g_content_right").height()-131);
    });

   
    $("li").mouseover(function(){
        $(this).css("color","#ef8282");
    });

    $("li").mouseout(function(){
        $(this).css("color","white");
    });

    $(".g_content_right_header").attr("style","display:none;");
    $(".g_maincontent1_1").attr("style","display:none;");
    $(".g_maincontent1_2").attr("style","display:none;");
    $(".g_maincontent1_3").attr("style","display:none;");
    $(".g_maincontent1_4").attr("style","display:none;");
    $(".g_maincontent1_5").attr("style","display:none;");
    $(".g_maincontent1_6").attr("style","display:none;");
    $(".g_maincontent1_7").attr("style","display:none;");
    /*
    关键数据
    */
    $(".g_ul2-1").click(function(){
        $(".g_maincontent1_2").attr("style","display:none;");
        $(".g_maincontent1_3").attr("style","display:none;");
        $(".g_maincontent1_4").attr("style","display:none;");
        $(".g_maincontent1_5").attr("style","display:none;");
        $(".g_maincontent1_6").attr("style","display:none;");
        $(".g_maincontent1_7").attr("style","display:none;");
        $(".g_content_right_header").attr("style","display:block;");
        $(".g_maincontent1_1").attr("style","display:block;");
        $(".g_ul2 li").css("background-color","#d71414");
        $(".g_ul2-1").css("background-color","#e42e2e");
        huatu1_1_1();
        huatu1_1_3();
    });

    /*
    新增设备
    */
    $(".g_ul2-2").click(function(){
        $(".g_maincontent1_1").attr("style","display:none;");
        $(".g_maincontent1_3").attr("style","display:none;");
        $(".g_maincontent1_4").attr("style","display:none;");
        $(".g_maincontent1_5").attr("style","display:none;");
        $(".g_maincontent1_6").attr("style","display:none;");
        $(".g_maincontent1_7").attr("style","display:none;");
        $(".g_content_right_header").attr("style","display:block;");
        $(".g_maincontent1_2").attr("style","display:block;");
        $(".g_ul2 li").css("background-color","#d71414");
        $(".g_ul2-2").css("background-color","#e42e2e");
        huatu1_2_1();
        huatu1_2_4();
    });

    /*
    新增用户
    */
    $(".g_ul2-3").click(function(){
        $(".g_maincontent1_1").attr("style","display:none;");
        $(".g_maincontent1_2").attr("style","display:none;");
        $(".g_maincontent1_4").attr("style","display:none;");
        $(".g_maincontent1_5").attr("style","display:none;");
        $(".g_maincontent1_6").attr("style","display:none;");
        $(".g_maincontent1_7").attr("style","display:none;");
        $(".g_content_right_header").attr("style","display:block;");
        $(".g_maincontent1_3").attr("style","display:block;");
        $(".g_ul2 li").css("background-color","#d71414");
        $(".g_ul2-3").css("background-color","#e42e2e");
        huatu1_3_1();
        huatu1_3_3();
    });

    /*
    活跃设备概况
    */
    $(".g_ul2-4").click(function(){
        $(".g_maincontent1_1").attr("style","display:none;");
        $(".g_maincontent1_2").attr("style","display:none;");
        $(".g_maincontent1_3").attr("style","display:none;");
        $(".g_maincontent1_5").attr("style","display:none;");
        $(".g_maincontent1_6").attr("style","display:none;");
        $(".g_maincontent1_7").attr("style","display:none;");
        $(".g_content_right_header").attr("style","display:block;");
        $(".g_maincontent1_4").attr("style","display:block;");
        $(".g_ul2 li").css("background-color","#d71414");
        $(".g_ul2-4").css("background-color","#e42e2e");
        huatu1_4_1();
        huatu1_4_4();
    });

    /*
    活跃玩家概况
    */
    $(".g_ul2-5").click(function(){
        $(".g_maincontent1_1").attr("style","display:none;");
        $(".g_maincontent1_2").attr("style","display:none;");
        $(".g_maincontent1_3").attr("style","display:none;");
        $(".g_maincontent1_4").attr("style","display:none;");
        $(".g_maincontent1_6").attr("style","display:none;");
        $(".g_maincontent1_7").attr("style","display:none;");
        $(".g_content_right_header").attr("style","display:block;");
        $(".g_maincontent1_5").attr("style","display:block;");
        $(".g_ul2 li").css("background-color","#d71414");
        $(".g_ul2-5").css("background-color","#e42e2e");
        huatu1_5_1();
        huatu1_5_4();
});

    /*
    留存用户设备
    */
    $(".g_ul2-6").click(function(){
        $(".g_maincontent1_1").attr("style","display:none;");
        $(".g_maincontent1_2").attr("style","display:none;");
        $(".g_maincontent1_3").attr("style","display:none;");
        $(".g_maincontent1_4").attr("style","display:none;");
        $(".g_maincontent1_5").attr("style","display:none;");
        $(".g_maincontent1_7").attr("style","display:none;");
        $(".g_content_right_header").attr("style","display:block;");
        $(".g_maincontent1_6").attr("style","display:block;");
        $(".g_ul2 li").css("background-color","#d71414");
        $(".g_ul2-6").css("background-color","#e42e2e");
        huatu1_6_1();
    });

    /*
    用户系统设备
    */
    $(".g_ul2-7").click(function(){
        $(".g_maincontent1_1").attr("style","display:none;");
        $(".g_maincontent1_2").attr("style","display:none;");
        $(".g_maincontent1_3").attr("style","display:none;");
        $(".g_maincontent1_4").attr("style","display:none;");
        $(".g_maincontent1_5").attr("style","display:none;");
        $(".g_maincontent1_6").attr("style","display:none;");
        $(".g_content_right_header").attr("style","display:block;");
        $(".g_maincontent1_7").attr("style","display:block;");
        $(".g_ul2 li").css("color","white");
        $(".g_ul2 li").css("background-color","#d71414");
        $(".g_ul2-7").css("background-color","#e42e2e");
        huatu1_7_1();
    });

    $('.form_date').datetimepicker({
        pickerPosition: "bottom-left",
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });


    /*
    波动预警
    */
    $(".g_ul3-1").click(function(){

    });

    /*
    运营日志
    */
    $(".g_ul3-2").click(function(){

    });

    /*
    关键数据：新增用户和设备
    */
    $(".g_ul2-1-1").click(function(){
        huatu1_1_1();
    });

    /*
    关键数据：活跃玩家和设备
    */
    $(".g_ul2-1-2").click(function(){
        huatu1_1_2();
    });

    /*
    关键数据：付费金额
    */
    $(".g_ul2-1-3").click(function(){
        huatu1_1_3();
    });

    /*
    关键数据：付费用户
    */
    $(".g_ul2-1-4").click(function(){
        huatu1_1_4();
    });

    /*
    关键数据：应用下载量
    */
    $(".g_ul2-1-5").click(function(){
        huatu1_1_5();
    });

    /*
    新增设备：新增设备数量
    */
    $(".g_ul2-2-1").click(function(){
        huatu1_2_1();
    });

    /*
    新增设备：首次使用时长
    */
    $(".g_ul2-2-2").click(function(){
        huatu1_2_2();
    });

    /*
    新增设备：设备品牌
    */
    $(".g_ul2-2-3").click(function(){
        huatu1_2_3();
    });

    /*
    新增设备：使用地区
    */
    $(".g_ul2-2-4").click(function(){
        huatu1_2_4();
    });

    /*
    新增用户：新增用户数量
    */
    $(".g_ul2-3-1").click(function(){
        huatu1_3_1();
    });

    /*
    新增用户：首次使用时长
    */
    $(".g_ul2-3-2").click(function(){
        huatu1_3_2();
    });

    /*
    新增用户：地区分布
    */
    $(".g_ul2-3-3").click(function(){
        huatu1_3_3();
    });

    /*
    活跃设备概况：日活跃设备
    */
    $(".g_ul2-4-1").click(function(){
        getAgoDay(1);
        huatu1_4_1();
    });

    /*
    活跃设备概况：周活跃设备
    */
    $(".g_ul2-4-2").click(function(){
        getAgoDay(7);
        huatu1_4_2();
    });

    /*
    活跃设备概况：月活跃设备
    */
    $(".g_ul2-4-3").click(function(){
        getAgoDay(30);
        huatu1_4_3();
    });

    /*
    活跃设备概况：已用天数
    */
    $(".g_ul2-4-4").click(function(){
        huatu1_4_4();
    });

    /*
    活跃玩家概况：日活跃设备
    */
    $(".g_ul2-5-1").click(function(){
        getAgoDay(1);
        huatu1_5_1();
    });

    /*
    活跃玩家概况：周活跃设备
    */
    $(".g_ul2-5-2").click(function(){
        getAgoDay(1);
        huatu1_5_2();
    });

    /*
    活跃玩家概况：月活跃设备
    */
    $(".g_ul2-5-3").click(function(){
        getAgoDay(1);
        huatu1_5_3();
    });

    /*
    活跃玩家概况：已用天数
    */
    $(".g_ul2-5-4").click(function(){
        huatu1_5_4();
    });


    /*
    留存用户设备：设备留存
    */
    $(".g_ul2-6-1").click(function(){
        huatu1_6_1();
    });

    /*
    留存用户设备：用户留存
    */
    $(".g_ul2-6-2").click(function(){
        huatu1_6_2();
    });

    /*
    用户系统设备：操作系统
    */
    $(".g_ul2-7-1").click(function(){
        huatu1_7_1();
    });

    /*
    用户系统设备：联网方式
    */
    $(".g_ul2-7-2").click(function(){
        huatu1_7_2();
    });

    /*
    用户系统设备：运营商
    */
    $(".g_ul2-7-3").click(function(){
        huatu1_7_3();
    });

    /*
    用户系统设备：设备型号
    */
    $(".g_ul2-7-4").click(function(){
        huatu1_7_4();
    });

    //左边自适应高度
    $(".g_shuju ul li").click(function(){
        if($(".g_content_left").height() < $(".g_content_right").height()){
            $(".g_down").css("height",$(".g_content_right").height()-131);
        }else{
           $(".g_down").css("height",$(".g_content_right").height()-131);
        }
    });


    $("#queding").click(function(){
    
    var startTime =  $("#date_start").val();
    var endTime =   $("#date_end").val();

    if (startTime == "" || endTime == "") {

            $("#DifDay").val(0);

        }

        else {

            var startNum = parseInt(startTime.replace(/-/g, ''), 10);

            var endNum = parseInt(endTime.replace(/-/g, ''), 10);

            if (startNum > endNum) {

                alert("结束时间不能在开始时间之前！");

            }

            else {

                $("#DifDay").val(DateDiff(startTime, endTime));  //调用/计算两个日期天数差的函数，通用

            }

        }

     //var day = DateDiff(startTime,endTime) + 1;
     // day = getCurrentTime();
     // console.log(day);
    });

    $("#last_day").click(function(){
        getAgoDay(1);
        // console.log(getAgoDay(1));
    }); 

    $("#7_day").click(function(){
        getAgoDay(7);
    });

    $("#30_day").click(function(){
        getAgoDay(30);
    });

    $("#all_day").click(function(){
        getAgoDay(30);
    });

});

