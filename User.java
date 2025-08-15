//
class User {
    String name;
    String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", User ID: " + userId;
    }
}
