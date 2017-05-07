// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive;

import com.google.android.gms.internal.fh;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class MetadataChangeSet
{
    private final MetadataBundle qV;
    
    private MetadataChangeSet(final MetadataBundle metadataBundle) {
        this.qV = MetadataBundle.a(metadataBundle);
    }
    
    public MetadataBundle cM() {
        return this.qV;
    }
    
    public String getMimeType() {
        return this.qV.a(fh.MIME_TYPE);
    }
    
    public String getTitle() {
        return this.qV.a(fh.TITLE);
    }
    
    public Boolean isStarred() {
        return this.qV.a(fh.STARRED);
    }
    
    public static class Builder
    {
        private final MetadataBundle qV;
        
        public Builder() {
            this.qV = MetadataBundle.cX();
        }
        
        public MetadataChangeSet build() {
            return new MetadataChangeSet(this.qV, null);
        }
        
        public Builder setMimeType(final String s) {
            this.qV.b(fh.MIME_TYPE, s);
            return this;
        }
        
        public Builder setStarred(final boolean b) {
            this.qV.b(fh.STARRED, b);
            return this;
        }
        
        public Builder setTitle(final String s) {
            this.qV.b(fh.TITLE, s);
            return this;
        }
    }
}
