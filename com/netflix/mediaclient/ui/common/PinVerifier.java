// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.app.DialogFragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;

public class PinVerifier
{
    private static final long DEFAULT_PIN_SESSION_TIMEOUT_MS = 1800000L;
    private static final boolean FORCE_PIN_VERIFY = false;
    private static final long MINUTE_MS = 60000L;
    private static final String TAG = "nf_pin";
    private static final String TAG_SESSION = "nf_pin_session";
    private static long mPinSessionExpiryTime;
    private static PinVerifier mPinVeriyInstance;
    
    static {
        PinVerifier.mPinVeriyInstance = new PinVerifier();
    }
    
    public static PinVerifier getInstance() {
        return PinVerifier.mPinVeriyInstance;
    }
    
    public static boolean isPinSessionActive() {
        return PinVerifier.mPinSessionExpiryTime > System.currentTimeMillis();
    }
    
    public static void lastUserInteractionTime(final long n) {
        registerUserInteractionEvent(n);
    }
    
    private void registerPinVerifyEvent() {
        Log.d("nf_pin_session", String.format("Active: %b to: true - registerPinVerifyEvent", isPinSessionActive()));
        updatePinSessionExpiryTime(System.currentTimeMillis() + 1800000L);
    }
    
    private static void registerUserInteractionEvent(final long n) {
        Log.d("nf_pin_session", String.format("Active: %b lastInteractionTime:%d - registerUserInteractionEvent", isPinSessionActive(), n));
        if (isPinSessionActive()) {
            updatePinSessionExpiryTime(System.currentTimeMillis() - n + 1800000L);
        }
    }
    
    private static void setPinSessionToInactive() {
        PinVerifier.mPinSessionExpiryTime = 0L;
    }
    
    private boolean shouldVerifyPin(final boolean b) {
        Log.d("nf_pin_session", String.format("Active:%b isPlayablePinProtected:%b - shouldVerifyPin", isPinSessionActive(), b));
        return b && !isPinSessionActive();
    }
    
    private static void updatePinSessionExpiryTime(final long mPinSessionExpiryTime) {
        if (mPinSessionExpiryTime > PinVerifier.mPinSessionExpiryTime) {
            Log.d("nf_pin_session", String.format("Active: %b, current:%d, newEpiry:%d - updatePinSessionExpiryTime", isPinSessionActive(), System.currentTimeMillis(), mPinSessionExpiryTime));
            PinVerifier.mPinSessionExpiryTime = mPinSessionExpiryTime;
        }
    }
    
    public void clearState() {
        setPinSessionToInactive();
    }
    
    public void registerPlayStopEvent() {
        Log.d("nf_pin_session", String.format("Active: %b - registerPlayStopEvent", isPinSessionActive()));
        if (isPinSessionActive()) {
            updatePinSessionExpiryTime(System.currentTimeMillis() + 1800000L);
        }
    }
    
    public void verify(final NetflixActivity netflixActivity, final boolean b, final PinVerificationCallback pinVerificationCallback) {
        if (!this.shouldVerifyPin(b)) {
            pinVerificationCallback.onPinVerification(true);
            return;
        }
        netflixActivity.showDialog(PinDialog.createPinDialog(netflixActivity.getServiceManager(), (PinDialog.PinDialogCallback)new PinCallback(pinVerificationCallback)));
    }
    
    private static class PinCallback implements PinDialogCallback
    {
        private final PinVerificationCallback cb;
        
        public PinCallback(final PinVerificationCallback cb) {
            this.cb = cb;
        }
        
        @Override
        public void pinCancelled() {
            Log.d("nf_pin", "pinVerification cancelled");
            this.cb.onPinCancelled();
        }
        
        @Override
        public void pinVerified(final boolean b) {
            PinVerifier.getInstance().registerPinVerifyEvent();
            this.cb.onPinVerification(b);
        }
    }
    
    public interface PinVerificationCallback
    {
        void onPinCancelled();
        
        void onPinVerification(final boolean p0);
    }
}
