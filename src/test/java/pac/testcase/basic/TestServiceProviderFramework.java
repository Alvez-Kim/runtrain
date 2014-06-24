package pac.testcase.basic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestServiceProviderFramework {

}
interface Service{
	
} 

interface Provider{
	Service newService();
}

class Services{
	private Services(){}
	
	private static final Map<String,Provider> providers = new ConcurrentHashMap<>();
	public static final String DEFAULT_PROVIDER_NAME="King_Service";
	
	public static void registerDefaultProvider(Provider p){
		providers.put(DEFAULT_PROVIDER_NAME, p);
	}
	
	public static void registerProvider(String name,Provider p){
		providers.put(name, p);
	}
	
	public static Service newInstance() throws IllegalAccessException{
		return newInstance(DEFAULT_PROVIDER_NAME);
	}
	public static Service newInstance(String name) throws IllegalAccessException{
		Provider p = providers.get(name);
		if(p==null)
			throw new IllegalAccessException("No provider registered with name:"+name);
		return p.newService();
	}
}