// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Moments$LoadMomentsResult;
import com.google.android.gms.plus.Plus$a;

abstract class nr$a extends Plus$a<Moments$LoadMomentsResult>
{
    public Moments$LoadMomentsResult aC(final Status status) {
        return new nr$a$1(this, status);
    }
}
