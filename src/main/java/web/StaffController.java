package web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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
	private StaffService staffService;

	@Autowired
	private JwtService jwtService;

	// 根据id查找用户信息
	@RequestMapping("/search")
	@ResponseBody
	public Staff getStaffTest(@RequestBody ID id) {
		String id1 = id.getId();
		System.out.println("id:" + id);
		Staff staff = staffService.getStaff(id1);
		System.out.println("staff:" + staff.getAddress());
		return staff;
	}

	@RequestMapping("id")
	@ResponseBody
	public Map<String, Object> getStaffById(HttpServletRequest request) {
		String staffNumber = request.getParameter("staffNumber");
		Staff staff = staffService.getStaff(staffNumber);
		Map<String, Object> map = new HashMap<String, Object>();
		if (staff == null) {
			return map;
		}
		map.put("staffNumber", staff.getStaffNumber());
		map.put("staffName", staff.getName());
		map.put("level", staff.getLevel());
		map.put("department", staff.getDepartment());
		map.put("position", staff.getPosition());
		map.put("birthday", staff.getBirthday());
		map.put("phone", staff.getPhone());
		map.put("address", staff.getAddress());
		return map;
	}

	// 仅作JWT测试
	// 正确用法：用户登陆后，用jwtService.create()创建token，并返回给用户
	@RequestMapping("/createToken")
	@ResponseBody
	public Map<String, String> createToken(HttpServletRequest request, HttpServletResponse response) {
		String staff_number = request.getParameter("staff_number");
		Map<String, String> map = new HashMap<String, String>();
		String token = null;
		if (staff_number == null) {
			map.put("token", null);
			return map;
		}
		// 放入cookie中
		token = jwtService.create(staff_number);
		Cookie cookie = new Cookie("token", token);
		cookie.setMaxAge(7 * 24 * 60 * 60);// 设置时间为7天
		cookie.setPath("/");
		response.addCookie(cookie);
		// 返回token
		map.put("token", token);
		return map;
	}

	// 仅作JWT测试
	// 注意token是放在header中的，注意获取方法
	// 目前是从Cookie中获取token
	@RequestMapping("/getStaffNum")
	@ResponseBody
	public String getStaffNum(@CookieValue(value = "token", required = false) String token) {
		if (token == null) {
			return "no cookie";
		}
		String staff_number = jwtService.getStaffNum(token);
		return staff_number;
	}

}
