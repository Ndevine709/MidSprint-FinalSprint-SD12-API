package com.airportAPI.rest.passenger;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.airportAPI.rest.aircraft.Aircraft;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Passenger {

    @Id
    @SequenceGenerator(name = "passenger_sequence", sequenceName = "passenger_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "passenger_sequence")
    private long id;

    private Calendar birthday;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "passenger_aircraft", joinColumns = @JoinColumn(name = "passenger_id"), inverseJoinColumns = @JoinColumn(name = "aircraft_id"))
    private Set<Aircraft> flights = new HashSet<>();

    // ————— Getters & Setters —————

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Aircraft> getFlights() {
        return flights;
    }

    public void setFlights(Set<Aircraft> flights) {
        this.flights = flights;
    }
}
