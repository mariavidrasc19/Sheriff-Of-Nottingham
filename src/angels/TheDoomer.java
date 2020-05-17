package angels;

import jucatori.Players;
import main.Constants;

public class TheDoomer extends Angels {

    public TheDoomer() {
    }

    public TheDoomer(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public final String getName() {
        return "TheDoomer";
    }

    public final void interactWith(final Players player) {
        player.setHp(Constants.ZERO);
    }
}
