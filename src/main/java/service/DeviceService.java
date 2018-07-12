package service;

import java.util.List;

import entity.Device;

public interface DeviceService {

	
	/**
	 * 得到所有设备信息
	 * @return
	 */
	List<Device> getAllDevice();
	
}
