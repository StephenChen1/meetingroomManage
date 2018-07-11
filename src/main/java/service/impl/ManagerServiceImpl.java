package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ManagerDao;
import dao.RoomDeviceDao;
import dtoin.FreeTime;
import entity.RoomDevice;
import service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	ManagerDao managerDao;
	@Autowired
	RoomDeviceDao roomDeviceDao;

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

	// 跟上面那个方法差不多，先全部删除再全部增加
	@Override
	public boolean modifyDevice(String roomNumber, List<RoomDevice> roomDeviceList) {
		boolean flag = true;
		// 先删除所有会议室设备
		roomDeviceDao.deleteRoomDevice(roomNumber);
		// 再添加所有会议室设备
		for (RoomDevice rd : roomDeviceList) {
			// 暂存本次修改结果
			boolean temp = roomDeviceDao.addRoomDevice(roomNumber, rd.getDeviceId(), rd.getCount());
			// 总修改结果与本次修改结果作与运算，一旦某次为false，则最后return false
			flag = flag & temp;
		}
		return flag;
	}

}
