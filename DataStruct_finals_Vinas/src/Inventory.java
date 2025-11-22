import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Inventory {
    // LinkedList to store Book objects (bookStock)
    private static LinkedList<Book> bookStock = new LinkedList<>();

    // Queue to store customer orders (book titles)
    private static Queue<String> customerRequests = new LinkedList<>();

    /**
     * @brief Method to add a new book to the bookStock
     * @param input Scanner object for user input
     */
    public static void addBook(Scanner input) {
        System.out.print("Enter book title: ");
        String title = input.nextLine();
        System.out.print("Enter book author: ");
        String author = input.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = input.nextLine();
        System.out.print("Enter book price: ");
        double price = input.nextDouble();
        input.nextLine(); // Consume newline

        Book itemToAdd = new Book(title, author, isbn, price);
        bookStock.add(itemToAdd);
        System.out.println("Book added successfully!");
    }

    /**
     * @brief Method to display all books in the bookStock
     */
    public static void displayAllBooks() {
        if (bookStock.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("--- All Books in Inventory ---");
            for (Book book : bookStock) {
                System.out.println(book);
            }
            System.out.println("-----------------------------");
        }
    }

    /**
     * @brief Method to sort books by title using Bubble Sort
     */
    public static void sortBooksByTitle() {
        if (bookStock.isEmpty()) {
            System.out.println("No books to sort.");
            return;
        }
        System.out.println("Sorting books by title...");
        // Convert LinkedList to array for easier sorting
        Book[] stockArray = bookStock.toArray(new Book[0]);
        int n = stockArray.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (stockArray[j].getTitle().compareToIgnoreCase(stockArray[j + 1].getTitle()) > 0) {
                    // Swap
                    Book temp = stockArray[j];
                    stockArray[j] = stockArray[j + 1];
                    stockArray[j + 1] = temp;
                }
            }
        }
        // Clear and refill the LinkedList with sorted array
        bookStock.clear();
        for (Book book : stockArray) {
            bookStock.add(book);
        }
        System.out.println("Books sorted successfully!");
    }

    /**
     * @brief Method to search for a book by title using Linear Search
     * @param input Scanner object for user input
     */
    public static void searchBookByTitle(Scanner input) {
        System.out.print("Enter the title of the book to search for: ");
        String targetTitle = input.nextLine();
        boolean found = false;
        for (Book book : bookStock) {
            if (book.getTitle().equalsIgnoreCase(targetTitle)) {
                System.out.println("Book found:");
                System.out.println(book);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

    /**
     * @brief Method to add an order to the queue
     * @param input Scanner object for user input
     */
    public static void addOrderToQueue(Scanner input) {
        System.out.print("Enter the title of the book to order: ");
        String requestedTitle = input.nextLine();
        customerRequests.add(requestedTitle);
        System.out.println("Order for \"" + requestedTitle + "\" has been added to the queue.");
    }

    /**
     * @brief Method to process the next order from the queue
     */
    public static void processNextOrder() {
        if (customerRequests.isEmpty()) {
            System.out.println("No orders in the queue.");
        } else {
            String completedRequest = customerRequests.poll();
            System.out.println("Processing next order...");
            System.out.println("Processed order for: " + completedRequest);
        }
    }

    /**
     * @brief Main method with menu-driven interface
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean isSystemActive = true;

        System.out.println("Welcome to the Bookstore Inventory Management System!");

        while (isSystemActive) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Sort books by title");
            System.out.println("4. Search for a book by title");
            System.out.println("5. Add a customer order to the queue");
            System.out.println("6. Process the next customer order");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook(input);
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    sortBooksByTitle();
                    break;
                case 4:
                    searchBookByTitle(input);
                    break;
                case 5:
                    addOrderToQueue(input);
                    break;
                case 6:
                    processNextOrder();
                    break;
                case 7:
                    isSystemActive = false;
                    System.out.println("Thank you for using the Bookstore Inventory Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        input.close();
    }
}