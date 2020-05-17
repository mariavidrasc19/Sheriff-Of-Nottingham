package jucatori;

import main.Constants;

public final class Knight extends Players {

    public Knight() {
    }

    public Knight(final int row, final int col, final Ground ground, final int id) {
        super(row, col, ground, id);
        this.setHp(Constants.NINE * Constants.ONE_HUNDRED);
        this.setMaxHP(Constants.NINE * Constants.ONE_HUNDRED);
    }

    public String getName() {
        return "Knight";
    }

    // jucatorul isi stabileste strategia
    public void makeStrategy() {
        if (((this.getMaxHP() / Constants.THREE) < this.getHp()) && (this.getHp()
                < (this.getMaxHP() / Constants.TWO))) {
            this.setStrategy(Constants.ONE);
        } else if (this.getHp() < (this.getMaxHP() / Constants.THREE)) {
            this.setStrategy(Constants.TWO);
        }
    }

    // verifica daca Knight nu a primit mai mult HP decat poate avea
    public void verifyHP() {
        int maxHP = Constants.NINE * Constants.ONE_HUNDRED + this.getLevel() * Constants.EIGHTY;
        if (this.getHp() > maxHP) {
            this.setHp(maxHP);
        }
    }

    //recalculeaza max HP
    public void recalculateMaxHp() {
        this.setMaxHP(this.getMaxHP() + Constants.EIGHTY);
    }

    // prima abilitate a lui Knight
    private int execute(final Players player, final int strategy) {
        float damage = 2 * Constants.ONE_HUNDRED + Constants.THIRTY * this.getLevel();
        if (this.getActualGround().equals(Ground.Land)) {
            damage += (Constants.FIFTEEN * damage) / Constants.ONE_HUNDRED;
        }
        this.setDamageWithoutRaceModifiers(this.getDamageWithoutRaceModifiers() + damage);

        int procent = this.getModifier();
        if (player instanceof Pyromancer) {
            procent += Constants.TEN;
        } else if (player instanceof Rogue) {
            procent += Constants.FIFTEEN;
        } else if (player instanceof Wizard) {
            procent -= Constants.FIFTEEN;
        } else {
            procent = Constants.ZERO;
        }

        if (strategy == Constants.ONE) {
            this.setHp(this.getHp() - this.getHp() / Constants.FIVE);
            procent += Constants.TEN * Constants.FIVE;
        } else if (strategy == Constants.TWO) {
            this.setHp(this.getHp() + this.getHp() / Constants.FOUR);
            procent -= Constants.TWENTY;
        }

        damage = Math.round(damage);
        damage += (procent * damage) / Constants.ONE_HUNDRED;
        return Math.round(damage);
    }

    // a doua abilitate a lui Knight
    private int slam(final Players player, final int strategy) {
        player.setStun(true);
        float damage = Constants.ONE_HUNDRED + Constants.FORTY * this.getLevel();
        if (this.getActualGround().equals(Ground.Land)) {
            damage += (Constants.FIFTEEN * damage) / Constants.ONE_HUNDRED;
        }
        this.setDamageWithoutRaceModifiers(this.getDamageWithoutRaceModifiers() + damage);

        int procent = this.getModifier();
        if (player instanceof Knight) {
            procent += Constants.TWENTY;
        } else if (player instanceof Pyromancer) {
            procent -= Constants.TEN;
        } else if (player instanceof Rogue) {
            procent -= Constants.TWENTY;
        } else if (player instanceof Wizard) {
            procent += Constants.FIVE;
        }

        while (player.getTemporalDamage().size() != Constants.ZERO) {
            player.getTemporalDamage().remove(Constants.ZERO);
        }

        if (strategy == Constants.ONE) {
            this.setHp(this.getHp() - this.getHp() / Constants.FIVE);
            procent += Constants.TEN * Constants.FIVE;
        } else if (strategy == Constants.TWO) {
            this.setHp(this.getHp() + this.getHp() / Constants.FOUR);
            procent -= Constants.TWENTY;
        }

        damage = Math.round(damage);
        damage += (procent * damage) / Constants.ONE_HUNDRED;
        return Math.round(damage);
    }

    // functia suprascrisa de wizard pt interactWith
    public void interactWith(final Players player,
                             final float rivalDamage) {
        int totalDamage = Constants.ZERO;
        totalDamage += execute(player, this.getStrategy());
        totalDamage += slam(player, this.getStrategy());
        player.setHp(player.getHp() - Math.round(totalDamage));
    }
}
