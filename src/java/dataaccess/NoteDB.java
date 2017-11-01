package dataaccess;

import domainmodel.Notes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class NoteDB {

    public int insert(Notes notes) throws NotesDBException {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.persist(notes);
            trans.commit();
            return 1;

        } catch (Exception ex) {

            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + notes.toString(), ex);
            throw new NotesDBException("Error inserting note");

        } finally {
            em.close();
        }
    }

    public int delete(Notes notes) throws NotesDBException {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.remove(em.merge(notes));
            trans.commit();
            return 1;

        } catch (Exception ex) {

            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + notes.toString(), ex);
            throw new NotesDBException("Error deleting note");

        } finally {
            em.close();
        }
    }

    public int update(Notes notes) throws NotesDBException {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {

            trans.begin();
            em.merge(notes);
            trans.commit();
            return 1;

        } catch (Exception ex) {

            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + notes.toString(), ex);
            throw new NotesDBException("Error updating user");

        } finally {
            em.close();
        }
    }

    public List<Notes> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            List<Notes> notes = em.createNamedQuery("Notes.findAll", Notes.class).getResultList();
            return notes;

        } catch (Exception ex) {

            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting Notes");

        } finally {
            em.close();
        }
    }

    public Notes getNote(int noteId) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {

            Notes notes = em.find(Notes.class, noteId);
            return notes;

        } catch (Exception ex) {

            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting Notes");

        } finally {
            em.close();
        }
    }

    public static java.sql.Date toSQlDate(java.util.Date date) {

        long javaDateMilisec = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(javaDateMilisec);
        return sqlDate;
    }

    public static java.util.Date toJavaDate(java.sql.Date date) {

        long sqlDateMilisec = date.getTime();
        java.util.Date javaDate = new java.util.Date(sqlDateMilisec);

        return javaDate;
    }
}
