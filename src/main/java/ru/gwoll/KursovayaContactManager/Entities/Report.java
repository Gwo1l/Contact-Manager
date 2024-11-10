package ru.gwoll.KursovayaContactManager.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Status status;
    private String content;
    private Long contactCount;
    private Long contactCountTime;
    private Long countriesTime;
    private Long totalTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reports_and_countries",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countrySet = new HashSet<>();

    public Long getContactCount() {
        return contactCount;
    }

    public void setContactCount(Long contactCount) {
        this.contactCount = contactCount;
    }

    public Long getContactCountTime() {
        return contactCountTime;
    }

    public void setContactCountTime(Long contactCountTime) {
        this.contactCountTime = contactCountTime;
    }

    public Long getCountriesTime() {
        return countriesTime;
    }

    public void setCountriesTime(Long countriesTime) {
        this.countriesTime = countriesTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Set<Country> getCountrySet() {
        return countrySet;
    }

    public void setCountrySet(Set<Country> countrySet) {
        this.countrySet = countrySet;
    }

    public Report(String content) {
        this.status = Status.CREATED;
        this.content = content;
    }
    public Report() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public enum Status {
        CREATED, COMPLETED, ERROR
    }
}
