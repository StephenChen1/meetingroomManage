package web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dtoout.AllRoom;
import entity.Staff;
import service.JwtService;
import service.LoginService;
import service.StaffService;

@RequestMapping("/login")
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private JwtService jwtService;

	// index.jsp重定向到登陆界面
	@RequestMapping("/login")
	public String toLoginjsp() {

		return "login";
	}

	/**
	 * 验证登陆，isLogin中返回验证状态，并返回其他个人信息 若登陆成功，创建token并写入cookie
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> validateLogin(HttpServletRequest request, HttpServletResponse response) {
		String staffNumber = request.getParameter("staffNumber");
		String password = request.getParameter("password");
		System.out.println(staffNumber);
		System.out.println(password);
		// 前端注意要有参数，不然会报500
		// 理论上后台也要再确认一次，然后返回对应的错误码（但目前懒得弄）
		if ((staffNumber == null) || (password == null)) {
			System.out.println("参数不完整");
			return null;
		}
		// 确定是否登陆成功
		boolean isLogin = loginService.login(staffNumber, password);
		Staff staff = new Staff();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isLogin", isLogin);
		if (isLogin) {
			// 如果登陆成功，创建token并写入cookie中
			this.writeCookie(response, staffNumber);
			// 返回前端用户信息
			staff = staffService.getStaff(staffNumber);
		}
		map.put("staffNumber", staff.getStaffNumber());
		map.put("staffName", staff.getName());
		map.put("level", staff.getLevel());
		map.put("department", staff.getDepartment());
		map.put("birthday", staff.getBirthday());
		map.put("phone", staff.getPhone());
		map.put("address", staff.getAddress());
		return map;
	}

	/**
	 * 获取当前登陆用户的staffNumber
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping("/current")
	@ResponseBody
	public Map<String, String> getCurrentStaff(@CookieValue(value = "token", required = false) String token) {
		String staffNumber = this.readCookie(token);
		Map<String, String> map = new HashMap<String, String>();
		map.put("staffNumber", staffNumber);
		return map;
	}

	/**
	 * 登出
	 * 将名字为token的cookie删除掉，完成登出
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String,String> logout(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                cookie.setValue(null);
                cookie.setMaxAge(0);// 立即销毁cookie
                cookie.setPath("/");
                response.addCookie(cookie);
            }
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("msg", "成功登出");
		return map;
	}

	// 通过登录验证后定向到管理员或用户界面
	@RequestMapping("/navigate")
	public String navigate(HttpServletRequest request, HttpServletResponse response) {
		String position = request.getParameter("position");
		String id = request.getParameter("id");
		System.out.println("position::" + position);
		System.out.println("id::" + id);
		if (position.equals("user")) {
			// addCookie(response,"teacher_id",id);
			System.out.println("返回user界面");
			return "user";
		} else if (position.equals("manager")) {
			// addCookie(response,"manager_id",id);
			return "manager";
		} else {
			return "login";
		}

	}

	/**
	 * 私有方法，创建token并写入cookie
	 * 
	 * @param response
	 * @param staffNumber
	 */
	private void writeCookie(HttpServletResponse response, String staffNumber) {
		String token = null;
		token = jwtService.create(staffNumber);
		Cookie cookie = new Cookie("token", token);
		cookie.setMaxAge(7 * 24 * 60 * 60);// 设置时间为7天
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 私有方法，读取cookie中的token并解析为staffNumber
	 * 
	 * @param token
	 * @return
	 */
	private String readCookie(@CookieValue(value = "token", required = false) String token) {
		if (token == null) {
			return null;
		}
		String staffNumber = jwtService.getStaffNum(token);
		return staffNumber;
	}
	
}
