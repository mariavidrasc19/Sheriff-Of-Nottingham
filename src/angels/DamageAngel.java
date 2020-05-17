package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import jucatori.Status;
import main.Constants;

public final class DamageAngel extends Angels {

    public DamageAngel() {
    }

    public DamageAngel(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public String getName() {
        return "DamageAngel";
    }

    public void interactWith(final Players player) {
        if (player.getStatus().equals(Status.Alive)) {
            if (player instanceof Knight) {
                player.setModifier(player.getModifier() + Constants.FIFTEEN);
            } else if (player instanceof Pyromancer) {
                player.setModifier(player.getModifier() + Constants.TWENTY);
            } else if (player instanceof Rogue) {
                player.setModifier(player.getModifier() + Constants.THIRTY);
            } else if (player instanceof Wizard) {
                player.setModifier(player.getModifier() + Constants.FORTY);
            }
        }
    }
}
