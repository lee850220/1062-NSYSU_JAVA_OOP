package myjava.homework.part1;

public class Apple {
    private String variety;
    private int price;
    private double weight;

    public Apple(int price, double weight) {
        this.price = price;
        this.weight = weight;
    }

    public Apple(String variety, int price, double weight) {
        this.variety = variety;
        this.price = price;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    protected void setPrice(int price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    protected void setVariety(String variety) {
        this.variety = variety;
    }
}
