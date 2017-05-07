// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Looper;
import java.util.List;

class Batch$1 implements PendingResult$a
{
    final /* synthetic */ Batch Iz;
    
    Batch$1(final Batch iz) {
        this.Iz = iz;
    }
    
    @Override
    public void n(Status jo) {
        while (true) {
        Label_0101:
            while (true) {
                synchronized (this.Iz.mw) {
                    if (this.Iz.isCanceled()) {
                        return;
                    }
                    if (jo.isCanceled()) {
                        this.Iz.Ix = true;
                        this.Iz.Iv--;
                        if (this.Iz.Iv == 0) {
                            if (!this.Iz.Ix) {
                                break Label_0101;
                            }
                            this.Iz.cancel();
                        }
                        return;
                    }
                }
                final Status status;
                if (!status.isSuccess()) {
                    this.Iz.Iw = true;
                    continue;
                }
                continue;
            }
            if (this.Iz.Iw) {
                jo = new Status(13);
            }
            else {
                jo = Status.Jo;
            }
            this.Iz.b(new BatchResult(jo, this.Iz.Iy));
        }
    }
}
