// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;

public final class dt$a
{
    public static dt a(final ax ax) {
        JSONObject jsonObject = null;
        final String a = ax.a(cr.i.a(), cr.i.b());
    Label_0036:
        while (true) {
            if (a == null) {
                jsonObject = null;
                break Label_0036;
            }
            while (true) {
                while (true) {
                    Label_0098: {
                        while (true) {
                            try {
                                jsonObject = new JSONObject(a);
                                if (jsonObject == null) {
                                    break Label_0098;
                                }
                                final int optBoolean = jsonObject.optBoolean("optOutStatusSet", false) ? 1 : 0;
                                if (optBoolean != 0) {
                                    final boolean b = jsonObject.optBoolean("optOutStatus", false);
                                    return new dt(b);
                                }
                            }
                            catch (JSONException ex) {
                                dy.b();
                                continue Label_0036;
                            }
                            final boolean b = ax.c(cr.l.a(), cr.l.b());
                            continue;
                        }
                    }
                    final int optBoolean = 0;
                    continue;
                }
            }
            break;
        }
    }
}
