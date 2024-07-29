package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connector {

    private final static String username = "root";
    private final static String password ="amaansalik2004";
    private final static String url = "jdbc:mysql://localhost:3306/spark";
    protected void selectOp (int key)
    {
        Operation operation = new Operation();
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            switch (key)
            {
                case 1:
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    operation.openAccount(connection);
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println();
                    break;

                case 2:
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println();
                    operation.checkBalance(connection);
                    System.out.println();
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    break;

                case 3:
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("---------WELCOME TO DEPOSIT PAGE---------------- ");
                    operation.withdrawDeposit(connection,key);
                    System.out.println();
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    break;

                case 4:
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("---------WELCOME TO WITHDRAWAL PAGE---------------- ");
                    operation.withdrawDeposit(connection,key);
                    System.out.println();
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    break;

                case 5:
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println();
                    operation.deactivate(connection);
                    System.out.println();
                    System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    break;
                default:
                    System.out.println("OOPS PLEASE ENTER CORRECT OPTION");
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
