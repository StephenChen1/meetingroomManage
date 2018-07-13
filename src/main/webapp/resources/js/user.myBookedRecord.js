//用户预约记录界面js

//自定义函数
(function ($) {
	
	//显示一条预约记录
	$.showMyBooked = function(oneBooked , i){
		//会议室号
		var roomNumber = oneBooked.roomNumber ;
		//容纳人数
		var capability = oneBooked.capability ;
		//预约时间字符串
		var timeText = oneBooked.bookedTime.startDate + "~" +
					   oneBooked.bookedTime.endDate + " " +
					   oneBooked.bookedTime.startTime + "~" +
					   oneBooked.bookedTime.endTime ;
		//状态（即备注）
		var status = oneBooked.status ;
		//alert("status:"+status);
		var statusText ;
		if(status == 0){
			statusText = '<td class ="text-danger">已取消</td>';
		}else if(status == 1){
			statusText = '<td class ="text-success">预约成功</td>';
		}else{
			statusText = '<td class ="text-info">已结束</td>';
		}
		
		//表格行字符串
    	var row = '<tr><td>'+ i +
    				'</td><td>'+roomNumber +
    				'</td><td>' +capability +
    				'</td><td>' +timeText +				
    				'</td>' + statusText + 
    			  '</tr>';
    	
    	//添加到表格中
    	$("#showMyHistoryBody").append(row);
	}
	
})(jQuery)



$(document).ready(function(){
	
	//定义全局变量，保存所有预约记录，方便查询，点击左侧菜单即可修改
	var allMyBooked ;
	
	
	//点击左侧菜单即可得到我的预约记录
	$("#userBookedRecord").click(function(){
		//alert("我的预约记录");
		//从后台得到我的预约记录
		//请求参数：我的id
		//得到我的id
		var staffNumber = $("#userId").text();
		/*var data = {
				staffNumber:staffNumber	
		}*/
		//传到后台得到我的预约记录
		$.ajax({
	    	type : "post",
	    	url:"../room/getPersonalBooked",
	    	//contentType:"application/json",
	        //data:JSON.stringify(data),
	    	//data:data,
	        success:function(result){
	        	//得到预约信息对象数组
	        	allMyBooked = result ;
	        	//先删除表格原内容
	        	$("#showMyHistoryBody").find("tr").remove();
	        	//显示在表格上
	        	for(var i = 0 ; i < result.length ; i ++){
	        		$.showMyBooked(result[i],(i+1));
	        	}
	        
	        }
		});
		
		
	});
	
	
	
	
	
});