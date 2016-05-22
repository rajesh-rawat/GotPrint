package com.gotprint.notesservice.domain.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Rajesh Rawat
 *
 */
@Entity
@Table
public class Note extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6877092347517524530L;

	@Id
	@GenericGenerator(name="AUTO" , strategy="increment")
	@GeneratedValue(generator="AUTO")
	private Long id;

	@Column
	private String title;

	@Column
	private String note;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;

		Note obj2 = (Note) obj;
		if (this.id == obj2.getId()) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		return (id + title).hashCode();
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", note=" + note + ", user=" + user + "]";
	}
}
