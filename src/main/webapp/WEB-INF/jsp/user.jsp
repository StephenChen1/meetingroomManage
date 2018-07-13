<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入jstl -->
<%@include file="common/tag.jsp" %>

<!doctype html>
<html lang="ch">

    <head>
    	<%@include file="common/head.jsp"%>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="会议室预约系统用户页面">
        <meta name="keywords" content="会议室 管理 ">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <title>会议室预约系统用户首页</title>
        <script src="${basePath}resources/js/jquery.min.js"></script>
        <script src="${basePath}resources/js/bootstrap.min.js"></script>
        <script>
            $(function() {
            	//此代码用于测试根据会议室号查询会议室信息,测试完成后user.jsp的id为test的button要去掉这的"test"Id
            	 	  /* $("#test").click(function(){
            		    alert("test按钮");
            		    var roomNumber = "90";
            		    var newCapability = "90";
            		    var data = {
            		    		roomNumber:roomNumber,
            		    		newCapability:newCapability
            		    }
            		    $.ajax({
            		    	type : "post",
            		    	url:"../room/modifyCapability",
            		    	contentType:"application/json",
            		        data:JSON.stringify(data),
            		        success:function(result){
            		        	alert("success");
            		        }
            		    });
            		  });  */
            	
            	
                $(".meun-item").click(function() {
                    $(".meun-item").removeClass("meun-item-active");
                    $(this).addClass("meun-item-active");
                    var itmeObj = $(".meun-item").find("img");
                    itmeObj.each(function() {
                        var items = $(this).attr("src");
                        items = items.replace("_grey.png", ".png");
                        items = items.replace(".png", "_grey.png")
                        $(this).attr("src", items);
                    });
                    var attrObj = $(this).find("img").attr("src");
                    ;
                    attrObj = attrObj.replace("_grey.png", ".png");
                    $(this).find("img").attr("src", attrObj);
                });
                $("#topAD").click(function() {
                    $("#topA").toggleClass(" glyphicon-triangle-right");
                    $("#topA").toggleClass(" glyphicon-triangle-bottom");
                });
                $("#topBD").click(function() {
                    $("#topB").toggleClass(" glyphicon-triangle-right");
                    $("#topB").toggleClass(" glyphicon-triangle-bottom");
                });
                $("#topCD").click(function() {
                    $("#topC").toggleClass(" glyphicon-triangle-right");
                    $("#topC").toggleClass(" glyphicon-triangle-bottom");
                });
                $(".toggle-btn").click(function() {
                    $("#leftMeun").toggleClass("show");
                    $("#rightContent").toggleClass("pd0px");
                })
            })
        </script>
        <!--[if lt IE 9]>
  <script src="js/html5shiv.min.js"></script>
  <script src="js/respond.min.js"></script>
<![endif]-->
        <link href="${basePath}resources/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${basePath}resources/css/common.css" />
        <link rel="stylesheet" type="text/css" href="${basePath}resources/css/slide.css" />
        <link rel="stylesheet" type="text/css" href="${basePath}resources/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${basePath}resources/css/flat-ui.min.css" />
        <link rel="stylesheet" type="text/css" href="${basePath}resources/css/jquery.nouislider.css">
        <link rel="stylesheet" type="text/css" href="${basePath}resources/css/ownDefine.css">
    </head>

    <body>
        <div id="wrap">
            <!-- 左侧菜单栏目块 -->
            <div class="leftMeun" id="leftMeun">
                <div id="logoDiv">
                    <p id="logoP"><img id="logo" alt="会议室预约系统" src="${basePath}resources/images/logo.png"><span>会议室预约系统</span></p>
                </div>
                <div id="personInfor">
					<p>欢迎您！</p>
                    <p id="userName"></p>
					<p id = "userId"></p>
                    <p>
                        <!-- 退出登录按钮 -->
						<button type="button" class="btn btn-default btn-sm" id = "exitLogin">退出登录</button>
                    </p>
                </div>
                <div id ="accordion">
                
                <!-- 预约管理折叠 -->
                <div class="meun-title" onClick="changeA()" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseBookedFunction" aria-expanded="true" aria-controls="collapseOne">
                	<span id="topB" class="glyphicon  glyphicon-triangle-bottom  "></span> <span>预约功能</span>
                </div>
                <div id="collapseBookedFunction" class="collapse in" aria-expanded="true">
                	<div class="meun-item" id = "userBookedRoom" href="#showNowBookedPane" aria-controls="showNowBookedPane" role="tab" data-toggle="tab"><img src="${basePath}resources/images/icon_house_grey.png">预约会议室</div>
                	<div class="meun-item" id = "userBookedRecord" href="#showHistoryBookedPane" aria-controls="showHistoryBookedPane" role="tab" data-toggle="tab"><img src="${basePath}resources/images/icon_rule_grey.png">预约记录</div>
            	</div>
            	<!-- 用户管理折叠 -->
            	<div class="meun-title" onClick="changeA()" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseStaffFunction" aria-expanded="true" aria-controls="collapseOne">
                	<span id="top111" class="glyphicon  glyphicon-triangle-bottom  "></span> <span>个人信息</span>
                </div>
                <div id="collapseStaffFunction" class="collapse " aria-expanded="true">
                	<div class="meun-item" id = "modifyMyInfoMenu1" href="#modifyInformation" aria-controls="modifyInformation" role="tab" data-toggle="tab"><img src="${basePath}resources/images/icon_house_grey.png">修改个人信息</div>
                	<div class="meun-item" id = "modifyMyPassMenu" href="#modifyPassword" aria-controls="modifyPassword" role="tab" data-toggle="tab"><img src="${basePath}resources/images/icon_rule_grey.png">修改密码</div>
                </div>
            	</div>
            </div>
            
            
            <!-- 右侧具体内容栏目 -->
            <div id="rightContent">
                <a class="toggle-btn" id="nimei">
                    <i class="glyphicon glyphicon-align-justify"></i>
                </a>
                <!-- Tab panes -->
                <div class="tab-content">
                    
                    
                  
        		
        			
        			
        			<!-- 预约会议室模块 -->
        			<div role="tabpanel" class="tab-pane" id="showNowBookedPane">
                		<div class="check-div form-inline">
                    		<div class="col-xs-2">
                        		<button class="btn btn-yellow btn-xs" >预约会议室</button>
                    		</div>
                    		<div class="col-xs-7">

                        		<input type="date" id = "bookedDateInput" class="form-control input-sm" placeholder="输入日期">
                        		<input type="time" id = "bookedTimeInput"class="form-control input-sm" placeholder="输入时间">
                        		<button class="btn btn-white btn-xs ">查 询 </button>

                    		</div>
                    		<div class="col-lg-3  col-xs-3" style=" padding-right: 40px;text-align: right;">
                        		<label for="sortNowBooked">排序:&nbsp;</label>
                        		<select class=" form-control" id = "sortNowBooked">
                            		<option>会议室号</option>
                            		<option>时间升序</option>
                            		<option>时间倒序</option>
                            		<option>容纳人数</option>
                        		</select>
                    		</div>
                		</div>
                		<div class="data-div">
                    		<!--自己写table -->
							<table class = "table" id = "bookedTable">
							   <caption><div align="center" class="text-success" id = "bookedTableTip">可预约信息</div></caption>
							   <thead class="row tableHeader">
							     <tr>
								    <th class="col-xs-2">编号</th>
									<th class="col-xs-2">会议室号</th>
									<th class="col-xs-2">容纳人数</th>
									<!-- <th class="col-xs-2">设备信息</th> -->
									<th class="col-xs-4">可预约时段</th>
									<th class="col-xs-2">操作</th>
								 </tr>
							   </thead>
							   <tbody class="tablebody" id = "BookedBody">
							      <tr>
							         <td>1</td>
							         <td>10001</td>
							         <td>100</td>
							         <!-- <td>
							         	可以点击弹出框查看会议室设备的详情
							            <p  style = "cursor:pointer;" data-toggle="modal" data-target="#showDeviceDetailPop">桌子*120</p>	
							         	<p  style = "cursor:pointer;" data-toggle="modal" data-target="#showDeviceDetailPop">椅子*120</p>	
							         </td>  -->
							         <td>
							         	  2018-04-05~2-18-07-09 8:00~17:00
							         </td>
							         <td>
							         	 <button class="btn btn-success btn-xs" data-toggle="modal" data-target="#bookedPop">预约</button>
                                		 
							         </td>
							         
							      </tr>
							   </tbody>
							</table>
                		</div> 
                		
                		<!--预约弹出窗口-->
                		<div class="modal fade" id="bookedPop" role="dialog" aria-labelledby="gridSystemModalLabel">
                    		<div class="modal-dialog" role="document">
                        		<div class="modal-content">
                            		<div class="modal-header">
                                		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                		<h4 class="modal-title" id="gridSystemModalLabel">预约</h4>
                            		</div>
                            		<div class="modal-body">
                                		<div class="container-fluid">
                                    		<form class="form-horizontal">
                                        			<div class="form-group ">
                                            			<label for="roomNumberBooked" class="col-xs-3 control-label">会议室号：</label>
                                            			<div class="col-xs-6 ">
                                                			<input type="text" class="form-control input-sm duiqi" id="roomNumberBooked" >
                                            			</div>
                                        			</div>
                                        			<div class="form-group ">
                                            			<label for="startDateBooked" class="col-xs-3 control-label">开始日期：</label>
                                            			<div class="col-xs-6 ">
                                                			<input type="date" class="form-control input-sm duiqi" id="startDateBooked" >
                                            			</div>
                                        			</div>
                                        			<div class="form-group ">
                                            			<label for="endDateBooked" class="col-xs-3 control-label">结束日期：</label>
                                            			<div class="col-xs-6 ">
                                                			<input type="date" class="form-control input-sm duiqi" id="endDateBooked" >
                                            			</div>
                                        			</div>
                                        			<div class="form-group ">
                                            			<label for="startTimeBooked" class="col-xs-3 control-label">开始时间：</label>
                                            			<div class="col-xs-6 ">
                                                			<input type="time" class="form-control input-sm duiqi" id="startTimeBooked" >
                                            			</div>
                                        			</div>
                                        			<div class="form-group ">
                                            			<label for="endTimeBooked" class="col-xs-3 control-label">结束时间：</label>
                                            			<div class="col-xs-6 ">
                                                			<input type="time" class="form-control input-sm duiqi" id="endTimeBooked" >
                                            			</div>
                                        			</div>                                       		
                                    			</form>
                                		</div>
                            		</div>
                            		<div class="modal-footer">
                                		<button type="button" class="btn btn-xs btn-white" data-dismiss="modal" id ="cancelBooked">取 消</button>
                                		<button type="button" class="btn btn-xs btn-green" data-dismiss="modal" id = "confirmBooked">确定</button>
                            		</div>
                        		</div>
                        		<!-- /.modal-content -->
                    		</div>
                    		<!-- /.modal-dialog -->
                		</div>
                		<!-- /.modal -->
                		     	
            		</div>
        			
        			
        			<!-- 查看历史预约记录模块 -->
        			<div role="tabpanel" class="tab-pane" id="showHistoryBookedPane">
                		<div class="check-div form-inline">
                    		<div class="col-xs-3">
                        		<button class="btn btn-yellow btn-xs" >我的预约记录</button>
                    		</div>
                    		<div class="col-xs-4">
                        		<input type="text" class="form-control input-sm" placeholder="输入日期搜索">
                        		<button class="btn btn-white btn-xs ">查 询 </button>
                    		</div>
                    		<div class="col-lg-3 col-lg-offset-2 col-xs-4" style=" padding-right: 40px;text-align: right;">
                        		<label for="sortHistoryBooked">排序:&nbsp;</label>
                        		<select class=" form-control" id = "sortHistoryBooked">
                            		<option>会议室号</option>
                            		<option>时间升序</option>
                            		<option>时间倒序</option>
                        		</select>
                    		</div>
                		</div>
                		<div class="data-div">
                    		<!--自己写table -->
							<table class = "table" id = "showMyHistoryTable">
							   <caption><div align="center" class="text-success" id = "modifyMeetingTableTip">我的预约记录</div></caption>
							   <thead class="row tableHeader">
							     <tr>
								    <th class="col-xs-2">编号</th>
									<th class="col-xs-2">会议室号</th>
									<th class="col-xs-2">容纳人数</th>
									<th class="col-xs-4">预约时间</th>
									<th class="col-xs-2">备注</th>
								 </tr>
							   </thead>
							   <tbody class="tablebody" id = "showMyHistoryBody">
							      <!-- <tr>
							         <td>1</td>
							         <td>
							         	可以点击弹出框查看预约员工的详情
							            <p  style = "cursor:pointer;" data-toggle="modal" data-target="#showRoomDetailPop">10001</p>	
							         </td>
							         <td>100</td>
							         <td>
							         	 2018-07-21 09:30~16:00<br>
							         </td>
							         <td class = "text-danger">已取消</td>
							      </tr> -->
							   </tbody>
							</table>
                		</div>
            		</div>
                      		
        		  	<!-- 修改个人信息模块 -->
        			<div role="tabpanel" class="tab-pane" id="modifyInformation"> 
                		<div class="check-div from-inline">
                                <div class="col-xs-3">
                        			<button class="btn btn-yellow btn-xs" >修改个人信息</button>
                    			</div>
                		</div>
                		<div style="padding: 50px 0;margin-top: 50px;background-color: #fff; text-align: right;width: 420px;margin: 50px auto;">
                    		<div class="form-horizontal">
                        		<div class="form-group">
                            		<label for="myId" class="col-xs-4 control-label">工号：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="myId" placeholder="" style="margin-top: 7px;">
                                    </div>
                                </div>
                                <div class="form-group">
                            		<label for="myName" class="col-xs-4 control-label">姓名：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="myName" placeholder="222" style="margin-top: 7px;">
                                    </div>
                                </div>
                                <div class="form-group">
                            		<label for="myDepartment" class="col-xs-4 control-label">部门：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="myDepartment" placeholder="" style="margin-top: 7px;">
                                    </div>
                                </div>
                                <div class="form-group">
                            		<label for="myPosition" class="col-xs-4 control-label">职位：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="myPosition" placeholder="" style="margin-top: 7px;">
                                    </div>
                                </div>
                                <div class="form-group">
                            		<label for="myPhone" class="col-xs-4 control-label">手机：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="myPhone" placeholder="" style="margin-top: 7px;">
                                    </div>
                                </div>
                                <div class="form-group">
                            		<label for="myPhone" class="col-xs-4 control-label">邮箱：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="myEmail" placeholder="" style="margin-top: 7px;">
                                    </div>
                                </div>
                                <div class="form-group">
                            		<label for="myBirthday" class="col-xs-4 control-label">生日：</label>
                            		<div class="col-xs-5">
                                		<input type="date" class="form-control input-sm duiqi" id="myBirthday"  style="margin-top: 7px;">
                                    </div>
                                </div>
                                <div class="form-group">
                            		<label for="myAddress" class="col-xs-4 control-label">住址：</label>
                            		<!-- <div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="myAddress" placeholder="" style="margin-top: 7px;">
                                    </div> -->
                                    <div class="col-xs-5">
                                		<textarea class="form-control input-sm duiqi" id="myAddress" style="margin-top: 7px;" rows="3" cols="20">输入住址..</textarea>
                                    </div>
                                    
                                </div>
                                
                                
                        		<div class="form-group text-right">
                            		<div class="col-xs-offset-4 col-xs-5" style="margin-left: 169px;">
                                		<button type="reset" class="btn btn-xs btn-white">取 消</button>
                                		<button type="submit" class="btn btn-xs btn-green" id = "modifyInformationBtn">修改</button>
                            		</div>
                        		</div>
                    		</div>
                		</div>
            		</div>
		     		<!--修改个人信息模块结束(上面的div) -->
        			
        		
        		
        			<!-- 修改密码 模块-->
        			<div role="tabpanel" class="tab-pane" id="modifyPassword"> 
                		<div class="check-div">
                				<div class="col-xs-3">
                        			<button class="btn btn-yellow btn-xs" >修改密码</button>
                    			</div>
                                 <!-- 原始密码为12312313 -->
                                 <!-- 用于显示用户修改密码操作结果 -->
                   				 <div align="center"><span id="isTwoPassSame"></span></div>
                		</div>
                		<div style="padding: 50px 0;margin-top: 50px;background-color: #fff; text-align: right;width: 420px;margin: 50px auto;">
                    		<div class="form-horizontal">
                        		<div class="form-group">
                            		<label for="userIdModifyP" class="col-xs-4 control-label">ID：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="userIdModifyP" placeholder="" style="margin-top: 7px;">
                                    </div>
                                </div>
                                <div class="form-group">
                                	<label for="oldPassword" class="col-xs-4 control-label">原密码：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="oldPassword" placeholder="" style="margin-top: 7px;">
                            		</div>
                        		</div>
                                <div class="form-group">
                                	<label for="newPassword" class="col-xs-4 control-label">新密码：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="newPassword" placeholder="" style="margin-top: 7px;">
                            		</div>
                        		</div>
                        		<div class="form-group">
                            		<label for="defineNewPassword" class="col-xs-4 control-label" >确认密码：</label>
                            		<div class="col-xs-5">
                                		<input type="text" class="form-control input-sm duiqi" id="defineNewPassword" placeholder="" style="margin-top: 7px;">
                            		</div>
                        		</div>
                        		<div class="form-group text-right">
                            		<div class="col-xs-offset-4 col-xs-5" style="margin-left: 169px;">
                                		<button type="reset" class="btn btn-xs btn-white">取 消</button>
                                		<button type="submit" class="btn btn-xs btn-green" id = "modifyPasswordBtn">修改</button>
                            		</div>
                        		</div>
                    		</div>
                		</div>
            		</div>
		     		<!--修改密码模块结束(上面的div) -->
        		
        		
        		
        		
        		</div>
    		</div>
    		
    		<!-- 定义模态框开始 -->
				<!--会议室信息详情弹出窗口，点击会议室ID即可弹出(因为这个模态框被多个元素调用，因此放在根节点才能满足多个子节点去调用-->
                		<div class="modal fade " id="showRoomDetailPop" role="dialog" aria-labelledby="gridSystemModalLabel">
                    		<div class="modal-dialog" role="document">
                        		<div class="modal-content">
                            		<div class="modal-header">
                                		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                		<h4 class="modal-title" id="gridSystemModalLabel">会议室详情</h4>
                            		</div>
                            		<div class="modal-body">
                                		<div class="container-fluid">
                                    		<form class="form-horizontal">
                                        		<div class="form-group ">
                                            		<label for="roomID" class="col-xs-3 control-label">会议室号：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="roomID" placeholder="">
                                            		</div>
                                        		</div>
                                        		<div class="form-group ">
                                            		<label for="capabilityShow" class="col-xs-3 control-label">容纳人数：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="capabilityShow" placeholder="">
                                            		</div>
                                        		</div>
                                        		<div class="form-group ">
                                            		<label for="deviceOwn" class="col-xs-3 control-label">配备设备：</label>
                                            		<div class="col-xs-6 ">
                                                		<!-- <input type="text" class="form-control input-sm duiqi" id="deviceOwn" placeholder=""> -->
                                            			<textarea class="form-control input-sm duiqi" id="deviceOwn" placeholder=""></textarea>
                                            		</div>
                                        		</div>
                                    		</form>
                                		</div>
                            		</div>
                            		<div class="modal-footer">
                                		
                                		<button type="button" class="btn btn-xs btn-green" data-dismiss="modal" >关闭</button>
                            		</div>
                        		</div>
                        		<!-- /.modal-content -->
                    		</div>
                    		<!-- /.modal-dialog -->
                		</div>
                		<!-- /.modal --> 
				
				
				<!--预约员工信息详情弹出窗口，点击员工即可弹出-->
                		<div class="modal fade" id="showStaffDetailPop" role="dialog" aria-labelledby="gridSystemModalLabel">
                    		<div class="modal-dialog" role="document">
                        		<div class="modal-content">
                            		<div class="modal-header">
                                		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                		<h4 class="modal-title" id="gridSystemModalLabel">员工详情</h4>
                            		</div>
                            		<div class="modal-body">
                                		<div class="container-fluid">
                                    		<form class="form-horizontal">
                                        		<div class="form-group ">
                                            		<label for="staffID" class="col-xs-3 control-label">员工ID：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="staffID" placeholder="">
                                            		</div>
                                        		</div>
                                        		<div class="form-group ">
                                            		<label for="staffNameShow" class="col-xs-3 control-label">姓名：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="staffNameShow" placeholder="">
                                            		</div>
                                        		</div>
                                        		<div class="form-group ">
                                            		<label for="departmemtShow" class="col-xs-3 control-label">部门：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="departmemtShow" placeholder=""> 
                                            			
                                            		</div>
                                        		</div>
                                        		<div class="form-group ">
                                            		<label for="positionShow" class="col-xs-3 control-label">职位：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="positionShow" placeholder=""> 
                                            			
                                            		</div>
                                        		</div>
                                        		<div class="form-group ">
                                            		<label for="phoneShow" class="col-xs-3 control-label">手机：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="phoneShow" placeholder=""> 
                                            			
                                            		</div>
                                        		</div>
                                    		</form>
                                		</div>
                            		</div>
                            		<div class="modal-footer">
                                		
                                		<button type="button" class="btn btn-xs btn-green" data-dismiss="modal" >关闭</button>
                            		</div>
                        		</div>
                        		<!-- /.modal-content -->
                    		</div>
                    		<!-- /.modal-dialog -->
                		</div>
                		<!-- /.modal -->   
				
				<!--设备信息详情弹出窗口，点击设备名称即可弹出-->
                		<div class="modal fade" id="showDeviceDetailPop" role="dialog" aria-labelledby="gridSystemModalLabel">
                    		<div class="modal-dialog" role="document">
                        		<div class="modal-content">
                            		<div class="modal-header">
                                		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                		<h4 class="modal-title" id="gridSystemModalLabel">设备详情</h4>
                            		</div>
                            		<div class="modal-body">
                                		<div class="container-fluid">
                                    		<form class="form-horizontal">
                                        		<div class="form-group ">
                                            		<label for="deviceID" class="col-xs-3 control-label">设备ID：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="deviceID" placeholder="">
                                            		</div>
                                        		</div>
                                        		<div class="form-group ">
                                            		<label for="deviceNameShow" class="col-xs-3 control-label">设备名：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="deviceNameShow" placeholder="">
                                            		</div>
                                        		</div>
                                        		<div class="form-group ">
                                            		<label for="deviceTypeShow" class="col-xs-3 control-label">设备型号：</label>
                                            		<div class="col-xs-6 ">
                                                		<input type="text" class="form-control input-sm duiqi" id="deviceTypeShow" placeholder="">
                                            		</div>
                                        		</div>
                                    		</form>
                                		</div>
                            		</div>
                            		<div class="modal-footer">
                                		
                                		<button type="button" class="btn btn-xs btn-green" data-dismiss="modal" >关闭</button>
                            		</div>
                        		</div>
                        		<!-- /.modal-content -->
                    		</div>
                    		<!-- /.modal-dialog -->
                		</div>
                		<!-- /.modal --> 
				
				
				
			<!-- 定义模态框结束 -->    		
    		
    		
 		</div>

		<script src="${basePath}resources/js/jquery.nouislider.js"></script>
		<script src="${basePath}resources/js/exitLogin.js"></script>
		<script src="${basePath}resources/js/user.commom.js"></script>
		<script src="${basePath}resources/js/user.modifyInfo.js"></script>
		<script src="${basePath}resources/js/user.modifyMyPassword.js"></script>
		<script src="${basePath}resources/js/user.myBookedRecord.js"></script>
		<script src="${basePath}resources/js/user.booked.js"></script> 
	</body>
</html>