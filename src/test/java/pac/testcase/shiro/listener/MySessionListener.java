package pac.testcase.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener implements SessionListener {

	public void onStart(Session session) {
		System.out.println(session.getId()+" start...");
	}

	public void onStop(Session session) {
		System.out.println(session.getId()+" stop...");
	}

	public void onExpiration(Session session) {
		System.out.println(session.getId()+" expired...");
	}

}
