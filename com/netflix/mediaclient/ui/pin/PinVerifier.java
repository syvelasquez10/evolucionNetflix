// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

import android.app.DialogFragment;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.UserInputManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.ApplicationStateListener;

public class PinVerifier implements ApplicationStateListener
{
    private static final long DEFAULT_PIN_SESSION_TIMEOUT_MS = 1800000L;
    private static final long DIALOG_TIMEOUT_IN_BACKGROUND = 180000L;
    private static final boolean FORCE_PIN_VERIFY = false;
    public static final boolean PIN_VERIFY_REQUIRED = true;
    public static final String TAG = "nf_pin";
    private static final String TAG_SESSION = "nf_pin_session";
    private static boolean sAppInBackground;
    private static long sBackgroundTime;
    private static PinDialog sPinDialog;
    private static long sPinSessionExpiryTime;
    private static PinVerifier sPinVeriyInstance;
    
    static {
        PinVerifier.sPinDialog = null;
        PinVerifier.sPinVeriyInstance = new PinVerifier();
    }
    
    public static PinVerifier getInstance() {
        return PinVerifier.sPinVeriyInstance;
    }
    
    public static boolean isPinSessionActive() {
        return PinVerifier.sPinSessionExpiryTime > System.currentTimeMillis();
    }
    
    public static void lastUserInteractionTime(final long n) {
        registerUserInteractionEvent(n);
    }
    
    private static void registerUserInteractionEvent(final long n) {
        if (isPinSessionActive()) {
            Log.d("nf_pin_session", String.format("isActive: %b lastInteractionTime:%d - registerUserInteractionEvent", isPinSessionActive(), n));
            updatePinSessionExpiryTime(System.currentTimeMillis() - n + 1800000L);
        }
    }
    
    private static void setPinSessionToInactive() {
        PinVerifier.sPinSessionExpiryTime = 0L;
    }
    
    private boolean shouldHandleNewRequest(final PinDialogVault pinDialogVault) {
        Log.w("nf_pin", String.format("pinDialog!=null loc:%s, visibile:%b, hidden:%b", pinDialogVault.getInvokeLocation(), PinVerifier.sPinDialog.isVisible(), PinVerifier.sPinDialog.isHidden()));
        if (!PinVerifier.sPinDialog.isVisible() && !PinVerifier.sPinDialog.isHidden()) {
            Log.w("nf_pin", String.format("Error.. pinDialog!=null but not visible - killing.. loc:%s, visibile:%b, hidden:%b", pinDialogVault.getInvokeLocation(), PinVerifier.sPinDialog.isVisible(), PinVerifier.sPinDialog.isHidden()));
            this.dismissPinVerification();
            return true;
        }
        if (Log.isLoggable("nf_pin", 3)) {
            Log.w("nf_pin", String.format("Error.. new pin request while in progress.. %s, visibile:%b, hidden:%b", pinDialogVault.getInvokeLocation(), PinVerifier.sPinDialog.isVisible(), PinVerifier.sPinDialog.isHidden()));
        }
        if (PinDialogVault$PinInvokedFrom.MDX.getValue().equals(pinDialogVault.getInvokeLocation())) {
            if (Log.isLoggable("nf_pin", 3)) {
                Log.d("nf_pin", String.format("Dismissing new request new one Invoked from: %s", pinDialogVault.getInvokeLocation()));
            }
            return false;
        }
        if (Log.isLoggable("nf_pin", 3)) {
            Log.d("nf_pin", String.format("Dismising old dialog. old one Invoked from: %s", PinVerifier.sPinDialog.getInvokeLocation()));
        }
        this.dismissPinVerification();
        return true;
    }
    
    private boolean shouldVerifyPin(final boolean b) {
        Log.d("nf_pin_session", String.format("Active:%b isPlayablePinProtected:%b - shouldVerifyPin", isPinSessionActive(), b));
        return b && !isPinSessionActive();
    }
    
    private static void updatePinSessionExpiryTime(final long sPinSessionExpiryTime) {
        if (sPinSessionExpiryTime > PinVerifier.sPinSessionExpiryTime) {
            Log.d("nf_pin_session", String.format("isActive: %b, current:%d, newEpiry:%d - updatePinSessionExpiryTime", isPinSessionActive(), System.currentTimeMillis(), sPinSessionExpiryTime));
            PinVerifier.sPinSessionExpiryTime = sPinSessionExpiryTime;
        }
    }
    
    public void clearState() {
        setPinSessionToInactive();
    }
    
    public void dismissPinVerification() {
        boolean b = false;
        final boolean sAppInBackground = PinVerifier.sAppInBackground;
        if (PinVerifier.sPinDialog != null) {
            b = true;
        }
        Log.d("nf_pin", String.format("dismissPinVerification appInBackground:%b, pinDialogValid:%b", sAppInBackground, b));
        if (PinVerifier.sPinDialog != null) {
            PinVerifier.sPinDialog.dismissDialog();
        }
    }
    
    @Override
    public void onBackground(final UserInputManager userInputManager) {
        Log.d("nf_pin", "app in background");
        PinVerifier.sBackgroundTime = System.currentTimeMillis();
        PinVerifier.sAppInBackground = true;
    }
    
    @Override
    public void onForeground(final UserInputManager userInputManager) {
        PinVerifier.sAppInBackground = false;
        Log.d("nf_pin", String.format("app in foreground ", new Object[0]));
    }
    
    @Override
    public void onUiGone(final UserInputManager userInputManager) {
    }
    
    @Override
    public void onUiStarted(final UserInputManager userInputManager) {
    }
    
    protected void pinDialogDismissed() {
        Log.d("nf_pin", "pinDialogDismissed mPinDialog=null");
        PinVerifier.sPinDialog = null;
    }
    
    public void registerPinVerifyEvent() {
        Log.d("nf_pin_session", String.format("isActive: %b to: true - registerPinVerifyEvent", isPinSessionActive()));
        updatePinSessionExpiryTime(System.currentTimeMillis() + 1800000L);
    }
    
    public void registerPlayEvent(final boolean b) {
        boolean b2 = false;
        final boolean pinSessionActive = isPinSessionActive();
        if (isPinSessionActive() || b) {
            b2 = true;
        }
        Log.d("nf_pin_session", String.format("isActive: %b, wasPinProtectedPlayback: %b, extendSession ? %b- registerPlayStopEvent", pinSessionActive, b, b2));
        if (isPinSessionActive()) {
            updatePinSessionExpiryTime(System.currentTimeMillis() + 1800000L);
        }
    }
    
    protected boolean toDismissDialog() {
        return PinVerifier.sAppInBackground && System.currentTimeMillis() - PinVerifier.sBackgroundTime > 180000L;
    }
    
    public void verify(final NetflixActivity netflixActivity, final boolean b, final PinDialogVault pinDialogVault) {
        if (!this.shouldVerifyPin(b)) {
            PinDialog.notifyCallerPinVerified(netflixActivity, pinDialogVault);
        }
        else {
            if (Log.isLoggable("nf_pin", 3)) {
                Log.d("nf_pin", String.format("verifyPin loc:%s", pinDialogVault.getInvokeLocation()));
            }
            if (PinVerifier.sPinDialog == null || this.shouldHandleNewRequest(pinDialogVault)) {
                ((NetflixApplication)netflixActivity.getApplication()).getUserInput().addListener(this);
                netflixActivity.showDialog(PinVerifier.sPinDialog = PinDialog.createPinDialog(netflixActivity, pinDialogVault));
            }
        }
    }
}
