package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dtoin.FreeTime;
import dtoin.SetFreeTime;
import service.ManagerService;

@RequestMapping(value = "/room", method = RequestMethod.POST)
@Controller
public class RoomController {

	@Autowired
	private ManagerService managerService;

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
		List<FreeTime> freeTimeList = setFreeTime.getFreeTime();
		return managerService.setFreeTime(roomNumber, freeTimeList);
	}
}
