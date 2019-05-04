package modules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvReader {
  public ArrayList<Integer> intList;
  /**
   * Constructor
   */
  public CsvReader() {
    intList = new ArrayList<Integer>();
  }

  /**
   * Read a csv with numbers and store them in an ArrayList
   *
   * @param file  path of the file to be read
   * */
  public void readFile(String file) throws Exception{
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line = null;

    while((line = br.readLine()) != null) {
      String[] values = line.split(", ");

      for (String str : values) {
        intList.add(Integer.parseInt(str));
      }
    }
  }

  /**
   * Main function
   */
  public static void main(String[] args) {
    CsvReader cr = new CsvReader();

    try {
      cr.readFile("/Users/db15/IdeaProjects/algo/src/resources/intList.csv");
    } catch(Exception e) {
      e.printStackTrace();
    }

    System.out.println("csv read: " + cr.intList.size() + " values");
  }
}


