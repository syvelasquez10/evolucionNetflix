// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.view.ViewTreeObserver$OnPreDrawListener;

class EnhancedSubtitleManager$1 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ EnhancedSubtitleManager this$0;
    
    EnhancedSubtitleManager$1(final EnhancedSubtitleManager this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onPreDraw() {
        Log.d("nf_subtitles_render", "onPreDraw on display area");
        return false;
    }
}
