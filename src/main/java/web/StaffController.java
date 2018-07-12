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
import org.springframework.web.bind.annotation.RequestMethod;
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
	public Map<String, String> createToken(HttpServletRequest request,
			HttpServletResponse response) {
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
	public String getStaffNum(
			@CookieValue(value = "token", required = false) String token) {
		if (token == null) {
			return "no cookie";
		}
		String staff_number = jwtService.getStaffNum(token);
		return staff_number;
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
	
	/**
	 * 修改个人信息
	 * @param 新员工编号newStaffNumber
	 * @param 新用户名newName
	 * @param 新电话号码newPhone
	 * @param 新生日newBirthday
	 * @param 新地址newAddress
	 * @param 新住址newDepartment
	 * @param 新Position
	 *            newPosition
	 * @return boolean
	 */
	@RequestMapping(value = "/modifyInfo", method = RequestMethod.POST)
	@ResponseBody
	public boolean modifyInfo(
			@RequestBody(required = false) Map<String, Object> map,@CookieValue(value = "token", required = false) String token) {
		String staffNumber = this.readCookie(token);
		//String staffNumber = "1";  //在还没实现登录功能之前手动给staffNumber赋值,赋值后测试已通过
		String newStaffNumber = map.get("newStaffNumber").toString();
		String newName = map.get("newName").toString();
		String newPhone = map.get("newPhone").toString();
		String newBirthday = map.get("newBirthday").toString();
		String newAddress = map.get("newAddress").toString();
		String newDepartment = map.get("newDepartment").toString();
		String newPosition = map.get("newPosition").toString();
		System.out.println("已进入modifyInfo方法");
		/*System.out.println(staffNumber+"  "+newStaffNumber + "   " + newName + "   " + newPhone
				+ "   " + newBirthday + "   " + newAddress + "   "
				+ newDepartment + "   " + newPosition);*/
		boolean isOK = staffService.modifyInfo(staffNumber,newStaffNumber,newName,newPhone
				,newBirthday,newAddress,newDepartment,newPosition);
		//System.out.println("ISOK：" + isOK);
		return isOK;
	}

}
