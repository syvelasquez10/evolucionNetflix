// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.webview;

import org.json.JSONException;
import org.json.JSONObject;
import com.crittercism.internal.co;
import android.webkit.JavascriptInterface;
import com.crittercism.internal.dw;
import com.crittercism.internal.ax;

public class CritterJSInterface
{
    private ax a;
    
    public CritterJSInterface(final ax a) {
        if (a == null) {
            a("CritterJSInterface");
        }
        this.a = a;
    }
    
    private static void a(final String s) {
        dw.b(CritterJSInterface.class.getName() + "." + s + "() badly initialized: null instance.", new NullPointerException());
    }
    
    private static void a(final String s, final String s2, final long n) {
        b(s, s2, "negative long integer: " + n);
    }
    
    private static boolean a(String string, final String s, final String s2) {
        if (string == null) {
            if (s2.length() > 0) {
                string = s2 + " ";
            }
            else {
                string = "";
            }
            dw.b(CritterJSInterface.class.getName() + "." + s + "() given invalid " + string + "parameter: null string or invalid javascript type. Request is being ignored...", new NullPointerException());
            return false;
        }
        if (string.length() == 0) {
            b(s, s2, "empty string");
            return false;
        }
        return true;
    }
    
    private static void b(final String s) {
        dw.d("User has opted out of Crittercism. " + CritterJSInterface.class.getName() + "." + s + "() call is being ignored...");
    }
    
    private static void b(final String s, String string, final String s2) {
        if (string != null && string.length() > 0) {
            string += " ";
        }
        else {
            string = "";
        }
        dw.b(CritterJSInterface.class.getName() + "." + s + "() given invalid " + string + "parameter: " + s2 + ". Request is being ignored.", new IllegalArgumentException());
    }
    
    @JavascriptInterface
    public void beginTransaction(final String s) {
        try {
            if (this.a == null) {
                a("beginTransaction");
                return;
            }
            if (this.a.g.a()) {
                b("beginTransaction");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public void cancelTransaction(final String s) {
        try {
            if (this.a == null) {
                a("cancelTransaction");
                return;
            }
            if (this.a.g.a()) {
                b("cancelTransaction");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public void endTransaction(final String s) {
        try {
            if (this.a == null) {
                a("endTransaction");
                return;
            }
            if (this.a.g.a()) {
                b("endTransaction");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public void failTransaction(final String s) {
        try {
            if (this.a == null) {
                a("failTransaction");
                return;
            }
            if (this.a.g.a()) {
                b("failTransaction");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public int getTransactionValue(final String s) {
        try {
            if (this.a == null) {
                a("getTransactionValue");
                return -1;
            }
            if (this.a.g.a()) {
                b("getTransactionValue");
                return -1;
            }
            goto Label_0037;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
        return -1;
    }
    
    @JavascriptInterface
    public void leaveBreadcrumb(final String s) {
        try {
            if (this.a == null) {
                a("leaveBreadcrumb");
                return;
            }
            if (this.a.g.a()) {
                b("leaveBreadcrumb");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public void logError(String trim, final String s) {
        try {
            if (this.a == null) {
                a("logError");
                return;
            }
            if (trim != null && trim.length() != 0 && s != null && s.length() != 0) {
                final String s2 = "";
                String trim2 = "";
                final String[] split = trim.split(":", 2);
                trim = s2;
                if (split.length > 0) {
                    if (split[0].indexOf("Uncaught ") >= 0) {
                        goto Label_0121;
                    }
                    trim = split[0];
                    trim = trim.trim();
                }
                if (split.length > 1) {
                    trim2 = split[1].trim();
                }
                this.a.b(new co(trim, trim2, s, false));
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public void logHandledException(final String s, final String s2, final String s3) {
        try {
            if (this.a == null) {
                a("logHandledException");
                return;
            }
            if (this.a.g.a()) {
                b("logHandledException");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public void logNetworkRequest(final String s, final String s2, final long n, final long n2, final long n3, final int n4, final int n5) {
        try {
            if (this.a == null) {
                a("logNetworkRequest");
                return;
            }
            if (this.a.g.a()) {
                b("logNetworkRequest");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        for (int i = 0; i < 9; ++i) {
            if ((new String[] { "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "CONNECT", "OPTIONS", "PATCH" })[i].equals(s)) {
                goto Label_0049;
            }
        }
        b("logNetworkRequest", "httpMethod", s);
        goto Label_0049;
        // iftrue(Label_0363:, n7 == 0)
        // iftrue(Label_0321:, n >= 0L)
        // iftrue(Label_0285:, n4 >= 0)
        // iftrue(Label_0682:, new int[] { 0, 100, 101, 200, 201, 202, 203, 204, 205, 206, 300, 301, 302, 303, 304, 305, 306, 307, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 500, 501, 502, 503, 504, 505 }[n6] != n4)
        // iftrue(Label_0363:, b == false)
        // iftrue(Label_0357:, n8 >= 0L)
        // iftrue(Label_0291:, n6 >= 42)
        boolean b;
        int n6;
        int n7;
        long n8 = 0L;
        Block_11_Outer:Block_6_Outer:Block_10_Outer:
        while (true) {
            Block_8: {
                while (true) {
                    Label_0373: {
                        while (true) {
                            Label_0230: {
                                while (true) {
                                Block_7:
                                    while (true) {
                                        b("logNetworkRequest", "responseCode", "negative integer: " + n4);
                                        b = false;
                                        break Label_0230;
                                        Label_0682: {
                                            ++n6;
                                        }
                                        break Label_0373;
                                        break Block_8;
                                        b = true;
                                        break Label_0230;
                                        n8 = System.currentTimeMillis() - n;
                                        break Block_7;
                                        continue Block_11_Outer;
                                    }
                                    a("logNetworkRequest", "latency", n);
                                    n7 = 0;
                                    continue Block_11_Outer;
                                    Label_0291: {
                                        b("logNetworkRequest", "responseCode", "the given HTTP response is not allowed: " + n4);
                                    }
                                    b = false;
                                    break Label_0230;
                                    continue Block_6_Outer;
                                }
                            }
                            continue Block_10_Outer;
                        }
                        a("logNetworkRequest", "bytesSent", n3);
                        return;
                        Label_0285: {
                            n6 = 0;
                        }
                        break Label_0373;
                        Label_0321:
                        b("logNetworkRequest", "latency", "excessively large long integer: " + n);
                        n7 = 0;
                        continue Block_6_Outer;
                    }
                    continue;
                }
            }
            this.a.a(s, s2, n, n2, n3, n4, n5, n8);
            return;
            Label_0357: {
                n7 = 1;
            }
            continue;
        }
        Label_0363:;
    }
    
    @JavascriptInterface
    public void logUnhandledException(final String s, final String s2, final String s3) {
        try {
            if (this.a == null) {
                a("logUnhandledException");
                return;
            }
            if (this.a.g.a()) {
                b("logUnhandledException");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public void setMetadata(final String t) {
        try {
            if (this.a == null) {
                a("setMetadata");
                return;
            }
            if (!a((String)t, "setMetadata", "metadataJson")) {
                return;
            }
            if (this.a.g.a()) {
                b("setMetadata");
                return;
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        try {
            this.a.a(new JSONObject((String)t));
        }
        catch (JSONException ex) {
            b("setMetadata", "", "badly formatted json string. " + (String)t);
        }
    }
    
    @JavascriptInterface
    public void setTransactionValue(final String s, final int n) {
        try {
            if (this.a == null) {
                a("setTransactionValue");
                return;
            }
            if (this.a.g.a()) {
                b("setTransactionValue");
                return;
            }
            goto Label_0035;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
        }
    }
    
    @JavascriptInterface
    public void setUsername(final String s) {
        try {
            if (this.a == null) {
                a("setUsername");
                return;
            }
            if (this.a.g.a()) {
                b("setUsername");
                return;
            }
            goto Label_0037;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dw.b(t);
            return;
        }
        try {
            final JSONObject jsonObject;
            jsonObject.putOpt("username", (Object)s);
            this.a.a(jsonObject);
        }
        catch (JSONException ex) {
            dw.b("Crittercism.setUsername()", (Throwable)ex);
        }
    }
}
