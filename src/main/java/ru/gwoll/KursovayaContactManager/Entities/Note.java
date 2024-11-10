package ru.gwoll.KursovayaContactManager.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private LocalDateTime creationDate;
    @OneToOne(mappedBy = "note", cascade = CascadeType.ALL)
    private Contact contact;

    public Note(LocalDateTime creationDate, String text) {
        this.creationDate = creationDate;
        this.text = text;
    }

    public Note() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Contact getContact() {
        return null;
    }


}
