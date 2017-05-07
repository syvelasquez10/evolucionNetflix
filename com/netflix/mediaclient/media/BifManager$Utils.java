// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;
import android.widget.ImageView;

public class BifManager$Utils
{
    public static void showBifInView(final BifManager bifManager, final int n, final ImageView imageView) {
        showBifInView(bifManager.getIndexFrame(n), imageView);
    }
    
    public static void showBifInView(final ByteBuffer byteBuffer, final ImageView imageView) {
        if (imageView == null) {
            Log.w("BifManager", "View is null");
            return;
        }
        if (byteBuffer == null) {
            Log.v("BifManager", "ByteBuffer is null");
            return;
        }
        if (imageView.getVisibility() != 0) {
            imageView.setVisibility(0);
        }
        final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        if (decodeByteArray != null) {
            imageView.setImageBitmap(decodeByteArray);
            return;
        }
        Log.w("BifManager", "decoded bif bitmap is null");
    }
}
