package nl.novi.first_demo.service;

import nl.novi.first_demo.dto.OrderRegelDto;
import nl.novi.first_demo.dto.OrderRequestDto;
import nl.novi.first_demo.exeption.RecordNotFoundException;
import nl.novi.first_demo.model.Order;
import nl.novi.first_demo.model.OrderRegel;
import nl.novi.first_demo.model.Supplier;
import nl.novi.first_demo.repository.CustomerRepository;
import nl.novi.first_demo.repository.OrderRepository;
import nl.novi.first_demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }
    }

    public Long addOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setDate(orderRequestDto.getDate());
        order.setCustomerId(orderRequestDto.getCustomerId());
        List<OrderRegel> orderRegels = new ArrayList<>();
        for(OrderRegelDto orderRegelDto: orderRequestDto.getOrderRegelDtos()) {
            OrderRegel orderRegel = new OrderRegel();
            orderRegel.setQuantity(orderRegelDto.getQuantity());
            orderRegel.setProduct(productRepository.findById(orderRegelDto.getProductId()).orElse(null));
            orderRegels.add(orderRegel);
        }
        order.setOrderRegels(orderRegels);

        Order newOrder = orderRepository.save(order);
        return newOrder.getId();
    }

    public void deleteOrder(long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }
    }

    public void updateOrder(long id, Order order) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order orderInDb = optionalOrder.get();
            orderInDb.setCustomerName(order.getCustomerName());
            orderInDb.setCustomerId(order.getCustomerId());
            orderInDb.setDate(order.getDate());
            orderInDb.setDelivered(order.isDelivered());
            orderInDb.setPaid(order.isPaid());
            orderRepository.save(orderInDb);
        } else {
            throw new RecordNotFoundException("Order with id " + id + " not found.");
        }


    }
}