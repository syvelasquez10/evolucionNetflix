// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

public final class NalUnitUtil$SpsData
{
    public final boolean deltaPicOrderAlwaysZeroFlag;
    public final boolean frameMbsOnlyFlag;
    public final int frameNumLength;
    public final int height;
    public final int picOrderCntLsbLength;
    public final int picOrderCountType;
    public final float pixelWidthAspectRatio;
    public final boolean separateColorPlaneFlag;
    public final int seqParameterSetId;
    public final int width;
    
    public NalUnitUtil$SpsData(final int seqParameterSetId, final int width, final int height, final float pixelWidthAspectRatio, final boolean separateColorPlaneFlag, final boolean frameMbsOnlyFlag, final int frameNumLength, final int picOrderCountType, final int picOrderCntLsbLength, final boolean deltaPicOrderAlwaysZeroFlag) {
        this.seqParameterSetId = seqParameterSetId;
        this.width = width;
        this.height = height;
        this.pixelWidthAspectRatio = pixelWidthAspectRatio;
        this.separateColorPlaneFlag = separateColorPlaneFlag;
        this.frameMbsOnlyFlag = frameMbsOnlyFlag;
        this.frameNumLength = frameNumLength;
        this.picOrderCountType = picOrderCountType;
        this.picOrderCntLsbLength = picOrderCntLsbLength;
        this.deltaPicOrderAlwaysZeroFlag = deltaPicOrderAlwaysZeroFlag;
    }
}
