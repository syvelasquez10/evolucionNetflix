// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.internal.fi;
import com.google.android.gms.internal.fh;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.CollectionMetadataField;
import java.util.Date;
import com.google.android.gms.drive.metadata.OrderedMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;

public class SearchableField
{
    public static final MetadataField<String> MIME_TYPE;
    public static final OrderedMetadataField<Date> MODIFIED_DATE;
    public static final CollectionMetadataField<DriveId> PARENTS;
    public static final MetadataField<Boolean> STARRED;
    public static final MetadataField<String> TITLE;
    public static final MetadataField<Boolean> TRASHED;
    public static final OrderedMetadataField<Date> rM;
    
    static {
        TITLE = fh.TITLE;
        MIME_TYPE = fh.MIME_TYPE;
        TRASHED = fh.TRASHED;
        PARENTS = fh.PARENTS;
        rM = fi.rM;
        STARRED = fh.STARRED;
        MODIFIED_DATE = fi.rJ;
    }
}
