package net.juanxxiii.womb.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @org.springframework.data.annotation.Transient
    @ManyToOne(optional = false)
    @JoinColumn(name = "idcategory", referencedColumnName = "idcategory")
    private Categories category;

    @org.springframework.data.annotation.Transient
    @ManyToOne(optional = false)
    @JoinColumn(name = "idbrand", referencedColumnName = "idbrand")
    private Brand brand;


}
