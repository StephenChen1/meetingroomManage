//展示所有设备界面js

//自定义函数
(function ($) {
	
	//显示一个修改设备信息
	$.showDevice = function(device , i){
		//表格行字符串
    	var row = '<tr><td>'+ i +
    				'</td><td>'+result.deviceId +
    				'</td><td>' +result.deviceName +
    				'</td><td>' +result.deviceType +
    				'</td></tr>' ;
    			  
    	
    	//添加到表格中
    	$("#showDeviceBody").append(row);
	}
})(jQuery)


$(document).ready(function(){
	
	
	//点击左侧菜单即可得到所有设备信息
	$("#").click(function(){
		
		//返回值：所有设备对象数组，包括设备id，设备名，设备型号
		$.ajax({
	    	type : "post",
	    	url:"../device/getAllDevice",
	    	//contentType:"application/json",
	        //data:JSON.stringify(data),
	    	//data:data,
	        success:function(result){
	        	//赋值给全局变量
	        	allDeviceM = result ;
	        	//先删除表格原数据内容
	        	$("#showDeviceBody").find("tr").remove();
	        	
	        	//遍历展示设备信息
	        	for(var i = 0 ; i < result.length ; i ++){
	        		//调用自动义函数展示
	        		$.showDevice(result[i],(i+1));
	        	}
	        }
		});
		
		
	});
	
	
})