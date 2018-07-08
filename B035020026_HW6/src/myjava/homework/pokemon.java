package myjava.homework;

public abstract class pokemon implements skill {
    private int hp;
    private int atk;
    private int unique;
    private String skill;
    private String name;
    private int ori_hp;

    public int getOri_hp() {
        return ori_hp;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getUnique() {
        return unique;
    }

    public String getSkill() {
        return skill;
    }

    public String getName() {
        return name;
    }

    public void setOri_hp(int hp) {
        this.ori_hp = hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setUnique(int unique) {
        this.unique = unique;
    }

    public void setSkill(String s) {
        this.skill = s;
    }

    public void setName(String s) {
        this.name = s;
    }

    public abstract int action();
}
