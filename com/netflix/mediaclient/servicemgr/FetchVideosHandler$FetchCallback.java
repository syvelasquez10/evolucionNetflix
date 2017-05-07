// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;

public interface FetchVideosHandler$FetchCallback<T>
{
    long getRequestId();
    
    void onErrorResponse();
    
    void onNoVideosInResponse();
    
    void updateDataSet(final List<T> p0, final String p1, final int p2, final int p3);
}
