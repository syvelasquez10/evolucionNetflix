// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Process;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.ju;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import android.content.Context;

class a$2 implements Runnable
{
    final /* synthetic */ a anG;
    
    a$2(final a anG) {
        this.anG = anG;
    }
    
    @Override
    public void run() {
        this.anG.nI();
    }
}
