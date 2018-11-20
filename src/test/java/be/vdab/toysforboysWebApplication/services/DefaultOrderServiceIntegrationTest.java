package be.vdab.toysforboysWebApplication.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.toysforboysWebApplication.enums.Status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/insertCountry.sql")
@Sql("/insertCustomer.sql")
@Sql("/insertOrder.sql")
public class DefaultOrderServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private DefaultOrderService service;
	@Autowired
	private EntityManager manager;

	private long idVanTestOrder() {
		return super.jdbcTemplate.queryForObject("select id from orders where orderDate='2000-01-01'", Long.class);
	}

	@Test
	public void setAsShippedActions() {
		long id = idVanTestOrder();
		service.shipOrder(id);
		manager.flush();
		Status newStatus = super.jdbcTemplate.queryForObject("select status from orders where id=?", Status.class, id);
		assertEquals(Status.SHIPPED, newStatus);
		LocalDate shippedDate = super.jdbcTemplate.queryForObject("select shippedDate from orders where id=?", LocalDate.class, id);
		assertEquals(LocalDate.now(), shippedDate);
		long newQuantityInStock = super.jdbcTemplate.queryForObject(
				"select quantityInStock from products where id=(select productId from orderdetails where orderId=?)", Long.class, id);
		assertEquals(90, newQuantityInStock);
		long newQuantityInOrder = super.jdbcTemplate.queryForObject(
				"select quantityInOrder from products where id=(select productId from orderdetails where orderId=?)", Long.class, id);
		assertEquals(90, newQuantityInOrder);
	}

}
