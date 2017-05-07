// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.common.ConnectionResult;
import android.content.Context;
import com.google.android.gms.plus.internal.i;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.api.Status;
import java.util.Collection;
import android.net.Uri;
import com.google.android.gms.common.api.a;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public class PlusClient implements GooglePlayServicesClient
{
    final e TL;
    
    PlusClient(final e tl) {
        this.TL = tl;
    }
    
    @Deprecated
    public void clearDefaultAccount() {
        this.TL.clearDefaultAccount();
    }
    
    @Deprecated
    @Override
    public void connect() {
        this.TL.connect();
    }
    
    @Deprecated
    @Override
    public void disconnect() {
        this.TL.disconnect();
    }
    
    @Deprecated
    public String getAccountName() {
        return this.TL.getAccountName();
    }
    
    @Deprecated
    public Person getCurrentPerson() {
        return this.TL.getCurrentPerson();
    }
    
    e iI() {
        return this.TL;
    }
    
    @Deprecated
    @Override
    public boolean isConnected() {
        return this.TL.isConnected();
    }
    
    @Deprecated
    @Override
    public boolean isConnecting() {
        return this.TL.isConnecting();
    }
    
    @Deprecated
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.TL.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.TL.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener onMomentsLoadedListener) {
        this.TL.l(new a.d<Moments.LoadMomentsResult>() {
            public void a(final Moments.LoadMomentsResult loadMomentsResult) {
                onMomentsLoadedListener.onMomentsLoaded(loadMomentsResult.getStatus().eq(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        });
    }
    
    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener onMomentsLoadedListener, final int n, final String s, final Uri uri, final String s2, final String s3) {
        this.TL.a(new a.d<Moments.LoadMomentsResult>() {
            public void a(final Moments.LoadMomentsResult loadMomentsResult) {
                onMomentsLoadedListener.onMomentsLoaded(loadMomentsResult.getStatus().eq(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        }, n, s, uri, s2, s3);
    }
    
    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener onPeopleLoadedListener, final Collection<String> collection) {
        this.TL.a(new a.d<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().eq(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, collection);
    }
    
    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener onPeopleLoadedListener, final String... array) {
        this.TL.d(new a.d<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().eq(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, array);
    }
    
    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener onPeopleLoadedListener, final int n, final String s) {
        this.TL.a(new a.d<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().eq(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, n, s);
    }
    
    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener onPeopleLoadedListener, final String s) {
        this.TL.o(new a.d<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().eq(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, s);
    }
    
    @Deprecated
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.TL.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.TL.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void removeMoment(final String s) {
        this.TL.removeMoment(s);
    }
    
    @Deprecated
    public void revokeAccessAndDisconnect(final OnAccessRevokedListener onAccessRevokedListener) {
        this.TL.n(new a.d<Status>() {
            public void Y(final Status status) {
                onAccessRevokedListener.onAccessRevoked(status.getStatus().eq());
            }
        });
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.TL.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.TL.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void writeMoment(final Moment moment) {
        this.TL.a(null, moment);
    }
    
    @Deprecated
    public static class Builder
    {
        private final ConnectionCallbacks TQ;
        private final OnConnectionFailedListener TR;
        private final i TS;
        private final Context mContext;
        
        public Builder(final Context mContext, final ConnectionCallbacks tq, final OnConnectionFailedListener tr) {
            this.mContext = mContext;
            this.TQ = tq;
            this.TR = tr;
            this.TS = new i(this.mContext);
        }
        
        public PlusClient build() {
            return new PlusClient(new e(this.mContext, this.TQ, this.TR, this.TS.iZ()));
        }
        
        public Builder clearScopes() {
            this.TS.iY();
            return this;
        }
        
        public Builder setAccountName(final String s) {
            this.TS.bh(s);
            return this;
        }
        
        public Builder setActions(final String... array) {
            this.TS.f(array);
            return this;
        }
        
        public Builder setScopes(final String... array) {
            this.TS.e(array);
            return this;
        }
    }
    
    @Deprecated
    public interface OnAccessRevokedListener
    {
        void onAccessRevoked(final ConnectionResult p0);
    }
    
    @Deprecated
    public interface OnMomentsLoadedListener
    {
        @Deprecated
        void onMomentsLoaded(final ConnectionResult p0, final MomentBuffer p1, final String p2, final String p3);
    }
    
    @Deprecated
    public interface OnPeopleLoadedListener
    {
        void onPeopleLoaded(final ConnectionResult p0, final PersonBuffer p1, final String p2);
    }
    
    @Deprecated
    public interface OrderBy
    {
        @Deprecated
        public static final int ALPHABETICAL = 0;
        @Deprecated
        public static final int BEST = 1;
    }
}
