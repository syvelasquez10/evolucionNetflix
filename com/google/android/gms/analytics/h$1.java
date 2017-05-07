// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.UUID;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import android.content.Context;

class h$1 extends Thread
{
    final /* synthetic */ h xU;
    
    h$1(final h xu, final String s) {
        this.xU = xu;
        super(s);
    }
    
    @Override
    public void run() {
        synchronized (this.xU.xT) {
            this.xU.xR = this.xU.dW();
            this.xU.xS = true;
            this.xU.xT.notifyAll();
        }
    }
}
