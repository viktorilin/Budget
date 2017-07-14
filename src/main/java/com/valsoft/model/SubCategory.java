package com.valsoft.model;

import javax.persistence.*;

/**
 * Created by sanya on 09.07.2017.
 */
@Entity
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public SubCategory() {
	}

	public SubCategory( Category category, String name) {
		this.name = name;
		this.category = category;
	}

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "SubCategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
