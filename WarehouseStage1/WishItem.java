public class WishItem {
    private Client client;  // The client adding items to their wishlist
    private Product product;  // The product they are adding
    private int quantity;  // The quantity of the product desired

    // Constructor to initialize the WishItem
    public WishItem(Client client, Product product, int quantity) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
    }

    // Getters for the fields
    public Client getClient() {
        return client;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
        return this.client.getId() + " wished for " + this.product.getName() + " x " + this.quantity;
    }
}