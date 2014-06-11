package pac.king.common.security.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import pac.king.dao.MainDao;
import pac.king.pojo.User;

@Service
public class MainRealm extends AuthorizingRealm {
	@Autowired
	private MainDao mainDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return new SimpleAuthorizationInfo();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uToken = (UsernamePasswordToken)token;
		uToken.setRememberMe(true);
		User user =new User();
		user.setName(uToken.getUsername());
		user.setPassword(uToken.getPassword().toString());
		uToken.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toUpperCase().toCharArray());
//		user = mainDao.queryUserInfo(user); 
		return new SimpleAuthenticationInfo(user,token.getCredentials(),getName());
	}

	public MainDao getMainDao() {
		return mainDao;
	}

	public void setMainDao(MainDao mainDao) {
		this.mainDao = mainDao;
	}

	
}
