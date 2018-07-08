package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dtoin.ID;
import dtoin.LoginForm;
import entity.Staff;
import service.StaffService;

@Controller
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService staffService ;
	
	//根据id查找用户信息
	@RequestMapping("/search")
	@ResponseBody
	public Staff getStaffTest(@RequestBody ID id){
		String id1 = id.getId();
		System.out.println("id:" + id);
		Staff staff = staffService.getStaff(id1);
		System.out.println("staff:" + staff.getAddress());
		return staff ;
	}
	
	
	
	
	
}
