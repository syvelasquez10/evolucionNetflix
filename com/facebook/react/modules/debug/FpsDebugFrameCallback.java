// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.debug;

import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import java.util.Map;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.TreeMap;
import com.facebook.react.bridge.ReactContext;
import android.view.Choreographer;
import android.annotation.TargetApi;
import android.view.Choreographer$FrameCallback;

@TargetApi(16)
public class FpsDebugFrameCallback implements Choreographer$FrameCallback
{
    private int m4PlusFrameStutters;
    private final Choreographer mChoreographer;
    private final DidJSUpdateUiDuringFrameDetector mDidJSUpdateUiDuringFrameDetector;
    private int mExpectedNumFramesPrev;
    private long mFirstFrameTime;
    private boolean mIsRecordingFpsInfoAtEachFrame;
    private long mLastFrameTime;
    private int mNumFrameCallbacks;
    private int mNumFrameCallbacksWithBatchDispatches;
    private final ReactContext mReactContext;
    private boolean mShouldStop;
    private TreeMap<Long, FpsDebugFrameCallback$FpsInfo> mTimeToFps;
    private final UIManagerModule mUIManagerModule;
    
    public FpsDebugFrameCallback(final Choreographer mChoreographer, final ReactContext mReactContext) {
        this.mShouldStop = false;
        this.mFirstFrameTime = -1L;
        this.mLastFrameTime = -1L;
        this.mNumFrameCallbacks = 0;
        this.mExpectedNumFramesPrev = 0;
        this.m4PlusFrameStutters = 0;
        this.mNumFrameCallbacksWithBatchDispatches = 0;
        this.mIsRecordingFpsInfoAtEachFrame = false;
        this.mChoreographer = mChoreographer;
        this.mReactContext = mReactContext;
        this.mUIManagerModule = mReactContext.getNativeModule(UIManagerModule.class);
        this.mDidJSUpdateUiDuringFrameDetector = new DidJSUpdateUiDuringFrameDetector();
    }
    
    public void doFrame(final long n) {
        if (this.mShouldStop) {
            return;
        }
        if (this.mFirstFrameTime == -1L) {
            this.mFirstFrameTime = n;
        }
        final long mLastFrameTime = this.mLastFrameTime;
        this.mLastFrameTime = n;
        if (this.mDidJSUpdateUiDuringFrameDetector.getDidJSHitFrameAndCleanup(mLastFrameTime, n)) {
            ++this.mNumFrameCallbacksWithBatchDispatches;
        }
        ++this.mNumFrameCallbacks;
        final int expectedNumFrames = this.getExpectedNumFrames();
        if (expectedNumFrames - this.mExpectedNumFramesPrev - 1 >= 4) {
            ++this.m4PlusFrameStutters;
        }
        if (this.mIsRecordingFpsInfoAtEachFrame) {
            Assertions.assertNotNull(this.mTimeToFps);
            this.mTimeToFps.put(System.currentTimeMillis(), new FpsDebugFrameCallback$FpsInfo(this.getNumFrames(), this.getNumJSFrames(), expectedNumFrames, this.m4PlusFrameStutters, this.getFPS(), this.getJSFPS(), this.getTotalTimeMS()));
        }
        this.mExpectedNumFramesPrev = expectedNumFrames;
        this.mChoreographer.postFrameCallback((Choreographer$FrameCallback)this);
    }
    
    public int getExpectedNumFrames() {
        return (int)(this.getTotalTimeMS() / 16.9 + 1.0);
    }
    
    public double getFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0;
        }
        return this.getNumFrames() * 1.0E9 / (this.mLastFrameTime - this.mFirstFrameTime);
    }
    
    public FpsDebugFrameCallback$FpsInfo getFpsInfo(final long n) {
        Assertions.assertNotNull(this.mTimeToFps, "FPS was not recorded at each frame!");
        final Map.Entry<Long, FpsDebugFrameCallback$FpsInfo> floorEntry = this.mTimeToFps.floorEntry(n);
        if (floorEntry == null) {
            return null;
        }
        return floorEntry.getValue();
    }
    
    public double getJSFPS() {
        if (this.mLastFrameTime == this.mFirstFrameTime) {
            return 0.0;
        }
        return this.getNumJSFrames() * 1.0E9 / (this.mLastFrameTime - this.mFirstFrameTime);
    }
    
    public int getNumFrames() {
        return this.mNumFrameCallbacks - 1;
    }
    
    public int getNumJSFrames() {
        return this.mNumFrameCallbacksWithBatchDispatches - 1;
    }
    
    public int getTotalTimeMS() {
        return (int)(this.mLastFrameTime - this.mFirstFrameTime) / 1000000;
    }
    
    public void start() {
        this.mShouldStop = false;
        this.mReactContext.getCatalystInstance().addBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mChoreographer.postFrameCallback((Choreographer$FrameCallback)this);
    }
    
    public void startAndRecordFpsAtEachFrame() {
        this.mTimeToFps = new TreeMap<Long, FpsDebugFrameCallback$FpsInfo>();
        this.mIsRecordingFpsInfoAtEachFrame = true;
        this.start();
    }
    
    public void stop() {
        this.mShouldStop = true;
        this.mReactContext.getCatalystInstance().removeBridgeIdleDebugListener(this.mDidJSUpdateUiDuringFrameDetector);
        this.mUIManagerModule.setViewHierarchyUpdateDebugListener(null);
    }
}
