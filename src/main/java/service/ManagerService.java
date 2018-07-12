package service;
import java.util.List;

import dtoout.AllBooked;
import dtoout.AllRoom;

public interface ManagerService {
	
	/**
	 * 管理员新增会议室
	 * @param roomNumber
	 * @param capability
	 * @return
	 */
	public boolean addRoom(String roomNumber,int capability);
	
	
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
	
	
	/**
	 * 根据roomNumber删除会议室
	 * @param roomNumber
	 * @return boolean
	 */
	public boolean deleteRoomById(String roomNumber);
	
	
	/**
	 * 查看全部员工所有预约记录
	 * @param 无
	 * @return List<AllBooked>
	 */
	public List<AllBooked> getAllBooked();
	
	/**
	 * 查看对应员工的所有预约记录
	 * @param staffNumber
	 * @return List<AllBooked>
	 */
	public List<AllBooked> getPersonalBooked(String staffNumber);


	/**
	 * 修改会议室容量
	 * @param 会议室编号roomNumber
	 * @param 新容量newCapability
	 * @return boolean
	 */
	public boolean modifyCapability(String roomNumber, int newCapability);
}
