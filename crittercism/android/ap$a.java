// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;

public final class ap$a
{
    public static ap a(final h h) {
        JSONObject jsonObject = null;
        final String a = h.a(ae.f.a(), ae.f.b(), null);
        Label_0071: {
            if (a == null) {
                break Label_0071;
            }
            while (true) {
                try {
                    jsonObject = new JSONObject(a);
                    boolean b;
                    if (jsonObject != null && jsonObject.optBoolean("optOutStatusSet", false)) {
                        b = jsonObject.optBoolean("optOutStatus", false);
                    }
                    else {
                        b = h.b(ae.k.a(), ae.k.b());
                    }
                    return new ap(b, (byte)0);
                    jsonObject = null;
                    continue;
                }
                catch (JSONException ex) {
                    continue;
                }
                break;
            }
        }
    }
}
