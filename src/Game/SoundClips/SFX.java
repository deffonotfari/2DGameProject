package Game.SoundClips;

//imports
import city.cs.engine.SoundClip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashMap;

/**
 * The SFX class manages the sound effects in the game.
 *
 * @author Fariha Alam, alamfariha560@gmail.com
 * @version 1.0
 * @since 17-04-2024
 */
public class SFX {
    /** HashMap to store the sound effects. */
    private HashMap<String, SoundClip> soundEffects;
    private SoundClip cannonExplosion, eatingCookie, coinCollect, playerDead, boxCrash, gunShot, heartPickUp;


    /**
     * Constructs an SFX object and initializes the sound effects.
     */
    public SFX(){
        try{
            soundEffects = new HashMap<>();

            //creating the sound effects
            cannonExplosion = new SoundClip("data/Sound/CannonExplosion.wav");
            eatingCookie = new SoundClip("data/Sound/eatingCookie.wav");
            coinCollect = new SoundClip("data/Sound/coinCollect.wav");
            playerDead = new SoundClip("data/Sound/playerDeath.wav");
            boxCrash = new SoundClip("data/Sound/boxCrash.wav");
            gunShot = new SoundClip("data/Sound/gunShot.wav");
            heartPickUp = new SoundClip("data/Sound/heartPickUp.wav");
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.out.println(e);
        }

        // Populate the sound effects HashMap
        soundEffects.put("cannonExplosion", cannonExplosion);
        soundEffects.put("cookie", eatingCookie);
        soundEffects.put("coin", coinCollect);
        soundEffects.put("death", playerDead);
        soundEffects.put("box", boxCrash);
        soundEffects.put("gunShot", gunShot);
        soundEffects.put("heart", heartPickUp);
    }


    /**
     * Plays the specified sound effect at the given volume.
     * @param key The key corresponding to the sound effect.
     * @param volume The volume level for the sound effect.
     */
    public void getSoundEffect(String key, float volume){
        soundEffects.get(key).setVolume(volume);
        soundEffects.get(key).play();
    }
}
