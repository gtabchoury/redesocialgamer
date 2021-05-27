package rsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsg.dto.DefaultDTO;
import rsg.dto.request.UserEditDTO;
import rsg.dto.request.UserEditPasswordDTO;
import rsg.dto.request.UserRegisterDTO;
import rsg.dto.response.GroupDTO;
import rsg.dto.response.PostDTO;
import rsg.dto.response.UserDTO;
import rsg.model.Group;
import rsg.model.Post;
import rsg.model.User;
import rsg.service.GroupService;
import rsg.service.PostService;
import rsg.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Autowired
	private GroupService groupService;

	@GetMapping("/logged")
	public ResponseEntity<UserDTO> getLoggedUser(HttpServletRequest req) {
		return new ResponseEntity<>(new UserDTO(userService.getByRequest(req)), HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getPosts(HttpServletRequest req) {
		List<Post> posts = postService.getByUser(userService.getByRequest(req));
		List<PostDTO> postDTOS = posts.stream().map(PostDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(postDTOS, HttpStatus.CREATED);
	}

	@GetMapping("/groups")
	public ResponseEntity<List<GroupDTO>> getGroups(HttpServletRequest req) {
		List<Group> groups = groupService.getByUser(userService.getByRequest(req));
		List<GroupDTO> groupDTOS = groups.stream().map(GroupDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(groupDTOS, HttpStatus.CREATED);
	}

	@PutMapping("")
	public ResponseEntity<UserDTO> edit(@RequestBody @Valid UserEditDTO request, HttpServletRequest req) {
		User user = userService.getByRequest(req);
		user = userService.edit(user, request.getModel());
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	@PutMapping("/password")
	public ResponseEntity<DefaultDTO> editPassword(@RequestBody @Valid UserEditPasswordDTO request, HttpServletRequest req) {
		User user = userService.getByRequest(req);
		userService.editPassword(user, request);
		DefaultDTO defaultDTO = new DefaultDTO(true, "User password changed successfully.");
		return new ResponseEntity<>(defaultDTO, HttpStatus.OK);
	}
}
