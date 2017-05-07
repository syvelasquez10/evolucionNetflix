// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.kf;
import java.util.Date;
import com.google.android.gms.internal.kd;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class MetadataChangeSet
{
    public static final MetadataChangeSet Nl;
    private final MetadataBundle Nm;
    
    static {
        Nl = new MetadataChangeSet(MetadataBundle.io());
    }
    
    public MetadataChangeSet(final MetadataBundle metadataBundle) {
        this.Nm = MetadataBundle.a(metadataBundle);
    }
    
    public String getDescription() {
        return this.Nm.a(kd.PH);
    }
    
    public String getIndexableText() {
        return this.Nm.a(kd.PM);
    }
    
    public Date getLastViewedByMeDate() {
        return this.Nm.a((MetadataField<Date>)kf.Qm);
    }
    
    public String getMimeType() {
        return this.Nm.a((MetadataField<String>)kd.PV);
    }
    
    public String getTitle() {
        return this.Nm.a((MetadataField<String>)kd.Qe);
    }
    
    public MetadataBundle hS() {
        return this.Nm;
    }
    
    public Boolean isPinned() {
        return this.Nm.a((MetadataField<Boolean>)kd.PQ);
    }
    
    public Boolean isStarred() {
        return this.Nm.a((MetadataField<Boolean>)kd.Qc);
    }
    
    public Boolean isViewed() {
        return this.Nm.a(kd.PU);
    }
}
