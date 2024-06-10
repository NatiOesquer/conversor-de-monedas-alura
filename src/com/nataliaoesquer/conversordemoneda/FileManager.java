package com.nataliaoesquer.conversordemoneda;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private List<String> records = new ArrayList<>();

    public void saveRecords() {
        try {
            FileOutputStream fos = new FileOutputStream("records.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(records);
            oos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readRecords() {
        File file = new File("records.dat");
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                records = (List<String>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addRecord(String s) {
        LocalDateTime ldt = LocalDateTime.now();
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String dateTime = " " + ldt.getDayOfMonth() + "/ " + ldt.getMonth() + " / "
                + ldt.getYear() + " Time:" + localTime.format(formatter);
        records.add(0, s + dateTime);
        if (records.size() > 12) records.remove(records.size() - 1);
    }

    public List<String> getRecords() {
        return records;
    }


}
