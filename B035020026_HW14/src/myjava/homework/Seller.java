package myjava.homework;

public class Seller extends StoreEmployee {

    private double salesRate;

    // level, name, Payment Base, Work hour, Payment hour, Sales rate
    public Seller( String level, String name, double base, double whour, double phour, double SalesRate) {
        super(whour, phour, level, base, name);
        this.salesRate = SalesRate;
    }

    @Override
    // Base Payment * Commission Rate
    public double calculateCommission() {
        if (salesRate > 30) {
            return getBasePay() * COMMISSION_RATE;
        } else return 0;
    }

    @Override
    public double calculatePay() {
        return getBasePay() + super.calculatePay() + calculateCommission();
    }

    @Override
    // Base + Commission + Payment > 25000 && SalesRate >= 10
    public boolean checkPromotionEligibility() {
        if (getBasePay() + calculatePay() + calculateCommission() > 25000 && salesRate >= 10) {
            return true;
        } else return false;
    }

    public double getSalesRate() {
        return salesRate;
    }
}
