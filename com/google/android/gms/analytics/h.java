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

class h implements l
{
    private static h xQ;
    private static final Object xz;
    private final Context mContext;
    private String xR;
    private boolean xS;
    private final Object xT;
    
    static {
        xz = new Object();
    }
    
    protected h(final Context mContext) {
        this.xS = false;
        this.xT = new Object();
        this.mContext = mContext;
        this.dV();
    }
    
    private boolean ad(final String s) {
        try {
            z.V("Storing clientId.");
            final FileOutputStream openFileOutput = this.mContext.openFileOutput("gaClientId", 0);
            openFileOutput.write(s.getBytes());
            openFileOutput.close();
            return true;
        }
        catch (FileNotFoundException ex) {
            z.T("Error creating clientId file.");
            return false;
        }
        catch (IOException ex2) {
            z.T("Error writing to clientId file.");
            return false;
        }
    }
    
    public static h dR() {
        synchronized (h.xz) {
            return h.xQ;
        }
    }
    
    private String dT() {
        Label_0042: {
            if (this.xS) {
                break Label_0042;
            }
            synchronized (this.xT) {
                Label_0040: {
                    if (this.xS) {
                        break Label_0040;
                    }
                    z.V("Waiting for clientId to load");
                    try {
                        do {
                            this.xT.wait();
                        } while (!this.xS);
                        // monitorexit(this.xT)
                        z.V("Loaded clientId");
                        return this.xR;
                    }
                    catch (InterruptedException ex) {
                        z.T("Exception while waiting for clientId: " + ex);
                    }
                }
            }
        }
    }
    
    private void dV() {
        new h$1(this, "client_id_fetcher").start();
    }
    
    public static void y(final Context context) {
        synchronized (h.xz) {
            if (h.xQ == null) {
                h.xQ = new h(context);
            }
        }
    }
    
    public boolean ac(final String s) {
        return "&cid".equals(s);
    }
    
    String dS() {
        synchronized (this.xT) {
            return this.xR = this.dU();
        }
    }
    
    protected String dU() {
        String lowerCase = UUID.randomUUID().toString().toLowerCase();
        try {
            if (!this.ad(lowerCase)) {
                lowerCase = "0";
            }
            return lowerCase;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    String dW() {
        final IOException ex = null;
        Serializable du = null;
        while (true) {
            FileInputStream openFileInput = null;
            try {
                openFileInput = this.mContext.openFileInput("gaClientId");
                final byte[] array = new byte[128];
                final int read = openFileInput.read(array, 0, 128);
                String s;
                if (openFileInput.available() > 0) {
                    z.T("clientId file seems corrupted, deleting it.");
                    openFileInput.close();
                    this.mContext.deleteFile("gaClientId");
                    s = (String)du;
                }
                else if (read <= 0) {
                    z.T("clientId file seems empty, deleting it.");
                    openFileInput.close();
                    this.mContext.deleteFile("gaClientId");
                    s = (String)du;
                }
                else {
                    s = new String(array, 0, read);
                    final FileInputStream fileInputStream = openFileInput;
                    fileInputStream.close();
                    final String s2 = "Loaded client id from disk.";
                    z.V(s2);
                }
                du = s;
                if (s == null) {
                    du = this.dU();
                }
                return (String)du;
            }
            catch (IOException s) {
                s = (String)ex;
            }
            catch (FileNotFoundException ex2) {
                final String s = (String)du;
                continue;
            }
            try {
                final FileInputStream fileInputStream = openFileInput;
                fileInputStream.close();
                final String s2 = "Loaded client id from disk.";
                z.V(s2);
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
            return this.dT();
        }
        return null;
    }
}
