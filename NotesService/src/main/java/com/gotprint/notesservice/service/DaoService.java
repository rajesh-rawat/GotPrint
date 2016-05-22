package com.gotprint.notesservice.service;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Transaction;

import com.gotprint.notesservice.dao.NotesDao;
import com.gotprint.notesservice.dao.UserDao;
import com.gotprint.notesservice.domain.object.Note;
import com.gotprint.notesservice.domain.object.Persistable;
import com.gotprint.notesservice.domain.object.User;

public class DaoService {

	public List<Note> getNotesForUser(String email){
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		List<Note> list = dao.getNotesForUser(email);
		dao.closeCurrentSession();
		return list;
	}

	public Note getNoteByTitle(String title){
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Note note = dao.getNoteByTitle(title);
		dao.closeCurrentSession();
		return note;
	}
	
	public Note getNoteById(long id){
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Note note = (Note) dao.findById(id, Note.class);
		dao.closeCurrentSession();
		return note;
	}

	public User findUserByEmail(String email){
		UserDao dao = new UserDao();
		dao.openCurrentSession();
		User user = dao.findUserByEmail(email);
		dao.closeCurrentSession();
		return user;
	}
		
	public void addNote(Note note){
		User user = findUserByEmail(note.getUser().getEmail());
		note.setUser(user);
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Transaction tx = dao.createTransaction();
				
		note.setCreateTime(new Timestamp(System.currentTimeMillis()));
		note.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		dao.persist((Persistable)note);
		dao.commitTransaction(tx);
		dao.closeCurrentSession();
	}
	
	public void updateNote(Note note){
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Transaction tx = dao.createTransaction();
		
		Note dbNote = dao.getNoteByTitle(note.getTitle());
		dbNote.setNote(note.getNote());
		dbNote.setTitle(note.getTitle());
		dbNote.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		dao.update((Persistable)dbNote);
		dao.commitTransaction(tx);
		dao.closeCurrentSession();
	}
	
	public void deleteNote(Note note){
		NotesDao dao = new NotesDao();
		dao.openCurrentSession();
		Transaction tx = dao.createTransaction();
		
		Note dbNote = dao.getNoteByTitle(note.getTitle());
		
		dao.delete((Persistable)dbNote);
		dao.commitTransaction(tx);
		dao.closeCurrentSession();
	}
}
