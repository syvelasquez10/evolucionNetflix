// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.internal.game.Acls;

public final class AclsImpl implements Acls
{
    private static LoadAclResult L(final Status status) {
        return new LoadAclResult() {
            @Override
            public Status getStatus() {
                return status;
            }
            
            @Override
            public void release() {
            }
        };
    }
    
    private abstract static class LoadNotifyAclImpl extends BaseGamesApiMethodImpl<LoadAclResult>
    {
        public LoadAclResult N(final Status status) {
            return L(status);
        }
    }
    
    private abstract static class UpdateNotifyAclImpl extends BaseGamesApiMethodImpl<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
}
