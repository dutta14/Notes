package dev.anindya.helloworld.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {NoteEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class NotesDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "NotesDatabase.db";

    private static volatile NotesDatabase instance;

    private static final Object LOCK = new Object();

    public abstract NoteDao noteDao();

    /**
     * Returns an instance of the {@link NotesDatabase}.
     *
     * @param context The {@link Context}.
     * @return an instance of the {@link NotesDatabase}.
     */
    public static NotesDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            NotesDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
