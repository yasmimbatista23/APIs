
package APIs;

import Jwiki.Jwiki;


public class JWikiTest {

    public static void main(String[] args) {
        
        Jwiki jwiki = new Jwiki("pizza");
        System.out.println(jwiki.getExtractText());
    }
    
}
