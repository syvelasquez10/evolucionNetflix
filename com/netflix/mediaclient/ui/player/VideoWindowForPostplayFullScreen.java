// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import android.view.View;
import com.netflix.mediaclient.Log;
import android.view.TextureView;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.widget.RelativeLayout;

public class VideoWindowForPostplayFullScreen implements VideoWindowForPostplay
{
    protected static String TAG;
    protected PlayerFragment mContext;
    protected RelativeLayout mParent;
    protected TappableSurfaceView mSurface;
    protected TextureView mSurface2;
    
    static {
        VideoWindowForPostplayFullScreen.TAG = "nf_postplay";
    }
    
    public VideoWindowForPostplayFullScreen(final PlayerFragment mContext) {
        this.mContext = mContext;
        final View view = this.mContext.getView();
        this.mSurface = (TappableSurfaceView)view.findViewById(2131690087);
        this.mSurface2 = (TextureView)view.findViewById(2131690088);
        this.mParent = (RelativeLayout)view.findViewById(2131689831);
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
            ViewUtils.setVisibility((View)this.mSurface, ViewUtils$Visibility.VISIBLE);
            ViewUtils.setVisibility((View)this.mSurface2, ViewUtils$Visibility.VISIBLE);
            return;
        }
        ViewUtils.setVisibility((View)this.mSurface, ViewUtils$Visibility.INVISIBLE);
        ViewUtils.setVisibility((View)this.mSurface2, ViewUtils$Visibility.INVISIBLE);
    }
}
