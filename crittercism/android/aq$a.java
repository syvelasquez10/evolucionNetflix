// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;

public final class aq$a
{
    public static aq a(final h h, final String s, final String s2) {
        final JSONObject jsonObject = null;
        final String a = h.a(s, s2, null);
        Label_0038: {
            if (a == null) {
                break Label_0038;
            }
            while (true) {
                try {
                    JSONObject jsonObject2 = new JSONObject(a);
                    if (jsonObject2 == null) {
                        return new aq((byte)0);
                    }
                    return new aq(jsonObject2, (byte)0);
                    jsonObject2 = null;
                    continue;
                }
                catch (JSONException ex) {
                    final JSONObject jsonObject2 = jsonObject;
                    continue;
                }
                break;
            }
        }
    }
}
