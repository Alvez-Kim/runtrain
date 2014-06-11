package pac.testcase.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;

public class MyRealm0 implements Realm {

	public String getName() {
		return this.getClass().getName();
	}

	public boolean supports(AuthenticationToken token) {
		System.out.println("check support...MyRealm0");
		return true;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		System.out.println("realm0 authenticating....");
		return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),this.getName());
	}
}