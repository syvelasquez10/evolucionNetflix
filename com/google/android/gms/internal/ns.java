// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.common.api.PendingResult;
import java.util.Collection;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.People;

public final class ns implements People
{
    @Override
    public Person getCurrentPerson(final GoogleApiClient googleApiClient) {
        return Plus.a(googleApiClient, Plus.CU).getCurrentPerson();
    }
    
    @Override
    public PendingResult<People$LoadPeopleResult> load(final GoogleApiClient googleApiClient, final Collection<String> collection) {
        return googleApiClient.a((PendingResult<People$LoadPeopleResult>)new ns$4(this, collection));
    }
    
    @Override
    public PendingResult<People$LoadPeopleResult> load(final GoogleApiClient googleApiClient, final String... array) {
        return googleApiClient.a((PendingResult<People$LoadPeopleResult>)new ns$5(this, array));
    }
    
    @Override
    public PendingResult<People$LoadPeopleResult> loadConnected(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<People$LoadPeopleResult>)new ns$3(this));
    }
    
    @Override
    public PendingResult<People$LoadPeopleResult> loadVisible(final GoogleApiClient googleApiClient, final int n, final String s) {
        return googleApiClient.a((PendingResult<People$LoadPeopleResult>)new ns$1(this, n, s));
    }
    
    @Override
    public PendingResult<People$LoadPeopleResult> loadVisible(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<People$LoadPeopleResult>)new ns$2(this, s));
    }
}
