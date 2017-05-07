// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.bitrate;

public final class VideoBitrateRange extends BitrateRange
{
    private String mProfile;
    
    public VideoBitrateRange(final int n, final int n2, final String mProfile) {
        super(n, n2);
        this.mProfile = mProfile;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof VideoBitrateRange)) {
                return false;
            }
            final VideoBitrateRange videoBitrateRange = (VideoBitrateRange)o;
            if (this.maximal != videoBitrateRange.maximal) {
                return false;
            }
            if (this.minimal != videoBitrateRange.minimal) {
                return false;
            }
            if (this.mProfile != videoBitrateRange.mProfile) {
                return false;
            }
        }
        return true;
    }
    
    public String getProfile() {
        return this.mProfile;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (((this.maximal + 31) * 31 + this.minimal) * 31 + this.mProfile == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mProfile.hashCode();
        }
        return hashCode * 31 + 1;
    }
    
    @Override
    public String toString() {
        return "VideoBitrateRange [profile=" + this.mProfile + "minimal=" + this.minimal + ", maximal=" + this.maximal + "]";
    }
}
