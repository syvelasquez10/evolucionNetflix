// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.view.TextureView;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.widget.RelativeLayout;

public class VideoWindowForPostplayFullScreen implements VideoWindowForPostplay
{
    protected static String TAG;
    protected PlayerActivity mContext;
    protected RelativeLayout mParent;
    protected TappableSurfaceView mSurface;
    protected TextureView mSurface2;
    
    static {
        VideoWindowForPostplayFullScreen.TAG = "nf_postplay";
    }
    
    public VideoWindowForPostplayFullScreen(final PlayerActivity mContext) {
        this.mContext = mContext;
        this.mSurface = (TappableSurfaceView)mContext.findViewById(2131165505);
        this.mSurface2 = (TextureView)mContext.findViewById(2131165510);
        this.mParent = (RelativeLayout)mContext.findViewById(2131165433);
        if (this.mSurface == null) {
            Log.w(VideoWindowForPostplayFullScreen.TAG, "PostPlayWithScaling:: surface not found");
        }
        if (this.mSurface2 == null) {
            Log.w(VideoWindowForPostplayFullScreen.TAG, "PostPlayWithScaling:: surface2 not found");
        }
        if (this.mParent == null) {
            Log.w(VideoWindowForPostplayFullScreen.TAG, "PostPlayWithScaling:: rootFrame not found");
        }
    }
    
    @Override
    public void animateIn() {
    }
    
    @Override
    public void animateOut(final Runnable runnable) {
    }
    
    @Override
    public boolean canVideoVindowResize() {
        return false;
    }
    
    @Override
    public void setVisible(final boolean b) {
        if (b) {
            ViewUtils.setVisibility((View)this.mSurface, ViewUtils.Visibility.VISIBLE);
            ViewUtils.setVisibility((View)this.mSurface2, ViewUtils.Visibility.VISIBLE);
            return;
        }
        ViewUtils.setVisibility((View)this.mSurface, ViewUtils.Visibility.INVISIBLE);
        ViewUtils.setVisibility((View)this.mSurface2, ViewUtils.Visibility.INVISIBLE);
    }
}
