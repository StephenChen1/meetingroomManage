package web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dtoin.ID;
import dtoin.LoginForm;
import entity.Staff;
import service.JwtService;
import service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService staffService ;
	
	
	@Autowired
	private JwtService jwtService;

	//根据id查找用户信息
	@RequestMapping("/search")
	@ResponseBody
	public Staff getStaffTest(@RequestBody ID id) {
		String id1 = id.getId();
		System.out.println("id:" + id);
		Staff staff = staffService.getStaff(id1);
		System.out.println("staff:" + staff.getAddress());
		return staff;
	}

	

	// 仅作JWT测试
	// 正确用法：用户登陆后，用jwtService.create()创建token，并返回给用户
	@RequestMapping("/createToken")
	@ResponseBody
	public Map<String, String> createToken(HttpServletRequest request) {
		String staff_number = request.getParameter("staff_number");
		Map<String, String> map = new HashMap<String, String>();
		String token = null;
		if (staff_number == null) {
			map.put("token", null);
			return map;
		}
		token = jwtService.create(staff_number);
		map.put("token", token);
		return map;
	}

	// 仅作JWT测试
	// 注意token是放在header中的，注意获取方法
	@RequestMapping("/getStaffNum")
	@ResponseBody
	public String getStaffNum(@RequestHeader("Authorization") String token) {
		token = token.substring(7);//去掉开头的Bearer  
		System.out.println(token);
		String staff_number = jwtService.getStaffNum(token);
		return staff_number;
	}

}
