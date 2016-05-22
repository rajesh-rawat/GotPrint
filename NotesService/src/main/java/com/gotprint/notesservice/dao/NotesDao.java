package com.gotprint.notesservice.dao;

import java.util.List;

import org.hibernate.Query;

import com.gotprint.notesservice.domain.object.Note;

public class NotesDao extends BaseDao{

	public List<Note> getNotesForUser(String email){
		Query query = getCurrentSession().createQuery("from Note n where n.user.email=:email");
		query.setString("email", email);
		List<Note> notes =(List<Note>)  query.list();
		return notes;
	}
	
	public Note getNoteByTitle(String title){
		Query query = getCurrentSession().createQuery("from Note n where n.title=:title");
		query.setString("title", title);
		List list = query.list();
		Note note = ((list==null || list.isEmpty())?null:(Note) list.get(0));
		if(note!=null) note.getUser();
		return note;
	}
	
}