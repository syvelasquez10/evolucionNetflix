// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import java.util.List;
import android.os.IInterface;

public interface d extends IInterface
{
    boolean b(final d p0);
    
    int getActiveLevelIndex();
    
    int getDefaultLevelIndex();
    
    List<IBinder> getLevels();
    
    int hashCodeRemote();
    
    boolean isUnderground();
}
