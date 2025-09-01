package APIs;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CatScraper1 {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://humaneanimalrescue.org/adopt/?_type=cat").get();
        //System.out.println(doc);
        Elements divs = doc.select("div[class=wpgb-card-inner]");
        //System.out.println(divs);
        
        String name;
        String hairLength;
        String gender;
        String age;
        
        int count = 1;
        for (Element div : divs) {
            System.out.println("-----------Cat " + count + "-----------------");
            name = div.getElementsByClass("wpgb-block-2").text();
            hairLength = div.getElementsByClass("wpgb-block-1").text();
            gender = div.getElementsByClass("wpgb-block-3").text();
            age = div.getElementsByClass("wpgb-block-4").text();
            System.out.println(name);
            System.out.println(hairLength);
            System.out.println(gender.replace(",", ""));
            System.out.println(age);
            count++;
        }
        
    } 
    
    
}