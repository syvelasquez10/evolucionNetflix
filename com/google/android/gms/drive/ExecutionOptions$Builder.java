// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

public final class ExecutionOptions$Builder
{
    private String Nf;
    private boolean Ng;
    private int Nh;
    
    public ExecutionOptions$Builder() {
        this.Nh = 0;
    }
    
    public ExecutionOptions build() {
        if (this.Nh == 1 && !this.Ng) {
            throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
        }
        return new ExecutionOptions(this.Nf, this.Ng, this.Nh, null);
    }
    
    public ExecutionOptions$Builder setConflictStrategy(final int nh) {
        if (!ExecutionOptions.aW(nh)) {
            throw new IllegalArgumentException("Unrecognized value for conflict strategy: " + nh);
        }
        this.Nh = nh;
        return this;
    }
    
    public ExecutionOptions$Builder setNotifyOnCompletion(final boolean ng) {
        this.Ng = ng;
        return this;
    }
    
    public ExecutionOptions$Builder setTrackingTag(final String nf) {
        if (!ExecutionOptions.bh(nf)) {
            throw new IllegalArgumentException(String.format("trackingTag must not be null nor empty, and the length must be <= the maximum length (%s)", 65536));
        }
        this.Nf = nf;
        return this;
    }
}
