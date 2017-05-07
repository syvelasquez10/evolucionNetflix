// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.internal.kf;
import com.google.android.gms.internal.kd;
import java.util.Date;
import com.google.android.gms.drive.metadata.SortableMetadataField;

public class SortableField
{
    public static final SortableMetadataField<Date> CREATED_DATE;
    public static final SortableMetadataField<Date> LAST_VIEWED_BY_ME;
    public static final SortableMetadataField<Date> MODIFIED_BY_ME_DATE;
    public static final SortableMetadataField<Date> MODIFIED_DATE;
    public static final SortableMetadataField<Long> QUOTA_USED;
    public static final SortableMetadataField<Date> SHARED_WITH_ME_DATE;
    public static final SortableMetadataField<String> TITLE;
    
    static {
        TITLE = kd.Qe;
        CREATED_DATE = kf.Ql;
        MODIFIED_DATE = kf.Qn;
        MODIFIED_BY_ME_DATE = kf.Qo;
        LAST_VIEWED_BY_ME = kf.Qm;
        SHARED_WITH_ME_DATE = kf.Qp;
        QUOTA_USED = kd.Qb;
    }
}
