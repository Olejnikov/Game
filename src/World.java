import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class World {

    private static BufferedReader br;

    private static Player player = null;

    private static Battle battle = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Как вас зовут? :");
        try {
            command(br.readLine());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Player(
                    100, 30, 15, string, 0, 0
            );
            System.out.println(String.format("Спасти наш мир от дракона вызвался %s!", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                commitDealer();
                printNavigation();
                command(br.readLine());
            }
            break;
            case "2": {
                player.treatment();
                printNavigation();
                command(br.readLine());
            }
            break;
            case "3": {
                commitFight();
                break;
            }
            case "4": {
                player.levelUp();
                printNavigation();
                command(br.readLine());
            }
            break;
            case "5":
                commitFightDragon();
                break;
            case "6":
                System.exit(1);
                break;
            case "да":
                command("3");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
            break;
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. Лечиться");
        System.out.println("3. В темный лес");
        System.out.println("4. Улучшать умения");
        System.out.println("5. На дракона!");
        System.out.println("6. Выход");
    }

    private static void commitFight()  {
        battle.fight(player, createMonster(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d единиц здоровья.", player.getName(), player.getExp(), player.getGold(), player.getHp()));
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
            }
        });
    }

    private static void commitFightDragon() {
        battle.fight(player, createDragon(), new FightCallback() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил самое сокровенное зло в этом мире! Зла больше нет...", player.getName()));
                System.out.println("...");
                System.out.println("Тут больше нечего делать! Пока!");
                try {
                    command("6");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
            }
        });
    }

    private static void commitDealer() {
        if(player.getGold() >= 15) {
            player.setPotion(player.getPotion()+1);
            player.setGold(player.getGold()-15);
            System.out.println("Ты купил 1 зелье и потратил 15 монет! Теперь у тебя " + player.getPotion() + " зелий ");
        } else System.out.println("У тебя не хватает денег :(");
    }

    private static Creature createMonster() {
        Random random = new Random();
        int randInt = random.nextInt(4);
        if (randInt == 1) return new Goblin(
                75, 20, 10, "Гоблин", 25, 15
        );
        if (randInt == 2 || randInt == 3) return new Zombie(
                25, 10, 5, "Зомби", 10, 5
        );
        else return new Skeleton(
                50, 15, 20, "Скелет", 25, 10
        );
    }

    private static Creature createDragon() {
        return new Dragon(1000, 45, 20, "БОСС ДРАКОН", 100, 100);
    }

    interface FightCallback {
        void fightWin();
        void fightLost();
    }
}
