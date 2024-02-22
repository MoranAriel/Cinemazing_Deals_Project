package login;

public enum ClientType {

    Default(0),
    Administrator(1),
    Company(2),
    Customer(3);

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    ClientType(int id) {
        this.id = id;
    }
}
