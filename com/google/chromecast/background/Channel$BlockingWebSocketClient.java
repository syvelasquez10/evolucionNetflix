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
import java.util.concurrent.TimeUnit;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import org.java_websocket.client.WebSocketClient;

class Channel$BlockingWebSocketClient extends WebSocketClient
{
    private CountDownLatch receiveSignal;
    private String response;
    
    Channel$BlockingWebSocketClient(final URI uri) {
        super(uri);
        this.receiveSignal = null;
    }
    
    private void clearWebSocketSingleton() {
        if (Channel.backgroundWebSocket != null) {
            Channel.backgroundWebSocket.close();
        }
        Channel.backgroundWebSocket = null;
    }
    
    public void onClose(final int n, final String s, final boolean b) {
        log("Channel closed " + s);
        this.clearWebSocketSingleton();
    }
    
    public void onError(final Exception ex) {
        log("Channel error " + ex.getMessage());
        this.clearWebSocketSingleton();
    }
    
    public void onMessage(final String response) {
        this.response = response;
        this.receiveSignal.countDown();
    }
    
    public void onOpen(final ServerHandshake serverHandshake) {
        log("Channel opened");
    }
    
    public int sendAndWaitAck(final String s) {
        this.receiveSignal = new CountDownLatch(1);
        this.send(s);
        while (true) {
            try {
                this.receiveSignal.await(3L, TimeUnit.SECONDS);
                if ("ACK".equals(this.response)) {
                    return 0;
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
        if ("NACK".equals(this.response)) {
            Log.w("Background", "Receiver is running in foreground");
            return 2;
        }
        Log.w("Background", "Got no response from receiver for 3 sec");
        this.clearWebSocketSingleton();
        return 1;
    }
}
