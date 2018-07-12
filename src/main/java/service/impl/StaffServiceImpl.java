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

}
