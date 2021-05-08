package com.hackdead.wheelmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false,length = 50)
    private String name;
    @Column(name="description",nullable = false)
    private String description;
    @Column(name="image_url",nullable = false)
    private String imageUrl;
    @Temporal(TemporalType.DATE)
    @Column(name="start_date", nullable = false)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name="end_date", nullable = false)
    private Date endDate;
    @Column(name="offer_price", nullable = false)
    private Double offerPrice;
}
