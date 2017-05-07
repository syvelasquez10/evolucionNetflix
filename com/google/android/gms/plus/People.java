// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.PendingResult;
import java.util.Collection;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.common.api.GoogleApiClient;

public interface People
{
    Person getCurrentPerson(final GoogleApiClient p0);
    
    PendingResult<People$LoadPeopleResult> load(final GoogleApiClient p0, final Collection<String> p1);
    
    PendingResult<People$LoadPeopleResult> load(final GoogleApiClient p0, final String... p1);
    
    PendingResult<People$LoadPeopleResult> loadConnected(final GoogleApiClient p0);
    
    PendingResult<People$LoadPeopleResult> loadVisible(final GoogleApiClient p0, final int p1, final String p2);
    
    PendingResult<People$LoadPeopleResult> loadVisible(final GoogleApiClient p0, final String p1);
}
