package myjava.homework;

import kotlin.reflect.jvm.internal.impl.util.Check;

import java.util.Scanner;
import java.sql.*;

public class main {

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    public static void main( String args[] ) {

        try {
            // connect MySQL server
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url  = "jdbc:mysql://localhost:3306/java?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
            String user = "root";
            String pass = "";
            Connection conDB = DriverManager.getConnection(url, user, pass);
            System.out.println("與資料庫連線成功");

            // run SQL
            while (true) {
                int id, score;
                PreparedStatement ps;

                // print menu
                Scanner scan = new Scanner(System.in);
                System.out.println("1.新增帳號\n" + "2.刪除帳號\n" + "3.修改分數\n" + "4.列出所有\n" + "5.離開");
                System.out.print("輸入選擇：");
                String in = scan.nextLine();
                int select = CheckInt(in);
                if (select == 5) break;

                // process
                switch (select) {
                    case 1:
                        System.out.print("輸入id：");
                        in = scan.nextLine();
                        id = CheckInt(in);
                        if (id < 1) {
                            System.out.println("wrong input\n");
                            break;
                        }
                        System.out.print("輸入name：");
                        String name = scan.nextLine();
                        System.out.print("輸入score：");
                        in = scan.nextLine();
                        score = CheckInt(in);
                        if (score < 0) {
                            System.out.println("wrong input\n");
                            break;
                        }
                        ps = conDB.prepareStatement("INSERT INTO hw12 VALUES (?, ?, ?)");
                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setInt(3, score);
                        try {
                            if (ps.executeUpdate() == 1) System.out.println("新增成功\n");
                            else System.out.println("新增失敗\n");
                        } catch (SQLIntegrityConstraintViolationException e) {
                            System.out.println("新增失敗：" + e + '\n');
                        }
                        ps.close();
                        break;
                    case 2:
                        System.out.print("輸入id：");
                        in = scan.nextLine();
                        id = CheckInt(in);
                        if (id < 1) {
                            System.out.println("wrong input\n");
                            break;
                        }
                        ps = conDB.prepareStatement("DELETE FROM hw12 WHERE id = ?");
                        ps.setInt(1, id);
                        try {
                            if (ps.executeUpdate() == 1) System.out.println("刪除成功\n");
                            else System.out.println("刪除失敗\n");
                        } catch (SQLIntegrityConstraintViolationException e) {
                            System.out.println("刪除失敗：" + e + '\n');
                        }
                        ps.close();
                        break;
                    case 3:
                        System.out.print("輸入id：");
                        in = scan.nextLine();
                        id = CheckInt(in);
                        if (id < 1) {
                            System.out.println("wrong input\n");
                            break;
                        }
                        System.out.print("輸入score：");
                        in = scan.nextLine();
                        score = CheckInt(in);
                        if (score < 0) {
                            System.out.println("wrong input\n");
                            break;
                        }
                        ps = conDB.prepareStatement("UPDATE hw12 SET score = ? WHERE id = ?");
                        ps.setInt(1, score);
                        ps.setInt(2, id);
                        try {
                            if (ps.executeUpdate() == 1) System.out.println("修改成功\n");
                            else System.out.println("修改失敗\n");
                        } catch (SQLIntegrityConstraintViolationException e) {
                            System.out.println("修改失敗：" + e + '\n');
                        }
                        ps.close();
                        break;
                    case 4:
                        ps = conDB.prepareStatement("SELECT * FROM hw12");
                        ResultSet rs = ps.executeQuery();
                        System.out.println("id\tname\tscore");
                        while (rs.next()) {
                            id = rs.getInt(1);
                            name = rs.getString(2);
                            score = rs.getInt(3);
                            System.out.println(id + "\t" + name + "\t" + score);
                        }
                        System.out.println("");
                        ps.close();
                        break;
                    default:
                        System.out.println("wrong input\n");
                }
            }
            System.out.println("離開");
            conDB.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("[Error] : " + e);
        }
    }
}
