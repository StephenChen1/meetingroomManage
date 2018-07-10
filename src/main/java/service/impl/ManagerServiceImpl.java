package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ManagerDao;
import dtoin.FreeTime;
import service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	ManagerDao managerDao;

	@Override
	public boolean addRoom(String roomNumber, int capability) {
		return managerDao.addMeetingRoom(roomNumber, capability);
	}

	@Override
	public boolean setFreeTime(String roomNumber, List<FreeTime> freeTimeList) {
		boolean flag = true;
		// 先删除所有已有空闲时间
		managerDao.deleteFreeTime(roomNumber);
		// 再添加所有空闲时间
		for (FreeTime ft : freeTimeList) {
			// 暂存本次修改结果
			boolean temp = managerDao.addFreeTime(roomNumber, ft.getStartDate(), ft.getEndDate(), ft.getStartTime(),
					ft.getEndTime());
			// 总修改结果与本次修改结果作与运算，一旦某次为false，则最后return false
			flag = flag & temp;
		}
		return flag;
	}

}
