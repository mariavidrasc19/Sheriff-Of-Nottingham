package jucatori;

import main.Constants;

public final class Wizard extends Players {

    public Wizard() {
    }

    public Wizard(final int row, final int col, final Ground ground, final int id) {
        super(row, col, ground, id);
        this.setHp(Constants.FOUR * Constants.ONE_HUNDRED);
        this.setMaxHP(Constants.FOUR * Constants.ONE_HUNDRED);
    }

    public Wizard(final Players players) {
        this.setPositionInTheCol(players.getPositionInTheCol());
        this.setPositionInTheRow(players.getPositionInTheRow());
        this.setActualGround(players.getActualGround());
        this.setHp(players.getHp());
        this.setXp(players.getXp());
        this.setStatus(players.getStatus());
        this.setTemporalDamage(players.getTemporalDamage());
    }

    public String getName() {
        return "Wizard";
    }

    // jucatorul isi stabileste strategia
    public void makeStrategy() {
        if (((this.getMaxHP() / Constants.FOUR) < this.getHp()) && (this.getHp()
                < (this.getMaxHP() / Constants.TWO))) {
            this.setStrategy(Constants.ONE);
        } else if (this.getHp() < (this.getMaxHP() / Constants.FOUR)) {
            this.setStrategy(Constants.TWO);
        }
    }

    // verifica daca Wizard nu a primit mai mult HP decat poate avea
    public void verifyHP() {
        int maxHP = Constants.FOUR * Constants.ONE_HUNDRED + this.getLevel() * Constants.THIRTY;
        if (this.getHp() > maxHP) {
            this.setHp(maxHP);
        }
    }

    //recalculeaza max HP
    public void recalculateMaxHp() {
        this.setMaxHP(this.getMaxHP() + Constants.THIRTY);
    }

    // prima abilitate a lui wizard
    private int drain(final Players player, final int strategy) {
        int procent = Constants.TWENTY + Constants.FIVE * this.getLevel();
        float baseDamage = (Constants.THREE * Constants.POINT_ONE) * player.getMaxHP();
        if (baseDamage > player.getHp()) {
            baseDamage = player.getHp();
        }
        float damage = (procent * baseDamage) / Constants.ONE_HUNDRED;
        if (this.getActualGround().equals(Ground.Desert)) {
            damage += (Constants.TEN * damage) / Constants.ONE_HUNDRED;
        }
        procent = this.getModifier();
        if (player instanceof Pyromancer) {
            procent -= Constants.TEN;
        } else if (player instanceof Rogue) {
            procent -= Constants.TWENTY;
        } else if (player instanceof Wizard) {
            procent += Constants.FIVE;
        } else if (player instanceof Knight) {
            procent += Constants.TWENTY;
        }

        if (strategy == Constants.ONE) {
            this.setHp(this.getHp() - this.getHp() / Constants.TEN);
            procent += Constants.TEN * Constants.SIX;
        } else if (strategy == Constants.TWO) {
            this.setHp(this.getHp() + this.getHp() / Constants.FIVE);
            procent -= Constants.TWENTY;
        }

        damage += (procent * damage) / Constants.ONE_HUNDRED;
        return Math.round(damage);
    }

    // a doua abilitate a lui wizard
    private int deflect(final Players player, final float rivalDamage, final int strategy) {
        int procent = Constants.THIRTY + Constants.FIVE
                + Constants.TWO * this.getLevel();
        if (procent > Constants.SEVENTY) {
            procent = Constants.SEVENTY;
        }
        float damage = (procent * rivalDamage) / Constants.ONE_HUNDRED;
        if (this.getActualGround().equals(Ground.Desert)) {
            damage += (Constants.TEN * damage) / Constants.ONE_HUNDRED;
        }

        procent = this.getModifier();
        if (player instanceof Pyromancer) {
            procent += Constants.THIRTY;
        } else if (player instanceof Rogue) {
            procent += Constants.TWENTY;
        } else if (player instanceof Knight) {
            procent += Constants.FORTY;
        } else if (player instanceof Wizard) {
            procent = Constants.ZERO;
        }

        if (strategy == Constants.ONE) {
            this.setHp(this.getHp() - this.getHp() / Constants.TEN);
            procent += Constants.TEN * Constants.SIX;
        } else if (strategy == Constants.TWO) {
            this.setHp(this.getHp() + this.getHp() / Constants.FIVE);
            procent -= Constants.TWENTY;
        }

        damage += (procent * damage) / Constants.ONE_HUNDRED;
        return Math.round(damage);
    }

    // functia suprascrisa de wizard pt interactWith
    public final void interactWith(final Players player, final float rivalDamage) {
        float totalDamage = Constants.ZERO;
        totalDamage += drain(player, this.getStrategy());
        totalDamage += deflect(player, rivalDamage, this.getStrategy());
        player.setHp(player.getHp() - totalDamage);
    }
}
