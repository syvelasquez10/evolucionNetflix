// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import android.graphics.SurfaceTexture;
import com.facebook.react.uimanager.UIViewOperationQueue;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.Rect;
import com.facebook.react.uimanager.ReactShadowNode;
import android.view.Surface;
import android.view.TextureView$SurfaceTextureListener;
import com.facebook.react.uimanager.LayoutShadowNode;

public class ARTSurfaceViewShadowNode extends LayoutShadowNode implements TextureView$SurfaceTextureListener
{
    private Surface mSurface;
    
    private void drawOutput() {
        if (this.mSurface == null || !this.mSurface.isValid()) {
            this.markChildrenUpdatesSeen(this);
        }
        else {
            try {
                final Canvas lockCanvas = this.mSurface.lockCanvas((Rect)null);
                lockCanvas.drawColor(0, PorterDuff$Mode.CLEAR);
                final Paint paint = new Paint();
                for (int i = 0; i < this.getChildCount(); ++i) {
                    final ARTVirtualNode artVirtualNode = (ARTVirtualNode)this.getChildAt(i);
                    artVirtualNode.draw(lockCanvas, paint, 1.0f);
                    artVirtualNode.markUpdateSeen();
                }
                if (this.mSurface != null) {
                    this.mSurface.unlockCanvasAndPost(lockCanvas);
                }
            }
            catch (IllegalArgumentException ex) {}
            catch (IllegalStateException lockCanvas) {
                goto Label_0105;
            }
        }
    }
    
    private void markChildrenUpdatesSeen(final ReactShadowNode reactShadowNode) {
        for (int i = 0; i < reactShadowNode.getChildCount(); ++i) {
            final ReactShadowNode child = reactShadowNode.getChildAt(i);
            child.markUpdateSeen();
            this.markChildrenUpdatesSeen(child);
        }
    }
    
    public boolean isVirtual() {
        return false;
    }
    
    public boolean isVirtualAnchor() {
        return true;
    }
    
    public void onCollectExtraUpdates(final UIViewOperationQueue uiViewOperationQueue) {
        super.onCollectExtraUpdates(uiViewOperationQueue);
        this.drawOutput();
        uiViewOperationQueue.enqueueUpdateExtraData(this.getReactTag(), this);
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        this.mSurface = new Surface(surfaceTexture);
        this.drawOutput();
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        surfaceTexture.release();
        this.mSurface = null;
        return true;
    }
    
    public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
    }
    
    public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
    }
}
