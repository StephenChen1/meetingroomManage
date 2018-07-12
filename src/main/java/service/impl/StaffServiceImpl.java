package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.StaffDao;
import entity.Staff;
import service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffDao staffDao ;
	
	@Override
	public Staff getStaff(String staffNumber) {
		
		Staff staff = staffDao.queryStaffById(staffNumber);
		
		return staff;
	}

	//修改个人信息
	@Override
	public boolean modifyInfo(String staffNumber,String newStaffNumber, String newName,
			String newPhone, String newBirthday, String newAddress,
			String newDepartment, String newPosition) {
		// TODO 自动生成的方法存根
		return staffDao.modifyInfo(staffNumber,newStaffNumber,newName,newPhone
				,newBirthday,newAddress,newDepartment,newPosition);
	}

}
