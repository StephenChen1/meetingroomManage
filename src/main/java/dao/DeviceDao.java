package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Device;

public interface DeviceDao {
    //添加设备
	boolean addDevice(@Param("name") String name,@Param("type") String type);
	
	//查看设备
	List<Device> queryDeviceByName(@Param("name") String name);
	
	//修改设备
	boolean modifyDevice(@Param("device_id") String device_id,@Param("name") String name,@Param("type") String type);
}
