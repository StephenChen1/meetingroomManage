package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ManagerDao;
import service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	ManagerDao managerDao;
	
	
	@Override
	public boolean addRoom(String roomNumber,int capability){
		return managerDao.addMeetingRoom(roomNumber, capability);
	}

}
