// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import android.graphics.Bitmap;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.LinkedList;

class ImageLoader$BatchedImageRequest
{
    private final LinkedList<ImageLoader$ImageContainer> mContainers;
    private VolleyError mError;
    private final Request<?> mRequest;
    private Bitmap mResponseBitmap;
    final /* synthetic */ ImageLoader this$0;
    
    public ImageLoader$BatchedImageRequest(final ImageLoader this$0, final Request<?> mRequest, final ImageLoader$ImageContainer imageLoader$ImageContainer) {
        this.this$0 = this$0;
        this.mContainers = new LinkedList<ImageLoader$ImageContainer>();
        this.mRequest = mRequest;
        this.mContainers.add(imageLoader$ImageContainer);
    }
    
    public void addContainer(final ImageLoader$ImageContainer imageLoader$ImageContainer) {
        this.mContainers.add(imageLoader$ImageContainer);
    }
    
    public VolleyError getError() {
        return this.mError;
    }
    
    public boolean removeContainerAndCancelIfNecessary(final ImageLoader$ImageContainer imageLoader$ImageContainer) {
        this.mContainers.remove(imageLoader$ImageContainer);
        if (this.mContainers.size() == 0) {
            this.mRequest.cancel();
            return true;
        }
        return false;
    }
    
    public void setError(final VolleyError mError) {
        this.mError = mError;
    }
}
