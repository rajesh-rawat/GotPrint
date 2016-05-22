package com.gotprint.notesservice.service;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Transaction;

import com.gotprint.notesservice.dao.NotesDao;
import com.gotprint.notesservice.dao.UserDao;
import com.gotprint.notesservice.domain.object.Note;
import com.gotprint.notesservice.domain.object.Persistable;
import com.gotprint.notesservice.domain.object.User;
import com.gotprint.notesservice.generic.exception.BusinessException;

/**
 * @author Rajesh Rawat
 *
 * Service Layer which talks to DAO's to interact with database
 */
public class DaoService {
	
	/**
	 * Find Notes for User
	 * 
	 * @param email
	 * @return
	 */
	public List<Note> getNotesForUser(String email) {
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		List<Note> list = dao.getNotesForUser(email);
		dao.closeCurrentSession();
		return list;
	}

	/**
	 * Find Note By Title
	 * @param title
	 * @return
	 */
	public Note getNoteByTitle(String title) {
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Note note = dao.getNoteByTitle(title);
		dao.closeCurrentSession();
		return note;
	}

	/**
	 * Find Note By Id
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public Note getNoteById(long id) throws BusinessException {
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Note note = (Note) dao.findById(id, Note.class);
		if (note == null)
			throw new BusinessException("Invalid Id");
		dao.closeCurrentSession();
		return note;
	}

	/**
	 * Find User By Email
	 * 
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email) {
		UserDao dao = new UserDao();
		dao.openCurrentSession();
		User user = dao.findUserByEmail(email);
		dao.closeCurrentSession();
		return user;
	}

	/**
	 * Create a Note
	 * 
	 * @param note
	 * @throws BusinessException
	 */
	public void addNote(Note note) throws BusinessException {
		User user = findUserByEmail(note.getUser().getEmail());
		if (user == null)
			throw new BusinessException("Invalid Email Id");
		note.setUser(user);
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Transaction tx = dao.createTransaction();

		Note temp = dao.getNoteByTitle(note.getTitle());
		if (temp != null)
			throw new BusinessException("Note with this title already exists");

		note.setCreateTime(new Timestamp(System.currentTimeMillis()));
		note.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		dao.persist((Persistable) note);
		dao.commitTransaction(tx);
		dao.closeCurrentSession();
	}

	/**
	 * Update a Note
	 * 
	 * @param note
	 * @throws BusinessException
	 */
	public void updateNote(Note note) throws BusinessException {
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Transaction tx = dao.createTransaction();

		Note dbNote = dao.getNoteByTitle(note.getTitle());
		if (!dbNote.getUser().getEmail().equalsIgnoreCase(note.getUser().getEmail())) {
			throw new BusinessException("User " + note.getUser().getEmail()
					+ " does not have priviliges to update Note with title " + note.getTitle());
		}
		dbNote.setNote(note.getNote());
		dbNote.setTitle(note.getTitle());
		dbNote.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));

		dao.update((Persistable) dbNote);
		dao.commitTransaction(tx);
		dao.closeCurrentSession();
	}

	/**
	 * Delete a Note
	 * 
	 * @param note
	 * @throws BusinessException
	 */
	public void deleteNote(Note note) throws BusinessException {
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Transaction tx = dao.createTransaction();

		Note dbNote = dao.getNoteByTitle(note.getTitle());
		if (!dbNote.getUser().getEmail().equalsIgnoreCase(note.getUser().getEmail())) {
			throw new BusinessException("User " + note.getUser().getEmail()
					+ " does not have priviliges to delete Note with title " + note.getTitle());
		}

		dao.delete((Persistable) dbNote);
		dao.commitTransaction(tx);
		dao.closeCurrentSession();
	}
}
