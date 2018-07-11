package service;

import java.util.List;

import dtoin.FreeTime;
import entity.RoomDevice;

public interface ManagerService {
	
	/**
	 * 管理员新增会议室
	 * @param roomNumber
	 * @param capability
	 * @return
	 */
	public boolean addRoom(String roomNumber,int capability);
	
	/**
	 * 修改会议室空闲时间
	 * @param roomNumber
	 * @param freeTime
	 * @return
	 */
	public boolean setFreeTime(String roomNumber,List<FreeTime> freeTimeList);

	/**
	 * 修改会议室设备
	 * @param roomNumber
	 * @param roomDeviceList
	 * @return
	 */
	public boolean modifyDevice(String roomNumber,List<RoomDevice> roomDeviceList);
}
