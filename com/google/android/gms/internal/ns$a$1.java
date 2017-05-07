// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People$LoadPeopleResult;

class ns$a$1 implements People$LoadPeopleResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ ns$a alP;
    
    ns$a$1(final ns$a alP, final Status cw) {
        this.alP = alP;
        this.CW = cw;
    }
    
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
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
