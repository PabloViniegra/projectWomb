package net.juanxxiii.demo.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @org.springframework.data.annotation.Transient
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
    private Categories category;
}
