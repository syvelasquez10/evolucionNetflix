// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Result;

abstract class p<R extends Result> extends BaseImplementation.a<R, q>
{
    public p() {
        super(Drive.CU);
    }
    
    abstract static class a extends p<Status>
    {
        protected Status d(final Status status) {
            return status;
        }
    }
}
