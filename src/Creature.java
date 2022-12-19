import java.util.Random;

abstract public class Creature {

    private int hp, strength, dexterity;
    private String name;
    private int exp, gold;

    public Creature(int hp,int strength, int dexterity, String name, int exp, int gold) {
        this.hp = hp;
        this.strength = strength;
        this.dexterity = dexterity;
        this.name = name;
        this.exp = exp;
        this.gold = gold;
    }

    public int attack() {
        if(dexterity * 3 > Random()) return strength;
        if(dexterity * 1.5 > Random()) return strength*2;
        else return 0;
    }

    public String getName() {
        return name;
    }
    //Геттеры и сеттеры
    public void setName(String name) {
        this.name = name;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public int getExp() {
        return exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d", name, hp);
    }

    public int Random() {
        Random random = new Random();
        int randInt = random.nextInt(100);
        return randInt;
    }


}
