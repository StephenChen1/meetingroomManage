package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dtoout.CanBooked;
import entity.Staff;

public interface StaffDao {

	/**
	 * 查询员工
	 * 
	 * @param id
	 * @return
	 */
	Staff queryStaffById(@Param("id") String id);

	/**
	 * 增加员工
	 * 
	 * @param id
	 * @param name
	 * @param level
	 * @param birthday
	 * @param phone
	 * @param address
	 * @return
	 */
	boolean addStaff(@Param("id") String id, @Param("name") String name, @Param("level") String level,
			@Param("birthday") String birthday, @Param("phone") String phone, @Param("address") String address);

	/**
	 * 修改员工资料
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	boolean modifyStaff(@Param("id") String id, @Param("name") String name, @Param("birthday") String birthday,
			@Param("phone") String phone);

	//修改个人信息，区别修改员工资料
	boolean modifyInfo(@Param("staffNumber")String staffNumber,@Param("newStaffNumber")String newStaffNumber, @Param("newName")String newName, @Param("newPhone")String newPhone,
			@Param("newEmail")String newEmail,@Param("newBirthday")String newBirthday, @Param("newAddress")String newAddress, @Param("newDepartment")String newDepartment,
			@Param("newPosition")String newPosition);

	//查找所有员工
	List<Staff> queryAll();

	
	//得到所有可预约时间段
	List<CanBooked> queryAllCanBooked();
	

}
