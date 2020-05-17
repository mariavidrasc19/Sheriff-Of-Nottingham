package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import jucatori.Status;
import main.Constants;

public class SmallAngel extends Angels {

    public SmallAngel() {
    }

    public SmallAngel(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public final String getName() {
        return "SmallAngel";
    }

    public final void interactWith(final Players player) {
        if (player.getStatus().equals(Status.Alive)) {
            if (player instanceof Knight) {
                player.setModifier(player.getModifier() + Constants.TEN);
                player.setHp(player.getHp() + Constants.TEN);
            } else if (player instanceof Pyromancer) {
                player.setModifier(player.getModifier() + Constants.FIFTEEN);
                player.setHp(player.getHp() + Constants.FIFTEEN);
            } else if (player instanceof Rogue) {
                player.setModifier(player.getModifier() + Constants.FIVE);
                player.setHp(player.getHp() + Constants.TWENTY);
            } else if (player instanceof Wizard) {
                player.setModifier(player.getModifier() + Constants.TEN);
                player.setHp(player.getHp() + Constants.TWENTY_FIVE);
            }
            player.verifyHP();
        }
    }
}
