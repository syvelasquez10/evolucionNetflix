// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.image.QualityInfo;

public interface ProgressiveJpegConfig
{
    int getNextScanNumberToDecode(final int p0);
    
    QualityInfo getQualityInfo(final int p0);
}
