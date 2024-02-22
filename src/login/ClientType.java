package login;

public enum ClientType {
    Administrator(1),
    Company(2),
    Customer(3);

    private int id;

    public int getId() {
        return id;
    }

    ClientType(int id) {
        this.id = id;
    }
}
