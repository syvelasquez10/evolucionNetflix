// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Process;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import java.io.IOException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;

class a$1 implements a$a
{
    final /* synthetic */ a anG;
    
    a$1(final a anG) {
        this.anG = anG;
    }
    
    @Override
    public AdvertisingIdClient$Info nK() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.anG.mContext);
        }
        catch (IllegalStateException ex) {
            bh.W("IllegalStateException getting Advertising Id Info");
            return null;
        }
        catch (GooglePlayServicesRepairableException ex2) {
            bh.W("GooglePlayServicesRepairableException getting Advertising Id Info");
            return null;
        }
        catch (IOException ex3) {
            bh.W("IOException getting Ad Id Info");
            return null;
        }
        catch (GooglePlayServicesNotAvailableException ex4) {
            bh.W("GooglePlayServicesNotAvailableException getting Advertising Id Info");
            return null;
        }
        catch (Exception ex5) {
            bh.W("Unknown exception. Could not get the Advertising Id Info.");
            return null;
        }
    }
}
