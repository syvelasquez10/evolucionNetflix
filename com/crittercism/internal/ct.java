// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.File;
import android.content.SharedPreferences$Editor;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public final class ct extends da
{
    private ar a;
    private Context b;
    private String c;
    private JSONObject d;
    private JSONObject e;
    private boolean f;
    
    public ct(final bq bq, final bq bq2, final String c, final Context b, final ar a) {
        super(bq, bq2);
        this.c = c;
        this.b = b;
        this.a = a;
    }
    
    @Override
    public final void a(final boolean b, int n, JSONObject jsonObject) {
        super.a(b, n, jsonObject);
        if (jsonObject == null) {
            return;
        }
        while (true) {
        Label_0427_Outer:
            while (true) {
            Label_0062:
                while (true) {
                    Label_0027: {
                        if (jsonObject.optBoolean("internalExceptionReporting", false)) {
                            dw.a = dw$a.b;
                            break Label_0027;
                        }
                        Label_0418: {
                            break Label_0418;
                            h h;
                            Context b2 = null;
                            SharedPreferences$Editor edit = null;
                            SharedPreferences$Editor edit2;
                            final JSONObject optJSONObject;
                            final ds n2;
                            File a;
                            Label_0198_Outer:Label_0281_Outer:
                            while (true) {
                                while (true) {
                                Label_0666:
                                    while (true) {
                                    Label_0634:
                                        while (true) {
                                            try {
                                                new dj(new cu(this.a).a("device_name", this.a.i()).a("pkg", this.b.getPackageName()), new dc(new db(this.c, "/android_v2/update_package_name").a()), null).run();
                                                this.f = true;
                                                this.d = jsonObject.optJSONObject("apm");
                                                if (this.d != null) {
                                                    h = new h(this.d);
                                                    b2 = this.b;
                                                    if (!h.c) {
                                                        break Label_0634;
                                                    }
                                                    com.crittercism.internal.h.b(b2);
                                                    edit = b2.getSharedPreferences("com.crittercism.optmz.config", 0).edit();
                                                    if (!h.b) {
                                                        break Label_0666;
                                                    }
                                                    edit.putBoolean("enabled", h.a);
                                                    edit.putBoolean("kill", h.c);
                                                    edit.putBoolean("persist", h.b);
                                                    edit.putInt("interval", h.d);
                                                    edit.commit();
                                                    ax.C().a(h);
                                                }
                                                this.e = jsonObject.optJSONObject("txnConfig");
                                                if (this.e != null) {
                                                    jsonObject = (JSONObject)new bf(this.e);
                                                    edit2 = this.b.getSharedPreferences("com.crittercism.txn.config", 0).edit();
                                                    edit2.putBoolean("enabled", ((bf)jsonObject).a);
                                                    edit2.putInt("interval", ((bf)jsonObject).b);
                                                    edit2.putInt("defaultTimeout", ((bf)jsonObject).c);
                                                    edit2.putString("transactions", ((bf)jsonObject).d.toString());
                                                    edit2.commit();
                                                    ax.C().a((bf)jsonObject);
                                                }
                                                return;
                                                try {
                                                    if ((n = optJSONObject.getInt("rateAfterLoadNum")) < 0) {
                                                        n = 0;
                                                    }
                                                    n2.a.edit().putInt("rateAfterNumLoads", n).commit();
                                                    if ((n = optJSONObject.getInt("remindAfterLoadNum")) <= 0) {
                                                        n = 1;
                                                    }
                                                    n2.a.edit().putInt("remindAfterNumLoads", n).commit();
                                                    n2.a.edit().putString("rateAppMessage", optJSONObject.getString("message")).commit();
                                                    n2.a.edit().putString("rateAppTitle", optJSONObject.getString("title")).commit();
                                                    n2.a(true);
                                                }
                                                catch (JSONException ex2) {
                                                    n2.a(false);
                                                }
                                                break Label_0062;
                                                dw.a = dw$a.c;
                                                break;
                                            }
                                            catch (IOException ex) {
                                                dw.d("IOException in handleResponse(): " + ex.getMessage());
                                                dw.a(ex);
                                                continue Label_0198_Outer;
                                            }
                                            break;
                                        }
                                        a = com.crittercism.internal.h.a(b2);
                                        if (!a.delete() && a.exists()) {
                                            dw.a("Unable to reenable OPTMZ instrumentation");
                                            continue Label_0281_Outer;
                                        }
                                        continue Label_0281_Outer;
                                    }
                                    edit.clear();
                                    continue Label_0427_Outer;
                                }
                            }
                        }
                    }
                    final ds n2 = this.a.n();
                    if (n2 != null) {
                        final JSONObject optJSONObject = jsonObject.optJSONObject("rateMyApp");
                        if (optJSONObject != null) {
                            continue;
                        }
                        n2.a(false);
                    }
                    break;
                }
                if (jsonObject.optInt("needPkg", 0) == 1) {
                    continue Label_0427_Outer;
                }
                break;
            }
            continue;
        }
    }
}
