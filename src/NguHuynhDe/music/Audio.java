package NguHuynhDe.music;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {
    public void playSound(String FileSound,int NumLoop) {
        File inputF = new File("./" + FileSound);
        AudioInputStream musicIn = null;
        try {
            musicIn = AudioSystem.getAudioInputStream(inputF.toURI().toURL());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Clip vid = null;
        try {
            vid = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            vid.open(musicIn);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        vid.start();
        vid.loop(NumLoop);
    }
}