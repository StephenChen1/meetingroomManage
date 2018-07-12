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

import service.ManagerService;
import dao.ManagerDao;
import dtoout.AllBooked;
import dtoout.AllRoom;
import dtoout.BookedTime;
import dtoout.FreeTime;


@RequestMapping(value = "/room", method = RequestMethod.POST)
@Controller
public class RoomController {

	@Autowired
	private ManagerDao managerDao;
	
	@Autowired
	private ManagerService managerService;

	@RequestMapping("/add")
	@ResponseBody
	public boolean addRoom(HttpServletRequest request) {
		String roomNumber = request.getParameter("roomNumber");
		int capability = Integer.parseInt(request.getParameter("capability"));
		return managerDao.addMeetingRoom(roomNumber, capability);
	}
	
	
	/**
	 * 获取所有会议室信息
	 * @param 无
	 * @return 所有会议室信息数据组
	 */
	@RequestMapping("/getAllRoom")
	@ResponseBody
	public List<AllRoom> getAllRooms(HttpServletRequest request) {
		List<AllRoom> allRoom=managerService.getAllRooms();
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
		AllRoom allRoom=managerService.searchRoomById(roomNumber);
		//注释代码用于测试
		/*System.out.println("这里是Controller的roomNumber: " + roomNumber);
		System.out.println("allRoom:"+allRoom.getCapability()+"   "+allRoom.getRoomNumber());
		List<FreeTime> freetime=allRoom.getFreetime();
		for(int i=0;i<freetime.size();i++){
			System.out.println(freetime.get(i).getStartDate()+"  "+freetime.get(i).getEndDate()+"  "+freetime.get(i).getStartTime()+"  "+freetime.get(i).getEndTime());
		}*/
		return allRoom;
	}
	
	/**
	 * 根据roomNumber删除会议室
	 * @param roomNumber
	 * @return boolean
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public boolean deleteRoomById(@RequestBody(required=false) Map<String,Object> map) {
		String roomNumber = map.get("roomNumber").toString();
		boolean flag=managerService.deleteRoomById(roomNumber);
		return flag;
	}
	
	/**
	 * 查看全部员工所有预约记录
	 * @param 无
	 * @return List<AllBooked>
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public List<AllBooked> getAllBooked(HttpServletRequest request) {
		List<AllBooked> allBooked=managerDao.getAllBooked();
		return allBooked;
	}
	
	/**
	 * 查看对应员工的所有预约记录
	 * @param roomNumber
	 * @return List<AllBooked>
	 */
	@RequestMapping("/getPersonalBooked")
	@ResponseBody
	public List<AllBooked> getPersonalBooked(@RequestBody(required=false) Map<String,Object> map) {
		String staffNumber = map.get("staffNumber").toString();
		List<AllBooked> allBooked=managerDao.getPersonalBooked(staffNumber);
		//注释代码用于测试
		/*System.out.println("这里是Controller外的staffNumber: " + staffNumber);
		System.out.println(allBooked.size());
		for(int i=0;i<allBooked.size();i++){
			AllBooked allRoom=allBooked.get(i);
			System.out.println("这里是Controller里的staffNumber: " + staffNumber);
			System.out.println("allRoom:"+allRoom.getCapability()+"   "+allRoom.getRoomNumber());
			BookedTime freetime=allRoom.getBookedTime();
			System.out.println(freetime.getStartDate()+"  "+freetime.getEndDate()+"  "+freetime.getStartTime()+"  "+freetime.getEndTime());
			System.out.println("\n\n");
		}*/
		return allBooked;
	}

}
