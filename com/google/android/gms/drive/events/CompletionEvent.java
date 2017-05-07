// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.events;

import android.os.Parcel;
import java.util.Locale;
import android.text.TextUtils;
import java.util.Collection;
import java.util.List;
import com.google.android.gms.drive.MetadataChangeSet;
import java.io.FileInputStream;
import java.io.InputStream;
import android.os.RemoteException;
import com.google.android.gms.drive.internal.ae;
import com.google.android.gms.drive.internal.v;
import com.google.android.gms.internal.jy;
import android.os.IBinder;
import java.util.ArrayList;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.drive.DriveId;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CompletionEvent implements SafeParcelable, ResourceEvent
{
    public static final Parcelable$Creator<CompletionEvent> CREATOR;
    public static final int STATUS_CONFLICT = 2;
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_SUCCESS = 0;
    final int BR;
    final String Dd;
    final int Fa;
    final DriveId MO;
    final ParcelFileDescriptor NF;
    final ParcelFileDescriptor NG;
    final MetadataBundle NH;
    final ArrayList<String> NI;
    final IBinder NJ;
    private boolean NK;
    private boolean NL;
    private boolean NM;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    CompletionEvent(final int br, final DriveId mo, final String dd, final ParcelFileDescriptor nf, final ParcelFileDescriptor ng, final MetadataBundle nh, final ArrayList<String> ni, final int fa, final IBinder nj) {
        this.NK = false;
        this.NL = false;
        this.NM = false;
        this.BR = br;
        this.MO = mo;
        this.Dd = dd;
        this.NF = nf;
        this.NG = ng;
        this.NH = nh;
        this.NI = ni;
        this.Fa = fa;
        this.NJ = nj;
    }
    
    private void L(final boolean b) {
        this.hU();
        this.NM = true;
        jy.a(this.NF);
        jy.a(this.NG);
        if (this.NJ == null) {
            final StringBuilder append = new StringBuilder().append("No callback on ");
            String s;
            if (b) {
                s = "snooze";
            }
            else {
                s = "dismiss";
            }
            v.q("CompletionEvent", append.append(s).toString());
            return;
        }
        try {
            ae.a.X(this.NJ).L(b);
        }
        catch (RemoteException ex) {
            final StringBuilder append2 = new StringBuilder().append("RemoteException on ");
            String s2;
            if (b) {
                s2 = "snooze";
            }
            else {
                s2 = "dismiss";
            }
            v.q("CompletionEvent", append2.append(s2).append(": ").append(ex).toString());
        }
    }
    
    private void hU() {
        if (this.NM) {
            throw new IllegalStateException("Event has already been dismissed or snoozed.");
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void dismiss() {
        this.L(false);
    }
    
    public String getAccountName() {
        this.hU();
        return this.Dd;
    }
    
    public InputStream getBaseContentsInputStream() {
        this.hU();
        if (this.NF == null) {
            return null;
        }
        if (this.NK) {
            throw new IllegalStateException("getBaseInputStream() can only be called once per CompletionEvent instance.");
        }
        this.NK = true;
        return new FileInputStream(this.NF.getFileDescriptor());
    }
    
    @Override
    public DriveId getDriveId() {
        this.hU();
        return this.MO;
    }
    
    public InputStream getModifiedContentsInputStream() {
        this.hU();
        if (this.NG == null) {
            return null;
        }
        if (this.NL) {
            throw new IllegalStateException("getModifiedInputStream() can only be called once per CompletionEvent instance.");
        }
        this.NL = true;
        return new FileInputStream(this.NG.getFileDescriptor());
    }
    
    public MetadataChangeSet getModifiedMetadataChangeSet() {
        this.hU();
        if (this.NH != null) {
            return new MetadataChangeSet(this.NH);
        }
        return null;
    }
    
    public int getStatus() {
        this.hU();
        return this.Fa;
    }
    
    public List<String> getTrackingTags() {
        this.hU();
        return new ArrayList<String>(this.NI);
    }
    
    public int getType() {
        return 2;
    }
    
    public void snooze() {
        this.L(true);
    }
    
    @Override
    public String toString() {
        String string;
        if (this.NI == null) {
            string = "<null>";
        }
        else {
            string = "'" + TextUtils.join((CharSequence)"','", (Iterable)this.NI) + "'";
        }
        return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", this.MO, this.Fa, string);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
