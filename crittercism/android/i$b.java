// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;

final class i$b extends Thread
{
    private InputStream a;
    private StringBuilder b;
    private BufferedReader c;
    
    public i$b(final InputStream a) {
        this.b = new StringBuilder();
        this.c = null;
        this.a = a;
    }
    
    public final StringBuilder a() {
        return this.b;
    }
    
    public final void b() {
        if (this.c == null) {
            return;
        }
        try {
            this.c.close();
        }
        catch (Exception ex) {
            this.c = null;
        }
    }
    
    @Override
    public final void run() {
        new String();
        this.c = new BufferedReader(new InputStreamReader(this.a));
        Label_0073: {
            try {
                while (true) {
                    final String line = this.c.readLine();
                    if (line == null) {
                        break Label_0073;
                    }
                    this.b.append(line);
                    this.b.append("\n");
                }
            }
            catch (Exception ex4) {
                try {
                    this.c.close();
                    return;
                    try {
                        this.c.close();
                    }
                    catch (Exception ex) {
                        new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ").append(ex.getClass().getName());
                    }
                }
                catch (Exception ex2) {
                    new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ").append(ex2.getClass().getName());
                }
            }
            finally {
                try {
                    this.c.close();
                }
                catch (Exception ex3) {
                    new StringBuilder("CrittercismAPI.StreamThread$makeLogcatJsonArray: ERROR closing bufferedReader!!! ").append(ex3.getClass().getName());
                }
            }
        }
    }
}
