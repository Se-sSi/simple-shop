package org.dmonix.shop;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Peter Nerg
 */
public class ProductTest {
  @Test
  public void createProductInstance() {
    Product product = new Product("123", "MyProduct", 69, 50);
    Assert.assertEquals(69, product.getMyQuantity());
    //TODO there is no assertion here, poor test case. Add some assertions that the values set in the constructor are stored on the product instance
  }
}
