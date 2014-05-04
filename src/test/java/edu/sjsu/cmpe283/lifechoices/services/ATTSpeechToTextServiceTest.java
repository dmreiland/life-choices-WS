package edu.sjsu.cmpe283.lifechoices.services;

import org.junit.Test;

import java.io.File;

public class ATTSpeechToTextServiceTest {

    ATTSpeechToTextService attSpeechToTextService = new ATTSpeechToTextService();

    @Test
    public void testGetText() throws Exception {
        final File AUDIO_FILE = new File("/lensoo/what-time-is-it-right-now.wav");

        String text = attSpeechToTextService.getText(AUDIO_FILE);

        System.out.println("Text: " + text);
    }
}