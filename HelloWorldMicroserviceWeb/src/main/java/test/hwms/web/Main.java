package test.hwms.web;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;
import test.hwms.model.IUserService;
import test.hwms.util.PropertyUtil;

public class Main {
	public static final TemplateEngine TEMPLATE_ENGINE = new FreeMarkerEngine();
	
	private static final String PROP_PREFIX = "HWMS";
	
	public static final int REST_DEFAULT_PORT = 8010;
	public static final String REST_HOSTNAME_PROP = PROP_PREFIX + "_REST_HOSTNAME";
	public static final String REST_PORT_PROP = PROP_PREFIX + "_REST_PORT";
	
	private static final int WEB_DEFAULT_PORT = 8020;
	private static final String WEB_PORT_PROP = PROP_PREFIX + "_WEB_PORT";
	
	public static void main(String[] args) {
		staticFileLocation("/public");
		port(PropertyUtil.getIntProperty(WEB_PORT_PROP, WEB_DEFAULT_PORT));
		
		HomeController.init();
		
		IUserService user_service = new RESTClientUserService(
				PropertyUtil.getProperty(REST_HOSTNAME_PROP, "localhost"),
				PropertyUtil.getIntProperty(REST_PORT_PROP, REST_DEFAULT_PORT));
		UserController.init(user_service);
		
		notFound((req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("error", "Not found: '" + req.url() + "'");
			return Main.TEMPLATE_ENGINE.render(new ModelAndView(model, "error.ftl"));
		});
		internalServerError((req, res) -> {
			Map<String, Object> model = new HashMap<>();
			model.put("error", "Internal Server Error");
			return Main.TEMPLATE_ENGINE.render(new ModelAndView(model, "error.ftl"));
		});
	}
}
