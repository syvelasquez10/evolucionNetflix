// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Iterator;
import org.json.JSONArray;
import android.content.SharedPreferences$Editor;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.SharedPreferences;

public final class dw
{
    private SharedPreferences a;
    
    public dw(final Context context, final String s) {
        this.a = context.getSharedPreferences("com.crittercism." + s + ".usermetadata", 0);
        if (this.a.contains("data")) {
            return;
        }
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.putOpt("username", (Object)"anonymous");
            this.a(jsonObject);
        }
        catch (JSONException ex) {}
    }
    
    private void b(final JSONObject jsonObject) {
        final SharedPreferences$Editor edit = this.a.edit();
        edit.putString("data", jsonObject.toString());
        edit.commit();
    }
    
    public final JSONObject a() {
        final String string = this.a.getString("data", "{}");
        try {
            return new JSONObject(string);
        }
        catch (JSONException ex) {
            return new JSONObject();
        }
    }
    
    public final void a(final JSONObject jsonObject) {
        final JSONObject a = this.a();
        if (a.length() == 0) {
            if (jsonObject.length() > 0) {
                this.b(jsonObject);
                this.a(true);
            }
        }
        else {
            final Iterator keys = jsonObject.keys();
            boolean b = false;
        Label_0086_Outer:
            while (true) {
                Label_0178: {
                    if (!keys.hasNext()) {
                        break Label_0178;
                    }
                    final String s = keys.next();
                    final Object opt = jsonObject.opt(s);
                    final Object opt2 = a.opt(s);
                    Label_0148: {
                        if (opt2 != null) {
                            break Label_0148;
                        }
                        int n = 1;
                    Label_0129_Outer:
                        while (true) {
                            Label_0153: {
                                if (opt2 != null) {
                                    if (!(opt instanceof JSONObject) && !(opt instanceof JSONArray)) {
                                        break Label_0153;
                                    }
                                    if (opt2.toString().equals(opt.toString())) {
                                        break Label_0153;
                                    }
                                    n = 1;
                                }
                                while (true) {
                                    if (n == 0) {
                                        continue Label_0086_Outer;
                                    }
                                    try {
                                        a.put(s, opt);
                                        b = true;
                                        continue Label_0086_Outer;
                                        n = 0;
                                        continue;
                                        // iftrue(Label_0173:, opt2.equals(opt))
                                        n = 1;
                                        continue;
                                        n = 0;
                                        continue Label_0129_Outer;
                                        Label_0173: {
                                            n = 0;
                                        }
                                        continue;
                                        // iftrue(Label_0031:, !b)
                                        this.b(a);
                                        this.a(true);
                                        return;
                                    }
                                    catch (JSONException ex) {}
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        Label_0031:;
    }
    
    public final void a(final boolean b) {
        this.a.edit().putBoolean("dirty", b).commit();
    }
    
    public final boolean b() {
        return this.a.getBoolean("dirty", false);
    }
}
