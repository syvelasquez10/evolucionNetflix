// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.internal.kf;
import java.util.Date;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.kd;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties$a;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class MetadataChangeSet$Builder
{
    private final MetadataBundle Nm;
    private AppVisibleCustomProperties$a Nn;
    
    public MetadataChangeSet$Builder() {
        this.Nm = MetadataBundle.io();
    }
    
    public MetadataChangeSet build() {
        if (this.Nn != null) {
            this.Nm.b(kd.PG, this.Nn.im());
        }
        return new MetadataChangeSet(this.Nm);
    }
    
    public MetadataChangeSet$Builder setDescription(final String s) {
        this.Nm.b(kd.PH, s);
        return this;
    }
    
    public MetadataChangeSet$Builder setIndexableText(final String s) {
        this.Nm.b(kd.PM, s);
        return this;
    }
    
    public MetadataChangeSet$Builder setLastViewedByMeDate(final Date date) {
        this.Nm.b(kf.Qm, date);
        return this;
    }
    
    public MetadataChangeSet$Builder setMimeType(final String s) {
        this.Nm.b(kd.PV, s);
        return this;
    }
    
    public MetadataChangeSet$Builder setPinned(final boolean b) {
        this.Nm.b(kd.PQ, b);
        return this;
    }
    
    public MetadataChangeSet$Builder setStarred(final boolean b) {
        this.Nm.b(kd.Qc, b);
        return this;
    }
    
    public MetadataChangeSet$Builder setTitle(final String s) {
        this.Nm.b(kd.Qe, s);
        return this;
    }
    
    public MetadataChangeSet$Builder setViewed(final boolean b) {
        this.Nm.b(kd.PU, b);
        return this;
    }
}
