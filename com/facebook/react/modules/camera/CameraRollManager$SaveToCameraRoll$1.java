// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Promise;
import android.content.Context;
import com.facebook.react.bridge.GuardedAsyncTask;
import android.net.Uri;
import android.media.MediaScannerConnection$OnScanCompletedListener;

class CameraRollManager$SaveToCameraRoll$1 implements MediaScannerConnection$OnScanCompletedListener
{
    final /* synthetic */ CameraRollManager$SaveToCameraRoll this$0;
    
    CameraRollManager$SaveToCameraRoll$1(final CameraRollManager$SaveToCameraRoll this$0) {
        this.this$0 = this$0;
    }
    
    public void onScanCompleted(final String s, final Uri uri) {
        if (uri != null) {
            this.this$0.mPromise.resolve(uri.toString());
            return;
        }
        this.this$0.mPromise.reject("E_UNABLE_TO_SAVE", "Could not add image to gallery");
    }
}
