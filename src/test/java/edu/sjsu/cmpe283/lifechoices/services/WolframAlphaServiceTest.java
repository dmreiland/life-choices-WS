package edu.sjsu.cmpe283.lifechoices.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

public class WolframAlphaServiceTest {

    WolframAlphaService wolframAlphaService = new WolframAlphaService();


    @Test
    public void testAsdf() throws Exception {
        String json = wolframAlphaService.formattedJson("eicosapentaenoic acid");

        System.out.println("\n--------------");
        System.out.println(json);
        System.out.println("\n--------------");

    }


    @Test
    public void xmlToJson() throws JSONException {
        JSONObject json = XML.toJSONObject(wolframAlphaService.sampleXmlResponse);
        System.out.println(json.getJSONObject("queryresult"));
        System.out.println("Number of pods: " + json.getJSONObject("queryresult").getInt("numpods"));
    }
}