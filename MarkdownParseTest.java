import static org.junit.Assert.*;

import java.nio.file.Files;
import org.junit.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.nio.file.Path;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void fileRead() throws Exception{
        HashMap<String, ArrayList<String>> fileMap = new HashMap<>();
        fileMap.put("test-file1.md", new ArrayList<String>(Arrays.asList("https://something.com", "some-page.html")));
        fileMap.put("test-file2.md", new ArrayList<String>(Arrays.asList()));
        fileMap.put("test-file3.md", new ArrayList<String>(Arrays.asList()));

        assertEquals(fileMap.get("test-file1.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file1.md"))));
        assertEquals(fileMap.get("test-file2.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file2.md"))));
        assertEquals(fileMap.get("test-file3.md"), MarkdownParse.getLinks(Files.readString(Path.of("test-file3.md"))));
    }
}