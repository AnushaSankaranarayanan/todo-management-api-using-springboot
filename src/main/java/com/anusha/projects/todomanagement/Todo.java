package com.anusha.projects.todomanagement;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class Todo.
 *
 * Represents Todo Entity 
 */
@Entity
public class Todo {

	/** The id. */
	@Id
	private String id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The date. */
	private String date;

	/** The time. */
	private String time;

	/** The status. */
	private String status;

	/**
	 * Instantiates a new todo.
	 */
	public Todo() {
		
	}
	
	/**
	 * Instantiates a new todo.
	 *
	 * @param id the id
	 * @param name the name
	 * @param description the description
	 * @param date the date
	 * @param time the time
	 */
	public Todo(String id, String name, String description, String date, String time) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.time = time;
		this.status = "PENDING"; // always set to PENDING when created.
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", time="
				+ time + ", status=" + status + "]";
	}

}
