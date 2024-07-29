package org.example;

import java.sql.*;
import java.util.Scanner;
import java.util.SortedSet;

public class Operation {
    static Scanner sc = new Scanner(System.in);
    private  PreparedStatement preparedStatement;
    protected  void openAccount(Connection connection){
        System.out.print("ENTER ACCOUNT TYPE: ");
        String acctype = sc.next();
        System.out.print("ENTER NAME: ");
        String name = sc.next();
        System.out.print("ENTER BALANCE: ");
        int balance = sc.nextInt();
        try {
            String insertSql = "INSERT INTO bank(acctype,name,balance) VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1,acctype);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,balance);
            preparedStatement.executeUpdate();
            System.out.println("\nYOUR  ACCOUNT IS CREATED\n");
            String sql = "SELECT acc FROM bank WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ACCOUNT NUMBER IS : " + resultSet.getInt("acc"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    protected  void checkBalance(Connection connection)
        {
        System.out.println("ENTER ACCOUNT NUMBER");
        int acount = sc.nextInt();
        try {
            String sql = "SELECT * FROM bank WHERE acc = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, acount);
            ResultSet resultSet = preparedStatement.executeQuery();
            int n = 0;
            while (resultSet.next()) {
                n = resultSet.getInt("acc");
                System.out.println("ACCOUNT TYPE        : " + resultSet.getString("acctype"));
                System.out.println("ACCOUNT HOLDER NAME : " + resultSet.getString("name"));
                System.out.println("ACCOUNT BALANCE     : " + resultSet.getInt("balance"));
            }
            if (n == 0)
                System.out.println("SORRY ACCOUNT NOT EXISTS");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    protected void withdrawDeposit(Connection connection,int key){
        System.out.println("ENTER ACCOUNT NUMBER");
        int acount = sc.nextInt();
        boolean flag = check(connection,acount);
        if(!flag){
            System.out.println("SORRY YOU CAN'T WITHDRAW FROM OR DEPOSIT IN ACCOUNT");
        }
        else {
            System.out.println("ENTER AMOUNT ");
            int bal = sc.nextInt();
            try{
                String sql = "SELECT balance FROM bank WHERE acc = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1,acount);
                ResultSet resultSet = preparedStatement.executeQuery();
                int num = 0;
                while (resultSet.next()){
                    num = resultSet.getInt("balance");
                }
                if(key==3){
                    num +=bal;
                } else if (key==4) {
                    num -= bal;
                }
                String sql1 = "UPDATE bank SET balance = ? WHERE acc = ?";
                preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setInt(1,num);
                preparedStatement.setInt(2,acount);
                preparedStatement.execute();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected  void deactivate(Connection connection)
    {
        System.out.println("ENTER ACCOUNT NUMBER");
        int acount = sc.nextInt();
        try {
            String sql = "DELETE  FROM bank WHERE acc = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,acount);
            preparedStatement.execute();
            System.out.println("ACCOUNT DELETED");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private  boolean check(Connection connection,int account){
        boolean flag = true;
        try {
            String sql = "SELECT acctype FROM bank WHERE acc = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,account);
            ResultSet resultSet = preparedStatement.executeQuery();
            String acc = null;
            while (resultSet.next()){
                acc = resultSet.getString("acctype");
            }
            if(acc.equalsIgnoreCase("F.D"))
                flag = false;
            else
               flag =  true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}