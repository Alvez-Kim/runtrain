package pac.testcase.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;


public class TestAuthorization {
	public static void main(String[] args) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro_perm.ini");
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("King","t;stmdtkg");
		currentUser.login(token);
		
		currentUser.hasRole("admin");
		currentUser.checkRole("admin");
		
		System.out.println(currentUser.isPermitted("user:delete:zhuang"));
		
	}
}

class MyPermission implements Permission{

	public MyPermission() {
	}

	public boolean implies(Permission p) {
		return false;
	}
}
