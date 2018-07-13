package web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ManagerDao;
import dao.StaffDao;
import dtoin.FreeTime;
import dtoin.ModifyDevice;
import dtoin.MyBookTime;
import dtoin.SetFreeTime;
import dtoin.UpdateBook;
import dtoout.AllBooked;
import dtoout.AllRoom;
import dtoout.BookedTime;
import entity.RoomDevice;
import service.BookService;
import service.JwtService;
import service.MailService;
import service.ManagerService;

@RequestMapping(value = "/room", method = { RequestMethod.POST, RequestMethod.GET })
@Controller
public class RoomController {

	@Autowired
	private ManagerService managerService;
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private BookService bookService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private StaffDao staffDao;
	@Autowired
	private MailService mailService;

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
	 * 
	 * @param 无
	 * @return 所有会议室信息数据组
	 */
	@RequestMapping("/getAllRoom")
	@ResponseBody
	public List<AllRoom> getAllRooms(HttpServletRequest request) {
		List<AllRoom> allRoom = managerDao.getAllRooms();
		return allRoom;
	}

	/**
	 * 根据id查询会议室获取该会议室信息
	 * 
	 * @param roomNumber
	 * @return 该会议室信息数据
	 */
	@RequestMapping("/searchById")
	@ResponseBody
	public AllRoom searchRoomById(@RequestBody(required = false) Map<String, Object> map) {
		String roomNumber = map.get("roomNumber").toString();
		AllRoom allRoom = managerDao.searchRoomById(roomNumber);
		// 注释代码用于测试
		/*
		 * System.out.println("这里是Controller的roomNumber: " + roomNumber);
		 * System.out.println("allRoom:"+allRoom.getCapability()+"   "+allRoom.
		 * getRoomNumber()); List<FreeTime> freetime=allRoom.getFreetime(); for(int
		 * i=0;i<freetime.size();i++){
		 * System.out.println(freetime.get(i).getStartDate()+"  "+freetime.get(i).
		 * getEndDate()+"  "+freetime.get(i).getStartTime()+"  "+freetime.get(i).
		 * getEndTime()); }
		 */
		return allRoom;
	}

	/**
	 * 用户预约会议室
	 * 
	 * @param updateBook
	 * @param token
	 * @return
	 * @throws MessagingException 
	 */
	@RequestMapping("/book")
	@ResponseBody
	public List<MyBookTime> book(@RequestBody(required = true) UpdateBook updateBook,
			@CookieValue(value = "token", required = false) String token) throws MessagingException {
		String canBookId = updateBook.getCanBookId();
		MyBookTime bookedTime = updateBook.getMyBookTime();
		// 插入一条booked数据，并获取其timeBookedId
		String staffNumber = this.readCookie(token);
		String roomNumber = bookService.getRoomNumber(canBookId);
		int timeBookedId = bookService.addBookedAndReturnTBid(staffNumber, roomNumber);
		// 插入time_booked数据
		boolean isOK = bookService.addTimeBooked(timeBookedId, bookedTime.getStartDate(), bookedTime.getEndDate(),
				bookedTime.getStartTime(), bookedTime.getEndTime());
		// 更新canBook表
		List<MyBookTime> list = bookService.updateCanBook(canBookId, bookedTime);
		// 成功预约后，若用户邮箱不为空则发送邮件
		if (staffDao.queryStaffById(staffNumber).getEmail() != null) {
			String msg = "您已成功预约" + roomNumber + "会议室,您可在" + bookedTime.getStartDate() + "~" + bookedTime.getEndDate()
					+ " , " + bookedTime.getStartTime() + "~" + bookedTime.getEndTime();
			mailService.bookedOK(staffDao.queryStaffById(staffNumber).getEmail(), msg);
		}

		return list;
	}

	public boolean deleteRoomById(@RequestBody(required = false) Map<String, Object> map) {
		String roomNumber = map.get("roomNumber").toString();
		System.out.println(roomNumber);
		boolean flag = managerService.deleteRoomById(roomNumber);
		return flag;
	}

	/**
	 * 查看全部员工所有预约记录
	 * 
	 * @param 无
	 * @return List<AllBooked>
	 */
	@RequestMapping("/getAll")
	@ResponseBody
	public List<AllBooked> getAllBooked(HttpServletRequest request) {
		List<AllBooked> allBooked = managerDao.getAllBooked();
		return allBooked;
	}

	/**
	 * 查看历史预约记录
	 * 
	 * @param 无
	 * @return List<AllBooked>
	 * @throws ParseException
	 */
	@RequestMapping("/getHistoryBooked")
	@ResponseBody
	public List<AllBooked> getHistoryBooked(HttpServletRequest request) throws ParseException {

		// 直接返回所有预约记录再对结果判断，要是历史预约记录则放入historyBooked中
		List<AllBooked> allBooked = managerDao.getAllBooked(); // 所有预约记录
		List<AllBooked> historyBooked = new ArrayList<AllBooked>(); // 历史预约记录
		System.out.println("这里是Controller里的getHistoryBooked方法");
		System.out.println(allBooked.size());
		for (int i = 0; i < allBooked.size(); i++) {
			AllBooked allRoom = allBooked.get(i);
			BookedTime freetime = allRoom.getBookedTime();
			try {
				String startDate = freetime.getStartDate();
				String endDate = freetime.getEndDate();
				String startTime = freetime.getStartTime();
				String endTime = freetime.getEndTime();
				System.out.println("start:" + startDate + " " + startTime);
				String startDate_Time = startDate + " " + startTime;
				System.out.println("end:" + endDate + " " + endTime);
				String endDate_Time = endDate + " " + endTime;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date nowTime = new Date();
				String s = null;
				s = sdf.format(nowTime);
				System.out.println("now:" + s);
				Date now = sdf.parse(s);
				Date bt = sdf.parse(startDate_Time);
				Date et = sdf.parse(endDate_Time);

				// 第一种情况，开始日期的开始时间位于now之前或刚好相等且结束日期的结束时间大于now
				if ((bt.before(now) || bt.equals(now)) && et.after(now)) {
					historyBooked.add(allRoom);
				}

				// 第二种情况，结束日期的结束时间早于或等于now
				if (et.before(now) || et.equals(now)) {
					historyBooked.add(allRoom);
				}
			} catch (NullPointerException e) {
				// 当取到BookedTime freetime里的值为null时抛出异常
				System.out.println("已捕获到NullPointerException异常");
			}
			// 用于在表数据还没完整前进行测试
			System.out.println("还没抛出异常时historyBooked的大小：" + historyBooked.size());
		}

		return historyBooked;
	}

	/**
	 * 查看未来的预约记录
	 * 
	 * @param 无
	 * @return List<AllBooked>
	 * @throws ParseException
	 */
	@RequestMapping("/getFutureBooked")
	@ResponseBody
	public List<AllBooked> getFutureBooked(HttpServletRequest request) throws ParseException {

		// 直接返回所有预约记录再对结果判断，要是未来的预约记录则放入futureBooked中
		List<AllBooked> allBooked = managerDao.getAllBooked(); // 所有预约记录
		List<AllBooked> futureBooked = new ArrayList<AllBooked>(); // 未来预约记录
		System.out.println("这里是Controller里的getFutureBooked方法");
		System.out.println(allBooked.size());
		for (int i = 0; i < allBooked.size(); i++) {
			AllBooked allRoom = allBooked.get(i);
			BookedTime freetime = allRoom.getBookedTime();
			try {
				String startDate = freetime.getStartDate();
				String startTime = freetime.getStartTime();
				System.out.println("start:" + startDate + " " + startTime);
				String startDate_Time = startDate + " " + startTime;

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date nowTime = new Date();
				String s = null;
				s = sdf.format(nowTime);
				System.out.println("now:" + s);
				Date now = sdf.parse(s);
				Date bt = sdf.parse(startDate_Time);

				// 开始日期的开始时间位于now之后
				if (bt.after(now)) {
					futureBooked.add(allRoom);
				}
			} catch (NullPointerException e) {
				// 当取到BookedTime freetime里的值为null时抛出异常
				System.out.println("已捕获到NullPointerException异常");
			}
			// 用于在表数据还没完整前进行测试
			System.out.println("还没抛出异常时futureBooked的大小：" + futureBooked.size());
		}
		return futureBooked;
	}

	/**
	 * 查看对应员工的所有预约记录
	 * 
	 * @param roomNumber
	 * @return List<AllBooked>
	 */
	@RequestMapping("/getPersonalBooked")
	@ResponseBody
	public List<AllBooked> getPersonalBooked(@RequestBody(required = false) Map<String, Object> map) {
		String staffNumber = map.get("staffNumber").toString();
		List<AllBooked> allBooked = managerDao.getPersonalBooked(staffNumber);
		// 注释代码用于测试
		/*
		 * System.out.println("这里是Controller外的staffNumber: " + staffNumber);
		 * System.out.println(allBooked.size()); for(int i=0;i<allBooked.size();i++){
		 * AllBooked allRoom=allBooked.get(i);
		 * System.out.println("这里是Controller里的staffNumber: " + staffNumber);
		 * System.out.println("allRoom:"+allRoom.getCapability()+"   "+allRoom.
		 * getRoomNumber()); BookedTime freetime=allRoom.getBookedTime();
		 * System.out.println(freetime.getStartDate()+"  "+freetime.getEndDate()+"  "
		 * +freetime.getStartTime()+"  "+freetime.getEndTime());
		 * System.out.println("\n\n"); }
		 */
		return allBooked;
	}

	/**
	 * 修改会议室容量
	 * 
	 * @param 会议室编号roomNumber
	 * @param 新容量newCapability
	 * @return boolean
	 */
	@RequestMapping(value = "/modifyCapability", method = RequestMethod.POST)
	@ResponseBody
	public boolean modifyPassword(@RequestBody(required = false) Map<String, Object> map) {
		String roomNumber = map.get("roomNumber").toString();
		int newCapability = Integer.valueOf(map.get("newCapability").toString());
		// System.out.println("已进入modifyCapability方法");
		// System.out.println(roomNumber+" "+newCapability);
		// boolean isOK = true;
		boolean isOK = managerService.modifyCapability(roomNumber, newCapability);
		// System.out.println("ISOK：" + isOK);
		return isOK;
	}

	/**
	 * 私有方法，读取cookie中的token并解析为staffNumber
	 * 
	 * @param token
	 * @return
	 */
	private String readCookie(@CookieValue(value = "token", required = false) String token) {
		if (token == null) {
			return null;
		}
		String staffNumber = jwtService.getStaffNum(token);
		return staffNumber;
	}
}
