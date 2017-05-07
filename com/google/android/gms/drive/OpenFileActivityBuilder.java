// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.o;
import android.os.RemoteException;
import com.google.android.gms.drive.internal.OpenFileIntentSenderRequest;
import com.google.android.gms.drive.internal.j;
import com.google.android.gms.internal.eg;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;

public class OpenFileActivityBuilder
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private String qL;
    private DriveId qM;
    private String[] qW;
    
    public IntentSender build(final GoogleApiClient googleApiClient) {
        eg.b(this.qW, "setMimeType(String[]) must be called on this builder before calling build()");
        eg.a(googleApiClient.isConnected(), (Object)"Client must be connected");
        final o cn = googleApiClient.a(Drive.jO).cN();
        try {
            return cn.a(new OpenFileIntentSenderRequest(this.qL, this.qW, this.qM));
        }
        catch (RemoteException ex) {
            throw new RuntimeException("Unable to connect Drive Play Service", (Throwable)ex);
        }
    }
    
    public OpenFileActivityBuilder setActivityStartFolder(final DriveId driveId) {
        this.qM = eg.f(driveId);
        return this;
    }
    
    public OpenFileActivityBuilder setActivityTitle(final String s) {
        this.qL = eg.f(s);
        return this;
    }
    
    public OpenFileActivityBuilder setMimeType(final String[] qw) {
        eg.b(qw != null && qw.length > 0, "mimeTypes may not be null and must contain at least one value");
        this.qW = qw;
        return this;
    }
}
