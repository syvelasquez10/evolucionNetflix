// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONObject;

public class db implements cz
{
    private bs a;
    private bs b;
    
    public db(final bs a, final bs b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void a(final boolean b, int n, final JSONObject jsonObject) {
        if (b || (n >= 200 && n < 300)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            this.a.a();
            return;
        }
        this.a.a(this.b);
    }
}
