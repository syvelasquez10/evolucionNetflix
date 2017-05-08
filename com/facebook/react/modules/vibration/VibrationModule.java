// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.vibration;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactMethod;
import android.os.Vibrator;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class VibrationModule extends ReactContextBaseJavaModule
{
    public VibrationModule(final ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
    
    @ReactMethod
    public void cancel() {
        final Vibrator vibrator = (Vibrator)this.getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.cancel();
        }
    }
    
    @Override
    public String getName() {
        return "Vibration";
    }
    
    @ReactMethod
    public void vibrate(final int n) {
        final Vibrator vibrator = (Vibrator)this.getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.vibrate((long)n);
        }
    }
    
    @ReactMethod
    public void vibrateByPattern(final ReadableArray readableArray, final int n) {
        final long[] array = new long[readableArray.size()];
        for (int i = 0; i < readableArray.size(); ++i) {
            array[i] = readableArray.getInt(i);
        }
        final Vibrator vibrator = (Vibrator)this.getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.vibrate(array, n);
        }
    }
}
