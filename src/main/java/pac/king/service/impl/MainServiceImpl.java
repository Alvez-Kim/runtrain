package pac.king.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import pac.king.dao.MainDao;
import pac.king.pojo.Menu;
import pac.king.pojo.User;
import pac.king.service.MainService;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	private MainDao mainDao;

	public boolean isUserAuthenticated(User user) {
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toUpperCase());
		return mainDao.checkUserExists(user) > 0;
	}
	
	public User userLogin(User user) {
		if(user==null||user.getPassword()==null)return null;
		
		String errorMsg = StringUtils.EMPTY;
		
		Subject currentUser = SecurityUtils.getSubject();
//		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toUpperCase());
		try {
			currentUser.login(new UsernamePasswordToken(user.getName(), user.getPassword()));
		} catch ( UnknownAccountException e ) {
			errorMsg = "你是谁?";
		} catch ( IncorrectCredentialsException e ) {
			errorMsg = "密码错误!!";
		} catch ( LockedAccountException e ) {
			errorMsg = "该账户不可用~";
		} catch ( ExcessiveAttemptsException e ) {
			errorMsg = "别再试了!!";
		}
		
		if(StringUtils.isNotBlank(errorMsg)){
			System.out.println("login error:::"+errorMsg);
			return null;
		}
//		return mainDao.queryUserInfo(user);
		return user;
	}

	public List<Menu> fetchMenu() {
		return mainDao.queryMenu();
	}

	public int userRegist(User user) {
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toUpperCase());
		return mainDao.insertUserInfo(user);
	}
}
