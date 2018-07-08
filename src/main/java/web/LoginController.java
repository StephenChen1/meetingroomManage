package web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dtoin.LoginForm;

@RequestMapping("/login")
@Controller
public class LoginController {

	//index.jsp重定向到登陆界面
	@RequestMapping("/login")
	public String toLoginjsp(){
		
		return "login";
	}
	
	//对登录参数进行是否允许登录验证,返回JSON格式的布尔值
	@RequestMapping("/validate")
	@ResponseBody
	public boolean validateLogin(@RequestBody LoginForm loginForm){
		System.out.println("id:"+ loginForm.getId());
		System.out.println("password:"+ loginForm.getPassword());
		System.out.println("position:" + loginForm.getPosition());
		//TODO  验证登录
		return true ;
	}
	
	
	//通过登录验证后定向到管理员或用户界面
	@RequestMapping("/navigate")
	public String navigate(HttpServletRequest request,HttpServletResponse response){
		
		
		String position = request.getParameter("position");
		String id = request.getParameter("id");
		System.out.println("position::" + position);
		System.out.println("id::" + id);
		if(position.equals("user")){
			//addCookie(response,"teacher_id",id);
			System.out.println("返回user界面");
			return "user";
		}else if(position.equals("manager")){
			//addCookie(response,"manager_id",id);
			return "manager";
		}else{
			return "login";
		}
		
		
	}
	
	
	//添加cookie
	private void addCookie(HttpServletResponse response,String position,String id){
			Cookie cookie = new Cookie(position, id);
	        cookie.setMaxAge(30 * 60);// 设置为30min
	        cookie.setPath("/");
	        System.out.println("已添加cookie");
	        response.addCookie(cookie);
	}
	
}
