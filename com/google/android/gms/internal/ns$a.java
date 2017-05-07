// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People$LoadPeopleResult;
import com.google.android.gms.plus.Plus$a;

abstract class ns$a extends Plus$a<People$LoadPeopleResult>
{
    public People$LoadPeopleResult aD(final Status status) {
        return new ns$a$1(this, status);
    }
}
