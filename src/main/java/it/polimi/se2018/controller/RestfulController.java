package it.polimi.se2018.controller;

import it.polimi.se2018.controller.services.ErrorService;
import it.polimi.se2018.controller.services.MatchService;
import it.polimi.se2018.model.*;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Queue;

@RestController
public class RestfulController implements ErrorController {
    private List<Player> players;
    private Player player1, player2;
    private Board board;
    private ToolCard[] toolCards;
    private PublicObjCard[] publicObjCards;
    private Match match;
    private Queue<Player> playerQueue;
    private Observer observer;

    /* Error mapping */

    @RequestMapping("/error")
    @ResponseBody
    public ErrorService handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String message = "Resource not found.";
        if(exception != null) {
            message = exception.getMessage();
        }
        return new ErrorService(statusCode, message);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    /* Match */

    @RequestMapping(value = "/rest/api/match", method = RequestMethod.GET)
    public MatchService doGetMatch() {
        Restriction[][] pattern1 = new Restriction[4][5];

        board = new Board(pattern1, 3);

        player1 = new Player("Pino");
        player1.setBoard(board);
        player2 = new Player("Gino");

        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        toolCards = new ToolCard[3];
        toolCards[0] = new ToolCard(0);
        toolCards[1] = new ToolCard(1);
        toolCards[2] = new ToolCard(2);

        publicObjCards = new PublicObjCard[3];
        publicObjCards[0] = new PublicObjCard(0);
        publicObjCards[1] = new PublicObjCard(1);
        publicObjCards[2] = new PublicObjCard(2);

        observer = (obs, obj) -> { /*do nothing*/ };

        match = new Match(players, toolCards, publicObjCards, observer);

        return new MatchService(match);
    }

    /* Match -> Players */

    @RequestMapping(value = "/rest/api/match/players", method = RequestMethod.GET)
    public List<Player> doGetPlayers() {

        return doGetMatch().getMatch().getPlayers();
    }

    @RequestMapping(value = "/rest/api/match/players/{id}", method = RequestMethod.GET)
    public Player doGetPlayerById(@PathVariable int id) {

        return doGetMatch().getMatch().getPlayers().get(id);
    }

    /* Match -> PlayerQueue */

    @RequestMapping(value = "/rest/api/match/playerqueue", method = RequestMethod.GET)
    public Queue<Player> doGetPlayerQueue() {

        return doGetMatch().getMatch().getPlayerQueue();
    }

    /* Match -> DraftPool */

    @RequestMapping(value = "/rest/api/match/draftpool", method = RequestMethod.GET)
    public DiceContainer doGetDraftPool() {

        return doGetMatch().getMatch().getDraftPool();
    }

    /* Match -> RoundTracker */

    @RequestMapping(value = "/rest/api/match/roundtracker", method = RequestMethod.GET)
    public DiceContainer doGetRoundTracker() {

        return doGetMatch().getMatch().getRoundTracker();
    }

    /* Match -> ToolCards */

    @RequestMapping(value = "/rest/api/match/toolcards", method = RequestMethod.GET)
    public ToolCard[] doGetToolCards() {

        return doGetMatch().getMatch().getToolCards();
    }

    @RequestMapping(value = "/rest/api/match/toolcards/{id}", method = RequestMethod.GET)
    public ToolCard doGetToolCards(@PathVariable int id) {

        return doGetMatch().getMatch().getToolCards()[id];
    }

    @RequestMapping(value = "/rest/api/match/toolcards/{id}/id", method = RequestMethod.GET)
    public Integer doGetToolCardId(@PathVariable int id) {

        return doGetMatch().getMatch().getToolCards()[id].getId();
    }

    @RequestMapping(value = "/rest/api/match/toolcards/{id}/cost", method = RequestMethod.GET)
    public Integer doGetToolCardCost(@PathVariable int id) {

        return doGetMatch().getMatch().getToolCards()[id].getCost();
    }

    @RequestMapping(value = "/rest/api/match/toolcards/{id}/title", method = RequestMethod.GET)
    public String doGetToolCardTitle(@PathVariable int id) {

        return doGetMatch().getMatch().getToolCards()[id].getTitle();
    }

    @RequestMapping(value = "/rest/api/match/toolcards/{id}/description", method = RequestMethod.GET)
    public String doGetToolCardDescription(@PathVariable int id) {

        return doGetMatch().getMatch().getToolCards()[id].getDescription();
    }

    @RequestMapping(value = "/rest/api/match/toolcards/{id}/color", method = RequestMethod.GET)
    public Color doGetToolCardColor(@PathVariable int id) {

        return doGetMatch().getMatch().getToolCards()[id].getColor();
    }

    /* Match -> PublicObjCards */

    @RequestMapping(value = "/rest/api/match/publicobjcards", method = RequestMethod.GET)
    public PublicObjCard[] doGetPublicObjCards() {

        return doGetMatch().getMatch().getPublicObjCards();
    }

    @RequestMapping(value = "/rest/api/match/publicobjcards/{id}", method = RequestMethod.GET)
    public PublicObjCard doGetPublicObjCardById(@PathVariable int id) {

        return doGetMatch().getMatch().getPublicObjCards()[id];
    }

    @RequestMapping(value = "/rest/api/match/publicobjcards/{id}/description", method = RequestMethod.GET)
    public String doGetPublicObjCardDescription(@PathVariable int id) {

        return doGetMatch().getMatch().getPublicObjCards()[id].getDescription();
    }

    @RequestMapping(value = "/rest/api/match/publicobjcards/{id}/title", method = RequestMethod.GET)
    public String doGetPublicObjCardTitle(@PathVariable int id) {

        return doGetMatch().getMatch().getPublicObjCards()[id].getTitle();
    }

    /* Match -> Finished */

    @RequestMapping(value = "/rest/api/match/finished", method = RequestMethod.GET)
    public Boolean doGetFinishedMatch() {

        return doGetMatch().getMatch().isFinished();
    }
}
