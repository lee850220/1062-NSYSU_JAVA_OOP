package myjava.homework;

import java.util.Scanner;

public class HW6_main {

    public static class wild extends pokemon {

        wild() {
            setHp((int)((Math.random()*151 + 150)));
            setOri_hp(getHp());
            setAtk((int)((Math.random()*6 + 30)));
        }

        public int action() {
            return attack_skill();
        }

        public int attack_skill() {
            return (int)(Math.random()*11 + getAtk());
        }

        public int defense_skill() {
            return 0;
        }

        public int buff_skill() {
            return 0;
        }
    }

    private static boolean ranRate(int rate) {
        //System.out.println("rate=" + rate);
        int r = (int)(Math.random()*100+1);
        if (r < rate) return true;
        else return false;
    }

    public static void main(String[] args) {
        String s;
        int chosen;
        boolean caught = false;
        pokemon p, w;

        Scanner scan = new Scanner(System.in);
        System.out.println("(1) Pikachu (2) Bulbasaur (3) Charizard");
        System.out.print("Choose your pokemon (By default (1)) : ");
        s = scan.nextLine();
        if (s.length() > 1) chosen = 0;
        else {
            switch (s.charAt(0)) {
                case '1':
                    chosen = 1;
                    break;
                case '2':
                    chosen = 2;
                    break;
                case '3':
                    chosen = 3;
                    break;
                default:
                    chosen = 0;
            }
        }
        switch (chosen) {
            default:
            case 1:
                p = new Pikachu();
                break;
            case 2:
                p = new Bulbasaur();
                break;
            case 3:
                p = new Charizard();
                break;
        }

        w = new wild();
        System.out.println("[Wild pokemon appeared!]");

        while (true) {
            /* show info */
            System.out.println("----Pokemon----          ----Wild Pokemon----");
            System.out.printf("  HP : %-20dHP : %d\n", p.getHp(), w.getHp());
            System.out.printf("  ATK: %-20dATK: %d\n", p.getAtk(), w.getAtk());
            System.out.printf("  %-3s: %d\n", p.getSkill(), p.getUnique());
            System.out.println("---------------          --------------------");

            if (p.getHp() == 0) {
                System.out.println("You dead.");
                return;
            } else if (w.getHp() == 0) {
                System.out.println("You win...");
                return;
            }

            /* my pokemon turn */
            int act;
            act = p.action();

            if (act == -1) {
                System.out.println("[catch]: Throw the Poké Ball");
                caught = ranRate((int)(((double)(((wild) w).getOri_hp() - ((wild) w).getHp()) / ((wild) w).getOri_hp())* 100));
                if (caught) {
                    System.out.println("---------------          --------------------\nyou caught the wild pokemon");
                    return;
                } else {
                    System.out.println("you did not catch the wild pokemon");
                }
                act = 0;
            }

            /* wild pokemon turn */
            int act_w = 0;
            act_w = w.action();
            System.out.printf("[Wild Pokemon]: %d damage.\n", act_w);

            /* pokemon got damage */
            if (p.getSkill() == "EVA") {
                if (ranRate(p.getUnique())) {
                    System.out.println("Evasion Succeed");
                } else {
                    System.out.printf("[%s]: HP - %d points.\n", p.getName(), act_w);
                    if (p.getHp() < act_w) p.setHp(0);
                    else p.setHp(p.getHp() - act_w);
                }
            } else if (p.getSkill() == "LS") {
                if (p.getUnique() > 0) {
                    /* have shield */
                    if (p.getUnique() >= act_w) {
                        /* shield enough */
                        System.out.printf("[Light Shield]: Shield -%d damage.\n", act_w);
                        p.setUnique(p.getUnique() - act_w);
                    } else {
                        /* shield not enough */
                        System.out.printf("[Light Shield]: Shield -%d damage.\n", p.getUnique());
                        System.out.printf("[%s]: HP - %d points.\n", p.getName(), act_w - p.getUnique());
                        p.setUnique(0);
                        if ((act_w - p.getUnique()) > p.getHp()) {
                            p.setHp(0);
                        } else {
                            p.setHp(p.getHp() - (act_w - p.getUnique()));
                        }
                    }
                } else {
                    /* no shield */
                    System.out.println("[Light Shield]: Shield -0 damage.");
                    System.out.printf("[%s]: HP - %d points.\n", p.getName(), act_w);
                    if (p.getHp() < act_w) p.setHp(0);
                    else p.setHp(p.getHp() - act_w);
                }
            } else {
                System.out.printf("[%s]: HP - %d points.\n", p.getName(), act_w);
                if (p.getHp() < act_w) p.setHp(0);
                else p.setHp(p.getHp() - act_w);

                /* parry */
                if (act == -2) act = act_w;
            }

            /* wild got damage */
            if (w.getHp() < act) w.setHp(0);
            else w.setHp(w.getHp() - act);
        }
    }
}
