package com.app.productservice.modals;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModal {


    private String title;
    private String description;
    private double price;
    private String image;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn
    private Category category;



}
