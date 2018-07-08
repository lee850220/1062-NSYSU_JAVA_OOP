package myjava.homework;

public class Manager extends StoreEmployee {

    private double bonusRate;
    private double salesDone;
    private double totalStoreSales;

    // totalSales, Sales done, payment hour, work hour, level, basePay, name
    public Manager( String level, String name, double base, double whour, double phour, double sales, double total) {
        super(whour, phour, level, base, name);
        salesDone = sales;
        totalStoreSales = total;
        if (sales > 25000) {
            bonusRate = 15;
        } else if (sales >= 5000) {
            bonusRate = 10;
        } else {
            bonusRate = 1;
        }
    }

    @Override
    public double calculatePay() {
        return salesDone * bonusRate * 0.01 + getBasePay() + getNumberOfHoursWorked() * getHourlyRate();
    }

    @Override
    // GrossPayment > 50000 && SalesPercent >= 50 && StoreRevenue >= BasePayment * 4
    public boolean checkPromotionEligibility() {
        if (calculatePay() > 50000 && salesPercentByManager() >= 50 && (calculateStoreRevenue(totalStoreSales) >= getBasePay() * 4)) {
            return true;
        } else return false;
    }

    public double getTotalStoreSales() {
        return totalStoreSales;
    }

    public double getSalesDone() {
        return salesDone;
    }

    public double salesPercentByManager() {
        return salesDone / totalStoreSales * 100;
    }
}
