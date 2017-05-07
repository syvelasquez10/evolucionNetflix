// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.request;

import com.google.android.gms.games.internal.constants.RequestUpdateResultOutcome;
import com.google.android.gms.internal.fq;
import java.util.Set;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;

public final class RequestUpdateOutcomes
{
    private static final String[] LN;
    private final int Ah;
    private final HashMap<String, Integer> LO;
    
    static {
        LN = new String[] { "requestId", "outcome" };
    }
    
    private RequestUpdateOutcomes(final int ah, final HashMap<String, Integer> lo) {
        this.Ah = ah;
        this.LO = lo;
    }
    
    public static RequestUpdateOutcomes J(final DataHolder dataHolder) {
        final Builder builder = new Builder();
        builder.bm(dataHolder.getStatusCode());
        for (int count = dataHolder.getCount(), i = 0; i < count; ++i) {
            final int g = dataHolder.G(i);
            builder.s(dataHolder.getString("requestId", i, g), dataHolder.getInteger("outcome", i, g));
        }
        return builder.hB();
    }
    
    public Set<String> getRequestIds() {
        return this.LO.keySet();
    }
    
    public int getRequestOutcome(final String s) {
        fq.b(this.LO.containsKey(s), "Request " + s + " was not part of the update operation!");
        return this.LO.get(s);
    }
    
    public static final class Builder
    {
        private int Ah;
        private HashMap<String, Integer> LO;
        
        public Builder() {
            this.LO = new HashMap<String, Integer>();
            this.Ah = 0;
        }
        
        public Builder bm(final int ah) {
            this.Ah = ah;
            return this;
        }
        
        public RequestUpdateOutcomes hB() {
            return new RequestUpdateOutcomes(this.Ah, this.LO, null);
        }
        
        public Builder s(final String s, final int n) {
            if (RequestUpdateResultOutcome.isValid(n)) {
                this.LO.put(s, n);
            }
            return this;
        }
    }
}
