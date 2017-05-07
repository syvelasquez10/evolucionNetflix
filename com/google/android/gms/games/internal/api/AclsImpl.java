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
    private static LoadAclResult v(final Status status) {
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
        public LoadAclResult x(final Status status) {
            return v(status);
        }
    }
    
    private abstract static class UpdateNotifyAclImpl extends BaseGamesApiMethodImpl<Status>
    {
        public Status f(final Status status) {
            return status;
        }
    }
}
