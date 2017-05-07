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
    private final String zzana;
    private T zzanb;
    
    protected zzg(final String zzana) {
        this.zzana = zzana;
    }
    
    protected final T zzar(Context remoteContext) {
        Label_0058: {
            if (this.zzanb != null) {
                break Label_0058;
            }
            zzx.zzv(remoteContext);
            remoteContext = GooglePlayServicesUtil.getRemoteContext(remoteContext);
            if (remoteContext == null) {
                throw new zzg$zza("Could not get remote context.");
            }
            final ClassLoader classLoader = remoteContext.getClassLoader();
            try {
                this.zzanb = this.zzd((IBinder)classLoader.loadClass(this.zzana).newInstance());
                return this.zzanb;
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
