package com.learn.boot.modal;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="user")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@userId")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String userName;
	
	private String password;
	
	private String eMail;
	
	private boolean isActive;
	
	private Timestamp createdDate;
	
	private String createdBy;
	
	private Timestamp updatedDate;
	
	private String updatedBy;
	
	//1 to 1 Unidirectional
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="adhar_id")
	private Adhar adhar;
	
	//1 to 1 Bidirectional
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="pan_id")
	private PanDetail panDetail;
	
	//1 to ~ Unidirectional
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private List<Address> address;
	
	//1 to ~ Bidirectional
	@OneToMany(cascade=CascadeType.ALL)
	//@JoinTable(name="user_has_vechile", joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="vechile_id", referencedColumnName="id")})
	@JoinColumn(name="user_id")
	private List<Vechile> vechile;
	
	//~ to ~ Unidirectional
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_has_books", joinColumns={@JoinColumn(name="user_id")}, inverseJoinColumns={@JoinColumn(name="book_id")})
	private List<Books> books;
	
	//~ to ~ Bidirectional
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_has_bank", joinColumns={@JoinColumn(name="user_id")}, inverseJoinColumns={@JoinColumn(name="bank_id")})
	private List<Bank> bank;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Adhar getAdhar() {
		return adhar;
	}

	public void setAdhar(Adhar adhar) {
		this.adhar = adhar;
	}

	public PanDetail getPanDetail() {
		return panDetail;
	}

	public void setPanDetail(PanDetail panDetail) {
		this.panDetail = panDetail;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Vechile> getVechile() {
		return vechile;
	}

	public void setVechile(List<Vechile> vechile) {
		this.vechile = vechile;
	}

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(List<Books> books) {
		this.books = books;
	}

	public List<Bank> getBank() {
		return bank;
	}

	public void setBank(List<Bank> bank) {
		this.bank = bank;
	}
}