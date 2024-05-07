package Game.SoundClips;

//imports
import city.cs.engine.SoundClip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Music class manages the background music for different levels.
 * Extends Thread to allow concurrent execution of music playback.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class Music extends Thread{
    /** ArrayList to store the game background music. */
    private static final ArrayList <SoundClip> gameMusic = new ArrayList<>();

    /** The volume of the music. */
    private float volume = 1.0f;


    /**
     * Constructs a Music object and initializes the game background music.
     */
    public Music(){
        try{
            //background music for level 1: When The Stars Meet The Sea by BigRicePiano, The Soul of Wind
            gameMusic.add(new SoundClip("data/Sound/level1.wav"));

            //background music for level 2: Love And Peace by Deep In A Dream, Dark Winter
            gameMusic.add(new SoundClip("data/Sound/level2.wav"));

            //background music for level 3: Starry Night by Theo Aabel, møndberg
            gameMusic.add(new SoundClip("data/Sound/level3.wav"));

            //background music for level4: Meteor Shower by Theo Aabel, møndberg
            gameMusic.add(new SoundClip("data/Sound/level4.wav"));

            //setting the volume of the music
            updateVolume(volume);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

    }


    /**
     * Retrieves the current volume level.
     * @return The current volume level.
     */
    public float getVol() {
        return volume;
    }


    /**
     * Updates the volume level.
     * @param volume The new volume level.
     */
    private void updateVolume(float volume) {
        // Ensure volume is within the allowable range (-80.0 dB to 6.0206 dB)
        volume = Math.max(volume, -80.0f);
        volume = Math.min(volume, 6.0206f);

        this.volume = volume;
    }


    /**
     * Retrieves the list of game background music.
     * @return The list of game background music.
     */
    public ArrayList<SoundClip> getGameMusic (){
        return gameMusic;
    }


    /**
     * Stops all background music playback.
     */
    public void mute(){
        gameMusic.get(0).stop();
        gameMusic.get(1).stop();
        gameMusic.get(2).stop();
        gameMusic.get(3).stop();
    }

}
