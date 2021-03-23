package com.capone.service;

import java.util.List;

import com.capone.model.Player;

public interface IPlayerService {

    List<Player> players();

    Player getPlayer(Long id);

    void addPlayer(Player player);

    void updatePlayer(Long id, Player player);

    void deletePlayer(Long id);

}
