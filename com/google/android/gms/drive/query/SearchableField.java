// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.internal.gt;
import com.google.android.gms.internal.gs;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import java.util.Date;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;

public class SearchableField
{
    public static final SearchableOrderedMetadataField<Date> GE;
    public static final SearchableMetadataField<Boolean> IS_PINNED;
    public static final SearchableOrderedMetadataField<Date> LAST_VIEWED_BY_ME;
    public static final SearchableMetadataField<String> MIME_TYPE;
    public static final SearchableOrderedMetadataField<Date> MODIFIED_DATE;
    public static final SearchableCollectionMetadataField<DriveId> PARENTS;
    public static final SearchableMetadataField<Boolean> STARRED;
    public static final SearchableMetadataField<String> TITLE;
    public static final SearchableMetadataField<Boolean> TRASHED;
    
    static {
        TITLE = gs.Go;
        MIME_TYPE = gs.Gh;
        TRASHED = gs.Gp;
        PARENTS = gs.Gk;
        GE = gt.Gw;
        STARRED = gs.Gm;
        MODIFIED_DATE = gt.Gu;
        LAST_VIEWED_BY_ME = gt.Gt;
        IS_PINNED = gs.Gc;
    }
}
