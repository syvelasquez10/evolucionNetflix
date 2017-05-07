// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.os.Build$VERSION;
import android.os.IBinder;
import android.content.Intent;
import java.util.Iterator;
import java.util.Map;
import android.os.Handler;
import android.content.Context;
import android.app.Service;

public class WhistleEngine extends Service
{
    private Context context;
    private Handler eventHandler;
    private WhistleEngineDelegate eventListener;
    private PowerManagement powerManagement;
    
    static {
        System.loadLibrary("whistleengine");
        loadInitialization();
    }
    
    private String NormalizeAccount(final String s, String string) {
        if (!s.contains("@")) {
            string = "sip:" + s + "@" + string;
        }
        else {
            string = s;
            if (!s.startsWith("sip:")) {
                return "sip:" + s;
            }
        }
        return string;
    }
    
    private native void addDialXHeader(final String p0, final String p1);
    
    private void callConnected(final int n) {
        this.callbackEvent(new WhistleEngine$3(this, n));
    }
    
    private void callDisconnected(final int n) {
        this.callbackEvent(new WhistleEngine$4(this, n));
    }
    
    private void callEnded(final int n) {
        if (this.eventListener != null) {
            this.callbackEvent(new WhistleEngine$7(this, n));
        }
    }
    
    private void callFailed(final int n, final String s, final int n2) {
        this.callbackEvent(new WhistleEngine$5(this, n, s, n2));
    }
    
    private void callRinging(final int n) {
        this.callbackEvent(new WhistleEngine$2(this, n));
    }
    
    private void callSecured(final int n, final boolean b) {
        this.callbackEvent(new WhistleEngine$12(this, n, b));
    }
    
    private void callbackEvent(final Runnable runnable) {
        this.eventHandler.post(runnable);
    }
    
    private void connectivityUpdate(final int n, final int n2) {
        WhistleEngineDelegate$ConnectivityState whistleEngineDelegate$ConnectivityState = null;
        switch (n2) {
            default: {
                return;
            }
            case 0: {
                whistleEngineDelegate$ConnectivityState = WhistleEngineDelegate$ConnectivityState.RED;
                break;
            }
            case 1: {
                whistleEngineDelegate$ConnectivityState = WhistleEngineDelegate$ConnectivityState.YELLOW;
                break;
            }
            case 2: {
                whistleEngineDelegate$ConnectivityState = WhistleEngineDelegate$ConnectivityState.GREEN;
                break;
            }
        }
        this.callbackEvent(new WhistleEngine$6(this, n, whistleEngineDelegate$ConnectivityState));
    }
    
    private void engineNotReady() {
        this.callbackEvent(new WhistleEngine$11(this));
    }
    
    private void engineReady() {
        this.callbackEvent(new WhistleEngine$10(this));
    }
    
    private void incomingCall(final int n, final String s, final String s2) {
        this.callbackEvent(new WhistleEngine$1(this, s2, n, s));
    }
    
    private void initAudio(final WhistleEngineConfig whistleEngineConfig) {
        int n = 48000;
        final int min = Math.min(AudioUtils.getSampleRate(), whistleEngineConfig.getSampleRate());
        if (min <= 0) {
            throw new Exception("Unable to initialize audio");
        }
        if (min < 48000) {
            if (min >= 32000) {
                n = 32000;
            }
            else if (min >= 24000) {
                n = 24000;
            }
            else if (min >= 16000) {
                n = 16000;
            }
            else if (min >= 8000) {
                n = 8000;
            }
            else {
                n = min;
            }
        }
        setAudioConfiguration(n, whistleEngineConfig.getEchoCanceler());
    }
    
    private void initNetworking(final WhistleEngineConfig whistleEngineConfig) {
        if (whistleEngineConfig.getTransportMode() == WhistleEngineConfig$TransportMode.UDP) {
            this.setUDPMode();
        }
        else if (whistleEngineConfig.getTransportMode() == WhistleEngineConfig$TransportMode.TLS) {
            this.setTLSMode(whistleEngineConfig.getTLSPort(), whistleEngineConfig.getPrivateKey(), whistleEngineConfig.getRootCertificate(), whistleEngineConfig.getCertificateAuthorityFile(), whistleEngineConfig.getCertificateChain());
        }
        final String[] bestBindAddress = BindAddress.getBestBindAddress(this.context);
        this.setBindAddress(bestBindAddress[0], bestBindAddress[1]);
    }
    
    private static native void loadInitialization();
    
    private void passwordDialog(final boolean b) {
        this.callbackEvent(new WhistleEngine$8(this, b));
    }
    
    private void registrationSuccessful() {
        this.callbackEvent(new WhistleEngine$9(this));
    }
    
    private void selectedCodec(final int n, final String s, final int n2) {
        this.callbackEvent(new WhistleEngine$13(this, n, s, n2));
    }
    
    private native void setAccountInfoNative(final String p0, final String p1, final String p2, final boolean p3);
    
    private native void setApplicationIdentifier(final String p0);
    
    private static native void setAudioConfiguration(final int p0, final boolean p1);
    
    private native void setBindAddress(final String p0, final String p1);
    
    private native void setJNIEngine(final int p0);
    
    private native void setTLSMode(final int p0, final String p1, final String p2, final String p3, final String p4);
    
    private native void setUDPMode();
    
    private native void stopNative();
    
    public native void answer(final int p0);
    
    public native int dial(final int p0, final String p1);
    
    public int dial(final int n, final String s, final DialExtra dialExtra) {
        for (final Map.Entry<String, String> entry : dialExtra.getXHeaders().entrySet()) {
            this.addDialXHeader(entry.getKey(), entry.getValue());
        }
        return this.dial(n, s);
    }
    
    public native float getInputLevel();
    
    public native float getLineLevel(final int p0);
    
    public native int getMaxLines();
    
    public native void hangup(final int p0);
    
    public IBinder onBind(final Intent intent) {
        return (IBinder)new WhistleEngine$WhistleEngineBinder(this);
    }
    
    public void onCreate() {
        super.onCreate();
        (this.powerManagement = new PowerManagement((Context)this)).acquireLocks();
    }
    
    public void onDestroy() {
        this.stop();
        this.powerManagement.releaseLocks();
        super.onDestroy();
    }
    
    public native void retryRegistrationAuth();
    
    public void setAccountInfo(final String s, final String s2, final String s3, final boolean b) {
        if (s3 == null && b) {
            throw new RuntimeException();
        }
        this.setAccountInfoNative(this.NormalizeAccount(s, s3), s2, s3, b);
    }
    
    public native void setMute(final boolean p0);
    
    public native void setOutputVolume(final float p0);
    
    public native void setSpeaker(final boolean p0);
    
    public void start(final WhistleEngineDelegate eventListener, final WhistleEngineConfig whistleEngineConfig, final Context context) {
        this.setJNIEngine(Build$VERSION.SDK_INT);
        this.eventListener = eventListener;
        this.context = context;
        this.eventHandler = new Handler();
        this.initAudio(whistleEngineConfig);
        this.setAccountInfo(whistleEngineConfig.getAccount(), whistleEngineConfig.getPassword(), whistleEngineConfig.getProxy(), whistleEngineConfig.getRegistrationEnabled());
        this.setApplicationIdentifier(whistleEngineConfig.getApplicationIdentifier());
        this.initNetworking(whistleEngineConfig);
    }
    
    public native void startDTMF(final char p0);
    
    public native void startSound(final String p0, final boolean p1, final boolean p2, final float p3);
    
    public void stop() {
        this.stopNative();
        this.eventListener = null;
    }
    
    public native void stopDTMF();
    
    public native void stopSound(final String p0);
}
