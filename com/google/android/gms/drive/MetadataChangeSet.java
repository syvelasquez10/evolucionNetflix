// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.gt;
import java.util.Date;
import com.google.android.gms.internal.gs;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class MetadataChangeSet
{
    private final MetadataBundle EP;
    
    private MetadataChangeSet(final MetadataBundle metadataBundle) {
        this.EP = MetadataBundle.a(metadataBundle);
    }
    
    public MetadataBundle fD() {
        return this.EP;
    }
    
    public String getDescription() {
        return this.EP.a(gs.FT);
    }
    
    public String getIndexableText() {
        return this.EP.a(gs.FY);
    }
    
    public Date getLastViewedByMeDate() {
        return this.EP.a((MetadataField<Date>)gt.Gt);
    }
    
    public String getMimeType() {
        return this.EP.a((MetadataField<String>)gs.Gh);
    }
    
    public String getTitle() {
        return this.EP.a((MetadataField<String>)gs.Go);
    }
    
    public Boolean isPinned() {
        return this.EP.a((MetadataField<Boolean>)gs.Gc);
    }
    
    public Boolean isStarred() {
        return this.EP.a((MetadataField<Boolean>)gs.Gm);
    }
    
    public Boolean isViewed() {
        return this.EP.a(gs.Gg);
    }
    
    public static class Builder
    {
        private final MetadataBundle EP;
        
        public Builder() {
            this.EP = MetadataBundle.fT();
        }
        
        public MetadataChangeSet build() {
            return new MetadataChangeSet(this.EP, null);
        }
        
        public Builder setDescription(final String s) {
            this.EP.b(gs.FT, s);
            return this;
        }
        
        public Builder setIndexableText(final String s) {
            this.EP.b(gs.FY, s);
            return this;
        }
        
        public Builder setLastViewedByMeDate(final Date date) {
            this.EP.b(gt.Gt, date);
            return this;
        }
        
        public Builder setMimeType(final String s) {
            this.EP.b(gs.Gh, s);
            return this;
        }
        
        public Builder setPinned(final boolean b) {
            this.EP.b(gs.Gc, b);
            return this;
        }
        
        public Builder setStarred(final boolean b) {
            this.EP.b(gs.Gm, b);
            return this;
        }
        
        public Builder setTitle(final String s) {
            this.EP.b(gs.Go, s);
            return this;
        }
        
        public Builder setViewed(final boolean b) {
            this.EP.b(gs.Gg, b);
            return this;
        }
    }
}
