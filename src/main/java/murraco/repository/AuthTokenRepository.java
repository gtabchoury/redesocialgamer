package murraco.repository;

import murraco.model.AuthToken;
import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Integer> {
	AuthToken findFirstAuthTokenByUserOrderByExpirationDateDesc(User user);
	AuthToken findAuthTokenByRefreshTokenAndUser(String refreshToken, User user);
}
