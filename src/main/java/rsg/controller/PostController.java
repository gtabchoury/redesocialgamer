package rsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsg.dto.request.CreateCommentDTO;
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
		return new ResponseEntity<>(new PostDTO(post), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> get(HttpServletRequest req, @PathVariable Long id) {
		Post post = postService.getById(id);
		return new ResponseEntity<>(new PostDTO(post), HttpStatus.CREATED);
	}

	@PostMapping("/{id}/like")
	public ResponseEntity<PostDTO> like(HttpServletRequest req, @PathVariable Long id) {
		Post post = postService.getById(id);
		post = postService.like(post, userService.getByRequest(req));
		return new ResponseEntity<>(new PostDTO(post), HttpStatus.CREATED);
	}

	@PostMapping("/{id}/like/remove")
	public ResponseEntity<PostDTO> removeLike(HttpServletRequest req, @PathVariable Long id) {
		Post post = postService.getById(id);
		post = postService.removeLike(post, userService.getByRequest(req));
		return new ResponseEntity<>(new PostDTO(post), HttpStatus.CREATED);
	}

	@PostMapping("/{id}/comment")
	public ResponseEntity<PostDTO> addComment(HttpServletRequest req, @PathVariable Long id, @RequestBody @Valid CreateCommentDTO request) {
		Post post = postService.getById(id);
		post = postService.addComment(post, request, userService.getByRequest(req));
		return new ResponseEntity<>(new PostDTO(post), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}/comment/{idComment}")
	public ResponseEntity<PostDTO> removeComment(HttpServletRequest req, @PathVariable Long id, @PathVariable Long idComment) {
		Post post = postService.getById(id);
		post = postService.removeComment(post, idComment, userService.getByRequest(req));
		return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
	}
}
