import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  private ProductList productList;
  private ClientList clientList;
  private static Warehouse warehouse;
  private Warehouse() {
    productList = ProductList.instance();
    clientList = ClientList.instance();
  }
  public static Warehouse instance() {
    if (warehouse == null) {
      //MemberIdServer.instance(); // instantiate all singletons
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  public Product addProduct(String name,int quantity,String manufacturer,float price) {
    Product product = new Product(name,quantity,manufacturer,price);
    if (productList.insertProduct(product)) {
      return (product);
    }
    return null;
  }
  public Client addClient(String name) {
    Client client = new Client(name);
    if (clientList.insertClient(client)) {
      return (client);
    }
    return null;
  }

  public WishItem addProductToWishlist(Client client, Product product, int quantity) {
    WishItem item = new WishItem(client, product, quantity);
    WishItem result;
    result = client.addToWishList(item);
    return result;
  }

  public Boolean ClientExists(Client searchClient) {
    Iterator<Client> iterator = clientList.getClients();
    do {
      Client client = iterator.next();
      if (client.getId() == searchClient.getId()) {
        return true;
      }
    }
    while(iterator.hasNext());
    return false;
  }

  public Boolean ProductExists(Product searchProduct) {
    Iterator<Product> iterator = productList.getProducts();
    do {
      Product product = iterator.next();
      if (product.getID() == searchProduct.getID()) {
        return true;
      }
    }
    while(iterator.hasNext());
    return false;
  }

  public Client searchClientByID(String id) {
    System.out.println("Entered function");
    Iterator<Client> iterator = clientList.getClients();
    do {
      Client client = iterator.next();
      System.out.println("ran");
      System.out.println(client.getId() + ", " + id);
      if (client.getId().equals(id)) {
        return client;
      }
    }
    while(iterator.hasNext());
    return null;
  }

  public Product searchProductByID(String id) {
    Iterator<Product> iterator = productList.getProducts();
    do {
      Product product = iterator.next();
      if (product.getID().equals(id)) {
        return product;
      }
    }
    while(iterator.hasNext());
    return null;
  }
}
