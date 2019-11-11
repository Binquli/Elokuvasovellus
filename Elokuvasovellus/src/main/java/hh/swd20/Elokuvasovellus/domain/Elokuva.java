package hh.swd20.Elokuvasovellus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Elokuva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String director;
	private int year;
	private String writer;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "categoryid")
	private Category category;

	public Elokuva() {
		super();	
		this.id = null;
		this.title = null;
		this.director = null;
		this.writer = null;
		this.year = 0;
	}
	public Elokuva(String title, String director, String writer, int year, Category category) {
		super();
		this.title = title;
		this.director = director;
		this.writer = writer;
		this.year = year;
		this.category = category;
	}
	public Elokuva(Long id, String title, String director, String writer, int year, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.writer = writer;
		this.year = year;
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDirector() {
		return director;
	}
	public int getYear() {
		return year;
	}
	public String getWriter() {
		return writer;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Category getCategory() {
		return category;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", writer=" + writer + "]";
	}

	

}
