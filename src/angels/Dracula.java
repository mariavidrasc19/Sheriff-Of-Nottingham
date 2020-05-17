package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import main.Constants;

public final class Dracula extends Angels {

    public Dracula() {
    }

    public Dracula(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public String getName() {
        return "Dracula";
    }

    public void interactWith(final Players player) {
        if (player instanceof Knight) {
            player.setModifier(player.getModifier() - Constants.TWENTY);
            player.setHp(player.getHp() - Constants.SIX * Constants.TEN);
        } else if (player instanceof Pyromancer) {
            player.setModifier(player.getModifier() - Constants.THIRTY);
            player.setHp(player.getHp() - Constants.FORTY);
        } else if (player instanceof Rogue) {
            player.setModifier(player.getModifier() - Constants.TEN);
            player.setHp(player.getHp() - Constants.TWENTY - Constants.FIFTEEN);
        } else if (player instanceof Wizard) {
            player.setModifier(player.getModifier() - Constants.FORTY);
            player.setHp(player.getHp() - Constants.TWENTY);
        }
    }
}
