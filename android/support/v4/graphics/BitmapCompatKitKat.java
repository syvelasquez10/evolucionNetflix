// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.annotation.TargetApi;

@TargetApi(19)
class BitmapCompatKitKat
{
    static int getAllocationByteCount(final Bitmap bitmap) {
        return bitmap.getAllocationByteCount();
    }
}
