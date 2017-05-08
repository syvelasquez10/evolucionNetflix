// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.net.SocketTimeoutException;
import java.io.IOException;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class dj extends di
{
    private cw a;
    private dc b;
    private boolean c;
    private cy d;
    
    public dj(final cw cw, final dc dc, final cy cy) {
        this(cw, dc, false, cy);
    }
    
    public dj(final cw a, final dc b, final boolean c, final cy d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public final void a() {
        Label_0329: {
            try {
                final HttpURLConnection a = this.b.a();
                if (a == null) {
                    return;
                }
                try {
                    this.a.a(a.getOutputStream());
                    a.getResponseCode();
                    try {
                        if (!this.c) {
                            break Label_0329;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a.getInputStream()));
                        while (true) {
                            final int read = bufferedReader.read();
                            if (read == -1) {
                                goto Label_0159;
                            }
                            sb.append((char)read);
                        }
                    }
                    catch (UnsupportedEncodingException ex) {}
                    catch (JSONException ex2) {}
                    catch (IOException ex3) {}
                    catch (SocketTimeoutException ex4) {}
                }
                catch (SocketTimeoutException ex5) {}
                catch (IOException ex6) {}
                catch (JSONException ex7) {}
                catch (UnsupportedEncodingException sb) {}
            }
            catch (GeneralSecurityException ex8) {
                return;
            }
            catch (IOException ex9) {
                return;
            }
        }
        goto Label_0178;
    }
}
