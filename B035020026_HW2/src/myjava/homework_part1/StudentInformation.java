package myjava.homework_part1;

public class StudentInformation {
    private String id;
    private String name;
    private int score;

    StudentInformation(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public void setID(String str) {
        this.id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void setData(String id, String name, int score) {
        setID(id);
        setName(name);
        setScore(score);
    }

    public void show_data() {
        System.out.println("student id :" + getID());
        System.out.println("student name :" + getName());
        System.out.println("Score :" + getScore());
    }
}
