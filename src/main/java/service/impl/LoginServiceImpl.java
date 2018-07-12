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
}
