// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

public class bb extends c
{
    private final BaseImplementation$b<Status> De;
    
    public bb(final BaseImplementation$b<Status> de) {
        this.De = de;
    }
    
    @Override
    public void o(final Status status) {
        this.De.b(status);
    }
    
    @Override
    public void onSuccess() {
        this.De.b(Status.Jo);
    }
}
