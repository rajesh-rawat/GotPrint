Description:
NotesService Provides CRUD (Create, Read, Update, & Delete) operations over Notes.

Technical Specifications:
1) Jersey is used to build REST Web Services
2) Hibernate is the ORM used

USAGE:
1) To Read a Note:
URL: http://<FQDN>/NotesService/rest/notes/read/<Note ID>

2) To Create a Note for the User
URL : http://<FQDN>/NotesService/rest/notes/save
JSON Payload:
{
    "email": "testuser1@email.com",
    "title": "Test Title",
	"note" : "Test Note"
}

3) To Update a Note for the user
URL : http://<FQDN>/NotesService/rest/notes/update
JSON Payload:
{
    "email": "testuser1@email.com",
    "title": "Test Title",
	"note" : "Test Note"
}

4) To delete a Note for the user
URL : http://<FQDN>/NotesService/rest/notes/delete
JSON Payload:
{
    "email": "testuser1@email.com",
    "title": "Test Title",
	"note" : "Test Note"
}

Validations:
1) Title can not be more than 50 characters
2) Note can not be more than 1000 characters
3) Basic email validation
4) A user should not be allowed to Create/Update/Delete other users notes. He can read other user's notes though.

Assumptions:
1) Note Title is unique
