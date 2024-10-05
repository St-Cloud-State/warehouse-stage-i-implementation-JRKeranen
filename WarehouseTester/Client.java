import java.util.*;
import java.io.*;
public class Client{
  private String name;
  private String id;
  private static int idNum = 1;
  private List wishList = new LinkedList<WishItem>();
  public  Client (String name) {
    this.name = name;
    id = "C-" + Integer.toString(idNum);
    idNum += 1;
  }

  public String getName() {
    return name;
  }
  public String getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }

  public WishItem addToWishList(WishItem item){
    try {
      wishList.add(item);
      return item;
    } 
    catch (Exception e) {
      return null;
    }
  }
  public Iterator getWishItems(){
    return wishList.iterator();
 }

  public String toString() {
    String string = "Client name " + name + " id " + id;
    return string;
  }
}
