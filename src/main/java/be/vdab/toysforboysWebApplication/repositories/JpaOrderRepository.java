package be.vdab.toysforboysWebApplication.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import be.vdab.toysforboysWebApplication.entities.Order;

@Repository
class JpaOrderRepository implements OrderRepository {
	
	private final EntityManager manager;
	
	JpaOrderRepository(EntityManager manager) {
		this.manager=manager;
	}
	
	@Override
	public List<Order> findAll() {
		return manager.createNamedQuery("Order.findAll", Order.class)
				.setHint("javax.persistence.loadgraph",
				manager.createEntityGraph(Order.WITH_CUSTOMER))
				.getResultList();	} 
	
	@Override
	public Optional<Order> read(long id) {
		return Optional.ofNullable(manager.find(Order.class, id));
	}
	
}
