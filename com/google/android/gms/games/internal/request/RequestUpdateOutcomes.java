// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.request;

import com.google.android.gms.games.internal.constants.RequestUpdateResultOutcome;
import com.google.android.gms.common.internal.n;
import java.util.Set;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;

public final class RequestUpdateOutcomes
{
    private static final String[] abh;
    private final int HF;
    private final HashMap<String, Integer> abi;
    
    static {
        abh = new String[] { "requestId", "outcome" };
    }
    
    private RequestUpdateOutcomes(final int hf, final HashMap<String, Integer> abi) {
        this.HF = hf;
        this.abi = abi;
    }
    
    public static RequestUpdateOutcomes V(final DataHolder dataHolder) {
        final Builder builder = new Builder();
        builder.dR(dataHolder.getStatusCode());
        for (int count = dataHolder.getCount(), i = 0; i < count; ++i) {
            final int ar = dataHolder.ar(i);
            builder.x(dataHolder.c("requestId", i, ar), dataHolder.b("outcome", i, ar));
        }
        return builder.lw();
    }
    
    public Set<String> getRequestIds() {
        return this.abi.keySet();
    }
    
    public int getRequestOutcome(final String s) {
        n.b(this.abi.containsKey(s), (Object)("Request " + s + " was not part of the update operation!"));
        return this.abi.get(s);
    }
    
    public static final class Builder
    {
        private int HF;
        private HashMap<String, Integer> abi;
        
        public Builder() {
            this.abi = new HashMap<String, Integer>();
            this.HF = 0;
        }
        
        public Builder dR(final int hf) {
            this.HF = hf;
            return this;
        }
        
        public RequestUpdateOutcomes lw() {
            return new RequestUpdateOutcomes(this.HF, this.abi, null);
        }
        
        public Builder x(final String s, final int n) {
            if (RequestUpdateResultOutcome.isValid(n)) {
                this.abi.put(s, n);
            }
            return this;
        }
    }
}
