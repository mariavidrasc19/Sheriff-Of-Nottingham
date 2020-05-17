package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import jucatori.Status;
import main.Constants;

public class XPAngel extends Angels {

    public XPAngel() {
    }

    public XPAngel(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public final String getName() {
        return "XPAngel";
    }

    public final void interactWith(final Players player) {
        if (player.getStatus().equals(Status.Alive)) {
            if (player instanceof Knight) {
                player.setXp(player.getXp() + Constants.FORTY + Constants.FIVE);
            } else if (player instanceof Pyromancer) {
                player.setXp(player.getXp() + Constants.FIFTY);
            } else if (player instanceof Rogue) {
                player.setXp(player.getXp() + Constants.FORTY);
            } else if (player instanceof Wizard) {
                player.setXp(player.getXp() + Constants.SIX * Constants.TEN);
            }
        }
    }
}
