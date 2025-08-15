import java.util.*;
//
classLibraryManagementSystem {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Loan> loans = new LinkedList<>();
    private final Map<String, Book> bookMap = new HashMap<>();
    private final Map<String, User> userMap = new HashMap<>();

    public void addBook(String title, String author, String isbn, int quantity) {
        books.add(new Book(title, author, isbn, quantity));
        System.out.println("Book added successfully.");
    }

    public void removeBook(String isbn) {
        boolean isRemoved = books.removeIf(book -> book.isbn.equals(isbn));
        if (isRemoved) {
            System.out.println("Book removed successfully.");
        }
    }

    public void searchBooks(String query) {
        for (Book book : books) {
            if (book.title.contains(query) || book.author.contains(query) || book.isbn.equals(query)) {
                System.out.println(book);
            }
        }
    }

    public void registerUser(String name, String userId) {
        users.add(new User(name, userId));
        System.out.println("User registered successfully.");
    }

    public void deleteUser(String userId) {
        users.removeIf(user -> user.userId.equals(userId));
        System.out.println("User deleted successfully.");
    }

    public void checkOutBook(String userId, String isbn, int days) {
        for (Book book : books) {
            if (book.isbn.equals(isbn) && book.quantity > 0) {
                book.quantity--;
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, days);
                loans.add(new Loan(userId, isbn, calendar.getTime()));
                System.out.println("Book checked out successfully.");
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String userId, String isbn) {
        Iterator<Loan> iterator = loans.iterator();
        while (iterator.hasNext()) {
            Loan loan = iterator.next();
            if (loan.userId.equals(userId) && loan.isbn.equals(isbn)) {
                iterator.remove();
                for (Book book : books) {
                    if (book.isbn.equals(isbn)) {
                        book.quantity++;
                        System.out.println("Book returned successfully.");
                        return;
                    }
                }
            }
        }
        System.out.println("Loan record not found.");
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Books");
            System.out.println("4. Register User");
            System.out.println("5. Delete User");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    library.addBook(title, author, isbn, quantity);
                    break;
                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 3:
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    library.searchBooks(query);
                    break;
                case 4:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    library.registerUser(name, userId);
                    break;
                case 5:
                    System.out.print("Enter user ID to delete: ");
                    String deleteUserId = scanner.nextLine();
                    library.deleteUser(deleteUserId);
                    break;
                case 6:
                    System.out.print("Enter user ID: ");
                    String checkOutUserId = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String checkOutIsbn = scanner.nextLine();
                    System.out.print("Enter loan period (days): ");
                    int days = scanner.nextInt();
                    library.checkOutBook(checkOutUserId, checkOutIsbn, days);
                    break;
                case 7:
                    System.out.print("Enter user ID: ");
                    String returnUserId = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnUserId, returnIsbn);
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
