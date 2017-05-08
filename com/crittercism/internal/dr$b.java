// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONException;
import org.json.JSONObject;

public final class dr$b extends di
{
    private boolean b;
    
    @Override
    public final void a() {
        final boolean b = this.b;
        final ax c = ax.C();
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("optOutStatus", b);
                jsonObject.put("optOutStatusSet", true);
                c.a(cq.i.m, cq.i.n, jsonObject.toString());
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
}
