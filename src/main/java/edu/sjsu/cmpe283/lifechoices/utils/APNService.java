package edu.sjsu.cmpe283.lifechoices.utils;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

import java.net.URL;

/**
 * User: maksim
 * Date: 4/12/14 - 2:57 PM
 */
public class APNService {


    static ApnsService service;

    private APNService(){

    }

    /**
     * Tip: Refrain from opening and closing the connections to the APNs
     * for each push notification that you want to send. Rapid opening and
     * closing of connections to the APNs will be deemed as a
     * Denial-of-Service (DOS) attack and may prevent your provider
     * from sending push notifications to your applications.
     */
    private static ApnsService init() {

        if (service == null) {
            URL url = ApnsService.class.getResource("/Certificates.p12");

            service = APNS
                    .newService()
                    .withCert(url.getPath(), "lifechoices14")
                    .withSandboxDestination()
                    .build();
        }

        return service;

    }

    public static void send(String token, String message) {

        init();
        /**
         * The payload is a JSON formatted string (maximum 256 bytes)
         * carrying the information you want to send to your application.
         */
        String payload = APNS.newPayload().alertBody(message).build();
        service.push(token, payload);
    }

    public static void closeConnection(){
        init();

        service.stop();
    }
}
