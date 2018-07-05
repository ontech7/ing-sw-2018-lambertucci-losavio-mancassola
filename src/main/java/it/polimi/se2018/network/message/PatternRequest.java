package it.polimi.se2018.network.message;

import it.polimi.se2018.model.Board;
import it.polimi.se2018.model.PrivateObjCard;

import java.util.List;

/**
 * This class encapsulates a board's pattern request
 */
public class PatternRequest extends Message {

    public final List<Board> boards;
    public final List<String> boardNames;
    public final PrivateObjCard privateObjCard;

    public PatternRequest(String username, List<Board> boards, List<String> boardNames, PrivateObjCard privateObjCard) {
        super(username, Content.PATTERN_REQUEST);
        this.boards = boards;
        this.boardNames = boardNames;
        this.privateObjCard = privateObjCard;
    }
}
