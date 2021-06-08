package cl.teamweichafe.controllers;

import javax.annotation.Resource;

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/token/revoke")
public class TokenController {
	
	@Resource(name="tokenServices")
	ConsumerTokenServices tokenServices;
		
	@ApiOperation(value = "Endpoint to revoke tokens")
	@PostMapping("/{tokenId:.*}")
	@ResponseBody
	public boolean revokeToken(@PathVariable String tokenId) {
	    
	    return tokenServices.revokeToken(tokenId);
	}
	
}
