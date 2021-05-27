package rsg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rsg.dto.request.GameRateRequestDTO;
import rsg.model.Game;
import rsg.model.GameRate;
import rsg.model.User;
import rsg.model.UserGame;
import rsg.repository.GameRateRepository;
import rsg.repository.GameRepository;
import rsg.repository.UserGameRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private UserGameRepository userGameRepository;

	@Autowired
	private GameRateRepository gameRateRepository;

	public Game getById(Long id){
		return gameRepository.getById(id);
	}

	public List<Game> getGamesByUser(User user){
		return userGameRepository.findByUser(user)
				.stream().map(UserGame::getGame)
				.filter(game -> game.getActive())
				.collect(Collectors.toList());
	}

	public List<Game> getAllGames(){
		return gameRepository.findByActiveTrue();
	}

	public void addUserGame(User user, Long idGame){
		Game game = getById(idGame);
		UserGame userGame = new UserGame();
		userGame.setGame(game);
		userGame.setUser(user);
		userGameRepository.save(userGame);
	}

	public void removeUserGame(User user, Long idGame){
		userGameRepository.delete(userGameRepository.getByUserAndGame(user, getById(idGame)));
	}

	public void rateGame(User user, Long idGame, GameRateRequestDTO gameRateRequestDTO){
		Game game = getById(idGame);
		GameRate gameRate = gameRateRepository.findByUserAndGame(user, game);
		if (gameRate==null){
			gameRate = new GameRate();
			gameRate.setUser(user);
			gameRate.setGame(game);
		}
		gameRate.setRate(gameRateRequestDTO.getRate());
		gameRate.setText(gameRateRequestDTO.getText());
		gameRateRepository.save(gameRate);
	}

	public Double getRate(Game game){
		return getScore(game.getId());
	}

	public Double getScore(Long idGame){
		return gameRateRepository.getScore(idGame);
	}

	public List<GameRate> getGameRates(Game game){
		return gameRateRepository.findByGame(game);
	}
}
