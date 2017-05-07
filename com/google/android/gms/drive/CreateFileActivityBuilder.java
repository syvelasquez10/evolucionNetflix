// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.internal.o;
import java.io.IOException;
import android.os.RemoteException;
import com.google.android.gms.drive.internal.CreateFileIntentSenderRequest;
import com.google.android.gms.drive.internal.j;
import com.google.android.gms.internal.eg;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;

public class CreateFileActivityBuilder
{
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private MetadataChangeSet qJ;
    private Contents qK;
    private String qL;
    private DriveId qM;
    
    public IntentSender build(GoogleApiClient cn) {
        eg.b(this.qK, "Must provide initial contents to CreateFileActivityBuilder.");
        while (true) {
            try {
                this.qK.getParcelFileDescriptor().close();
                this.qK.close();
                eg.a(((GoogleApiClient)cn).isConnected(), (Object)"Client must be connected");
                cn = (RemoteException)((GoogleApiClient)cn).a(Drive.jO).cN();
                try {
                    return ((o)cn).a(new CreateFileIntentSenderRequest(this.qJ.cM(), this.qK.cJ(), this.qL, this.qM));
                }
                catch (RemoteException cn) {
                    throw new RuntimeException("Unable to connect Drive Play Service", (Throwable)cn);
                }
            }
            catch (IOException ex) {
                continue;
            }
            break;
        }
    }
    
    public CreateFileActivityBuilder setActivityStartFolder(final DriveId driveId) {
        this.qM = eg.f(driveId);
        return this;
    }
    
    public CreateFileActivityBuilder setActivityTitle(final String s) {
        this.qL = eg.f(s);
        return this;
    }
    
    public CreateFileActivityBuilder setInitialContents(final Contents contents) {
        this.qK = eg.f(contents);
        return this;
    }
    
    public CreateFileActivityBuilder setInitialMetadata(final MetadataChangeSet set) {
        this.qJ = eg.f(set);
        return this;
    }
}
