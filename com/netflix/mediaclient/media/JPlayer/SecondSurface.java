// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Log;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup$LayoutParams;
import android.annotation.TargetApi;
import android.view.TextureView$SurfaceTextureListener;

@TargetApi(14)
public class SecondSurface implements TextureView$SurfaceTextureListener
{
    private static final String TAG = "JPlayer_Surface2";
    private final ViewGroup$LayoutParams layoutParams;
    private final TextureView mSurface2;
    private int mSurfaceTextureHeight;
    private int mSurfaceTextureWidth;
    
    public SecondSurface(final TextureView mSurface2) {
        this.mSurfaceTextureWidth = 0;
        this.mSurfaceTextureHeight = 0;
        (this.mSurface2 = mSurface2).setSurfaceTextureListener((TextureView$SurfaceTextureListener)this);
        this.layoutParams = this.mSurface2.getLayoutParams();
        this.mSurface2.setVisibility(0);
        this.mSurface2.setAlpha(0.0f);
    }
    
    public final Surface getSurface() {
        if (this.mSurface2.isAvailable()) {
            return new Surface(this.mSurface2.getSurfaceTexture());
        }
        return null;
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        if (Log.isLoggable("JPlayer_Surface2", 3)) {
            Log.d("JPlayer_Surface2", "onSurfaceTextureAvailable " + n + " x " + n2);
        }
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        Log.d("JPlayer_Surface2", "onSurfaceTextureDestroyed");
        return true;
    }
    
    public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        if (Log.isLoggable("JPlayer_Surface2", 3)) {
            Log.d("JPlayer_Surface2", "onSurfaceTextureSizeChanged " + n + " x " + n2);
        }
    }
    
    public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
    }
    
    public void setSurfaceInvisible() {
        Log.d("JPlayer_Surface2", "Surface2 invisiable");
        this.mSurface2.setAlpha(0.0f);
    }
    
    public void setSurfaceSize(final int n, final int n2) {
        if (this.mSurfaceTextureWidth != n || this.mSurfaceTextureHeight != n2) {
            this.layoutParams.width = n;
            this.layoutParams.height = n2;
            this.mSurface2.setLayoutParams(this.layoutParams);
            this.mSurface2.requestLayout();
            if (Log.isLoggable("JPlayer_Surface2", 3)) {
                Log.d("JPlayer_Surface2", "layout " + this.layoutParams.width + " x " + this.layoutParams.height);
            }
            this.mSurfaceTextureWidth = n;
            this.mSurfaceTextureHeight = n2;
        }
    }
    
    public void setSurfaceVisible() {
        Log.d("JPlayer_Surface2", "Surface2 visiable");
        this.mSurface2.setAlpha(1.0f);
    }
}
