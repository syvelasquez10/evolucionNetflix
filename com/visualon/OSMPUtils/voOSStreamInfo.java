// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public interface voOSStreamInfo
{
    int getBitrate();
    
    int getSelInfo();
    
    int getStreamID();
    
    int getTrackCount();
    
    voOSTrackInfo[] getTrackInfo();
    
    boolean parse(final Parcel p0);
}
