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

public final class ih extends ga implements SafeParcelable, Person
{
    public static final ii CREATOR;
    private static final HashMap<String, ga.a<?, ?>> UI;
    private String HA;
    private final Set<Integer> UJ;
    private String VH;
    private a VI;
    private String VJ;
    private String VK;
    private int VL;
    private b VM;
    private String VN;
    private c VO;
    private boolean VP;
    private String VQ;
    private d VR;
    private String VS;
    private int VT;
    private List<f> VU;
    private List<g> VV;
    private int VW;
    private int VX;
    private String VY;
    private List<h> VZ;
    private boolean Wa;
    private int lZ;
    private String ro;
    private String wp;
    private final int xH;
    
    static {
        CREATOR = new ii();
        (UI = new HashMap<String, ga.a<?, ?>>()).put("aboutMe", ga.a.j("aboutMe", 2));
        ih.UI.put("ageRange", ga.a.a("ageRange", 3, (Class<?>)a.class));
        ih.UI.put("birthday", (ga.a<?, ?>)ga.a.j("birthday", 4));
        ih.UI.put("braggingRights", (ga.a<?, ?>)ga.a.j("braggingRights", 5));
        ih.UI.put("circledByCount", (ga.a<?, ?>)ga.a.g("circledByCount", 6));
        ih.UI.put("cover", ga.a.a("cover", 7, (Class<?>)b.class));
        ih.UI.put("currentLocation", (ga.a<?, ?>)ga.a.j("currentLocation", 8));
        ih.UI.put("displayName", (ga.a<?, ?>)ga.a.j("displayName", 9));
        ih.UI.put("gender", (ga.a<?, ?>)ga.a.a("gender", 12, new fx().f("male", 0).f("female", 1).f("other", 2), false));
        ih.UI.put("id", (ga.a<?, ?>)ga.a.j("id", 14));
        ih.UI.put("image", ga.a.a("image", 15, (Class<?>)c.class));
        ih.UI.put("isPlusUser", (ga.a<?, ?>)ga.a.i("isPlusUser", 16));
        ih.UI.put("language", (ga.a<?, ?>)ga.a.j("language", 18));
        ih.UI.put("name", ga.a.a("name", 19, (Class<?>)d.class));
        ih.UI.put("nickname", (ga.a<?, ?>)ga.a.j("nickname", 20));
        ih.UI.put("objectType", (ga.a<?, ?>)ga.a.a("objectType", 21, new fx().f("person", 0).f("page", 1), false));
        ih.UI.put("organizations", (ga.a<?, ?>)ga.a.b("organizations", 22, f.class));
        ih.UI.put("placesLived", (ga.a<?, ?>)ga.a.b("placesLived", 23, g.class));
        ih.UI.put("plusOneCount", (ga.a<?, ?>)ga.a.g("plusOneCount", 24));
        ih.UI.put("relationshipStatus", (ga.a<?, ?>)ga.a.a("relationshipStatus", 25, new fx().f("single", 0).f("in_a_relationship", 1).f("engaged", 2).f("married", 3).f("its_complicated", 4).f("open_relationship", 5).f("widowed", 6).f("in_domestic_partnership", 7).f("in_civil_union", 8), false));
        ih.UI.put("tagline", (ga.a<?, ?>)ga.a.j("tagline", 26));
        ih.UI.put("url", (ga.a<?, ?>)ga.a.j("url", 27));
        ih.UI.put("urls", (ga.a<?, ?>)ga.a.b("urls", 28, h.class));
        ih.UI.put("verified", (ga.a<?, ?>)ga.a.i("verified", 29));
    }
    
    public ih() {
        this.xH = 2;
        this.UJ = new HashSet<Integer>();
    }
    
    public ih(final String ha, final String wp, final c vo, final int vt, final String ro) {
        this.xH = 2;
        this.UJ = new HashSet<Integer>();
        this.HA = ha;
        this.UJ.add(9);
        this.wp = wp;
        this.UJ.add(14);
        this.VO = vo;
        this.UJ.add(15);
        this.VT = vt;
        this.UJ.add(21);
        this.ro = ro;
        this.UJ.add(27);
    }
    
    ih(final Set<Integer> uj, final int xh, final String vh, final a vi, final String vj, final String vk, final int vl, final b vm, final String vn, final String ha, final int lz, final String wp, final c vo, final boolean vp, final String vq, final d vr, final String vs, final int vt, final List<f> vu, final List<g> vv, final int vw, final int vx, final String vy, final String ro, final List<h> vz, final boolean wa) {
        this.UJ = uj;
        this.xH = xh;
        this.VH = vh;
        this.VI = vi;
        this.VJ = vj;
        this.VK = vk;
        this.VL = vl;
        this.VM = vm;
        this.VN = vn;
        this.HA = ha;
        this.lZ = lz;
        this.wp = wp;
        this.VO = vo;
        this.VP = vp;
        this.VQ = vq;
        this.VR = vr;
        this.VS = vs;
        this.VT = vt;
        this.VU = vu;
        this.VV = vv;
        this.VW = vw;
        this.VX = vx;
        this.VY = vy;
        this.ro = ro;
        this.VZ = vz;
        this.Wa = wa;
    }
    
    public static ih i(final byte[] array) {
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final ih an = ih.CREATOR.aN(obtain);
        obtain.recycle();
        return an;
    }
    
    @Override
    protected boolean a(final ga.a a) {
        return this.UJ.contains(a.ff());
    }
    
    @Override
    protected Object aq(final String s) {
        return null;
    }
    
    @Override
    protected boolean ar(final String s) {
        return false;
    }
    
    @Override
    protected Object b(final ga.a a) {
        switch (a.ff()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
            }
            case 2: {
                return this.VH;
            }
            case 3: {
                return this.VI;
            }
            case 4: {
                return this.VJ;
            }
            case 5: {
                return this.VK;
            }
            case 6: {
                return this.VL;
            }
            case 7: {
                return this.VM;
            }
            case 8: {
                return this.VN;
            }
            case 9: {
                return this.HA;
            }
            case 12: {
                return this.lZ;
            }
            case 14: {
                return this.wp;
            }
            case 15: {
                return this.VO;
            }
            case 16: {
                return this.VP;
            }
            case 18: {
                return this.VQ;
            }
            case 19: {
                return this.VR;
            }
            case 20: {
                return this.VS;
            }
            case 21: {
                return this.VT;
            }
            case 22: {
                return this.VU;
            }
            case 23: {
                return this.VV;
            }
            case 24: {
                return this.VW;
            }
            case 25: {
                return this.VX;
            }
            case 26: {
                return this.VY;
            }
            case 27: {
                return this.ro;
            }
            case 28: {
                return this.VZ;
            }
            case 29: {
                return this.Wa;
            }
        }
    }
    
    public int describeContents() {
        final ii creator = ih.CREATOR;
        return 0;
    }
    
    @Override
    public HashMap<String, ga.a<?, ?>> eY() {
        return ih.UI;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ih)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ih ih = (ih)o;
        for (final ga.a a : com.google.android.gms.internal.ih.UI.values()) {
            if (this.a((ga.a)a)) {
                if (!ih.a((ga.a)a)) {
                    return false;
                }
                if (!this.b((ga.a)a).equals(ih.b((ga.a)a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ih.a((ga.a)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public String getAboutMe() {
        return this.VH;
    }
    
    @Override
    public AgeRange getAgeRange() {
        return this.VI;
    }
    
    @Override
    public String getBirthday() {
        return this.VJ;
    }
    
    @Override
    public String getBraggingRights() {
        return this.VK;
    }
    
    @Override
    public int getCircledByCount() {
        return this.VL;
    }
    
    @Override
    public Cover getCover() {
        return this.VM;
    }
    
    @Override
    public String getCurrentLocation() {
        return this.VN;
    }
    
    @Override
    public String getDisplayName() {
        return this.HA;
    }
    
    @Override
    public int getGender() {
        return this.lZ;
    }
    
    @Override
    public String getId() {
        return this.wp;
    }
    
    @Override
    public Image getImage() {
        return this.VO;
    }
    
    @Override
    public String getLanguage() {
        return this.VQ;
    }
    
    @Override
    public Name getName() {
        return this.VR;
    }
    
    @Override
    public String getNickname() {
        return this.VS;
    }
    
    @Override
    public int getObjectType() {
        return this.VT;
    }
    
    @Override
    public List<Organizations> getOrganizations() {
        return (List<Organizations>)(ArrayList)this.VU;
    }
    
    @Override
    public List<PlacesLived> getPlacesLived() {
        return (List<PlacesLived>)(ArrayList)this.VV;
    }
    
    @Override
    public int getPlusOneCount() {
        return this.VW;
    }
    
    @Override
    public int getRelationshipStatus() {
        return this.VX;
    }
    
    @Override
    public String getTagline() {
        return this.VY;
    }
    
    @Override
    public String getUrl() {
        return this.ro;
    }
    
    @Override
    public List<Urls> getUrls() {
        return (List<Urls>)(ArrayList)this.VZ;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public boolean hasAboutMe() {
        return this.UJ.contains(2);
    }
    
    @Override
    public boolean hasAgeRange() {
        return this.UJ.contains(3);
    }
    
    @Override
    public boolean hasBirthday() {
        return this.UJ.contains(4);
    }
    
    @Override
    public boolean hasBraggingRights() {
        return this.UJ.contains(5);
    }
    
    @Override
    public boolean hasCircledByCount() {
        return this.UJ.contains(6);
    }
    
    @Override
    public boolean hasCover() {
        return this.UJ.contains(7);
    }
    
    @Override
    public boolean hasCurrentLocation() {
        return this.UJ.contains(8);
    }
    
    @Override
    public boolean hasDisplayName() {
        return this.UJ.contains(9);
    }
    
    @Override
    public boolean hasGender() {
        return this.UJ.contains(12);
    }
    
    @Override
    public boolean hasId() {
        return this.UJ.contains(14);
    }
    
    @Override
    public boolean hasImage() {
        return this.UJ.contains(15);
    }
    
    @Override
    public boolean hasIsPlusUser() {
        return this.UJ.contains(16);
    }
    
    @Override
    public boolean hasLanguage() {
        return this.UJ.contains(18);
    }
    
    @Override
    public boolean hasName() {
        return this.UJ.contains(19);
    }
    
    @Override
    public boolean hasNickname() {
        return this.UJ.contains(20);
    }
    
    @Override
    public boolean hasObjectType() {
        return this.UJ.contains(21);
    }
    
    @Override
    public boolean hasOrganizations() {
        return this.UJ.contains(22);
    }
    
    @Override
    public boolean hasPlacesLived() {
        return this.UJ.contains(23);
    }
    
    @Override
    public boolean hasPlusOneCount() {
        return this.UJ.contains(24);
    }
    
    @Override
    public boolean hasRelationshipStatus() {
        return this.UJ.contains(25);
    }
    
    @Override
    public boolean hasTagline() {
        return this.UJ.contains(26);
    }
    
    @Override
    public boolean hasUrl() {
        return this.UJ.contains(27);
    }
    
    @Override
    public boolean hasUrls() {
        return this.UJ.contains(28);
    }
    
    @Override
    public boolean hasVerified() {
        return this.UJ.contains(29);
    }
    
    @Override
    public int hashCode() {
        final Iterator<ga.a<?, ?>> iterator = ih.UI.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final ga.a a = (ga.a)iterator.next();
            if (this.a((ga.a)a)) {
                n = this.b((ga.a)a).hashCode() + (n + a.ff());
            }
        }
        return n;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public boolean isPlusUser() {
        return this.VP;
    }
    
    @Override
    public boolean isVerified() {
        return this.Wa;
    }
    
    List<g> jA() {
        return this.VV;
    }
    
    List<h> jB() {
        return this.VZ;
    }
    
    public ih jC() {
        return this;
    }
    
    Set<Integer> ja() {
        return this.UJ;
    }
    
    a jv() {
        return this.VI;
    }
    
    b jw() {
        return this.VM;
    }
    
    c jx() {
        return this.VO;
    }
    
    d jy() {
        return this.VR;
    }
    
    List<f> jz() {
        return this.VU;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ii creator = ih.CREATOR;
        ii.a(this, parcel, n);
    }
    
    public static final class a extends ga implements SafeParcelable, AgeRange
    {
        public static final ij CREATOR;
        private static final HashMap<String, ga.a<?, ?>> UI;
        private final Set<Integer> UJ;
        private int Wb;
        private int Wc;
        private final int xH;
        
        static {
            CREATOR = new ij();
            (UI = new HashMap<String, ga.a<?, ?>>()).put("max", ga.a.g("max", 2));
            a.UI.put("min", (ga.a<?, ?>)ga.a.g("min", 3));
        }
        
        public a() {
            this.xH = 1;
            this.UJ = new HashSet<Integer>();
        }
        
        a(final Set<Integer> uj, final int xh, final int wb, final int wc) {
            this.UJ = uj;
            this.xH = xh;
            this.Wb = wb;
            this.Wc = wc;
        }
        
        @Override
        protected boolean a(final ga.a a) {
            return this.UJ.contains(a.ff());
        }
        
        @Override
        protected Object aq(final String s) {
            return null;
        }
        
        @Override
        protected boolean ar(final String s) {
            return false;
        }
        
        @Override
        protected Object b(final ga.a a) {
            switch (a.ff()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                }
                case 2: {
                    return this.Wb;
                }
                case 3: {
                    return this.Wc;
                }
            }
        }
        
        public int describeContents() {
            final ij creator = a.CREATOR;
            return 0;
        }
        
        @Override
        public HashMap<String, ga.a<?, ?>> eY() {
            return a.UI;
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
            for (final ga.a a2 : ih.a.UI.values()) {
                if (this.a((ga.a)a2)) {
                    if (!a.a((ga.a)a2)) {
                        return false;
                    }
                    if (!this.b((ga.a)a2).equals(a.b((ga.a)a2))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (a.a((ga.a)a2)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public int getMax() {
            return this.Wb;
        }
        
        @Override
        public int getMin() {
            return this.Wc;
        }
        
        int getVersionCode() {
            return this.xH;
        }
        
        @Override
        public boolean hasMax() {
            return this.UJ.contains(2);
        }
        
        @Override
        public boolean hasMin() {
            return this.UJ.contains(3);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ga.a<?, ?>> iterator = a.UI.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ga.a a = (ga.a)iterator.next();
                if (this.a((ga.a)a)) {
                    n = this.b((ga.a)a).hashCode() + (n + a.ff());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        public a jD() {
            return this;
        }
        
        Set<Integer> ja() {
            return this.UJ;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ij creator = a.CREATOR;
            ij.a(this, parcel, n);
        }
    }
    
    public static final class b extends ga implements SafeParcelable, Cover
    {
        public static final ik CREATOR;
        private static final HashMap<String, ga.a<?, ?>> UI;
        private final Set<Integer> UJ;
        private a Wd;
        private ih.b.b We;
        private int Wf;
        private final int xH;
        
        static {
            CREATOR = new ik();
            (UI = new HashMap<String, ga.a<?, ?>>()).put("coverInfo", ga.a.a("coverInfo", 2, (Class<?>)a.class));
            ih.b.UI.put("coverPhoto", ga.a.a("coverPhoto", 3, (Class<?>)ih.b.b.class));
            ih.b.UI.put("layout", (ga.a<?, ?>)ga.a.a("layout", 4, new fx().f("banner", 0), false));
        }
        
        public b() {
            this.xH = 1;
            this.UJ = new HashSet<Integer>();
        }
        
        b(final Set<Integer> uj, final int xh, final a wd, final ih.b.b we, final int wf) {
            this.UJ = uj;
            this.xH = xh;
            this.Wd = wd;
            this.We = we;
            this.Wf = wf;
        }
        
        @Override
        protected boolean a(final ga.a a) {
            return this.UJ.contains(a.ff());
        }
        
        @Override
        protected Object aq(final String s) {
            return null;
        }
        
        @Override
        protected boolean ar(final String s) {
            return false;
        }
        
        @Override
        protected Object b(final ga.a a) {
            switch (a.ff()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                }
                case 2: {
                    return this.Wd;
                }
                case 3: {
                    return this.We;
                }
                case 4: {
                    return this.Wf;
                }
            }
        }
        
        public int describeContents() {
            final ik creator = ih.b.CREATOR;
            return 0;
        }
        
        @Override
        public HashMap<String, ga.a<?, ?>> eY() {
            return ih.b.UI;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof ih.b)) {
                return false;
            }
            if (this == o) {
                return true;
            }
            final ih.b b = (ih.b)o;
            for (final ga.a a : ih.b.UI.values()) {
                if (this.a((ga.a)a)) {
                    if (!b.a((ga.a)a)) {
                        return false;
                    }
                    if (!this.b((ga.a)a).equals(b.b((ga.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (b.a((ga.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public CoverInfo getCoverInfo() {
            return this.Wd;
        }
        
        @Override
        public CoverPhoto getCoverPhoto() {
            return this.We;
        }
        
        @Override
        public int getLayout() {
            return this.Wf;
        }
        
        int getVersionCode() {
            return this.xH;
        }
        
        @Override
        public boolean hasCoverInfo() {
            return this.UJ.contains(2);
        }
        
        @Override
        public boolean hasCoverPhoto() {
            return this.UJ.contains(3);
        }
        
        @Override
        public boolean hasLayout() {
            return this.UJ.contains(4);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ga.a<?, ?>> iterator = ih.b.UI.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ga.a a = (ga.a)iterator.next();
                if (this.a((ga.a)a)) {
                    n = this.b((ga.a)a).hashCode() + (n + a.ff());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        a jE() {
            return this.Wd;
        }
        
        ih.b.b jF() {
            return this.We;
        }
        
        public ih.b jG() {
            return this;
        }
        
        Set<Integer> ja() {
            return this.UJ;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ik creator = ih.b.CREATOR;
            ik.a(this, parcel, n);
        }
        
        public static final class a extends ga implements SafeParcelable, CoverInfo
        {
            public static final il CREATOR;
            private static final HashMap<String, ga.a<?, ?>> UI;
            private final Set<Integer> UJ;
            private int Wg;
            private int Wh;
            private final int xH;
            
            static {
                CREATOR = new il();
                (UI = new HashMap<String, ga.a<?, ?>>()).put("leftImageOffset", ga.a.g("leftImageOffset", 2));
                a.UI.put("topImageOffset", (ga.a<?, ?>)ga.a.g("topImageOffset", 3));
            }
            
            public a() {
                this.xH = 1;
                this.UJ = new HashSet<Integer>();
            }
            
            a(final Set<Integer> uj, final int xh, final int wg, final int wh) {
                this.UJ = uj;
                this.xH = xh;
                this.Wg = wg;
                this.Wh = wh;
            }
            
            @Override
            protected boolean a(final ga.a a) {
                return this.UJ.contains(a.ff());
            }
            
            @Override
            protected Object aq(final String s) {
                return null;
            }
            
            @Override
            protected boolean ar(final String s) {
                return false;
            }
            
            @Override
            protected Object b(final ga.a a) {
                switch (a.ff()) {
                    default: {
                        throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                    }
                    case 2: {
                        return this.Wg;
                    }
                    case 3: {
                        return this.Wh;
                    }
                }
            }
            
            public int describeContents() {
                final il creator = a.CREATOR;
                return 0;
            }
            
            @Override
            public HashMap<String, ga.a<?, ?>> eY() {
                return a.UI;
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
                for (final ga.a a2 : ih.b.a.UI.values()) {
                    if (this.a((ga.a)a2)) {
                        if (!a.a((ga.a)a2)) {
                            return false;
                        }
                        if (!this.b((ga.a)a2).equals(a.b((ga.a)a2))) {
                            return false;
                        }
                        continue;
                    }
                    else {
                        if (a.a((ga.a)a2)) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
            
            @Override
            public int getLeftImageOffset() {
                return this.Wg;
            }
            
            @Override
            public int getTopImageOffset() {
                return this.Wh;
            }
            
            int getVersionCode() {
                return this.xH;
            }
            
            @Override
            public boolean hasLeftImageOffset() {
                return this.UJ.contains(2);
            }
            
            @Override
            public boolean hasTopImageOffset() {
                return this.UJ.contains(3);
            }
            
            @Override
            public int hashCode() {
                final Iterator<ga.a<?, ?>> iterator = a.UI.values().iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    final ga.a a = (ga.a)iterator.next();
                    if (this.a((ga.a)a)) {
                        n = this.b((ga.a)a).hashCode() + (n + a.ff());
                    }
                }
                return n;
            }
            
            public boolean isDataValid() {
                return true;
            }
            
            public a jH() {
                return this;
            }
            
            Set<Integer> ja() {
                return this.UJ;
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                final il creator = a.CREATOR;
                il.a(this, parcel, n);
            }
        }
        
        public static final class b extends ga implements SafeParcelable, CoverPhoto
        {
            public static final im CREATOR;
            private static final HashMap<String, ga.a<?, ?>> UI;
            private final Set<Integer> UJ;
            private int kr;
            private int ks;
            private String ro;
            private final int xH;
            
            static {
                CREATOR = new im();
                (UI = new HashMap<String, ga.a<?, ?>>()).put("height", ga.a.g("height", 2));
                b.UI.put("url", (ga.a<?, ?>)ga.a.j("url", 3));
                b.UI.put("width", (ga.a<?, ?>)ga.a.g("width", 4));
            }
            
            public b() {
                this.xH = 1;
                this.UJ = new HashSet<Integer>();
            }
            
            b(final Set<Integer> uj, final int xh, final int ks, final String ro, final int kr) {
                this.UJ = uj;
                this.xH = xh;
                this.ks = ks;
                this.ro = ro;
                this.kr = kr;
            }
            
            @Override
            protected boolean a(final ga.a a) {
                return this.UJ.contains(a.ff());
            }
            
            @Override
            protected Object aq(final String s) {
                return null;
            }
            
            @Override
            protected boolean ar(final String s) {
                return false;
            }
            
            @Override
            protected Object b(final ga.a a) {
                switch (a.ff()) {
                    default: {
                        throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                    }
                    case 2: {
                        return this.ks;
                    }
                    case 3: {
                        return this.ro;
                    }
                    case 4: {
                        return this.kr;
                    }
                }
            }
            
            public int describeContents() {
                final im creator = b.CREATOR;
                return 0;
            }
            
            @Override
            public HashMap<String, ga.a<?, ?>> eY() {
                return b.UI;
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
                for (final ga.a a : ih.b.b.UI.values()) {
                    if (this.a((ga.a)a)) {
                        if (!b.a((ga.a)a)) {
                            return false;
                        }
                        if (!this.b((ga.a)a).equals(b.b((ga.a)a))) {
                            return false;
                        }
                        continue;
                    }
                    else {
                        if (b.a((ga.a)a)) {
                            return false;
                        }
                        continue;
                    }
                }
                return true;
            }
            
            @Override
            public int getHeight() {
                return this.ks;
            }
            
            @Override
            public String getUrl() {
                return this.ro;
            }
            
            int getVersionCode() {
                return this.xH;
            }
            
            @Override
            public int getWidth() {
                return this.kr;
            }
            
            @Override
            public boolean hasHeight() {
                return this.UJ.contains(2);
            }
            
            @Override
            public boolean hasUrl() {
                return this.UJ.contains(3);
            }
            
            @Override
            public boolean hasWidth() {
                return this.UJ.contains(4);
            }
            
            @Override
            public int hashCode() {
                final Iterator<ga.a<?, ?>> iterator = b.UI.values().iterator();
                int n = 0;
                while (iterator.hasNext()) {
                    final ga.a a = (ga.a)iterator.next();
                    if (this.a((ga.a)a)) {
                        n = this.b((ga.a)a).hashCode() + (n + a.ff());
                    }
                }
                return n;
            }
            
            public boolean isDataValid() {
                return true;
            }
            
            public b jI() {
                return this;
            }
            
            Set<Integer> ja() {
                return this.UJ;
            }
            
            public void writeToParcel(final Parcel parcel, final int n) {
                final im creator = b.CREATOR;
                im.a(this, parcel, n);
            }
        }
    }
    
    public static final class c extends ga implements SafeParcelable, Image
    {
        public static final in CREATOR;
        private static final HashMap<String, ga.a<?, ?>> UI;
        private final Set<Integer> UJ;
        private String ro;
        private final int xH;
        
        static {
            CREATOR = new in();
            (UI = new HashMap<String, ga.a<?, ?>>()).put("url", ga.a.j("url", 2));
        }
        
        public c() {
            this.xH = 1;
            this.UJ = new HashSet<Integer>();
        }
        
        public c(final String ro) {
            this.UJ = new HashSet<Integer>();
            this.xH = 1;
            this.ro = ro;
            this.UJ.add(2);
        }
        
        c(final Set<Integer> uj, final int xh, final String ro) {
            this.UJ = uj;
            this.xH = xh;
            this.ro = ro;
        }
        
        @Override
        protected boolean a(final ga.a a) {
            return this.UJ.contains(a.ff());
        }
        
        @Override
        protected Object aq(final String s) {
            return null;
        }
        
        @Override
        protected boolean ar(final String s) {
            return false;
        }
        
        @Override
        protected Object b(final ga.a a) {
            switch (a.ff()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                }
                case 2: {
                    return this.ro;
                }
            }
        }
        
        public int describeContents() {
            final in creator = c.CREATOR;
            return 0;
        }
        
        @Override
        public HashMap<String, ga.a<?, ?>> eY() {
            return c.UI;
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
            for (final ga.a a : ih.c.UI.values()) {
                if (this.a((ga.a)a)) {
                    if (!c.a((ga.a)a)) {
                        return false;
                    }
                    if (!this.b((ga.a)a).equals(c.b((ga.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (c.a((ga.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getUrl() {
            return this.ro;
        }
        
        int getVersionCode() {
            return this.xH;
        }
        
        @Override
        public boolean hasUrl() {
            return this.UJ.contains(2);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ga.a<?, ?>> iterator = c.UI.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ga.a a = (ga.a)iterator.next();
                if (this.a((ga.a)a)) {
                    n = this.b((ga.a)a).hashCode() + (n + a.ff());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        public c jJ() {
            return this;
        }
        
        Set<Integer> ja() {
            return this.UJ;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final in creator = c.CREATOR;
            in.a(this, parcel, n);
        }
    }
    
    public static final class d extends ga implements SafeParcelable, Name
    {
        public static final io CREATOR;
        private static final HashMap<String, ga.a<?, ?>> UI;
        private final Set<Integer> UJ;
        private String Vh;
        private String Vk;
        private String Wi;
        private String Wj;
        private String Wk;
        private String Wl;
        private final int xH;
        
        static {
            CREATOR = new io();
            (UI = new HashMap<String, ga.a<?, ?>>()).put("familyName", ga.a.j("familyName", 2));
            d.UI.put("formatted", (ga.a<?, ?>)ga.a.j("formatted", 3));
            d.UI.put("givenName", (ga.a<?, ?>)ga.a.j("givenName", 4));
            d.UI.put("honorificPrefix", (ga.a<?, ?>)ga.a.j("honorificPrefix", 5));
            d.UI.put("honorificSuffix", (ga.a<?, ?>)ga.a.j("honorificSuffix", 6));
            d.UI.put("middleName", (ga.a<?, ?>)ga.a.j("middleName", 7));
        }
        
        public d() {
            this.xH = 1;
            this.UJ = new HashSet<Integer>();
        }
        
        d(final Set<Integer> uj, final int xh, final String vh, final String wi, final String vk, final String wj, final String wk, final String wl) {
            this.UJ = uj;
            this.xH = xh;
            this.Vh = vh;
            this.Wi = wi;
            this.Vk = vk;
            this.Wj = wj;
            this.Wk = wk;
            this.Wl = wl;
        }
        
        @Override
        protected boolean a(final ga.a a) {
            return this.UJ.contains(a.ff());
        }
        
        @Override
        protected Object aq(final String s) {
            return null;
        }
        
        @Override
        protected boolean ar(final String s) {
            return false;
        }
        
        @Override
        protected Object b(final ga.a a) {
            switch (a.ff()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                }
                case 2: {
                    return this.Vh;
                }
                case 3: {
                    return this.Wi;
                }
                case 4: {
                    return this.Vk;
                }
                case 5: {
                    return this.Wj;
                }
                case 6: {
                    return this.Wk;
                }
                case 7: {
                    return this.Wl;
                }
            }
        }
        
        public int describeContents() {
            final io creator = d.CREATOR;
            return 0;
        }
        
        @Override
        public HashMap<String, ga.a<?, ?>> eY() {
            return d.UI;
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
            for (final ga.a a : ih.d.UI.values()) {
                if (this.a((ga.a)a)) {
                    if (!d.a((ga.a)a)) {
                        return false;
                    }
                    if (!this.b((ga.a)a).equals(d.b((ga.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (d.a((ga.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getFamilyName() {
            return this.Vh;
        }
        
        @Override
        public String getFormatted() {
            return this.Wi;
        }
        
        @Override
        public String getGivenName() {
            return this.Vk;
        }
        
        @Override
        public String getHonorificPrefix() {
            return this.Wj;
        }
        
        @Override
        public String getHonorificSuffix() {
            return this.Wk;
        }
        
        @Override
        public String getMiddleName() {
            return this.Wl;
        }
        
        int getVersionCode() {
            return this.xH;
        }
        
        @Override
        public boolean hasFamilyName() {
            return this.UJ.contains(2);
        }
        
        @Override
        public boolean hasFormatted() {
            return this.UJ.contains(3);
        }
        
        @Override
        public boolean hasGivenName() {
            return this.UJ.contains(4);
        }
        
        @Override
        public boolean hasHonorificPrefix() {
            return this.UJ.contains(5);
        }
        
        @Override
        public boolean hasHonorificSuffix() {
            return this.UJ.contains(6);
        }
        
        @Override
        public boolean hasMiddleName() {
            return this.UJ.contains(7);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ga.a<?, ?>> iterator = d.UI.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ga.a a = (ga.a)iterator.next();
                if (this.a((ga.a)a)) {
                    n = this.b((ga.a)a).hashCode() + (n + a.ff());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        public d jK() {
            return this;
        }
        
        Set<Integer> ja() {
            return this.UJ;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final io creator = d.CREATOR;
            io.a(this, parcel, n);
        }
    }
    
    public static class e
    {
        public static int bi(final String s) {
            if (s.equals("person")) {
                return 0;
            }
            if (s.equals("page")) {
                return 1;
            }
            throw new IllegalArgumentException("Unknown objectType string: " + s);
        }
    }
    
    public static final class f extends ga implements SafeParcelable, Organizations
    {
        public static final ip CREATOR;
        private static final HashMap<String, ga.a<?, ?>> UI;
        private String EB;
        private String HD;
        private int LF;
        private final Set<Integer> UJ;
        private String Vg;
        private String Vw;
        private String Wm;
        private String Wn;
        private boolean Wo;
        private String mName;
        private final int xH;
        
        static {
            CREATOR = new ip();
            (UI = new HashMap<String, ga.a<?, ?>>()).put("department", ga.a.j("department", 2));
            f.UI.put("description", (ga.a<?, ?>)ga.a.j("description", 3));
            f.UI.put("endDate", (ga.a<?, ?>)ga.a.j("endDate", 4));
            f.UI.put("location", (ga.a<?, ?>)ga.a.j("location", 5));
            f.UI.put("name", (ga.a<?, ?>)ga.a.j("name", 6));
            f.UI.put("primary", (ga.a<?, ?>)ga.a.i("primary", 7));
            f.UI.put("startDate", (ga.a<?, ?>)ga.a.j("startDate", 8));
            f.UI.put("title", (ga.a<?, ?>)ga.a.j("title", 9));
            f.UI.put("type", (ga.a<?, ?>)ga.a.a("type", 10, new fx().f("work", 0).f("school", 1), false));
        }
        
        public f() {
            this.xH = 1;
            this.UJ = new HashSet<Integer>();
        }
        
        f(final Set<Integer> uj, final int xh, final String wm, final String hd, final String vg, final String wn, final String mName, final boolean wo, final String vw, final String eb, final int lf) {
            this.UJ = uj;
            this.xH = xh;
            this.Wm = wm;
            this.HD = hd;
            this.Vg = vg;
            this.Wn = wn;
            this.mName = mName;
            this.Wo = wo;
            this.Vw = vw;
            this.EB = eb;
            this.LF = lf;
        }
        
        @Override
        protected boolean a(final ga.a a) {
            return this.UJ.contains(a.ff());
        }
        
        @Override
        protected Object aq(final String s) {
            return null;
        }
        
        @Override
        protected boolean ar(final String s) {
            return false;
        }
        
        @Override
        protected Object b(final ga.a a) {
            switch (a.ff()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                }
                case 2: {
                    return this.Wm;
                }
                case 3: {
                    return this.HD;
                }
                case 4: {
                    return this.Vg;
                }
                case 5: {
                    return this.Wn;
                }
                case 6: {
                    return this.mName;
                }
                case 7: {
                    return this.Wo;
                }
                case 8: {
                    return this.Vw;
                }
                case 9: {
                    return this.EB;
                }
                case 10: {
                    return this.LF;
                }
            }
        }
        
        public int describeContents() {
            final ip creator = f.CREATOR;
            return 0;
        }
        
        @Override
        public HashMap<String, ga.a<?, ?>> eY() {
            return f.UI;
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
            for (final ga.a a : ih.f.UI.values()) {
                if (this.a((ga.a)a)) {
                    if (!f.a((ga.a)a)) {
                        return false;
                    }
                    if (!this.b((ga.a)a).equals(f.b((ga.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (f.a((ga.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getDepartment() {
            return this.Wm;
        }
        
        @Override
        public String getDescription() {
            return this.HD;
        }
        
        @Override
        public String getEndDate() {
            return this.Vg;
        }
        
        @Override
        public String getLocation() {
            return this.Wn;
        }
        
        @Override
        public String getName() {
            return this.mName;
        }
        
        @Override
        public String getStartDate() {
            return this.Vw;
        }
        
        @Override
        public String getTitle() {
            return this.EB;
        }
        
        @Override
        public int getType() {
            return this.LF;
        }
        
        int getVersionCode() {
            return this.xH;
        }
        
        @Override
        public boolean hasDepartment() {
            return this.UJ.contains(2);
        }
        
        @Override
        public boolean hasDescription() {
            return this.UJ.contains(3);
        }
        
        @Override
        public boolean hasEndDate() {
            return this.UJ.contains(4);
        }
        
        @Override
        public boolean hasLocation() {
            return this.UJ.contains(5);
        }
        
        @Override
        public boolean hasName() {
            return this.UJ.contains(6);
        }
        
        @Override
        public boolean hasPrimary() {
            return this.UJ.contains(7);
        }
        
        @Override
        public boolean hasStartDate() {
            return this.UJ.contains(8);
        }
        
        @Override
        public boolean hasTitle() {
            return this.UJ.contains(9);
        }
        
        @Override
        public boolean hasType() {
            return this.UJ.contains(10);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ga.a<?, ?>> iterator = f.UI.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ga.a a = (ga.a)iterator.next();
                if (this.a((ga.a)a)) {
                    n = this.b((ga.a)a).hashCode() + (n + a.ff());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        @Override
        public boolean isPrimary() {
            return this.Wo;
        }
        
        public f jL() {
            return this;
        }
        
        Set<Integer> ja() {
            return this.UJ;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ip creator = f.CREATOR;
            ip.a(this, parcel, n);
        }
    }
    
    public static final class g extends ga implements SafeParcelable, PlacesLived
    {
        public static final iq CREATOR;
        private static final HashMap<String, ga.a<?, ?>> UI;
        private final Set<Integer> UJ;
        private boolean Wo;
        private String mValue;
        private final int xH;
        
        static {
            CREATOR = new iq();
            (UI = new HashMap<String, ga.a<?, ?>>()).put("primary", ga.a.i("primary", 2));
            g.UI.put("value", (ga.a<?, ?>)ga.a.j("value", 3));
        }
        
        public g() {
            this.xH = 1;
            this.UJ = new HashSet<Integer>();
        }
        
        g(final Set<Integer> uj, final int xh, final boolean wo, final String mValue) {
            this.UJ = uj;
            this.xH = xh;
            this.Wo = wo;
            this.mValue = mValue;
        }
        
        @Override
        protected boolean a(final ga.a a) {
            return this.UJ.contains(a.ff());
        }
        
        @Override
        protected Object aq(final String s) {
            return null;
        }
        
        @Override
        protected boolean ar(final String s) {
            return false;
        }
        
        @Override
        protected Object b(final ga.a a) {
            switch (a.ff()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                }
                case 2: {
                    return this.Wo;
                }
                case 3: {
                    return this.mValue;
                }
            }
        }
        
        public int describeContents() {
            final iq creator = g.CREATOR;
            return 0;
        }
        
        @Override
        public HashMap<String, ga.a<?, ?>> eY() {
            return g.UI;
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
            for (final ga.a a : ih.g.UI.values()) {
                if (this.a((ga.a)a)) {
                    if (!g.a((ga.a)a)) {
                        return false;
                    }
                    if (!this.b((ga.a)a).equals(g.b((ga.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (g.a((ga.a)a)) {
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
        
        int getVersionCode() {
            return this.xH;
        }
        
        @Override
        public boolean hasPrimary() {
            return this.UJ.contains(2);
        }
        
        @Override
        public boolean hasValue() {
            return this.UJ.contains(3);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ga.a<?, ?>> iterator = g.UI.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ga.a a = (ga.a)iterator.next();
                if (this.a((ga.a)a)) {
                    n = this.b((ga.a)a).hashCode() + (n + a.ff());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        @Override
        public boolean isPrimary() {
            return this.Wo;
        }
        
        public g jM() {
            return this;
        }
        
        Set<Integer> ja() {
            return this.UJ;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final iq creator = g.CREATOR;
            iq.a(this, parcel, n);
        }
    }
    
    public static final class h extends ga implements SafeParcelable, Urls
    {
        public static final ir CREATOR;
        private static final HashMap<String, ga.a<?, ?>> UI;
        private int LF;
        private final Set<Integer> UJ;
        private String Wp;
        private final int Wq;
        private String mValue;
        private final int xH;
        
        static {
            CREATOR = new ir();
            (UI = new HashMap<String, ga.a<?, ?>>()).put("label", ga.a.j("label", 5));
            h.UI.put("type", (ga.a<?, ?>)ga.a.a("type", 6, new fx().f("home", 0).f("work", 1).f("blog", 2).f("profile", 3).f("other", 4).f("otherProfile", 5).f("contributor", 6).f("website", 7), false));
            h.UI.put("value", (ga.a<?, ?>)ga.a.j("value", 4));
        }
        
        public h() {
            this.Wq = 4;
            this.xH = 2;
            this.UJ = new HashSet<Integer>();
        }
        
        h(final Set<Integer> uj, final int xh, final String wp, final int lf, final String mValue, final int n) {
            this.Wq = 4;
            this.UJ = uj;
            this.xH = xh;
            this.Wp = wp;
            this.LF = lf;
            this.mValue = mValue;
        }
        
        @Override
        protected boolean a(final ga.a a) {
            return this.UJ.contains(a.ff());
        }
        
        @Override
        protected Object aq(final String s) {
            return null;
        }
        
        @Override
        protected boolean ar(final String s) {
            return false;
        }
        
        @Override
        protected Object b(final ga.a a) {
            switch (a.ff()) {
                default: {
                    throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
                }
                case 5: {
                    return this.Wp;
                }
                case 6: {
                    return this.LF;
                }
                case 4: {
                    return this.mValue;
                }
            }
        }
        
        public int describeContents() {
            final ir creator = h.CREATOR;
            return 0;
        }
        
        @Override
        public HashMap<String, ga.a<?, ?>> eY() {
            return h.UI;
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
            for (final ga.a a : ih.h.UI.values()) {
                if (this.a((ga.a)a)) {
                    if (!h.a((ga.a)a)) {
                        return false;
                    }
                    if (!this.b((ga.a)a).equals(h.b((ga.a)a))) {
                        return false;
                    }
                    continue;
                }
                else {
                    if (h.a((ga.a)a)) {
                        return false;
                    }
                    continue;
                }
            }
            return true;
        }
        
        @Override
        public String getLabel() {
            return this.Wp;
        }
        
        @Override
        public int getType() {
            return this.LF;
        }
        
        @Override
        public String getValue() {
            return this.mValue;
        }
        
        int getVersionCode() {
            return this.xH;
        }
        
        @Override
        public boolean hasLabel() {
            return this.UJ.contains(5);
        }
        
        @Override
        public boolean hasType() {
            return this.UJ.contains(6);
        }
        
        @Override
        public boolean hasValue() {
            return this.UJ.contains(4);
        }
        
        @Override
        public int hashCode() {
            final Iterator<ga.a<?, ?>> iterator = h.UI.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final ga.a a = (ga.a)iterator.next();
                if (this.a((ga.a)a)) {
                    n = this.b((ga.a)a).hashCode() + (n + a.ff());
                }
            }
            return n;
        }
        
        public boolean isDataValid() {
            return true;
        }
        
        @Deprecated
        public int jN() {
            return 4;
        }
        
        public h jO() {
            return this;
        }
        
        Set<Integer> ja() {
            return this.UJ;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ir creator = h.CREATOR;
            ir.a(this, parcel, n);
        }
    }
}
