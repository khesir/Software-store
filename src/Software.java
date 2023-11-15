package src;


public class Software {
    String name;
    String version;
    int quantity;
    int price;

    public Software(String name, String version, int quantity, int price) {
        this.name = name;
        this.version = version;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0){
            throw new IllegalArgumentException("Quantity cannot be negative");
        }else{
            this.quantity = quantity;
        }
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-30s%-10s%-10d%-10d\n", name,version,quantity,price);
    }

}
