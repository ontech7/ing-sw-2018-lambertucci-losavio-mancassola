package it.polimi.se2018.model;

/**
 * This class represents the object Cell of the board
 * @version 1.0
 */
public class Cell {
    private Restriction restriction;
    private Die die;

    public Cell(Restriction restriction) {
        this.die = null;
        this.restriction = restriction;
    }

    /**
     * @param die the Die to check
     * @return the related error
     */
    public PlacementError isDieAllowed(Die die) {

        PlacementError err = new PlacementError();

        // Check if the placed Die violate the restriction
        err = PlacementError.union(err, restriction.isDieAllowed(die));

        // Check if the Cell is occupied yet
        if (!isEmpty())
            err = PlacementError.union(err, restriction.isDieAllowed(die));

        return err;
    }

    /**
     * @return the Die in the Cell
     */
    public Die getDie() {
        return this.die;
    }

    /**
     * This method set a Die in the Cell
     * @param die the Die to set
     */
    public void setDie(Die die) {
        this.die = die;
    }

    public Restriction getRestriction() { return this.restriction; }

    /**
     * This method check if a Cell is empty or not
     * @return True or False
     */
    public boolean isEmpty() {
        return die == null;
    }
}
