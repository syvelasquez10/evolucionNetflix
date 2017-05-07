// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Notifications;

public final class NotificationsImpl implements Notifications
{
    @Override
    public void clear(final GoogleApiClient googleApiClient, final int n) {
        Games.c(googleApiClient).dC(n);
    }
    
    @Override
    public void clearAll(final GoogleApiClient googleApiClient) {
        this.clear(googleApiClient, 31);
    }
    
    private abstract static class ContactSettingLoadImpl extends BaseGamesApiMethodImpl<ContactSettingLoadResult>
    {
        public ContactSettingLoadResult aa(final Status status) {
            return new ContactSettingLoadResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class ContactSettingUpdateImpl extends BaseGamesApiMethodImpl<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
    
    private abstract static class InboxCountImpl extends BaseGamesApiMethodImpl<InboxCountResult>
    {
        public InboxCountResult ab(final Status status) {
            return new InboxCountResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
}
