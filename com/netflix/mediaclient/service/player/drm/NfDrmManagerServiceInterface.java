// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.util.Triple;
import java.util.List;

public interface NfDrmManagerServiceInterface
{
    void clear(final Long p0);
    
    void clearAll();
    
    void onUiHidden();
    
    void prepare(final List<Triple<Long, Integer, PlayContext>> p0);
    
    void release();
}
