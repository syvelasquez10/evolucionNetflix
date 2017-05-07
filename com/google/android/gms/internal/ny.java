// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.people.Person.Cover;
import java.util.ArrayList;
import java.util.Iterator;
import android.os.Parcel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.people.Person;

public final class ny extends jj implements Person
{
    public static final nz CREATOR;
    private static final HashMap<String, ji.a<?, ?>> alQ;
    String BL;
    final int BR;
    String Fc;
    String Nz;
    final Set<Integer> alR;
    String amP;
    a amQ;
    String amR;
    String amS;
    int amT;
    b amU;
    String amV;
    c amW;
    boolean amX;
    d amY;
    String amZ;
    int ana;
    List<f> anb;
    List<g> anc;
    int and;
    int ane;
    String anf;
    List<h> ang;
    boolean anh;
    int om;
    String uR;
    
    static {
        CREATOR = new nz();
        (alQ = new HashMap<String, ji.a<?, ?>>()).put("aboutMe", ji.a.l("aboutMe", 2));
        ny.alQ.put("ageRange", ji.a.a("ageRange", 3, (Class<?>)a.class));
        ny.alQ.put("birthday", (ji.a<?, ?>)ji.a.l("birthday", 4));
        ny.alQ.put("braggingRights", (ji.a<?, ?>)ji.a.l("braggingRights", 5));
        ny.alQ.put("circledByCount", (ji.a<?, ?>)ji.a.i("circledByCount", 6));
        ny.alQ.put("cover", ji.a.a("cover", 7, (Class<?>)b.class));
        ny.alQ.put("currentLocation", (ji.a<?, ?>)ji.a.l("currentLocation", 8));
        ny.alQ.put("displayName", (ji.a<?, ?>)ji.a.l("displayName", 9));
        ny.alQ.put("gender", (ji.a<?, ?>)ji.a.a("gender", 12, new jf().h("male", 0).h("female", 1).h("other", 2), false));
        ny.alQ.put("id", (ji.a<?, ?>)ji.a.l("id", 14));
        ny.alQ.put("image", ji.a.a("image", 15, (Class<?>)c.class));
        ny.alQ.put("isPlusUser", (ji.a<?, ?>)ji.a.k("isPlusUser", 16));
        ny.alQ.put("language", (ji.a<?, ?>)ji.a.l("language", 18));
        ny.alQ.put("name", ji.a.a("name", 19, (Class<?>)d.class));
        ny.alQ.put("nickname", (ji.a<?, ?>)ji.a.l("nickname", 20));
        ny.alQ.put("objectType", (ji.a<?, ?>)ji.a.a("objectType", 21, new jf().h("person", 0).h("page", 1), false));
        ny.alQ.put("organizations", (ji.a<?, ?>)ji.a.b("organizations", 22, f.class));
        ny.alQ.put("placesLived", (ji.a<?, ?>)ji.a.b("placesLived", 23, g.class));
        ny.alQ.put("plusOneCount", (ji.a<?, ?>)ji.a.i("plusOneCount", 24));
        ny.alQ.put("relationshipStatus", (ji.a<?, ?>)ji.a.a("relationshipStatus", 25, new jf().h("single", 0).h("in_a_relationship", 1).h("engaged", 2).h("married", 3).h("its_complicated", 4).h("open_relationship", 5).h("widowed", 6).h("in_domestic_partnership", 7).h("in_civil_union", 8), false));
        ny.alQ.put("tagline", (ji.a<?, ?>)ji.a.l("tagline", 26));
        ny.alQ.put("url", (ji.a<?, ?>)ji.a.l("url", 27));
        ny.alQ.put("urls", (ji.a<?, ?>)ji.a.b("urls", 28, h.class));
        ny.alQ.put("verified", (ji.a<?, ?>)ji.a.k("verified", 29));
    }
    
    public ny() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    public ny(final String nz, final String bl, final c amW, final int ana, final String ur) {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
        this.Nz = nz;
        this.alR.add(9);
        this.BL = bl;
        this.alR.add(14);
        this.amW = amW;
        this.alR.add(15);
        this.ana = ana;
        this.alR.add(21);
        this.uR = ur;
        this.alR.add(27);
    }
    
    ny(final Set<Integer> alR, final int br, final String amP, final a amQ, final String amR, final String amS, final int amT, final b amU, final String amV, final String nz, final int om, final String bl, final c amW, final boolean amX, final String fc, final d amY, final String amZ, final int ana, final List<f> anb, final List<g> anc, final int and, final int ane, final String anf, final String ur, final List<h> ang, final boolean anh) {
        this.alR = alR;
        this.BR = br;
        this.amP = amP;
        this.amQ = amQ;
        this.amR = amR;
        this.amS = amS;
        this.amT = amT;
        this.amU = amU;
        this.amV = amV;
        this.Nz = nz;
        this.om = om;
        this.BL = bl;
        this.amW = amW;
        this.amX = amX;
        this.Fc = fc;
        this.amY = amY;
        this.amZ = amZ;
        this.ana = ana;
        this.anb = anb;
        this.anc = anc;
        this.and = and;
        this.ane = ane;
        this.anf = anf;
        this.uR = ur;
        this.ang = ang;
        this.anh = anh;
    }
    
    public static ny i(final byte[] array) {
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final ny dd = ny.CREATOR.dd(obtain);
        obtain.recycle();
        return dd;
    }
    
    @Override
    protected boolean a(final ji.a a) {
        return this.alR.contains(a.hm());
    }
    
    @Override
    protected Object b(final ji.a a) {
        switch (a.hm()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
            }
            case 2: {
                return this.amP;
            }
            case 3: {
                return this.amQ;
            }
            case 4: {
                return this.amR;
            }
            case 5: {
                return this.amS;
            }
            case 6: {
                return this.amT;
            }
            case 7: {
                return this.amU;
            }
            case 8: {
                return this.amV;
            }
            case 9: {
                return this.Nz;
            }
            case 12: {
                return this.om;
            }
            case 14: {
                return this.BL;
            }
            case 15: {
                return this.amW;
            }
            case 16: {
                return this.amX;
            }
            case 18: {
                return this.Fc;
            }
            case 19: {
                return this.amY;
            }
            case 20: {
                return this.amZ;
            }
            case 21: {
                return this.ana;
            }
            case 22: {
                return this.anb;
            }
            case 23: {
                return this.anc;
            }
            case 24: {
                return this.and;
            }
            case 25: {
                return this.ane;
            }
            case 26: {
                return this.anf;
            }
            case 27: {
                return this.uR;
            }
            case 28: {
                return this.ang;
            }
            case 29: {
                return this.anh;
            }
        }
    }
    
    public int describeContents() {
        final nz creator = ny.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ny)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ny ny = (ny)o;
        for (final ji.a a : com.google.android.gms.internal.ny.alQ.values()) {
            if (this.a((ji.a)a)) {
                if (!ny.a((ji.a)a)) {
                    return false;
                }
                if (!this.b((ji.a)a).equals(ny.b((ji.a)a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny.a((ji.a)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getAboutMe() {
        return this.amP;
    }
    
    @Override
    public AgeRange getAgeRange() {
        return this.amQ;
    }
    
    @Override
    public String getBirthday() {
        return this.amR;
    }
    
    @Override
    public String getBraggingRights() {
        return this.amS;
    }
    
    @Override
    public int getCircledByCount() {
        return this.amT;
    }
    
    @Override
    public Cover getCover() {
        return this.amU;
    }
    
    @Override
    public String getCurrentLocation() {
        return this.amV;
    }
    
    @Override
    public String getDisplayName() {
        return this.Nz;
    }
    
    @Override
    public int getGender() {
        return this.om;
    }
    
    @Override
    public String getId() {
        return this.BL;
    }
    
    @Override
    public Image getImage() {
        return this.amW;
    }
    
    @Override
    public String getLanguage() {
        return this.Fc;
    }
    
    @Override
    public Name getName() {
        return this.amY;
    }
    
    @Override
    public String getNickname() {
        return this.amZ;
    }
    
    @Override
    public int getObjectType() {
        return this.ana;
    }
    
    @Override
    public List<Organizations> getOrganizations() {
        return (List<Organizations>)(ArrayList)this.anb;
    }
    
    @Override
    public List<PlacesLived> getPlacesLived() {
        return (List<PlacesLived>)(ArrayList)this.anc;
    }
    
    @Override
    public int getPlusOneCount() {
        return this.and;
    }
    
    @Override
    public int getRelationshipStatus() {
        return this.ane;
    }
    
    @Override
    public String getTagline() {
        return this.anf;
    }
    
    @Override
    public String getUrl() {
        return this.uR;
    }
    
    @Override
    public List<Urls> getUrls() {
        return (List<Urls>)(ArrayList)this.ang;
    }
    
    @Override
    public boolean hasAboutMe() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasAgeRange() {
        return this.alR.contains(3);
    }
    
    @Override
    public boolean hasBirthday() {
        return this.alR.contains(4);
    }
    
    @Override
    public boolean hasBraggingRights() {
        return this.alR.contains(5);
    }
    
    @Override
    public boolean hasCircledByCount() {
        return this.alR.contains(6);
    }
    
    @Override
    public boolean hasCover() {
        return this.alR.contains(7);
    }
    
    @Override
    public boolean hasCurrentLocation() {
        return this.alR.contains(8);
    }
    
    @Override
    public boolean hasDisplayName() {
        return this.alR.contains(9);
    }
    
    @Override
    public boolean hasGender() {
        return this.alR.contains(12);
    }
    
    @Override
    public boolean hasId() {
        return this.alR.contains(14);
    }
    
    @Override
    public boolean hasImage() {
        return this.alR.contains(15);
    }
    
    @Override
    public boolean hasIsPlusUser() {
        return this.alR.contains(16);
    }
    
    @Override
    public boolean hasLanguage() {
        return this.alR.contains(18);
    }
    
    @Override
    public boolean hasName() {
        return this.alR.contains(19);
    }
    
    @Override
    public boolean hasNickname() {
        return this.alR.contains(20);
    }
    
    @Override
    public boolean hasObjectType() {
        return this.alR.contains(21);
    }
    
    @Override
    public boolean hasOrganizations() {
        return this.alR.contains(22);
    }
    
    @Override
    public boolean hasPlacesLived() {
        return this.alR.contains(23);
    }
    
    @Override
    public boolean hasPlusOneCount() {
        return this.alR.contains(24);
    }
    
    @Override
    public boolean hasRelationshipStatus() {
        return this.alR.contains(25);
    }
    
    @Override
    public boolean hasTagline() {
        return this.alR.contains(26);
    }
    
    @Override
    public boolean hasUrl() {
        return this.alR.contains(27);
    }
    
    @Override
    public boolean hasUrls() {
        return this.alR.contains(28);
    }
    
    @Override
    public boolean hasVerified() {
        return this.alR.contains(29);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ji.a<?, ?>> iterator = ny.alQ.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final ji.a a = (ji.a)iterator.next();
            if (this.a((ji.a)a)) {
                n = this.b((ji.a)a).hashCode() + (n + a.hm());
            }
        }
        return n;
    }
    
    @Override
    public HashMap<String, ji.a<?, ?>> hf() {
        return ny.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean isPlusUser() {
        return this.amX;
    }
    
    @Override
    public boolean isVerified() {
        return this.anh;
    }
    
    public ny ns() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final nz creator = ny.CREATOR;
        nz.a(this, parcel, n);
    }
    
    public static final class a extends jj implements AgeRange
    {
        public static final oa CREATOR;
        private static final HashMap<String, ji.a<?, ?>> alQ;
        final int BR;
        final Set<Integer> alR;
        int ani;
        int anj;
        
        static {
            CREATOR = new oa();
            (alQ = new HashMap<String, ji.a<?, ?>>()).put("max", ji.a.i("max", 2));
            a.alQ.put("min", (ji.a<?, ?>)ji.a.i("min", 3));
        }
        
        public a() {
            this.BR = 1;
            this.alR = new HashSet<Integer>();
        }
        
        a(final Set<Integer> alR, final int br, final int ani, final int anj) {
            this.alR = alR;
            this.BR = br;
            this.ani = ani;
            this.anj = anj;
        }
        
        @Override
        protected boolean a(final ji.a a) {
            return this.alR.contains(a.hm());
        }
        
        @Override
        protected Object b(final ji.a a) {
            switch (a.hm()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                }
                case 2: {
                    return this.ani;
                }
                case 3: {
                    return this.anj;
                }
            }
        }
        
        public int describeContents() {
            final oa creator = a.CREATOR;
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof a)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final a a = (a)o;
            for (final ji.a a2 : ny.a.alQ.values()) {
                if (this.a((ji.a)a2)) {
                    if (!a.a((ji.a)a2)) {
                        return false;
                    }
                    if (!this.b((ji.a)a2).equals(a.b((ji.a)a2))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (a.a((ji.a)a2)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public int getMax() {
            return this.ani;
        }
        
        @Override
        public int getMin() {
            return this.anj;
        }
        
        @Override
        public boolean hasMax() {
            return this.alR.contains(2);
        }
        
        @Override
        public boolean hasMin() {
            return this.alR.contains(3);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ji.a<?, ?>> iterator = a.alQ.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ji.a a = (ji.a)iterator.next();
                if (this.a((ji.a)a)) {
                    n = this.b((ji.a)a).hashCode() + (n + a.hm());
                }
            }
            return n;
        }
        
        @Override
        public HashMap<String, ji.a<?, ?>> hf() {
            return a.alQ;
        }
        
        @Override
        public boolean isDataValid() {
            return true;
        }
        
        public a nt() {
            return this;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final oa creator = a.CREATOR;
            oa.a(this, parcel, n);
        }
    }
    
    public static final class b extends jj implements Cover
    {
        public static final ob CREATOR;
        private static final HashMap<String, ji.a<?, ?>> alQ;
        final int BR;
        final Set<Integer> alR;
        a ank;
        ny.b.b anl;
        int anm;
        
        static {
            CREATOR = new ob();
            (alQ = new HashMap<String, ji.a<?, ?>>()).put("coverInfo", ji.a.a("coverInfo", 2, (Class<?>)a.class));
            ny.b.alQ.put("coverPhoto", ji.a.a("coverPhoto", 3, (Class<?>)ny.b.b.class));
            ny.b.alQ.put("layout", (ji.a<?, ?>)ji.a.a("layout", 4, new jf().h("banner", 0), false));
        }
        
        public b() {
            this.BR = 1;
            this.alR = new HashSet<Integer>();
        }
        
        b(final Set<Integer> alR, final int br, final a ank, final ny.b.b anl, final int anm) {
            this.alR = alR;
            this.BR = br;
            this.ank = ank;
            this.anl = anl;
            this.anm = anm;
        }
        
        @Override
        protected boolean a(final ji.a a) {
            return this.alR.contains(a.hm());
        }
        
        @Override
        protected Object b(final ji.a a) {
            switch (a.hm()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                }
                case 2: {
                    return this.ank;
                }
                case 3: {
                    return this.anl;
                }
                case 4: {
                    return this.anm;
                }
            }
        }
        
        public int describeContents() {
            final ob creator = ny.b.CREATOR;
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof ny.b)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final ny.b b = (ny.b)o;
            for (final ji.a a : ny.b.alQ.values()) {
                if (this.a((ji.a)a)) {
                    if (!b.a((ji.a)a)) {
                        return false;
                    }
                    if (!this.b((ji.a)a).equals(b.b((ji.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (b.a((ji.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public CoverInfo getCoverInfo() {
            return this.ank;
        }
        
        @Override
        public CoverPhoto getCoverPhoto() {
            return this.anl;
        }
        
        @Override
        public int getLayout() {
            return this.anm;
        }
        
        @Override
        public boolean hasCoverInfo() {
            return this.alR.contains(2);
        }
        
        @Override
        public boolean hasCoverPhoto() {
            return this.alR.contains(3);
        }
        
        @Override
        public boolean hasLayout() {
            return this.alR.contains(4);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ji.a<?, ?>> iterator = ny.b.alQ.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ji.a a = (ji.a)iterator.next();
                if (this.a((ji.a)a)) {
                    n = this.b((ji.a)a).hashCode() + (n + a.hm());
                }
            }
            return n;
        }
        
        @Override
        public HashMap<String, ji.a<?, ?>> hf() {
            return ny.b.alQ;
        }
        
        @Override
        public boolean isDataValid() {
            return true;
        }
        
        public ny.b nu() {
            return this;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ob creator = ny.b.CREATOR;
            ob.a(this, parcel, n);
        }
        
        public static final class a extends jj implements CoverInfo
        {
            public static final oc CREATOR;
            private static final HashMap<String, ji.a<?, ?>> alQ;
            final int BR;
            final Set<Integer> alR;
            int ann;
            int ano;
            
            static {
                CREATOR = new oc();
                (alQ = new HashMap<String, ji.a<?, ?>>()).put("leftImageOffset", ji.a.i("leftImageOffset", 2));
                a.alQ.put("topImageOffset", (ji.a<?, ?>)ji.a.i("topImageOffset", 3));
            }
            
            public a() {
                this.BR = 1;
                this.alR = new HashSet<Integer>();
            }
            
            a(final Set<Integer> alR, final int br, final int ann, final int ano) {
                this.alR = alR;
                this.BR = br;
                this.ann = ann;
                this.ano = ano;
            }
            
            @Override
            protected boolean a(final ji.a a) {
                return this.alR.contains(a.hm());
            }
            
            @Override
            protected Object b(final ji.a a) {
                switch (a.hm()) {
                    default: {
                        throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                    }
                    case 2: {
                        return this.ann;
                    }
                    case 3: {
                        return this.ano;
                    }
                }
            }
            
            public int describeContents() {
                final oc creator = a.CREATOR;
                return 0;
            }
            
            @Override
            public boolean equals(final Object o) {
                if (!(o instanceof a)) {
                    return false;
                }
                if (this == o) {
                    return true;
                }
                final a a = (a)o;
                for (final ji.a a2 : ny.b.a.alQ.values()) {
                    if (this.a((ji.a)a2)) {
                        if (!a.a((ji.a)a2)) {
                            return false;
                        }
                        if (!this.b((ji.a)a2).equals(a.b((ji.a)a2))) {
                            return false;
                        }
                        continue;
                    }
                    else {
                        if (a.a((ji.a)a2)) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
            
            @Override
            public int getLeftImageOffset() {
                return this.ann;
            }
            
            @Override
            public int getTopImageOffset() {
                return this.ano;
            }
            
            @Override
            public boolean hasLeftImageOffset() {
                return this.alR.contains(2);
            }
            
            @Override
            public boolean hasTopImageOffset() {
                return this.alR.contains(3);
            }
            
            @Override
            public int hashCode() {
                final Iterator<ji.a<?, ?>> iterator = a.alQ.values().iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    final ji.a a = (ji.a)iterator.next();
                    if (this.a((ji.a)a)) {
                        n = this.b((ji.a)a).hashCode() + (n + a.hm());
                    }
                }
                return n;
            }
            
            @Override
            public HashMap<String, ji.a<?, ?>> hf() {
                return a.alQ;
            }
            
            @Override
            public boolean isDataValid() {
                return true;
            }
            
            public a nv() {
                return this;
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                final oc creator = a.CREATOR;
                oc.a(this, parcel, n);
            }
        }
        
        public static final class b extends jj implements CoverPhoto
        {
            public static final od CREATOR;
            private static final HashMap<String, ji.a<?, ?>> alQ;
            final int BR;
            final Set<Integer> alR;
            int lf;
            int lg;
            String uR;
            
            static {
                CREATOR = new od();
                (alQ = new HashMap<String, ji.a<?, ?>>()).put("height", ji.a.i("height", 2));
                b.alQ.put("url", (ji.a<?, ?>)ji.a.l("url", 3));
                b.alQ.put("width", (ji.a<?, ?>)ji.a.i("width", 4));
            }
            
            public b() {
                this.BR = 1;
                this.alR = new HashSet<Integer>();
            }
            
            b(final Set<Integer> alR, final int br, final int lg, final String ur, final int lf) {
                this.alR = alR;
                this.BR = br;
                this.lg = lg;
                this.uR = ur;
                this.lf = lf;
            }
            
            @Override
            protected boolean a(final ji.a a) {
                return this.alR.contains(a.hm());
            }
            
            @Override
            protected Object b(final ji.a a) {
                switch (a.hm()) {
                    default: {
                        throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                    }
                    case 2: {
                        return this.lg;
                    }
                    case 3: {
                        return this.uR;
                    }
                    case 4: {
                        return this.lf;
                    }
                }
            }
            
            public int describeContents() {
                final od creator = b.CREATOR;
                return 0;
            }
            
            @Override
            public boolean equals(final Object o) {
                if (!(o instanceof b)) {
                    return false;
                }
                if (this == o) {
                    return true;
                }
                final b b = (b)o;
                for (final ji.a a : ny.b.b.alQ.values()) {
                    if (this.a((ji.a)a)) {
                        if (!b.a((ji.a)a)) {
                            return false;
                        }
                        if (!this.b((ji.a)a).equals(b.b((ji.a)a))) {
                            return false;
                        }
                        continue;
                    }
                    else {
                        if (b.a((ji.a)a)) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
            
            @Override
            public int getHeight() {
                return this.lg;
            }
            
            @Override
            public String getUrl() {
                return this.uR;
            }
            
            @Override
            public int getWidth() {
                return this.lf;
            }
            
            @Override
            public boolean hasHeight() {
                return this.alR.contains(2);
            }
            
            @Override
            public boolean hasUrl() {
                return this.alR.contains(3);
            }
            
            @Override
            public boolean hasWidth() {
                return this.alR.contains(4);
            }
            
            @Override
            public int hashCode() {
                final Iterator<ji.a<?, ?>> iterator = b.alQ.values().iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    final ji.a a = (ji.a)iterator.next();
                    if (this.a((ji.a)a)) {
                        n = this.b((ji.a)a).hashCode() + (n + a.hm());
                    }
                }
                return n;
            }
            
            @Override
            public HashMap<String, ji.a<?, ?>> hf() {
                return b.alQ;
            }
            
            @Override
            public boolean isDataValid() {
                return true;
            }
            
            public b nw() {
                return this;
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                final od creator = b.CREATOR;
                od.a(this, parcel, n);
            }
        }
    }
    
    public static final class c extends jj implements Image
    {
        public static final oe CREATOR;
        private static final HashMap<String, ji.a<?, ?>> alQ;
        final int BR;
        final Set<Integer> alR;
        String uR;
        
        static {
            CREATOR = new oe();
            (alQ = new HashMap<String, ji.a<?, ?>>()).put("url", ji.a.l("url", 2));
        }
        
        public c() {
            this.BR = 1;
            this.alR = new HashSet<Integer>();
        }
        
        public c(final String ur) {
            this.alR = new HashSet<Integer>();
            this.BR = 1;
            this.uR = ur;
            this.alR.add(2);
        }
        
        c(final Set<Integer> alR, final int br, final String ur) {
            this.alR = alR;
            this.BR = br;
            this.uR = ur;
        }
        
        @Override
        protected boolean a(final ji.a a) {
            return this.alR.contains(a.hm());
        }
        
        @Override
        protected Object b(final ji.a a) {
            switch (a.hm()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                }
                case 2: {
                    return this.uR;
                }
            }
        }
        
        public int describeContents() {
            final oe creator = c.CREATOR;
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof c)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final c c = (c)o;
            for (final ji.a a : ny.c.alQ.values()) {
                if (this.a((ji.a)a)) {
                    if (!c.a((ji.a)a)) {
                        return false;
                    }
                    if (!this.b((ji.a)a).equals(c.b((ji.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (c.a((ji.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getUrl() {
            return this.uR;
        }
        
        @Override
        public boolean hasUrl() {
            return this.alR.contains(2);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ji.a<?, ?>> iterator = c.alQ.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ji.a a = (ji.a)iterator.next();
                if (this.a((ji.a)a)) {
                    n = this.b((ji.a)a).hashCode() + (n + a.hm());
                }
            }
            return n;
        }
        
        @Override
        public HashMap<String, ji.a<?, ?>> hf() {
            return c.alQ;
        }
        
        @Override
        public boolean isDataValid() {
            return true;
        }
        
        public c nx() {
            return this;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final oe creator = c.CREATOR;
            oe.a(this, parcel, n);
        }
    }
    
    public static final class d extends jj implements Name
    {
        public static final of CREATOR;
        private static final HashMap<String, ji.a<?, ?>> alQ;
        final int BR;
        final Set<Integer> alR;
        String amp;
        String ams;
        String anp;
        String anq;
        String anr;
        String ans;
        
        static {
            CREATOR = new of();
            (alQ = new HashMap<String, ji.a<?, ?>>()).put("familyName", ji.a.l("familyName", 2));
            d.alQ.put("formatted", (ji.a<?, ?>)ji.a.l("formatted", 3));
            d.alQ.put("givenName", (ji.a<?, ?>)ji.a.l("givenName", 4));
            d.alQ.put("honorificPrefix", (ji.a<?, ?>)ji.a.l("honorificPrefix", 5));
            d.alQ.put("honorificSuffix", (ji.a<?, ?>)ji.a.l("honorificSuffix", 6));
            d.alQ.put("middleName", (ji.a<?, ?>)ji.a.l("middleName", 7));
        }
        
        public d() {
            this.BR = 1;
            this.alR = new HashSet<Integer>();
        }
        
        d(final Set<Integer> alR, final int br, final String amp, final String anp, final String ams, final String anq, final String anr, final String ans) {
            this.alR = alR;
            this.BR = br;
            this.amp = amp;
            this.anp = anp;
            this.ams = ams;
            this.anq = anq;
            this.anr = anr;
            this.ans = ans;
        }
        
        @Override
        protected boolean a(final ji.a a) {
            return this.alR.contains(a.hm());
        }
        
        @Override
        protected Object b(final ji.a a) {
            switch (a.hm()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                }
                case 2: {
                    return this.amp;
                }
                case 3: {
                    return this.anp;
                }
                case 4: {
                    return this.ams;
                }
                case 5: {
                    return this.anq;
                }
                case 6: {
                    return this.anr;
                }
                case 7: {
                    return this.ans;
                }
            }
        }
        
        public int describeContents() {
            final of creator = d.CREATOR;
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof d)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final d d = (d)o;
            for (final ji.a a : ny.d.alQ.values()) {
                if (this.a((ji.a)a)) {
                    if (!d.a((ji.a)a)) {
                        return false;
                    }
                    if (!this.b((ji.a)a).equals(d.b((ji.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (d.a((ji.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getFamilyName() {
            return this.amp;
        }
        
        @Override
        public String getFormatted() {
            return this.anp;
        }
        
        @Override
        public String getGivenName() {
            return this.ams;
        }
        
        @Override
        public String getHonorificPrefix() {
            return this.anq;
        }
        
        @Override
        public String getHonorificSuffix() {
            return this.anr;
        }
        
        @Override
        public String getMiddleName() {
            return this.ans;
        }
        
        @Override
        public boolean hasFamilyName() {
            return this.alR.contains(2);
        }
        
        @Override
        public boolean hasFormatted() {
            return this.alR.contains(3);
        }
        
        @Override
        public boolean hasGivenName() {
            return this.alR.contains(4);
        }
        
        @Override
        public boolean hasHonorificPrefix() {
            return this.alR.contains(5);
        }
        
        @Override
        public boolean hasHonorificSuffix() {
            return this.alR.contains(6);
        }
        
        @Override
        public boolean hasMiddleName() {
            return this.alR.contains(7);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ji.a<?, ?>> iterator = d.alQ.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ji.a a = (ji.a)iterator.next();
                if (this.a((ji.a)a)) {
                    n = this.b((ji.a)a).hashCode() + (n + a.hm());
                }
            }
            return n;
        }
        
        @Override
        public HashMap<String, ji.a<?, ?>> hf() {
            return d.alQ;
        }
        
        @Override
        public boolean isDataValid() {
            return true;
        }
        
        public d ny() {
            return this;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final of creator = d.CREATOR;
            of.a(this, parcel, n);
        }
    }
    
    public static class e
    {
        public static int cf(final String s) {
            if (s.equals("person")) {
                return 0;
            }
            if (s.equals("page")) {
                return 1;
            }
            throw new IllegalArgumentException("Unknown objectType string: " + s);
        }
    }
    
    public static final class f extends jj implements Organizations
    {
        public static final og CREATOR;
        private static final HashMap<String, ji.a<?, ?>> alQ;
        final int BR;
        int FD;
        String No;
        String Tg;
        final Set<Integer> alR;
        String amE;
        String amo;
        String ant;
        String anu;
        boolean anv;
        String mName;
        
        static {
            CREATOR = new og();
            (alQ = new HashMap<String, ji.a<?, ?>>()).put("department", ji.a.l("department", 2));
            f.alQ.put("description", (ji.a<?, ?>)ji.a.l("description", 3));
            f.alQ.put("endDate", (ji.a<?, ?>)ji.a.l("endDate", 4));
            f.alQ.put("location", (ji.a<?, ?>)ji.a.l("location", 5));
            f.alQ.put("name", (ji.a<?, ?>)ji.a.l("name", 6));
            f.alQ.put("primary", (ji.a<?, ?>)ji.a.k("primary", 7));
            f.alQ.put("startDate", (ji.a<?, ?>)ji.a.l("startDate", 8));
            f.alQ.put("title", (ji.a<?, ?>)ji.a.l("title", 9));
            f.alQ.put("type", (ji.a<?, ?>)ji.a.a("type", 10, new jf().h("work", 0).h("school", 1), false));
        }
        
        public f() {
            this.BR = 1;
            this.alR = new HashSet<Integer>();
        }
        
        f(final Set<Integer> alR, final int br, final String ant, final String tg, final String amo, final String anu, final String mName, final boolean anv, final String amE, final String no, final int fd) {
            this.alR = alR;
            this.BR = br;
            this.ant = ant;
            this.Tg = tg;
            this.amo = amo;
            this.anu = anu;
            this.mName = mName;
            this.anv = anv;
            this.amE = amE;
            this.No = no;
            this.FD = fd;
        }
        
        @Override
        protected boolean a(final ji.a a) {
            return this.alR.contains(a.hm());
        }
        
        @Override
        protected Object b(final ji.a a) {
            switch (a.hm()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                }
                case 2: {
                    return this.ant;
                }
                case 3: {
                    return this.Tg;
                }
                case 4: {
                    return this.amo;
                }
                case 5: {
                    return this.anu;
                }
                case 6: {
                    return this.mName;
                }
                case 7: {
                    return this.anv;
                }
                case 8: {
                    return this.amE;
                }
                case 9: {
                    return this.No;
                }
                case 10: {
                    return this.FD;
                }
            }
        }
        
        public int describeContents() {
            final og creator = f.CREATOR;
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof f)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final f f = (f)o;
            for (final ji.a a : ny.f.alQ.values()) {
                if (this.a((ji.a)a)) {
                    if (!f.a((ji.a)a)) {
                        return false;
                    }
                    if (!this.b((ji.a)a).equals(f.b((ji.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (f.a((ji.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getDepartment() {
            return this.ant;
        }
        
        @Override
        public String getDescription() {
            return this.Tg;
        }
        
        @Override
        public String getEndDate() {
            return this.amo;
        }
        
        @Override
        public String getLocation() {
            return this.anu;
        }
        
        @Override
        public String getName() {
            return this.mName;
        }
        
        @Override
        public String getStartDate() {
            return this.amE;
        }
        
        @Override
        public String getTitle() {
            return this.No;
        }
        
        @Override
        public int getType() {
            return this.FD;
        }
        
        @Override
        public boolean hasDepartment() {
            return this.alR.contains(2);
        }
        
        @Override
        public boolean hasDescription() {
            return this.alR.contains(3);
        }
        
        @Override
        public boolean hasEndDate() {
            return this.alR.contains(4);
        }
        
        @Override
        public boolean hasLocation() {
            return this.alR.contains(5);
        }
        
        @Override
        public boolean hasName() {
            return this.alR.contains(6);
        }
        
        @Override
        public boolean hasPrimary() {
            return this.alR.contains(7);
        }
        
        @Override
        public boolean hasStartDate() {
            return this.alR.contains(8);
        }
        
        @Override
        public boolean hasTitle() {
            return this.alR.contains(9);
        }
        
        @Override
        public boolean hasType() {
            return this.alR.contains(10);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ji.a<?, ?>> iterator = f.alQ.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ji.a a = (ji.a)iterator.next();
                if (this.a((ji.a)a)) {
                    n = this.b((ji.a)a).hashCode() + (n + a.hm());
                }
            }
            return n;
        }
        
        @Override
        public HashMap<String, ji.a<?, ?>> hf() {
            return f.alQ;
        }
        
        @Override
        public boolean isDataValid() {
            return true;
        }
        
        @Override
        public boolean isPrimary() {
            return this.anv;
        }
        
        public f nz() {
            return this;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final og creator = f.CREATOR;
            og.a(this, parcel, n);
        }
    }
    
    public static final class g extends jj implements PlacesLived
    {
        public static final oh CREATOR;
        private static final HashMap<String, ji.a<?, ?>> alQ;
        final int BR;
        final Set<Integer> alR;
        boolean anv;
        String mValue;
        
        static {
            CREATOR = new oh();
            (alQ = new HashMap<String, ji.a<?, ?>>()).put("primary", ji.a.k("primary", 2));
            g.alQ.put("value", (ji.a<?, ?>)ji.a.l("value", 3));
        }
        
        public g() {
            this.BR = 1;
            this.alR = new HashSet<Integer>();
        }
        
        g(final Set<Integer> alR, final int br, final boolean anv, final String mValue) {
            this.alR = alR;
            this.BR = br;
            this.anv = anv;
            this.mValue = mValue;
        }
        
        @Override
        protected boolean a(final ji.a a) {
            return this.alR.contains(a.hm());
        }
        
        @Override
        protected Object b(final ji.a a) {
            switch (a.hm()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                }
                case 2: {
                    return this.anv;
                }
                case 3: {
                    return this.mValue;
                }
            }
        }
        
        public int describeContents() {
            final oh creator = g.CREATOR;
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof g)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final g g = (g)o;
            for (final ji.a a : ny.g.alQ.values()) {
                if (this.a((ji.a)a)) {
                    if (!g.a((ji.a)a)) {
                        return false;
                    }
                    if (!this.b((ji.a)a).equals(g.b((ji.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (g.a((ji.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getValue() {
            return this.mValue;
        }
        
        @Override
        public boolean hasPrimary() {
            return this.alR.contains(2);
        }
        
        @Override
        public boolean hasValue() {
            return this.alR.contains(3);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ji.a<?, ?>> iterator = g.alQ.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ji.a a = (ji.a)iterator.next();
                if (this.a((ji.a)a)) {
                    n = this.b((ji.a)a).hashCode() + (n + a.hm());
                }
            }
            return n;
        }
        
        @Override
        public HashMap<String, ji.a<?, ?>> hf() {
            return g.alQ;
        }
        
        @Override
        public boolean isDataValid() {
            return true;
        }
        
        @Override
        public boolean isPrimary() {
            return this.anv;
        }
        
        public g nA() {
            return this;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final oh creator = g.CREATOR;
            oh.a(this, parcel, n);
        }
    }
    
    public static final class h extends jj implements Urls
    {
        public static final oi CREATOR;
        private static final HashMap<String, ji.a<?, ?>> alQ;
        final int BR;
        int FD;
        final Set<Integer> alR;
        String anw;
        private final int anx;
        String mValue;
        
        static {
            CREATOR = new oi();
            (alQ = new HashMap<String, ji.a<?, ?>>()).put("label", ji.a.l("label", 5));
            h.alQ.put("type", (ji.a<?, ?>)ji.a.a("type", 6, new jf().h("home", 0).h("work", 1).h("blog", 2).h("profile", 3).h("other", 4).h("otherProfile", 5).h("contributor", 6).h("website", 7), false));
            h.alQ.put("value", (ji.a<?, ?>)ji.a.l("value", 4));
        }
        
        public h() {
            this.anx = 4;
            this.BR = 1;
            this.alR = new HashSet<Integer>();
        }
        
        h(final Set<Integer> alR, final int br, final String anw, final int fd, final String mValue, final int n) {
            this.anx = 4;
            this.alR = alR;
            this.BR = br;
            this.anw = anw;
            this.FD = fd;
            this.mValue = mValue;
        }
        
        @Override
        protected boolean a(final ji.a a) {
            return this.alR.contains(a.hm());
        }
        
        @Override
        protected Object b(final ji.a a) {
            switch (a.hm()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
                }
                case 5: {
                    return this.anw;
                }
                case 6: {
                    return this.FD;
                }
                case 4: {
                    return this.mValue;
                }
            }
        }
        
        public int describeContents() {
            final oi creator = h.CREATOR;
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof h)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final h h = (h)o;
            for (final ji.a a : ny.h.alQ.values()) {
                if (this.a((ji.a)a)) {
                    if (!h.a((ji.a)a)) {
                        return false;
                    }
                    if (!this.b((ji.a)a).equals(h.b((ji.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (h.a((ji.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getLabel() {
            return this.anw;
        }
        
        @Override
        public int getType() {
            return this.FD;
        }
        
        @Override
        public String getValue() {
            return this.mValue;
        }
        
        @Override
        public boolean hasLabel() {
            return this.alR.contains(5);
        }
        
        @Override
        public boolean hasType() {
            return this.alR.contains(6);
        }
        
        @Override
        public boolean hasValue() {
            return this.alR.contains(4);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ji.a<?, ?>> iterator = h.alQ.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ji.a a = (ji.a)iterator.next();
                if (this.a((ji.a)a)) {
                    n = this.b((ji.a)a).hashCode() + (n + a.hm());
                }
            }
            return n;
        }
        
        @Override
        public HashMap<String, ji.a<?, ?>> hf() {
            return h.alQ;
        }
        
        @Override
        public boolean isDataValid() {
            return true;
        }
        
        @Deprecated
        public int nB() {
            return 4;
        }
        
        public h nC() {
            return this;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final oi creator = h.CREATOR;
            oi.a(this, parcel, n);
        }
    }
}
