package org.dmonix.shop;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a shopping cart containing products to be purchased
 * @author Peter Nerg
 */
public class Basket {

  public List<Product> list_products(){
    return basket_products;
  }

  private List<Product> basket_products = new ArrayList();



  public void buyProduct(Product storeProduct, int product_amount) {
    Product boughtProducts = storeProduct.buy(product_amount);
     basket_products.add(boughtProducts);
  }

  public void displayProducts() {
    int total_final_cost = 0;
    for(Product p : basket_products){
      int total_cost = 0;
      int total_product_cost = total_cost + (p.getMyCost() * p.getMyQuantity());
      total_final_cost += total_product_cost;
      System.out.println("Basket:");
      System.out.println(p.getMyQuantity() + " " + p.product_name() + "s " + total_product_cost + "Kr");
    }
    if (basket_products.isEmpty()){
      System.out.println("Basket is empty");
    }
    else{
      System.out.println("Total cost:" + total_final_cost + "Kr");
    }



  }
  public int cacheoutprice() {
    int total_cost = 0;
    for (Product p : basket_products) {
      int total_product_cost = total_cost + (p.getMyCost() * p.getMyQuantity());
      total_cost += total_product_cost;
    }
    return total_cost;
  }

  public List basketproducts() {
    return basket_products;
  }


  public Product getProduct(String id) {
      for(Product p : basket_products) {
        if(p.myId.equals(id) )

          return p;
      }
      return null;
    }

  public void removeProduct(Product basketProduct, int product_amount) {
    Product removeProducts = basketProduct.buy(product_amount);
    basket_products.remove(removeProducts);
    }




  public void list_basket_products() {
      for(Product p : basket_products){
        System.out.println(p + "s");
      }
    }

  public void remove_empty() {
      for(Product p : basket_products) {
        if (p.getMyQuantity() == 0) {
          basket_products.remove(p);
        }
    }
    }


  public void remove_all() {
    if (basket_products.isEmpty()){
    }
    else{
      basket_products.clear();
    }

  }
}




  /**
   * TODO it must be possible to add/remove/view items in the cart
   *
   */

