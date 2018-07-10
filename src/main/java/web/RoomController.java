package web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ManagerDao;

@RequestMapping(value = "/room", method = RequestMethod.POST)
@Controller
public class RoomController {

	@Autowired
	private ManagerDao managerDao;

	@RequestMapping("/add")
	@ResponseBody
	public boolean addRoom(HttpServletRequest request) {
		String roomNumber = request.getParameter("roomNumber");
		int capability = Integer.parseInt(request.getParameter("capability"));
		return managerDao.addMeetingRoom(roomNumber, capability);
	}
}
