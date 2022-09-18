package com.hepsisurada.emailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.web.reactive.server.DefaultWebFluxTagsProvider;
import org.springframework.boot.actuate.metrics.web.reactive.server.WebFluxTagsProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	/**
	 * Bean to avoid requests which has UNKNOWN uri field
	 * https://github.com/spring-projects/spring-boot/pull/15609
	 */
	@Bean
	WebFluxTagsProvider webFluxTagsProvider() {
		return new DefaultWebFluxTagsProvider() {
			@Override
			public Iterable<Tag> httpRequestTags(ServerWebExchange exchange, Throwable exception) {
				return Tags.concat(super.httpRequestTags(exchange, exception), Tags.of(Tag.of("uri", exchange.getRequest().getPath().value())));
			}
		};
	}
	
}
