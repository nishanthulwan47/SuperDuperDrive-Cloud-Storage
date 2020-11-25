package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note, Integer userid, Integer noteid) {
        return noteMapper.insert(note, userid, noteid);
    }

    public List<Note> getAllNotes() {
        return this.noteMapper.getAllNotes();
    }

    public List<Note> getAllNotesByUserId(Integer userId) {
        return this.noteMapper.getAllNoteByUserId(userId);
    }

    public List<Note> getNoteByNoteId(Integer noteId) {
        return this.noteMapper.getNoteByNoteId(noteId);
    }

    public void deleteNote(Integer id) {
        this.noteMapper.delete(id);
    }

    public void editNote(Note note) {
        this.noteMapper.update(note);
    }
}
