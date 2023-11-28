package com.LupiSenai.prjGame.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LupiSenai.prjGame.entities.Game;
import com.LupiSenai.prjGame.servico.GameService;

@RestController
@RequestMapping("/jogos")
public class GameController {
	private final GameService gameService;
	
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/home")
	public String paginaInicial() {
		return "index"; // Nome do seu arquivo HTML (sem a extensão)
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Game> getJogo(@PathVariable Long id) {
		Game game = gameService.getJogoById(id);
		if (game != null) {
			return ResponseEntity.ok(game);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public Game createJogo(@RequestBody Game game) {
		return gameService.saveJogo(game);
	}

	// Utilizando o ResponseEntity e RequestEntity
	@GetMapping
	public ResponseEntity<List<Game>> getAllJogos(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Game> jogos = gameService.getAllJogos();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(jogos);
	}

	@PutMapping("/{id}")
	public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
		return gameService.updateGame(id, game);
	}

	@DeleteMapping("/{id}")
	public void deleteJogo(@PathVariable Long id) {
		gameService.deleteJogo(id);
	}

}
