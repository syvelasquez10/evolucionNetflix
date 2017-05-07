// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;

public final class bx$k implements bw
{
    public String a;
    
    public bx$k() {
        this.a = null;
        bx.b;
        this.a = bx.b.getResources().getConfiguration().locale.getLanguage();
        if (this.a == null || this.a.length() == 0) {
            this.a = "en";
        }
    }
    
    @Override
    public final String a() {
        return "locale";
    }
}
