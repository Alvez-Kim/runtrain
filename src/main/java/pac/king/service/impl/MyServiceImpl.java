package pac.king.service.impl;

import org.springframework.stereotype.Service;

import pac.king.service.MyService;

@Service
public class MyServiceImpl implements MyService {

	public String sayHiTo(String name) {
		return "Hi!"+name;
	}

}
