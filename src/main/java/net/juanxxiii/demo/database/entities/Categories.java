package net.juanxxiii.demo.database.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Categories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "idcategory")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
