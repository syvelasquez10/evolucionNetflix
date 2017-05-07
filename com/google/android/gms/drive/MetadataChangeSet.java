// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
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
    
    public static class Builder
    {
        private final MetadataBundle Nm;
        private AppVisibleCustomProperties.a Nn;
        
        public Builder() {
            this.Nm = MetadataBundle.io();
        }
        
        public MetadataChangeSet build() {
            if (this.Nn != null) {
                this.Nm.b(kd.PG, this.Nn.im());
            }
            return new MetadataChangeSet(this.Nm);
        }
        
        public Builder setDescription(final String s) {
            this.Nm.b(kd.PH, s);
            return this;
        }
        
        public Builder setIndexableText(final String s) {
            this.Nm.b(kd.PM, s);
            return this;
        }
        
        public Builder setLastViewedByMeDate(final Date date) {
            this.Nm.b(kf.Qm, date);
            return this;
        }
        
        public Builder setMimeType(final String s) {
            this.Nm.b(kd.PV, s);
            return this;
        }
        
        public Builder setPinned(final boolean b) {
            this.Nm.b(kd.PQ, b);
            return this;
        }
        
        public Builder setStarred(final boolean b) {
            this.Nm.b(kd.Qc, b);
            return this;
        }
        
        public Builder setTitle(final String s) {
            this.Nm.b(kd.Qe, s);
            return this;
        }
        
        public Builder setViewed(final boolean b) {
            this.Nm.b(kd.PU, b);
            return this;
        }
    }
}
