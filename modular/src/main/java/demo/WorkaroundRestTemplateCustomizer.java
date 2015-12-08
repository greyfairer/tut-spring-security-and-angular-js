package demo;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * WorkaroundRestTemplateCustomizer.
 */ // Remove this when upgrading to Spring Boot 1.3.1 (https://github.com/spring-projects/spring-boot/issues/4553)
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WorkaroundRestTemplateCustomizer implements UserInfoRestTemplateCustomizer {

	@Override
	public void customize(OAuth2RestTemplate template) {
		template.setInterceptors(new ArrayList<>(template.getInterceptors()));
	}

}
