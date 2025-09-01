
package APIs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CatScraperUser {
    
    private String name, hairLenght, gender, age, URL;

    public CatScraperUser(String name, String hairLenght, String gender, String age, String URL) {
        this.name = name;
        this.hairLenght = hairLenght.toLowerCase();
        this.gender = gender.replace(",", "").toLowerCase();
        this.age = age;
        this.URL = URL;
    }

    public String getName() {
        return name;
    }

    public String getHairLenght() {
        return hairLenght;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getURL() {
        return URL;
    }
    
    @Override
    public String toString(){
        return this.name + " is a " + this.gender + " cat who is " + this.age + " old. They are a " + this.hairLenght + ".\n" + "Find more about " + this.name + " here: " + this.URL;
    }
}

class CatScraperDemoGender {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Document doc = Jsoup.connect("https://humaneanimalrescue.org/adopt/?_type=cat").get();
        Elements divs = doc.select("div[class=wpgb-card-inner]");
        
        ArrayList<Cat> cats = new ArrayList();
        Cat currentCat;
        
        //load the cat list
        for (Element div : divs) {
            currentCat = new Cat(
                    div.getElementsByClass("wpgb-block-2").text(), //name
                    div.getElementsByClass("wpgb-block-1").text(), //hair lenght
                    div.getElementsByClass("wpgb-block-3").text(), //gender
                    div.getElementsByClass("wpgb-block-4").text(), //age
                    div.getElementsByClass("wpgb-card-layer-link").attr("href") //URL
            );
            //try and print the URL
            //String url = div.getElementsByClass("wpgb-card-layer-link").attr("href");
            //System.out.println(url);  
            
            //add currentCat to the list
            cats.add(currentCat);
        }
        //ask user for preferences
        System.out.print("What gender would you like to see? ");
        String gender = scan.nextLine();
        
        int count = 1;
        //print all cat objects
        for (Cat cat : cats) {
            if (cat.getGender().equalsIgnoreCase(gender)) {
                System.out.println("Cat #" + count + ": " + cat + '\n');
                count++;   
            }
        }
    }
}
