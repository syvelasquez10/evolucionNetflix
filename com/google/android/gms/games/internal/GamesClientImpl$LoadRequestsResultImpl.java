// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import java.util.Iterator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.RequestType;
import com.google.android.gms.games.request.GameRequestBuffer;
import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$LoadRequestsResult;

final class GamesClientImpl$LoadRequestsResultImpl implements Requests$LoadRequestsResult
{
    private final Status CM;
    private final Bundle WQ;
    
    GamesClientImpl$LoadRequestsResultImpl(final Status cm, final Bundle wq) {
        this.CM = cm;
        this.WQ = wq;
    }
    
    @Override
    public GameRequestBuffer getRequests(final int n) {
        final String dh = RequestType.dH(n);
        if (!this.WQ.containsKey(dh)) {
            return null;
        }
        return new GameRequestBuffer((DataHolder)this.WQ.get(dh));
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    @Override
    public void release() {
        final Iterator<String> iterator = this.WQ.keySet().iterator();
        while (iterator.hasNext()) {
            final DataHolder dataHolder = (DataHolder)this.WQ.getParcelable((String)iterator.next());
            if (dataHolder != null) {
                dataHolder.close();
            }
        }
    }
}
