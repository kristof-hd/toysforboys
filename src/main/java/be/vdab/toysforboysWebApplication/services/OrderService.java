package be.vdab.toysforboysWebApplication.services;

import java.util.List;
import java.util.Optional;

import be.vdab.toysforboysWebApplication.entities.Order;

public interface OrderService {
	List<Order> findAll();
	Optional<Order> read(long id); 
}
