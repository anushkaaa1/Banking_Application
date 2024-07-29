package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connector connector = new Connector();
        Scanner scan = new Scanner(System.in);
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" ***Banking System Application***");
        System.out.println(" -----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(" 1. Create a new account  \n 2. Check Balance\n 3. Deposit the amount \n 4. Withdraw the amount  \n 5. Deactivate \n 6. Exit\n ");
        while (true){
            System.out.println("ENTER YOUR CHOICE ::");
            int key=scan.nextInt();
            if(key==6){
                break;
            }
            connector.selectOp(key);
        }
        System.out.println("THANKS FOR USING OUR BANK APPLICATION");
    }
}
