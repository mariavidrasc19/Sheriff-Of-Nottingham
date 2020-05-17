package jucatori;

import main.Constants;

import java.util.ArrayList;

public final class Pyromancer extends Players {
    private ArrayList<Integer> initeDamage = new ArrayList<Integer>();

    public Pyromancer() {
    }

    public Pyromancer(final int row, final int col, final Ground ground, final int id) {
        super(row, col, ground, id);
        this.setHp(Constants.FIVE * Constants.ONE_HUNDRED);
        this.setMaxHP(Constants.FIVE * Constants.ONE_HUNDRED);
    }

    public String getName() {
        return "Pyromancer";
    }

    // jucatorul isi stabileste strategia
    public void makeStrategy() {
        if (((this.getMaxHP() / Constants.FOUR) <= this.getHp()) && (this.getHp()
                < (this.getMaxHP() / Constants.THREE))) {
            this.setStrategy(Constants.ONE);
        } else if (this.getHp() < (this.getMaxHP() / Constants.FOUR)) {
            this.setStrategy(Constants.TWO);
        }
    }

    // verifica daca Pyromancer nu a primit mai mult HP decat poate avea
    public void verifyHP() {
        int maxHP = Constants.FIVE * Constants.ONE_HUNDRED + this.getLevel() * Constants.FIFTY;
        if (this.getHp() > maxHP) {
            this.setHp(maxHP);
        }
    }

    //recalculeaza max HP
    public void recalculateMaxHp() {
        this.setMaxHP(this.getMaxHP() + Constants.FIFTY);
    }

    // prima abilitate a lui Pyromancer
    private int fireblast(final Players player, final int strategy) {
        float damage = Constants.THREE_HUNDRED_FIFTY + Constants.FIFTY * this.getLevel();
        if (this.getActualGround().equals(Ground.Volcanic)) {
            damage += (Constants.TWENTY_FIVE * damage) / Constants.ONE_HUNDRED;
        }
        this.setDamageWithoutRaceModifiers(this.getDamageWithoutRaceModifiers()
                + Math.round(damage));

        float procent = this.getModifier();
        if (player instanceof Knight) {
            procent += Constants.TWENTY;
        } else if (player instanceof Pyromancer) {
            procent -= Constants.TEN;
        } else if (player instanceof Rogue) {
            procent -= Constants.TWENTY;
        } else if (player instanceof Wizard) {
            procent += Constants.FIVE;
        }

        if (strategy == Constants.ONE) {
            this.setHp(this.getHp() - this.getHp() / Constants.TEN);
            procent += Constants.SIX * Constants.TEN;
        } else if (strategy == Constants.TWO) {
            this.setHp(this.getHp() + this.getHp() / Constants.FIVE);
            procent -= Constants.TWENTY;
        }
        damage = Math.round(damage);
        damage += (procent * damage) / Constants.ONE_HUNDRED;
        return Math.round(damage);
    }

    // a doua abilitate a lui Pyromancer
    private int ignite(final Players player, final int strategy) {
        if (initeDamage.size() == Constants.ZERO) {
            float baseDamage = Constants.ONE_HUNDRED + Constants.FIFTY
                    + Constants.TWENTY * this.getLevel();
            float tempDamage = Constants.FIFTY + Constants.THIRTY * this.getLevel();
            if (this.getActualGround().equals(Ground.Volcanic)) {
                baseDamage += (Constants.TWENTY_FIVE
                        * baseDamage) / Constants.ONE_HUNDRED;
            }
            this.setDamageWithoutRaceModifiers(this.getDamageWithoutRaceModifiers()
                    + Math.round(baseDamage));

            float procent = this.getModifier();
            if (player instanceof Knight) {
                procent += Constants.TWENTY;
                tempDamage += tempDamage / Constants.FIVE;
            } else if (player instanceof Pyromancer) {
                procent -= Constants.TEN;
                tempDamage -= tempDamage / Constants.TEN;
            } else if (player instanceof Rogue) {
                procent -= Constants.TWENTY;
                tempDamage -= tempDamage / Constants.FIVE;
            } else if (player instanceof Wizard) {
                procent += Constants.FIVE;
                tempDamage += tempDamage / Constants.TWENTY;
            }
            final ArrayList<Integer> temporalDamage = new ArrayList<Integer>();
            for (int i = Constants.ZERO; i < Constants.TWO; ++i) {
                initeDamage.add(i, Constants.ONE);
                temporalDamage.add(i, Math.round(tempDamage));
            }
            player.setTemporalDamage(temporalDamage);

            if (strategy == Constants.ONE) {
                this.setHp(this.getHp() - this.getHp() / Constants.TEN);
                procent += Constants.SIX * Constants.TEN;
            } else if (strategy == Constants.TWO) {
                this.setHp(this.getHp() + this.getHp() / Constants.FIVE);
                procent -= Constants.TWENTY;
            }
            baseDamage = Math.round(baseDamage);
            baseDamage += (procent * baseDamage) / Constants.ONE_HUNDRED;
            return Math.round(baseDamage);
        } else {
            initeDamage.remove(Constants.ZERO);
            float baseDamage = Constants.ONE_HUNDRED + Constants.FIFTY
                    + Constants.TWENTY * this.getLevel();
            if (this.getActualGround().equals(Ground.Volcanic)) {
                baseDamage += (Constants.TWENTY_FIVE
                        * baseDamage) / Constants.ONE_HUNDRED;
            }
            this.setDamageWithoutRaceModifiers(this.getDamageWithoutRaceModifiers()
                    + Math.round(baseDamage));

            float procent = this.getModifier();
            if (player instanceof Knight) {
                procent += Constants.TWENTY;
            } else if (player instanceof Pyromancer) {
                procent -= Constants.TEN;
            } else if (player instanceof Rogue) {
                procent -= Constants.TWENTY;
            } else if (player instanceof Wizard) {
                procent += Constants.FIVE;
            }

            if (strategy == Constants.ONE) {
                this.setHp(this.getHp() - this.getHp() / Constants.TEN);
                procent += Constants.SIX * Constants.TEN;
            } else if (strategy == Constants.TWO) {
                this.setHp(this.getHp() + this.getHp() / Constants.FIVE);
                procent -= Constants.TWENTY;
            }
            baseDamage = Math.round(baseDamage);
            baseDamage += (procent * baseDamage) / Constants.ONE_HUNDRED;
            return Math.round(baseDamage);
        }
    }

    // functia suprascrisa de wizard pt interactWith
    public void interactWith(final Players player,
                                   final float rivalDamage) {
        int totalDamage = Constants.ZERO;
        totalDamage += fireblast(player, this.getStrategy());
        totalDamage += ignite(player, this.getStrategy());
        player.setHp(player.getHp() - Math.round(totalDamage));
    }
}
