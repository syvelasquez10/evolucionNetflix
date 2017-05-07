// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.n;
import org.json.JSONException;
import org.json.JSONObject;
import android.text.TextUtils;
import java.util.Map;

class fo$1 implements by
{
    final /* synthetic */ ah ub;
    final /* synthetic */ gk uc;
    final /* synthetic */ fo ud;
    
    fo$1(final fo ud, final ah ub, final gk uc) {
        this.ud = ud;
        this.ub = ub;
        this.uc = uc;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        this.ub.g("/nativeAdPreProcess");
        try {
            final String s = map.get("success");
            if (!TextUtils.isEmpty((CharSequence)s)) {
                this.uc.a(new JSONObject(s).getJSONArray("ads").getJSONObject(0));
                return;
            }
        }
        catch (JSONException ex) {
            gs.b("Malformed native JSON response.", (Throwable)ex);
        }
        this.ud.s(0);
        n.a(this.ud.cI(), (Object)"Unable to set the ad state error!");
        this.uc.a(null);
    }
}
