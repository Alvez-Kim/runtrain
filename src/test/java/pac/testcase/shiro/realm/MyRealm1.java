package pac.testcase.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

	public String getName() {
		return this.getClass().getName();
	}

	public boolean supports(AuthenticationToken token) {
		System.out.println("check support...MyRealm1");
		return true;
	}

	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		if(!new String((char[])token.getCredentials()).equals("t;stmdtkg"))
			throw new IncorrectCredentialsException();
			
		return new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),this.getName());
	}
}