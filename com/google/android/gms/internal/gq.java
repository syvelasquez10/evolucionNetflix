// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import android.content.Context;

@ez
public final class gq extends gg
{
    private final Context mContext;
    private final String mv;
    private final String uR;
    private String vW;
    
    public gq(final Context mContext, final String mv, final String ur) {
        this.vW = null;
        this.mContext = mContext;
        this.mv = mv;
        this.uR = ur;
    }
    
    public gq(final Context mContext, final String mv, final String ur, final String vw) {
        this.vW = null;
        this.mContext = mContext;
        this.mv = mv;
        this.uR = ur;
        this.vW = vw;
    }
    
    @Override
    public void cp() {
        try {
            gs.V("Pinging URL: " + this.uR);
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.uR).openConnection();
            try {
                if (this.vW == null) {
                    gj.a(this.mContext, this.mv, true, httpURLConnection);
                }
                else {
                    gj.a(this.mContext, this.mv, true, httpURLConnection, this.vW);
                }
                final int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    gs.W("Received non-success response code " + responseCode + " from pinging URL: " + this.uR);
                }
            }
            finally {
                httpURLConnection.disconnect();
            }
        }
        catch (IndexOutOfBoundsException ex) {
            gs.W("Error while parsing ping URL: " + this.uR + ". " + ex.getMessage());
        }
        catch (IOException ex2) {
            gs.W("Error while pinging URL: " + this.uR + ". " + ex2.getMessage());
        }
    }
    
    @Override
    public void onStop() {
    }
}
