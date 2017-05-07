// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public interface voOSAudioInfo
{
    voOSAudioFormat Format();
    
    String Language();
    
    boolean parse(final Parcel p0);
}
