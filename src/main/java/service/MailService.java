package service;

public interface MailService {
	/**
	 * 发送预定【成功】的邮件
	 * @param mailAddress
	 * @return
	 */
	public void bookedOK(String mailAddress);
	
	/**
	 * 发送预定【取消】的邮件
	 * @param mailAddress
	 * @return
	 */
	public void bookedCancel(String mailAddress);
	
}
