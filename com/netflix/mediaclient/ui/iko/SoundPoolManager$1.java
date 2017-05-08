// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko;

import com.netflix.mediaclient.Log;
import android.media.SoundPool;
import android.media.SoundPool$OnLoadCompleteListener;

class SoundPoolManager$1 implements SoundPool$OnLoadCompleteListener
{
    final /* synthetic */ SoundPoolManager this$0;
    
    SoundPoolManager$1(final SoundPoolManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onLoadComplete(final SoundPool soundPool, final int n, final int n2) {
        if (Log.isLoggable()) {
            final StringBuilder append = new StringBuilder().append("SoundPool load complete callback status for sound id = ").append(n).append(" is ");
            String s;
            if (n2 == 0) {
                s = "success";
            }
            else {
                s = "failure";
            }
            Log.d("SoundPoolManager", append.append(s).toString());
        }
        this.this$0.soundIdToLoadedMap.put(n, n2 == 0);
    }
}
