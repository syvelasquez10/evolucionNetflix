// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.g;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.h;
import com.google.android.gms.drive.metadata.internal.i;
import com.google.android.gms.drive.metadata.internal.a;
import com.google.android.gms.drive.metadata.internal.e;
import com.google.android.gms.drive.metadata.internal.j;
import android.graphics.Bitmap;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.MetadataField;

public class gs
{
    public static final MetadataField<DriveId> FR;
    public static final MetadataField<String> FS;
    public static final MetadataField<String> FT;
    public static final MetadataField<String> FU;
    public static final MetadataField<String> FV;
    public static final MetadataField<Long> FW;
    public static final MetadataField<Boolean> FX;
    public static final MetadataField<String> FY;
    public static final MetadataField<Boolean> FZ;
    public static final MetadataField<Boolean> Ga;
    public static final MetadataField<Boolean> Gb;
    public static final a Gc;
    public static final MetadataField<Boolean> Gd;
    public static final MetadataField<Boolean> Ge;
    public static final MetadataField<Boolean> Gf;
    public static final MetadataField<Boolean> Gg;
    public static final b Gh;
    public static final MetadataField<String> Gi;
    public static final com.google.android.gms.drive.metadata.b<String> Gj;
    public static final c Gk;
    public static final d Gl;
    public static final e Gm;
    public static final MetadataField<Bitmap> Gn;
    public static final f Go;
    public static final g Gp;
    public static final MetadataField<String> Gq;
    public static final MetadataField<String> Gr;
    
    static {
        FR = gu.Gx;
        FS = new j("alternateLink", 4300000);
        FT = new j("description", 4300000);
        FU = new j("embedLink", 4300000);
        FV = new j("fileExtension", 4300000);
        FW = new com.google.android.gms.drive.metadata.internal.e("fileSize", 4300000);
        FX = new com.google.android.gms.drive.metadata.internal.a("hasThumbnail", 4300000);
        FY = new j("indexableText", 4300000);
        FZ = new com.google.android.gms.drive.metadata.internal.a("isAppData", 4300000);
        Ga = new com.google.android.gms.drive.metadata.internal.a("isCopyable", 4300000);
        Gb = new com.google.android.gms.drive.metadata.internal.a("isEditable", 4100000);
        Gc = new a("isPinned", 4100000);
        Gd = new com.google.android.gms.drive.metadata.internal.a("isRestricted", 4300000);
        Ge = new com.google.android.gms.drive.metadata.internal.a("isShared", 4300000);
        Gf = new com.google.android.gms.drive.metadata.internal.a("isTrashable", 4400000);
        Gg = new com.google.android.gms.drive.metadata.internal.a("isViewed", 4300000);
        Gh = new b("mimeType", 4100000);
        Gi = new j("originalFilename", 4300000);
        Gj = new i("ownerNames", 4300000);
        Gk = new c("parents", 4100000);
        Gl = new d("quotaBytesUsed", 4300000);
        Gm = new e("starred", 4100000);
        Gn = new h<Bitmap>("thumbnail", 4400000) {
            protected Bitmap i(final DataHolder dataHolder, final int n, final int n2) {
                throw new IllegalStateException("Thumbnail field is write only");
            }
        };
        Go = new f("title", 4100000);
        Gp = new g("trashed", 4100000);
        Gq = new j("webContentLink", 4300000);
        Gr = new j("webViewLink", 4300000);
    }
    
    public static class a extends com.google.android.gms.drive.metadata.internal.a implements SearchableMetadataField<Boolean>
    {
        public a(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class b extends j implements SearchableMetadataField<String>
    {
        public b(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class c extends g<DriveId> implements SearchableCollectionMetadataField<DriveId>
    {
        public c(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class d extends e implements SortableMetadataField<Long>
    {
        public d(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class e extends a implements SearchableMetadataField<Boolean>
    {
        public e(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class f extends j implements SearchableMetadataField<String>, SortableMetadataField<String>
    {
        public f(final String s, final int n) {
            super(s, n);
        }
    }
    
    public static class g extends a implements SearchableMetadataField<Boolean>
    {
        public g(final String s, final int n) {
            super(s, n);
        }
        
        @Override
        protected Boolean d(final DataHolder dataHolder, final int n, final int n2) {
            return dataHolder.getInteger(this.getName(), n, n2) != 0;
        }
    }
}
