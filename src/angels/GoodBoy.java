package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import jucatori.Status;
import main.Constants;

public final class GoodBoy extends Angels {

    public GoodBoy() {
    }

    public GoodBoy(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public String getName() {
        return "GoodBoy";
    }

    public void interactWith(final Players player) {
        if (player.getStatus().equals(Status.Alive)) {
            if (player instanceof Knight) {
                player.setModifier(player.getModifier() + Constants.FORTY);
                player.setHp(player.getHp() + Constants.TWENTY);
            } else if (player instanceof Pyromancer) {
                player.setModifier(player.getModifier() + Constants.FIFTY);
                player.setHp(player.getHp() + Constants.THIRTY);
            } else if (player instanceof Rogue) {
                player.setModifier(player.getModifier() + Constants.FORTY);
                player.setHp(player.getHp() + Constants.FORTY);
            } else if (player instanceof Wizard) {
                player.setModifier(player.getModifier() + Constants.THIRTY);
                player.setHp(player.getHp() + Constants.FIFTY);
            }
            player.verifyHP();
        }
    }
}
