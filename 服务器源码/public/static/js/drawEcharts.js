/*
柱状图
*/
function Bar(BarID,BarData,BarYaxis) {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(BarID));

    // 指定图表的配置项和数据
    var data = BarData;
    var yAxis = BarYaxis;

    option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: yAxis
        },
        series: [
            {
                type: 'bar',
                data: data
            }
        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);


}

/*
折线图
*/
function Line(LineID,LineData,LineXaxis) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(LineID));

    // 指定图表的配置项和数据
    var data = LineData;
    var xAxis = LineXaxis;


    var option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xAxis
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                type: 'line',
                stack: '总量',
                data: data
            },

        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

}

/*
双折线图
*/
function DoubleLine(DLid,DLUserNum,DLDeviNum,DLXaxis) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(DLid));

    // 指定图表的配置项和数据
    var UserNum = DLUserNum;
    var DeviceNum = DLDeviNum;
    var xAxis = DLXaxis;

    option = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        legend: {
            data:['用户','设备'] 
        },

        xAxis: {
            type: 'category',
            boundaryGap: [0, 0.01],
            data: xAxis
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {   
                name:"用户",    
                type: 'line',
                data: UserNum
            },
            {
                name:"设备",     
                type: 'line',
                data: DeviceNum
            }


        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

/*
地图
*/
function Map(MapID,MapData){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(MapID));

    // 指定图表的配置项和数据

    //这里赋值数据
    var data = MapData;

    var geoCoordMap = {
       "北京":[116.46,39.92],
        "上海":[121.48,31.22],
        "天津":[117.2,39.13],
        "重庆":[106.54,29.59],
        "哈尔滨":[126.63,45.75],
        "长春":[125.35,43.88],
        "沈阳":[123.38,41.8],
        "呼和浩特":[111.65,40.82],
        "石家庄":[114.48,38.03],
        "乌鲁木齐":[87.68,43.77],
        "兰州":[103.73,36.03],
        "西宁":[101.74,36.56],
        "西安":[108.93,34.27],
        "银川":[106.27,38.47],
        "郑州":[113.65,34.76],
        "济南":[117,36.65],
        "太原":[112.33,37.87],
        "合肥":[117.27,31.86],
        "武汉":[114.31,30.52],
        "长沙":[113,28.21],
        "南京":[118.78,32.04],
        "成都":[104.06,30.67],
        "贵阳":[106.71,26.57],
        "昆明":[102.73,25.04],
        "南宁":[108.33,22.84],
        "拉萨":[91.11,29.97],
        "杭州":[120.19,30.26],
        "南昌":[115.89,28.68],
        "广州":[113.23,23.16],
        "福州":[119.30,26.08],
        "台北":[121.50,25.05],
        "海口":[110.35,20.02],
        "香港":[114.10,22.20],
        "澳门":[113.33,22.13]

    };
    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };

    option = {
        backgroundColor: '#fff',
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                return params.name + ' : ' + params.value[2];
            }
        },

        //这里定义坐标尺
        visualMap: {
            min: 0,
            max: 100000,
            calculable: true,
            inRange: {
                color: ['#50a3ba', '#eac736', '#d94e5d']
            },
            textStyle: {
                color: '#121010'
            }
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: false
                }
            },
            roam:true,
            itemStyle: {
                normal: {
                    areaColor: '#fff',
                    borderColor: '#e3e2e2'
                },
                emphasis: {
                    areaColor: '#fce3e3'
                }
            }
        },
        series: [
            {
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(data),

                symbolSize: function (val) {
                    if(val[2]<=100000){return val[2] / 5000;}
                    else{val[2]=100000;return val[2]/5000;}
                },

                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    emphasis: {
                        borderColor: '#fff',
                        borderWidth: 1
                    }
                }

            },
            {
                name: 'Top 5',
                type: 'effectScatter',
                coordinateSystem: 'geo',
                data: convertData(data.sort(function (a, b) {
                    return b.value - a.value;
                }).slice(0, 6)),
                symbolSize: function (val) {
                    if(val[2]<=100000){return val[2] / 5000;}
                    else{val[2]=100000;return val[2]/5000;}



                },
                showEffectOn: 'render',
                rippleEffect: {
                    brushType: 'stroke'
                },
                hoverAnimation: true,
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#f4e925',
                        shadowBlur: 5,
                        shadowColor: '#b5b2b2'
                    }
                },
                zlevel: 1
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

}