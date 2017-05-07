// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.u;
import java.io.IOException;
import android.os.RemoteException;
import com.google.android.gms.drive.internal.CreateFileIntentSenderRequest;
import com.google.android.gms.drive.internal.n;
import com.google.android.gms.internal.fq;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;

public class CreateFileActivityBuilder
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private Contents EA;
    private String EB;
    private DriveId EC;
    private MetadataChangeSet Ez;
    
    public IntentSender build(GoogleApiClient fe) {
        fq.b(this.Ez, "Must provide initial metadata to CreateFileActivityBuilder.");
        fq.b(this.EA, "Must provide initial contents to CreateFileActivityBuilder.");
        while (true) {
            try {
                this.EA.getParcelFileDescriptor().close();
                this.EA.close();
                fq.a(((GoogleApiClient)fe).isConnected(), (Object)"Client must be connected");
                fe = (RemoteException)((GoogleApiClient)fe).a(Drive.wx).fE();
                try {
                    return ((u)fe).a(new CreateFileIntentSenderRequest(this.Ez.fD(), this.EA.fA(), this.EB, this.EC));
                }
                catch (RemoteException fe) {
                    throw new RuntimeException("Unable to connect Drive Play Service", (Throwable)fe);
                }
            }
            catch (IOException ex) {
                continue;
            }
            break;
        }
    }
    
    public CreateFileActivityBuilder setActivityStartFolder(final DriveId driveId) {
        this.EC = fq.f(driveId);
        return this;
    }
    
    public CreateFileActivityBuilder setActivityTitle(final String s) {
        this.EB = fq.f(s);
        return this;
    }
    
    public CreateFileActivityBuilder setInitialContents(final Contents contents) {
        this.EA = fq.f(contents);
        return this;
    }
    
    public CreateFileActivityBuilder setInitialMetadata(final MetadataChangeSet set) {
        this.Ez = fq.f(set);
        return this;
    }
}
