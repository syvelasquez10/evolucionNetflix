// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONObject;

public final class l$3 implements Runnable
{
    final /* synthetic */ JSONObject a;
    final /* synthetic */ l b;
    
    public l$3(final l b, final JSONObject a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void run() {
        try {
            this.b.c.b(this.a);
        }
        catch (Exception ex) {}
    }
}
