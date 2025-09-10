import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {
    WebBrowser web1;
    WebBrowser web2;
    WebBrowser web3;
    @Test
    public void test1() {
        web1 = new WebBrowser();
        web2 = new WebBrowser();
        web3 = new WebBrowser();
        assertEquals("google.com", web1.getCurrentPage());
        web1.openNewPage("gradescope.com");
        web1.openNewPage("canvas.edu");
        assertEquals("canvas.edu", web1.getCurrentPage());
        web2.openNewPage("autograder.ucsd.edu");
        web2.openNewPage("discord.com");
        assertEquals("discord.com", web2.getCurrentPage());
        web3.openNewPage("twitter.cn");
        assertEquals("twitter.cn", web3.getCurrentPage());
        assertEquals("gradescope.com", web1.getHistory().get(0));
        web1.previousPage();
        web1.previousPage();
        assertEquals("google.com", web1.getCurrentPage());
        web2.previousPage();
        assertEquals("autograder.ucsd.edu", web2.getCurrentPage());
        Assertions.assertThrows(IllegalStateException.class, () -> {
            web3.nextPage();
        });
        Assertions.assertThrows(IllegalStateException.class, () -> {
            web1.previousPage();
        });
        assertEquals("google.com", web1.getHistory().get(3));
        assertEquals("autograder.ucsd.edu", web2.getHistory().get(2));
        web1.nextPage();
        assertEquals("gradescope.com", web1.getCurrentPage());
        web2.nextPage();
        assertEquals("discord.com", web2.getCurrentPage());
        assertEquals(false, web1.findLink("twitter.cn"));
        assertEquals(true, web1.findLink("gradescope.com"));
        assertEquals("gradescope.com", web1.getCurrentPage());
        web1.previousPage();
        assertEquals("gradescope.com", web1.getCurrentPage());
        assertEquals(true, web2.findLink("discord.com"));
        web2.previousPage();
        web2.previousPage();
        assertEquals("autograder.ucsd.edu", web2.getCurrentPage());
        web1.newTab();
        assertEquals("gradescope.com", web1.getHistory().get(5));
        web1.clearHistory();
        assertEquals(0, web1.getHistory().size());
        web2.newTab();
        assertEquals("autograder.ucsd.edu", web2.getHistory().get(6));
        web2.clearHistory();
        assertEquals(0, web2.getHistory().size());
        web3.newTab();
        assertEquals(1, web3.getHistory().size());
        web3.clearHistory();
        assertEquals(0, web3.getHistory().size());
    }
}