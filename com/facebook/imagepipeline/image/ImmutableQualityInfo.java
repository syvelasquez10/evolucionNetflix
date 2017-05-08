// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.image;

public class ImmutableQualityInfo implements QualityInfo
{
    public static final QualityInfo FULL_QUALITY;
    boolean mIsOfFullQuality;
    boolean mIsOfGoodEnoughQuality;
    int mQuality;
    
    static {
        FULL_QUALITY = of(Integer.MAX_VALUE, true, true);
    }
    
    private ImmutableQualityInfo(final int mQuality, final boolean mIsOfGoodEnoughQuality, final boolean mIsOfFullQuality) {
        this.mQuality = mQuality;
        this.mIsOfGoodEnoughQuality = mIsOfGoodEnoughQuality;
        this.mIsOfFullQuality = mIsOfFullQuality;
    }
    
    public static QualityInfo of(final int n, final boolean b, final boolean b2) {
        return new ImmutableQualityInfo(n, b, b2);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ImmutableQualityInfo)) {
                return false;
            }
            final ImmutableQualityInfo immutableQualityInfo = (ImmutableQualityInfo)o;
            if (this.mQuality != immutableQualityInfo.mQuality || this.mIsOfGoodEnoughQuality != immutableQualityInfo.mIsOfGoodEnoughQuality || this.mIsOfFullQuality != immutableQualityInfo.mIsOfFullQuality) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int getQuality() {
        return this.mQuality;
    }
    
    @Override
    public int hashCode() {
        int n = 0;
        final int mQuality = this.mQuality;
        int n2;
        if (this.mIsOfGoodEnoughQuality) {
            n2 = 4194304;
        }
        else {
            n2 = 0;
        }
        if (this.mIsOfFullQuality) {
            n = 8388608;
        }
        return n2 ^ mQuality ^ n;
    }
    
    @Override
    public boolean isOfFullQuality() {
        return this.mIsOfFullQuality;
    }
    
    @Override
    public boolean isOfGoodEnoughQuality() {
        return this.mIsOfGoodEnoughQuality;
    }
}
