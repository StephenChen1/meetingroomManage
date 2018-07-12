package web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Device;
import service.DeviceService;

//设备接口

@RequestMapping(value = "/device")
@Controller
public class DeviceController {

	@Autowired
	private DeviceService deviceService ;
	
	//得到所有设备
	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<Device> getAllDevices(){
		List<Device> devices = deviceService.getAllDevice();
		return devices ;
	}
	
}
