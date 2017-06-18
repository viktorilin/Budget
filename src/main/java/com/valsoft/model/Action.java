package com.valsoft.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Asus on 18.06.2017.
 */
@Entity
@Table(name = "ACTION")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTION_ID")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "BUDGET_USER")
    @JoinColumn(name = "BUDGET_USER_ID")
    private BudgetUser budgetUser;

    @Size(min = 3, max = 50)
    @Column(name = "TYPE")
    private String type;

    public Action() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BudgetUser getBudgetUser() {
        return budgetUser;
    }

    public void setBudgetUser(BudgetUser budgetUser) {
        this.budgetUser = budgetUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", budgetUser=" + budgetUser +
                ", type='" + type + '\'' +
                '}';
    }

}
