// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.debug;

import com.facebook.common.logging.FLog;
import java.util.Locale;
import android.content.Context;
import android.widget.Toast;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContext;
import android.view.Choreographer;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import android.os.Build$VERSION;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class AnimationsDebugModule extends ReactContextBaseJavaModule
{
    private final DeveloperSettings mCatalystSettings;
    private FpsDebugFrameCallback mFrameCallback;
    
    public AnimationsDebugModule(final ReactApplicationContext reactApplicationContext, final DeveloperSettings mCatalystSettings) {
        super(reactApplicationContext);
        this.mCatalystSettings = mCatalystSettings;
    }
    
    private static void checkAPILevel() {
        if (Build$VERSION.SDK_INT < 16) {
            throw new JSApplicationCausedNativeException("Animation debugging is not supported in API <16");
        }
    }
    
    @Override
    public String getName() {
        return "AnimationsDebugModule";
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        if (this.mFrameCallback != null) {
            this.mFrameCallback.stop();
            this.mFrameCallback = null;
        }
    }
    
    @ReactMethod
    public void startRecordingFps() {
        if (this.mCatalystSettings == null || !this.mCatalystSettings.isAnimationFpsDebugEnabled()) {
            return;
        }
        if (this.mFrameCallback != null) {
            throw new JSApplicationCausedNativeException("Already recording FPS!");
        }
        checkAPILevel();
        (this.mFrameCallback = new FpsDebugFrameCallback(Choreographer.getInstance(), this.getReactApplicationContext())).startAndRecordFpsAtEachFrame();
    }
    
    @ReactMethod
    public void stopRecordingFps(final double n) {
        if (this.mFrameCallback == null) {
            return;
        }
        checkAPILevel();
        this.mFrameCallback.stop();
        final FpsDebugFrameCallback$FpsInfo fpsInfo = this.mFrameCallback.getFpsInfo((long)n);
        if (fpsInfo == null) {
            Toast.makeText((Context)this.getReactApplicationContext(), (CharSequence)"Unable to get FPS info", 1);
        }
        else {
            final String string = String.format(Locale.US, "FPS: %.2f, %d frames (%d expected)", fpsInfo.fps, fpsInfo.totalFrames, fpsInfo.totalExpectedFrames) + "\n" + String.format(Locale.US, "JS FPS: %.2f, %d frames (%d expected)", fpsInfo.jsFps, fpsInfo.totalJsFrames, fpsInfo.totalExpectedFrames) + "\n" + "Total Time MS: " + String.format(Locale.US, "%d", fpsInfo.totalTimeMs);
            FLog.d("React", string);
            Toast.makeText((Context)this.getReactApplicationContext(), (CharSequence)string, 1).show();
        }
        this.mFrameCallback = null;
    }
}
