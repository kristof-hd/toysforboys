package be.vdab.toysforboysWebApplication.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import be.vdab.toysforboysWebApplication.entities.Order;
import be.vdab.toysforboysWebApplication.enums.Status;

public interface OrderService {
	List<Order> findAllUnshippedOrders();
	Optional<Order> read(long id); 
	Set<Long> getUnshippableOrders(); 
	void setStatus(long id, Status status); 
}
