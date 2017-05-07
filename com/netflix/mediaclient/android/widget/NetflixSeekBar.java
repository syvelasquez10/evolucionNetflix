// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.util.api.Api21Util;
import com.netflix.mediaclient.util.AndroidUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.SeekBar;

public class NetflixSeekBar extends SeekBar
{
    public NetflixSeekBar(final Context context) {
        super(context);
        this.init();
    }
    
    public NetflixSeekBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public NetflixSeekBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        if (AndroidUtils.getAndroidVersion() >= 21) {
            Api21Util.setSplitTrack(this, false);
        }
    }
}
