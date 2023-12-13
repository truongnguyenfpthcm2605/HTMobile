package com.poly.app.enity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date createday;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "id")
    private Product product;
}
