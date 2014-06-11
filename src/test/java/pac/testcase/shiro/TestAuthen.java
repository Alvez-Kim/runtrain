package pac.testcase.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class TestAuthen {
	public static void main(String[] args) {

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
	    SecurityManager manager = factory.getInstance();
	    SecurityUtils.setSecurityManager(manager);
	    
		UsernamePasswordToken token = new UsernamePasswordToken("king", "t;stmdtkg");
		token.setRememberMe(true);
		
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch ( UnknownAccountException e ) {
			System.out.println("你是谁?");
		} catch ( IncorrectCredentialsException e ) {
			System.out.println("密码错误!!");
		} catch ( LockedAccountException e ) {
			System.out.println("该账户不可用~");
		} catch ( ExcessiveAttemptsException e ) {
			System.out.println("别再试了!!");
		}
		
	}
}
