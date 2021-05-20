package murraco.controller;

import murraco.dto.request.UserRegisterDTO;
import murraco.dto.response.UserDTO;
import murraco.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import murraco.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("register")
	public ResponseEntity<UserDTO> register(@RequestBody UserRegisterDTO request){
		User user = modelMapper.map(request, User.class);
		UserDTO userDTO = modelMapper.map(userService.register(user), UserDTO.class);
		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
	}
}
