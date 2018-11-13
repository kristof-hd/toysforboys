package be.vdab.toysforboysWebApplication.valueobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboysWebApplication.entities.Product;
import be.vdab.toysforboysWebApplication.entities.ProductLine; 

public class OrderDetailTest {

	private OrderDetail detail1, againDetail1, detail2; 

	@Before
	public void before() {
		ProductLine productLine1 = new ProductLine("test", "test");
		Product product1 = new Product("test1", "test", "test", 100, 100, BigDecimal.TEN, productLine1); 
		Product product2 = new Product("test2", "test", "test", 100, 100, BigDecimal.TEN, productLine1);
		detail1 = new OrderDetail(product1, 70, BigDecimal.TEN);
		againDetail1 = new OrderDetail(product1, 70, BigDecimal.TEN);
		detail2 = new OrderDetail(product2, 70, BigDecimal.TEN); 
	}

	@Test
	public void detailsAreEqualIfTheirProductNamesAreEqual() {
		assertEquals(detail1, againDetail1); 
	}
	@Test
	public void detailsAreDifferentIfTheirProductNamesAreDifferent() {
		assertNotEquals(detail1, detail2); 
	}
	
	@Test
	public void equalDetailsGiveTheSameHashCode() {
		assertEquals(detail1.hashCode(), againDetail1.hashCode()); 
	}
	
}
