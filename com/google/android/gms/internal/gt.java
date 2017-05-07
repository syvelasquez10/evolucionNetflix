// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import java.util.Date;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.internal.b;

public class gt
{
    public static final a Gs;
    public static final b Gt;
    public static final d Gu;
    public static final c Gv;
    public static final e Gw;
    
    static {
        Gs = new a("created", 4100000);
        Gt = new b("lastOpenedTime", 4300000);
        Gu = new d("modified", 4100000);
        Gv = new c("modifiedByMe", 4100000);
        Gw = new e("sharedWithMe", 4100000);
    }
    
    public static class a extends b implements SortableMetadataField<Date>
    {
        public a(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class b extends com.google.android.gms.drive.metadata.internal.b implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
    {
        public b(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class c extends b implements SortableMetadataField<Date>
    {
        public c(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class d extends b implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
    {
        public d(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class e extends b implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
    {
        public e(final String s, final int n) {
            super(s, n);
        }
    }
}
