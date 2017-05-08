// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import android.annotation.SuppressLint;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.view.View;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.view.TextureView;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.widget.RelativeLayout;

public class VideoWindowForPostplayWithScaling implements VideoWindowForPostplay
{
    protected static String TAG;
    private final int END_MARGIN_LEFT_DP;
    private final int END_MARGIN_TOP_DP;
    private final int END_WIDTH_DP;
    protected PlayerFragment mContext;
    protected VideoWindowForPostplayWithScaling$SurfaceState mOriginalSurfaceState;
    protected VideoWindowForPostplayWithAnimation$ScaleAnimationParameters mParams;
    protected RelativeLayout mParent;
    protected TappableSurfaceView mSurface;
    protected TextureView mSurface2;
    
    static {
        VideoWindowForPostplayWithScaling.TAG = "nf_postplay";
    }
    
    VideoWindowForPostplayWithScaling(final PlayerFragment mContext) {
        this.END_MARGIN_TOP_DP = 12;
        this.END_MARGIN_LEFT_DP = 12;
        this.END_WIDTH_DP = 300;
        this.mContext = mContext;
        final View view = mContext.getView();
        this.mSurface = (TappableSurfaceView)view.findViewById(2131624490);
        this.mSurface2 = (TextureView)view.findViewById(2131624491);
        this.mParent = (RelativeLayout)view.findViewById(2131624244);
        if (this.mSurface == null) {
            Log.w(VideoWindowForPostplayWithScaling.TAG, "PostPlayWithScaling:: surface not found");
        }
        if (this.mSurface2 == null) {
            Log.w(VideoWindowForPostplayWithScaling.TAG, "PostPlayWithScaling:: surface2 not found");
        }
        if (this.mParent == null) {
            Log.w(VideoWindowForPostplayWithScaling.TAG, "PostPlayWithScaling:: rootFrame not found");
        }
        this.mParams = new VideoWindowForPostplayWithAnimation$ScaleAnimationParameters(0, 0, 0, 1.0f, AndroidUtils.dipToPixels((Context)this.mContext.getActivity(), 12), AndroidUtils.dipToPixels((Context)this.mContext.getActivity(), 12), AndroidUtils.dipToPixels((Context)this.mContext.getActivity(), 300) / DeviceUtils.getScreenWidthInPixels((Context)this.mContext.getActivity()));
    }
    
    protected void addCenterInParent(final View view) {
        if (view != null) {
            final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)view.getLayoutParams();
            layoutParams.addRule(13, -1);
            view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        }
    }
    
    @Override
    public void animateIn() {
        Log.d(VideoWindowForPostplayWithScaling.TAG, "PostPlayWithScaling:: doTransitionToPostPlay starts");
        this.mOriginalSurfaceState = this.getCurrentSurfaceState();
        if (Log.isLoggable()) {
            Log.d(VideoWindowForPostplayWithScaling.TAG, "doTransitionToPostPlay bottom margin: " + this.mOriginalSurfaceState.bottomMargin);
            Log.d(VideoWindowForPostplayWithScaling.TAG, "doTransitionToPostPlay top margin: " + this.mOriginalSurfaceState.topMargin);
            Log.d(VideoWindowForPostplayWithScaling.TAG, "doTransitionToPostPlay left margin: " + this.mOriginalSurfaceState.leftMargin);
            Log.d(VideoWindowForPostplayWithScaling.TAG, "doTransitionToPostPlay right margin: " + this.mOriginalSurfaceState.rightMargin);
            Log.d(VideoWindowForPostplayWithScaling.TAG, "doTransitionToPostPlay new left margin: " + 0);
            Log.d(VideoWindowForPostplayWithScaling.TAG, "doTransitionToPostPlay new top margin: " + 0);
        }
        this.removeCenterInParent((View)this.mSurface);
        this.removeCenterInParent((View)this.mSurface2);
        this.resizeVideo(0, 0, this.mParams.getEndScale());
        Log.d(VideoWindowForPostplayWithScaling.TAG, "PostPlayWithScaling:: doTransitionToPostPlay ends after request layout");
    }
    
    @Override
    public void animateOut(final Runnable runnable) {
        Log.d(VideoWindowForPostplayWithScaling.TAG, "PostPlayWithScaling:: doTransitionFromPostPlay starts");
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.mSurface.getLayoutParams();
        if (this.mOriginalSurfaceState == null) {
            Log.e(VideoWindowForPostplayWithScaling.TAG, "Previos state unknown");
            this.mOriginalSurfaceState = new VideoWindowForPostplayWithScaling$SurfaceState(0, 0, 0, 0, 0);
        }
        layoutParams.addRule(13, -1);
        layoutParams.setMargins(this.mOriginalSurfaceState.leftMargin, this.mOriginalSurfaceState.topMargin, this.mOriginalSurfaceState.rightMargin, this.mOriginalSurfaceState.bottomMargin);
        this.mSurface.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mSurface.setMode(this.mOriginalSurfaceState.surfaceMode);
        this.mSurface.postInvalidate();
        if (this.mSurface2 != null) {
            final RelativeLayout$LayoutParams layoutParams2 = (RelativeLayout$LayoutParams)this.mSurface2.getLayoutParams();
            layoutParams2.addRule(13, -1);
            layoutParams2.setMargins(this.mOriginalSurfaceState.leftMargin, this.mOriginalSurfaceState.topMargin, this.mOriginalSurfaceState.rightMargin, this.mOriginalSurfaceState.bottomMargin);
            this.mSurface2.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
            this.mSurface2.postInvalidate();
        }
        Log.d(VideoWindowForPostplayWithScaling.TAG, "PostPlayWithScaling:: doTransitionFromPostPlay ends after request layout");
    }
    
    @Override
    public boolean canVideoVindowResize() {
        return true;
    }
    
    protected VideoWindowForPostplayWithScaling$SurfaceState getCurrentSurfaceState() {
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.mSurface.getLayoutParams();
        return new VideoWindowForPostplayWithScaling$SurfaceState(relativeLayout$LayoutParams.bottomMargin, relativeLayout$LayoutParams.topMargin, relativeLayout$LayoutParams.leftMargin, relativeLayout$LayoutParams.rightMargin, this.mSurface.getMode());
    }
    
    protected VideoWindowForPostplayWithAnimation$ScaleAnimationParameters getTransitionToPostPlayAnimationParameters() {
        return this.mParams;
    }
    
    @SuppressLint({ "NewApi" })
    protected void removeCenterInParent(final View view) {
        if (view == null) {
            return;
        }
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)view.getLayoutParams();
        if (AndroidUtils.getAndroidVersion() >= 17) {
            layoutParams.removeRule(13);
        }
        else {
            layoutParams.addRule(13, 0);
        }
        view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    protected void resizeSurfaceView(final int n, final int n2, final float scale) {
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.mSurface.getLayoutParams();
        layoutParams.setMargins(n, n2, 0, 0);
        this.mSurface.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mSurface.setScale(scale);
        this.mSurface.postInvalidate();
    }
    
    protected void resizeTextureView(final int n, final int n2) {
        if (this.mSurface2 != null) {
            final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.mSurface2.getLayoutParams();
            layoutParams.setMargins(n, n2, 0, 0);
            this.mSurface2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.mSurface2.postInvalidate();
        }
    }
    
    protected void resizeVideo(final int n, final int n2, final float n3) {
        this.mContext.runOnUiThread(new VideoWindowForPostplayWithScaling$1(this, n, n2, n3));
    }
    
    @Override
    public void setVisible(final boolean b) {
        if (b) {
            ViewUtils.setVisibility((View)this.mSurface, ViewUtils$Visibility.VISIBLE);
            ViewUtils.setVisibility((View)this.mSurface2, ViewUtils$Visibility.VISIBLE);
            return;
        }
        ViewUtils.setVisibility((View)this.mSurface, ViewUtils$Visibility.INVISIBLE);
        ViewUtils.setVisibility((View)this.mSurface2, ViewUtils$Visibility.INVISIBLE);
    }
}
