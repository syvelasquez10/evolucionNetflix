// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Date;
import java.net.MalformedURLException;
import org.json.JSONArray;
import java.net.URL;

public final class b implements o
{
    e a;
    private long b;
    private long c;
    private boolean d;
    private boolean e;
    private boolean f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private int k;
    private String l;
    private d m;
    private URL n;
    
    public b(final JSONArray jsonArray) {
        this.b = Long.MAX_VALUE;
        this.c = Long.MAX_VALUE;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = 0;
        this.h = 0;
        this.i = false;
        this.j = false;
        this.k = 0;
        this.l = "";
        this.m = crittercism.android.d.a;
        this.a = crittercism.android.e.a;
        this.l = jsonArray.getString(0);
        while (true) {
            try {
                URL n;
                if (jsonArray.getString(1).equals("NULL")) {
                    n = null;
                }
                else {
                    n = new URL(jsonArray.getString(1));
                }
                this.n = n;
                this.b = au.a.a(jsonArray.getString(2));
                this.c = this.b + jsonArray.getLong(3);
                this.a = crittercism.android.e.a(jsonArray.getInt(4));
                this.g = jsonArray.getInt(5);
                this.h = jsonArray.getInt(6);
                this.k = jsonArray.getInt(7);
                this.m = crittercism.android.d.a(jsonArray.getInt(9));
            }
            catch (MalformedURLException ex) {
                this.n = null;
                continue;
            }
            break;
        }
    }
    
    private long c() {
        long n = Long.MAX_VALUE;
        if (this.b != Long.MAX_VALUE) {
            n = n;
            if (this.c != Long.MAX_VALUE) {
                n = this.c - this.b;
            }
        }
        return n;
    }
    
    public final JSONArray a() {
        final JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.put((Object)this.l);
            String externalForm;
            if (this.n != null) {
                externalForm = this.n.toExternalForm();
            }
            else {
                externalForm = "NULL";
            }
            jsonArray.put((Object)externalForm);
            jsonArray.put((Object)au.a.a(new Date(this.b)));
            jsonArray.put(this.c());
            jsonArray.put(this.a.a());
            jsonArray.put(this.g);
            jsonArray.put(this.h);
            jsonArray.put(this.k);
            jsonArray.put(3);
            jsonArray.put((Object)Integer.toString(this.m.a()));
            return jsonArray;
        }
        catch (Exception ex) {
            System.out.println("Failed to create statsArray");
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public final String toString() {
        return "" + "Response time  : " + this.c() + "\n" + "Start time     : " + this.b + "\n" + "End time       : " + this.c + "\n" + "\n" + "Bytes out    : " + this.h + "\n" + "Bytes in     : " + this.g + "\n" + "\n" + "Response code  : " + this.k + "\n" + "Request method : " + this.l + "\n";
    }
}
