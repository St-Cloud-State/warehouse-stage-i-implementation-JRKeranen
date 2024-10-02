import java.util.*;
import java.text.*;
import java.io.*;
public class SimpleTester {
  
  public static void main(String[] s) {
     Product b1 = new Product("qq", "ww", "b1");
     Product b2 = new Product("ee", "rr", "b2");
     ProductList ProductList = ProductList.instance();
     ProductList.insertBook(b1);
     ProductList.insertBook(b2);
     Client m1 = new Client("m1"); 
     Client m2 = new Client("m2");
     System.out.println(b1.getBorrower() + " should be null");
     b1.issue(m1); 
     System.out.println(b1.getBorrower() + " should be m1");
     b1.issue(m2); // try issuing to someone else
     System.out.println(b1.getBorrower() + " still issue to m1");
     System.out.println(b1.getDueDate() + " check due date as per Business Rule"); 
     System.out.println(b1.returnBook()); 
     System.out.println(b1.getBorrower() + " should be null");
     System.out.println(b1.getDueDate() + " should be null");
     Iterator products = ProductList.getBooks();
     System.out.println("List of products");
     while (products.hasNext()){
       System.out.println(products.next());
     }
  }
}
