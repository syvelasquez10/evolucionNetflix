// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import java.util.Date;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.internal.d;

public class kf
{
    public static final a Ql;
    public static final b Qm;
    public static final d Qn;
    public static final c Qo;
    public static final e Qp;
    
    static {
        Ql = new a("created", 4100000);
        Qm = new b("lastOpenedTime", 4300000);
        Qn = new d("modified", 4100000);
        Qo = new c("modifiedByMe", 4100000);
        Qp = new e("sharedWithMe", 4100000);
    }
    
    public static class a extends d implements SortableMetadataField<Date>
    {
        public a(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class b extends d implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
    {
        public b(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class c extends d implements SortableMetadataField<Date>
    {
        public c(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class d extends com.google.android.gms.drive.metadata.internal.d implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
    {
        public d(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class e extends d implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
    {
        public e(final String s, final int n) {
            super(s, n);
        }
    }
}
