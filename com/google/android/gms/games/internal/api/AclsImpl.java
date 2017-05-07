// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.internal.game.Acls$LoadAclResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.internal.game.Acls;

public final class AclsImpl implements Acls
{
    private static Acls$LoadAclResult L(final Status status) {
        return new AclsImpl$1(status);
    }
}
