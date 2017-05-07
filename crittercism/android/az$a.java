// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.MessageQueue$IdleHandler;

final class az$a implements MessageQueue$IdleHandler
{
    private boolean a;
    
    private az$a() {
        this.a = false;
    }
    
    public final boolean queueIdle() {
        synchronized (this) {
            if (!this.a) {
                this.a = true;
                bg.g();
            }
            return true;
        }
    }
}
