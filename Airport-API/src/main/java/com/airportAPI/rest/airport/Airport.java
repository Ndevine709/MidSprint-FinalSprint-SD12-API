package com.airportAPI.rest.airport;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.airportAPI.rest.city.City;


@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    // Constructors
    public Airport() {}

    public Airport(String name, String code, City city){
        this.name = name;
        this.code = code;
        this.city = city;
    }

    // Getters and Setters
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public City getCity(){
        return city;
    }

    public void setCity(City city){
        this.city = city;
    }
}
