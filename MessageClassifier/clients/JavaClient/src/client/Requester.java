/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 *
 * @author Shakya
 */
public class Requester implements Runnable {

    private String getURL;
    private String txtPath;
    private int delay;
    public boolean isSending = false;
    ArrayList<File> filesList;
    ClientUI ui;
    
    private final String ENCODING_TYPE =  "UTF-8";
    private final String USER_AGENT = "Mozilla/5.0";

    public void run() {

        try {

            FReader fr = new FReader();
            for (File f : filesList) {
                if (!isSending) {
                    return;
                }
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f.getAbsoluteFile()));
                String message = fr.readFile(bufferedReader);                
                String encodedMessage = URLEncoder.encode(message, ENCODING_TYPE).replaceAll("//+", "%20");
                
                sendRequests(encodedMessage, getURL);
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            System.out.println("sendig request exception");
        }
        ui.isSending=false;
        ui.sendigChangedUpdateUI();

    }

    public void sendRequests(String message, String getURL) throws Exception {
        // change this format accordingly
        String url=getURL + message;
        ui.updateList(url);
        sendGet(url);
    }

    // HTTP GET request
    private void sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header        
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    /**
     * @param getURL the getURL to set
     */
    public void setGetURL(String getURL) {
        this.getURL = getURL;
    }

    /**
     * @param txtPath the txtPath to set
     */
    public void setTxtPath(String txtPath) {
        this.txtPath = txtPath;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
