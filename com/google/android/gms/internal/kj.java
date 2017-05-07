// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Result;
import android.content.Context;
import com.google.android.gms.common.api.Api;

public interface kj extends Api.a
{
    Context getContext();
    
    ko iT();
    
    public abstract static class a<R extends Result> extends BaseImplementation.a<R, kj>
    {
        public a() {
            super(Fitness.CU);
        }
    }
    
    public static class b extends ks.a
    {
        private final BaseImplementation.b<Status> De;
        
        public b(final BaseImplementation.b<Status> de) {
            this.De = de;
        }
        
        public void k(final Status status) {
            this.De.b(status);
        }
    }
    
    public abstract static class c extends a<Status>
    {
        protected Status d(final Status status) {
            n.K(!status.isSuccess());
            return status;
        }
    }
}
