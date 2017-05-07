// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import android.content.Context;

public final class du extends do
{
    private final String lh;
    private final Context mContext;
    private final String ro;
    
    public du(final Context mContext, final String lh, final String ro) {
        this.mContext = mContext;
        this.lh = lh;
        this.ro = ro;
    }
    
    @Override
    public void aY() {
        try {
            dw.y("Pinging URL: " + this.ro);
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.ro).openConnection();
            try {
                dq.a(this.mContext, this.lh, true, httpURLConnection);
                final int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    dw.z("Received non-success response code " + responseCode + " from pinging URL: " + this.ro);
                }
            }
            finally {
                httpURLConnection.disconnect();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            dw.z("Error while parsing ping URL: " + this.ro + ". " + ex.getMessage());
        }
        catch (IOException ex2) {
            dw.z("Error while pinging URL: " + this.ro + ". " + ex2.getMessage());
        }
    }
    
    @Override
    public void onStop() {
    }
}
