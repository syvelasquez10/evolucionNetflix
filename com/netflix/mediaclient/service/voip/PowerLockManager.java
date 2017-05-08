// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.annotation.TargetApi;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.Log;
import android.os.PowerManager;
import android.os.PowerManager$WakeLock;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent;

class PowerLockManager
{
    private static final String TAG = "nf_voip";
    private ServiceAgent mAgent;
    private PowerLockManager$AudioRoutingChangedReceiver mAudioRoutingChangedReceiver;
    private boolean mCallInProgress;
    private boolean mConnectedHeadphones;
    private Context mContex;
    private PowerManager$WakeLock mCpuWakeLock;
    private PowerManager$WakeLock mProximityWakeLock;
    private boolean mSpeakerOn;
    
    PowerLockManager(final Context mContex, final ServiceAgent mAgent) {
        this.mAudioRoutingChangedReceiver = new PowerLockManager$AudioRoutingChangedReceiver(this, null);
        if (mContex == null) {
            throw new IllegalArgumentException("Context can not be null");
        }
        this.mContex = mContex;
        this.mAgent = mAgent;
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
        if (this.mAgent.getConfigurationAgent().getDeviceCategory() == DeviceCategory.TABLET) {
            Log.d("nf_voip", "Device is tablet, do NOT acquire screen lock!");
            return;
        }
        Log.d("nf_voip", "Device is phone, do acquire screen lock!");
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
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: iconst_0       
        //     4: putfield        com/netflix/mediaclient/service/voip/PowerLockManager.mCallInProgress:Z
        //     7: aload_0        
        //     8: invokespecial   com/netflix/mediaclient/service/voip/PowerLockManager.releaseScreenLock:()V
        //    11: aload_0        
        //    12: invokespecial   com/netflix/mediaclient/service/voip/PowerLockManager.releaseCpuLock:()V
        //    15: aload_0        
        //    16: getfield        com/netflix/mediaclient/service/voip/PowerLockManager.mContex:Landroid/content/Context;
        //    19: aload_0        
        //    20: getfield        com/netflix/mediaclient/service/voip/PowerLockManager.mAudioRoutingChangedReceiver:Lcom/netflix/mediaclient/service/voip/PowerLockManager$AudioRoutingChangedReceiver;
        //    23: invokevirtual   android/content/Context.unregisterReceiver:(Landroid/content/BroadcastReceiver;)V
        //    26: aload_0        
        //    27: monitorexit    
        //    28: return         
        //    29: astore_1       
        //    30: aload_0        
        //    31: monitorexit    
        //    32: aload_1        
        //    33: athrow         
        //    34: astore_1       
        //    35: goto            26
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      15     29     34     Any
        //  15     26     34     38     Ljava/lang/Throwable;
        //  15     26     29     34     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
