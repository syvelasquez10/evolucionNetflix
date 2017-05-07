// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.internal.gt;
import java.util.Date;
import com.google.android.gms.internal.gv;
import com.google.android.gms.internal.gs;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.common.data.Freezable;

public abstract class Metadata implements Freezable<Metadata>
{
    public static final int CONTENT_AVAILABLE_LOCALLY = 1;
    public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;
    
    protected abstract <T> T a(final MetadataField<T> p0);
    
    public String getAlternateLink() {
        return this.a(gs.FS);
    }
    
    public int getContentAvailability() {
        final Integer n = this.a(gv.Gy);
        if (n == null) {
            return 0;
        }
        return n;
    }
    
    public Date getCreatedDate() {
        return this.a((MetadataField<Date>)gt.Gs);
    }
    
    public String getDescription() {
        return this.a(gs.FT);
    }
    
    public DriveId getDriveId() {
        return this.a(gs.FR);
    }
    
    public String getEmbedLink() {
        return this.a(gs.FU);
    }
    
    public String getFileExtension() {
        return this.a(gs.FV);
    }
    
    public long getFileSize() {
        return this.a(gs.FW);
    }
    
    public Date getLastViewedByMeDate() {
        return this.a((MetadataField<Date>)gt.Gt);
    }
    
    public String getMimeType() {
        return this.a((MetadataField<String>)gs.Gh);
    }
    
    public Date getModifiedByMeDate() {
        return this.a((MetadataField<Date>)gt.Gv);
    }
    
    public Date getModifiedDate() {
        return this.a((MetadataField<Date>)gt.Gu);
    }
    
    public String getOriginalFilename() {
        return this.a(gs.Gi);
    }
    
    public long getQuotaBytesUsed() {
        return this.a((MetadataField<Long>)gs.Gl);
    }
    
    public Date getSharedWithMeDate() {
        return this.a((MetadataField<Date>)gt.Gw);
    }
    
    public String getTitle() {
        return this.a((MetadataField<String>)gs.Go);
    }
    
    public String getWebContentLink() {
        return this.a(gs.Gq);
    }
    
    public String getWebViewLink() {
        return this.a(gs.Gr);
    }
    
    public boolean isEditable() {
        final Boolean b = this.a(gs.Gb);
        return b != null && b;
    }
    
    public boolean isFolder() {
        return "application/vnd.google-apps.folder".equals(this.getMimeType());
    }
    
    public boolean isInAppFolder() {
        final Boolean b = this.a(gs.FZ);
        return b != null && b;
    }
    
    public boolean isPinnable() {
        final Boolean b = this.a(gv.Gz);
        return b != null && b;
    }
    
    public boolean isPinned() {
        final Boolean b = this.a((MetadataField<Boolean>)gs.Gc);
        return b != null && b;
    }
    
    public boolean isRestricted() {
        final Boolean b = this.a(gs.Gd);
        return b != null && b;
    }
    
    public boolean isShared() {
        final Boolean b = this.a(gs.Ge);
        return b != null && b;
    }
    
    public boolean isStarred() {
        final Boolean b = this.a((MetadataField<Boolean>)gs.Gm);
        return b != null && b;
    }
    
    public boolean isTrashed() {
        final Boolean b = this.a((MetadataField<Boolean>)gs.Gp);
        return b != null && b;
    }
    
    public boolean isViewed() {
        final Boolean b = this.a(gs.Gg);
        return b != null && b;
    }
}
