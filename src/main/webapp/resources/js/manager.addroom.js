//管理员界面的添加会议室模块

$(document).ready(function(){

	//定义全局变量，会议室号，根据按钮点击事件改变
	var roomNumber ;
	
	
	//设置空闲时间按钮点击事件,更改当前操作的会议室号
	$(document).on('click','.setTimeBtn',function(){
		roomNumber = $(this).parents("tr").find(".roomNumber").text();
		//alert("roomNumber:" + roomNumber);
	});
	
	//设置设备信息按钮点击事件，更改当前操作的会议室号,更新设备下拉框
	$(document).on('click','.setDeviceBtn',function(){
		roomNumber = $(this).parents("tr").find(".roomNumber").text();
		//alert("roomNumber:" + roomNumber);
		//更新设备下拉框
		//从后台得到所有设备的信息,将设备名（设备id）选项添加到select
		$.ajax({
	    	type : "post",
	    	url:"../device/getAll",
	    	//contentType:"application/json",
	        //data:JSON.stringify(data),
	    	//data:data,
	        success:function(result){
	        	//TODO
	        	for(var i = 0 ; i < result.length ; i ++){
    	    		  var text = result[i];
    	    		  $("#yearOptionsEnter").append("<option value='"+text+"'>"+text+"</option>"); 
    	    	  }
	        }
	    });		
	});
	
	
	
	
	
	
	//新增会议室弹出框确认按钮点击事件
	$("#confirmAddRoom").click(function(){
		
		//得到会议室号
		var roomNumber = $("#roomNumberInput").val();
		//得到容纳人数
		var capability = $("#capabilityInput").val();
		//封装参数
		var data = {
				roomNumber:roomNumber,
				capability:capability
		}
		//AJAX
		$.ajax({
	    	type : "post",
	    	url:"../room/add",
	    	//contentType:"application/json",
	        //data:JSON.stringify(data),
	    	data:data,
	        success:function(result){
	        	//TODO
	        
	        }
	    });		
	 });
	
	
	
	
	//因为动态增加的元素，使用原本的$(selector).click()不起作用，所以用下面这种方法
	//设置空闲时间弹出框 - 按钮点击事件
	$(document).on('click','.deleteFreePane',function(){
		//alert("删除键被点击了！");
		//删除所在的父级为form的元素，由于不是直接父级，因此是parents,不是parent
		$(this).parents("form").remove();
	})
	
	
	//新增表单
	//设置空闲时间弹出框  + 按钮点击事件
	$(document).on('click','.addFreePane',function(){
		//alert("+键被点击了");
		//新表单的样式，字符串使用单双引号均可，主要看里面的是用什么，反着来就行，不用转义
		var form = '<form class="form-horizontal freeTime"  onsubmit="return false;">' +
		'<div class="form-group "> '+
    		'<label  class="col-xs-3 control-label">开始日期：</label>'+
    		'<div class="col-xs-6 ">'+
        		'<input type="date" class="form-control input-sm duiqi startDate" value="2018-09-24" min="2018-09-16" />'+
        		'</div>'+
		'</div>'+
		'<div class="form-group ">'+
    		'<label  class="col-xs-3 control-label">结束日期：</label>'+
    		'<div class="col-xs-6 ">'+
    			'<input type="date" class="form-control input-sm duiqi endDate" value="2018-09-24" min="2018-09-16" />'+
    		'</div>'+
		'</div>'+
		'<div class="form-group ">'+
    		'<label  class="col-xs-3 control-label">开始时间：</label>'+
    		'<div class="col-xs-6 ">'+
    			'<input type="time" class="form-control input-sm duiqi startTime"  value="09:00"/>'+
        		
    		'</div>'+
		'</div>'+
		'<div class="form-group ">'+
    		'<label  class="col-xs-3 control-label">结束时间：</label>'+
    		'<div class="col-xs-6 ">'+
    		    '<input type="time" class="form-control input-sm duiqi endTime"  value="18:00"/>'+
    		'</div>'+
		'</div>'+
		'<div class="form-group ">'+
    		'<div class="col-xs-6 ">'+
				'<button class="btn btn-success btn-xs addDevicePane" >+</button>'+
				'<button class="btn btn-danger btn-xs deleteDevicePane"  >-</button>'+
    		'</div>'+
		'</div> '+               
	'</form>';
		
		//alert("来一个新表单！");
		$("#freeBody").append(form);
	});
	
	//设置空闲时间弹出框保存按钮点击事件
	$("#saveSetFreeTime").click(function(){
		//alert("保存空闲时间");
		//定义空闲时间对象数组
		var freeTimeArray = new Array();
		//遍历各个表单，收集表单中的数据
		$(".freeTime").each(function(){
		    alert($(this).find(".startDate").val());
		    alert($(this).find(".endDate").val());
		    alert($(this).find(".startTime").val());
		    alert($(this).find(".endTime").val());
		    var freeTimeObject = new Object();
		    freeTimeObject.startDate = $(this).find(".startDate").val();
		    freeTimeObject.endDate = $(this).find(".endDate").val();
		    freeTimeObject.startTime = $(this).find(".startTime").val();
		    freeTimeObject.endTime = $(this).find(".endTime").val();
		    freeTimeArray.push(freeTimeObject);
		  });
		
		//封装数据
		var data = {
				roomNumber:roomNumber,
				freeTimeDate:freeTimeArray
		}
		//AJAX,设置（修改）会议室空闲时间
		$.ajax({
	      	  type : "post",
	      	  url:"../room/modifyFreeTime",
	      	  //contentType:"application/json",
	          //data:JSON.stringify(data),
	      	  data:data,
	          success:function(result){
	          	  //TODO
	          	  
	          }  	
	       });
	}); 
	
	//设置设备信息弹出框 + 按钮点击事件
	$(document).on('click','.addDevicePane',function(){
		
		var deviceForm = '<form class="form-horizontal setDeviceForm" onsubmit="return false;">'+
			'<div class="form-group ">'+
				'<label for="sName" class="col-xs-3 control-label">设备：</label>'+
				'<div class="col-xs-6 ">'+
					'<select  class="form-control input-sm duiqi deviceSelect"  >'+				
					'</select>'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label for="sLink" class="col-xs-3 control-label">数量：</label>'+
				'<div class="col-xs-6 ">'+
					'<input type = "text" class="form-control input-sm duiqi deviceNumInput"></input>'+
				'</div>'+
			'</div>'+
			'<div class="form-group ">'+
				'<div class="col-xs-6 ">'+
					'<button class="btn btn-success btn-xs addDevicePane" >+</button>'+
					'<button class="btn btn-danger btn-xs deleteDevicePane"  >-</button>'+
				'</div>'+
			'</div>'+       
		'</form>';
		
		//增加表单
		$("#deviceBody").append(deviceForm);
		//表单的设备下拉框填上从后台获取的所有设备
		var value = 10001 ;
		var option1 = "<option value='"+value+"'>"+"桌子("+value+")</option>";
		var option2 = "<option value='"+value+"'>"+"桌子("+value+")</option>";
		//当前点击元素的form父级的下一个元素（也是form）的select元素
		$(this).parents("form").next().find("select").append(option1);
		$(this).parents("form").next().find("select").append(option2);
		
	});
	
	 //设置设备弹出框 - 按钮点击事件
	$(document).on('click','.deleteDevicePane',function(){
		//删除所在的父级为form的元素，由于不是直接父级，因此是parents,不是parent
		$(this).parents("form").remove();
	});
	
	//设置设备弹出框  保存按钮点击事件
	$("#saveSetDevice").click(function(){
		
		//声明数组存储设备id和设备数量的对象
		var devicesArray = new Array();
		//遍历各个form
		$(".setDeviceForm").each(function(){
			//得到下拉框值和输入的数量
		    alert($(this).find(".deviceSelect").val());
		    alert($(this).find(".deviceNumInput").val());
		    var deviceObject = new Object();
		    deviceObject.name = $(this).find(".deviceSelect").val();
		    deviceObject.count = $(this).find(".deviceNumInput").val();
		    devicesArray.push(deviceObject);
		  });
		
		//封装上传数据
		var data = {
			roomNumber:roomNumber,
			deviceData:devicesArray
		}
		
		//AJAX，设置（修改）会议室设备信息
		$.ajax({
      	  type : "post",
      	  url:"../room/modifyDevice",
      	  //contentType:"application/json",
          //data:JSON.stringify(data),
            data:data,
          success:function(result){
          	  //TODO
          	  
          }  	
       });
		
		
	});
	
})

