package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import jucatori.Status;
import main.Constants;

public final class DarkAngel extends Angels {

    public DarkAngel() {
    }

    public DarkAngel(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public String getName() {
        return "DarkAngel";
    }

    public void interactWith(final Players player) {
        if (player.getStatus().equals(Status.Alive)) {
            if (player instanceof Knight) {
                player.setHp(player.getHp() - Constants.FORTY);
            } else if (player instanceof Pyromancer) {
                player.setHp(player.getHp() - Constants.THIRTY);
            } else if (player instanceof Rogue) {
                player.setHp(player.getHp() - Constants.TEN);
            } else if (player instanceof Wizard) {
                player.setHp(player.getHp() - Constants.TWENTY);
            }
        }
    }
}
