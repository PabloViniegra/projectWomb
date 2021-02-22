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

    @Column(name = "idfavourite")
    private int idfavourite;

    @Column(name = "idwomb")
    private int idwomb;

    @Column(name = "date")
    private String date;
}
