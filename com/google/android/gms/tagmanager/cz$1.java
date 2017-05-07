// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.SharedPreferences$Editor;

final class cz$1 implements Runnable
{
    final /* synthetic */ SharedPreferences$Editor arr;
    
    cz$1(final SharedPreferences$Editor arr) {
        this.arr = arr;
    }
    
    @Override
    public void run() {
        this.arr.commit();
    }
}
