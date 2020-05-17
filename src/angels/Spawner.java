package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import jucatori.Status;
import main.Constants;

public class Spawner extends Angels {

    public Spawner() {
    }

    public Spawner(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public final String getName() {
        return "Spawner";
    }

    public final void interactWith(final Players player) {
        if (player.getStatus().equals(Status.Dead)) {
            player.setStatus(Status.Alive);
            if (player instanceof Knight) {
                player.setHp(Constants.TWO * Constants.ONE_HUNDRED);
            } else if (player instanceof Pyromancer) {
                player.setHp(Constants.ONE_HUNDRED + Constants.FIFTY);
            } else if (player instanceof Rogue) {
                player.setHp(Constants.ONE_HUNDRED + Constants.EIGHTY);
            } else if (player instanceof Wizard) {
                player.setHp(Constants.ONE_HUNDRED + Constants.TWENTY);
            }
        }
    }
}
