package dao;

import org.apache.ibatis.annotations.Param;

import entity.Staff;

public interface StaffDao {

	Staff queryStaffById(@Param("id") String id);
	
}
