// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

public final class cd implements ch
{
    private JSONObject a;
    private JSONObject b;
    private JSONArray c;
    private JSONArray d;
    private File e;
    private String f;
    
    public cd(final File e, final bs bs, final bs bs2, final bs bs3, final bs bs4) {
        e.exists();
        this.f = cg.a.a();
        this.e = e;
        this.a = new bu().a(new bx$c()).a(new bx$b()).a(new bx$d()).a(new bx$f()).a(new bx$o()).a(new bx$p()).a(new bx$j()).a(new bx$h()).a(new bx$z()).a(new bx$aa()).a(new bx$k()).a(new bx$r()).a(new bx$m()).a(new bx$s()).a(new bx$w()).a(new bx$x()).a();
        final HashMap<String, JSONArray> hashMap = new HashMap<String, JSONArray>();
        hashMap.put("crashed_session", new bo(bs).a);
        if (bs2.b() > 0) {
            hashMap.put("previous_session", new bo(bs2).a);
        }
        this.b = new JSONObject((Map)hashMap);
        this.c = new bo(bs3).a;
        this.d = new bo(bs4).a;
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
        hashMap2.put("dmp_file", cs.a(array));
        hashMap.put("ndk_dmp_info", new JSONObject((Map)hashMap2));
        outputStream.write(new JSONObject((Map)hashMap).toString().getBytes());
    }
    
    @Override
    public final String e() {
        return this.f;
    }
}
