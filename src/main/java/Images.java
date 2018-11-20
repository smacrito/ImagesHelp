import java.io.*;//for website
import java.net.URL;//retrieve url
import java.util.logging.Level;//log errors
import java.util.logging.Logger;
import java.io.*; //I/O stream
import java.util.logging.Level;
import java.util.logging.Logger;


import org.jsoup.Jsoup;//web scraper
import org.jsoup.nodes.Document;//document is basically the website made into a readable file of html
import org.jsoup.nodes.Element;//elements within the document, like pics or sources
import org.jsoup.select.Elements;

import java.net.URL;

class Images{
    private static String webSiteURL = "https://www.reddit.com/r/EarthPorn/comments/9t8r5o/massive_sunbeams_the_largest_ive_ever_seen/"; //original source to scrap from-- TODO: Create UI to have an entry field that stores into this variable
    private static final String folderPath = "C:\\Downloads/"; //finds the users local downloads folder TODO: create a UI to store the folder path in this location
    // private because when you try to use these in an OutPutStream, they must be static... might make problems if we want to change the URL?
    public static void main(String[] args){
        try{
            Document doc = Jsoup.connect(webSiteURL).get(); //connects to website and makes it a document (basically a file)
            Elements img = doc.getElementsByTag("img"); //finds all elements in the new doc that match the "img" tag
            for(Element el : img){// for each element, get source (src) url
                String src = el.absUrl("src"); //gets the "absolute" URL of the SRC, AKA the online host of the picture
                System.out.println("image found"); //prints when image is found
                getImages(src); //calls getImage method with the SRC as the source for the picture for us to obtain
                }
            } catch(IOException ex){ //if no pic is found, throw exception and log it as severe failure
                System.out.println("error.");
                Logger.getLogger(Images.class.getName()).log(Level.SEVERE,null,ex);
            }
        }
    private static void getImages(String src) throws IOException{
        //String folder = null; //local folder path(not using right now)
        int indexName = src.lastIndexOf("/");// extract image name from src attribute EX: https://www.youtube.com/ <-- will stop at last forward slash and get the index of the last "/"
        // TODO: 11/13/2018 change indexName variable to be more accurate
        if (indexName == src.length()){
            src = src.substring(1,indexName);
        }
        indexName = src.lastIndexOf("/");

        String name = src;//src.substring(indexName,src.length());
        name = name.replace(":",""); // replace all : with ""
        name = name.replaceAll("[^a-zA-Z0-9-.]", "");
        for(int i = 0; i < name.length() - 3; i++){ //replaces all but the last "." for the file extention
            name = name.replaceFirst(".","");
        }

        //System.out.println(name);
        //must open stream for URL
        URL url = new URL(src);
        InputStream in = url.openStream(); //reads the bytes from our stream (website)
        // The openStream() method returns a java.io.InputStream object, so reading from a URL is as easy as reading from an input stream. (from java documentation https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html)
        OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name)); // bufferedoutputstream allows us to write to the computer without calling the underlying system byte-per-byte
        //FileOutputStream out  = new FileOutputStream(folderPath+name);
        for(int b; (b = in.read()) != -1;){ // for all pictures, write them to output file
            out.write(b); // writes to folder path
        }
        out.close(); //close Streams to avoid memory leaks
        in.close();

    } 
}
    
