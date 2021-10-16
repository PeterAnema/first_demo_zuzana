package nl.novi.first_demo.model;

import javax.persistence.*;

@Entity
@Table(name = "products")

public class Product {

    // attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String kind;
    private String packaging;
    private String description;
    private String photo;
    private double price = 0;
    private int vatPercentage = 0;

    // constructor not required for Springboot Hibernate
    public Product() {
    }
    public Product(String name,
                   String kind,
                   String packaging,
                   String description,
                   String photo,
                   double price,
                   int vatPercentage) {
        this.name = name;
        this.kind = kind;
        this.packaging = packaging;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.vatPercentage = vatPercentage;
    }

    // getters setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(int vatPercentage) {
        this.vatPercentage = vatPercentage;
    }
}
