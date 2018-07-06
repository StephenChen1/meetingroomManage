$(document).ready(function(){
	
	$("#serch").click(function(){
		
		var id = $("#staffId").val();
		alert(id);
		
		var data = {
				id:id
		}
		
		$.ajax({
	    	type : "post",
	    	url:"serch",
	    	contentType:"application/json",
	        data:JSON.stringify(data),
	        success:function(result){        	
	        	alert("result:"+ result.staffNumber+result.name);	
	        }
	    });
	});
	
})