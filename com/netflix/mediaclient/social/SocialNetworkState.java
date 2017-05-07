// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.social;

public class SocialNetworkState
{
    private boolean disabled;
    private boolean share;
    
    public SocialNetworkState(final boolean disabled, final boolean share) {
        this.disabled = true;
        this.share = false;
        this.disabled = disabled;
        this.share = share;
    }
    
    public boolean isDisabled() {
        return this.disabled;
    }
    
    public boolean isShare() {
        return this.share;
    }
    
    @Override
    public String toString() {
        return "SocialNetworkState [disabled=" + this.disabled + ", share=" + this.share + "]";
    }
}
