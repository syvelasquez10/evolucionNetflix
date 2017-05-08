// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.decoder;

import java.util.Collections;
import java.util.List;

class SimpleProgressiveJpegConfig$DefaultDynamicValueConfig implements SimpleProgressiveJpegConfig$DynamicValueConfig
{
    @Override
    public int getGoodEnoughScanNumber() {
        return 0;
    }
    
    @Override
    public List<Integer> getScansToDecode() {
        return (List<Integer>)Collections.EMPTY_LIST;
    }
}
