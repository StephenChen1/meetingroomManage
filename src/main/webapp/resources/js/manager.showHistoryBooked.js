//管理员展示历史预约记录界面js

//自定义函数
(function ($) {
	
	//显示一条预约记录
	$.showHistoryBooked = function(oneBooked , i){
		//得到预约员工
		var staffBooked = oneBooked.name +"("+oneBooked.staffNumber +")";
		//会议室号
		var roomNumer = oneBooked.roomNumber ;
		//预约时间字符串
		var timeText = oneBooked.startDate + "~" +
					   oneBooked.endDate + " " +
					   oneBooked.startTime + "~" +
					   oneBooked.endTime ;
		//状态（即备注）
		var status = oneBooked.status ;
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
    				'</td><td>'+staffBooked +
    				'</td><td>' +roomNumber +
    				'</td><td>' +timeText +				
    				'</td><td>' + statusText + 
    			  '</td></tr>';
    	
    	//添加到表格中
    	$("#showMyHistoryBody").append(row);
	}

})(jQuery)



$(ducument).ready(function(){
	
	//定义全局变量，保存所有预约记录
	var allHistoryBooked ;
	
	
	//点击左侧菜单，即将苏朋友历史预约记录从后台得到
	//请求参数：无
	//返回数据和用户界面查看我的预约记录形式相似，返回对象数组，对象中包含着一次预约的时间、会议室和员工
	$.ajax({
    	type : "post",
    	url:"../booked/getAllHistoryBooked",
    	//contentType:"application/json",
        //data:JSON.stringify(data),
    	//data:data,
        success:function(result){
        	//赋值给全局变量
        	allHistoryBooked = result ;
        	//先删除表格原数据
        	$("#showHistoryBookedBody").find("tr").remove();
        	//遍历展示
        	for(var i = 0 ; i < result.length ; i++){
        		//调用自定义函数展示每一行数据
        		$.showHistoryBooked(result[i] , (i+1));
        	}
        }
	});
	
	//查询按钮点击事件
	//TODO
	
	
})