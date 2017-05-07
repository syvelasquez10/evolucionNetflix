// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.File;
import android.content.SharedPreferences$Editor;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public final class cu extends db
{
    private au a;
    private Context b;
    private String c;
    private JSONObject d;
    private JSONObject e;
    private boolean f;
    
    public cu(final bs bs, final bs bs2, final String c, final Context b, final au a) {
        super(bs, bs2);
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
        Label_0430_Outer:
            while (true) {
            Label_0065:
                while (true) {
                    Label_0030: {
                        if (jsonObject.optBoolean("internalExceptionReporting", false)) {
                            dy.a = dy$a.b;
                            i.d();
                            break Label_0030;
                        }
                        Label_0421: {
                            break Label_0421;
                            h h;
                            Context b2 = null;
                            SharedPreferences$Editor edit = null;
                            SharedPreferences$Editor edit2;
                            final JSONObject optJSONObject;
                            final du m;
                            File a;
                            Label_0201_Outer:Label_0284_Outer:
                            while (true) {
                                while (true) {
                                Label_0665:
                                    while (true) {
                                    Label_0633:
                                        while (true) {
                                            try {
                                                new dk(new cv(this.a).a("device_name", this.a.i()).a("pkg", this.b.getPackageName()), new dd(new dc(this.c, "/android_v2/update_package_name").a()), null).run();
                                                this.f = true;
                                                this.d = jsonObject.optJSONObject("apm");
                                                if (this.d != null) {
                                                    h = new h(this.d);
                                                    b2 = this.b;
                                                    if (!h.c) {
                                                        break Label_0633;
                                                    }
                                                    crittercism.android.h.b(b2);
                                                    edit = b2.getSharedPreferences("com.crittercism.optmz.config", 0).edit();
                                                    if (!h.b) {
                                                        break Label_0665;
                                                    }
                                                    edit.putBoolean("enabled", h.a);
                                                    edit.putBoolean("kill", h.c);
                                                    edit.putBoolean("persist", h.b);
                                                    edit.putInt("interval", h.d);
                                                    edit.commit();
                                                    az.A().a(h);
                                                }
                                                this.e = jsonObject.optJSONObject("txnConfig");
                                                if (this.e != null) {
                                                    jsonObject = (JSONObject)new bh(this.e);
                                                    edit2 = this.b.getSharedPreferences("com.crittercism.txn.config", 0).edit();
                                                    edit2.putBoolean("enabled", ((bh)jsonObject).a);
                                                    edit2.putInt("interval", ((bh)jsonObject).b);
                                                    edit2.putInt("defaultTimeout", ((bh)jsonObject).c);
                                                    edit2.putString("transactions", ((bh)jsonObject).d.toString());
                                                    edit2.commit();
                                                    az.A().a((bh)jsonObject);
                                                }
                                                return;
                                                try {
                                                    if ((n = optJSONObject.getInt("rateAfterLoadNum")) < 0) {
                                                        n = 0;
                                                    }
                                                    m.a.edit().putInt("rateAfterNumLoads", n).commit();
                                                    if ((n = optJSONObject.getInt("remindAfterLoadNum")) <= 0) {
                                                        n = 1;
                                                    }
                                                    m.a.edit().putInt("remindAfterNumLoads", n).commit();
                                                    m.a.edit().putString("rateAppMessage", optJSONObject.getString("message")).commit();
                                                    m.a.edit().putString("rateAppTitle", optJSONObject.getString("title")).commit();
                                                    m.a(true);
                                                }
                                                catch (JSONException ex2) {
                                                    m.a(false);
                                                }
                                                break Label_0065;
                                                dy.a = dy$a.c;
                                                break;
                                            }
                                            catch (IOException ex) {
                                                new StringBuilder("IOException in handleResponse(): ").append(ex.getMessage());
                                                dy.b();
                                                dy.c();
                                                continue Label_0201_Outer;
                                            }
                                            break;
                                        }
                                        a = crittercism.android.h.a(b2);
                                        if (!a.delete() && a.exists()) {
                                            dy.b("Unable to reenable OPTMZ instrumentation");
                                            continue Label_0284_Outer;
                                        }
                                        continue Label_0284_Outer;
                                    }
                                    edit.clear();
                                    continue Label_0430_Outer;
                                }
                            }
                        }
                    }
                    final du m = this.a.m();
                    if (m != null) {
                        final JSONObject optJSONObject = jsonObject.optJSONObject("rateMyApp");
                        if (optJSONObject != null) {
                            continue;
                        }
                        m.a(false);
                    }
                    break;
                }
                if (jsonObject.optInt("needPkg", 0) == 1) {
                    continue Label_0430_Outer;
                }
                break;
            }
            continue;
        }
    }
}
