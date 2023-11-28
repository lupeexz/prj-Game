package com.LupiSenai.prjGame.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LupiSenai.prjGame.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}