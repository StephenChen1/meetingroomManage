package dao;

import org.apache.ibatis.annotations.Param;

import entity.Login;

public interface LoginDao {

	/**
	 * 根据staffNumber获取认证信息
	 * @param staffNumber
	 * @return
	 */
	Login getByID(@Param("staffNumber") String staffNumber);

}
