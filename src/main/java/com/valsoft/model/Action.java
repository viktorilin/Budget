package com.valsoft.model;

import javafx.util.converter.LocalDateStringConverter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BUDGET_USER_ID")
    private BudgetUser budgetUser;

    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy")
    @Column(nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate createDate;

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private SubCategory subCategory;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "action", orphanRemoval = true,  cascade = CascadeType.ALL)
    @OrderBy("id DESC")
    private Set<SubAction> subActions = new HashSet<>();

    public Action() {
    }

    public Set<SubAction> getSubActions() {
        return subActions;
    }

    public void setSubActions(Set<SubAction> subActions) {
        this.subActions = subActions;
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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", subCategory=" + subCategory +
                ", subActions=" + subActions +
                '}';
    }
}
