package net.juanxxiii.womb.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "favouriteswomb")
public class FavouritesWomb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idwombfavourite")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private Users user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idwomb", referencedColumnName = "idwomb")
    private Womb womb;

    @Column(name = "date")
    private String date;
}
