// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.internal.fh;
import com.google.android.gms.internal.fi;
import java.util.Date;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.common.data.Freezable;

public abstract class Metadata implements Freezable<Metadata>
{
    protected abstract <T> T a(final MetadataField<T> p0);
    
    public Date getCreatedDate() {
        return this.a(fi.rL);
    }
    
    public DriveId getDriveId() {
        return this.a(fh.rG);
    }
    
    public String getMimeType() {
        return this.a(fh.MIME_TYPE);
    }
    
    public Date getModifiedByMeDate() {
        return this.a(fi.rK);
    }
    
    public Date getModifiedDate() {
        return this.a(fi.rJ);
    }
    
    public Date getSharedWithMeDate() {
        return this.a(fi.rM);
    }
    
    public String getTitle() {
        return this.a(fh.TITLE);
    }
    
    public boolean isEditable() {
        final Boolean b = this.a(fh.rH);
        return b != null && b;
    }
    
    public boolean isFolder() {
        return "application/vnd.google-apps.folder".equals(this.getMimeType());
    }
    
    public boolean isStarred() {
        final Boolean b = this.a(fh.STARRED);
        return b != null && b;
    }
    
    public boolean isTrashed() {
        final Boolean b = this.a(fh.TRASHED);
        return b != null && b;
    }
}
