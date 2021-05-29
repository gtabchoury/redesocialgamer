package rsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsg.dto.DefaultDTO;
import rsg.dto.request.GameRateRequestDTO;
import rsg.dto.response.GameDTO;
import rsg.dto.response.GameRateDTO;
import rsg.model.Game;
import rsg.model.GameRate;
import rsg.model.UserGame;
import rsg.service.GameService;
import rsg.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController extends BaseController{
	@Autowired
	private GameService gameService;

	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> getAllGames(HttpServletRequest req){
		Page<Game> pageGames = gameService.getAllGames(getPageable(req));
		List<GameDTO> games = pageGames.getContent().stream().map(GameDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(getPaginatedResponse(pageGames, games), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GameDTO> getById(@PathVariable Long id){
		Game game = gameService.getById(id);
		game.setRatingScore(gameService.getScore(game.getId()));
		return new ResponseEntity<>(new GameDTO(game), HttpStatus.OK);
	}

	@PatchMapping("/{id}/add")
	public ResponseEntity<DefaultDTO> addGame(HttpServletRequest req, @PathVariable Long id){
		gameService.addUserGame(userService.getByRequest(req), id);
		return new ResponseEntity<>(new DefaultDTO(true, "Game added successfully"), HttpStatus.OK);
	}

	@PatchMapping("/{id}/remove")
	public ResponseEntity<DefaultDTO> removeGame(HttpServletRequest req, @PathVariable Long id){
		gameService.removeUserGame(userService.getByRequest(req), id);
		return new ResponseEntity<>(new DefaultDTO(true, "Game removed successfully"), HttpStatus.OK);
	}

	@GetMapping("/{id}/rate")
	public ResponseEntity<GameRateDTO> getGameRate(@PathVariable Long id){
		Game game = gameService.getById(id);
		Double score = gameService.getScore(game.getId());
		List<GameRate> gameRates = gameService.getGameRates(game);
		return new ResponseEntity<>(new GameRateDTO(score, gameRates), HttpStatus.OK);
	}

	@PostMapping("/{id}/rate")
	public ResponseEntity<DefaultDTO> rateGame(HttpServletRequest req, @PathVariable Long id, @RequestBody GameRateRequestDTO request){
		gameService.rateGame(userService.getByRequest(req), id, request);
		return new ResponseEntity<>(new DefaultDTO(true, "Game successfully rated"), HttpStatus.OK);
	}
}
