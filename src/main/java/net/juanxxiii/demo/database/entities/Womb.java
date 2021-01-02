package net.juanxxiii.demo.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name = "womb")
public class Womb implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idwomb")
    private int id;

    @Column(name = "review")
    private String review;

    @Column(name = "score")
    private float score;

    @Column(name = "date")
    private String date;

    @Transient
    @ManyToOne(optional = false)
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private Users user;

    @Transient
    @ManyToOne(optional = false)
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
    private Products product;
}
