package APIs;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CCACScraper {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://ccac.edu").get();
        //System.out.println(doc);
        
        Elements divs = doc.select("div[class=card-body]");
        //System.out.println(divs);
        
        //System.out.println(divs.text());
        
//        System.out.println("Here's your CCAC News!");
//        for(Element div : divs) {
//            System.out.println(div.text());
//        }
        
        
    }
    
}