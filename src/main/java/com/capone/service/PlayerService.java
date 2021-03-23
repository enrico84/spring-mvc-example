package com.capone.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capone.model.Player;

import lombok.extern.slf4j.Slf4j;

@Service(value = "serviceLista")
@Slf4j
public class PlayerService implements IPlayerService {

    List<Player> players = new ArrayList<>(Arrays.asList(new Player(new Long(1), "Gianluigi", "Buffon", "Juventus"),
	    new Player(new Long(2), "Gigio", "Donnarumma", "Milan"),
	    new Player(new Long(3), "Lorenzo", "Insigne", "Napoli")));

    public List<Player> players() {
	return players;
    }

    public Player getPlayer(Long id) {
	log.info("Aggiungiamo un player con id {}", id);
	Player player = null;
	for (int i = 0; i < players.size(); i++) {
	    Player p = players.get(i);
	    if (p.getId().intValue() == id.intValue()) {
		player = p;
		break;
	    }
	}
	return player;
    }

    public void addPlayer(Player player) {
	log.info("Aggiungiamo il calciatore {}", player);
	Long id = null;
	if (!players.isEmpty()) {
	    Player lastPlayer = players.get(players.size() - 1);
	    id = new Long(lastPlayer.getId().intValue() + 1);
	} else {
	    id = new Long(1);
	}
	player.setId(id);
	players.add(player);

    }

    public void updatePlayer(Long id, Player player) {
	log.info("Update del calciatore con id {}", id);
	for (int i = 0; i < players.size(); i++) {
	    Player pl = players.get(i);
	    if (pl.getId().intValue() == id.intValue()) {
		players.set(i, player);
	    }
	}
    }

    public void deletePlayer(Long id) {
	log.info("Eliminiamo il calciatore con id {}", id);
	for (int i = 0; i < players.size(); i++) {
	    Player pl = players.get(i);
	    if (pl.getId().intValue() == id.intValue()) {
		players.remove(pl);
		break;
	    }
	}
    }

}
