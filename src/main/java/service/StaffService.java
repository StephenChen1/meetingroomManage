package service;

import java.util.List;

import entity.Staff;

public interface StaffService {

	Staff getStaff(String staffNumber);

	//修改个人信息
	boolean modifyInfo(String staffNumber,String newStaffNumber, String newName, String newPhone,
			String newBirthday, String newAddress, String newDepartment,
			String newPosition);

	//得到所有员工信息
	List<Staff> getAllStaff();
	
}
