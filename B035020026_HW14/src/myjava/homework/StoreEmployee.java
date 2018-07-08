package myjava.homework;

public class StoreEmployee implements Employee, Shop {

    private double numberOfHoursWorked;
    private double hourlyRate;
    private String storeDetails;
    private double basePay;
    private String employeeName;

    public StoreEmployee(double whour, double phour, String level, double base, String name) {
        numberOfHoursWorked = whour;
        hourlyRate = phour;
        storeDetails = "Level" + String.valueOf(level);
        basePay = base;
        employeeName = name;
    }

    @Override
    public double calculatePay() {
        return 0;
    }

    @Override
    public boolean checkPromotionEligibility() {
        return true;
    }

    @Override
    public double calculateCommission() {
        return 0;
    }

    @Override
    public double calculateStoreRevenue( double totalStoreSales ) {
        double saleTax;
        if (totalStoreSales > 255000) saleTax = 5;
        else if (totalStoreSales >= 155000) saleTax = 3;
        else saleTax = 1;
        return totalStoreSales - totalStoreSales * TAX - totalStoreSales * saleTax * 0.01 - calculatePay();
    }

    public String getStoreDetails() {
        return storeDetails;
    }

    public double getBasePay() {
        return basePay;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getNumberOfHoursWorked() {
        return numberOfHoursWorked;
    }

}
