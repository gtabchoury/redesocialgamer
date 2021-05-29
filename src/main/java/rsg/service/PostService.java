package rsg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import rsg.dto.request.CreateCommentDTO;
import rsg.dto.request.CreatePostDTO;
import rsg.exception.CustomException;
import rsg.model.Comment;
import rsg.model.Like;
import rsg.model.Post;
import rsg.model.User;
import rsg.repository.CommentRepository;
import rsg.repository.LikeRepository;
import rsg.repository.PostRepository;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private LikeRepository likeRepository;

	public Post create(CreatePostDTO postRequest, User user){
		Post post = postRequest.getModel();
		post.setCreationDate(new Date());
		post.setUser(user);
		postRepository.save(post);
		return post;
	}

	public Post addComment(Post post, CreateCommentDTO commentDTO, User user){
		Comment comment = new Comment();
		comment.setDate(new Date());
		comment.setPost(post);
		comment.setUser(user);
		comment.setText(commentDTO.getText());
		commentRepository.save(comment);
		return postRepository.getById(post.getId());
	}

	public Post removeComment(Post post, Long idComment, User user){
		Comment comment = commentRepository.getById(idComment);
		if (!comment.getPost().equals(post))
			throw new CustomException("Invalid post", HttpStatus.BAD_REQUEST);

		if (!comment.getUser().equals(user))
			throw new CustomException("User not allowed to remove this comment", HttpStatus.BAD_REQUEST);

		commentRepository.delete(comment);
		return postRepository.getById(post.getId());
	}

	public List<Post> getByUser(User user){
		return postRepository.findAllByUser(user);
	}

	public Page<Post> getByUser(User user, Pageable pageable){
		return postRepository.findAllByUser(user, pageable);
	}

	public Post getById(Long id){
		return postRepository.getById(id);
	}

	public Post like(Post post, User user){
		Like like = likeRepository.findByUserAndPost(user, post);
		if (like==null){
			like = new Like();
			like.setDate(new Date());
			like.setPost(post);
			like.setUser(user);
			likeRepository.save(like);
			post = postRepository.getById(post.getId());
		}
		return post;
	}

	public Post removeLike(Post post, User user){
		Like like = likeRepository.findByUserAndPost(user, post);
		if (like!=null){
			likeRepository.delete(like);
			post = postRepository.getById(post.getId());
		}
		return post;
	}
}
