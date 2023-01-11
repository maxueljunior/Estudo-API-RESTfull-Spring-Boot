package br.com.erudio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String author;

	@Column(name = "launc_date", nullable = false)
	private Date launchDate;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private String title;

	public Book() {

	}

	public Book(Integer id, String author, Date launchDate, Double price, String title) {
		super();
		this.id = id;
		this.author = author;
		this.launchDate = launchDate;
		this.price = price;
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public Double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, launchDate, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}
}
