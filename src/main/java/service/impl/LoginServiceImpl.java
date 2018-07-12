package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.LoginDao;
import entity.Login;
import service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	/**
	 * 判断登陆是否成功
	 * 
	 * @param staffNum
	 * @param password
	 * @return 登陆成功返回true，失败返回false
	 */
	public boolean login(String staffNumber, String password) {
		Login login = loginDao.getByID(staffNumber);
		// login == null表示没有该用户
		if ((login==null) || (!password.equals(login.getPassword()))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 修改密码
	 * @param 员工编号staffNumber
	 * @param 原密码oldPass
	 * @param 新密码newPass
	 * @return boolean
	 */
	@Override
	public boolean modifyPassword(String staffNumber, String oldPass,
			String newPass) {
		return loginDao.modifyPassword(staffNumber,oldPass,newPass);
	}
}
