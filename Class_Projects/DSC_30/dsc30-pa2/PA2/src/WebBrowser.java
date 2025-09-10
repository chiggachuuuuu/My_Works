import java.util.ArrayList;
import utilities.FullStackException;



/*
 * NAME: Lin Tian
 * PID: A16844916
 */

/**
 * WebBrowser Implementation
 *
 * @author Lin Tian
 * @since 10/10/2023
 */
public class WebBrowser {

    private String currentPage;
    private ArrayList<String> history;
    private MyStack prev;
    private MyStack next;

    private static final String DEFAULT_PAGE = "google.com";
    private static final int DEFAULTCAPACITY = 100;

    /**
     * This is a default constructor that
     * initialize the current page to the default page,
     * create the history as an array list,
     * initialize a MyStack object with a capacity of 100
     * to store the previously opened pages,
     * and initialize a MyStack object with a capacity of 100
     * to store the next opened pages.
     */

    public WebBrowser() {
        currentPage = DEFAULT_PAGE;
        history = new ArrayList<String>();
        prev = new MyStack(DEFAULTCAPACITY);
        next = new MyStack(DEFAULTCAPACITY);

    }

    /**
     * This method returns the current page.
     * @return a String represents the link of teh current page.
     */
    public String getCurrentPage() {
        return currentPage;
    }

    /**
     * This method opens a new page in the browser with the link passed in,
     * clears the next opened pages,
     * puts the current page to the previously opened pages' memory,
     * set the current page to the newly opened page,
     * and update the history with the current page.
     * @param newLink A String represent the link of the website that needs to be opened.
     */
    public void openNewPage(String newLink) {
        next.clear();
        prev.push(currentPage);
        currentPage = newLink;
        history.add(newLink);
    }

    /**
     * This method imitates the action of getting back to the previous page by
     * putting the current page to the next opened pages' memory,
     * setting the current page to the previous page,
     * and updating the history with current page.
     * @throws IllegalStateException when there is no previous page.
     */
    public void previousPage() throws IllegalStateException {
        if (prev.isEmpty()) {
            throw new IllegalStateException();
        }
        next.push(currentPage);
        currentPage = prev.pop();
        history.add(currentPage);
    }

    /**
     * This method imitates the action of getting back to the previous page by
     * putting the current page to the previously opened pages' memory,
     * setting the current page to the next page,
     * and updating the history with current page.
     * @throws IllegalStateException when there is no next page.
     */
      public void nextPage() throws IllegalStateException {
          if (next.isEmpty()) {
              throw new IllegalStateException();
          }
          prev.push(currentPage);
          currentPage = next.pop();
          history.add(currentPage);
    }

    /**
     * This method opens up a new tab by
     * setting the current page to be the default page,
     * wiping out all the previously opened pages' memory,
     * and wiping out all the next opened pages' memory.
     */
    public void newTab() {
        currentPage = DEFAULT_PAGE;
        prev.clear();
        next.clear();
    }

    /**
     * This method returns the history of the browser.
     * @return an ArrayList that stores the history of the user's actions.
     */
    public ArrayList getHistory() {
        return history;
    }

    /**
     * This method clears all the history of the browser.
     */
    public void clearHistory() {
        history.clear();
    }

    /**
     * This method Search the history to find the given link.
     * If the link is found, opens a new page using this link and returns true.
     * Otherwise, simply returns false.
     * @return a boolean indicating whether the link is found in the history or not.
     */
    public boolean findLink(String link) {
        if (history.contains(link)) {
            this.openNewPage(link);
            return true;
        } else {
            return false;
        }
    }
}
