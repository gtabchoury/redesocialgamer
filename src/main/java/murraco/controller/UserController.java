package murraco.controller;

import murraco.dto.response.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import murraco.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/logged")
	public ResponseEntity<UserDTO> refresh(HttpServletRequest req) {
		return new ResponseEntity<>(modelMapper.map(userService.getByRequest(req), UserDTO.class), HttpStatus.OK);
	}
}
