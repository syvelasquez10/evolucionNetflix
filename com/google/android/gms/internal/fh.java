// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.internal.e;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.a;
import com.google.android.gms.drive.metadata.StringMetadataField;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.CollectionMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;

public class fh
{
    public static final MetadataField<String> MIME_TYPE;
    public static final CollectionMetadataField<DriveId> PARENTS;
    public static final MetadataField<Boolean> STARRED;
    public static final MetadataField<String> TITLE;
    public static final MetadataField<Boolean> TRASHED;
    public static final MetadataField<DriveId> rG;
    public static final MetadataField<Boolean> rH;
    public static final MetadataField<Boolean> rI;
    
    static {
        rG = fj.rN;
        TITLE = new StringMetadataField("title");
        MIME_TYPE = new StringMetadataField("mimeType");
        STARRED = new a("starred");
        TRASHED = new a("trashed") {
            @Override
            protected Boolean e(final DataHolder dataHolder, final int n, final int n2) {
                return dataHolder.getInteger(this.getName(), n, n2) != 0;
            }
        };
        rH = new a("isEditable");
        rI = new a("isPinned");
        PARENTS = new e<DriveId>("parents");
    }
}
