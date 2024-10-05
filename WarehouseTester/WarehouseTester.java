import java.util.*;
import java.text.*;
import java.io.*;
public class WarehouseTester {
  
  public static void main(String[] s) {
    Client c1 = new Client("name1");
    Client c2 = new Client("name2");
    ClientList clientList = ClientList.instance();
    clientList.insertClient(c1);
    clientList.insertClient(c2);
    Product p1 = new Product("product1", 1, "man1", 10.21f);
    Product p2 = new Product("product2", 1, "man2", 11.22f);
    ProductList productList = ProductList.instance();
    productList.insertProduct(p1);
    productList.insertProduct(p2);
    WishItem wish = new WishItem(c1, p1, 0);

    // check client list
    Iterator clients = clientList.getClients();
    System.out.println("List of clients");
    while (clients.hasNext()){
      System.out.println(clients.next());
    }
    // check product list
    Iterator products = productList.getProducts();
    System.out.println("List of products");
    while (products.hasNext()){
      System.out.println(products.next());
    }
    // check wish item before wish added
    Iterator wishesb = c1.getWishItems();
    System.out.println("List of Wishes before added a wish, should be none");
    while (wishesb.hasNext()){
      System.out.println(wishesb.next());
    }
    // check wish item after wish added
    c1.addToWishList(wish);
    Iterator wishesp = c1.getWishItems();
    System.out.println("List of Wishes after adding wish");
    while (wishesp.hasNext()){
      System.out.println(wishesp.next());
    }
    
  }
}
    /*UserInterface
            
-Products: List
-userInterface: UserInterface
Reader: Buffered Reader
+UserInterface(): static void
+getToken(prompt: String): String
+addProducts(): void
+addProductToWishlist(): void
+addClient(): void
+help(): void
+getFloat(): Float
+getNumber(): int
+getCommand(): int
+yesOrNo(prompt: String): bool
*/

/* Warehouse
 * -Clients: ClientList
-Products: ProductList
+Warehouse(): static void
+addProduct(): Book
+addClient(): Client
+addProductToWishlist(): WishItem
+SearchClient(): Bool
+SearchProduct(): Bool
+SearchWishlist(): Bool
 */
