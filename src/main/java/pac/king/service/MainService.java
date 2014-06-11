package pac.king.service;

import java.util.List;

import pac.king.pojo.Menu;
import pac.king.pojo.User;

public interface MainService {
	public boolean isUserAuthenticated(User user);	
	public List<Menu> fetchMenu();
	public int userRegist(User user);
	public User userLogin(User user);
}
