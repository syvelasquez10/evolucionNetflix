// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.internal.game.Acls$LoadAclResult;

final class AclsImpl$1 implements Acls$LoadAclResult
{
    final /* synthetic */ Status CW;
    
    AclsImpl$1(final Status cw) {
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
