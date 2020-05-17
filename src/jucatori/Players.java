package jucatori;

import angels.Angels;
import main.Constants;

import java.util.ArrayList;

public class Players {
    private String name = null;
    private int id;
    private int positionInTheRow;
    private int positionInTheCol;
    private float hp = Constants.ZERO;
    private int level = Constants.ZERO;
    private int xp = Constants.ZERO;
    private Ground actualGround;
    private Status status;
    private ArrayList<Integer> temporalDamage = new ArrayList<Integer>();
    private float damageWithoutRaceModifiers = Constants.ZERO;
    private int modifier;
    private int strategy;
    private boolean stun;
    private int maxHP;

    public Players() {
        positionInTheCol = Constants.ZERO;
        positionInTheRow = Constants.ZERO;
    }

    public Players(final int row, final int col, final Ground ground, final int id) {
        positionInTheCol = col;
        positionInTheRow = row;
        this.xp = Constants.ZERO;
        this.setActualGround(ground);
        this.id = id;
        modifier = Constants.ZERO;
        strategy = Constants.ZERO;
        stun = false;
        level = Constants.ZERO;
        status = Status.Alive;
        temporalDamage = new ArrayList<Integer>();
        for (int i = Constants.ZERO; i < Constants.TWENTY; ++i) {
            temporalDamage.add(Constants.ZERO);
        }
    }

    // gettere si settere pentru positia in rand si in coloana a jucatorului
    public final int getPositionInTheRow() {
        return positionInTheRow;
    }

    public final void setPositionInTheRow(final int positionInTheRow) {
        this.positionInTheRow = positionInTheRow;
    }

    public final int getPositionInTheCol() {
        return positionInTheCol;
    }

    public final void setPositionInTheCol(final int positionInTheCol) {
        this.positionInTheCol = positionInTheCol;
    }

    public final int getLevel() {
        return level;
    }

    public final float getHp() {
        return hp;
    }

    public final void setHp(final float hp) {
        this.hp = hp;
    }

    public final int getXp() {
        return xp;
    }

    public final void setXp(final int xp) {
        this.xp = xp;
    }

    public final Ground getActualGround() {
        return actualGround;
    }

    public final void setActualGround(final Ground actualGround) {
        this.actualGround = actualGround;
    }

    public final Status getStatus() {
        return status;
    }

    public final void setStatus(final Status status) {
        this.status = status;
    }

    public final ArrayList<Integer> getTemporalDamage() {
        return temporalDamage;
    }

    public final void setTemporalDamage(final ArrayList<Integer> temporalDamage) {
        this.temporalDamage = temporalDamage;
    }

    public final float getDamageWithoutRaceModifiers() {
        return damageWithoutRaceModifiers;
    }

    public final void setDamageWithoutRaceModifiers(final float damageWithoutRaceModifiers) {
        this.damageWithoutRaceModifiers = damageWithoutRaceModifiers;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    /**
     *
     */
    public String getName() {
        return name;
    }

    public final int getModifier() {
        return modifier;
    }

    public final void setModifier(final int modifier) {
        this.modifier = modifier;
    }

    public final int getStrategy() {
        return strategy;
    }

    public final void setStrategy(final int strategy) {
        this.strategy = strategy;
    }

    public final boolean isStun() {
        return stun;
    }

    public final void setStun(final boolean stun) {
        this.stun = stun;
    }

    // seteaza nivelul jucatorului, daca e cazul
    public final void setLevel() {
        int levell = this.level;
        boolean ok = true;
        while (ok) {
            int needXp = Constants.TWO_HUNDRED_FIFTY + levell * Constants.FIFTY;
            if (this.getXp() < needXp) {
                ok = false;
            } else {
                levell++;
            }
        }
        if (levell > this.getLevel()) {
            for (int i = this.level + Constants.ONE; i <= levell; i++) {
                System.out.println(this.getName() + " " + this.getId()
                        + " reached level " + i);
                this.recalculateMaxHp();
                this.setHp(this.getMaxHP());
            }
            this.level = (int) levell;
        }
    }

    // verifica daca jucatorul nu a primit mai mult HP decat poate avea
    public void verifyHP() {
    }

    // functiile  accept si  interactWith pt double dispatch
    // interactWith este suprascrisa in celelalte clase
    public final void accept(final Players player, final float totalDamage) {
        player.interactWith(this, totalDamage);
    }

    public void interactWith(final Players player, final float rivalDamage) {
    }

    public final void accept(final Angels angel) {
        angel.interactWith(this);
    }

    public void makeStrategy() {
    }

    public void recalculateMaxHp() {
    }

    public final int getMaxHP() {
        return this.maxHP;
    }

    public final void setMaxHP(final int maxHP) {
        this.maxHP = maxHP;
    }
}
