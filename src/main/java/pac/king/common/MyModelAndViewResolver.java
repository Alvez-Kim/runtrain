package pac.king.common;

import java.lang.reflect.Method;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

public class MyModelAndViewResolver implements ModelAndViewResolver {

	public ModelAndView resolveModelAndView(Method handlerMethod,
			Class<?> handlerType, Object returnValue,
			ExtendedModelMap implicitModel, NativeWebRequest webRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
