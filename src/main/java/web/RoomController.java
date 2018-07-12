package web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import dtoin.FreeTime;
import dtoin.ModifyDevice;
import dtoin.SetFreeTime;
import entity.RoomDevice;
import service.ManagerService;

import dao.ManagerDao;
import dtoout.AllRoom;

@RequestMapping(value = "/room", method = RequestMethod.POST)
@Controller
public class RoomController {

	@Autowired
	private ManagerService managerService;
	@Autowired
	private ManagerDao managerDao;

	/**
	 * 新增会议室
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public boolean addRoom(HttpServletRequest request) {
		String roomNumber = request.getParameter("roomNumber");
		int capability = Integer.parseInt(request.getParameter("capability"));
		// 若参数有误，则返回false
		if ((roomNumber == null) || (request.getParameter("capability") == null)) {
			return false;
		}
		return managerService.addRoom(roomNumber, capability);
	}

	/**
	 * 设置会议室空闲时间
	 * 
	 * @param setFreeTime
	 * @return
	 */
	@RequestMapping("/setFreeTime")
	@ResponseBody
	public boolean setFreeTime(@RequestBody SetFreeTime setFreeTime) {
		String roomNumber = setFreeTime.getRoomNumber();
		System.out.println("addRoomNumber:" + roomNumber);
		List<FreeTime> freeTimeList = setFreeTime.getFreeTime();
		return managerService.setFreeTime(roomNumber, freeTimeList);
	}

	/**
	 * 修改会议室设备
	 * 
	 * @param modifyDevice
	 * @return
	 */
	@RequestMapping("/modifyDevice")
	@ResponseBody
	public boolean modifyDevice(@RequestBody ModifyDevice modifyDevice) {
		String roomNumber = modifyDevice.getRoomNumber();
		List<RoomDevice> roomDeviceList = modifyDevice.getRoomDevice();
		return managerService.modifyDevice(roomNumber, roomDeviceList);
	}        
	
	/**
	 * 获取所有会议室信息
	 * @param 无
	 * @return 所有会议室信息数据组
	 */
	@RequestMapping("/getAllRoom")
	@ResponseBody
	public List<AllRoom> getAllRooms(HttpServletRequest request) {
		List<AllRoom> allRoom=managerDao.getAllRooms();
		return allRoom;
	}
	
	/**
	 * 根据id查询会议室获取该会议室信息
	 * @param roomNumber
	 * @return 该会议室信息数据
	 */
	@RequestMapping("/searchById")
	@ResponseBody
	public AllRoom searchRoomById(@RequestBody(required=false) Map<String,Object> map) {
		String roomNumber = map.get("roomNumber").toString();
		AllRoom allRoom=managerDao.searchRoomById(roomNumber);
		//注释代码用于测试
		/*System.out.println("这里是Controller的roomNumber: " + roomNumber);
		System.out.println("allRoom:"+allRoom.getCapability()+"   "+allRoom.getRoomNumber());
		List<FreeTime> freetime=allRoom.getFreetime();
		for(int i=0;i<freetime.size();i++){
			System.out.println(freetime.get(i).getStartDate()+"  "+freetime.get(i).getEndDate()+"  "+freetime.get(i).getStartTime()+"  "+freetime.get(i).getEndTime());
		}*/
		return allRoom;
	}
}
