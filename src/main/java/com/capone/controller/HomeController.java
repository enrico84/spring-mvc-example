package com.capone.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capone.model.Player;
import com.capone.model.User;
import com.capone.service.IPlayerService;

import io.swagger.annotations.ApiOperation;

@Controller
public class HomeController {

    /**
     * Si noti che Spring MVC si occupa di mappare le variabili delle view.jsp alle
     * variabili di classe del modello, per cui si ha lo stesso nome di variabile in
     * entrambi i posti.
     * 
     * Deployare l'app sotto un server Tomcat, far partire questi e dal browser
     * digitare => http://localhost:8080/spring-mvc-example/
     */

    @Autowired
    @Resource(name = "serviceLista")
    private IPlayerService playerService;

    @GetMapping(value = "/")
    public String home(Locale locale, Model model) {
	System.out.println("Home Page Requested, locale = " + locale);
	Date date = new Date();
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

	String formattedDate = dateFormat.format(date);

	model.addAttribute("serverTime", formattedDate);

	return "home";
    }

    @GetMapping(value = "/login")
    public String loginPage(Locale locale, Model model) {
	return "login";
    }

    @PostMapping(value = "/user")
    public String user(@Validated User user, Model model) {
	System.out.println("User Page Requested: " + user);
	model.addAttribute("userName", user.getUserName());
	return "user";
    }

    /*
     * La parola "command" è un request-attribute riservato usato per visualizzare
     * il data-object(in questo caso -> new Player()) nel Form
     */
    @GetMapping("/addform")
    public String showForm(Model model) {
	model.addAttribute("command", new Player());
	return "addform";
    }

    @PostMapping("/addplayer")
    @ApiOperation(value = "Aggiunta di un player")
    public String addPlayer(@ModelAttribute("player") Player player) {
	playerService.addPlayer(player);
	return "redirect:/players"; // redireziona verso la Request "getAllPlayers"
    }

    @GetMapping("/players")
    @ApiOperation(value = "Lista dei players")
    public String getAllPlayers(Model m) {

	List<Player> players = playerService.players();
	m.addAttribute("players", players);
	return "players"; // Redireziona verso la jsp che contiene la lista dei players
    }

    /*
     * La parola "command" è un request-attribute riservato usato per visualizzare
     * il data-object(in questo caso -> new Playe()r) nel Form
     */
    @RequestMapping(value = "/editplayer/{id}")
    @ApiOperation(value = "Modifica player")
    public String editPlayer(@PathVariable Long id, Model model) {

	Player p = playerService.getPlayer(id);
	model.addAttribute("command", p);
	return "editform"; // Redireziona verso la jsp che contiene la pagina che contiene il players
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    @ApiOperation(value = "Salva modifiche al player")
    public String editSavePlayer(@ModelAttribute("player") Player player) {

	playerService.updatePlayer(player.getId(), player);
	return "redirect:/players"; // redireziona verso la Request "getAllPlayers"
    }

    @RequestMapping(value = "/deleteplayer/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Elimina player")
    public String deletePlayer(@PathVariable Long id) {
	playerService.deletePlayer(id);
	return "redirect:/players"; // Redireziona verso la jsp che contiene la lista dei players
    }
}
