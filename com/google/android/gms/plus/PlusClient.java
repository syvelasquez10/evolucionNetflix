// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.common.ConnectionResult;
import android.content.Context;
import com.google.android.gms.internal.hv;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.api.Status;
import java.util.Collection;
import android.net.Uri;
import com.google.android.gms.common.api.a;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.internal.hs;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public class PlusClient implements GooglePlayServicesClient
{
    final hs Du;
    
    PlusClient(final hs du) {
        this.Du = du;
    }
    
    @Deprecated
    public void clearDefaultAccount() {
        this.Du.clearDefaultAccount();
    }
    
    @Deprecated
    @Override
    public void connect() {
        this.Du.connect();
    }
    
    @Deprecated
    @Override
    public void disconnect() {
        this.Du.disconnect();
    }
    
    hs eK() {
        return this.Du;
    }
    
    @Deprecated
    public String getAccountName() {
        return this.Du.getAccountName();
    }
    
    @Deprecated
    public Person getCurrentPerson() {
        return this.Du.getCurrentPerson();
    }
    
    @Deprecated
    @Override
    public boolean isConnected() {
        return this.Du.isConnected();
    }
    
    @Deprecated
    @Override
    public boolean isConnecting() {
        return this.Du.isConnecting();
    }
    
    @Deprecated
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.Du.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.Du.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener onMomentsLoadedListener) {
        this.Du.j(new a.c<Moments.LoadMomentsResult>() {
            public void a(final Moments.LoadMomentsResult loadMomentsResult) {
                onMomentsLoadedListener.onMomentsLoaded(loadMomentsResult.getStatus().bu(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        });
    }
    
    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener onMomentsLoadedListener, final int n, final String s, final Uri uri, final String s2, final String s3) {
        this.Du.a(new a.c<Moments.LoadMomentsResult>() {
            public void a(final Moments.LoadMomentsResult loadMomentsResult) {
                onMomentsLoadedListener.onMomentsLoaded(loadMomentsResult.getStatus().bu(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        }, n, s, uri, s2, s3);
    }
    
    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener onPeopleLoadedListener, final Collection<String> collection) {
        this.Du.a(new a.c<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().bu(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, collection);
    }
    
    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener onPeopleLoadedListener, final String... array) {
        this.Du.a(new a.c<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().bu(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, array);
    }
    
    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener onPeopleLoadedListener, final int n, final String s) {
        this.Du.a(new a.c<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().bu(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, n, s);
    }
    
    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener onPeopleLoadedListener, final String s) {
        this.Du.i(new a.c<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().bu(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, s);
    }
    
    @Deprecated
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Du.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Du.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void removeMoment(final String s) {
        this.Du.removeMoment(s);
    }
    
    @Deprecated
    public void revokeAccessAndDisconnect(final OnAccessRevokedListener onAccessRevokedListener) {
        this.Du.l(new a.c<Status>() {
            public void a(final Status status) {
                onAccessRevokedListener.onAccessRevoked(status.getStatus().bu());
            }
        });
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.Du.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.Du.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void writeMoment(final Moment moment) {
        this.Du.writeMoment(moment);
    }
    
    @Deprecated
    public static class Builder
    {
        private final hv DA;
        private final ConnectionCallbacks Dz;
        private final OnConnectionFailedListener jE;
        private final Context mContext;
        
        public Builder(final Context mContext, final ConnectionCallbacks dz, final OnConnectionFailedListener je) {
            this.mContext = mContext;
            this.Dz = dz;
            this.jE = je;
            this.DA = new hv(this.mContext);
        }
        
        public PlusClient build() {
            return new PlusClient(new hs(this.mContext, this.Dz, this.jE, this.DA.eZ()));
        }
        
        public Builder clearScopes() {
            this.DA.eY();
            return this;
        }
        
        public Builder setAccountName(final String s) {
            this.DA.aA(s);
            return this;
        }
        
        public Builder setActions(final String... array) {
            this.DA.e(array);
            return this;
        }
        
        public Builder setScopes(final String... array) {
            this.DA.d(array);
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
