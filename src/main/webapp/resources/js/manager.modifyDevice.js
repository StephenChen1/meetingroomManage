//管理员修改设备信息界面js

//自定义函数
(function ($) {
	
	//显示一个修改设备信息
	$.showDeviceModify = function(device , i){
		//表格行字符串
    	var row = '<tr><td>'+ i +
    				'</td><td>'+device.deviceId +
    				'</td><td>' +device.deviceName +
    				'</td><td>' +device.deviceType +
    				'</td><td><div>'+
	         			'<button class="btn btn-success btn-xs modifyDevicePopB" >修改</button>'+
	         			'<button class="btn btn-success btn-xs deleteDevicePopB" >删除</button>'+
    			    '</div></td></tr>' ;
    			  
    	
    	//添加到表格中
    	$("#modifyDeviceBody").append(row);
	}
})(jQuery)



$(document).ready(function(){
	
	//定义全局变量。保存所有设备信息
	var allDeviceM ;
	
	
	//点击左侧菜单，即从后台得到所有设备信息
	$("#").click(function(){
		//请求参数：无
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
	        	$("#modifyDeviceBody").find("tr").remove();
	        	
	        	//遍历展示设备信息
	        	for(var i = 0 ; i < result.length ; i ++){
	        		//调用自动义函数展示
	        		$.showDeviceModify(result[i],(i+1));
	        	}
	        }
		});
	})
	
	//当点击修改按钮时，弹出模态框，并填上模态框
	//TODO
	
	//当点击删除按钮时，弹出警示模态框
	//TODO
	
	
	//弹出模态框保存按钮点击事件
	$("#").click(function(){
		//设备id
		
		//设备名
		
		//设备型号
		
		//封装参数
		var data = {
				
		}
		//传到后台修改该设备，返回布尔值
		$.ajax({
	    	type : "post",
	    	url:"../device/modifyDevice",
	    	//contentType:"application/json",
	        //data:JSON.stringify(data),
	    	data:data,
	        success:function(result){
	        	
	        }
		});
		
		
	});
	
	//删除模态框确定按钮点击事件
	$("#confirmDeleteDevice").click(function(){
		
		//设备id
		
		//封装参数
		var data = {
				
		}
		//传到后台，删除该id的设备,返回布尔值
		$.ajax({
	    	type : "post",
	    	url:"../device/deleteDeviceById",            
	    	//contentType:"application/json",
	        //data:JSON.stringify(data),
	    	data:data,
	        success:function(result){
	        	//从界面删除这个设备（即这行）
	        }
		});
		
	})
	
	
	
	
})