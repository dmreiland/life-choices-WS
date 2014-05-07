package edu.sjsu.cmpe283.lifechoices.services;

import com.att.api.oauth.OAuthService;
import com.att.api.oauth.OAuthToken;
import com.att.api.speech.model.NBest;
import com.att.api.speech.model.NLUHypothesis;
import com.att.api.speech.model.OutComposite;
import com.att.api.speech.model.SpeechResponse;
import com.att.api.speech.service.SpeechService;
import com.att.api.speech.service.TtsService;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * User: maksim
 * Date: 5/3/14 - 9:39 PM
 */
public class ATTSpeechToTextService {

    String appKey = "9rvk6aad6bxs02j3voj08ta0rtyaodi2";
    String appSecret = "qg2msukkhu0lqjdljlxdr2m98vctvurv";

    final String fqdn = "https://api.att.com";
    // Enter the value from 'App Key' field
    final String clientId = appKey;
    // Enter the value from 'Secret' field
    final String clientSecret = appSecret;


    public String getText(final File AUDIO_FILE) throws Exception {

        String srtResult = "";

        // Use the app settings from developer.att.com for the following
        // values. Make sure Speech is enabled for the app key/secret.




        // Create service for requesting an OAuth token
        OAuthService osrvc = new OAuthService(fqdn, clientId, clientSecret);

        // Get OAuth token using the Speech scope
        OAuthToken token = osrvc.getToken("SPEECH");

        // Create service for interacting with the Speech api
        SpeechService speechSrvc = new SpeechService(fqdn, token);

        // Set this to a single channel audio file

        // Make the request to convert the audio file
        final SpeechResponse response = speechSrvc.speechToText(AUDIO_FILE);

        System.out.println("Converted Speech with status response:" + response.getStatus());
        System.out.println("Response ID:" + response.getResponseId() + "\n");

        System.out.println("NBest values:");
        System.out.println("---------------");
        for (NBest nbest : response.getNbests()) {

            srtResult = nbest.getResultText();
            System.out.println("\tHypothesis: " + nbest.getHypothesis());
            System.out.println("\tConfidence: " + nbest.getConfidence());
            System.out.println("\tGrade: " + nbest.getGrade());
            System.out.println("\tLanguage Id: " + nbest.getLanguageId());
            System.out.println("\tResult Text: " + nbest.getResultText());
            System.out.println(formatList("\tWords: ", nbest.getWords()));
            System.out.println(formatList("\tWord Scores: ", nbest.getWordScores()));

            NLUHypothesis nlu = nbest.getNluHypothesis();
            if (nlu != null) {
                System.out.println("\tNluHypothesis:");
                List<OutComposite> composites = nlu.getOutComposite();
                for (OutComposite comp : composites) {
                    System.out.println("\t\tOut: " + comp.getOut());
                    System.out.println("\t\tGrammar: " + comp.getGrammar());
                }
            }
        }


        return srtResult;
    }

    public byte[] textToSpeech(final String text){

        byte[] audio = null;

        try {



            // Create service for requesting an OAuth token
            OAuthService osrvc = new OAuthService(fqdn, clientId, clientSecret);

            // Get OAuth token using the Text To Speech scope
            OAuthToken token = osrvc.getToken("TTS");

            // Create service for interacting with the Speech api
            TtsService ttsService = new TtsService(fqdn, token);

            // Send the request to obtain the audio
            audio = ttsService.sendRequest("text/plain", text, "audio/x-wav");

            // If the request fails an exception is thrown, thus if we get here
            // we've succeeded.
            System.out.println("Successfully got audio file!");

        } catch (Exception re) {
            // handle exceptions here
            re.printStackTrace();
        } finally {
            // perform any clean up here
        }

        return audio;
    }


    private static String formatList(String preface, List<?> lstring) {
        StringBuilder sbuild = new StringBuilder(preface);

        Iterator<?> itr = lstring.iterator();
        if (itr.hasNext()) {
            sbuild.append("[ " + itr.next());
            while (itr.hasNext()) {
                sbuild.append(", " + itr.next());
            }
        }
        sbuild.append(" ]");

        return sbuild.toString();
    }
}
