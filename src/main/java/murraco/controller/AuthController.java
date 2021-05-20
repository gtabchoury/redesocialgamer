package murraco.controller;

import murraco.dto.request.UserLoginDTO;
import murraco.dto.request.UserRegisterDTO;
import murraco.dto.response.TokenDTO;
import murraco.dto.response.UserDTO;
import murraco.service.AuthTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import murraco.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthTokenService authTokenService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("access-token")
	public ResponseEntity<TokenDTO> accessToken(@RequestBody UserLoginDTO request) {
		return new ResponseEntity<>(authTokenService.accessToken(request.getUsername(), request.getPassword()), HttpStatus.OK);
	}

	@PostMapping("refresh-token")
	public ResponseEntity<TokenDTO> refreshToken(@RequestBody TokenDTO request) {
		return new ResponseEntity<>(authTokenService.refreshToken(request.getRefresh_token()), HttpStatus.OK);
	}

}
