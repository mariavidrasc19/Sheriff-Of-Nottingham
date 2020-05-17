package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import main.Constants;

public final class LifeGiver extends Angels {
    public LifeGiver() {
    }

    public LifeGiver(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public String getName() {
        return "LifeGiver";
    }

    public void interactWith(final Players player) {
        if (player instanceof Knight) {
            player.setHp(player.getHp() + Constants.ONE_HUNDRED);
        } else if (player instanceof Pyromancer) {
            player.setHp(player.getHp() + Constants.EIGHTY);
        } else if (player instanceof Rogue) {
            player.setHp(player.getHp() + Constants.NINE * Constants.TEN);
        } else if (player instanceof Wizard) {
            player.setHp(player.getHp() + Constants.ONE_HUNDRED + Constants.TWENTY);
        }
        player.verifyHP();
    }
}
