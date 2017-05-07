// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.BaseImplementation$b;

class aw$5 extends a
{
    final /* synthetic */ aw avI;
    final /* synthetic */ BaseImplementation$b avK;
    
    aw$5(final aw avI, final BaseImplementation$b avK) {
        this.avI = avI;
        this.avK = avK;
    }
    
    @Override
    public void aa(final DataHolder dataHolder) {
        this.avK.b(new DataItemBuffer(dataHolder));
    }
}
