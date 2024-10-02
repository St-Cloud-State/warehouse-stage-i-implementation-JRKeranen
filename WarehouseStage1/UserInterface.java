import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT = 2;
  private static final int ADD_PODUCT_TO_CLIENT_WISHLIST = 3;
  private static final int HELP = 4;
  private UserInterface() {
      warehouse = Warehouse.instance();
  }

  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }

  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }

  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }

  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }

  public Float getFloat(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Float num = Float.valueOf(item);
        return num.floatValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public void help() {
    System.out.println("Enter a number between 0 and 4 as explained below:");
    System.out.println(EXIT + " to Exit");
    System.out.println(ADD_CLIENT + " to add a client");
    System.out.println(ADD_PRODUCT + " to add a product");
    System.out.println(ADD_PODUCT_TO_CLIENT_WISHLIST + " to add product to clients wishlist");
  }

  public void addClient() {
    String name = getToken("Enter Client name");
    Client result;
    result = warehouse.addClient(name);
    Boolean test = warehouse.ClientExists(result);

    if ((result == null) || (test == false) ) {
      System.out.println("Could not add Client");
    }
    System.out.println(result);
  }

  public void addProducts() {
    Product result;
    do {
      String name = getToken("Enter  name");
      String manufacturer = getToken("Enter manufacturer");
      int quantity = getNumber("Enter quantity");
      Float price = getFloat("Enter price");
      result = warehouse.addProduct(name, quantity, manufacturer, price);
      Boolean test = warehouse.ProductExists(result);
      System.out.println(test);
      if ((result != null) || (test == false)) {
        System.out.println(result);
      } else {
        System.out.println("Product could not be added");
      }
      if (!yesOrNo("Add more Products?")) {
        break;
      }
    } while (true);
  }

  
  public void addProductToWishlist() {
    Product result;
    String cid = getToken("Enter client id");
    do {
      try {
        Client client;
        client = warehouse.searchClientByID(cid);
        if (client == null) {
          System.out.println("Client With id not found");
          return ;
        }

        String pid = getToken("Enter product id you want to add");
        int quantity = getNumber("Enter quantity");
        
        Product product;
        product = warehouse.searchProductByID(pid);

        if (product == null) {
          System.out.println("product With id not found");
          return ;
        }

        WishItem item;
        item = warehouse.addProductToWishlist(client, product, quantity);

        if (item == null){
          System.out.println("There was an error add product to wishlist");
        }
        System.out.println(item);

        if (!yesOrNo("Add more Products to wishlist?")) {
          break;
        }
      } 
      catch (Exception err){
        System.out.println("There was an error add product to wishlist");
      }
    } while (true);
  }

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:
                      addClient();
                      break;
        case ADD_PRODUCT:
                      addProducts();
                      break;
        case ADD_PODUCT_TO_CLIENT_WISHLIST:
                      addProductToWishlist();
                      break;
        case HELP:
                      help();
                      break;
      }
    }
  }
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
