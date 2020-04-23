package dev.anindya.helloworld.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(tableName = "notes")
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Date date;
    private String noteText;

    /**
     * Creates a {@link NoteEntity}.
     *
     * @param date     The date.
     * @param noteText The note text.
     */
    @Ignore
    public NoteEntity(Date date, String noteText) {
        this.date = date;
        this.noteText = noteText;
    }
}
