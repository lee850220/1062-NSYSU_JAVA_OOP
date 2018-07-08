package myjava.homework;

import java.util.Scanner;

public class Charizard extends pokemon {

    Charizard() {
        setHp(200);
        setAtk(60);
        setUnique(30);
        setSkill("CRI");
        setName("Charizard");
    }

    private boolean ranRate(int rate) {
        //System.out.println("rate=" + rate);
        int r = (int)(Math.random()*100+1);
        if (r < rate) return true;
        else return false;
    }

    public int action() {
        String s;
        int chosen;
        Scanner scan = new Scanner(System.in);
        System.out.println("(1) FLamethrower   (2) Parry   (3) Work up   (4) Catch");
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
                    chosen = -2;
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
        if (ranRate(getUnique())) {
            d *= 2;
            System.out.printf("[FLamethrower]: %d damage. (critical)\n", d);
        } else {
            System.out.printf("[FLamethrower]: %d damage.\n", d);
        }
        return d;
    }

    public int defense_skill() {
        System.out.println("[Parry]: return next damage");
        return 0;
    }

    public int buff_skill() {
        int ori = getUnique();
        if (ori + 25 > 100) setUnique(100);
        else setUnique(ori + 25);
        System.out.printf("[Work up]: CRI %d\n", getUnique());
        return 0;
    }
}
