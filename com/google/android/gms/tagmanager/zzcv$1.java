// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.SharedPreferences$Editor;

final class zzcv$1 implements Runnable
{
    final /* synthetic */ SharedPreferences$Editor zzaSk;
    
    zzcv$1(final SharedPreferences$Editor zzaSk) {
        this.zzaSk = zzaSk;
    }
    
    @Override
    public void run() {
        this.zzaSk.commit();
    }
}
