// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.KeyEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.mdx.RemotePlayer;
import com.netflix.mediaclient.util.LastKeyEvent;

public class MdxKeyEventHandler
{
    private static final String TAG = "nf_key";
    private static final int VOLUME_DELTA = 10;
    private final MdxKeyEventCallbacks callbacks;
    private final LastKeyEvent lastKey;
    
    public MdxKeyEventHandler(final MdxKeyEventCallbacks callbacks) {
        this.lastKey = new LastKeyEvent();
        this.callbacks = callbacks;
    }
    
    private boolean isPlayerInValidState(final RemotePlayer remotePlayer) {
        String value;
        if (remotePlayer == null) {
            value = "null player";
        }
        else {
            value = String.valueOf(remotePlayer.getCapabilities());
        }
        Log.v("nf_key", value);
        return remotePlayer != null && remotePlayer.getCapabilities() != null && remotePlayer.getCapabilities().isVolumeControl();
    }
    
    public boolean handleKeyEvent(final KeyEvent keyEvent, final ServiceManager serviceManager, final RemotePlayer remotePlayer) {
        if (remotePlayer != null) {
            if (!MdxUtils.isCurrentMdxTargetAvailable(serviceManager)) {
                Log.d("nf_key", "Current mdx target is not available - not handling key event");
                return false;
            }
            if (keyEvent.getAction() == 0) {
                switch (keyEvent.getKeyCode()) {
                    default: {
                        return false;
                    }
                    case 24: {
                        if (this.isPlayerInValidState(remotePlayer)) {
                            if (this.lastKey.shouldIgnore(keyEvent)) {
                                Log.d("nf_key", "Volume key up is pressed, ignored");
                            }
                            else {
                                Log.d("nf_key", "Volume key up is pressed, sending...");
                                new BackgroundTask().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        remotePlayer.setVolume(MdxKeyEventHandler.this.callbacks.getVolumeAsPercent() + 10);
                                        MdxKeyEventHandler.this.callbacks.onVolumeSet(remotePlayer.getVolume());
                                    }
                                });
                            }
                            return true;
                        }
                        Log.d("nf_key", "Volume key up is pressed, pass it");
                        return false;
                    }
                    case 25: {
                        if (this.isPlayerInValidState(remotePlayer)) {
                            if (this.lastKey.shouldIgnore(keyEvent)) {
                                Log.d("nf_key", "Volume key down is pressed, ignored");
                            }
                            else {
                                Log.d("nf_key", "Volume key down is pressed, sending...");
                                new BackgroundTask().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        remotePlayer.setVolume(MdxKeyEventHandler.this.callbacks.getVolumeAsPercent() - 10);
                                        MdxKeyEventHandler.this.callbacks.onVolumeSet(remotePlayer.getVolume());
                                    }
                                });
                            }
                            return true;
                        }
                        Log.d("nf_key", "Volume key down is pressed, pass it");
                        return false;
                    }
                }
            }
        }
        return false;
    }
    
    public interface MdxKeyEventCallbacks
    {
        int getVolumeAsPercent();
        
        void onVolumeSet(final int p0);
    }
}
