package pac.testcase.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogback {
	
	
	/*
	 * Logback tries to find a file called logback.groovy in the classpath.
	 * If no such file is found, logback tries to find a file called logback-test.xml in the classpath.
	 * If no such file is found, it checks for the file logback.xml in the classpath..
	 * If neither file is found, 
	 * logback configures itself automatically using the BasicConfigurator which will cause logging output to be directed to the console.*/
	
	static final Logger logger = LoggerFactory.getLogger(TestLogback.class);

	public static void main(String[] args) {
		logger.debug("debug msg");
	}
}
