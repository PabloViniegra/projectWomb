package net.juanxxiii.womb.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "favourites")
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfavourite")
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private Users user;

    @Column(name = "date")
    private String date;

    @OneToMany(mappedBy = "idfavourite")
    List<FavouritesWomb> favouritesWombs;
}
