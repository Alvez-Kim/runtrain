package pac.king.webservice.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MyMapAdapter extends XmlAdapter<MyGeneralBean[], HashMap<String,String>>{

	@Override
	public HashMap<String, String> unmarshal(MyGeneralBean[] v)
			throws Exception {
		HashMap<String,String> resultMap = new HashMap<String, String>();
		for (MyGeneralBean e : v) {
			resultMap.put(e.getKey(), e.getValue());
		}
		return resultMap;
	}

	@Override
	public MyGeneralBean[] marshal(HashMap<String, String> v) throws Exception {
		MyGeneralBean[] m = new MyGeneralBean[10];
		int i=0;
		for (Entry<String, String> entry : v.entrySet()) {
			m[++i] = new MyGeneralBean(entry.getKey(), entry.getValue());
		}
		return m;
	}
	
}
