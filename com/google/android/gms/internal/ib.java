// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import java.util.Collection;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.People;

public final class ib implements People
{
    @Override
    public Person getCurrentPerson(final GoogleApiClient googleApiClient) {
        return Plus.a(googleApiClient, Plus.wx).getCurrentPerson();
    }
    
    @Override
    public PendingResult<LoadPeopleResult> load(final GoogleApiClient googleApiClient, final Collection<String> collection) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                e.a((d<LoadPeopleResult>)this, collection);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPeopleResult> load(final GoogleApiClient googleApiClient, final String... array) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                e.d((d<LoadPeopleResult>)this, array);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPeopleResult> loadConnected(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                e.m((d<LoadPeopleResult>)this);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPeopleResult> loadVisible(final GoogleApiClient googleApiClient, final int n, final String s) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                ((com.google.android.gms.common.api.a.a)this).a(e.a((d<LoadPeopleResult>)this, n, s));
            }
        });
    }
    
    @Override
    public PendingResult<LoadPeopleResult> loadVisible(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<LoadPeopleResult>)new a() {
            protected void a(final e e) {
                ((com.google.android.gms.common.api.a.a)this).a(e.o((d<LoadPeopleResult>)this, s));
            }
        });
    }
    
    private abstract static class a extends Plus.a<LoadPeopleResult>
    {
        public LoadPeopleResult ab(final Status status) {
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
