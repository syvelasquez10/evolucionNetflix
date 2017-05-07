// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import java.util.List;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.zza;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.dynamic.zzd;
import android.os.IInterface;

public interface zzen extends IInterface
{
    void destroy();
    
    zzd getView();
    
    boolean isInitialized();
    
    void pause();
    
    void resume();
    
    void showInterstitial();
    
    void showVideo();
    
    void zza(final AdRequestParcel p0, final String p1);
    
    void zza(final zzd p0, final AdRequestParcel p1, final String p2, final zza p3, final String p4);
    
    void zza(final zzd p0, final AdRequestParcel p1, final String p2, final zzeo p3);
    
    void zza(final zzd p0, final AdRequestParcel p1, final String p2, final String p3, final zzeo p4);
    
    void zza(final zzd p0, final AdRequestParcel p1, final String p2, final String p3, final zzeo p4, final NativeAdOptionsParcel p5, final List<String> p6);
    
    void zza(final zzd p0, final AdSizeParcel p1, final AdRequestParcel p2, final String p3, final zzeo p4);
    
    void zza(final zzd p0, final AdSizeParcel p1, final AdRequestParcel p2, final String p3, final String p4, final zzeo p5);
    
    zzeq zzdV();
    
    zzer zzdW();
    
    Bundle zzdX();
    
    Bundle zzdY();
    
    Bundle zzdZ();
}
