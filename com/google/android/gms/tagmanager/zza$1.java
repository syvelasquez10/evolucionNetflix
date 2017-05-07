// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Process;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;

class zza$1 implements zza$zza
{
    final /* synthetic */ zza zzaOH;
    
    zza$1(final zza zzaOH) {
        this.zzaOH = zzaOH;
    }
    
    @Override
    public AdvertisingIdClient$Info zzzw() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.zzaOH.mContext);
        }
        catch (IllegalStateException ex) {
            zzbg.zzaE("IllegalStateException getting Advertising Id Info");
            return null;
        }
        catch (GooglePlayServicesRepairableException ex2) {
            zzbg.zzaE("GooglePlayServicesRepairableException getting Advertising Id Info");
            return null;
        }
        catch (IOException ex3) {
            zzbg.zzaE("IOException getting Ad Id Info");
            return null;
        }
        catch (GooglePlayServicesNotAvailableException ex4) {
            zzbg.zzaE("GooglePlayServicesNotAvailableException getting Advertising Id Info");
            return null;
        }
        catch (Exception ex5) {
            zzbg.zzaE("Unknown exception. Could not get the Advertising Id Info.");
            return null;
        }
    }
}
