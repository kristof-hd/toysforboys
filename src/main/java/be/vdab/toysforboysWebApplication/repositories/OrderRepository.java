package be.vdab.toysforboysWebApplication.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.toysforboysWebApplication.entities.Order;

public interface OrderRepository {

	List<Order> findAllUnshippedOrders();
	Optional<Order> read(long id);

}