// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.u;
import android.os.RemoteException;
import com.google.android.gms.drive.internal.OpenFileIntentSenderRequest;
import com.google.android.gms.drive.internal.n;
import com.google.android.gms.internal.fq;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;

public class OpenFileActivityBuilder
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private String EB;
    private DriveId EC;
    private String[] EQ;
    
    public IntentSender build(final GoogleApiClient googleApiClient) {
        fq.b(this.EQ, "setMimeType(String[]) must be called on this builder before calling build()");
        fq.a(googleApiClient.isConnected(), (Object)"Client must be connected");
        final u fe = googleApiClient.a(Drive.wx).fE();
        try {
            return fe.a(new OpenFileIntentSenderRequest(this.EB, this.EQ, this.EC));
        }
        catch (RemoteException ex) {
            throw new RuntimeException("Unable to connect Drive Play Service", (Throwable)ex);
        }
    }
    
    public OpenFileActivityBuilder setActivityStartFolder(final DriveId driveId) {
        this.EC = fq.f(driveId);
        return this;
    }
    
    public OpenFileActivityBuilder setActivityTitle(final String s) {
        this.EB = fq.f(s);
        return this;
    }
    
    public OpenFileActivityBuilder setMimeType(final String[] eq) {
        fq.b(eq != null && eq.length > 0, "mimeTypes may not be null and must contain at least one value");
        this.EQ = eq;
        return this;
    }
}
