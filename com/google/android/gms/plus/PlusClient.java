// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.api.Status;
import java.util.Collection;
import android.net.Uri;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesClient$ConnectionCallbacks;
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
    public boolean isConnectionCallbacksRegistered(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        return this.akS.isConnectionCallbacksRegistered(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Deprecated
    @Override
    public boolean isConnectionFailedListenerRegistered(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        return this.akS.isConnectionFailedListenerRegistered(googlePlayServicesClient$OnConnectionFailedListener);
    }
    
    @Deprecated
    public void loadMoments(final PlusClient$OnMomentsLoadedListener plusClient$OnMomentsLoadedListener) {
        this.akS.k(new PlusClient$1(this, plusClient$OnMomentsLoadedListener));
    }
    
    @Deprecated
    public void loadMoments(final PlusClient$OnMomentsLoadedListener plusClient$OnMomentsLoadedListener, final int n, final String s, final Uri uri, final String s2, final String s3) {
        this.akS.a(new PlusClient$2(this, plusClient$OnMomentsLoadedListener), n, s, uri, s2, s3);
    }
    
    @Deprecated
    public void loadPeople(final PlusClient$OnPeopleLoadedListener plusClient$OnPeopleLoadedListener, final Collection<String> collection) {
        this.akS.a(new PlusClient$5(this, plusClient$OnPeopleLoadedListener), collection);
    }
    
    @Deprecated
    public void loadPeople(final PlusClient$OnPeopleLoadedListener plusClient$OnPeopleLoadedListener, final String... array) {
        this.akS.d(new PlusClient$6(this, plusClient$OnPeopleLoadedListener), array);
    }
    
    @Deprecated
    public void loadVisiblePeople(final PlusClient$OnPeopleLoadedListener plusClient$OnPeopleLoadedListener, final int n, final String s) {
        this.akS.a(new PlusClient$3(this, plusClient$OnPeopleLoadedListener), n, s);
    }
    
    @Deprecated
    public void loadVisiblePeople(final PlusClient$OnPeopleLoadedListener plusClient$OnPeopleLoadedListener, final String s) {
        this.akS.r(new PlusClient$4(this, plusClient$OnPeopleLoadedListener), s);
    }
    
    e mX() {
        return this.akS;
    }
    
    @Deprecated
    @Override
    public void registerConnectionCallbacks(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        this.akS.registerConnectionCallbacks(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void registerConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        this.akS.registerConnectionFailedListener(googlePlayServicesClient$OnConnectionFailedListener);
    }
    
    @Deprecated
    public void removeMoment(final String s) {
        this.akS.removeMoment(s);
    }
    
    @Deprecated
    public void revokeAccessAndDisconnect(final PlusClient$OnAccessRevokedListener plusClient$OnAccessRevokedListener) {
        this.akS.m(new PlusClient$7(this, plusClient$OnAccessRevokedListener));
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionCallbacks(final GooglePlayServicesClient$ConnectionCallbacks googlePlayServicesClient$ConnectionCallbacks) {
        this.akS.unregisterConnectionCallbacks(googlePlayServicesClient$ConnectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener googlePlayServicesClient$OnConnectionFailedListener) {
        this.akS.unregisterConnectionFailedListener(googlePlayServicesClient$OnConnectionFailedListener);
    }
    
    @Deprecated
    public void writeMoment(final Moment moment) {
        this.akS.a(null, moment);
    }
}
