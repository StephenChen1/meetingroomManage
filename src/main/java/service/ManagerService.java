package service;
import java.util.List;

import dtoin.FreeTime;
import dtoout.AllRoom;
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

	
	/**获取所有会议室信息
	 * @param 无
	 * @return 返回所有会议室的信息
	 */
	public List<AllRoom> getAllRooms();
	
	
	/**
	 * 根据id查询会议室获取该会议室信息
	 * @param roomNumber
	 * @return 该会议室信息数据
	 */
	public AllRoom searchRoomById(String roomNumber);
}
