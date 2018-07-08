package myjava.homework;

public interface Shop {
    double COMMISSION_RATE = 0.15;
    double TAX = 0.15;

    double calculateCommission();
    double calculateStoreRevenue(double x);
}
