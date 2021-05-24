package rsg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsg.dto.request.CreatePostDTO;
import rsg.model.Post;
import rsg.model.User;
import rsg.repository.PostRepository;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Post create(CreatePostDTO postRequest, User user){
		Post post = postRequest.getModel();
		post.setCreationDate(new Date());
		post.setUser(user);
		postRepository.save(post);
		return post;
	}

	public List<Post> getByUser(User user){
		return postRepository.getAllByUser(user);
	}

	public Post getById(Long id){
		return postRepository.getById(id);
	}
}
