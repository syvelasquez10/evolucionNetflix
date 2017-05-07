// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.api;

import android.widget.SeekBar;
import android.annotation.TargetApi;

@TargetApi(21)
public class Api21Util
{
    public static void setSplitTrack(final SeekBar seekBar, final boolean splitTrack) {
        seekBar.setSplitTrack(splitTrack);
    }
}
