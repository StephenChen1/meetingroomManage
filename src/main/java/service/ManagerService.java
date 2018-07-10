package service;

import java.util.List;

import dtoin.FreeTime;

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

}
