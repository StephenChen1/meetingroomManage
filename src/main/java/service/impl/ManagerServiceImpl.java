package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ManagerDao;
import dtoout.AllBooked;
import dtoout.AllRoom;
import service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	ManagerDao managerDao;
	
	
	@Override
	public boolean addRoom(String roomNumber,int capability){
		return managerDao.addMeetingRoom(roomNumber, capability);
	}

	/**获取所有会议室信息
	 * @param 无
	 * @return 返回所有会议室的信息
	 */
	@Override
	public List<AllRoom> getAllRooms() {
		return managerDao.getAllRooms();
	}

	
	/**
	 * 根据id查询会议室获取该会议室信息
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
