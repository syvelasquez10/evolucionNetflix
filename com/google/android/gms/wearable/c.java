// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable;

import java.util.Set;
import java.util.Map;
import android.net.Uri;
import com.google.android.gms.common.data.Freezable;

public interface c extends Freezable<c>
{
    byte[] getData();
    
    Uri getUri();
    
    Map<String, d> ma();
    
    @Deprecated
    Set<String> mb();
}
