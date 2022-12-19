public class Battle {
    public void fight(Creature player, Creature monster, World.FightCallback fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----���: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(monster, player, fightCallback);
                } else {
                    isFightEnded = makeHit(player, monster, fightCallback);
                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
    private Boolean makeHit(Creature defender, Creature attacker, World.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHp() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s ����� ���� � %d ������!", attacker.getName(), hit));
            System.out.println(String.format("� %s �������� %d ������ ��������...", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s �����������!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Player) {
            System.out.println("� ���������, �� ��������");
            fightCallback.fightLost();
            return true;
        } else if(defenderHealth <= 0) {
            System.out.println(String.format("���� ��������! �� ��������� %d ���� � %d ������", defender.getExp(), defender.getGold()));
            attacker.setExp(attacker.getExp() + defender.getExp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHp(defenderHealth);
            return false;
        }
    }
}
