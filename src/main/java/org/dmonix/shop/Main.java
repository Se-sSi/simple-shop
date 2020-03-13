package org.dmonix.shop;


import java.util.Scanner;
import java.util.stream.Collectors;

import static javax.management.Query.and;


/**
 * Entry point to start the "web shop"
 *
 * @author Peter Nerg
 */
public class Main {

  private static Basket myBasket = new Basket();
  private static Shop myShop = new Shop();

  public static void main(String[] args) {


    myShop.addProduct(new Product("1", "Hat", 5, 50));
    myShop.addProduct(new Product("2", "Banana", 25, 10));
    myShop.addProduct(new Product("3", "train toy", 20, 75));
    myShop.addProduct(new Product("4", "skittles", 23, 25));
    myShop.addProduct(new Product("5", "laptop", 4, 3000));
    myShop.addProduct(new Product("6", "coffee cup", 27, 30));
    myShop.addProduct(new Product("7", "flower pot", 13, 40));


    topMenu();
  }

  private static void topMenu() {
    do {
      try {
        simonsMenuAction();
      } catch (Exception e){
        System.out.println("Wrong input");
      }
    } while (true);
  }

  private static void simonsMenuAction() {
    int product_amount = 0;

    myBasket.remove_empty();
    System.out.println("<----------------------------->");
    System.out.println("1. Shop");
    System.out.println("2. Basket");
    System.out.println("3. Cache out");
    System.out.println("0. Exit application");
    switch (getUserInput()) {
      case 1:
        shop();
        break;


      case 2:
        Basket();
        break;

      case 3:
        cache_out();
        break;

      case 0:
        System.exit(0);
        break;
      default:
        break;
    }
  }

  private static void cache_out() {
    if (myBasket.cacheoutprice() != 0) {
      myBasket.remove_empty();
      myBasket.displayProducts();
      System.out.println("are you sure you want to cash out?");
      System.out.println("1. yes");
      System.out.println("2. no");
      switch (getUserInput()){
        case 1:
          System.out.println("That would be " + myBasket.cacheoutprice() + "Kr");
          System.exit(0);
          break;
        case 2:
          simonsMenuAction();
          break;
      }

    }
    else {
      System.out.println("You need to buy something to cache out");

    }
  }

  private static void Basket() {
    System.out.println("1. List basket");
    System.out.println("2. Remove product from basket");
    System.out.println("3. Empty basket");
    System.out.println("0. Back to top menu");
    switch (getUserInput()){
      case 1:
        basket_items();
        break;
      case 2:
        remove_basket_items();
        break;
      case 3:
        empty_basket();
        break;
      case 0:
        simonsMenuAction();
        break;
    }
  }

  private static void empty_basket() {
    if (myBasket.basketproducts().isEmpty()){
      System.out.println("your basket is already empty");
    }
    else{
    System.out.println("are you sure?");
    System.out.println("1. yes");
    System.out.println("2. no");
    switch (getUserInput()) {
      case 1:
        myBasket.remove_all();
        break;
      case 2:
        simonsMenuAction();
        break;
    }

    }

  }

  private static void remove_basket_items() {
    if (myBasket.basketproducts().isEmpty()){
      System.out.println("your baskt is already empty");
    }
    else {
      int product_amount;
      myBasket.remove_empty();
      myBasket.list_basket_products();
      int id = getUserInput();
      Product p = myBasket.getProduct(String.valueOf(id));
      System.out.println("how many " + p.product_name() + "s do you want to remove?");
      product_amount = getUserInput();
      if (p != null) {
        if (product_amount <= p.getMyQuantity()) {
          myBasket.removeProduct(p, product_amount);
          myBasket.remove_empty();
          myBasket.displayProducts();
        } else {
          if (product_amount > 1) {
            System.out.println("There are not " + product_amount + " " + p.product_name() + "s in the basket");
            System.out.println("There are only " + p.getMyQuantity() + " " + p.product_name() + "s in your basket");
          } else {
            System.out.println("There are not " + product_amount + " " + p.product_name() + " in the basket");
            System.out.println("There are only " + p.getMyQuantity() + " " + p.product_name() + " in your basket");
          }


        }
      } else {
        System.out.println("Product does not exist");
      }

      return;
    }
  }

  private static void shop() {
    System.out.println("1. List products in basket and their total price");
    System.out.println("2. Add product to basket");
    System.out.println("0. Back to top menu");
    switch (getUserInput()) {
      case 1:
        basket_items();
        break;
      case 2:
        shop_buy_items();
        break;
      case 0:
        simonsMenuAction();
        break;
      //TODO print all products in the store and let the user choose to add one or more products
    }
  }

  private static void basket_items() {
    myBasket.remove_empty();
    myBasket.displayProducts();
    //TODO list all items in the shopping cart and let the user to remove one or more items
    return;
  }

  private static void shop_buy_items() {
    int product_amount;
    myBasket.remove_empty();
    myShop.printProducts();
    System.out.println("What product would you like to buy?");
    int id = getUserInput();
    Product p = myShop.getProduct(String.valueOf(id));
    System.out.println("how many " + p.product_name() + "s do do you want");
    product_amount = getUserInput();
    if (product_amount > 0){
      if (p != null) {
        if (product_amount <= p.getMyQuantity()){
          myBasket.buyProduct(p, product_amount);
          myBasket.displayProducts();

        }
        else{
          if (product_amount > 1) {
            System.out.println("There are not " + product_amount + " " + p.product_name() + "s in the storage");
            System.out.println("There are only " + p.getMyQuantity() + " " + p.product_name() + "s in storage");
          }
          else {
            System.out.println("There are not " + product_amount + " " + p.product_name() + " in the storage");
            System.out.println("There are only " + p.getMyQuantity() + " " + p.product_name() + " in storage");
          }
        }
        myBasket.remove_empty();
        System.out.println(myBasket.list_products());
      } else {
        System.out.println("Product does not exist");
      }
    }
    else {
      System.out.println("You need to buy atleast 1 " + p.product_name());
    }
  }


  /**
   * Reads the input from the console and tries to parse it into a string
   *
   * @return
   * @throws NumberFormatException
   */
  private static int getUserInput() throws NumberFormatException {

//TODO wrap all code in this method in a Try/catch statement and return the value 666 in case any exception happens
    Scanner in = new Scanner(System.in);
    return Integer.parseInt(in.nextLine());
//    try {
//      return Integer.parseInt(in.nextLine());
//    } catch (Exception e){
//      System.out.println("invalid input (numbers only)");
//      return getUserInput();
//    }
  }
}
