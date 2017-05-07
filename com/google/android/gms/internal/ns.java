// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api;
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
    public PendingResult<LoadPeopleResult> load(final GoogleApiClient googleApiClient, final Collection<String> collection) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                e.a((BaseImplementation.b<LoadPeopleResult>)this, collection);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPeopleResult> load(final GoogleApiClient googleApiClient, final String... array) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                e.d((BaseImplementation.b<LoadPeopleResult>)this, array);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPeopleResult> loadConnected(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                e.l((BaseImplementation.b<LoadPeopleResult>)this);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPeopleResult> loadVisible(final GoogleApiClient googleApiClient, final int n, final String s) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                ((BaseImplementation.AbstractPendingResult)this).a(e.a((BaseImplementation.b<LoadPeopleResult>)this, n, s));
            }
        });
    }
    
    @Override
    public PendingResult<LoadPeopleResult> loadVisible(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                ((BaseImplementation.AbstractPendingResult)this).a(e.r((BaseImplementation.b<LoadPeopleResult>)this, s));
            }
        });
    }
    
    private abstract static class a extends Plus.a<LoadPeopleResult>
    {
        public LoadPeopleResult aD(final Status status) {
            return new LoadPeopleResult() {
                @Override
                public String getNextPageToken() {
                    return null;
                }
                
                @Override
                public PersonBuffer getPersonBuffer() {
                    return null;
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
}
