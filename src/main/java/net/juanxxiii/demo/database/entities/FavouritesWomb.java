package net.juanxxiii.demo.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "favouriteswomb")
public class FavouritesWomb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idwombfavourite")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idfavourite")
    private Favourites favourites;

    @ManyToOne
    @JoinColumn(name = "idwomb")
    private Womb womb;

    @Column(name = "date")
    private String date;
}
