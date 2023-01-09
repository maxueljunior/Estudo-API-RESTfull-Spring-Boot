package br.com.erudio.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "addres"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	private String firstName;
	private String lastName;
	private String addres;
	
	//@JsonIgnore
	private String gender;
	
	public PersonVO(Long key, String firstName, String lastName, String addres, String gender) {
		this.key = key;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addres = addres;
		this.gender = gender;
	}
	
	public PersonVO() {
		
	}
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddres() {
		return addres;
	}

	public String getGender() {
		return gender;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addres, firstName, gender, key, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVO other = (PersonVO) obj;
		return Objects.equals(addres, other.addres) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(gender, other.gender) && Objects.equals(key, other.key)
				&& Objects.equals(lastName, other.lastName);
	}

	
	
}
