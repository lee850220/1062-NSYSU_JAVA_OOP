package myjava.homework;

import java.util.Scanner;

public class Bulbasaur extends pokemon {

    Bulbasaur() {
        setHp(40);
        setOri_hp(40);
        setAtk(20);
        setUnique(0);
        setSkill("LS");
        setName("Bulbsaur");
    }

    public int action() {
        String s;
        int chosen;
        Scanner scan = new Scanner(System.in);
        System.out.println("(1) Leaf Razor   (2) Light Shield   (3) Synthesis   (4) Catch");
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
        int d = (int)(Math.random()*11 + getAtk()*4);
        System.out.printf("[Leaf Razor]: %d damage.\n", d);
        return d;
    }

    public int defense_skill() {
        int ori = getUnique();
        if (ori > 0);
        else {
            setUnique((int)(Math.random()*11 + getAtk()*4));
            System.out.printf("[Light Shield]: Shield +%d points.\n", getUnique());
        }
        return 0;
    }

    public int buff_skill() {
        int d = (int)(Math.random()*11 + getAtk()*2);
        System.out.printf("[Synthesis]: HP +%d points.\n", d);
        if ((getHp() + d) > getOri_hp()) {
            setHp(getOri_hp());
        } else {
            setHp(getHp() + d);
        }

        return 0;
    }
}
