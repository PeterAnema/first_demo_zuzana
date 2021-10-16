package nl.novi.first_demo.repository;

import nl.novi.first_demo.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByCustomerId(long customerId);

    List<Order> findAllByPaid(boolean paid);
    List<Order> findAllByDelivered(boolean delvered);
    List<Order> findAllByPaidAndDelivered(boolean paid, boolean delvered);

}
