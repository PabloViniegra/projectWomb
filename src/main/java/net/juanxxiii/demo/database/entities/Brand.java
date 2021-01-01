package net.juanxxiii.demo.database.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "idbrand")
    private int id;

    @Column(name = "name")
    private String name;

    @org.springframework.data.annotation.Transient
    @OneToMany(targetEntity = Products.class, mappedBy = "idproduct", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Products> product;


}
