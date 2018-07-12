package service;

import entity.Staff;

public interface StaffService {

	Staff getStaff(String staffNumber);

	//修改个人信息
	boolean modifyInfo(String staffNumber,String newStaffNumber, String newName, String newPhone,
			String newBirthday, String newAddress, String newDepartment,
			String newPosition);

}
