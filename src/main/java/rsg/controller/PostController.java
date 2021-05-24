package rsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsg.dto.request.CreatePostDTO;
import rsg.dto.response.PostDTO;
import rsg.model.Post;
import rsg.service.PostService;
import rsg.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController extends BaseController{
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@PostMapping("")
	public ResponseEntity<PostDTO> create(HttpServletRequest req, @RequestBody @Valid CreatePostDTO postRequest) {
		Post post = postService.create(postRequest, userService.getByRequest(req));
		return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> get(HttpServletRequest req, @PathVariable Long id) {
		Post post = postService.getById(id);
		return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.CREATED);
	}
}
