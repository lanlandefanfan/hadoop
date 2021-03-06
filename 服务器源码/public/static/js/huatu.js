    /*
    关键数据：新增用户和设备
    */
function huatu1_1_1(){
    
    DoubleLine("main1_1_1",
        [18203, 23489, 29034, 104970, 131744, 630230,89999],
        [19203, 25789, 32034, 106970, 142744, 63079,91232],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    关键数据：活跃玩家和设备
    */
function huatu1_1_2(){
    DoubleLine("main1_1_1",
        [15000, 23489, 29034, 104970, 131744, 630230,89999],
        [19203, 25789, 32034, 106970, 100000, 63079,91232],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    关键数据：付费金额
    */
function huatu1_1_3(){
    Line("main1_1_2",
        [120, 132, 101, 134, 90, 230, 210],
        ['2017-7-1','2017-7-2','2017-7-3','2017-7-4','2017-7-5','2017-7-6','2017-7-7']);
}

    /*
    关键数据：付费用户
    */
function huatu1_1_4(){
     Line("main1_1_2",
        [120, 90, 101, 134, 90, 230, 210],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    关键数据：应用下载量
    */
function huatu1_1_5(){
     Line("main1_1_2",
        [120, 200, 101, 134, 90, 230, 210],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    新增设备：新增设备数量
    */
function huatu1_2_1(){ 
     Line("main1_2_1",
        [120, 200, 101, 134, 90, 90, 210],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    新增设备：首次使用时长
    */
function huatu1_2_2(){
    Line("main1_2_1",
        [120, 200, 101, 134, 90, 200, 210],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    新增设备：设备品牌
    */
function huatu1_2_3(){
    Line("main1_2_1",
        [120, 200, 210, 134, 90, 200, 210],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    新增设备：使用地区
    */
function huatu1_2_4(){
    Map("main1_2_2",
        [
        {name:'北京',value:10000},
        {name:'上海',value:23330},
        {name:'天津',value:45630},
        {name:'重庆',value:88880},
        {name:'哈尔滨',value:35640},
        {name:'长春',value:34507},
        {name:'沈阳',value:84930},
        {name:'呼和浩特',value:89630},
        {name:'石家庄',value:14540},
        {name:'乌鲁木齐',value:65630},
        {name:'兰州',value:78970},
        {name:'西宁',value:89890},
        {name:'西安',value:71230},
        {name:'银川',value:9950},
        {name:'郑州',value:921000},
        {name:'济南',value:66660},
        {name:'太原',value:42870},
        {name:'合肥',value:79870},
        {name:'武汉',value:96540},
        {name:'长沙',value:87000},
        {name:'南京',value:39660},
        {name:'贵阳',value:97000},
        {name:'成都',value:7700},
        {name:'昆明',value:43820},
        {name:'南宁',value:54000},
        {name:'拉萨',value:9700},
        {name:'杭州',value:78650},
        {name:'南昌',value:24870},
        {name:'广州',value:65000},
        {name:'福州',value:47000},
        {name:'台北',value:37830},
        {name:'海口',value:20000},
        {name:'香港',value:32000},
        {name:'澳门',value:43000}

    ]);
}
    
    /*
    新增用户：新增用户数量
    */
function huatu1_3_1(){
    Line("main1_3_1",
        [120, 132, 101, 134, 90, 230, 210],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    新增用户：首次使用时长
    */
function huatu1_3_2(){
    Line("main1_3_1",
        [120, 132, 101, 134, 90, 230, 210],
        ['0-1','1-2','3-4','5-6','6-7','7-8','8+']);
}

    /*
    新增用户：地区分布
    */
function huatu1_3_3(){
    Map("main1_3_2",
        [
        {name:'北京',value:10000},
        {name:'上海',value:23330},
        {name:'天津',value:45630},
        {name:'重庆',value:88880},
        {name:'哈尔滨',value:35640},
        {name:'长春',value:34507},
        {name:'沈阳',value:84930},
        {name:'呼和浩特',value:89630},
        {name:'石家庄',value:14540},
        {name:'乌鲁木齐',value:65630},
        {name:'兰州',value:78970},
        {name:'西宁',value:89890},
        {name:'西安',value:71230},
        {name:'银川',value:9950},
        {name:'郑州',value:921000},
        {name:'济南',value:66660},
        {name:'太原',value:42870},
        {name:'合肥',value:79870},
        {name:'武汉',value:96540},
        {name:'长沙',value:87000},
        {name:'南京',value:39660},
        {name:'贵阳',value:97000},
        {name:'成都',value:7700},
        {name:'昆明',value:43820},
        {name:'南宁',value:54000},
        {name:'拉萨',value:9700},
        {name:'杭州',value:78650},
        {name:'南昌',value:24870},
        {name:'广州',value:65000},
        {name:'福州',value:47000},
        {name:'台北',value:37830},
        {name:'海口',value:20000},
        {name:'香港',value:32000},
        {name:'澳门',value:43000}

    ]);
}
    
    /*
    活跃设备概况：日活跃设备
    */
function huatu1_4_1(){
     Line("main1_4_1",
        [120],
        [1]);
}

    /*
    活跃设备概况：周活跃设备
    */
function huatu1_4_2(){
    Line("main1_4_1",
        [100,20,03,04,05,36,07],
        [01,02,03,04,05,06,07]);
}

    /*
    活跃设备概况：月活跃设备
    */
function huatu1_4_3(){
    Line("main1_4_1",
        [100,20,03,04,05,36,07,08,09,50,11,12,13,14,15,78,17,18,19,20,
            21,59,23,24,25,42,27,28,29,30,31],
        [01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,31]);
}

    /*
    活跃设备概况：已用天数
    */
function huatu1_4_4(){
    Bar("main1_4_2",
        [18203, 23489, 29034, 104970, 131744, 630230],
        ['0-1','1-2','2-3','3-4','4-5','5+']);
}

    /*
    活跃玩家概况：日活跃设备
    */
function huatu1_5_1(){
    Line("main1_5_1",
        [100],
        [01]);
}

    /*
    活跃玩家概况：周活跃设备
    */
function huatu1_5_2(){
    Line("main1_5_1",
        [100,20,03,04,05,36,20],
        [01,02,03,04,05,06,07]);    
}

    /*
    活跃玩家概况：月活跃设备
    */
function huatu1_5_3(){
    Line("main1_5_1",
        [100,20,03,04,05,36,07,08,09,50,11,12,13,14,15,78,17,18,19,20,
            21,59,23,24,25,42,27,28,29,30,31],
        [01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,
            21,22,23,24,25,26,27,28,29,30,31]);
}

    /*
    活跃玩家概况：已用天数
    */
function huatu1_5_4(){
    Bar("main1_5_2",[18203, 23489, 29034, 400000, 131744, 630230],['0-1','1-2','2-3','3-4','4-5','5+']);
}

    /*
    留存用户设备：设备留存
    */
function huatu1_6_1(){
    Line("main1_6",
        [120, 132, 101, 134, 90, 230, 210],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    留存用户设备：用户留存
    */
function huatu1_6_2(){
    Line("main1_6",
        [120, 132, 101, 134, 90, 80, 210],
        ['周一','周二','周三','周四','周五','周六','周日']);
}

    /*
    用户系统设备：操作系统
    */
function huatu1_7_1(){
    Bar("main1_7",
        [18203, 23489, 29034, 104970, 131744, 630230],
        ['0-1','1-2','2-3','3-4','4-5','5+']);
}

    /*
    用户系统设备：联网方式
    */
function huatu1_7_2(){
    Bar("main1_7",
        [18203, 340000, 29034, 104970, 131744, 630230],
        ['0-1','1-2','2-3','3-4','4-5','5+']);
}

    /*
    用户系统设备：运营商
    */
function huatu1_7_3(){
    Bar("main1_7",
        [18203, 340000, 29034, 104970, 90000, 630230],
        ['0-1','1-2','2-3','3-4','4-5','5+']);
}

    /*
    用户系统设备：设备型号
    */
function huatu1_7_4(){
    Bar("main1_7",
        [500000, 340000, 29034, 104970, 131744, 630230],
        ['0-1','1-2','2-3','3-4','4-5','5+']);
}