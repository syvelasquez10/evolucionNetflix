// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.request;

import com.google.android.gms.games.internal.constants.RequestUpdateResultOutcome;
import java.util.HashMap;

public final class RequestUpdateOutcomes$Builder
{
    private int HF;
    private HashMap<String, Integer> abi;
    
    public RequestUpdateOutcomes$Builder() {
        this.abi = new HashMap<String, Integer>();
        this.HF = 0;
    }
    
    public RequestUpdateOutcomes$Builder dR(final int hf) {
        this.HF = hf;
        return this;
    }
    
    public RequestUpdateOutcomes lw() {
        return new RequestUpdateOutcomes(this.HF, this.abi, null);
    }
    
    public RequestUpdateOutcomes$Builder x(final String s, final int n) {
        if (RequestUpdateResultOutcome.isValid(n)) {
            this.abi.put(s, n);
        }
        return this;
    }
}
