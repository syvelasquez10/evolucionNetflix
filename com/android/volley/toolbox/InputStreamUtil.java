// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.io.IOException;
import com.android.volley.VolleyLog;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

public class InputStreamUtil
{
    public static String convertStreamToString(final InputStream ex) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)ex));
        final StringBuilder sb = new StringBuilder();
        try {
            Label_0082: {
                try {
                    while (true) {
                        final String line = bufferedReader.readLine();
                        if (line == null) {
                            break Label_0082;
                        }
                        sb.append(line + "\n");
                    }
                }
                catch (IOException ex2) {
                    VolleyLog.e("Failed to read: ", ex2);
                    if (ex == null) {
                        break Label_0082;
                    }
                    final IOException ex3 = ex;
                    ((InputStream)ex3).close();
                }
                try {
                    final IOException ex3 = ex;
                    ((InputStream)ex3).close();
                    return sb.toString();
                    try {
                        ((InputStream)ex).close();
                    }
                    catch (IOException ex) {
                        VolleyLog.e(ex, "failed to close inputStream", new Object[0]);
                    }
                    return sb.toString();
                }
                // iftrue(Label_0082:, ex == null)
                catch (IOException ex) {
                    VolleyLog.e(ex, "failed to close inputStream", new Object[0]);
                }
            }
        }
        finally {
            Label_0135: {
                if (ex == null) {
                    break Label_0135;
                }
                try {
                    ((InputStream)ex).close();
                }
                catch (IOException ex4) {
                    VolleyLog.e(ex4, "failed to close inputStream", new Object[0]);
                }
            }
        }
    }
}
