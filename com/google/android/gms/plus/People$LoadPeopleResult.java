// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface People$LoadPeopleResult extends Releasable, Result
{
    String getNextPageToken();
    
    PersonBuffer getPersonBuffer();
}
