package be.vdab.toysforboysWebApplication.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.vdab.toysforboysWebApplication.enums.Status;
import be.vdab.toysforboysWebApplication.valueobjects.Address;
import be.vdab.toysforboysWebApplication.valueobjects.OrderDetail; 

public class OrderTest {

	private ProductLine productLine1; 
	private Product product1, product2; 
	private OrderDetail detail1, detail2;
	private Customer customer1;
	private Address address1; 
	private Country country1; 
	private Order order1, order2; 
	
	@Before
	public void before() {
		productLine1=new ProductLine("test", "test"); 
		product1=new Product("test1", "test1", "test1", 300, 200, BigDecimal.TEN, productLine1);
		product2=new Product("test2", "test2", "test2", 300, 200, BigDecimal.TEN, productLine1);
		detail1=new OrderDetail(product1, 10, BigDecimal.ONE);
		detail2=new OrderDetail(product2, 20, BigDecimal.valueOf(2));
		address1=new Address("test", "test", "test", "test");
		country1=new Country("test"); 
		customer1=new Customer("test", address1, country1); 
		order1=new Order(LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 10), LocalDate.of(2000, 1, 5), "test", customer1, Status.WAITING); 
		order1.add(detail1); 

		order2=new Order(LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 10), LocalDate.of(2000, 1, 5), "test", customer1, Status.WAITING); 
		order2.add(detail1); 
		order2.add(detail2); 
	}
	
	@Test
	public void getTotalValue() {
		assertEquals(BigDecimal.TEN, order1.getTotalValue()); 
	}

	@Test
	public void getTotalValueForAnOrderWithMultipleLines() {
		assertEquals(BigDecimal.valueOf(50), order2.getTotalValue()); 
	}
	
	@Test
	public void ship() {
		order1.ship();
		assertEquals(Status.SHIPPED, order1.getStatus()); 
		//assertEquals(LocalDate.now(), order1.getShippedDate());
		assertNotNull(order1.getShippedDate());
		OrderDetail detail = order1.getOrderDetails().stream().findFirst().get(); 
		assertEquals(290, detail.getProduct().getQuantityInStock()); 
		assertEquals(190, detail.getProduct().getQuantityInOrder());
	}
	
}