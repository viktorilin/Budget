package com.valsoft.model;

import javax.persistence.*;

/**
 * Created by Asus on 18.06.2017.
 */
@Entity
@Table(name = "BUDGET_USER")
public class BudgetUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUDGET_USER_ID")
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "BUDGET")
    @JoinColumn(name = "BUDGET_ID")
    private Budget budget;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER")
    @JoinColumn(name = "USER_ID")
    private User user;

    public BudgetUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BudgetUser{" +
                "id=" + id +
                ", budget=" + budget +
                ", user=" + user +
                '}';
    }
}
