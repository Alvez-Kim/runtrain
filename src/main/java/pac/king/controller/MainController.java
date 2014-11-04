package pac.king.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pac.king.common.SessionScope;
import pac.king.dao.MainDao;
import pac.king.pojo.Menu;
import pac.king.pojo.User;
import pac.king.service.MainService;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private MainDao mainDao;
	@Autowired
	private MainService mainService;
	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/index")
	//@SessionScope("currentUser")
	public ModelAndView index(User currentUser) {
        ModelAndView mav;
		if (currentUser==null || currentUser.getId()==null)
			mav = new ModelAndView("/login");
		else {
			mav = new ModelAndView("/index");
			List<Menu> menuList = mainService.fetchMenu();
			mav.addObject(menuList);
		}
		return mav;
	}
	
	@RequestMapping(value ="/userList",method=RequestMethod.GET,headers={"Accept="+MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody User[] queryUserList(){
		System.out.println("delete+++");
		User[] u = new User[4];
		u[0] = new User("0001","King","t;stmdtkg");
		u[1] = new User("0001","King","t;stmdtkg");
		u[2] = new User("0001","King","t;stmdtkg");
		u[3] = new User("0001",null,"");

		return u; 
	}
	
	@RequestMapping(value="/upload")
	public void uploadFile(@RequestParam MultipartFile file){
		System.out.println(file.getContentType());
	}
	
	@RequestMapping(value = "/welcome")
	public String welcome(@SessionScope("currentUser")User currentUser) {
		System.out.println("");
//		System.out.println(((User)WebUtils.getSessionAttribute(request, "currentUser")).getName());
		return "/main";
	}

	@RequestMapping(value = "/menu")
	public String initMenu() {
		List<Menu> menuList = mainService.fetchMenu();
		for (Menu menu : menuList) {
			System.out.println(menu.getName());
		}
		return "/menu";
	}

	@RequestMapping(value = "/loginPage")
	public String initLogin() {
		System.out.println("initLogin");
		return "/login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		System.out.println("logout++++++");
		return "/main";
	}

	@RequestMapping(value = "/login")
	public String login(User user, ModelMap model) {
		System.out.println("login+++++");
		WebUtils.getRequiredWebEnvironment(servletContext).getSecurityManager();
		mainDao.queryMenu();
		if ((user=mainService.userLogin(user))!=null) {
			model.addAttribute("currentUser", user);
//			WebUtils.setSessionAttribute(request, "currentUser", user);
			return "/main";
		} 
		return "/login";
	}

	@RequestMapping(value = "/regist")
	public String regist(User user) {
		mainService.userRegist(user);

		return "/regist";
	}
	
	final Logger logger = LoggerFactory.getLogger(MainController.class);

}
