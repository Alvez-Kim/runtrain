package pac.testcase.shiro;

import java.util.Iterator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.util.WebUtils;

import pac.king.pojo.User;

public class TestSession {
	public static void main(String[] args) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro_session.ini");
		
		SecurityUtils.setSecurityManager(factory.getInstance());
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("King","t;stmdtkg");
		currentUser.login(token);
		
		Session session = currentUser.getSession();
		System.out.println("Host:::"+session.getHost());
		System.out.println("session ID:::"+session.getId());
		
		System.out.println("start time:::"+session.getStartTimestamp());
		System.out.println("last access time:::"+session.getLastAccessTime());
		
		session.touch();
		
		User u = new User(); 
		session.setAttribute(u, "King.");
		Iterator<Object> keyItr = session.getAttributeKeys().iterator();
		while(keyItr.hasNext()){
			Object key = keyItr.next();
			System.out.println(key+":::itr element:::"+session.getAttribute(key));
		}
		
		session.stop();
		
	}
}
