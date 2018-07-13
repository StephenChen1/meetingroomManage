package service;

import java.util.List;

import dtoout.CanBooked;
import entity.Staff;

public interface StaffService {

	Staff getStaff(String staffNumber);

	//修改个人信息
	boolean modifyInfo(String staffNumber,String newStaffNumber, String newName, String newPhone,
			String newEmail,String newBirthday, String newAddress, String newDepartment,
			String newPosition);

	//得到所有员工信息
	List<Staff> getAllStaff();
	
	
	//得到所有可预约时间段信息
	List<CanBooked> getAllCanBooked();
	
	
}
