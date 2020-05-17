package jucatori;

import main.Constants;

public class Rogue extends Players {
    private int hit = Constants.ZERO;

    public Rogue() {
    }

    public Rogue(final int row, final int col, final Ground ground, final int id) {
        super(row, col, ground, id);
        this.setHp(Constants.SIX * Constants.ONE_HUNDRED);
        this.setMaxHP(Constants.SIX * Constants.ONE_HUNDRED);
    }

    public final String getName() {
        return "Rogue";
    }

    // jucatorul isi stabileste strategia
    public final void makeStrategy() {
        if (((this.getMaxHP() / Constants.SEVEN) <= this.getHp()) && (this.getHp()
                < (this.getMaxHP() / Constants.FIVE))) {
            this.setStrategy(Constants.ONE);
        } else if (this.getHp() < (this.getMaxHP() / Constants.SEVEN)) {
            this.setStrategy(Constants.TWO);
        }
    }

    // verifica daca Rogue nu a primit mai mult HP decat poate avea
    public final void verifyHP() {
        int maxHP = Constants.SIX * Constants.ONE_HUNDRED + this.getLevel() * Constants.FORTY;
        if (this.getHp() > maxHP) {
            this.setHp(maxHP);
        }
    }

    //recalculeaza max HP
    public final void recalculateMaxHp() {
        this.setMaxHP(this.getMaxHP() + Constants.FORTY);
    }

    // prima abilitate a lui Rogue
    private int backstab(final Players player, final int strategy) {
        float damage = Constants.TWO * Constants.ONE_HUNDRED
                + Constants.TWENTY * this.getLevel();
        if (this.getActualGround().equals(Ground.Woods)) {
            damage += (Constants.FIFTEEN * damage) / Constants.ONE_HUNDRED;
            if (hit % Constants.THREE == Constants.ZERO) {
                damage += damage / Constants.TWO;
            }
            hit++;
        } else {
            hit++;
        }
        this.setDamageWithoutRaceModifiers(this.getDamageWithoutRaceModifiers()
                + Math.round(damage));

        float procent = this.getModifier();
        if (player instanceof Pyromancer) {
            procent += Constants.TWENTY_FIVE;
        } else if (player instanceof Rogue) {
            procent += Constants.TWENTY;
        } else if (player instanceof Wizard) {
            procent += Constants.TWENTY_FIVE;
        } else if (player instanceof Knight) {
            procent -= Constants.TEN;
        }

        if (strategy == Constants.ONE) {
            this.setHp(this.getHp() - this.getHp() / Constants.SEVEN);
            procent += Constants.FORTY;
        } else if (strategy == Constants.TWO) {
            this.setHp(this.getHp() + this.getHp() / Constants.TWO);
            procent -= Constants.TEN;
        }
        damage = Math.round(damage);
        damage += (procent * damage) / Constants.ONE_HUNDRED;
        return Math.round(damage);
    }

    // a doua abilitate a lui Rogue
    private int paralysis(final Players player, final int number, final int strategy) {
        player.setStun(true);
        float damage = Constants.FORTY + Constants.TEN * this.getLevel();
        if (this.getActualGround().equals(Ground.Woods)) {
            damage += (Constants.FIFTEEN * damage) / Constants.ONE_HUNDRED;
        }
        this.setDamageWithoutRaceModifiers(this.getDamageWithoutRaceModifiers()
                + Math.round(damage));

        float procent = this.getModifier();
        if (player instanceof Knight) {
            procent -= Constants.TWENTY;
        } else if (player instanceof Pyromancer) {
            procent += Constants.TWENTY;
        } else if (player instanceof Rogue) {
            procent -= Constants.TEN;
        } else if (player instanceof Wizard) {
            procent += Constants.TWENTY_FIVE;
        }

        if (strategy == Constants.ONE) {
            this.setHp(this.getHp() - this.getHp() / Constants.SEVEN);
            procent += Constants.FORTY;
        } else if (strategy == Constants.TWO) {
            this.setHp(this.getHp() + this.getHp() / Constants.TWO);
            procent -= Constants.TEN;
        }
        damage = Math.round(damage);
        damage += (procent * damage) / Constants.ONE_HUNDRED;

        while (player.getTemporalDamage().size() != Constants.ZERO) {
            player.getTemporalDamage().remove(Constants.ZERO);
        }
        for (int i = Constants.ZERO; i < number; ++i) {
            player.getTemporalDamage().add(Math.round(damage));
        }
        return (int) Math.floor(damage);
    }

    // functia suprascrisa de wizard pt interactWith
    public final void interactWith(final Players player,
                                   final float rivalDamage) {
        float totalDamage = Constants.ZERO;
        totalDamage += backstab(player, this.getStrategy());
        int number = Constants.THREE;
        if (this.getActualGround().equals(Ground.Woods)) {
            number = Constants.SIX;
        }
        totalDamage += paralysis(player, number, this.getStrategy());
        player.setHp(player.getHp() - Math.round(totalDamage));
    }
}
