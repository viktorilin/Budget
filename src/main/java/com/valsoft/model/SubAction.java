package com.valsoft.model;

import com.sun.javafx.beans.IDProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by Asus on 18.06.2017.
 */
@Entity
@Table(name = "SUB_ACTION")
public class SubAction {
    @Id
    @GeneratedValue
    @Column(name = "SUB_ACTION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTION_ID")
    private Action action;

    @Column
    private Double amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PICTURE")
    private String picture;


    public SubAction(){}

    public SubAction(Action action, Double amount, String description, String picture) {
        this.action = action;
        this.amount = amount;
        this.description = description;
        this.picture = picture;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "SubAction{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
