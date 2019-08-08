package it.polimi.se2018.controller.services;

import it.polimi.se2018.model.Match;

import java.util.Map;

public class MatchesService {

    private final Map<String, Match> matches;

    public MatchesService(Map<String, Match> matches) {
        this.matches = matches;
    }

    public Map<String, Match> getMatches() {
        return matches;
    }
}
