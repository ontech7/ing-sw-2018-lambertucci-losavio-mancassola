package it.polimi.se2018.controller.services;

import it.polimi.se2018.model.Match;

public class MatchService {

    private final Match match;

    public MatchService(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }
}
