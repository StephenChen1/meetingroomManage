(function ($) {
        //函数定义
        $.navigate = function (position,id) {
            //函数内容
        	//alert("position:" + position);
        	//alert("id:" + id);
        	var url = "./navigate?position="+position+"&id="+id ;
    		//把请求转发
        	window.location.href=url;
        }
    })(jQuery);

$(document).ready(function(){
	
	//隐藏loginTip
	$("#loginTip").hide();
			
  $("#login1").click(function(){
    //alert("点击了登录");
    var id = $("#userId").val();
    var password = $("#password").val();
    var position = $("input[name='position']:checked").val();
    console.log("id:" + id);
    console.log("password:" + password);
    console.log("position:" + position);
    var data = {
    	  position:position,
      	  id:id,
      	  password:password
    }
    
    $.ajax({
    	type : "post",
    	url:"validate",
    	contentType:"application/json",
        data:JSON.stringify(data),
        success:function(result){
        	
        
        	//alert("result3:" + result);
        	//此时的result应该是String值，用parseJSON把其转换为boolean值
        	result = $.parseJSON(result);
        	
        	if(result){
        		//alert("true");
        		//能登录，转到相应页面,调用自定义函数
//        		var url = "./navigate?position="+position ;
        		$.navigate(position,id);
        	}else if(!result){
        		//密码或账号错误，出现提示
        		//alert("false");
        		$("#loginTip").hide().html('<label class = "text-danger">账号或密码错误</label>').show(300);
        		//alert("false");
        	}else{
        		//alert("error");
        		$("#loginTip").hide().html('<label class = "text-danger">未知错误</label>').show(300);
        	}
        }
    });
  });
});