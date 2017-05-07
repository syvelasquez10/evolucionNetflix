// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.widget.SeekBar;

public interface SnappableSeekBar$OnSnappableSeekBarChangeListener
{
    void onProgressChanged(final SeekBar p0, final int p1, final boolean p2);
    
    void onStartTrackingTouch(final SeekBar p0);
    
    void onStopTrackingTouch(final SeekBar p0, final boolean p1);
}
