// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.io.FileInputStream;
import java.io.Serializable;
import java.util.UUID;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import android.content.Context;

class h implements m
{
    private static final Object sf;
    private static h st;
    private final Context mContext;
    private String su;
    private boolean sv;
    private final Object sw;
    
    static {
        sf = new Object();
    }
    
    protected h(final Context mContext) {
        this.sv = false;
        this.sw = new Object();
        this.mContext = mContext;
        this.ce();
    }
    
    private boolean D(final String s) {
        try {
            aa.y("Storing clientId.");
            final FileOutputStream openFileOutput = this.mContext.openFileOutput("gaClientId", 0);
            openFileOutput.write(s.getBytes());
            openFileOutput.close();
            return true;
        }
        catch (FileNotFoundException ex) {
            aa.w("Error creating clientId file.");
            return false;
        }
        catch (IOException ex2) {
            aa.w("Error writing to clientId file.");
            return false;
        }
    }
    
    public static h cb() {
        synchronized (h.sf) {
            return h.st;
        }
    }
    
    private String cc() {
        Label_0042: {
            if (this.sv) {
                break Label_0042;
            }
            synchronized (this.sw) {
                Label_0040: {
                    if (this.sv) {
                        break Label_0040;
                    }
                    aa.y("Waiting for clientId to load");
                    try {
                        do {
                            this.sw.wait();
                        } while (!this.sv);
                        // monitorexit(this.sw)
                        aa.y("Loaded clientId");
                        return this.su;
                    }
                    catch (InterruptedException ex) {
                        aa.w("Exception while waiting for clientId: " + ex);
                    }
                }
            }
        }
    }
    
    private void ce() {
        new Thread("client_id_fetcher") {
            @Override
            public void run() {
                synchronized (h.this.sw) {
                    h.this.su = h.this.cf();
                    h.this.sv = true;
                    h.this.sw.notifyAll();
                }
            }
        }.start();
    }
    
    public static void n(final Context context) {
        synchronized (h.sf) {
            if (h.st == null) {
                h.st = new h(context);
            }
        }
    }
    
    public boolean C(final String s) {
        return "&cid".equals(s);
    }
    
    protected String cd() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase();
        try {
            if (!this.D(lowerCase)) {
                lowerCase = "0";
            }
            return lowerCase;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    String cf() {
        final IOException ex = null;
        Serializable cd = null;
        while (true) {
            FileInputStream openFileInput = null;
            try {
                openFileInput = this.mContext.openFileInput("gaClientId");
                final byte[] array = new byte[128];
                final int read = openFileInput.read(array, 0, 128);
                String s;
                if (openFileInput.available() > 0) {
                    aa.w("clientId file seems corrupted, deleting it.");
                    openFileInput.close();
                    this.mContext.deleteFile("gaClientId");
                    s = (String)cd;
                }
                else if (read <= 0) {
                    aa.w("clientId file seems empty, deleting it.");
                    openFileInput.close();
                    this.mContext.deleteFile("gaClientId");
                    s = (String)cd;
                }
                else {
                    s = new String(array, 0, read);
                    final FileInputStream fileInputStream = openFileInput;
                    fileInputStream.close();
                }
                cd = s;
                if (s == null) {
                    cd = this.cd();
                }
                return (String)cd;
            }
            catch (IOException s) {
                s = (String)ex;
            }
            catch (FileNotFoundException ex2) {
                final String s = (String)cd;
                continue;
            }
            try {
                final FileInputStream fileInputStream = openFileInput;
                fileInputStream.close();
                continue;
            }
            catch (IOException ex3) {}
            catch (FileNotFoundException ex4) {}
            break;
        }
    }
    
    @Override
    public String getValue(final String s) {
        if ("&cid".equals(s)) {
            return this.cc();
        }
        return null;
    }
}
