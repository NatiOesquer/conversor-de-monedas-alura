package com.nataliaoesquer.conversordemoneda;

import java.util.Scanner;
public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private int options = 0;
    private String invalidValueMessage = "Invalid value, please try again";
    private String historyMessage = "These are your last conversions";
    private String noConversionsMessage = "You haven't made any conversions yet";
    private FileManager fileManager;
    private String conversionResult;

    public MainMenu(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void showMenu() {
        System.out.println("_____________________________________________________");
        System.out.println("""
            Welcome to the currency converter
                            Choose an option to convert
                            1  ARS>>>>>>>>>>>>>>USD
                            2  USD>>>>>>>>>>>>>>ARS
                            3  COP>>>>>>>>>>>>>>USD
                            4  USD>>>>>>>>>>>>>>COP
                            5  UYU>>>>>>>>>>>>>>USD
                            6  USD>>>>>>>>>>>>>>UYU
                            7  CLP>>>>>>>>>>>>>>USD
                            8  USD>>>>>>>>>>>>>>CLP
                            9  BRL>>>>>>>>>>>>>>USD
                            10 USD>>>>>>>>>>>>>>BRL
                            11 Custom conversion
                            12 View last conversions
                            0  >>>>>>>>>>>>>>>>> Exit
            """);
        System.out.println("_________________________________________________________");

        if (scanner.hasNextInt()) {
            int input = scanner.nextInt();
            scanner.nextLine();

            if (input >= 13 || input < 0) {
                System.out.println(invalidValueMessage);
                showMenu();
            } else if (input == 12) {
                displayHistory();
            } else if (input == 0) {
                System.out.println("Thank you for using our system");
            } else if (input < 12 && input > 0) {
                Calculator conversion = new Calculator();
                conversionResult = conversion.performConversion(input);
                System.out.println("The conversion result is: " + conversionResult);
                fileManager.addRecord(conversionResult);
                fileManager.saveRecords();
                continueMenu();
            }
        } else {
            System.out.println(invalidValueMessage);
            scanner.next();
            showMenu();
        }
    }

    private void displayHistory() {
        if (fileManager.getRecords().size() != 0) {
            System.out.println(historyMessage);
            for (String s : fileManager.getRecords()) {
                System.out.println(s);
            }
            continueMenu();
        } else {
            System.out.println(noConversionsMessage);
            continueMenu();
        }
    }

    public void continueMenu() {
        options = 0;
        System.out.println("""
                Do you want to continue?
                1) Menu
                2) Exit
                """);
        if (scanner.hasNextInt()) {
            options = scanner.nextInt();
            if (options < 1 || options > 2) {
                System.out.println(invalidValueMessage);
                continueMenu();
            } else {
                if (options == 1) {
                    showMenu();
                } else {
                    System.out.println("Thank you for using our Converter");
                }
            }
        } else {
            System.out.println(invalidValueMessage);
            scanner.next();
            continueMenu();
        }
    }


}
