// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int falseOpBr = markdown.indexOf("![",currentIndex);
            if (falseOpBr != -1){
                int falseClBr = markdown.indexOf("](", falseOpBr+1);
                int falseClPr = markdown.indexOf(")", falseClBr + 1);
                currentIndex = falseClPr;
            } else {
                int nextOpenBracket = markdown.indexOf("[", currentIndex);
                int nextCloseBracket = markdown.indexOf("](", nextOpenBracket);
                int closeParen = markdown.indexOf(")", nextCloseBracket + 1);
                if(nextOpenBracket == -1){
                    nextCloseBracket = -1;
                    closeParen = -1;
                }
                if(nextCloseBracket != -1 && nextCloseBracket != -1){
                    toReturn.add(markdown.substring(nextCloseBracket + 2, closeParen));
                } else {
                    break;
                }
                currentIndex = closeParen + 1;
            }
            
            
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}