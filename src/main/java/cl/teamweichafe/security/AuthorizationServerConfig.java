package cl.teamweichafe.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Value("${authorization.server.client.id}")
	private String CLIENT_ID;
	
	@Value("${authorization.server.client.secret}")
	private String CLIENT_SECRET;
	
	@Value("${authorization.server.client.scope-read}")
	private String SCOPE_READ;
	
	@Value("${authorization.server.client.scope-write}")
	private String SCOPE_WRITE;
	
	@Value("${authorization.server.grant-type-pwd}")
	private String GRANT_TYPE_PWD;
	
	@Value("${authorization.server.grant-type-refresh-token}")
	private String GRANT_TYPE_REFRESH_TOKEN;
	
	@Value("${authorization.server.access-token-validity-seconds}")
	private int ACCESS_TOKEN_VALIDITY_SECONDS;
	
	@Value("${authorization.server.refresh-token-validity-seconds}")
	private int REFRESH_TOKEN_VALIDITY_SECONDS;
	
	@Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }
	
	@Bean
    public TokenStore tokenStore() {
		//In memory
//        return new JwtTokenStore(accesTokenConverter());
        //In DDBB
		return new JdbcTokenStore(this.dataSource);
    }
	
	/**
	 * 
	 * Bean encargado de de firmar, codificar y validar token
	 */
	@Bean
	public JwtAccessTokenConverter accesTokenConverter() {
		
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(JwtConfig.PRIVATE_KEY);
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.PUBLIC_KEY);
		
		return jwtAccessTokenConverter;
	}

	/**
	 * Configuración de acceso a los endpoints del Authorization Server
	 *  /oauth/token_key -> endpoint encargado de generar token de acceso
	 *  /oauth/check_token -> endpoint encargado de validar token
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		security
		.tokenKeyAccess("permitAll()") //Endpoint que nos permite obtener token de acceso 
		.checkTokenAccess("isAuthenticated()"); //Enpoint que nos permite validar token de acceso
	}

	/**
	 * Método que nos permite configurar app clientes que se comunicarán con el Back
	 * End
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients
		.inMemory()
		.withClient(CLIENT_ID)
		.secret(passwordEncoder.encode(CLIENT_SECRET))
		.scopes(SCOPE_READ, SCOPE_WRITE)
		.authorizedGrantTypes(GRANT_TYPE_PWD, GRANT_TYPE_REFRESH_TOKEN)
		.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
		.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}

	/**
	 * Método encargado de registrar los Beans encargados del proceso de
	 * autenticación (authenticationManager) y del proceso de generación y
	 * validación de Tokens
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints
		.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accesTokenConverter());
	}

}
