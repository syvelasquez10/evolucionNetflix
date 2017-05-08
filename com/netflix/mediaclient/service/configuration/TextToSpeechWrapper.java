// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import java.util.List;
import com.netflix.mediaclient.Log;
import android.view.accessibility.AccessibilityManager;
import android.os.Handler;
import android.content.Context;
import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;

public class TextToSpeechWrapper
{
    private static final String TAG;
    private AccessibilityManager$AccessibilityStateChangeListener mAccessibilityListener;
    private Context mContext;
    private Handler mHandler;
    private boolean mSpokenAccessibilityEnabled;
    
    static {
        TAG = TextToSpeechWrapper.class.getSimpleName();
    }
    
    TextToSpeechWrapper(final Context mContext, final Handler mHandler) {
        this.mSpokenAccessibilityEnabled = false;
        this.mAccessibilityListener = (AccessibilityManager$AccessibilityStateChangeListener)new TextToSpeechWrapper$1(this);
        this.mContext = mContext;
        this.mHandler = mHandler;
        final AccessibilityManager accessibilityManager = (AccessibilityManager)this.mContext.getSystemService("accessibility");
        if (accessibilityManager != null) {
            accessibilityManager.addAccessibilityStateChangeListener(this.mAccessibilityListener);
        }
        this.mSpokenAccessibilityEnabled = this.isSpokenAccessibilityEnabled();
    }
    
    private boolean isSpokenAccessibilityEnabled() {
        final AccessibilityManager accessibilityManager = (AccessibilityManager)this.mContext.getSystemService("accessibility");
        if (accessibilityManager != null) {
            final List enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
            if (enabledAccessibilityServiceList != null) {
                final boolean b = enabledAccessibilityServiceList.size() > 0;
                if (Log.isLoggable()) {
                    Log.d(TextToSpeechWrapper.TAG, "Enabled Spoken Accessibility list size: " + enabledAccessibilityServiceList.size());
                }
                return b;
            }
        }
        return false;
    }
    
    public boolean isAssistiveAudioEnabled() {
        return this.mSpokenAccessibilityEnabled;
    }
}
