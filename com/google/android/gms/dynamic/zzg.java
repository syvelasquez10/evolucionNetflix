// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;

public abstract class zzg<T>
{
    private final String zzapA;
    private T zzapB;
    
    protected zzg(final String zzapA) {
        this.zzapA = zzapA;
    }
    
    protected final T zzas(Context remoteContext) {
        Label_0058: {
            if (this.zzapB != null) {
                break Label_0058;
            }
            zzx.zzw(remoteContext);
            remoteContext = GooglePlayServicesUtil.getRemoteContext(remoteContext);
            if (remoteContext == null) {
                throw new zzg$zza("Could not get remote context.");
            }
            final ClassLoader classLoader = remoteContext.getClassLoader();
            try {
                this.zzapB = this.zzd((IBinder)classLoader.loadClass(this.zzapA).newInstance());
                return this.zzapB;
            }
            catch (ClassNotFoundException ex) {
                throw new zzg$zza("Could not load creator class.", ex);
            }
            catch (InstantiationException ex2) {
                throw new zzg$zza("Could not instantiate creator.", ex2);
            }
            catch (IllegalAccessException ex3) {
                throw new zzg$zza("Could not access creator.", ex3);
            }
        }
    }
    
    protected abstract T zzd(final IBinder p0);
}
