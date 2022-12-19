public class Player extends Creature {
    private int level, potion, hpMax;
    public Player(int hp,int strength, int dexterity, String name, int exp, int gold) {
        super(hp, strength, dexterity, name, exp, gold);
        level = 1;
        potion = 0;
        hpMax = 100;
    }

    public void levelUp() {
        if(getExp() < 60) System.out.println(String.format("%s пока что не заработал никаких улучшений!", getName()));
        else {
            level += 1;
            setExp(getExp()-60);
            hpMax += 10;
            setStrength(getStrength()+10);
            setDexterity(getDexterity()+15);
            System.out.println(String.format("Твои навыки улучшены до %d уровня! У тебя осталось %d опыта.", level, getExp()));
        }
    }

    public void treatment() {
        if(potion < 1) System.out.println("Тебе нечем лечиться :/ ");
        else if (getHp() == hpMax) System.out.println("У тебя максимум здоровья!");
        else {
            potion -= 1;
            setHp(getHp()+15);
            if(getHp()>hpMax) setHp(hpMax);
            System.out.println("Ты вылечился и теперь у тебя " + getHp() + " единиц здоровья!");
        }
    }

    public int getPotion() {
        return potion;
    }

    public void setPotion(int potion) {
        this.potion = potion;
    }
}
