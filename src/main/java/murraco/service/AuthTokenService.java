package murraco.service;

import murraco.dto.response.TokenDTO;
import murraco.exception.CustomException;
import murraco.model.AuthToken;
import murraco.model.User;
import murraco.repository.AuthTokenRepository;
import murraco.repository.UserRepository;
import murraco.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthTokenService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthTokenRepository authTokenRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	public TokenDTO accessToken(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			User user = userRepository.findByUsername(username);
			AuthToken existingToken = authTokenRepository.findFirstAuthTokenByUserOrderByExpirationDateDesc(user);
			if (existingToken!=null && jwtTokenProvider.isValidToken(existingToken.getAccessToken())){
				return new TokenDTO(existingToken);
			}else{
				TokenDTO token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles(), true);
				AuthToken authToken = new AuthToken();
				authToken.setAccessToken(token.getAccess_token());
				authToken.setRefreshToken(token.getRefresh_token());
				authToken.setExpirationDate(token.getExpirationDate());
				authToken.setUser(user);
				authTokenRepository.save(authToken);
				return token;
			}
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	public TokenDTO refreshToken(String refreshToken) {
		if (jwtTokenProvider.isValidToken(refreshToken)){
			User user = userRepository.findByUsername(jwtTokenProvider.getUsername(refreshToken));
			AuthToken existingToken = authTokenRepository.findAuthTokenByRefreshTokenAndUser(refreshToken, user);
			if (existingToken==null)
				throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);

			if (jwtTokenProvider.isValidToken(existingToken.getAccessToken())){
				return new TokenDTO(existingToken);
			}else{
				TokenDTO refreshedToken = jwtTokenProvider.createToken(user.getUsername(), user.getRoles(), false);
				refreshedToken.setRefresh_token(refreshToken);
				existingToken.setAccessToken(refreshedToken.getAccess_token());
				existingToken.setExpirationDate(refreshedToken.getExpirationDate());
				authTokenRepository.save(existingToken);
				return refreshedToken;
			}
		}else{
			throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
