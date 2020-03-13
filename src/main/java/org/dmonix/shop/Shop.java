package org.dmonix.shop;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shop with a number of items
 * @author Peter Nerg
 */
public class Shop {

  private List<Product> store_products = new ArrayList<>();

  public void addProduct(Product p){
    store_products.add(p);
  }

  public void printProducts() {
    for(Product p : store_products){
      System.out.println(p.toString());
    }
  }

  public boolean hasProductId(String id) {

    return store_products.stream()
            .anyMatch(p -> p.myId == id);
  }

  public Product getProduct(String id){
    for(Product p : store_products) {
      if(p.myId.equals(id) )

        return p;
    }
    return null;
  }

  /**
   * TODO it must be possible to add/list product in the shop
   */
  
}
