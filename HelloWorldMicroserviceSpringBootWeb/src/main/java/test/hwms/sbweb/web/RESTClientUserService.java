package test.hwms.sbweb.web;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import test.hwms.domain.IUserService;
import test.hwms.domain.User;
import test.hwms.domain.UserNotFoundException;
import test.hwms.util.PropertyUtil;

@Component
public class RESTClientUserService implements IUserService {
	private RestTemplate restTemplate;
	private String baseUrl;

	public RESTClientUserService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		baseUrl = "http://" + PropertyUtil.getProperty("HWMS_REST_HOSTNAME", "192.168.99.100") + ":"
				+ PropertyUtil.getIntProperty("HWMS_REST_PORT", 8080) + "/service";
	}

	@Override
	public Collection<User> getAllUsers() {
		return Arrays.asList(restTemplate.getForObject(baseUrl + "/users", User[].class));
	}

	@Override
	public User getUser(int id) throws UserNotFoundException {
		try {
			return restTemplate.getForObject(baseUrl + "/user/" + id, User.class);
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new UserNotFoundException(id);
			}
			throw e;
		}
	}

	@Override
	public User createUser(String name, String email) {
		return restTemplate.postForObject(baseUrl + "/user", new User(name, email), User.class);
	}

	@Override
	public void updateUser(int id, String name, String email) throws UserNotFoundException {
		restTemplate.put(baseUrl + "/user/" + id, new User(name, email));
	}

	@Override
	public void deleteUser(int id) throws UserNotFoundException {
		restTemplate.delete(baseUrl + "/user/" + id);
	}
}
