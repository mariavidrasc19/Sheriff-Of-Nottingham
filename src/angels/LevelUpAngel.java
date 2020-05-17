package angels;

import jucatori.Wizard;
import jucatori.Pyromancer;
import jucatori.Rogue;
import jucatori.Knight;
import jucatori.Players;
import jucatori.Status;
import main.Constants;

public final class LevelUpAngel extends Angels {

    public LevelUpAngel() {
    }

    public LevelUpAngel(final int positionInTheRow, final int positionInTheCol) {
        super(positionInTheRow, positionInTheCol);
    }

    public String getName() {
        return "LevelUpAngel";
    }

    public void interactWith(final Players player) {
        if (player.getStatus().equals(Status.Alive)) {
            player.setXp(Constants.TWO_HUNDRED_FIFTY + player.getLevel() * Constants.FIFTY);
            if (player instanceof Knight) {
                player.setModifier(player.getModifier() + Constants.TEN);
                player.setHp(Constants.NINE * Constants.ONE_HUNDRED
                        + player.getLevel() * Constants.EIGHTY);
            } else if (player instanceof Pyromancer) {
                player.setModifier(player.getModifier() + Constants.TWENTY);
                player.setHp(Constants.FIVE * Constants.ONE_HUNDRED
                        + player.getLevel() * Constants.FIFTY);
            } else if (player instanceof Rogue) {
                player.setModifier(player.getModifier() + Constants.FIFTEEN);
                player.setHp(Constants.SIX * Constants.ONE_HUNDRED
                        + player.getLevel() * Constants.FORTY);
            } else if (player instanceof Wizard) {
                player.setModifier(player.getModifier() + Constants.TWENTY_FIVE);
                player.setHp(Constants.FOUR * Constants.ONE_HUNDRED
                        + player.getLevel() * Constants.THIRTY);
            }
        }
    }
}
