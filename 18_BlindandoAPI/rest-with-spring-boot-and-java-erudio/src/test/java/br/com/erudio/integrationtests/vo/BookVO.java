package br.com.erudio.integrationtests.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

public class BookVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String author;
	private Date launchDate;
	private Double price;
	private String title;
	
	public BookVO() {}

	public Long getId() {
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

	public void setId(Long id) {
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
		BookVO other = (BookVO) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id)
				&& Objects.equals(launchDate, other.launchDate) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}

	
}