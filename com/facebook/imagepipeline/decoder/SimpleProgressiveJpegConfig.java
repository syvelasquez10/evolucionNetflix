// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import java.util.List;
import com.facebook.common.internal.Preconditions;

public class SimpleProgressiveJpegConfig implements ProgressiveJpegConfig
{
    private final SimpleProgressiveJpegConfig$DynamicValueConfig mDynamicValueConfig;
    
    public SimpleProgressiveJpegConfig() {
        this(new SimpleProgressiveJpegConfig$DefaultDynamicValueConfig(null));
    }
    
    public SimpleProgressiveJpegConfig(final SimpleProgressiveJpegConfig$DynamicValueConfig simpleProgressiveJpegConfig$DynamicValueConfig) {
        this.mDynamicValueConfig = Preconditions.checkNotNull(simpleProgressiveJpegConfig$DynamicValueConfig);
    }
    
    @Override
    public int getNextScanNumberToDecode(final int n) {
        final List<Integer> scansToDecode = this.mDynamicValueConfig.getScansToDecode();
        if (scansToDecode == null || scansToDecode.isEmpty()) {
            return n + 1;
        }
        for (int i = 0; i < scansToDecode.size(); ++i) {
            if (scansToDecode.get(i) > n) {
                return scansToDecode.get(i);
            }
        }
        return Integer.MAX_VALUE;
    }
    
    @Override
    public QualityInfo getQualityInfo(final int n) {
        return ImmutableQualityInfo.of(n, n >= this.mDynamicValueConfig.getGoodEnoughScanNumber(), false);
    }
}
