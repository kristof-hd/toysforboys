package be.vdab.toysforboysWebApplication.entities;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals; 
import be.vdab.toysforboysWebApplication.valueobjects.OrderDetail; 

public class ProductTest {

	private ProductLine productLine1; 
	private Product product1; 
	private OrderDetail detail1;
	
	@Before
	public void before() {
		productLine1=new ProductLine("test", "test"); 
		product1=new Product("test", "test", "test", 300, 200, BigDecimal.TEN, productLine1);
		detail1=new OrderDetail(product1, 10, BigDecimal.ONE);
	}
	
	@Test
	//Purpose of this test: I adjust BOTH quantityInStock and quantityInOrder, and I change the value by the correct amount. 
	public void adjustQuantities() {
		product1.adjustQuantities(detail1); 
		assertEquals(290, product1.getQuantityInStock()); 
		assertEquals(190, product1.getQuantityInOrder());
	}
	
}
