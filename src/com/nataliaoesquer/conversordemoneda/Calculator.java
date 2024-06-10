package com.nataliaoesquer.conversordemoneda;

import java.text.DecimalFormat;

import java.util.Scanner;
public class Calculator {
    private ApiQuery apiQuery = new ApiQuery();
    private ExchangeRates exchangeRates = apiQuery.getExchangeRates();
    private Scanner scanner = new Scanner(System.in);
    private String historyMessage = "These are your last conversions";
    private String invalidValueMessage = "Invalid value, please try again";
    private FileManager fileManager;
    private boolean exit = false;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public boolean validateAmount(double amount) {
        return amount > 0 && amount <= 50000000;
    }

    public String performConversion(int input) {
        String fromCurrency = "";
        String toCurrency = "";

        switch (input) {
            case 0:
                System.out.println("Thank you for using our system");
                exit = true;
                break;
            case 1:
                fromCurrency = "ARS";
                toCurrency = "USD";
                break;
            case 2:
                fromCurrency = "USD";
                toCurrency = "ARS";
                break;
            case 3:
                fromCurrency = "COP";
                toCurrency = "USD";
                break;
            case 4:
                fromCurrency = "USD";
                toCurrency = "COP";
                break;
            case 5:
                fromCurrency = "UYU";
                toCurrency = "USD";
                break;
            case 6:
                fromCurrency = "USD";
                toCurrency = "UYU";
                break;
            case 7:
                fromCurrency = "CLP";
                toCurrency = "USD";
                break;
            case 8:
                fromCurrency = "USD";
                toCurrency = "CLP";
                break;
            case 9:
                fromCurrency = "BRL";
                toCurrency = "USD";
                break;
            case 10:
                fromCurrency = "USD";
                toCurrency = "BRL";
                break;
            case 11:
                System.out.println("Enter the source currency code");
                fromCurrency = scanner.nextLine().toUpperCase();
                System.out.println("Enter the target currency code");
                toCurrency = scanner.nextLine().toUpperCase();
                break;
            case 12:
                System.out.println(historyMessage);
                break;
        }

        System.out.println("Enter the amount to convert");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (exchangeRates.getConversionRates() == null) {
            throw new RuntimeException("Conversion rates are not available");
        }

        return amount + " " + fromCurrency + " = " + decimalFormat.format(
                amount * exchangeRates.getConversionRates().get(toCurrency) /
                        exchangeRates.getConversionRates().get(fromCurrency)) + " " + toCurrency;
    }
}
