// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public interface voOSTrackInfo
{
    voOSAudioInfo getAudioInfo();
    
    int getBitrate();
    
    int getChunkCounts();
    
    int getCodec();
    
    long getDuration();
    
    char[] getFourCC();
    
    char[] getHeadData();
    
    int getHeadSize();
    
    int getSelectInfo();
    
    int getTrackID();
    
    voOSType.VOOSMP_SOURCE_STREAMTYPE getTrackType();
    
    voOSVideoInfo getVideoInfo();
    
    boolean parse(final Parcel p0);
}
