// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import android.graphics.Bitmap;
import java.util.concurrent.CountDownLatch;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;

class FalkorAgent$18 implements ImageLoader$ImageLoaderListener
{
    final /* synthetic */ FalkorAgent this$0;
    final /* synthetic */ CountDownLatch val$videoImagesRequest;
    
    FalkorAgent$18(final FalkorAgent this$0, final CountDownLatch val$videoImagesRequest) {
        this.this$0 = this$0;
        this.val$videoImagesRequest = val$videoImagesRequest;
    }
    
    private void countDownRequests() {
        this.val$videoImagesRequest.countDown();
    }
    
    @Override
    public void onErrorResponse(final String s) {
        this.countDownRequests();
    }
    
    @Override
    public void onResponse(final Bitmap bitmap, final String s) {
        this.countDownRequests();
    }
}
