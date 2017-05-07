// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.i;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import com.google.android.gms.drive.metadata.internal.j;
import java.util.Collections;
import com.google.android.gms.drive.metadata.internal.k;
import com.google.android.gms.drive.metadata.internal.g;
import com.google.android.gms.drive.metadata.internal.l;
import com.google.android.gms.common.data.a;
import com.google.android.gms.drive.metadata.internal.m;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.MetadataField;

public class kd
{
    public static final MetadataField<DriveId> PE;
    public static final MetadataField<String> PF;
    public static final a PG;
    public static final MetadataField<String> PH;
    public static final MetadataField<String> PI;
    public static final MetadataField<String> PJ;
    public static final MetadataField<Long> PK;
    public static final MetadataField<Boolean> PL;
    public static final MetadataField<String> PM;
    public static final MetadataField<Boolean> PN;
    public static final MetadataField<Boolean> PO;
    public static final MetadataField<Boolean> PP;
    public static final b PQ;
    public static final MetadataField<Boolean> PR;
    public static final MetadataField<Boolean> PS;
    public static final MetadataField<Boolean> PT;
    public static final MetadataField<Boolean> PU;
    public static final c PV;
    public static final MetadataField<String> PW;
    public static final com.google.android.gms.drive.metadata.b<String> PX;
    public static final m PY;
    public static final m PZ;
    public static final d Qa;
    public static final e Qb;
    public static final f Qc;
    public static final MetadataField<com.google.android.gms.common.data.a> Qd;
    public static final g Qe;
    public static final h Qf;
    public static final MetadataField<String> Qg;
    public static final MetadataField<String> Qh;
    public static final MetadataField<String> Qi;
    public static final com.google.android.gms.drive.metadata.internal.b Qj;
    public static final MetadataField<String> Qk;
    
    static {
        PE = kg.Qq;
        PF = new l("alternateLink", 4300000);
        PG = new a(5000000);
        PH = new l("description", 4300000);
        PI = new l("embedLink", 4300000);
        PJ = new l("fileExtension", 4300000);
        PK = new com.google.android.gms.drive.metadata.internal.g("fileSize", 4300000);
        PL = new com.google.android.gms.drive.metadata.internal.b("hasThumbnail", 4300000);
        PM = new l("indexableText", 4300000);
        PN = new com.google.android.gms.drive.metadata.internal.b("isAppData", 4300000);
        PO = new com.google.android.gms.drive.metadata.internal.b("isCopyable", 4300000);
        PP = new com.google.android.gms.drive.metadata.internal.b("isEditable", 4100000);
        PQ = new b("isPinned", 4100000);
        PR = new com.google.android.gms.drive.metadata.internal.b("isRestricted", 4300000);
        PS = new com.google.android.gms.drive.metadata.internal.b("isShared", 4300000);
        PT = new com.google.android.gms.drive.metadata.internal.b("isTrashable", 4400000);
        PU = new com.google.android.gms.drive.metadata.internal.b("isViewed", 4300000);
        PV = new c("mimeType", 4100000);
        PW = new l("originalFilename", 4300000);
        PX = new k("ownerNames", 4300000);
        PY = new m("lastModifyingUser", 6000000);
        PZ = new m("sharingUser", 6000000);
        Qa = new d("parents", 4100000);
        Qb = new e("quotaBytesUsed", 4300000);
        Qc = new f("starred", 4100000);
        Qd = new j<com.google.android.gms.common.data.a>("thumbnail", Collections.emptySet(), Collections.emptySet(), 4400000) {
            protected a k(final DataHolder dataHolder, final int n, final int n2) {
                throw new IllegalStateException("Thumbnail field is write only");
            }
        };
        Qe = new g("title", 4100000);
        Qf = new h("trashed", 4100000);
        Qg = new l("webContentLink", 4300000);
        Qh = new l("webViewLink", 4300000);
        Qi = new l("uniqueIdentifier", 5000000);
        Qj = new com.google.android.gms.drive.metadata.internal.b("writersCanShare", 6000000);
        Qk = new l("role", 6000000);
    }
    
    public static class a extends ke implements SearchableMetadataField<AppVisibleCustomProperties>
    {
        public a(final int n) {
            super(n);
        }
    }
    
    public static class b extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean>
    {
        public b(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class c extends l implements SearchableMetadataField<String>
    {
        public c(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class d extends i<DriveId> implements SearchableCollectionMetadataField<DriveId>
    {
        public d(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class e extends g implements SortableMetadataField<Long>
    {
        public e(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class f extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean>
    {
        public f(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class g extends l implements SearchableMetadataField<String>, SortableMetadataField<String>
    {
        public g(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class h extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean>
    {
        public h(final String s, final int n) {
            super(s, n);
        }
        
        @Override
        protected Boolean e(final DataHolder dataHolder, final int n, final int n2) {
            return dataHolder.b(this.getName(), n, n2) != 0;
        }
    }
}
