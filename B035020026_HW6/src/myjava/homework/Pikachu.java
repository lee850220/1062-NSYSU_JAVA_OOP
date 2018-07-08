package myjava.homework;

import java.util.Scanner;

public class Pikachu extends pokemon {



    Pikachu() {
        setHp(80);
        setAtk(40);
        setUnique(20);
        setSkill("EVA");
        setName("Pikachu");
    }

    public int action() {
        String s;
        int chosen;
        Scanner scan = new Scanner(System.in);
        System.out.println("(1) Thunder Shock   (2) Double Team   (3) Thunder   (4) Catch");
        System.out.print("Action: (By default: (1)) ");
        s = scan.nextLine();
        if (s.length() > 1) {
            chosen = attack_skill();
        } else {
            switch (s.charAt(0)) {
                default:
                case '1':
                    chosen = attack_skill();
                    break;
                case '2':
                    defense_skill();
                    chosen = 0;
                    break;
                case '3':
                    buff_skill();
                    chosen = 0;
                    break;
                case '4':
                    chosen = -1;
                    break;
            }
        }
        return chosen;
    }

    public int attack_skill() {
        int d = (int)(Math.random()*11 + getAtk());
        System.out.printf("[Thunder Shock]: %d damage.\n", d);
        return d;
    }

    public int defense_skill() {
        int ori = getUnique();
        if (ori * 2 > 100) setUnique(100);
        else setUnique(ori * 2);
        System.out.printf("[Double Team]: EVA +%d points.\n", getUnique() - ori);
        return 0;
    }

    public int buff_skill() {
        setAtk(getAtk() * 2);
        System.out.printf("[Thunder]: ATK +%d damage.\n", getAtk() / 2);
        return 0;
    }
}
