// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.slider;

import com.facebook.react.uimanager.events.Event;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import android.widget.SeekBar;
import android.widget.SeekBar$OnSeekBarChangeListener;

final class ReactSliderManager$1 implements SeekBar$OnSeekBarChangeListener
{
    public void onProgressChanged(final SeekBar seekBar, final int n, final boolean b) {
        ((ReactContext)seekBar.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(new ReactSliderEvent(seekBar.getId(), ((ReactSlider)seekBar).toRealProgress(n), b));
    }
    
    public void onStartTrackingTouch(final SeekBar seekBar) {
    }
    
    public void onStopTrackingTouch(final SeekBar seekBar) {
        ((ReactContext)seekBar.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(new ReactSlidingCompleteEvent(seekBar.getId(), ((ReactSlider)seekBar).toRealProgress(seekBar.getProgress())));
    }
}
