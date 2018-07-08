package myjava.homework;

import com.opencsv.CSVReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.jfree.chart.ChartUtils.saveChartAsJPEG;


public class readCSV {

    private HashMap map;

    private String[] getTitle( int index) {
        if (index == 1) return new String[] {"productA", "productB", "productC"};
        else return new String[] {"Male", "Female"};
    }

    private static CategoryDataset createDataset(int [] value, String [] key) {
        DefaultCategoryDataset d = new DefaultCategoryDataset();
        for (int i = 0; i < value.length; i++) {
            d.addValue(value[i], "Quantity", key[i]);
        }
        return d;
    }

    private static PieDataset createPieDataset(int [] value, String [] key) {
        DefaultPieDataset d = new DefaultPieDataset();
        for (int i = 0; i < value.length; i++) {
            d.setValue(key[i], value[i]);
        }
        return d;
    }

    public void readfile() throws IOException {

        CSVReader csvReader = new CSVReader(new FileReader("src/myjava/homework/query_result.csv")); // project root
        map = new HashMap();

        /* read title */
        csvReader.readNext();
        //title = csvReader.readNext();

        /* read file */
        List<String[]> list = csvReader.readAll();
        for (int i = 0; i < list.size(); i++) {
            Object a = list.get(i)[0];
            if (map.get(a) != null) {
                ArrayList<String> b = (ArrayList<String>) map.get(a);
                b.add(list.get(i)[1]);
                map.put(a, b);
            } else {
                ArrayList<String> b = new ArrayList<>();
                b.add(list.get(i)[2]);
                b.add(list.get(i)[1]);
                map.put(a, b);
            }
        }
        //System.out.println(map.size());
/*
                for (Object key : map.keySet()) {
                    System.out.print(key + " : ");
                    ArrayList<String> a = (ArrayList<String>) map.get(key);
                    System.out.println(a.get(0) + " , " + a.get(1));
                }
*/
        csvReader.close();
    }

    public void printRes(int a, int b) {
        JFreeChart chart;
        String filename;
        int[] value;
        if (a == 1) {
            value = new int[]{0, 0, 0};
            /* calculate statistic */
            for (Object key : map.keySet()) {
                ArrayList<String> s = (ArrayList<String>) map.get(key);
                for (int i = 1; i < s.size(); i++) {
                    switch (s.get(i)) {
                        case "Product_A":
                            value[0]++;
                            break;
                        case "Product_B":
                            value[1]++;
                            break;
                        case "Product_C":
                            value[2]++;
                            break;
                        default:
                    }
                }
            }

            if (b == 1) {
                /* initialize Histogram chart */
                CategoryDataset dataset;
                dataset = createDataset(value, getTitle(b));
                chart = ChartFactory.createBarChart(
                        "Products sale situation",
                        "Product Compare",
                        "Quantity",
                        dataset,
                        PlotOrientation.VERTICAL,
                        false,
                        false,
                        false
                );
                filename = "product_bar";
            } else {
                /* initialize Pie chart */
                PieDataset dataset;
                dataset = createPieDataset(value, getTitle(a));
                chart = ChartFactory.createPieChart3D(
                        "Products sale situation",
                        dataset,
                        true,
                        false,
                        false
                );
                /* Customize chart  */
                PiePlot3D piePlot3D = (PiePlot3D) chart.getPlot();

                /* Set precision */
                NumberFormat p = NumberFormat.getPercentInstance();
                p.setMinimumFractionDigits(2);

                PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} : {2}", NumberFormat.getInstance(), p);
                piePlot3D.setLabelGenerator(labelGenerator);
                labelGenerator = new StandardPieSectionLabelGenerator("{0}={1}({2})");
                piePlot3D.setLegendLabelGenerator(labelGenerator);
                filename = "product_pie";
            }
        } else {
            value = new int[]{0, 0};
            /* calculate statistic */
            for (Object key : map.keySet()) {
                ArrayList<String> s = (ArrayList<String>) map.get(key);
                switch (s.get(0)) {
                    case "M":
                        value[0]++;
                        break;
                    case "F":
                        value[1]++;
                        break;
                    default:
                }
            }

            if (b == 1) {
                /* initialize Histogram chart */
                CategoryDataset dataset;
                dataset = createDataset(value, getTitle(a));
                chart = ChartFactory.createBarChart(
                        "id_sex",
                        "Sex",
                        "Quantity",
                        dataset,
                        PlotOrientation.VERTICAL,
                        false,
                        false,
                        false
                );
                filename = "sex_bar";
            } else {
                /* initialize Pie chart */
                PieDataset dataset;
                dataset = createPieDataset(value, getTitle(b));
                chart = ChartFactory.createPieChart3D(
                        "id_sex",
                        dataset,
                        true,
                        false,
                        false
                );
                filename = "sex_pie";

                /* Customize chart  */
                PiePlot3D piePlot3D = (PiePlot3D) chart.getPlot();

                /* Set precision */
                NumberFormat p = NumberFormat.getPercentInstance();
                p.setMinimumFractionDigits(2);

                PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} : {2}", NumberFormat.getInstance(), p);
                piePlot3D.setLabelGenerator(labelGenerator);
                labelGenerator = new StandardPieSectionLabelGenerator("{0}={1}({2})");
                piePlot3D.setLegendLabelGenerator(labelGenerator);
            }
        }



        writeChartAsImage(chart, filename);
    }

    public void printRes(String s) {
        int[] value = {0, 0, 0};
        Object key = s;
        ArrayList<String> a = (ArrayList<String>) map.get(key);
        if (a == null) {
            System.out.println("ID is not exist.");
        } else {
            /* calculate statistic */
            for (int i = 1; i < a.size(); i++) {
                switch (a.get(i)) {
                    case "Product_A":
                        value[0]++;
                        break;
                    case "Product_B":
                        value[1]++;
                        break;
                    case "Product_C":
                        value[2]++;
                        break;
                    default:
                }
            }
            /* initialize Histogram chart */
            JFreeChart chart;
            CategoryDataset dataset;
            dataset = createDataset(value, getTitle(1));
            chart = ChartFactory.createBarChart(
                    "Products sale situation",
                    "Product Compare",
                    "Quantity",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false,
                    false,
                    false
            );
            writeChartAsImage(chart, "purchase");
        }
    }

    private static void writeChartAsImage(JFreeChart chart, String filename)  {
        try {
            File file = null;
            file = new File( filename + ".jpg");
            saveChartAsJPEG(file, chart, 600, 400);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
