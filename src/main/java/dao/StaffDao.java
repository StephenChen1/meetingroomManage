package dao;

import org.apache.ibatis.annotations.Param;

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
}
