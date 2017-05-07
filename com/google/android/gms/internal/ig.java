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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ig extends es implements SafeParcelable, Person
{
    public static final ih CREATOR;
    private static final HashMap<String, es.a<?, ?>> Ep;
    private final Set<Integer> Eq;
    private String FA;
    private int FB;
    private List<f> FC;
    private List<g> FD;
    private int FE;
    private int FF;
    private String FG;
    private List<h> FH;
    private boolean FI;
    private String Fp;
    private a Fq;
    private String Fr;
    private String Fs;
    private int Ft;
    private b Fu;
    private String Fv;
    private c Fw;
    private boolean Fx;
    private String Fy;
    private d Fz;
    private int eL;
    private String iH;
    private final int kg;
    private String qa;
    private String uS;
    
    static {
        CREATOR = new ih();
        (Ep = new HashMap<String, es.a<?, ?>>()).put("aboutMe", es.a.g("aboutMe", 2));
        ig.Ep.put("ageRange", es.a.a("ageRange", 3, (Class<?>)a.class));
        ig.Ep.put("birthday", (es.a<?, ?>)es.a.g("birthday", 4));
        ig.Ep.put("braggingRights", (es.a<?, ?>)es.a.g("braggingRights", 5));
        ig.Ep.put("circledByCount", (es.a<?, ?>)es.a.d("circledByCount", 6));
        ig.Ep.put("cover", es.a.a("cover", 7, (Class<?>)b.class));
        ig.Ep.put("currentLocation", (es.a<?, ?>)es.a.g("currentLocation", 8));
        ig.Ep.put("displayName", (es.a<?, ?>)es.a.g("displayName", 9));
        ig.Ep.put("gender", (es.a<?, ?>)es.a.a("gender", 12, new ep().c("male", 0).c("female", 1).c("other", 2), false));
        ig.Ep.put("id", (es.a<?, ?>)es.a.g("id", 14));
        ig.Ep.put("image", es.a.a("image", 15, (Class<?>)c.class));
        ig.Ep.put("isPlusUser", (es.a<?, ?>)es.a.f("isPlusUser", 16));
        ig.Ep.put("language", (es.a<?, ?>)es.a.g("language", 18));
        ig.Ep.put("name", es.a.a("name", 19, (Class<?>)d.class));
        ig.Ep.put("nickname", (es.a<?, ?>)es.a.g("nickname", 20));
        ig.Ep.put("objectType", (es.a<?, ?>)es.a.a("objectType", 21, new ep().c("person", 0).c("page", 1), false));
        ig.Ep.put("organizations", (es.a<?, ?>)es.a.b("organizations", 22, f.class));
        ig.Ep.put("placesLived", (es.a<?, ?>)es.a.b("placesLived", 23, g.class));
        ig.Ep.put("plusOneCount", (es.a<?, ?>)es.a.d("plusOneCount", 24));
        ig.Ep.put("relationshipStatus", (es.a<?, ?>)es.a.a("relationshipStatus", 25, new ep().c("single", 0).c("in_a_relationship", 1).c("engaged", 2).c("married", 3).c("its_complicated", 4).c("open_relationship", 5).c("widowed", 6).c("in_domestic_partnership", 7).c("in_civil_union", 8), false));
        ig.Ep.put("tagline", (es.a<?, ?>)es.a.g("tagline", 26));
        ig.Ep.put("url", (es.a<?, ?>)es.a.g("url", 27));
        ig.Ep.put("urls", (es.a<?, ?>)es.a.b("urls", 28, h.class));
        ig.Ep.put("verified", (es.a<?, ?>)es.a.f("verified", 29));
    }
    
    public ig() {
        this.kg = 2;
        this.Eq = new HashSet<Integer>();
    }
    
    public ig(final String qa, final String us, final c fw, final int fb, final String ih) {
        this.kg = 2;
        this.Eq = new HashSet<Integer>();
        this.qa = qa;
        this.Eq.add(9);
        this.uS = us;
        this.Eq.add(14);
        this.Fw = fw;
        this.Eq.add(15);
        this.FB = fb;
        this.Eq.add(21);
        this.iH = ih;
        this.Eq.add(27);
    }
    
    ig(final Set<Integer> eq, final int kg, final String fp, final a fq, final String fr, final String fs, final int ft, final b fu, final String fv, final String qa, final int el, final String us, final c fw, final boolean fx, final String fy, final d fz, final String fa, final int fb, final List<f> fc, final List<g> fd, final int fe, final int ff, final String fg, final String ih, final List<h> fh, final boolean fi) {
        this.Eq = eq;
        this.kg = kg;
        this.Fp = fp;
        this.Fq = fq;
        this.Fr = fr;
        this.Fs = fs;
        this.Ft = ft;
        this.Fu = fu;
        this.Fv = fv;
        this.qa = qa;
        this.eL = el;
        this.uS = us;
        this.Fw = fw;
        this.Fx = fx;
        this.Fy = fy;
        this.Fz = fz;
        this.FA = fa;
        this.FB = fb;
        this.FC = fc;
        this.FD = fd;
        this.FE = fe;
        this.FF = ff;
        this.FG = fg;
        this.iH = ih;
        this.FH = fh;
        this.FI = fi;
    }
    
    public static ig g(final byte[] array) {
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final ig au = ig.CREATOR.au(obtain);
        obtain.recycle();
        return au;
    }
    
    @Override
    protected Object V(final String s) {
        return null;
    }
    
    @Override
    protected boolean W(final String s) {
        return false;
    }
    
    @Override
    protected boolean a(final es.a a) {
        return this.Eq.contains(a.cq());
    }
    
    @Override
    protected Object b(final es.a a) {
        switch (a.cq()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
            }
            case 2: {
                return this.Fp;
            }
            case 3: {
                return this.Fq;
            }
            case 4: {
                return this.Fr;
            }
            case 5: {
                return this.Fs;
            }
            case 6: {
                return this.Ft;
            }
            case 7: {
                return this.Fu;
            }
            case 8: {
                return this.Fv;
            }
            case 9: {
                return this.qa;
            }
            case 12: {
                return this.eL;
            }
            case 14: {
                return this.uS;
            }
            case 15: {
                return this.Fw;
            }
            case 16: {
                return this.Fx;
            }
            case 18: {
                return this.Fy;
            }
            case 19: {
                return this.Fz;
            }
            case 20: {
                return this.FA;
            }
            case 21: {
                return this.FB;
            }
            case 22: {
                return this.FC;
            }
            case 23: {
                return this.FD;
            }
            case 24: {
                return this.FE;
            }
            case 25: {
                return this.FF;
            }
            case 26: {
                return this.FG;
            }
            case 27: {
                return this.iH;
            }
            case 28: {
                return this.FH;
            }
            case 29: {
                return this.FI;
            }
        }
    }
    
    @Override
    public HashMap<String, es.a<?, ?>> cj() {
        return ig.Ep;
    }
    
    public int describeContents() {
        final ih creator = ig.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ig)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ig ig = (ig)o;
        for (final es.a a : com.google.android.gms.internal.ig.Ep.values()) {
            if (this.a((es.a)a)) {
                if (!ig.a((es.a)a)) {
                    return false;
                }
                if (!this.b((es.a)a).equals(ig.b((es.a)a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ig.a((es.a)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    List<g> fA() {
        return this.FD;
    }
    
    List<h> fB() {
        return this.FH;
    }
    
    public ig fC() {
        return this;
    }
    
    Set<Integer> fa() {
        return this.Eq;
    }
    
    a fv() {
        return this.Fq;
    }
    
    b fw() {
        return this.Fu;
    }
    
    c fx() {
        return this.Fw;
    }
    
    d fy() {
        return this.Fz;
    }
    
    List<f> fz() {
        return this.FC;
    }
    
    @Override
    public String getAboutMe() {
        return this.Fp;
    }
    
    @Override
    public AgeRange getAgeRange() {
        return this.Fq;
    }
    
    @Override
    public String getBirthday() {
        return this.Fr;
    }
    
    @Override
    public String getBraggingRights() {
        return this.Fs;
    }
    
    @Override
    public int getCircledByCount() {
        return this.Ft;
    }
    
    @Override
    public Cover getCover() {
        return this.Fu;
    }
    
    @Override
    public String getCurrentLocation() {
        return this.Fv;
    }
    
    @Override
    public String getDisplayName() {
        return this.qa;
    }
    
    @Override
    public int getGender() {
        return this.eL;
    }
    
    @Override
    public String getId() {
        return this.uS;
    }
    
    @Override
    public Image getImage() {
        return this.Fw;
    }
    
    @Override
    public String getLanguage() {
        return this.Fy;
    }
    
    @Override
    public Name getName() {
        return this.Fz;
    }
    
    @Override
    public String getNickname() {
        return this.FA;
    }
    
    @Override
    public int getObjectType() {
        return this.FB;
    }
    
    @Override
    public List<Organizations> getOrganizations() {
        return (List<Organizations>)(ArrayList)this.FC;
    }
    
    @Override
    public List<PlacesLived> getPlacesLived() {
        return (List<PlacesLived>)(ArrayList)this.FD;
    }
    
    @Override
    public int getPlusOneCount() {
        return this.FE;
    }
    
    @Override
    public int getRelationshipStatus() {
        return this.FF;
    }
    
    @Override
    public String getTagline() {
        return this.FG;
    }
    
    @Override
    public String getUrl() {
        return this.iH;
    }
    
    @Override
    public List<Urls> getUrls() {
        return (List<Urls>)(ArrayList)this.FH;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public boolean hasAboutMe() {
        return this.Eq.contains(2);
    }
    
    @Override
    public boolean hasAgeRange() {
        return this.Eq.contains(3);
    }
    
    @Override
    public boolean hasBirthday() {
        return this.Eq.contains(4);
    }
    
    @Override
    public boolean hasBraggingRights() {
        return this.Eq.contains(5);
    }
    
    @Override
    public boolean hasCircledByCount() {
        return this.Eq.contains(6);
    }
    
    @Override
    public boolean hasCover() {
        return this.Eq.contains(7);
    }
    
    @Override
    public boolean hasCurrentLocation() {
        return this.Eq.contains(8);
    }
    
    @Override
    public boolean hasDisplayName() {
        return this.Eq.contains(9);
    }
    
    @Override
    public boolean hasGender() {
        return this.Eq.contains(12);
    }
    
    @Override
    public boolean hasId() {
        return this.Eq.contains(14);
    }
    
    @Override
    public boolean hasImage() {
        return this.Eq.contains(15);
    }
    
    @Override
    public boolean hasIsPlusUser() {
        return this.Eq.contains(16);
    }
    
    @Override
    public boolean hasLanguage() {
        return this.Eq.contains(18);
    }
    
    @Override
    public boolean hasName() {
        return this.Eq.contains(19);
    }
    
    @Override
    public boolean hasNickname() {
        return this.Eq.contains(20);
    }
    
    @Override
    public boolean hasObjectType() {
        return this.Eq.contains(21);
    }
    
    @Override
    public boolean hasOrganizations() {
        return this.Eq.contains(22);
    }
    
    @Override
    public boolean hasPlacesLived() {
        return this.Eq.contains(23);
    }
    
    @Override
    public boolean hasPlusOneCount() {
        return this.Eq.contains(24);
    }
    
    @Override
    public boolean hasRelationshipStatus() {
        return this.Eq.contains(25);
    }
    
    @Override
    public boolean hasTagline() {
        return this.Eq.contains(26);
    }
    
    @Override
    public boolean hasUrl() {
        return this.Eq.contains(27);
    }
    
    @Override
    public boolean hasUrls() {
        return this.Eq.contains(28);
    }
    
    @Override
    public boolean hasVerified() {
        return this.Eq.contains(29);
    }
    
    @Override
    public int hashCode() {
        final Iterator<es.a<?, ?>> iterator = ig.Ep.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final es.a a = (es.a)iterator.next();
            if (this.a((es.a)a)) {
                n = this.b((es.a)a).hashCode() + (n + a.cq());
            }
        }
        return n;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean isPlusUser() {
        return this.Fx;
    }
    
    @Override
    public boolean isVerified() {
        return this.FI;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ih creator = ig.CREATOR;
        ih.a(this, parcel, n);
    }
    
    public static final class a extends es implements SafeParcelable, AgeRange
    {
        public static final ii CREATOR;
        private static final HashMap<String, es.a<?, ?>> Ep;
        private final Set<Integer> Eq;
        private int FJ;
        private int FK;
        private final int kg;
        
        static {
            CREATOR = new ii();
            (Ep = new HashMap<String, es.a<?, ?>>()).put("max", es.a.d("max", 2));
            a.Ep.put("min", (es.a<?, ?>)es.a.d("min", 3));
        }
        
        public a() {
            this.kg = 1;
            this.Eq = new HashSet<Integer>();
        }
        
        a(final Set<Integer> eq, final int kg, final int fj, final int fk) {
            this.Eq = eq;
            this.kg = kg;
            this.FJ = fj;
            this.FK = fk;
        }
        
        @Override
        protected Object V(final String s) {
            return null;
        }
        
        @Override
        protected boolean W(final String s) {
            return false;
        }
        
        @Override
        protected boolean a(final es.a a) {
            return this.Eq.contains(a.cq());
        }
        
        @Override
        protected Object b(final es.a a) {
            switch (a.cq()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                }
                case 2: {
                    return this.FJ;
                }
                case 3: {
                    return this.FK;
                }
            }
        }
        
        @Override
        public HashMap<String, es.a<?, ?>> cj() {
            return a.Ep;
        }
        
        public int describeContents() {
            final ii creator = a.CREATOR;
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
            for (final es.a a2 : ig.a.Ep.values()) {
                if (this.a((es.a)a2)) {
                    if (!a.a((es.a)a2)) {
                        return false;
                    }
                    if (!this.b((es.a)a2).equals(a.b((es.a)a2))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (a.a((es.a)a2)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        public a fD() {
            return this;
        }
        
        Set<Integer> fa() {
            return this.Eq;
        }
        
        @Override
        public int getMax() {
            return this.FJ;
        }
        
        @Override
        public int getMin() {
            return this.FK;
        }
        
        int getVersionCode() {
            return this.kg;
        }
        
        @Override
        public boolean hasMax() {
            return this.Eq.contains(2);
        }
        
        @Override
        public boolean hasMin() {
            return this.Eq.contains(3);
        }
        
        @Override
        public int hashCode() {
            final Iterator<es.a<?, ?>> iterator = a.Ep.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final es.a a = (es.a)iterator.next();
                if (this.a((es.a)a)) {
                    n = this.b((es.a)a).hashCode() + (n + a.cq());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ii creator = a.CREATOR;
            ii.a(this, parcel, n);
        }
    }
    
    public static final class b extends es implements SafeParcelable, Cover
    {
        public static final ij CREATOR;
        private static final HashMap<String, es.a<?, ?>> Ep;
        private final Set<Integer> Eq;
        private a FL;
        private ig.b.b FM;
        private int FN;
        private final int kg;
        
        static {
            CREATOR = new ij();
            (Ep = new HashMap<String, es.a<?, ?>>()).put("coverInfo", es.a.a("coverInfo", 2, (Class<?>)a.class));
            ig.b.Ep.put("coverPhoto", es.a.a("coverPhoto", 3, (Class<?>)ig.b.b.class));
            ig.b.Ep.put("layout", (es.a<?, ?>)es.a.a("layout", 4, new ep().c("banner", 0), false));
        }
        
        public b() {
            this.kg = 1;
            this.Eq = new HashSet<Integer>();
        }
        
        b(final Set<Integer> eq, final int kg, final a fl, final ig.b.b fm, final int fn) {
            this.Eq = eq;
            this.kg = kg;
            this.FL = fl;
            this.FM = fm;
            this.FN = fn;
        }
        
        @Override
        protected Object V(final String s) {
            return null;
        }
        
        @Override
        protected boolean W(final String s) {
            return false;
        }
        
        @Override
        protected boolean a(final es.a a) {
            return this.Eq.contains(a.cq());
        }
        
        @Override
        protected Object b(final es.a a) {
            switch (a.cq()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                }
                case 2: {
                    return this.FL;
                }
                case 3: {
                    return this.FM;
                }
                case 4: {
                    return this.FN;
                }
            }
        }
        
        @Override
        public HashMap<String, es.a<?, ?>> cj() {
            return ig.b.Ep;
        }
        
        public int describeContents() {
            final ij creator = ig.b.CREATOR;
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof ig.b)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final ig.b b = (ig.b)o;
            for (final es.a a : ig.b.Ep.values()) {
                if (this.a((es.a)a)) {
                    if (!b.a((es.a)a)) {
                        return false;
                    }
                    if (!this.b((es.a)a).equals(b.b((es.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (b.a((es.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        a fE() {
            return this.FL;
        }
        
        ig.b.b fF() {
            return this.FM;
        }
        
        public ig.b fG() {
            return this;
        }
        
        Set<Integer> fa() {
            return this.Eq;
        }
        
        @Override
        public CoverInfo getCoverInfo() {
            return this.FL;
        }
        
        @Override
        public CoverPhoto getCoverPhoto() {
            return this.FM;
        }
        
        @Override
        public int getLayout() {
            return this.FN;
        }
        
        int getVersionCode() {
            return this.kg;
        }
        
        @Override
        public boolean hasCoverInfo() {
            return this.Eq.contains(2);
        }
        
        @Override
        public boolean hasCoverPhoto() {
            return this.Eq.contains(3);
        }
        
        @Override
        public boolean hasLayout() {
            return this.Eq.contains(4);
        }
        
        @Override
        public int hashCode() {
            final Iterator<es.a<?, ?>> iterator = ig.b.Ep.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final es.a a = (es.a)iterator.next();
                if (this.a((es.a)a)) {
                    n = this.b((es.a)a).hashCode() + (n + a.cq());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ij creator = ig.b.CREATOR;
            ij.a(this, parcel, n);
        }
        
        public static final class a extends es implements SafeParcelable, CoverInfo
        {
            public static final ik CREATOR;
            private static final HashMap<String, es.a<?, ?>> Ep;
            private final Set<Integer> Eq;
            private int FO;
            private int FP;
            private final int kg;
            
            static {
                CREATOR = new ik();
                (Ep = new HashMap<String, es.a<?, ?>>()).put("leftImageOffset", es.a.d("leftImageOffset", 2));
                a.Ep.put("topImageOffset", (es.a<?, ?>)es.a.d("topImageOffset", 3));
            }
            
            public a() {
                this.kg = 1;
                this.Eq = new HashSet<Integer>();
            }
            
            a(final Set<Integer> eq, final int kg, final int fo, final int fp) {
                this.Eq = eq;
                this.kg = kg;
                this.FO = fo;
                this.FP = fp;
            }
            
            @Override
            protected Object V(final String s) {
                return null;
            }
            
            @Override
            protected boolean W(final String s) {
                return false;
            }
            
            @Override
            protected boolean a(final es.a a) {
                return this.Eq.contains(a.cq());
            }
            
            @Override
            protected Object b(final es.a a) {
                switch (a.cq()) {
                    default: {
                        throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                    }
                    case 2: {
                        return this.FO;
                    }
                    case 3: {
                        return this.FP;
                    }
                }
            }
            
            @Override
            public HashMap<String, es.a<?, ?>> cj() {
                return a.Ep;
            }
            
            public int describeContents() {
                final ik creator = a.CREATOR;
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
                for (final es.a a2 : ig.b.a.Ep.values()) {
                    if (this.a((es.a)a2)) {
                        if (!a.a((es.a)a2)) {
                            return false;
                        }
                        if (!this.b((es.a)a2).equals(a.b((es.a)a2))) {
                            return false;
                        }
                        continue;
                    }
                    else {
                        if (a.a((es.a)a2)) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
            
            public a fH() {
                return this;
            }
            
            Set<Integer> fa() {
                return this.Eq;
            }
            
            @Override
            public int getLeftImageOffset() {
                return this.FO;
            }
            
            @Override
            public int getTopImageOffset() {
                return this.FP;
            }
            
            int getVersionCode() {
                return this.kg;
            }
            
            @Override
            public boolean hasLeftImageOffset() {
                return this.Eq.contains(2);
            }
            
            @Override
            public boolean hasTopImageOffset() {
                return this.Eq.contains(3);
            }
            
            @Override
            public int hashCode() {
                final Iterator<es.a<?, ?>> iterator = a.Ep.values().iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    final es.a a = (es.a)iterator.next();
                    if (this.a((es.a)a)) {
                        n = this.b((es.a)a).hashCode() + (n + a.cq());
                    }
                }
                return n;
            }
            
            public boolean isDataValid() {
                return true;
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                final ik creator = a.CREATOR;
                ik.a(this, parcel, n);
            }
        }
        
        public static final class b extends es implements SafeParcelable, CoverPhoto
        {
            public static final il CREATOR;
            private static final HashMap<String, es.a<?, ?>> Ep;
            private final Set<Integer> Eq;
            private String iH;
            private final int kg;
            private int v;
            private int w;
            
            static {
                CREATOR = new il();
                (Ep = new HashMap<String, es.a<?, ?>>()).put("height", es.a.d("height", 2));
                b.Ep.put("url", (es.a<?, ?>)es.a.g("url", 3));
                b.Ep.put("width", (es.a<?, ?>)es.a.d("width", 4));
            }
            
            public b() {
                this.kg = 1;
                this.Eq = new HashSet<Integer>();
            }
            
            b(final Set<Integer> eq, final int kg, final int v, final String ih, final int w) {
                this.Eq = eq;
                this.kg = kg;
                this.v = v;
                this.iH = ih;
                this.w = w;
            }
            
            @Override
            protected Object V(final String s) {
                return null;
            }
            
            @Override
            protected boolean W(final String s) {
                return false;
            }
            
            @Override
            protected boolean a(final es.a a) {
                return this.Eq.contains(a.cq());
            }
            
            @Override
            protected Object b(final es.a a) {
                switch (a.cq()) {
                    default: {
                        throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                    }
                    case 2: {
                        return this.v;
                    }
                    case 3: {
                        return this.iH;
                    }
                    case 4: {
                        return this.w;
                    }
                }
            }
            
            @Override
            public HashMap<String, es.a<?, ?>> cj() {
                return b.Ep;
            }
            
            public int describeContents() {
                final il creator = b.CREATOR;
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
                for (final es.a a : ig.b.b.Ep.values()) {
                    if (this.a((es.a)a)) {
                        if (!b.a((es.a)a)) {
                            return false;
                        }
                        if (!this.b((es.a)a).equals(b.b((es.a)a))) {
                            return false;
                        }
                        continue;
                    }
                    else {
                        if (b.a((es.a)a)) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
            
            public b fI() {
                return this;
            }
            
            Set<Integer> fa() {
                return this.Eq;
            }
            
            @Override
            public int getHeight() {
                return this.v;
            }
            
            @Override
            public String getUrl() {
                return this.iH;
            }
            
            int getVersionCode() {
                return this.kg;
            }
            
            @Override
            public int getWidth() {
                return this.w;
            }
            
            @Override
            public boolean hasHeight() {
                return this.Eq.contains(2);
            }
            
            @Override
            public boolean hasUrl() {
                return this.Eq.contains(3);
            }
            
            @Override
            public boolean hasWidth() {
                return this.Eq.contains(4);
            }
            
            @Override
            public int hashCode() {
                final Iterator<es.a<?, ?>> iterator = b.Ep.values().iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    final es.a a = (es.a)iterator.next();
                    if (this.a((es.a)a)) {
                        n = this.b((es.a)a).hashCode() + (n + a.cq());
                    }
                }
                return n;
            }
            
            public boolean isDataValid() {
                return true;
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                final il creator = b.CREATOR;
                il.a(this, parcel, n);
            }
        }
    }
    
    public static final class c extends es implements SafeParcelable, Image
    {
        public static final im CREATOR;
        private static final HashMap<String, es.a<?, ?>> Ep;
        private final Set<Integer> Eq;
        private String iH;
        private final int kg;
        
        static {
            CREATOR = new im();
            (Ep = new HashMap<String, es.a<?, ?>>()).put("url", es.a.g("url", 2));
        }
        
        public c() {
            this.kg = 1;
            this.Eq = new HashSet<Integer>();
        }
        
        public c(final String ih) {
            this.Eq = new HashSet<Integer>();
            this.kg = 1;
            this.iH = ih;
            this.Eq.add(2);
        }
        
        c(final Set<Integer> eq, final int kg, final String ih) {
            this.Eq = eq;
            this.kg = kg;
            this.iH = ih;
        }
        
        @Override
        protected Object V(final String s) {
            return null;
        }
        
        @Override
        protected boolean W(final String s) {
            return false;
        }
        
        @Override
        protected boolean a(final es.a a) {
            return this.Eq.contains(a.cq());
        }
        
        @Override
        protected Object b(final es.a a) {
            switch (a.cq()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                }
                case 2: {
                    return this.iH;
                }
            }
        }
        
        @Override
        public HashMap<String, es.a<?, ?>> cj() {
            return c.Ep;
        }
        
        public int describeContents() {
            final im creator = c.CREATOR;
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
            for (final es.a a : ig.c.Ep.values()) {
                if (this.a((es.a)a)) {
                    if (!c.a((es.a)a)) {
                        return false;
                    }
                    if (!this.b((es.a)a).equals(c.b((es.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (c.a((es.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        public c fJ() {
            return this;
        }
        
        Set<Integer> fa() {
            return this.Eq;
        }
        
        @Override
        public String getUrl() {
            return this.iH;
        }
        
        int getVersionCode() {
            return this.kg;
        }
        
        @Override
        public boolean hasUrl() {
            return this.Eq.contains(2);
        }
        
        @Override
        public int hashCode() {
            final Iterator<es.a<?, ?>> iterator = c.Ep.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final es.a a = (es.a)iterator.next();
                if (this.a((es.a)a)) {
                    n = this.b((es.a)a).hashCode() + (n + a.cq());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final im creator = c.CREATOR;
            im.a(this, parcel, n);
        }
    }
    
    public static final class d extends es implements SafeParcelable, Name
    {
        public static final in CREATOR;
        private static final HashMap<String, es.a<?, ?>> Ep;
        private String EP;
        private String ES;
        private final Set<Integer> Eq;
        private String FQ;
        private String FR;
        private String FS;
        private String FT;
        private final int kg;
        
        static {
            CREATOR = new in();
            (Ep = new HashMap<String, es.a<?, ?>>()).put("familyName", es.a.g("familyName", 2));
            d.Ep.put("formatted", (es.a<?, ?>)es.a.g("formatted", 3));
            d.Ep.put("givenName", (es.a<?, ?>)es.a.g("givenName", 4));
            d.Ep.put("honorificPrefix", (es.a<?, ?>)es.a.g("honorificPrefix", 5));
            d.Ep.put("honorificSuffix", (es.a<?, ?>)es.a.g("honorificSuffix", 6));
            d.Ep.put("middleName", (es.a<?, ?>)es.a.g("middleName", 7));
        }
        
        public d() {
            this.kg = 1;
            this.Eq = new HashSet<Integer>();
        }
        
        d(final Set<Integer> eq, final int kg, final String ep, final String fq, final String es, final String fr, final String fs, final String ft) {
            this.Eq = eq;
            this.kg = kg;
            this.EP = ep;
            this.FQ = fq;
            this.ES = es;
            this.FR = fr;
            this.FS = fs;
            this.FT = ft;
        }
        
        @Override
        protected Object V(final String s) {
            return null;
        }
        
        @Override
        protected boolean W(final String s) {
            return false;
        }
        
        @Override
        protected boolean a(final es.a a) {
            return this.Eq.contains(a.cq());
        }
        
        @Override
        protected Object b(final es.a a) {
            switch (a.cq()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                }
                case 2: {
                    return this.EP;
                }
                case 3: {
                    return this.FQ;
                }
                case 4: {
                    return this.ES;
                }
                case 5: {
                    return this.FR;
                }
                case 6: {
                    return this.FS;
                }
                case 7: {
                    return this.FT;
                }
            }
        }
        
        @Override
        public HashMap<String, es.a<?, ?>> cj() {
            return d.Ep;
        }
        
        public int describeContents() {
            final in creator = d.CREATOR;
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
            for (final es.a a : ig.d.Ep.values()) {
                if (this.a((es.a)a)) {
                    if (!d.a((es.a)a)) {
                        return false;
                    }
                    if (!this.b((es.a)a).equals(d.b((es.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (d.a((es.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        public d fK() {
            return this;
        }
        
        Set<Integer> fa() {
            return this.Eq;
        }
        
        @Override
        public String getFamilyName() {
            return this.EP;
        }
        
        @Override
        public String getFormatted() {
            return this.FQ;
        }
        
        @Override
        public String getGivenName() {
            return this.ES;
        }
        
        @Override
        public String getHonorificPrefix() {
            return this.FR;
        }
        
        @Override
        public String getHonorificSuffix() {
            return this.FS;
        }
        
        @Override
        public String getMiddleName() {
            return this.FT;
        }
        
        int getVersionCode() {
            return this.kg;
        }
        
        @Override
        public boolean hasFamilyName() {
            return this.Eq.contains(2);
        }
        
        @Override
        public boolean hasFormatted() {
            return this.Eq.contains(3);
        }
        
        @Override
        public boolean hasGivenName() {
            return this.Eq.contains(4);
        }
        
        @Override
        public boolean hasHonorificPrefix() {
            return this.Eq.contains(5);
        }
        
        @Override
        public boolean hasHonorificSuffix() {
            return this.Eq.contains(6);
        }
        
        @Override
        public boolean hasMiddleName() {
            return this.Eq.contains(7);
        }
        
        @Override
        public int hashCode() {
            final Iterator<es.a<?, ?>> iterator = d.Ep.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final es.a a = (es.a)iterator.next();
                if (this.a((es.a)a)) {
                    n = this.b((es.a)a).hashCode() + (n + a.cq());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final in creator = d.CREATOR;
            in.a(this, parcel, n);
        }
    }
    
    public static class e
    {
        public static int aB(final String s) {
            if (s.equals("person")) {
                return 0;
            }
            if (s.equals("page")) {
                return 1;
            }
            throw new IllegalArgumentException("Unknown objectType string: " + s);
        }
    }
    
    public static final class f extends es implements SafeParcelable, Organizations
    {
        public static final io CREATOR;
        private static final HashMap<String, es.a<?, ?>> Ep;
        private String EO;
        private final Set<Integer> Eq;
        private String FU;
        private String FV;
        private boolean FW;
        private String Fe;
        private final int kg;
        private String mName;
        private int os;
        private String qL;
        private String sJ;
        
        static {
            CREATOR = new io();
            (Ep = new HashMap<String, es.a<?, ?>>()).put("department", es.a.g("department", 2));
            f.Ep.put("description", (es.a<?, ?>)es.a.g("description", 3));
            f.Ep.put("endDate", (es.a<?, ?>)es.a.g("endDate", 4));
            f.Ep.put("location", (es.a<?, ?>)es.a.g("location", 5));
            f.Ep.put("name", (es.a<?, ?>)es.a.g("name", 6));
            f.Ep.put("primary", (es.a<?, ?>)es.a.f("primary", 7));
            f.Ep.put("startDate", (es.a<?, ?>)es.a.g("startDate", 8));
            f.Ep.put("title", (es.a<?, ?>)es.a.g("title", 9));
            f.Ep.put("type", (es.a<?, ?>)es.a.a("type", 10, new ep().c("work", 0).c("school", 1), false));
        }
        
        public f() {
            this.kg = 1;
            this.Eq = new HashSet<Integer>();
        }
        
        f(final Set<Integer> eq, final int kg, final String fu, final String sj, final String eo, final String fv, final String mName, final boolean fw, final String fe, final String ql, final int os) {
            this.Eq = eq;
            this.kg = kg;
            this.FU = fu;
            this.sJ = sj;
            this.EO = eo;
            this.FV = fv;
            this.mName = mName;
            this.FW = fw;
            this.Fe = fe;
            this.qL = ql;
            this.os = os;
        }
        
        @Override
        protected Object V(final String s) {
            return null;
        }
        
        @Override
        protected boolean W(final String s) {
            return false;
        }
        
        @Override
        protected boolean a(final es.a a) {
            return this.Eq.contains(a.cq());
        }
        
        @Override
        protected Object b(final es.a a) {
            switch (a.cq()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                }
                case 2: {
                    return this.FU;
                }
                case 3: {
                    return this.sJ;
                }
                case 4: {
                    return this.EO;
                }
                case 5: {
                    return this.FV;
                }
                case 6: {
                    return this.mName;
                }
                case 7: {
                    return this.FW;
                }
                case 8: {
                    return this.Fe;
                }
                case 9: {
                    return this.qL;
                }
                case 10: {
                    return this.os;
                }
            }
        }
        
        @Override
        public HashMap<String, es.a<?, ?>> cj() {
            return f.Ep;
        }
        
        public int describeContents() {
            final io creator = f.CREATOR;
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
            for (final es.a a : ig.f.Ep.values()) {
                if (this.a((es.a)a)) {
                    if (!f.a((es.a)a)) {
                        return false;
                    }
                    if (!this.b((es.a)a).equals(f.b((es.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (f.a((es.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        public f fL() {
            return this;
        }
        
        Set<Integer> fa() {
            return this.Eq;
        }
        
        @Override
        public String getDepartment() {
            return this.FU;
        }
        
        @Override
        public String getDescription() {
            return this.sJ;
        }
        
        @Override
        public String getEndDate() {
            return this.EO;
        }
        
        @Override
        public String getLocation() {
            return this.FV;
        }
        
        @Override
        public String getName() {
            return this.mName;
        }
        
        @Override
        public String getStartDate() {
            return this.Fe;
        }
        
        @Override
        public String getTitle() {
            return this.qL;
        }
        
        @Override
        public int getType() {
            return this.os;
        }
        
        int getVersionCode() {
            return this.kg;
        }
        
        @Override
        public boolean hasDepartment() {
            return this.Eq.contains(2);
        }
        
        @Override
        public boolean hasDescription() {
            return this.Eq.contains(3);
        }
        
        @Override
        public boolean hasEndDate() {
            return this.Eq.contains(4);
        }
        
        @Override
        public boolean hasLocation() {
            return this.Eq.contains(5);
        }
        
        @Override
        public boolean hasName() {
            return this.Eq.contains(6);
        }
        
        @Override
        public boolean hasPrimary() {
            return this.Eq.contains(7);
        }
        
        @Override
        public boolean hasStartDate() {
            return this.Eq.contains(8);
        }
        
        @Override
        public boolean hasTitle() {
            return this.Eq.contains(9);
        }
        
        @Override
        public boolean hasType() {
            return this.Eq.contains(10);
        }
        
        @Override
        public int hashCode() {
            final Iterator<es.a<?, ?>> iterator = f.Ep.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final es.a a = (es.a)iterator.next();
                if (this.a((es.a)a)) {
                    n = this.b((es.a)a).hashCode() + (n + a.cq());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        @Override
        public boolean isPrimary() {
            return this.FW;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final io creator = f.CREATOR;
            io.a(this, parcel, n);
        }
    }
    
    public static final class g extends es implements SafeParcelable, PlacesLived
    {
        public static final ip CREATOR;
        private static final HashMap<String, es.a<?, ?>> Ep;
        private final Set<Integer> Eq;
        private boolean FW;
        private final int kg;
        private String mValue;
        
        static {
            CREATOR = new ip();
            (Ep = new HashMap<String, es.a<?, ?>>()).put("primary", es.a.f("primary", 2));
            g.Ep.put("value", (es.a<?, ?>)es.a.g("value", 3));
        }
        
        public g() {
            this.kg = 1;
            this.Eq = new HashSet<Integer>();
        }
        
        g(final Set<Integer> eq, final int kg, final boolean fw, final String mValue) {
            this.Eq = eq;
            this.kg = kg;
            this.FW = fw;
            this.mValue = mValue;
        }
        
        @Override
        protected Object V(final String s) {
            return null;
        }
        
        @Override
        protected boolean W(final String s) {
            return false;
        }
        
        @Override
        protected boolean a(final es.a a) {
            return this.Eq.contains(a.cq());
        }
        
        @Override
        protected Object b(final es.a a) {
            switch (a.cq()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                }
                case 2: {
                    return this.FW;
                }
                case 3: {
                    return this.mValue;
                }
            }
        }
        
        @Override
        public HashMap<String, es.a<?, ?>> cj() {
            return g.Ep;
        }
        
        public int describeContents() {
            final ip creator = g.CREATOR;
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
            for (final es.a a : ig.g.Ep.values()) {
                if (this.a((es.a)a)) {
                    if (!g.a((es.a)a)) {
                        return false;
                    }
                    if (!this.b((es.a)a).equals(g.b((es.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (g.a((es.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        public g fM() {
            return this;
        }
        
        Set<Integer> fa() {
            return this.Eq;
        }
        
        @Override
        public String getValue() {
            return this.mValue;
        }
        
        int getVersionCode() {
            return this.kg;
        }
        
        @Override
        public boolean hasPrimary() {
            return this.Eq.contains(2);
        }
        
        @Override
        public boolean hasValue() {
            return this.Eq.contains(3);
        }
        
        @Override
        public int hashCode() {
            final Iterator<es.a<?, ?>> iterator = g.Ep.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final es.a a = (es.a)iterator.next();
                if (this.a((es.a)a)) {
                    n = this.b((es.a)a).hashCode() + (n + a.cq());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        @Override
        public boolean isPrimary() {
            return this.FW;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ip creator = g.CREATOR;
            ip.a(this, parcel, n);
        }
    }
    
    public static final class h extends es implements SafeParcelable, Urls
    {
        public static final iq CREATOR;
        private static final HashMap<String, es.a<?, ?>> Ep;
        private final Set<Integer> Eq;
        private String FX;
        private final int FY;
        private final int kg;
        private String mValue;
        private int os;
        
        static {
            CREATOR = new iq();
            (Ep = new HashMap<String, es.a<?, ?>>()).put("label", es.a.g("label", 5));
            h.Ep.put("type", (es.a<?, ?>)es.a.a("type", 6, new ep().c("home", 0).c("work", 1).c("blog", 2).c("profile", 3).c("other", 4).c("otherProfile", 5).c("contributor", 6).c("website", 7), false));
            h.Ep.put("value", (es.a<?, ?>)es.a.g("value", 4));
        }
        
        public h() {
            this.FY = 4;
            this.kg = 2;
            this.Eq = new HashSet<Integer>();
        }
        
        h(final Set<Integer> eq, final int kg, final String fx, final int os, final String mValue, final int n) {
            this.FY = 4;
            this.Eq = eq;
            this.kg = kg;
            this.FX = fx;
            this.os = os;
            this.mValue = mValue;
        }
        
        @Override
        protected Object V(final String s) {
            return null;
        }
        
        @Override
        protected boolean W(final String s) {
            return false;
        }
        
        @Override
        protected boolean a(final es.a a) {
            return this.Eq.contains(a.cq());
        }
        
        @Override
        protected Object b(final es.a a) {
            switch (a.cq()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
                }
                case 5: {
                    return this.FX;
                }
                case 6: {
                    return this.os;
                }
                case 4: {
                    return this.mValue;
                }
            }
        }
        
        @Override
        public HashMap<String, es.a<?, ?>> cj() {
            return h.Ep;
        }
        
        public int describeContents() {
            final iq creator = h.CREATOR;
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
            for (final es.a a : ig.h.Ep.values()) {
                if (this.a((es.a)a)) {
                    if (!h.a((es.a)a)) {
                        return false;
                    }
                    if (!this.b((es.a)a).equals(h.b((es.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (h.a((es.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Deprecated
        public int fN() {
            return 4;
        }
        
        public h fO() {
            return this;
        }
        
        Set<Integer> fa() {
            return this.Eq;
        }
        
        @Override
        public String getLabel() {
            return this.FX;
        }
        
        @Override
        public int getType() {
            return this.os;
        }
        
        @Override
        public String getValue() {
            return this.mValue;
        }
        
        int getVersionCode() {
            return this.kg;
        }
        
        @Override
        public boolean hasLabel() {
            return this.Eq.contains(5);
        }
        
        @Override
        public boolean hasType() {
            return this.Eq.contains(6);
        }
        
        @Override
        public boolean hasValue() {
            return this.Eq.contains(4);
        }
        
        @Override
        public int hashCode() {
            final Iterator<es.a<?, ?>> iterator = h.Ep.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final es.a a = (es.a)iterator.next();
                if (this.a((es.a)a)) {
                    n = this.b((es.a)a).hashCode() + (n + a.cq());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final iq creator = h.CREATOR;
            iq.a(this, parcel, n);
        }
    }
}
