package dev.anindya.helloworld.testdata;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dev.anindya.helloworld.database.NoteEntity;

/**
 * Provides sample notes for testing.
 */
public class SampleNotesProvider {

    private static final String NOTE_1 = "Hello";
    private static final String NOTE_2 = "Hello\nWorld";
    private static final String NOTE_3 = "Lorem ipsum dolor sit amet, consectetur adipiscing "
            + "elit. In convallis magna id felis egestas malesuada. Donec rutrum tristique purus"
            + " quis efficitur. Cras viverra libero leo, at tempus dolor tempor tempus. Curabitur "
            + "elementum enim eu feugiat tincidunt. Maecenas sit amet magna sed magna ullamcorper "
            + "dictum et et libero. Phasellus varius mattis ultricies. Donec tristique nisi"
            + " elementum mi sagittis consequat.\n"
            + "\n"
            + "Suspendisse vitae ornare elit. In ultrices egestas porttitor. Etiam malesuada "
            + "elementum feugiat. Donec tortor nisl, efficitur ut ultrices et, mattis sed nunc."
            + " Cras imperdiet sem in lacus iaculis, non cursus tortor lobortis. Pellentesque "
            + "vehicula ante sed lectus commodo mollis. Donec egestas, lorem a placerat mattis, "
            + "nibh ipsum laoreet neque, in congue dolor elit volutpat lorem. Praesent at augue "
            + "iaculis, tempor augue id, dignissim neque.\n"
            + "\n"
            + "Praesent bibendum pretium ante eu commodo. Pellentesque habitant morbi tristique "
            + "senectus et netus et malesuada fames ac turpis egestas. Duis non maximus ipsum. "
            + "Etiam mollis urna in dolor dapibus, eu condimentum magna dignissim. Lorem ipsum"
            + " dolor sit amet, consectetur adipiscing elit. Nunc augue tellus, interdum eu elit "
            + "at, cursus aliquet nisl. Suspendisse in nunc ac enim tincidunt semper. Maecenas at "
            + "ullamcorper metus. Morbi quis dapibus metus. Vivamus a odio est. Vivamus ultricies, "
            + "lorem eget volutpat mollis, arcu velit commodo elit, sit amet ultricies lacus urna "
            + "vitae leo. Etiam in est malesuada, elementum nisl at, aliquet tortor. Proin commodo"
            + " posuere arcu, eu rhoncus augue suscipit ac. Donec ultricies finibus eleifend. "
            + "Quisque facilisis elit elit, vel porttitor tortor aliquet sed.";

    /**
     * Return a list of notes.
     *
     * @return a list of notes.
     */
    public static List<NoteEntity> getSampleNotes() {
        final NoteEntity noteEntity1 = new NoteEntity(1, new Date(), NOTE_1);
        final NoteEntity noteEntity2 = new NoteEntity(2, new Date(), NOTE_2);
        final NoteEntity noteEntity3 = new NoteEntity(3, new Date(), NOTE_3);
        return Arrays.asList(noteEntity1, noteEntity2, noteEntity3);
    }
}
