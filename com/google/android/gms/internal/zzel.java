// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.zzd;
import java.util.List;
import android.os.Bundle;
import android.os.IInterface;

public interface zzel extends IInterface
{
    String getAdvertiser();
    
    String getBody();
    
    String getCallToAction();
    
    Bundle getExtras();
    
    String getHeadline();
    
    List getImages();
    
    boolean getOverrideClickHandling();
    
    boolean getOverrideImpressionRecording();
    
    void recordImpression();
    
    void zzc(final zzd p0);
    
    void zzd(final zzd p0);
    
    zzcj zzdw();
}
