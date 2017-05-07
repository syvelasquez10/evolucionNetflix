// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.android.widget.TappableSurfaceView$TapListener;
import com.netflix.mediaclient.android.widget.TappableSurfaceView$SurfaceMeasureListener;
import android.view.SurfaceHolder$Callback;
import android.view.View$OnClickListener;
import android.widget.SeekBar$OnSeekBarChangeListener;

public class PlayScreen$Listeners
{
    public SeekBar$OnSeekBarChangeListener audioPositionListener;
    public View$OnClickListener episodeSelectorListener;
    public View$OnClickListener playPauseListener;
    public View$OnClickListener skipBackListener;
    public SurfaceHolder$Callback surfaceListener;
    public TappableSurfaceView$SurfaceMeasureListener surfaceMeasureListener;
    public TappableSurfaceView$TapListener tapListener;
    public SeekBar$OnSeekBarChangeListener videoPositionListener;
    public View$OnClickListener zoomListener;
}
