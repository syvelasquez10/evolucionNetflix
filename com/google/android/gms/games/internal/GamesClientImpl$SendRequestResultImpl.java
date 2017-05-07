// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.g;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.Requests$SendRequestResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$SendRequestResultImpl extends a implements Requests$SendRequestResult
{
    private final GameRequest Xq;
    
    GamesClientImpl$SendRequestResultImpl(DataHolder dataHolder) {
        super(dataHolder);
        dataHolder = (DataHolder)new GameRequestBuffer(dataHolder);
        try {
            if (((g)dataHolder).getCount() > 0) {
                this.Xq = ((g<GameRequest>)dataHolder).get(0).freeze();
            }
            else {
                this.Xq = null;
            }
        }
        finally {
            ((DataBuffer)dataHolder).release();
        }
    }
}
