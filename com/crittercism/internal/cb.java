// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

public final class cb implements cf
{
    private JSONObject a;
    private JSONObject b;
    private JSONArray c;
    private JSONArray d;
    private File e;
    private String f;
    
    public cb(final File e, final bq bq, final bq bq2, final bq bq3, final bq bq4) {
        e.exists();
        this.f = ce.a.a();
        this.e = e;
        this.a = new bs().a(new bv$c()).a(new bv$b()).a(new bv$d()).a(new bv$f()).a(new bv$o()).a(new bv$p()).a(new bv$j()).a(new bv$h()).a(new bv$z()).a(new bv$aa()).a(new bv$k()).a(new bv$r()).a(new bv$m()).a(new bv$s()).a(new bv$w()).a(new bv$x()).a();
        final HashMap<String, JSONArray> hashMap = new HashMap<String, JSONArray>();
        hashMap.put("crashed_session", new bm(bq).a);
        if (bq2.b() > 0) {
            hashMap.put("previous_session", new bm(bq2).a);
        }
        this.b = new JSONObject((Map)hashMap);
        this.c = new bm(bq3).a;
        this.d = new bm(bq4).a;
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        final HashMap<String, JSONObject> hashMap = new HashMap<String, JSONObject>();
        hashMap.put("app_state", this.a);
        hashMap.put("breadcrumbs", this.b);
        hashMap.put("endpoints", (JSONObject)this.c);
        hashMap.put("systemBreadcrumbs", (JSONObject)this.d);
        byte[] array = new byte[0];
        final byte[] array2 = new byte[8192];
        final FileInputStream fileInputStream = new FileInputStream(this.e);
        while (true) {
            final int read = fileInputStream.read(array2);
            if (read == -1) {
                break;
            }
            final byte[] array3 = new byte[array.length + read];
            System.arraycopy(array, 0, array3, 0, array.length);
            System.arraycopy(array2, 0, array3, array.length, read);
            array = array3;
        }
        fileInputStream.close();
        final HashMap<String, String> hashMap2 = new HashMap<String, String>();
        hashMap2.put("dmp_name", this.e.getName());
        hashMap2.put("dmp_file", cr.a(array));
        hashMap.put("ndk_dmp_info", new JSONObject((Map)hashMap2));
        outputStream.write(new JSONObject((Map)hashMap).toString().getBytes());
    }
    
    @Override
    public final String e() {
        return this.f;
    }
}
