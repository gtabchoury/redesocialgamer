package rsg.controller;

import rsg.dto.request.UserRegisterDTO;
import rsg.dto.response.UserDTO;
import rsg.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rsg.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController extends BaseController{
	@Autowired
	private UserService userService;

	@PostMapping("register")
	public ResponseEntity<UserDTO> register(@RequestBody UserRegisterDTO request){
		return new ResponseEntity<>(new UserDTO(userService.register(request.getModel())), HttpStatus.CREATED);
	}
}
