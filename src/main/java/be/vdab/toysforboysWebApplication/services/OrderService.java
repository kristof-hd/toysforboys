package be.vdab.toysforboysWebApplication.services;

import java.util.List;
import java.util.Optional;

import be.vdab.toysforboysWebApplication.entities.Order;
import be.vdab.toysforboysWebApplication.enums.Status;

public interface OrderService {
	List<Order> findAll();
	Optional<Order> read(long id); 
	//void setStatus(long id, Status status); 
}
