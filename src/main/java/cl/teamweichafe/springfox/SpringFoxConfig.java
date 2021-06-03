package cl.teamweichafe.springfox;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final Contact DEFAULT_CONTACT = new Contact("Italo Honorato",
			"https://www.linkedin.com/in/italohonorato/", 
			"italohonorato@gmail.com");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Team Weichafe API",
			"API to manage useres of the Team Weichafe", 
			"API TOS", 
			"Terms of service", DEFAULT_CONTACT,
			"Licence Apache 2.0", 
			"http://www.apache.org/licenses/LICENSE-2.0", 
			Collections.emptyList());

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("cl.teamweichafe.controllers"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(DEFAULT_API_INFO)
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET,
						newArrayList(
								new ResponseBuilder().code("200").description("Request processed successfully").build(),
								new ResponseBuilder().code("500").description("An Internal Server Error has ocurred").build(),
								new ResponseBuilder().code("400").description("Bad Request").build(),
								new ResponseBuilder().code("401").description("Request unauthorized, authentication required").build(),
								new ResponseBuilder().code("403").description("Forbidden").build()))
				.globalResponses(HttpMethod.POST,
						newArrayList(
								new ResponseBuilder().code("200").description("Request processed successfully").build(),
								new ResponseBuilder().code("201").description("Data saved successfully").build(),
								new ResponseBuilder().code("400").description("Bad Request").build(),
								new ResponseBuilder().code("401").description("Request unauthorized, authentication required").build(),
								new ResponseBuilder().code("403").description("Forbidden").build(),
								new ResponseBuilder().code("500").description("An Internal Server Error has ocurred").build()))
				.globalResponses(HttpMethod.PUT,
						newArrayList(
						new ResponseBuilder().code("200").description("Request processed successfully").build(),
						new ResponseBuilder().code("201").description("Data saved successfully").build(),
						new ResponseBuilder().code("400").description("Bad Request").build(),
						new ResponseBuilder().code("401").description("Request unauthorized, authentication required").build(),
						new ResponseBuilder().code("403").description("Forbidden").build(),
						new ResponseBuilder().code("500").description("An Internal Server Error has ocurred").build()))
				.globalResponses(HttpMethod.DELETE,
						newArrayList(
								new ResponseBuilder().code("200").description("Request processed successfully").build(),
								new ResponseBuilder().code("204").description("Data removed successfully").build(),
								new ResponseBuilder().code("400").description("Bad Request").build(),
								new ResponseBuilder().code("401").description("Request unauthorized, authentication required").build(),
								new ResponseBuilder().code("403").description("Forbidden").build(),
								new ResponseBuilder().code("500").description("An Internal Server Error has ocurred").build()));
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
}
