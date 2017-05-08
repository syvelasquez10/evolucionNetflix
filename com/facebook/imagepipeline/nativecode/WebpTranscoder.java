// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.nativecode;

import java.io.OutputStream;
import java.io.InputStream;
import com.facebook.imageformat.ImageFormat;

public interface WebpTranscoder
{
    boolean isWebpNativelySupported(final ImageFormat p0);
    
    void transcodeWebpToJpeg(final InputStream p0, final OutputStream p1, final int p2);
    
    void transcodeWebpToPng(final InputStream p0, final OutputStream p1);
}
