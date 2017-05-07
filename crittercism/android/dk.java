// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.net.SocketTimeoutException;
import java.io.IOException;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class dk extends dj
{
    private cx a;
    private dd b;
    private boolean c;
    private cz d;
    
    public dk(final cx cx, final dd dd, final cz cz) {
        this(cx, dd, false, cz);
    }
    
    public dk(final cx a, final dd b, final boolean c, final cz d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public final void a() {
        Label_0315: {
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
                            break Label_0315;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a.getInputStream()));
                        while (true) {
                            final int read = bufferedReader.read();
                            if (read == -1) {
                                goto Label_0155;
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
        goto Label_0174;
    }
}
