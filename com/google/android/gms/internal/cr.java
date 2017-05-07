// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import android.content.Context;

public final class cr extends cm
{
    private final String iG;
    private final String iH;
    private final Context mContext;
    
    public cr(final Context mContext, final String ig, final String ih) {
        this.mContext = mContext;
        this.iG = ig;
        this.iH = ih;
    }
    
    @Override
    public void ai() {
        try {
            ct.u("Pinging URL: " + this.iH);
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.iH).openConnection();
            try {
                co.a(this.mContext, this.iG, true, httpURLConnection);
                final int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    ct.v("Received non-success response code " + responseCode + " from pinging URL: " + this.iH);
                }
            }
            finally {
                httpURLConnection.disconnect();
            }
        }
        catch (IOException ex) {
            ct.v("Error while pinging URL: " + this.iH + ". " + ex.getMessage());
        }
    }
    
    @Override
    public void onStop() {
    }
}
