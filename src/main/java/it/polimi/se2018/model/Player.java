package it.polimi.se2018.model;

import java.security.InvalidParameterException;

/**
 * This class describes the Player
 * @version 1.1
 */
public class Player {
    private Board board;
    private int token;
    private String name;
    private PrivateObjCard privateObjCard;
    private ToolCard activatedToolcard;
    private PlayerState state;

    // Player constructor
    public Player(String name) {
        this.name = name;
    }

    /**
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return the player's board
     */
    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        if(board == null){
            throw new NullPointerException("`board` must be not null");
        }
        this.board = board;
        this.token = board.getBoardDifficulty();
    }

    /**
     * @return the number of tokens of the player
     */
    public int getToken() {
        return this.token;
    }

    /**
     * Setter of the tokens of the player
     * @param token number of tokens
     */
    public void setToken(int token) {
        if(token < 0){
            throw new InvalidParameterException("`token` must be grater than 0");
        }
        this.token = token;
    }

    /**
     * @return the private objective card assigned to the player
     */
    public PrivateObjCard getPrivateObjCard() {
        return privateObjCard;
    }

    /**
     * Setter of the player's private objective card
     * @param privateObjCard to assign to the player
     */
    public void setPrivateObjCard(PrivateObjCard privateObjCard) {
        this.privateObjCard = privateObjCard;
    }

    /**
     * @return the toolcard activated by the player
     */
    public ToolCard getActivatedToolcard() {
        return this.activatedToolcard;
    }

    /**
     * Setter of the activated toolcard
     * @param activatedToolcard the toolcard activated by the player
     */
    public void setActivatedToolcard(ToolCard activatedToolcard) {
        this.activatedToolcard = activatedToolcard;
    }

    public void setState(PlayerState state){
        this.state = state;
    }
    public PlayerState getState() { return state; }
}
