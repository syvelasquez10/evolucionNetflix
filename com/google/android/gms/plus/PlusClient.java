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
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public class PlusClient implements GooglePlayServicesClient
{
    final e akS;
    
    PlusClient(final e akS) {
        this.akS = akS;
    }
    
    @Deprecated
    public void clearDefaultAccount() {
        this.akS.clearDefaultAccount();
    }
    
    @Deprecated
    @Override
    public void connect() {
        this.akS.connect();
    }
    
    @Deprecated
    @Override
    public void disconnect() {
        this.akS.disconnect();
    }
    
    @Deprecated
    public String getAccountName() {
        return this.akS.getAccountName();
    }
    
    @Deprecated
    public Person getCurrentPerson() {
        return this.akS.getCurrentPerson();
    }
    
    @Deprecated
    @Override
    public boolean isConnected() {
        return this.akS.isConnected();
    }
    
    @Deprecated
    @Override
    public boolean isConnecting() {
        return this.akS.isConnecting();
    }
    
    @Deprecated
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.akS.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.akS.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener onMomentsLoadedListener) {
        this.akS.k(new BaseImplementation.b<Moments.LoadMomentsResult>() {
            public void a(final Moments.LoadMomentsResult loadMomentsResult) {
                onMomentsLoadedListener.onMomentsLoaded(loadMomentsResult.getStatus().gu(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        });
    }
    
    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener onMomentsLoadedListener, final int n, final String s, final Uri uri, final String s2, final String s3) {
        this.akS.a(new BaseImplementation.b<Moments.LoadMomentsResult>() {
            public void a(final Moments.LoadMomentsResult loadMomentsResult) {
                onMomentsLoadedListener.onMomentsLoaded(loadMomentsResult.getStatus().gu(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        }, n, s, uri, s2, s3);
    }
    
    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener onPeopleLoadedListener, final Collection<String> collection) {
        this.akS.a(new BaseImplementation.b<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().gu(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, collection);
    }
    
    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener onPeopleLoadedListener, final String... array) {
        this.akS.d(new BaseImplementation.b<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().gu(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, array);
    }
    
    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener onPeopleLoadedListener, final int n, final String s) {
        this.akS.a(new BaseImplementation.b<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().gu(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, n, s);
    }
    
    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener onPeopleLoadedListener, final String s) {
        this.akS.r(new BaseImplementation.b<People.LoadPeopleResult>() {
            public void a(final People.LoadPeopleResult loadPeopleResult) {
                onPeopleLoadedListener.onPeopleLoaded(loadPeopleResult.getStatus().gu(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, s);
    }
    
    e mX() {
        return this.akS;
    }
    
    @Deprecated
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.akS.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.akS.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void removeMoment(final String s) {
        this.akS.removeMoment(s);
    }
    
    @Deprecated
    public void revokeAccessAndDisconnect(final OnAccessRevokedListener onAccessRevokedListener) {
        this.akS.m(new BaseImplementation.b<Status>() {
            public void aA(final Status status) {
                onAccessRevokedListener.onAccessRevoked(status.getStatus().gu());
            }
        });
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.akS.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.akS.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void writeMoment(final Moment moment) {
        this.akS.a(null, moment);
    }
    
    @Deprecated
    public static class Builder
    {
        private final ConnectionCallbacks akX;
        private final OnConnectionFailedListener akY;
        private final i akZ;
        private final Context mContext;
        
        public Builder(final Context mContext, final ConnectionCallbacks akX, final OnConnectionFailedListener akY) {
            this.mContext = mContext;
            this.akX = akX;
            this.akY = akY;
            this.akZ = new i(this.mContext);
        }
        
        public PlusClient build() {
            return new PlusClient(new e(this.mContext, this.akX, this.akY, this.akZ.no()));
        }
        
        public Builder clearScopes() {
            this.akZ.nn();
            return this;
        }
        
        public Builder setAccountName(final String s) {
            this.akZ.ce(s);
            return this;
        }
        
        public Builder setActions(final String... array) {
            this.akZ.h(array);
            return this;
        }
        
        public Builder setScopes(final String... array) {
            this.akZ.g(array);
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
