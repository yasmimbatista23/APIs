package APIs;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CCACScraper2 {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://ccac.edu").get();
        Elements divs = doc.select("div[class=event-item]");

        System.out.println("Here's your CCAC Events!");
        for(Element div : divs) {
            System.out.println(div.text());
        }
        
        
    }
    
}