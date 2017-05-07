// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.Log;
import android.media.AudioManager$OnAudioFocusChangeListener;

class WhistleVoipAgent$7 implements AudioManager$OnAudioFocusChangeListener
{
    final /* synthetic */ WhistleVoipAgent this$0;
    
    WhistleVoipAgent$7(final WhistleVoipAgent this$0) {
        this.this$0 = this$0;
    }
    
    public void onAudioFocusChange(final int n) {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "Change in audio focus " + n);
        }
    }
}
