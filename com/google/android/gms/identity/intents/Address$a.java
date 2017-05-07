// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.ll;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$a;

abstract class Address$a extends BaseImplementation$a<Status, ll>
{
    public Address$a() {
        super(Address.CU);
    }
    
    public Status d(final Status status) {
        return status;
    }
}
