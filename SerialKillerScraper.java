package java244;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class SerialKillerScraper {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Serial Killers Scraper!");
        System.out.println("I will show you a list of serial killers with some information..");
        System.out.print("Enter a year or a range of years to find out (1980 or 1980-1990): ");
        String yearInput = scanner.nextLine();

        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_serial_killers_in_the_United_States").get();
        Elements tables = doc.select("table.wikitable");

        boolean found = false;

        //loop through all tables
        for (Element table : tables) {
            Elements rows = table.select("tr");

            //loop through all rows
            for (Element row : rows) {
                Elements cells = row.select("td");

                if (cells.size() >= 6) {
                    String name = cells.get(0).text();
                    String yearsActive = cells.get(1).text(); 

                    if (isYearInRange(yearInput, yearsActive)) {
                        String status = cells.get(4).text();
                        String notes = cells.get(5).text(); 

                        //theres a table for unidentified serial killers that doesnt have status so instead they put their state, this changes the state to unkown which is a better output
                        if (status.length() == 2) {
                            status = "Unknown";
                        }

                        System.out.println("--------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Name: " + name);
                        System.out.println("Years Active: " + yearsActive); //I'm not sure if Years Active is the best title but I cant think anymore at this point lol
                        System.out.println("Status: " + status + ".");
                        System.out.println("Notes: " + notes + ".");
                        found = true;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("No serial killers found for the year or range " + yearInput + ".");
        }
    }

    private static boolean isYearInRange(String yearInput, String yearsActive) {
            
        if (yearsActive.contains(yearInput)) {
            return true;
        }

        if (yearsActive.contains("-")) {
            String[] range = yearsActive.split("[-–]");
            try {
                int startYear = Integer.parseInt(range[0].trim());
                int endYear = Integer.parseInt(range[1].trim());
                int inputYear = Integer.parseInt(yearInput);

                if (inputYear >= startYear && inputYear <= endYear) {
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Try again.");
            }
        }

        return false;
    }
}
