package myjava.homework;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    public static void main(String args[]) {
        String line, name;
        int level, base, whour, phour, sales, total, rate;
        ArrayList<Manager> ArrayM = new ArrayList<Manager>();
        ArrayList<Seller> ArrayS = new ArrayList<Seller>();

        try {
            // open file
            FileReader fr = new FileReader("inputfile.txt");
            BufferedReader br = new BufferedReader(fr);

            // read data
            while (true) {
                line = br.readLine();
                if (line == null) break;

                if (line.equals("Manager")) {
                    line = br.readLine();
                    level = CheckInt(String.valueOf(line.charAt(5))); // Level
                    line = br.readLine();
                    name = line; // Name
                    line = br.readLine();
                    base = CheckInt(line); // Base pay
                    line = br.readLine();
                    whour = CheckInt(line); // Work hours
                    line = br.readLine();
                    phour = CheckInt(line); // Payment of hours
                    line = br.readLine();
                    sales = CheckInt(line); // Sales done
                    line = br.readLine();
                    total = CheckInt(line); // Total sale
                    line = br.readLine(); // read empty line
                    ArrayM.add(new Manager(String.valueOf(level), name, base, whour, phour, sales, total));
                } else {
                    line = br.readLine();
                    level = CheckInt(String.valueOf(line.charAt(5))); // Level
                    line = br.readLine();
                    name = line; // Name
                    line = br.readLine();
                    base = CheckInt(line); // Base pay
                    line = br.readLine();
                    whour = CheckInt(line); // Work hours
                    line = br.readLine();
                    phour = CheckInt(line); // Payment of hours
                    line = br.readLine();
                    rate = CheckInt(line); // Sales rate
                    line = br.readLine(); // read empty line
                    ArrayS.add(new Seller(String.valueOf(level), name, base, whour, phour, rate));
                }
            }

            // output result
            System.out.println("********************************************************");
            System.out.printf("Number of employees working as MANAGER are: %d\n", ArrayM.size());
            System.out.println("********************************************************");
            for (int i = 0; i < ArrayM.size(); i++) {
                System.out.printf("%d. Manager Details:\n", i+1);
                System.out.printf("Store Details: %s\n", ArrayM.get(i).getStoreDetails());
                System.out.printf("Employee Name: %s\n", ArrayM.get(i).getEmployeeName());
                System.out.printf("Base Pay: $%.1f\n", ArrayM.get(i).getBasePay());
                System.out.printf("Number of Hours worked: %.1fhrs\n", ArrayM.get(i).getNumberOfHoursWorked());
                System.out.printf("Payment Rate per hour: $%.1f/hr\n", ArrayM.get(i).getHourlyRate());
                System.out.printf("Total Sales in store: $%.1f\n", ArrayM.get(i).getTotalStoreSales());
                System.out.printf("Sales done: $%.1f\n", ArrayM.get(i).getSalesDone());
                System.out.printf("Percentage of sales done: %.2f", ArrayM.get(i).salesPercentByManager());
                System.out.println("%");
                System.out.printf("Gross Payment: $%.1f\n", ArrayM.get(i).calculatePay());
                System.out.printf("Remaining stroe revenue: $%.1f\n", ArrayM.get(i).calculateStoreRevenue(ArrayM.get(i).getTotalStoreSales()));
                System.out.printf("Is %s eligible for promotion? ", ArrayM.get(i).getEmployeeName());

                // GrossPayment > 50000 && SalesPercent >= 50 && StoreRevenue >= BasePayment * 4
                if (ArrayM.get(i).checkPromotionEligibility()) {
                    System.out.println("Yes, he/she is");
                } else {
                    System.out.println("No, he/she is not");
                }
                System.out.println();
            }

            System.out.println("********************************************************");
            System.out.printf("Number of employees working as SALES ASSOCIATES are: %d\n", ArrayS.size());
            System.out.println("********************************************************");
            for (int i = 0; i < ArrayS.size(); i++) {
                System.out.printf("%d. Sales Associate Details:\n", i+1);
                System.out.printf("Store Details: %s\n", ArrayS.get(i).getStoreDetails());
                System.out.printf("Employee Name: %s\n", ArrayS.get(i).getEmployeeName());
                System.out.printf("Base Pay: $%.1f\n", ArrayS.get(i).getBasePay());
                System.out.printf("Number of Hours worked: %.1fhrs\n", ArrayS.get(i).getNumberOfHoursWorked());
                System.out.printf("Payment Rate per hour: $%.1f/hr\n", ArrayS.get(i).getHourlyRate());
                System.out.printf("Sales Rate: %.1f", ArrayS.get(i).getSalesRate());
                System.out.println("%");
                System.out.printf("Total commission: $%.1f\n", ArrayS.get(i).calculateCommission());
                System.out.printf("Gross Payment: $%.1f\n", ArrayS.get(i).calculatePay());
                System.out.printf("Is %s eligible for promotion? ", ArrayS.get(i).getEmployeeName());

                // Base + Commission + Payment > 25000 && SalesRate >= 10
                if (ArrayS.get(i).checkPromotionEligibility()) {
                    System.out.println("Yes, he/she is eligible");
                } else {
                    System.out.println("No, he/she is not eligible");
                }
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            System.out.println("[Error] : File open failed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
