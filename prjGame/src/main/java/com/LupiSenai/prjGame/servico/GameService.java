package com.LupiSenai.prjGame.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.LupiSenai.prjGame.Repository.GameRepository;
import com.LupiSenai.prjGame.entities.Game;

@Service
public class GameService {
	private final GameRepository gameRepository;

	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	// preparando as buscas por id
	public Game getJogoById(Long id) {
		return gameRepository.findById(id).orElse(null);
	}

	// preparando a busca geral
	public List<Game> getAllJogos() {
		return gameRepository.findAll();
	}

	// salvando o Jogo
	public Game saveJogo(Game game) {
		return gameRepository.save(game);
	}

	// excluindo o Jogo
	public void deleteJogo(Long id) {
		gameRepository.deleteById(id);
	}

	// fazendo o update do jogo com o optional
	public Game updateGame(Long id, Game novoGame) {
		Optional<Game> jogoOptional = gameRepository.findById(id);
		if (jogoOptional.isPresent()) {
			Game jogoExistente = jogoOptional.get();
			jogoExistente.setName(novoGame.getName());
			jogoExistente.setPlataform(novoGame.getPlataform());
			return gameRepository.save(jogoExistente);
		} else {
			return null;
		}
	}
}
