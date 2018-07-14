//用户预约界面js


//自定义函数
(function ($) {
        //函数定义，将可预约对象展示在查看可预约表格中
		//参数为会议室对象和第几个会I也是记录
        $.showCanBooked = function (canBooked,i) {
        	
        	//可预订时段id
        	var canBookedId = canBooked.canBookedId ;
        	//得到会议室编号
        	var roomNumber = canBooked.roomNumber;
        	//得到会议室容纳人数
        	var capability = canBooked.capability;
        	//得到设备信息字符串
        	/*var devicesText ;
        	for(var j = 0 ; j < canBooked.devices.length ; j++){
        		devicesText = devicesText + canBooked.devices[j].name +"*" +
        		devicesText + canBooked.devices[j].count +"\n";
        	}*/
        	
        	//得到该可预约时间段id（不展示出来，预约时会上传至后台，方便判断）
        	var canBookedId = canBooked.timeCanBookedId ;
        	
        	//得到可预约时间段字符串组合，一个时间段写一行数据
        	var canBookedText ="";
        	
        	canBookedText = canBooked.startDate + "~" +
        						canBooked.endDate +"  " +
        						canBooked.startTime+ "~" +
        						canBooked.endTime +"\n";
        		        		
        		//表格行字符串
            	var row = '<tr><td class = "index">'+ i +
            				'</td><td class = "roomNumber">'+roomNumber +
            				'</td><td>' +capability +
            				'</td><td class = "canBookedId" value="'+canBooked.canBookedId+'">' +canBookedText +
            				'</td><td>' +'<button class="btn btn-success btn-xs bookedPop" >预约</button>'+ 
            			  '</td></tr>';
            	//往表格添加一行
            	$("#BookedBody").append(row);

        	
        }
})(jQuery);       




$(document).ready(function(){

	//定义全局变量，存放当前所有可预约时间记录，点击左侧菜单即可更新
	var allCanBookedTime ;
	//定义全局变量，预约时间id，点击表格预约按钮即可更新该值
	var timeId ;
	//时间全局变量，方便提交时进行判断
	//定义当前行开始日期
	var startDate ;
	//定义当前行结束日期
	var endDate  ;
	//定义当前行开始时间
	var startTime ; 
	//定义当前行结束时间
	var endTime ; 
	
	
	//点击左侧预约菜单及从后台得到最新的可预约数据
	$("#userBookedRoom").click(function(){
		//alert("888");
		//请求参数无，后台获取所有会议室可预约时间，
		//一个时间段一行，点击预约,最小时间最大时间受此限制
		//返回的是可预约表的全部内容，为对象数组，一个会议室封装成一个对象
		//里面包括属性：会议室号，容纳人数，设备信息(对象数组），可预约时间(对象数组）
		//其中设备信息包括设备id，设备名，设备数量，设备型号
		//可预约时间包括可预约id，开始日期，结束日期，开始时间，结束时间
		$.ajax({
	    	type : "post",
	    	url:"../staff/getAllCanBooked",
	    	//contentType:"application/json",
	        //data:JSON.stringify(data),
	    	//data:data,
	        success:function(result){
	        	//赋值给全局变量，以便后面调用
	        	allCanBookedTime = result ;
	        	//先清除原数据
	        	$("#BookedBody").find("tr").remove();
	        	//编号
	        	var index = 0 ;
	        	//遍历展示这些数据在表格上
	        	for(var i = 0 ; i < result.length ; i ++){
	        		
	        		//调用方法展示每一条记录
	        		$.showCanBooked(result[i],(i+1));
	        		//得到当前编号，一条时间段就一个编号
	        		//index = index + result[i].time.length ;
	        	}
	        }
		});
		
	});
	
	//给表格中的预约按钮设置点击即弹出模态框
	$(document).on('click','.bookedPop',function(){
		//弹出模态框
		$("#bookedPop").modal("toggle");
		//将开始时间，结束时间，最大时间，最小时间设定，填进模态框
		//得到当前行号
		var index = $(this).parents("tr").find(".index").text();
		
		//得到当前行会议室号
		var roomNumber = $(this).parents("tr").find(".roomNumber").text();
		//得到当前时间段id
		 timeId = $(this).parents("tr").find(".canBookedId").attr("value");
		 //alert(index);
		 //alert(roomNumber);
		 //alert("timeId1:"+timeId);
		
		
		for(var i = 0 ; i　< allCanBookedTime.length ; i++){
			if(allCanBookedTime[i].canBookedId == timeId){			
					
					//找到了这个时间段,得到时间
					startDate = allCanBookedTime[i].startDate;
					endDate = allCanBookedTime[i].endDate;
					startTime = allCanBookedTime[i].startTime;
					endTime = allCanBookedTime[i].endTime;
					break ;
			}
		}
		
		//alert(startDate);
		//alert(endDate);
		//alert(startTime);
		//alert(endTime);
		
		//用这些时间设置输入框
		//会议室号
		$("#roomNumberBooked").val(roomNumber);
		$("#roomNumberBooked").attr("disabled", true); //设置为不可编辑
		//开始日期(设置默认值和最大最小值）
		$("#startDateBooked").val(startDate);
		$('#startDateBooked').attr('min',startDate);
		$('#startDateBooked').attr('max',endDate);
		//结束日期(设置默认值和最大最小值）
		$("#endDateBooked").val(endDate);
		$('#endDateBooked').attr('min',startDate);
		$('#endDateBooked').attr('max',endDate);
		//开始时间(设置默认值和最大最小值）
		$("#startTimeBooked").val(startTime);
		$('#startTimeBooked').attr('min',startTime);
		$('#startTimeBooked').attr('max',endTime);
		//结束时间(设置默认值和最大最小值）
		$("#endTimeBooked").val(endTime);
		$('#endTimeBooked').attr('min',startTime);
		$('#endTimeBooked').attr('max',endTime);
		

		
	});
	
	
	
	//预约弹出框预约按钮点击事件
	$("#confirmBooked").click(function(){
		
		//alert("点击保存");
		
		//得到会议室号
		var roomNumber = $("#roomNumberBooked").val();
		//得到时间段id
		//alert("timeId:" + timeId);
		//得到开始日期
		var startDate = $("#startDateBooked").val();
		//得到结束日期
		var endDate = $("#endDateBooked").val();
		//得到开始时间
		var startTime = $("#startTimeBooked").val();
		//得到结束时间
		var endTime = $("#endTimeBooked").val();
		
		//比较：结束时间不能小于开始时间
		//var starttime = $('#txtStorageTimeStart').val();
		//var endtime = $('#txtStorageTimeEnd').val();
		//var start = new Date(startDate.replace("-", "/").replace("-", "/"));
		//var end = new Date(endDate.replace("-", "/").replace("-", "/"));
		/*if(end<start){  
			return false;  
		} */
		//alert("start: "+ start);
		//alert("end:" + end);
		//判断结束时间不能大于开始时间
		var start = startDate + " " + startTime ;
		var end = endDate + " " + endTime ;
		//开始时间转换为标准日期
		var startT = start.replace("-","/");//替换字符，变成标准格式  
		var startD = new Date(Date.parse(startT)); 
		//结束时间转换为结束日期
		var endT = endDate + " " + endTime ;
		var endD = new Date(Date.parse(endT));
		
		//alert(startD);
		//alert(endD);
		//另外，判断24小时时间
		var date = new Date();  
	    var a = startTime.split(":");  
	    var b = endTime.split(":");  
	  
		
		
		if(end >= start && date.setHours(a[0],a[1]) < date.setHours(b[0],b[1])){
			var myBookTime = new Object();
			myBookTime.startDate = startDate ;
			myBookTime.endDate = endDate ;
			myBookTime.startTime = startTime ;
			myBookTime.endTime = endTime ;
		
			//封装参数
			var data = {
					
					canBookId:timeId,
					myBookTime:myBookTime 
			}
			
			
			//alert(roomNumber);
			//alert(timeId);
			//alert(startDate);
			//alert(endDate);
			//alert(startTime);
			//alert(endTime);
			
			//接口未开放
			//发送该预约记录给后台,预约
			$.ajax({
		    	type : "post",
		    	url:"../room/book",
		    	contentType:"application/json",
		        data:JSON.stringify(data),
		    	//data:data,
		        success:function(result){
		        	//TODO返回布尔值
		        	//if(result.length > 0){
		        		//返回的是数组，但数组可能为空
		        		alert("预约已提交");
		        	//}
		        }
			});
		}else{
			//提示结束时间不能小于开始时间
			alert("结束时间不能早于开始时间");
		}
		
	});
	
})





