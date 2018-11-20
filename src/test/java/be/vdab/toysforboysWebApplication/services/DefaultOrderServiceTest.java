package be.vdab.toysforboysWebApplication.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import be.vdab.toysforboysWebApplication.entities.Country;
import be.vdab.toysforboysWebApplication.entities.Customer;
import be.vdab.toysforboysWebApplication.entities.Order;
import be.vdab.toysforboysWebApplication.entities.Product;
import be.vdab.toysforboysWebApplication.entities.ProductLine;
import be.vdab.toysforboysWebApplication.enums.Status;
import be.vdab.toysforboysWebApplication.exceptions.OrderNotFoundException;
import be.vdab.toysforboysWebApplication.exceptions.ShippingException;
import be.vdab.toysforboysWebApplication.repositories.OrderRepository;
import be.vdab.toysforboysWebApplication.valueobjects.Address;
import be.vdab.toysforboysWebApplication.valueobjects.OrderDetail;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOrderServiceTest {
	private DefaultOrderService service;
	@Mock
	private OrderRepository repository;
	private Order order;
	private OrderDetail orderDetail; 

	@Before
	public void before() {
		Country country = new Country("test");
		Address adress = new Address("test", "test", "test", "test");
		Customer customer = new Customer("test", adress, country); 
		order = new Order(LocalDate.of(2000, 1, 1), LocalDate.of(2000, 1, 10), LocalDate.of(2000,1, 5), "test", customer, Status.WAITING);
		ProductLine productLine = new ProductLine("test", "test");
		Product product = new Product("test", "test", "test", 100, 100, BigDecimal.TEN, productLine); 
		orderDetail = new OrderDetail(product, 70, BigDecimal.TEN);  
		order.add(orderDetail);

		when(repository.read(1)).thenReturn(Optional.of(order));
		when(repository.read(-1)).thenReturn(Optional.empty());
		service = new DefaultOrderService(repository);

	}

	@Test
	public void shipped() {

		service.shipOrder(1);
		assertEquals(Status.SHIPPED, order.getStatus());
		assertEquals(LocalDate.now(), order.getShippedDate());
		verify(repository).read(1);
		
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		for (OrderDetail detail : orderDetails) {
			assertEquals(30, detail.getProduct().getQuantityInStock()); 
			assertEquals(30, detail.getProduct().getQuantityInOrder());
		}
	}

	@Test(expected = OrderNotFoundException.class)
	public void setAsShippedForNonExistingOrder() {
		service.shipOrder(-1);
		verify(repository).read(-1);
	}
	
	@Test(expected=ShippingException.class) 
	public void orderingTooMuch() {
		orderDetail.setQuantityOrdered(130); 
		service.shipOrder(1);
	}
}