package test.hwms.service;

import static spark.Spark.port;

import test.hwms.util.PropertyUtil;

public class Main {
	public static final int REST_DEFAULT_PORT = 8010;
	public static final String REST_PORT_PROP = "HWMS_REST_PORT";
	
	public static void main(String[] args) {
		port(PropertyUtil.getIntProperty(REST_PORT_PROP, REST_DEFAULT_PORT));
		
		UserServiceController.init(new Sql2oUserService());
	}
}
