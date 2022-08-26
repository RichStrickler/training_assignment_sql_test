package com.training.assignments.sqlTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import au.com.bytecode.opencsv.CSVReader;

public class configManager {

  private Map<String, String> configMap = new HashMap<String, String>();

  public configManager(String fileName, char seperator, char quote) {

    try {
      CSVReader reader = new CSVReader(new FileReader(fileName), seperator, quote, 1);

      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
        if (nextLine != null) {
          configMap.put(nextLine[0], nextLine[1]);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Invalid File");
    } catch (IOException e) {
      System.out.println("Input Error");
    }

  }

  public Map<String, String> getMap() {

    return configMap;
  }

}
