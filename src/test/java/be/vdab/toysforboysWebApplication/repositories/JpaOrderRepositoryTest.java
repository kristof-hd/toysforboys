package be.vdab.toysforboysWebApplication.repositories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.toysforboysWebApplication.entities.Order;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JpaOrderRepository.class)
@Sql("/insertCountry.sql")
@Sql("/insertCustomer.sql")
@Sql("/insertOrder.sql")
public class JpaOrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final String ORDERS = "orders";
	@Autowired
	private JpaOrderRepository repository;
	@Autowired
	private EntityManager manager;

	private long idVanTestOrder() {
		return super.jdbcTemplate.queryForObject("select id from orders where orderDate='2000-01-01'", Long.class);
	}

	@Test
	public void read() {
		Order order = repository.read(idVanTestOrder()).get();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse("2000-01-01", formatter);
		
		assertEquals(date, order.getOrderDate());
		assertEquals("test", order.getCustomer().getName());
		assertEquals(0, BigDecimal.TEN.compareTo(order.getTotalValue()));
	}

	@Test
	public void findAllUnshippedOrder() {
		List<Order> orders = repository.findAllUnshippedOrders();
		manager.clear();
		assertEquals(super.countRowsInTableWhere(ORDERS, "status not in ('SHIPPED', 'CANCELLED')"), orders.size());
		//where o.status not in ('SHIPPED', 'CANCELLED')
//String vorigeNaam = "";
//for (Album album : albums) {
//assertTrue(album.getNaam().compareToIgnoreCase(vorigeNaam) >= 0);
//vorigeNaam = album.getNaam();
//System.out.println(album.getNaam() + ' ' + album.getArtiest().getNaam());
//}
	}
}