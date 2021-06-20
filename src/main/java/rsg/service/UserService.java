package rsg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rsg.dto.request.UserEditPasswordDTO;
import rsg.exception.CustomException;
import rsg.model.User;
import rsg.repository.UserRepository;
import rsg.security.JwtTokenProvider;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public User getById(Long id){
		return userRepository.getById(id);
	}

	public User register(User user) {
		if (!userRepository.existsByUsername(user.getUsername())) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return user;
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public User getByRequest(HttpServletRequest req) {
		return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	public User edit(User user, User newUser){
		user.setName(newUser.getName());
		user.setEmail(newUser.getEmail());
		userRepository.save(user);
		return user;
	}

	public User editPassword(User user, UserEditPasswordDTO passwordDTO){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), passwordDTO.getCurrentPassword()));
			user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
			userRepository.save(user);
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid current password.", HttpStatus.BAD_REQUEST);
		}
		return user;
	}

	public User save(User user){
		return userRepository.save(user);
	}
}
