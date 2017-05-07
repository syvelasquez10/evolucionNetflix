// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.os.Build$VERSION;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONArray;
import java.io.InputStream;

public final class cc implements cb
{
    private static JSONArray a(InputStream ex) {
        final JSONArray jsonArray = new JSONArray();
        ex = (IOException)new BufferedReader(new InputStreamReader((InputStream)ex));
        int n = 0;
        try {
            do {
                final String line = ((BufferedReader)ex).readLine();
                if (line == null) {
                    break;
                }
                jsonArray.put((Object)line);
            } while (++n <= 200);
            try {
                ((BufferedReader)ex).close();
                return jsonArray;
            }
            catch (IOException ex) {
                dy.a();
                return jsonArray;
            }
        }
        catch (IOException ex2) {
            dy.a();
            try {
                ((BufferedReader)ex).close();
                return jsonArray;
            }
            catch (IOException ex3) {
                dy.a();
                return jsonArray;
            }
        }
        finally {
            try {
                ((BufferedReader)ex).close();
            }
            catch (IOException ex4) {
                dy.a();
            }
        }
    }
    
    @Override
    public final JSONArray a() {
        final JSONArray jsonArray = new JSONArray();
        Object a = null;
        JSONArray start = null;
        if (Build$VERSION.SDK_INT < 10) {
            jsonArray.put((Object)"Logcat data is not collected for Android APIs before 10 (Gingerbread)");
            jsonArray.put((Object)("API level is " + Build$VERSION.SDK_INT + "(" + Build$VERSION.CODENAME + ")"));
            a = jsonArray;
            return (JSONArray)a;
        }
        try {
            start = (JSONArray)(a = a(((Process)(a = (start = (JSONArray)new ProcessBuilder(new String[] { "logcat", "-t", Integer.toString(100), "-v", "time" }).redirectErrorStream(true).start()))).getInputStream()));
            return start;
        }
        catch (Exception ex) {
            a = start;
            dy.b("Unable to collect logcat data", ex);
            a = start;
            jsonArray.put((Object)("Unable to collect logcat data due to a(n) " + ex.getClass().getName()));
            a = start;
            final String message = ex.getMessage();
            if (message != null) {
                a = start;
                jsonArray.put((Object)message);
            }
            a = jsonArray;
            return jsonArray;
        }
        finally {
            if (a != null) {
                ((Process)a).destroy();
            }
        }
    }
}
