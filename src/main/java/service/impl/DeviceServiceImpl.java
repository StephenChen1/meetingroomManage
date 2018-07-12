package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DeviceDao;
import entity.Device;
import service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceDao deviceDao ;
	
	@Override
	public List<Device> getAllDevice() {
		List<Device> devices  = deviceDao.queryAllDevices();
		return devices;
	}

	
	
}
