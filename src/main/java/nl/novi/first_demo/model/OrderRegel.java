package nl.novi.first_demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import static nl.novi.first_demo.util.Rounding.roundTo;

@Entity
@Table(name = "order_regels")
public class OrderRegel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Product product;

    private int quantity;

    @ManyToOne
    @JsonBackReference
    private Order order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    // more methodes

    @JsonGetter("SubTotalExclVat")
    public double calculateSubTotalExclVat() {
        return roundTo(quantity * product.getPrice(), 2);
    }

    @JsonGetter("SubTotalVatPercentage")
    public double calculateSubTotalVatPercentage() {
        return product.getVatPercentage();
    }

    @JsonGetter("SubTotalVat")
    public double calculateSubTotalVat() {
        return roundTo(quantity * product.getPrice() * product.getVatPercentage() / 100, 2);
    }

    @JsonGetter("SubTotalInclVat")
    public double calculateSubTotalInclVat() {
        return calculateSubTotalExclVat() + calculateSubTotalVat();
    }

}
