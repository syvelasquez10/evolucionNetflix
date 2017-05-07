// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class InvitationBuffer extends d<Invitation>
{
    public InvitationBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected Invitation getEntry(final int n, final int n2) {
        return new InvitationRef(this.BB, n, n2);
    }
    
    @Override
    protected String getPrimaryDataMarkerColumn() {
        return "external_invitation_id";
    }
}
