package angels;

import jucatori.Players;

public class Angels {
    private int positionInTheRow;
    private int positionInTheCol;

    public Angels() {}

    //constructor inger
    public Angels(final int positionInTheRow, final int positionInTheCol) {
        this.positionInTheCol = positionInTheCol;
        this.positionInTheRow = positionInTheRow;
    }

    public final int getPositionInTheRow() {
        return positionInTheRow;
    }

    public final void setPositionInTheRow(final int positionInTheRow) {
        this.positionInTheRow = positionInTheRow;
    }

    public final int getPositionInTheCol() {
        return positionInTheCol;
    }

    public final void setPositionInTheCol(final int positionInTheCol) {
        this.positionInTheCol = positionInTheCol;
    }

    /**
     */
    // fiecare inger va avea un nume care va fi printat
    // cel default este null din nevoi personale
    public String getName() {
        return null;
    }

    // ingerul actioneaza asupra eroului
    public void interactWith(final Players player) {
    }
}
