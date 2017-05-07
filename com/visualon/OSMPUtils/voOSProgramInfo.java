// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public interface voOSProgramInfo
{
    int getProgramID();
    
    String getProgramName();
    
    voOSType.VOOSMP_SRC_PROGRAM_TYPE getProgramType();
    
    int getSelInfo();
    
    int getStreamCount();
    
    voOSStreamInfo[] getStreamInfo();
    
    boolean parse(final Parcel p0);
}
