// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.media;

import android.os.Bundle;

public final class MediaRouteDiscoveryRequest
{
    private final Bundle mBundle;
    private MediaRouteSelector mSelector;
    
    public MediaRouteDiscoveryRequest(final MediaRouteSelector mSelector, final boolean b) {
        if (mSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        }
        this.mBundle = new Bundle();
        this.mSelector = mSelector;
        this.mBundle.putBundle("selector", mSelector.asBundle());
        this.mBundle.putBoolean("activeScan", b);
    }
    
    private void ensureSelector() {
        if (this.mSelector == null) {
            this.mSelector = MediaRouteSelector.fromBundle(this.mBundle.getBundle("selector"));
            if (this.mSelector == null) {
                this.mSelector = MediaRouteSelector.EMPTY;
            }
        }
    }
    
    public Bundle asBundle() {
        return this.mBundle;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof MediaRouteDiscoveryRequest) {
            final MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = (MediaRouteDiscoveryRequest)o;
            b2 = b;
            if (this.getSelector().equals(mediaRouteDiscoveryRequest.getSelector())) {
                b2 = b;
                if (this.isActiveScan() == mediaRouteDiscoveryRequest.isActiveScan()) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public MediaRouteSelector getSelector() {
        this.ensureSelector();
        return this.mSelector;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = this.getSelector().hashCode();
        boolean b;
        if (this.isActiveScan()) {
            b = true;
        }
        else {
            b = false;
        }
        return (b ? 1 : 0) ^ hashCode;
    }
    
    public boolean isActiveScan() {
        return this.mBundle.getBoolean("activeScan");
    }
    
    public boolean isValid() {
        this.ensureSelector();
        return this.mSelector.isValid();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DiscoveryRequest{ selector=").append(this.getSelector());
        sb.append(", activeScan=").append(this.isActiveScan());
        sb.append(", isValid=").append(this.isValid());
        sb.append(" }");
        return sb.toString();
    }
}
