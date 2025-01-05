package ru.gwoll.KursovayaContactManager.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subscription_user_id")
    private User subscribedUser;


    public Subscription(User user, User subscribedUser) {
        this.user = user;
        this.subscribedUser = subscribedUser;
    }


    /**
     * Конструктор по умолчанию для создания пустой подписки.
     */
    public Subscription() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getSubscribedUser() {
        return subscribedUser;
    }

    public void setSubscribedUser(User subscribedUser) {
        this.subscribedUser = subscribedUser;
    }
}