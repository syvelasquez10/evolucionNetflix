// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.media.AudioManager;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;
import android.os.PowerManager;
import android.os.PowerManager$WakeLock;
import android.content.Context;

class PowerLockManager
{
    private static final String TAG = "nf_voip";
    private PowerLockManager$AudioRoutingChangedReceiver mAudioRoutingChangedReceiver;
    private boolean mCallInProgress;
    private boolean mConnectedHeadphones;
    private Context mContex;
    private PowerManager$WakeLock mCpuWakeLock;
    private PowerManager$WakeLock mProximityWakeLock;
    private boolean mSpeakerOn;
    
    PowerLockManager(final Context mContex) {
        this.mAudioRoutingChangedReceiver = new PowerLockManager$AudioRoutingChangedReceiver(this, null);
        if (mContex == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        this.mContex = mContex;
    }
    
    private void acquireCpuLock() {
        if (this.mCpuWakeLock != null && this.mCpuWakeLock.isHeld()) {
            this.mCpuWakeLock.release();
        }
        final PowerManager powerManager = (PowerManager)this.mContex.getSystemService("power");
        if (powerManager == null) {
            Log.w("nf_voip", "Power manager is not available!");
        }
        else {
            while (true) {
                try {
                    this.mCpuWakeLock = powerManager.newWakeLock(1, "nf_voip");
                    if (this.mCpuWakeLock != null) {
                        Log.d("nf_voip", "acquireCpuLock: acquiring...");
                        this.mCpuWakeLock.acquire();
                        Log.d("nf_voip", "acquireCpuLock: acquired.");
                    }
                }
                catch (Throwable t) {
                    Log.e("nf_voip", "Failed to created new wake lock for promixity!");
                    continue;
                }
                break;
            }
        }
    }
    
    @TargetApi(21)
    private void acquireScreenLock() {
        if (this.mProximityWakeLock != null && this.mProximityWakeLock.isHeld()) {
            this.mProximityWakeLock.release();
        }
        final PowerManager powerManager = (PowerManager)this.mContex.getSystemService("power");
        if (powerManager == null) {
            Log.w("nf_voip", "Power manager is not available!");
            return;
        }
        while (true) {
            try {
                this.mProximityWakeLock = powerManager.newWakeLock(32, "nf_voip");
                if (this.mProximityWakeLock != null) {
                    Log.d("nf_voip", "Proximity screen wake off is supported on this device. Aquiring...");
                    this.mProximityWakeLock.acquire();
                    Log.d("nf_voip", "Proximity sensor aquired.");
                    return;
                }
            }
            catch (Throwable t) {
                Log.e("nf_voip", "Failed to created new wake lock for promixity!", t);
                continue;
            }
            break;
        }
        Log.d("nf_voip", "Proximity screen wake off is not supported on this device");
    }
    
    private void handleStateChange() {
        int n = 1;
        this.setStates();
        if (!this.mCallInProgress) {
            return;
        }
        if (this.mSpeakerOn) {
            Log.d("nf_voip", "Speaker is on, release proximity lock.");
        }
        else if (this.mConnectedHeadphones) {
            Log.d("nf_voip", "Headphones are used for call, release proximity lock.");
        }
        else {
            Log.d("nf_voip", "Internal speaker is used, acquire proximity lock.");
            n = 0;
        }
        if (n != 0) {
            Log.d("nf_voip", "Release proximity lock...");
            this.releaseScreenLock();
            return;
        }
        Log.d("nf_voip", "Acquire proximity lock...");
        this.acquireScreenLock();
    }
    
    private void releaseCpuLock() {
        if (this.mCpuWakeLock == null) {
            return;
        }
        if (this.mCpuWakeLock.isHeld()) {
            Log.d("nf_voip", "releaseCpuLock: releasing...");
            this.mCpuWakeLock.release();
        }
        else {
            Log.w("nf_voip", "releaseCpuLock: lock already released!");
        }
        this.mCpuWakeLock = null;
    }
    
    private void releaseScreenLock() {
        if (this.mProximityWakeLock == null) {
            return;
        }
        if (this.mProximityWakeLock.isHeld()) {
            Log.d("nf_voip", "releaseScreenLock: releasing...");
            this.mProximityWakeLock.release();
        }
        else {
            Log.w("nf_voip", "releaseScreenLock: lock already released!");
        }
        this.mProximityWakeLock = null;
    }
    
    private void setStates() {
        boolean mConnectedHeadphones = false;
        final AudioManager audioManager = (AudioManager)this.mContex.getSystemService("audio");
        if (audioManager != null) {
            if (audioManager.isWiredHeadsetOn() || audioManager.isBluetoothA2dpOn()) {
                mConnectedHeadphones = true;
            }
            this.mConnectedHeadphones = mConnectedHeadphones;
            this.mSpeakerOn = audioManager.isSpeakerphoneOn();
            if (Log.isLoggable()) {
                Log.d("nf_voip", "Wired headphone on: " + audioManager.isWiredHeadsetOn());
                Log.d("nf_voip", "Bluetooth headphone on: " + audioManager.isBluetoothA2dpOn());
            }
            return;
        }
        this.mConnectedHeadphones = false;
        this.mSpeakerOn = false;
    }
    
    public void callEnded() {
        synchronized (this) {
            this.mCallInProgress = false;
            this.releaseScreenLock();
            this.releaseCpuLock();
            try {
                this.mContex.unregisterReceiver((BroadcastReceiver)this.mAudioRoutingChangedReceiver);
            }
            catch (Throwable t) {
                Log.e("nf_voip", "Failed to register audio jack receiver", t);
            }
        }
    }
    
    public void callStarted() {
        synchronized (this) {
            this.mCallInProgress = true;
            this.acquireCpuLock();
            this.handleStateChange();
            try {
                final IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
                intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
                this.mContex.registerReceiver((BroadcastReceiver)this.mAudioRoutingChangedReceiver, intentFilter);
            }
            catch (Throwable t) {
                Log.e("nf_voip", "Failed to register audio jack receiver", t);
            }
        }
    }
    
    public void setSpeakerOn(final boolean b) {
        this.handleStateChange();
    }
}
