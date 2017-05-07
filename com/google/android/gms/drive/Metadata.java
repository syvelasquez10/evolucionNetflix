// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.internal.kf;
import java.util.Date;
import com.google.android.gms.internal.kh;
import com.google.android.gms.internal.kd;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.common.data.Freezable;

public abstract class Metadata implements Freezable<Metadata>
{
    public static final int CONTENT_AVAILABLE_LOCALLY = 1;
    public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;
    
    protected abstract <T> T a(final MetadataField<T> p0);
    
    public String getAlternateLink() {
        return this.a(kd.PF);
    }
    
    public int getContentAvailability() {
        final Integer n = this.a(kh.Qr);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    public Date getCreatedDate() {
        return this.a((MetadataField<Date>)kf.Ql);
    }
    
    public String getDescription() {
        return this.a(kd.PH);
    }
    
    public DriveId getDriveId() {
        return this.a(kd.PE);
    }
    
    public String getEmbedLink() {
        return this.a(kd.PI);
    }
    
    public String getFileExtension() {
        return this.a(kd.PJ);
    }
    
    public long getFileSize() {
        return this.a(kd.PK);
    }
    
    public Date getLastViewedByMeDate() {
        return this.a((MetadataField<Date>)kf.Qm);
    }
    
    public String getMimeType() {
        return this.a((MetadataField<String>)kd.PV);
    }
    
    public Date getModifiedByMeDate() {
        return this.a((MetadataField<Date>)kf.Qo);
    }
    
    public Date getModifiedDate() {
        return this.a((MetadataField<Date>)kf.Qn);
    }
    
    public String getOriginalFilename() {
        return this.a(kd.PW);
    }
    
    public long getQuotaBytesUsed() {
        return this.a((MetadataField<Long>)kd.Qb);
    }
    
    public Date getSharedWithMeDate() {
        return this.a((MetadataField<Date>)kf.Qp);
    }
    
    public String getTitle() {
        return this.a((MetadataField<String>)kd.Qe);
    }
    
    public String getWebContentLink() {
        return this.a(kd.Qg);
    }
    
    public String getWebViewLink() {
        return this.a(kd.Qh);
    }
    
    public boolean isEditable() {
        final Boolean b = this.a(kd.PP);
        return b != null && b;
    }
    
    public boolean isFolder() {
        return "application/vnd.google-apps.folder".equals(this.getMimeType());
    }
    
    public boolean isInAppFolder() {
        final Boolean b = this.a(kd.PN);
        return b != null && b;
    }
    
    public boolean isPinnable() {
        final Boolean b = this.a(kh.Qs);
        return b != null && b;
    }
    
    public boolean isPinned() {
        final Boolean b = this.a((MetadataField<Boolean>)kd.PQ);
        return b != null && b;
    }
    
    public boolean isRestricted() {
        final Boolean b = this.a(kd.PR);
        return b != null && b;
    }
    
    public boolean isShared() {
        final Boolean b = this.a(kd.PS);
        return b != null && b;
    }
    
    public boolean isStarred() {
        final Boolean b = this.a((MetadataField<Boolean>)kd.Qc);
        return b != null && b;
    }
    
    public boolean isTrashed() {
        final Boolean b = this.a((MetadataField<Boolean>)kd.Qf);
        return b != null && b;
    }
    
    public boolean isViewed() {
        final Boolean b = this.a(kd.PU);
        return b != null && b;
    }
}
