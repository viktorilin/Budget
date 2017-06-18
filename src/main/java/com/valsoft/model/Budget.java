package com.valsoft.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Asus on 18.06.2017.
 */
@Entity
@Table(name = "BUDGET")
public class Budget {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "BUDGET_ID")
    private Long id;

    @Size(min=3, max=50)
    @Column(name = "NAME", nullable = false)
    private String name;

    @Size(min=3, max=150)
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    //admin

    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(name = "CREATION_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate creationDate;

}
