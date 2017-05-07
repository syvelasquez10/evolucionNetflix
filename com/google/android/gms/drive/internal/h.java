// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.Drive;
import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.DriveId;

public class h
{
    private String No;
    private DriveId Nq;
    protected MetadataChangeSet Oa;
    private Integer Ob;
    private final int Oc;
    
    public h(final int oc) {
        this.Oc = oc;
    }
    
    public void a(final DriveId driveId) {
        this.Nq = n.i(driveId);
    }
    
    public void a(final MetadataChangeSet set) {
        this.Oa = n.i(set);
    }
    
    public void bi(final String s) {
        this.No = n.i(s);
    }
    
    public void bk(final int n) {
        this.Ob = n;
    }
    
    public IntentSender build(final GoogleApiClient googleApiClient) {
        n.b(this.Oa, "Must provide initial metadata to CreateFileActivityBuilder.");
        n.a(googleApiClient.isConnected(), (Object)"Client must be connected");
        final q q = googleApiClient.a(Drive.CU);
        this.Oa.hS().setContext(q.getContext());
        final ab hy = q.hY();
        Label_0098: {
            if (this.Ob != null) {
                break Label_0098;
            }
            int intValue = -1;
            try {
                return hy.a(new CreateFileIntentSenderRequest(this.Oa.hS(), intValue, this.No, this.Nq, this.Oc));
                intValue = this.Ob;
                return hy.a(new CreateFileIntentSenderRequest(this.Oa.hS(), intValue, this.No, this.Nq, this.Oc));
            }
            catch (RemoteException ex) {
                throw new RuntimeException("Unable to connect Drive Play Service", (Throwable)ex);
            }
        }
    }
}
