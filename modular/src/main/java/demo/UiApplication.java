package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@Controller
//@EnableResourceServer
@EnableOAuth2Sso
public class UiApplication {

	// Match everything without a suffix (so not a static resource)
	@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
		// Forward to home page so that route is preserved.
		return "forward:/";
	}

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}

	@RequestMapping("/resource")
	@ResponseBody
	public Map<String, Object> home(Authentication authentication) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		model.put("authentication", authentication);
		model.put("authenticationDetails", authentication.getDetails());
		return model;
	}

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}
}

