package dev.anindya.helloworld.database;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NoteEntity {

    private int id;
    private Date date;
    private String noteText;
}
