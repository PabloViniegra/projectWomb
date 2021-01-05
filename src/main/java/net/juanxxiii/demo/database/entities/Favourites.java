package net.juanxxiii.demo.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "favourites")
    List<FavouritesWomb> favouritesWombs;
}
