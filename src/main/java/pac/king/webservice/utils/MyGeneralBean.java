package pac.king.webservice.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


public class MyGeneralBean {
	private String key;
	private String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public MyGeneralBean() {}
	public MyGeneralBean(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
}
