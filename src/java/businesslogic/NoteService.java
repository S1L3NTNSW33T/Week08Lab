package businesslogic;

import dataaccess.NoteDB;
import domainmodel.Notes;
import java.util.List;

public class NoteService {

    private NoteDB noteDB;

    public NoteService() {
        noteDB = new NoteDB();
    }

    public Notes get(int noteId) throws Exception {
        return noteDB.getNote(noteId);
    }

    public List<Notes> getAll() throws Exception {
        return noteDB.getAll();
    }

    public int update(int noteId, String contents) throws Exception {

        java.util.Date date = new java.util.Date();
        Notes notes = new Notes(noteId, dataaccess.NoteDB.toSQlDate(date), contents);
        return noteDB.update(notes);
    }

    public int delete(int noteId) throws Exception {

        Notes deletedNote = noteDB.getNote(noteId);
        return noteDB.delete(deletedNote);
    }

    public int insert(String contents) throws Exception {

        java.util.Date date = new java.util.Date();
        Notes note = new Notes(-1, date, contents);
        return noteDB.insert(note);
    }
}
