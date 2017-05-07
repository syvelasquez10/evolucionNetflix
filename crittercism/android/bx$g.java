// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.content.Context;
import android.net.NetworkInfo;
import org.json.JSONException;
import android.net.ConnectivityManager;
import org.json.JSONObject;

class bx$g implements bw
{
    private JSONObject a;
    
    public bx$g(final int n) {
        this.a = null;
        bx.b;
        bx.c;
        this.a = a(n);
    }
    
    private static JSONObject a(int n) {
        final int n2 = 1;
        final int n3 = 1;
        JSONObject jsonObject;
        if (!bx.c.c) {
            jsonObject = null;
        }
        else {
            if (!ConnectivityManager.isNetworkTypeValid(n)) {
                return null;
            }
            final NetworkInfo networkInfo = ((ConnectivityManager)bx.b.getSystemService("connectivity")).getNetworkInfo(n);
            final JSONObject jsonObject2 = new JSONObject();
            if (networkInfo != null) {
                while (true) {
                    while (true) {
                        try {
                            jsonObject2.put("available", networkInfo.isAvailable());
                            jsonObject2.put("connected", networkInfo.isConnected());
                            if (!networkInfo.isConnected()) {
                                jsonObject2.put("connecting", networkInfo.isConnectedOrConnecting());
                            }
                            jsonObject2.put("failover", networkInfo.isFailover());
                            if (n == 0) {
                                n = n3;
                                jsonObject = jsonObject2;
                                if (n != 0) {
                                    jsonObject2.put("roaming", networkInfo.isRoaming());
                                    return jsonObject2;
                                }
                                break;
                            }
                        }
                        catch (JSONException ex) {
                            dy.c();
                            return null;
                        }
                        n = 0;
                        continue;
                    }
                }
            }
            else {
                jsonObject2.put("available", false);
                jsonObject2.put("connected", false);
                jsonObject2.put("connecting", false);
                jsonObject2.put("failover", false);
                if (n == 0) {
                    n = n2;
                }
                else {
                    n = 0;
                }
                jsonObject = jsonObject2;
                if (n != 0) {
                    jsonObject2.put("roaming", false);
                    return jsonObject2;
                }
            }
        }
        return jsonObject;
    }
    
    @Override
    public String a() {
        return null;
    }
    
    public JSONObject c() {
        return this.a;
    }
}
