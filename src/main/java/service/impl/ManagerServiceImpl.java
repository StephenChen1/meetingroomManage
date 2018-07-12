package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ManagerDao;
import dao.RoomDeviceDao;
import dtoin.FreeTime;
import dtoout.AllBooked;
import dtoout.AllRoom;
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

	/**
	 * 获取所有会议室信息
	 * 
	 * @param 无
	 * @return 返回所有会议室的信息
	 */
	@Override
	public List<AllRoom> getAllRooms() {
		return managerDao.getAllRooms();
	}

	/**
	 * 根据id查询会议室获取该会议室信息
	 * 
	 * @param roomNumber
	 * @return 该会议室信息数据
	 */
	@Override
	public AllRoom searchRoomById(String roomNumber) {
		return managerDao.searchRoomById(roomNumber);
	}
	

	/**
	 * 根据roomNumber删除会议室
	 * @param roomNumber
	 * @return boolean
	 */
	@Override
	public boolean deleteRoomById(String roomNumber) {
		return managerDao.deleteRoomById(roomNumber);
	}

	
	/**
	 * 查看全部员工所有预约记录
	 * @param 无
	 * @return List<AllBooked>
	 */
	@Override
	public List<AllBooked> getAllBooked() {
		return managerDao.getAllBooked();
	}

	/**
	 * 查看对应员工的所有预约记录
	 * @param staffNumber
	 * @return List<AllBooked>
	 */
	public List<AllBooked> getPersonalBooked(String staffNumber){
		return managerDao.getPersonalBooked(staffNumber);
	}
	
}
