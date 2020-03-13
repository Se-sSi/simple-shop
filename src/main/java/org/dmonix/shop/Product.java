package org.dmonix.shop;

/**
 * Represents one single product item
 * @author Peter Nerg
 */
public class Product {
  
  //TODO implement attributes for ID, name, price and quantity/stock
  public final String myId;
  private final String myName;
  private int myQuantity;
  private int myCost;
  
  public Product(String id, String name, int quantity, int cost) {
    this.myId = id;
    this.myName = name;
    this.myQuantity = quantity;
    this.myCost = cost;
    //TODO store all constructor arguments in own fields
  }

  public String toString() {
    return myId +":"+ myName + " quantity:" + myQuantity + " cost:" + myCost;
  }

  public Product buy(int product_amount) {
    myQuantity -= product_amount;
    return new Product(myId, myName, product_amount, myCost);
  }

  public int getMyQuantity() {
    return myQuantity;
  }
  public int getMyCost(){
    return myCost;
  }
  public String product_name(){
    return myName;

  }


}
