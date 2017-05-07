// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.ab;
import android.os.RemoteException;
import com.google.android.gms.drive.internal.OpenFileIntentSenderRequest;
import com.google.android.gms.drive.internal.q;
import com.google.android.gms.common.internal.n;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;

public class OpenFileActivityBuilder
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private String No;
    private String[] Np;
    private DriveId Nq;
    
    public IntentSender build(final GoogleApiClient googleApiClient) {
        n.a(googleApiClient.isConnected(), (Object)"Client must be connected");
        if (this.Np == null) {
            this.Np = new String[0];
        }
        final ab hy = googleApiClient.a(Drive.CU).hY();
        try {
            return hy.a(new OpenFileIntentSenderRequest(this.No, this.Np, this.Nq));
        }
        catch (RemoteException ex) {
            throw new RuntimeException("Unable to connect Drive Play Service", (Throwable)ex);
        }
    }
    
    public OpenFileActivityBuilder setActivityStartFolder(final DriveId driveId) {
        this.Nq = n.i(driveId);
        return this;
    }
    
    public OpenFileActivityBuilder setActivityTitle(final String s) {
        this.No = n.i(s);
        return this;
    }
    
    public OpenFileActivityBuilder setMimeType(final String[] np) {
        n.b(np != null, (Object)"mimeTypes may not be null");
        this.Np = np;
        return this;
    }
}
