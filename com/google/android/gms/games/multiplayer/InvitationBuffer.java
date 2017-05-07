// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class InvitationBuffer extends g<Invitation>
{
    public InvitationBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    protected String gE() {
        return "external_invitation_id";
    }
    
    protected Invitation j(final int n, final int n2) {
        return new InvitationRef(this.IC, n, n2);
    }
}
