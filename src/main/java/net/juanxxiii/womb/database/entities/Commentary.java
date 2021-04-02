package net.juanxxiii.womb.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@Entity
@Table(name = "commentaries")
public class Commentary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcommentary")
    private int id;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "date")
    private String date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private Users user;


    @ManyToOne(optional = false)
    @JoinColumn(name = "idwomb", referencedColumnName = "idwomb")
    private Womb womb;
}
