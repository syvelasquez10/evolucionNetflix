// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import com.netflix.mediaclient.android.app.UserInputManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.ApplicationStateListener;

public class PinVerifier implements ApplicationStateListener
{
    private static final long DEFAULT_PIN_SESSION_TIMEOUT_MS = 1800000L;
    public static final long DIALOG_TIMEOUT_IN_BACKGROUND = 180000L;
    private static final boolean FORCE_PREVIEW_CHECK = false;
    public static final boolean PIN_VERIFY_REQUIRED = true;
    public static final String TAG = "nf_pin";
    private static final String TAG_SESSION = "nf_pin_session";
    private static boolean sAppInBackground;
    private static long sBackgroundTime;
    private static PinDialog sPinDialog;
    private static long sPinSessionExpiryTime;
    private static PinVerifier sPinVerifyInstance;
    
    static {
        PinVerifier.sPinDialog = null;
        PinVerifier.sPinVerifyInstance = new PinVerifier();
    }
    
    public static PinVerifier getInstance() {
        return PinVerifier.sPinVerifyInstance;
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
    
    private boolean shouldHandleNewRequest(final PlayVerifierVault playVerifierVault) {
        Log.w("nf_pin", String.format("pinDialog!=null loc:%s, visible:%b, hidden:%b", playVerifierVault.getInvokeLocation(), PinVerifier.sPinDialog.isVisible(), PinVerifier.sPinDialog.isHidden()));
        if (!PinVerifier.sPinDialog.isVisible() && !PinVerifier.sPinDialog.isHidden()) {
            Log.w("nf_pin", String.format("Error.. pinDialog!=null but not visible - killing.. loc:%s, visible:%b, hidden:%b", playVerifierVault.getInvokeLocation(), PinVerifier.sPinDialog.isVisible(), PinVerifier.sPinDialog.isHidden()));
            this.dismissPinVerification();
            return true;
        }
        if (Log.isLoggable()) {
            Log.w("nf_pin", String.format("Error.. new pin request while in progress.. %s, visible:%b, hidden:%b", playVerifierVault.getInvokeLocation(), PinVerifier.sPinDialog.isVisible(), PinVerifier.sPinDialog.isHidden()));
        }
        if (PlayVerifierVault$RequestedBy.MDX.getValue().equals(playVerifierVault.getInvokeLocation())) {
            if (Log.isLoggable()) {
                Log.d("nf_pin", String.format("Dismissing new request new one Invoked from: %s", playVerifierVault.getInvokeLocation()));
            }
            return false;
        }
        if (Log.isLoggable()) {
            Log.d("nf_pin", String.format("Dismissing old dialog. old one Invoked from: %s", PinVerifier.sPinDialog.getInvokeLocation()));
        }
        this.dismissPinVerification();
        return true;
    }
    
    private boolean shouldVerifyPin(final boolean b, final boolean b2) {
        Log.d("nf_pin_session", String.format("Active:%b isPinProtected:%b isPreviewProtected: %b - shouldVerifyPin", isPinSessionActive(), b, b2));
        if (!b2) {
            if (!b) {
                return false;
            }
            if (isPinSessionActive()) {
                return false;
            }
        }
        return true;
    }
    
    private static void updatePinSessionExpiryTime(final long sPinSessionExpiryTime) {
        if (sPinSessionExpiryTime > PinVerifier.sPinSessionExpiryTime) {
            Log.d("nf_pin_session", String.format("isActive: %b, current:%d, newExpiry:%d - updatePinSessionExpiryTime", isPinSessionActive(), System.currentTimeMillis(), sPinSessionExpiryTime));
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
    public void onFocusGain(final UserInputManager userInputManager) {
    }
    
    @Override
    public void onFocusLost(final UserInputManager userInputManager) {
    }
    
    @Override
    public void onForeground(final UserInputManager userInputManager, final Intent intent) {
        PinVerifier.sAppInBackground = false;
        Log.d("nf_pin", "app in foreground ");
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
    
    public void registerPlayEvent(final boolean b, final boolean b2) {
        boolean b3;
        if (!b2 && isPinSessionActive()) {
            updatePinSessionExpiryTime(System.currentTimeMillis() + 1800000L);
            b3 = true;
        }
        else {
            b3 = false;
        }
        Log.d("nf_pin_session", String.format("isActive: %b, wasPinProtectedPlayback: %b, wasPreviewProtectedPlayback: %b, extendSession ? %b- registerPlayStopEvent", isPinSessionActive(), b, b2, b3));
    }
    
    protected boolean toDismissDialog() {
        return PinVerifier.sAppInBackground && System.currentTimeMillis() - PinVerifier.sBackgroundTime > 180000L;
    }
    
    public void verify(final NetflixActivity netflixActivity, final boolean b, final PlayVerifierVault playVerifierVault, final PinAndAgeVerifier$PinAndAgeVerifyCallback pinVerifierCallback) {
        if (!this.shouldVerifyPin(b, playVerifierVault.getAsset() != null && playVerifierVault.getAsset().isPreviewProtected())) {
            PinDialog.notifyCallerPinVerified(netflixActivity, playVerifierVault, pinVerifierCallback);
        }
        else {
            if (Log.isLoggable()) {
                Log.d("nf_pin", String.format("verifyPin loc:%s", playVerifierVault.getInvokeLocation()));
            }
            if (PinVerifier.sPinDialog == null || this.shouldHandleNewRequest(playVerifierVault)) {
                final NetflixApplication netflixApplication = (NetflixApplication)netflixActivity.getApplication();
                if (netflixApplication.wasInBackground()) {
                    Log.d("nf_pin", "skip pin dialog - was in background");
                    return;
                }
                netflixApplication.getUserInput().addListener(this);
                (PinVerifier.sPinDialog = PinDialog.createPinDialog(playVerifierVault)).setPinVerifierCallback(pinVerifierCallback);
                PinVerifier.sPinDialog.show(netflixActivity.getFragmentManager(), "frag_dialog");
            }
        }
    }
}
