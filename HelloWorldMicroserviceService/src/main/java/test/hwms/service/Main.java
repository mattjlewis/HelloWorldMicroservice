package test.hwms.service;

import static spark.Spark.port;

import test.hwms.domain.IUserService;
import test.hwms.util.PropertyUtil;

public class Main {
	public static final int REST_DEFAULT_PORT = 8010;
	public static final String REST_PORT_PROP = "HWMS_REST_PORT";
	
	public static void main(String[] args) {
		start(new Sql2oUserService());
		//start(new StaticUserService());
	}
	
	public static void start(IUserService userService) {
		port(PropertyUtil.getIntProperty(REST_PORT_PROP, REST_DEFAULT_PORT));
		
		UserServiceController.init(userService);
	}
}
