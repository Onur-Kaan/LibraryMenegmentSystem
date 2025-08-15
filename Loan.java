import java.util.Date;
//
class  Loan {
    String userId;
    String isbn;
    Date dueDate;

    public Loan(String userId, String isbn, Date dueDate) {
        this.userId = userId;
        this.isbn = isbn;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", ISBN: " + isbn + ", Due Date: " + dueDate;
    }
}
