// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ic extends ga implements SafeParcelable, ItemScope
{
    public static final id CREATOR;
    private static final HashMap<String, a<?, ?>> UI;
    private String HD;
    private double NX;
    private double NY;
    private String Rd;
    private final Set<Integer> UJ;
    private ic UK;
    private List<String> UL;
    private ic UM;
    private String UN;
    private String UO;
    private String UP;
    private List<ic> UQ;
    private int UR;
    private List<ic> US;
    private ic UT;
    private List<ic> UU;
    private String UV;
    private String UW;
    private ic UX;
    private String UY;
    private String UZ;
    private String VA;
    private String VB;
    private String VC;
    private String VD;
    private List<ic> Va;
    private String Vb;
    private String Vc;
    private String Vd;
    private String Ve;
    private String Vf;
    private String Vg;
    private String Vh;
    private String Vi;
    private ic Vj;
    private String Vk;
    private String Vl;
    private String Vm;
    private ic Vn;
    private ic Vo;
    private ic Vp;
    private List<ic> Vq;
    private String Vr;
    private String Vs;
    private String Vt;
    private String Vu;
    private ic Vv;
    private String Vw;
    private String Vx;
    private String Vy;
    private ic Vz;
    private String lY;
    private String mName;
    private String ro;
    private String wp;
    private final int xH;
    
    static {
        CREATOR = new id();
        (UI = new HashMap<String, a<?, ?>>()).put("about", a.a("about", 2, (Class<?>)ic.class));
        ic.UI.put("additionalName", (a<?, ?>)a.k("additionalName", 3));
        ic.UI.put("address", a.a("address", 4, (Class<?>)ic.class));
        ic.UI.put("addressCountry", (a<?, ?>)a.j("addressCountry", 5));
        ic.UI.put("addressLocality", (a<?, ?>)a.j("addressLocality", 6));
        ic.UI.put("addressRegion", (a<?, ?>)a.j("addressRegion", 7));
        ic.UI.put("associated_media", (a<?, ?>)a.b("associated_media", 8, ic.class));
        ic.UI.put("attendeeCount", (a<?, ?>)a.g("attendeeCount", 9));
        ic.UI.put("attendees", (a<?, ?>)a.b("attendees", 10, ic.class));
        ic.UI.put("audio", a.a("audio", 11, (Class<?>)ic.class));
        ic.UI.put("author", (a<?, ?>)a.b("author", 12, ic.class));
        ic.UI.put("bestRating", (a<?, ?>)a.j("bestRating", 13));
        ic.UI.put("birthDate", (a<?, ?>)a.j("birthDate", 14));
        ic.UI.put("byArtist", a.a("byArtist", 15, (Class<?>)ic.class));
        ic.UI.put("caption", (a<?, ?>)a.j("caption", 16));
        ic.UI.put("contentSize", (a<?, ?>)a.j("contentSize", 17));
        ic.UI.put("contentUrl", (a<?, ?>)a.j("contentUrl", 18));
        ic.UI.put("contributor", (a<?, ?>)a.b("contributor", 19, ic.class));
        ic.UI.put("dateCreated", (a<?, ?>)a.j("dateCreated", 20));
        ic.UI.put("dateModified", (a<?, ?>)a.j("dateModified", 21));
        ic.UI.put("datePublished", (a<?, ?>)a.j("datePublished", 22));
        ic.UI.put("description", (a<?, ?>)a.j("description", 23));
        ic.UI.put("duration", (a<?, ?>)a.j("duration", 24));
        ic.UI.put("embedUrl", (a<?, ?>)a.j("embedUrl", 25));
        ic.UI.put("endDate", (a<?, ?>)a.j("endDate", 26));
        ic.UI.put("familyName", (a<?, ?>)a.j("familyName", 27));
        ic.UI.put("gender", (a<?, ?>)a.j("gender", 28));
        ic.UI.put("geo", a.a("geo", 29, (Class<?>)ic.class));
        ic.UI.put("givenName", (a<?, ?>)a.j("givenName", 30));
        ic.UI.put("height", (a<?, ?>)a.j("height", 31));
        ic.UI.put("id", (a<?, ?>)a.j("id", 32));
        ic.UI.put("image", (a<?, ?>)a.j("image", 33));
        ic.UI.put("inAlbum", a.a("inAlbum", 34, (Class<?>)ic.class));
        ic.UI.put("latitude", (a<?, ?>)a.h("latitude", 36));
        ic.UI.put("location", a.a("location", 37, (Class<?>)ic.class));
        ic.UI.put("longitude", (a<?, ?>)a.h("longitude", 38));
        ic.UI.put("name", (a<?, ?>)a.j("name", 39));
        ic.UI.put("partOfTVSeries", a.a("partOfTVSeries", 40, (Class<?>)ic.class));
        ic.UI.put("performers", (a<?, ?>)a.b("performers", 41, ic.class));
        ic.UI.put("playerType", (a<?, ?>)a.j("playerType", 42));
        ic.UI.put("postOfficeBoxNumber", (a<?, ?>)a.j("postOfficeBoxNumber", 43));
        ic.UI.put("postalCode", (a<?, ?>)a.j("postalCode", 44));
        ic.UI.put("ratingValue", (a<?, ?>)a.j("ratingValue", 45));
        ic.UI.put("reviewRating", a.a("reviewRating", 46, (Class<?>)ic.class));
        ic.UI.put("startDate", (a<?, ?>)a.j("startDate", 47));
        ic.UI.put("streetAddress", (a<?, ?>)a.j("streetAddress", 48));
        ic.UI.put("text", (a<?, ?>)a.j("text", 49));
        ic.UI.put("thumbnail", a.a("thumbnail", 50, (Class<?>)ic.class));
        ic.UI.put("thumbnailUrl", (a<?, ?>)a.j("thumbnailUrl", 51));
        ic.UI.put("tickerSymbol", (a<?, ?>)a.j("tickerSymbol", 52));
        ic.UI.put("type", (a<?, ?>)a.j("type", 53));
        ic.UI.put("url", (a<?, ?>)a.j("url", 54));
        ic.UI.put("width", (a<?, ?>)a.j("width", 55));
        ic.UI.put("worstRating", (a<?, ?>)a.j("worstRating", 56));
    }
    
    public ic() {
        this.xH = 1;
        this.UJ = new HashSet<Integer>();
    }
    
    ic(final Set<Integer> uj, final int xh, final ic uk, final List<String> ul, final ic um, final String un, final String uo, final String up, final List<ic> uq, final int ur, final List<ic> us, final ic ut, final List<ic> uu, final String uv, final String uw, final ic ux, final String uy, final String uz, final String ly, final List<ic> va, final String vb, final String vc, final String vd, final String hd, final String ve, final String vf, final String vg, final String vh, final String vi, final ic vj, final String vk, final String vl, final String wp, final String vm, final ic vn, final double nx, final ic vo, final double ny, final String mName, final ic vp, final List<ic> vq, final String vr, final String vs, final String vt, final String vu, final ic vv, final String vw, final String vx, final String vy, final ic vz, final String va2, final String vb2, final String rd, final String ro, final String vc2, final String vd2) {
        this.UJ = uj;
        this.xH = xh;
        this.UK = uk;
        this.UL = ul;
        this.UM = um;
        this.UN = un;
        this.UO = uo;
        this.UP = up;
        this.UQ = uq;
        this.UR = ur;
        this.US = us;
        this.UT = ut;
        this.UU = uu;
        this.UV = uv;
        this.UW = uw;
        this.UX = ux;
        this.UY = uy;
        this.UZ = uz;
        this.lY = ly;
        this.Va = va;
        this.Vb = vb;
        this.Vc = vc;
        this.Vd = vd;
        this.HD = hd;
        this.Ve = ve;
        this.Vf = vf;
        this.Vg = vg;
        this.Vh = vh;
        this.Vi = vi;
        this.Vj = vj;
        this.Vk = vk;
        this.Vl = vl;
        this.wp = wp;
        this.Vm = vm;
        this.Vn = vn;
        this.NX = nx;
        this.Vo = vo;
        this.NY = ny;
        this.mName = mName;
        this.Vp = vp;
        this.Vq = vq;
        this.Vr = vr;
        this.Vs = vs;
        this.Vt = vt;
        this.Vu = vu;
        this.Vv = vv;
        this.Vw = vw;
        this.Vx = vx;
        this.Vy = vy;
        this.Vz = vz;
        this.VA = va2;
        this.VB = vb2;
        this.Rd = rd;
        this.ro = ro;
        this.VC = vc2;
        this.VD = vd2;
    }
    
    public ic(final Set<Integer> uj, final ic uk, final List<String> ul, final ic um, final String un, final String uo, final String up, final List<ic> uq, final int ur, final List<ic> us, final ic ut, final List<ic> uu, final String uv, final String uw, final ic ux, final String uy, final String uz, final String ly, final List<ic> va, final String vb, final String vc, final String vd, final String hd, final String ve, final String vf, final String vg, final String vh, final String vi, final ic vj, final String vk, final String vl, final String wp, final String vm, final ic vn, final double nx, final ic vo, final double ny, final String mName, final ic vp, final List<ic> vq, final String vr, final String vs, final String vt, final String vu, final ic vv, final String vw, final String vx, final String vy, final ic vz, final String va2, final String vb2, final String rd, final String ro, final String vc2, final String vd2) {
        this.UJ = uj;
        this.xH = 1;
        this.UK = uk;
        this.UL = ul;
        this.UM = um;
        this.UN = un;
        this.UO = uo;
        this.UP = up;
        this.UQ = uq;
        this.UR = ur;
        this.US = us;
        this.UT = ut;
        this.UU = uu;
        this.UV = uv;
        this.UW = uw;
        this.UX = ux;
        this.UY = uy;
        this.UZ = uz;
        this.lY = ly;
        this.Va = va;
        this.Vb = vb;
        this.Vc = vc;
        this.Vd = vd;
        this.HD = hd;
        this.Ve = ve;
        this.Vf = vf;
        this.Vg = vg;
        this.Vh = vh;
        this.Vi = vi;
        this.Vj = vj;
        this.Vk = vk;
        this.Vl = vl;
        this.wp = wp;
        this.Vm = vm;
        this.Vn = vn;
        this.NX = nx;
        this.Vo = vo;
        this.NY = ny;
        this.mName = mName;
        this.Vp = vp;
        this.Vq = vq;
        this.Vr = vr;
        this.Vs = vs;
        this.Vt = vt;
        this.Vu = vu;
        this.Vv = vv;
        this.Vw = vw;
        this.Vx = vx;
        this.Vy = vy;
        this.Vz = vz;
        this.VA = va2;
        this.VB = vb2;
        this.Rd = rd;
        this.ro = ro;
        this.VC = vc2;
        this.VD = vd2;
    }
    
    @Override
    protected boolean a(final a a) {
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
    protected Object b(final a a) {
        switch (a.ff()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + a.ff());
            }
            case 2: {
                return this.UK;
            }
            case 3: {
                return this.UL;
            }
            case 4: {
                return this.UM;
            }
            case 5: {
                return this.UN;
            }
            case 6: {
                return this.UO;
            }
            case 7: {
                return this.UP;
            }
            case 8: {
                return this.UQ;
            }
            case 9: {
                return this.UR;
            }
            case 10: {
                return this.US;
            }
            case 11: {
                return this.UT;
            }
            case 12: {
                return this.UU;
            }
            case 13: {
                return this.UV;
            }
            case 14: {
                return this.UW;
            }
            case 15: {
                return this.UX;
            }
            case 16: {
                return this.UY;
            }
            case 17: {
                return this.UZ;
            }
            case 18: {
                return this.lY;
            }
            case 19: {
                return this.Va;
            }
            case 20: {
                return this.Vb;
            }
            case 21: {
                return this.Vc;
            }
            case 22: {
                return this.Vd;
            }
            case 23: {
                return this.HD;
            }
            case 24: {
                return this.Ve;
            }
            case 25: {
                return this.Vf;
            }
            case 26: {
                return this.Vg;
            }
            case 27: {
                return this.Vh;
            }
            case 28: {
                return this.Vi;
            }
            case 29: {
                return this.Vj;
            }
            case 30: {
                return this.Vk;
            }
            case 31: {
                return this.Vl;
            }
            case 32: {
                return this.wp;
            }
            case 33: {
                return this.Vm;
            }
            case 34: {
                return this.Vn;
            }
            case 36: {
                return this.NX;
            }
            case 37: {
                return this.Vo;
            }
            case 38: {
                return this.NY;
            }
            case 39: {
                return this.mName;
            }
            case 40: {
                return this.Vp;
            }
            case 41: {
                return this.Vq;
            }
            case 42: {
                return this.Vr;
            }
            case 43: {
                return this.Vs;
            }
            case 44: {
                return this.Vt;
            }
            case 45: {
                return this.Vu;
            }
            case 46: {
                return this.Vv;
            }
            case 47: {
                return this.Vw;
            }
            case 48: {
                return this.Vx;
            }
            case 49: {
                return this.Vy;
            }
            case 50: {
                return this.Vz;
            }
            case 51: {
                return this.VA;
            }
            case 52: {
                return this.VB;
            }
            case 53: {
                return this.Rd;
            }
            case 54: {
                return this.ro;
            }
            case 55: {
                return this.VC;
            }
            case 56: {
                return this.VD;
            }
        }
    }
    
    public int describeContents() {
        final id creator = ic.CREATOR;
        return 0;
    }
    
    @Override
    public HashMap<String, a<?, ?>> eY() {
        return ic.UI;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ic)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ic ic = (ic)o;
        for (final a a : com.google.android.gms.internal.ic.UI.values()) {
            if (this.a((a)a)) {
                if (!ic.a((a)a)) {
                    return false;
                }
                if (!this.b((a)a).equals(ic.b((a)a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ic.a((a)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public ItemScope getAbout() {
        return this.UK;
    }
    
    @Override
    public List<String> getAdditionalName() {
        return this.UL;
    }
    
    @Override
    public ItemScope getAddress() {
        return this.UM;
    }
    
    @Override
    public String getAddressCountry() {
        return this.UN;
    }
    
    @Override
    public String getAddressLocality() {
        return this.UO;
    }
    
    @Override
    public String getAddressRegion() {
        return this.UP;
    }
    
    @Override
    public List<ItemScope> getAssociated_media() {
        return (List<ItemScope>)(ArrayList)this.UQ;
    }
    
    @Override
    public int getAttendeeCount() {
        return this.UR;
    }
    
    @Override
    public List<ItemScope> getAttendees() {
        return (List<ItemScope>)(ArrayList)this.US;
    }
    
    @Override
    public ItemScope getAudio() {
        return this.UT;
    }
    
    @Override
    public List<ItemScope> getAuthor() {
        return (List<ItemScope>)(ArrayList)this.UU;
    }
    
    @Override
    public String getBestRating() {
        return this.UV;
    }
    
    @Override
    public String getBirthDate() {
        return this.UW;
    }
    
    @Override
    public ItemScope getByArtist() {
        return this.UX;
    }
    
    @Override
    public String getCaption() {
        return this.UY;
    }
    
    @Override
    public String getContentSize() {
        return this.UZ;
    }
    
    @Override
    public String getContentUrl() {
        return this.lY;
    }
    
    @Override
    public List<ItemScope> getContributor() {
        return (List<ItemScope>)(ArrayList)this.Va;
    }
    
    @Override
    public String getDateCreated() {
        return this.Vb;
    }
    
    @Override
    public String getDateModified() {
        return this.Vc;
    }
    
    @Override
    public String getDatePublished() {
        return this.Vd;
    }
    
    @Override
    public String getDescription() {
        return this.HD;
    }
    
    @Override
    public String getDuration() {
        return this.Ve;
    }
    
    @Override
    public String getEmbedUrl() {
        return this.Vf;
    }
    
    @Override
    public String getEndDate() {
        return this.Vg;
    }
    
    @Override
    public String getFamilyName() {
        return this.Vh;
    }
    
    @Override
    public String getGender() {
        return this.Vi;
    }
    
    @Override
    public ItemScope getGeo() {
        return this.Vj;
    }
    
    @Override
    public String getGivenName() {
        return this.Vk;
    }
    
    @Override
    public String getHeight() {
        return this.Vl;
    }
    
    @Override
    public String getId() {
        return this.wp;
    }
    
    @Override
    public String getImage() {
        return this.Vm;
    }
    
    @Override
    public ItemScope getInAlbum() {
        return this.Vn;
    }
    
    @Override
    public double getLatitude() {
        return this.NX;
    }
    
    @Override
    public ItemScope getLocation() {
        return this.Vo;
    }
    
    @Override
    public double getLongitude() {
        return this.NY;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    @Override
    public ItemScope getPartOfTVSeries() {
        return this.Vp;
    }
    
    @Override
    public List<ItemScope> getPerformers() {
        return (List<ItemScope>)(ArrayList)this.Vq;
    }
    
    @Override
    public String getPlayerType() {
        return this.Vr;
    }
    
    @Override
    public String getPostOfficeBoxNumber() {
        return this.Vs;
    }
    
    @Override
    public String getPostalCode() {
        return this.Vt;
    }
    
    @Override
    public String getRatingValue() {
        return this.Vu;
    }
    
    @Override
    public ItemScope getReviewRating() {
        return this.Vv;
    }
    
    @Override
    public String getStartDate() {
        return this.Vw;
    }
    
    @Override
    public String getStreetAddress() {
        return this.Vx;
    }
    
    @Override
    public String getText() {
        return this.Vy;
    }
    
    @Override
    public ItemScope getThumbnail() {
        return this.Vz;
    }
    
    @Override
    public String getThumbnailUrl() {
        return this.VA;
    }
    
    @Override
    public String getTickerSymbol() {
        return this.VB;
    }
    
    @Override
    public String getType() {
        return this.Rd;
    }
    
    @Override
    public String getUrl() {
        return this.ro;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public String getWidth() {
        return this.VC;
    }
    
    @Override
    public String getWorstRating() {
        return this.VD;
    }
    
    @Override
    public boolean hasAbout() {
        return this.UJ.contains(2);
    }
    
    @Override
    public boolean hasAdditionalName() {
        return this.UJ.contains(3);
    }
    
    @Override
    public boolean hasAddress() {
        return this.UJ.contains(4);
    }
    
    @Override
    public boolean hasAddressCountry() {
        return this.UJ.contains(5);
    }
    
    @Override
    public boolean hasAddressLocality() {
        return this.UJ.contains(6);
    }
    
    @Override
    public boolean hasAddressRegion() {
        return this.UJ.contains(7);
    }
    
    @Override
    public boolean hasAssociated_media() {
        return this.UJ.contains(8);
    }
    
    @Override
    public boolean hasAttendeeCount() {
        return this.UJ.contains(9);
    }
    
    @Override
    public boolean hasAttendees() {
        return this.UJ.contains(10);
    }
    
    @Override
    public boolean hasAudio() {
        return this.UJ.contains(11);
    }
    
    @Override
    public boolean hasAuthor() {
        return this.UJ.contains(12);
    }
    
    @Override
    public boolean hasBestRating() {
        return this.UJ.contains(13);
    }
    
    @Override
    public boolean hasBirthDate() {
        return this.UJ.contains(14);
    }
    
    @Override
    public boolean hasByArtist() {
        return this.UJ.contains(15);
    }
    
    @Override
    public boolean hasCaption() {
        return this.UJ.contains(16);
    }
    
    @Override
    public boolean hasContentSize() {
        return this.UJ.contains(17);
    }
    
    @Override
    public boolean hasContentUrl() {
        return this.UJ.contains(18);
    }
    
    @Override
    public boolean hasContributor() {
        return this.UJ.contains(19);
    }
    
    @Override
    public boolean hasDateCreated() {
        return this.UJ.contains(20);
    }
    
    @Override
    public boolean hasDateModified() {
        return this.UJ.contains(21);
    }
    
    @Override
    public boolean hasDatePublished() {
        return this.UJ.contains(22);
    }
    
    @Override
    public boolean hasDescription() {
        return this.UJ.contains(23);
    }
    
    @Override
    public boolean hasDuration() {
        return this.UJ.contains(24);
    }
    
    @Override
    public boolean hasEmbedUrl() {
        return this.UJ.contains(25);
    }
    
    @Override
    public boolean hasEndDate() {
        return this.UJ.contains(26);
    }
    
    @Override
    public boolean hasFamilyName() {
        return this.UJ.contains(27);
    }
    
    @Override
    public boolean hasGender() {
        return this.UJ.contains(28);
    }
    
    @Override
    public boolean hasGeo() {
        return this.UJ.contains(29);
    }
    
    @Override
    public boolean hasGivenName() {
        return this.UJ.contains(30);
    }
    
    @Override
    public boolean hasHeight() {
        return this.UJ.contains(31);
    }
    
    @Override
    public boolean hasId() {
        return this.UJ.contains(32);
    }
    
    @Override
    public boolean hasImage() {
        return this.UJ.contains(33);
    }
    
    @Override
    public boolean hasInAlbum() {
        return this.UJ.contains(34);
    }
    
    @Override
    public boolean hasLatitude() {
        return this.UJ.contains(36);
    }
    
    @Override
    public boolean hasLocation() {
        return this.UJ.contains(37);
    }
    
    @Override
    public boolean hasLongitude() {
        return this.UJ.contains(38);
    }
    
    @Override
    public boolean hasName() {
        return this.UJ.contains(39);
    }
    
    @Override
    public boolean hasPartOfTVSeries() {
        return this.UJ.contains(40);
    }
    
    @Override
    public boolean hasPerformers() {
        return this.UJ.contains(41);
    }
    
    @Override
    public boolean hasPlayerType() {
        return this.UJ.contains(42);
    }
    
    @Override
    public boolean hasPostOfficeBoxNumber() {
        return this.UJ.contains(43);
    }
    
    @Override
    public boolean hasPostalCode() {
        return this.UJ.contains(44);
    }
    
    @Override
    public boolean hasRatingValue() {
        return this.UJ.contains(45);
    }
    
    @Override
    public boolean hasReviewRating() {
        return this.UJ.contains(46);
    }
    
    @Override
    public boolean hasStartDate() {
        return this.UJ.contains(47);
    }
    
    @Override
    public boolean hasStreetAddress() {
        return this.UJ.contains(48);
    }
    
    @Override
    public boolean hasText() {
        return this.UJ.contains(49);
    }
    
    @Override
    public boolean hasThumbnail() {
        return this.UJ.contains(50);
    }
    
    @Override
    public boolean hasThumbnailUrl() {
        return this.UJ.contains(51);
    }
    
    @Override
    public boolean hasTickerSymbol() {
        return this.UJ.contains(52);
    }
    
    @Override
    public boolean hasType() {
        return this.UJ.contains(53);
    }
    
    @Override
    public boolean hasUrl() {
        return this.UJ.contains(54);
    }
    
    @Override
    public boolean hasWidth() {
        return this.UJ.contains(55);
    }
    
    @Override
    public boolean hasWorstRating() {
        return this.UJ.contains(56);
    }
    
    @Override
    public int hashCode() {
        final Iterator<a<?, ?>> iterator = ic.UI.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final a a = (a)iterator.next();
            if (this.a((a)a)) {
                n = this.b((a)a).hashCode() + (n + a.ff());
            }
        }
        return n;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    Set<Integer> ja() {
        return this.UJ;
    }
    
    ic jb() {
        return this.UK;
    }
    
    ic jc() {
        return this.UM;
    }
    
    List<ic> jd() {
        return this.UQ;
    }
    
    List<ic> je() {
        return this.US;
    }
    
    ic jf() {
        return this.UT;
    }
    
    List<ic> jg() {
        return this.UU;
    }
    
    ic jh() {
        return this.UX;
    }
    
    List<ic> ji() {
        return this.Va;
    }
    
    ic jj() {
        return this.Vj;
    }
    
    ic jk() {
        return this.Vn;
    }
    
    ic jl() {
        return this.Vo;
    }
    
    ic jm() {
        return this.Vp;
    }
    
    List<ic> jn() {
        return this.Vq;
    }
    
    ic jo() {
        return this.Vv;
    }
    
    ic jp() {
        return this.Vz;
    }
    
    public ic jq() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final id creator = ic.CREATOR;
        id.a(this, parcel, n);
    }
}
