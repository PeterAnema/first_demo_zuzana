package nl.novi.first_demo.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static nl.novi.first_demo.util.Rounding.roundTo;

@Entity
@Table(name = "orders")

public class Order {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonInclude
    Customer customer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern= "yyyy-MM-dd")
    Date date;

    boolean paid = false;
    boolean delivered = false;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    List<OrderRegel> orderRegels = new ArrayList<>();

    //setter en getters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderRegel> getOrderRegels() {
        return orderRegels;
    }

    public void setOrderRegels(List<OrderRegel> orderRegels) {
        this.orderRegels = orderRegels;
    }

    // more methodes

    public void addOrderRegel(OrderRegel orderRegel) {
        this.orderRegels.add(orderRegel);
    }

    @JsonGetter("TotalExclVat")
    public double calculateTotalExclVat() {
        double total = 0;
        for (OrderRegel orderRegel: orderRegels) {
            total += orderRegel.calculateSubTotalExclVat();
        }
        return roundTo(total, 2);
    }

    @JsonGetter("TotalVat")
    public double calculateTotalVat() {
        double total = 0;
        for (OrderRegel orderRegel: orderRegels) {
            total += orderRegel.calculateSubTotalVat();
        }
        return roundTo(total, 2);
    }

    @JsonGetter("TotalInclVat")
    public double calculateTotalInclVat() {
        double total = 0;
        for (OrderRegel orderRegel: orderRegels) {
            total += orderRegel.calculateSubTotalInclVat();
        }
        return roundTo(total, 2);
    }

}
