package com.web.recipe.component;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class UserAuditorAware implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("USER");
	}

}
