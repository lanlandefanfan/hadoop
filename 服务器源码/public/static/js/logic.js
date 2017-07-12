//计算两个日期相差天数
function DateDiff(sDate1, sDate2) {  //sDate1和sDate2是yyyy-MM-dd格式

 

    var aDate, oDate1, oDate2, iDays;

    aDate = sDate1.split("-");

    oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  //转换为yyyy-MM-dd格式

    aDate = sDate2.split("-");

    oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);

    iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); //把相差的毫秒数转换为天数

 
    return iDays;  //返回相差天数

}

//根据指定的一个日期和相差的天数，获取另外一个日期

//dateParameter为指定已经存在的日期yyyy-MM-dd  num为相差天数为整型 


function addByTransDate(dateParameter, num) {

 

    var translateDate = "", dateString = "", monthString = "", dayString = "";

    translateDate = dateParameter.replace("-", "/").replace("-", "/"); ;


    var newDate = new Date(translateDate);

    newDate = newDate.valueOf();

    newDate = newDate - num * 24 * 60 * 60 * 1000;  //备注 如果是往前计算日期则为减号 否则为加号

    newDate = new Date(newDate);

 

    //如果月份长度少于2，则前加 0 补位   

    if ((newDate.getMonth() + 1).toString().length == 1) {

        monthString = 0 + "" + (newDate.getMonth() + 1).toString();

    } else {

        monthString = (newDate.getMonth() + 1).toString();

    }

 

    //如果天数长度少于2，则前加 0 补位   

    if (newDate.getDate().toString().length == 1) {

 

        dayString = 0 + "" + newDate.getDate().toString();

    } else {

 

        dayString = newDate.getDate().toString();

    }

 

    dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString;

    return dateString;


}

// function dateArray(startTime,endTime){
// 	var days = DateDiff(startTime,endTime);
// 	var date;
// 		for(i=0;i<days;i++){
// 			if(days>0){
// 				date[i] = addByTransDate(endTime, days);
// 				days--;
// 			}
// 		}
// 	return date;	
// }

//获取当前时间
function getCurrentTime(){

	var  dateString = "", monthString = "", dayString = "";

    var newDate = new Date();

	//如果月份长度少于2，则前加 0 补位   

    if ((newDate.getMonth() + 1).toString().length == 1) {

        monthString = 0 + "" + (newDate.getMonth() + 1).toString();

    } else {

        monthString = (newDate.getMonth() + 1).toString();

    }


    //如果天数长度少于2，则前加 0 补位   

    if (newDate.getDate().toString().length == 1) {

 

        dayString = 0 + "" + newDate.getDate().toString();

    } else {

 

        dayString = newDate.getDate().toString();

    }

 

    dateString = newDate.getFullYear() + "-" + monthString + "-" + dayString;

    return dateString;
}
//获取当前时间num天前的数据
function getAgoDay(num){
	var startTime =  $("#date_start").val();
    var endTime =   $("#date_end").val();

    var day = addByTransDate(getCurrentTime(),num);
    return day;
}
