package test.hwms.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import test.hwms.model.IUserService;
import test.hwms.model.User;
import test.hwms.model.UserNotFoundException;

public class RESTClientUserService implements IUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RESTClientUserService.class);
	private static final Gson GSON = new Gson();
	
	private HttpHost httpHost;
	
	public RESTClientUserService(String hostname, int port) {
		this(hostname, port, "http");
	}
	
	public RESTClientUserService(String hostname, int port, String scheme) {
		httpHost = new HttpHost(hostname, port, scheme);
	}

	@Override
	public Collection<User> getAllUsers() {
		HttpGet get = new HttpGet("/service/users");
		try (CloseableHttpClient client = HttpClients.createDefault();
				CloseableHttpResponse response = client.execute(httpHost, get)) {
			int status = response.getStatusLine().getStatusCode();
			LOGGER.debug("Get users response status: {}", Integer.valueOf(status));
			// TODO Handle error status codes
			
			return Arrays.asList(GSON.fromJson(EntityUtils.toString(response.getEntity()), User[].class));
		} catch (IOException e) {
			LOGGER.error("Error invoking get Users REST service: " + e, e);
		}
		return null;
	}

	@Override
	public User getUser(int id) throws UserNotFoundException {
		HttpGet get = new HttpGet("/service/user/" + id);
		try (CloseableHttpClient client = HttpClients.createDefault();
				CloseableHttpResponse response = client.execute(httpHost, get)) {
			int status = response.getStatusLine().getStatusCode();
			LOGGER.debug("Get user response status: {}", Integer.valueOf(status));
			// TODO Handle error status codes
			
			return GSON.fromJson(EntityUtils.toString(response.getEntity()), User.class);
		} catch (IOException e) {
			LOGGER.error("Error invoking get User REST service: " + e, e);
		}
		return null;
	}

	@Override
	public User createUser(String name, String email) {
		HttpPost post = new HttpPost("/service/user");
		StringEntity input;
		try {
			input = new StringEntity(GSON.toJson(new User(name, email)));
			input.setContentType("application/json");
			post.setEntity(input);
			
			try (CloseableHttpClient client = HttpClients.createDefault();
					CloseableHttpResponse response = client.execute(httpHost, post)) {
				int status = response.getStatusLine().getStatusCode();
				LOGGER.debug("Create users response status: {}", Integer.valueOf(status));
				// TODO Handle error status codes
				
				return GSON.fromJson(EntityUtils.toString(response.getEntity()), User.class);
			}
		} catch (IOException e) {
			LOGGER.error("Error invoking get User REST service: " + e, e);
		}
		return null;
	}

	@Override
	public User updateUser(int id, String name, String email) throws UserNotFoundException {
		HttpPut put = new HttpPut("/service/user");
		StringEntity input;
		try {
			input = new StringEntity(GSON.toJson(new User(id, name, email)));
			input.setContentType("application/json");
			put.setEntity(input);
			
			try (CloseableHttpClient client = HttpClients.createDefault();
					CloseableHttpResponse response = client.execute(httpHost, put)) {
				int status = response.getStatusLine().getStatusCode();
				LOGGER.debug("Update users response status: {}", Integer.valueOf(status));
				// TODO Handle error status codes
				
				return GSON.fromJson(EntityUtils.toString(response.getEntity()), User.class);
			}
		} catch (IOException e) {
			LOGGER.error("Error invoking get User REST service: " + e, e);
		}
		return null;
	}

	@Override
	public User deleteUser(int id) throws UserNotFoundException {
		HttpDelete delete = new HttpDelete("/service/user/" + id);
		try (CloseableHttpClient client = HttpClients.createDefault();
				CloseableHttpResponse response = client.execute(httpHost, delete)) {
			int status = response.getStatusLine().getStatusCode();
			LOGGER.debug("Delete user response status: {}", Integer.valueOf(status));
			// TODO Handle error status codes
			
			return GSON.fromJson(EntityUtils.toString(response.getEntity()), User.class);
		} catch (IOException e) {
			LOGGER.error("Error invoking get User REST service: " + e, e);
		}
		return null;
	}
}
