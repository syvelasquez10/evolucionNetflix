// 
// Decompiled by Procyon v0.5.30
// 

package com.google.chromecast.background;

import com.google.android.gms.cast.CastDevice;
import org.json.JSONException;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import org.json.JSONObject;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import android.util.Log;
import java.net.URI;

public class Channel
{
    private static Channel$BlockingWebSocketClient backgroundWebSocket;
    private static final Object sendMessageLock;
    
    static {
        sendMessageLock = new Object();
        Channel.backgroundWebSocket = null;
    }
    
    private static Channel$BlockingWebSocketClient connectWebSocket(final String s) {
        log("Connect channel >>");
        Channel$BlockingWebSocketClient channel$BlockingWebSocketClient;
        try {
            channel$BlockingWebSocketClient = new Channel$BlockingWebSocketClient(new URI(s));
            final String s2 = "Waiting for connection te be established";
            log(s2);
            final Channel$BlockingWebSocketClient channel$BlockingWebSocketClient2 = channel$BlockingWebSocketClient;
            channel$BlockingWebSocketClient2.connectBlocking();
            final String s3 = "Web socket connection is established";
            log(s3);
            final String s4 = "Background";
            final String s5 = "Background channel is established";
            Log.i(s4, s5);
            return channel$BlockingWebSocketClient;
        }
        catch (URISyntaxException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            final String s2 = "Waiting for connection te be established";
            log(s2);
            final Channel$BlockingWebSocketClient channel$BlockingWebSocketClient2 = channel$BlockingWebSocketClient;
            channel$BlockingWebSocketClient2.connectBlocking();
            final String s3 = "Web socket connection is established";
            log(s3);
            final String s4 = "Background";
            final String s5 = "Background channel is established";
            Log.i(s4, s5);
            return channel$BlockingWebSocketClient;
        }
        catch (InterruptedException ex2) {
            ex2.printStackTrace();
            return null;
        }
    }
    
    private static String getHttpResponse(String string) {
        log("get connection query response >>");
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(string).openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(3000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.connect();
        final JSONObject jsonObject = new JSONObject();
        final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(jsonObject.toString().getBytes("UTF-8"));
        dataOutputStream.flush();
        dataOutputStream.close();
        final int responseCode = httpURLConnection.getResponseCode();
        if (responseCode != 200) {
            Log.e("Background", "Failed http request with response code: " + responseCode);
            httpURLConnection.disconnect();
            return null;
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        string = "";
        while (true) {
            final String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            string += line;
        }
        httpURLConnection.disconnect();
        return string;
    }
    
    private static Channel$BlockingWebSocketClient getWebSocket(String s) {
        s = "http://" + s + ":8008/connection/" + "CAST.BACKGROUND.CHANNEL";
        log("VERSION: 0.0.1");
        while (true) {
            try {
                s = getWebSocketUrl(s);
                if (s == null) {
                    Log.e("Background", "Cannot get a connection");
                    return null;
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
                s = null;
                continue;
            }
            break;
        }
        return connectWebSocket(s);
    }
    
    private static String getWebSocketUrl(String s) {
        log("get connection url");
        s = getHttpResponse(s);
        if (s == null) {
            Log.e("Background", "getHttpResponse() failed");
            return null;
        }
        try {
            s = new JSONObject(s).getString("URL");
            return s;
        }
        catch (JSONException ex) {
            Log.e("Background", "JSON", (Throwable)ex);
            return null;
        }
    }
    
    private static void log(final String s) {
        Log.i("Background", s);
    }
    
    public static int sendMessage(final CastDevice castDevice, final String s) {
        return sendMessage(castDevice.getIpAddress().getHostAddress(), s);
    }
    
    public static int sendMessage(final String s, final String s2) {
        synchronized (Channel.sendMessageLock) {
            if (Channel.backgroundWebSocket == null) {
                Channel.backgroundWebSocket = getWebSocket(s);
                if (Channel.backgroundWebSocket == null) {
                    return 1;
                }
            }
            return Channel.backgroundWebSocket.sendAndWaitAck(s2);
        }
    }
}
