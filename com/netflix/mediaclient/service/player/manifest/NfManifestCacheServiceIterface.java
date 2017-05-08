// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.manifest;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import java.util.List;

public interface NfManifestCacheServiceIterface
{
    void clearAll();
    
    void onBackgroundTrimMem();
    
    void prepare(final List<Triple<Long, Integer, PlayContext>> p0);
    
    void release();
}
