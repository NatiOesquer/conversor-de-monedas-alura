package com.nataliaoesquer.conversordemoneda;

public class Converter {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        fileManager.readRecords();

        MainMenu mainMenu = new MainMenu(fileManager);
        mainMenu.showMenu();



    }
}
