// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.widget.ImageView;
import android.graphics.Bitmap;

class KongInteractivePostPlayManager$3 implements Runnable
{
    final /* synthetic */ KongInteractivePostPlayManager this$0;
    final /* synthetic */ Bitmap val$bitmap;
    final /* synthetic */ ImageView val$imageView;
    
    KongInteractivePostPlayManager$3(final KongInteractivePostPlayManager this$0, final ImageView val$imageView, final Bitmap val$bitmap) {
        this.this$0 = this$0;
        this.val$imageView = val$imageView;
        this.val$bitmap = val$bitmap;
    }
    
    @Override
    public void run() {
        if (this.val$imageView != null) {
            this.val$imageView.setImageBitmap(this.val$bitmap);
        }
    }
}
