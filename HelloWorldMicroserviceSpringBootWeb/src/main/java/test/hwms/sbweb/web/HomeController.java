package test.hwms.sbweb.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SuppressWarnings("static-method")
public class HomeController {
	@GetMapping("/")
	String home(Map<String, Object> model) {
		model.put("page", "home");
		return "home";
	}
	
	@GetMapping("/about")
	String about(Map<String, Object> model) throws UnknownHostException {
		model.put("page", "about");
		model.put("os_name", System.getProperty("os.name"));
		model.put("hostname", InetAddress.getLocalHost().getHostName());
		return "about";
	}
}
