package rsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsg.dto.DefaultDTO;
import rsg.dto.request.UserEditDTO;
import rsg.dto.request.UserEditPasswordDTO;
import rsg.dto.request.UserRegisterDTO;
import rsg.dto.response.GameDTO;
import rsg.dto.response.GroupDTO;
import rsg.dto.response.PostDTO;
import rsg.dto.response.UserDTO;
import rsg.model.Group;
import rsg.model.Post;
import rsg.model.User;
import rsg.model.UserGame;
import rsg.service.GameService;
import rsg.service.GroupService;
import rsg.service.PostService;
import rsg.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
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

	@Autowired
	private GameService gameService;

	@GetMapping("/logged")
	public ResponseEntity<UserDTO> getLoggedUser(HttpServletRequest req) {
		return new ResponseEntity<>(new UserDTO(userService.getByRequest(req)), HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<Map<String, Object>> getPosts(HttpServletRequest req) {
		Page<Post> pagePosts = postService.getByUser(userService.getByRequest(req), getPageable(req));
		List<PostDTO> posts = pagePosts.getContent().stream().map(PostDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(getPaginatedResponse(pagePosts, posts), HttpStatus.OK);
	}

	@GetMapping("/groups")
	public ResponseEntity<Map<String, Object>> getGroups(HttpServletRequest req) {
		Page<Group> pageGroups = groupService.getByUser(userService.getByRequest(req), getPageable(req));
		List<GroupDTO> groups = pageGroups.getContent().stream().map(GroupDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(getPaginatedResponse(pageGroups, groups), HttpStatus.OK);
	}

	@GetMapping("/games")
	public ResponseEntity<Map<String, Object>> getMyGames(HttpServletRequest req){
		Page<UserGame> pageGames = gameService.getGamesByUser(userService.getByRequest(req), getPageable(req));
		List<GameDTO> games = pageGames.getContent().stream().map(UserGame::getGame).map(GameDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(getPaginatedResponse(pageGames, games), HttpStatus.OK);
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
