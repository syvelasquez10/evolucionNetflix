// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import java.util.List;
import android.view.accessibility.AccessibilityManager;
import android.os.Handler;
import android.content.Context;
import com.netflix.mediaclient.Log;
import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;

class TextToSpeechWrapper$1 implements AccessibilityManager$AccessibilityStateChangeListener
{
    final /* synthetic */ TextToSpeechWrapper this$0;
    
    TextToSpeechWrapper$1(final TextToSpeechWrapper this$0) {
        this.this$0 = this$0;
    }
    
    public void onAccessibilityStateChanged(final boolean b) {
        if (Log.isLoggable()) {
            Log.d(TextToSpeechWrapper.TAG, "onAccessibilityStateChanged: " + b);
        }
        this.this$0.mHandler.postDelayed((Runnable)new TextToSpeechWrapper$1$1(this), 500L);
    }
}
