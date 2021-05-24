package rsg.controller;

import rsg.dto.request.UserLoginDTO;
import rsg.dto.response.TokenDTO;
import rsg.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController{
	@Autowired
	private AuthTokenService authTokenService;

	@PostMapping("access-token")
	public ResponseEntity<TokenDTO> accessToken(@RequestBody UserLoginDTO request) {
		return new ResponseEntity<>(authTokenService.accessToken(request.getUsername(), request.getPassword()), HttpStatus.OK);
	}

	@PostMapping("refresh-token")
	public ResponseEntity<TokenDTO> refreshToken(@RequestBody TokenDTO request) {
		return new ResponseEntity<>(authTokenService.refreshToken(request.getRefresh_token()), HttpStatus.OK);
	}

}
