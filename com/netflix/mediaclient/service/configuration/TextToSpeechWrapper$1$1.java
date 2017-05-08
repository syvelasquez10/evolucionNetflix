// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import java.util.List;
import android.view.accessibility.AccessibilityManager;
import android.os.Handler;
import android.content.Context;
import android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener;
import com.netflix.mediaclient.Log;

class TextToSpeechWrapper$1$1 implements Runnable
{
    final /* synthetic */ TextToSpeechWrapper$1 this$1;
    
    TextToSpeechWrapper$1$1(final TextToSpeechWrapper$1 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        final boolean access$100 = this.this$1.this$0.isSpokenAccessibilityEnabled();
        if (this.this$1.this$0.mSpokenAccessibilityEnabled != access$100) {
            this.this$1.this$0.mSpokenAccessibilityEnabled = access$100;
            if (Log.isLoggable()) {
                Log.d(TextToSpeechWrapper.TAG, "onAccessibilityStateChanged: mSpokenAccessibilityEnabled change to " + this.this$1.this$0.mSpokenAccessibilityEnabled);
            }
        }
    }
}
