//用户预约界面js


//自定义函数
(function ($) {
        //函数定义，将可预约对象展示在查看可预约表格中
		//参数为会议室对象和第几个会I也是记录
        $.showCanBooked = function (canBooked,i) {
        	//得到会议室编号
        	var roomNumber = canBooked.roomNumber;
        	//得到会议室容纳人数
        	var capability = canBooked.capability;
        	//得到设备信息字符串
        	var devicesText ;
        	for(var j = 0 ; j < canBooked.devices.length ; j++){
        		devicesText = devicesText + canBooked.devices[j].name +"*" +
        		devicesText + canBooked.devices[j].count +"\n";
        	}
        	
        	//得到该可预约时间段id（不展示出来，预约时会上传至后台，方便判断）
        	var canBookedId = canBooked.timeCanBookedId ;
        	
        	//得到可预约时间段字符串组合，一个时间段写一行数据
        	var canBookedText ;
        	for(var j = 0 ; j < canBooked.time.length ; j++){
        		canBookedText = canBooked.time[j].startDate + "~" +
        						canBooked.time[j].endDate +"  " +
        						canBooked.time[j].startTime+ "~" +
        						canBooked.time[j].endTime +"\n";
        		        		
        		//表格行字符串
            	var row = '<tr><td>'+(i+j)+
            				'</td><td class = "roomNumber">'+roomNumber +
            				'</td><td>' +capability +
            				'</td><td>' +devicesText +
            				'</td><td class = "timeId" value="'+canBooked.time[j].timeId+'">' +canBookedText +
            				'</td><td>' +'<button class="btn btn-success btn-xs bookedPop" >预约</button>'+ 
            			  '</td></tr>';
            	//往表格添加一行
            	$("#BookedBody").append(row);
        		
        		
        	}
        
        	
        	
        	
        	
        }
})(jQuery);       




$(document).ready(function(){

	//定义全局变量，存放当前所有可预约时间记录，点击左侧菜单即可更新
	var allCanBookedTime ;
	//定义全局变量，预约时间id，点击表格预约按钮即可更新该值
	var timeId ;
	
	
	//点击左侧预约菜单及从后台得到最新的可预约数据
	$("#userBookedRoom").click(function(){
		
		//请求参数无，后台获取所有会议室可预约时间，
		//一个时间段一行，点击预约,最小时间最大时间受此限制
		//返回的是可预约表的全部内容，为对象数组，一个会议室封装成一个对象
		//里面包括属性：会议室号，容纳人数，设备信息(对象数组），可预约时间(对象数组）
		//其中设备信息包括设备id，设备名，设备数量，设备型号
		//可预约时间包括可预约id，开始日期，结束日期，开始时间，结束时间
		$.ajax({
	    	type : "post",
	    	url:"../timeCanBooked/getAll",
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
	        		index = index + result[i].time.length ;
	        	}
	        }
		});
		
	});
	
	//给表格中的预约按钮设置点击即弹出模态框
	$(document).on('click','.bookedPop',function(){
		//弹出模态框
		$("#bookedPop").modal("toggle");
		//将开始时间，结束时间，最大时间，最小时间设定，填进模态框
		//得到当前行会议室号
		var roomNumber = $(this).parents("tr").find(".roomNumber").text();
		//得到当前时间段id
		 timeId = $(this).parents("tr").find(".timeId").attr("value");
		//定义当前行开始日期
		var startDate ;
		//定义当前行结束日期
		var endDate  ;
		//定义当前行开始时间
		var startTime ; 
		//定义当前行结束时间
		var endTime ; 
		
		for(var i = 0 ; i　< allCanBookedTime.length ; i++){
			if(allCanBookedTime[i].roomNumber == roomNumber){
				for(var j = 0 ; j < allCanBookedTime[i].time.length ; j++){
					if(j < allCanBookedTime[i].time[j].timeId == timeId){
						//找到了这个时间段,得到时间
						startDate = allCanBookedTime[i].time[j].startDate;
						endDate = allCanBookedTime[i].time[j].endDate;
						startTime = allCanBookedTime[i].time[j].startTime;
						endTime = allCanBookedTime[i].time[j].endTime;
						break ;
					}
				}
				break ;
			}
		}
		
	
		
		
		
		
		
		
		
	});
	
	
	
	//预约弹出框预约按钮点击事件
	$("#confirmBooked").click(function(){
		
		//得到会议室号
		var roomNumber = $(this).parents("tr").find(".roomNumber").text();
		//得到时间段id
		var timeId = timeId;
		//得到开始日期
		var startDate = $("#startDateBooked").val();
		//得到结束日期
		var endDate = $("#endDateBooked").val();
		//得到开始时间
		var startTime = $("#startTimeBooked").val();
		//得到结束时间
		var endTime = $("#endTimeBooked").val();
		
		
			//封装参数
			var data = {
					roomNumber:roomNumber ,
					timeId:timeId,
					startDate:startDate,
					endDate:endDate,
					startTime:startTime,
					endTime:endTime
			}
			
			//发送该预约记录给后台,预约
			$.ajax({
		    	type : "post",
		    	url:"../booked/commitOne",
		    	//contentType:"application/json",
		        //data:JSON.stringify(data),
		    	data:data,
		        success:function(result){
		        	//TODO返回布尔值
		        	if(result){
		        		//TODO
		        	}
		        }
			});
			
		
	});
	
})





