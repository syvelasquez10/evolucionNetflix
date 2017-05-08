// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.webp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.Rect;
import java.io.FileDescriptor;

public interface WebpBitmapFactory
{
    Bitmap decodeFileDescriptor(final FileDescriptor p0, final Rect p1, final BitmapFactory$Options p2);
}
