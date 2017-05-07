// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ib extends es implements SafeParcelable, ItemScope
{
    public static final ic CREATOR;
    private static final HashMap<String, a<?, ?>> Ep;
    private String AI;
    private ib EA;
    private List<ib> EB;
    private String EC;
    private String ED;
    private ib EE;
    private String EF;
    private String EG;
    private String EH;
    private List<ib> EI;
    private String EJ;
    private String EK;
    private String EL;
    private String EM;
    private String EN;
    private String EO;
    private String EP;
    private String EQ;
    private ib ER;
    private String ES;
    private String ET;
    private String EU;
    private ib EV;
    private ib EW;
    private ib EX;
    private List<ib> EY;
    private String EZ;
    private final Set<Integer> Eq;
    private ib Er;
    private List<String> Es;
    private ib Et;
    private String Eu;
    private String Ev;
    private String Ew;
    private List<ib> Ex;
    private int Ey;
    private List<ib> Ez;
    private String Fa;
    private String Fb;
    private String Fc;
    private ib Fd;
    private String Fe;
    private String Ff;
    private String Fg;
    private ib Fh;
    private String Fi;
    private String Fj;
    private String Fk;
    private String Fl;
    private String iH;
    private final int kg;
    private String mName;
    private String sJ;
    private String uS;
    private double xw;
    private double xx;
    
    static {
        CREATOR = new ic();
        (Ep = new HashMap<String, a<?, ?>>()).put("about", a.a("about", 2, (Class<?>)ib.class));
        ib.Ep.put("additionalName", (a<?, ?>)a.h("additionalName", 3));
        ib.Ep.put("address", a.a("address", 4, (Class<?>)ib.class));
        ib.Ep.put("addressCountry", (a<?, ?>)a.g("addressCountry", 5));
        ib.Ep.put("addressLocality", (a<?, ?>)a.g("addressLocality", 6));
        ib.Ep.put("addressRegion", (a<?, ?>)a.g("addressRegion", 7));
        ib.Ep.put("associated_media", (a<?, ?>)a.b("associated_media", 8, ib.class));
        ib.Ep.put("attendeeCount", (a<?, ?>)a.d("attendeeCount", 9));
        ib.Ep.put("attendees", (a<?, ?>)a.b("attendees", 10, ib.class));
        ib.Ep.put("audio", a.a("audio", 11, (Class<?>)ib.class));
        ib.Ep.put("author", (a<?, ?>)a.b("author", 12, ib.class));
        ib.Ep.put("bestRating", (a<?, ?>)a.g("bestRating", 13));
        ib.Ep.put("birthDate", (a<?, ?>)a.g("birthDate", 14));
        ib.Ep.put("byArtist", a.a("byArtist", 15, (Class<?>)ib.class));
        ib.Ep.put("caption", (a<?, ?>)a.g("caption", 16));
        ib.Ep.put("contentSize", (a<?, ?>)a.g("contentSize", 17));
        ib.Ep.put("contentUrl", (a<?, ?>)a.g("contentUrl", 18));
        ib.Ep.put("contributor", (a<?, ?>)a.b("contributor", 19, ib.class));
        ib.Ep.put("dateCreated", (a<?, ?>)a.g("dateCreated", 20));
        ib.Ep.put("dateModified", (a<?, ?>)a.g("dateModified", 21));
        ib.Ep.put("datePublished", (a<?, ?>)a.g("datePublished", 22));
        ib.Ep.put("description", (a<?, ?>)a.g("description", 23));
        ib.Ep.put("duration", (a<?, ?>)a.g("duration", 24));
        ib.Ep.put("embedUrl", (a<?, ?>)a.g("embedUrl", 25));
        ib.Ep.put("endDate", (a<?, ?>)a.g("endDate", 26));
        ib.Ep.put("familyName", (a<?, ?>)a.g("familyName", 27));
        ib.Ep.put("gender", (a<?, ?>)a.g("gender", 28));
        ib.Ep.put("geo", a.a("geo", 29, (Class<?>)ib.class));
        ib.Ep.put("givenName", (a<?, ?>)a.g("givenName", 30));
        ib.Ep.put("height", (a<?, ?>)a.g("height", 31));
        ib.Ep.put("id", (a<?, ?>)a.g("id", 32));
        ib.Ep.put("image", (a<?, ?>)a.g("image", 33));
        ib.Ep.put("inAlbum", a.a("inAlbum", 34, (Class<?>)ib.class));
        ib.Ep.put("latitude", (a<?, ?>)a.e("latitude", 36));
        ib.Ep.put("location", a.a("location", 37, (Class<?>)ib.class));
        ib.Ep.put("longitude", (a<?, ?>)a.e("longitude", 38));
        ib.Ep.put("name", (a<?, ?>)a.g("name", 39));
        ib.Ep.put("partOfTVSeries", a.a("partOfTVSeries", 40, (Class<?>)ib.class));
        ib.Ep.put("performers", (a<?, ?>)a.b("performers", 41, ib.class));
        ib.Ep.put("playerType", (a<?, ?>)a.g("playerType", 42));
        ib.Ep.put("postOfficeBoxNumber", (a<?, ?>)a.g("postOfficeBoxNumber", 43));
        ib.Ep.put("postalCode", (a<?, ?>)a.g("postalCode", 44));
        ib.Ep.put("ratingValue", (a<?, ?>)a.g("ratingValue", 45));
        ib.Ep.put("reviewRating", a.a("reviewRating", 46, (Class<?>)ib.class));
        ib.Ep.put("startDate", (a<?, ?>)a.g("startDate", 47));
        ib.Ep.put("streetAddress", (a<?, ?>)a.g("streetAddress", 48));
        ib.Ep.put("text", (a<?, ?>)a.g("text", 49));
        ib.Ep.put("thumbnail", a.a("thumbnail", 50, (Class<?>)ib.class));
        ib.Ep.put("thumbnailUrl", (a<?, ?>)a.g("thumbnailUrl", 51));
        ib.Ep.put("tickerSymbol", (a<?, ?>)a.g("tickerSymbol", 52));
        ib.Ep.put("type", (a<?, ?>)a.g("type", 53));
        ib.Ep.put("url", (a<?, ?>)a.g("url", 54));
        ib.Ep.put("width", (a<?, ?>)a.g("width", 55));
        ib.Ep.put("worstRating", (a<?, ?>)a.g("worstRating", 56));
    }
    
    public ib() {
        this.kg = 1;
        this.Eq = new HashSet<Integer>();
    }
    
    ib(final Set<Integer> eq, final int kg, final ib er, final List<String> es, final ib et, final String eu, final String ev, final String ew, final List<ib> ex, final int ey, final List<ib> ez, final ib ea, final List<ib> eb, final String ec, final String ed, final ib ee, final String ef, final String eg, final String eh, final List<ib> ei, final String ej, final String ek, final String el, final String sj, final String em, final String en, final String eo, final String ep, final String eq2, final ib er2, final String es2, final String et2, final String us, final String eu2, final ib ev2, final double xw, final ib ew2, final double xx, final String mName, final ib ex2, final List<ib> ey2, final String ez2, final String fa, final String fb, final String fc, final ib fd, final String fe, final String ff, final String fg, final ib fh, final String fi, final String fj, final String ai, final String ih, final String fk, final String fl) {
        this.Eq = eq;
        this.kg = kg;
        this.Er = er;
        this.Es = es;
        this.Et = et;
        this.Eu = eu;
        this.Ev = ev;
        this.Ew = ew;
        this.Ex = ex;
        this.Ey = ey;
        this.Ez = ez;
        this.EA = ea;
        this.EB = eb;
        this.EC = ec;
        this.ED = ed;
        this.EE = ee;
        this.EF = ef;
        this.EG = eg;
        this.EH = eh;
        this.EI = ei;
        this.EJ = ej;
        this.EK = ek;
        this.EL = el;
        this.sJ = sj;
        this.EM = em;
        this.EN = en;
        this.EO = eo;
        this.EP = ep;
        this.EQ = eq2;
        this.ER = er2;
        this.ES = es2;
        this.ET = et2;
        this.uS = us;
        this.EU = eu2;
        this.EV = ev2;
        this.xw = xw;
        this.EW = ew2;
        this.xx = xx;
        this.mName = mName;
        this.EX = ex2;
        this.EY = ey2;
        this.EZ = ez2;
        this.Fa = fa;
        this.Fb = fb;
        this.Fc = fc;
        this.Fd = fd;
        this.Fe = fe;
        this.Ff = ff;
        this.Fg = fg;
        this.Fh = fh;
        this.Fi = fi;
        this.Fj = fj;
        this.AI = ai;
        this.iH = ih;
        this.Fk = fk;
        this.Fl = fl;
    }
    
    public ib(final Set<Integer> eq, final ib er, final List<String> es, final ib et, final String eu, final String ev, final String ew, final List<ib> ex, final int ey, final List<ib> ez, final ib ea, final List<ib> eb, final String ec, final String ed, final ib ee, final String ef, final String eg, final String eh, final List<ib> ei, final String ej, final String ek, final String el, final String sj, final String em, final String en, final String eo, final String ep, final String eq2, final ib er2, final String es2, final String et2, final String us, final String eu2, final ib ev2, final double xw, final ib ew2, final double xx, final String mName, final ib ex2, final List<ib> ey2, final String ez2, final String fa, final String fb, final String fc, final ib fd, final String fe, final String ff, final String fg, final ib fh, final String fi, final String fj, final String ai, final String ih, final String fk, final String fl) {
        this.Eq = eq;
        this.kg = 1;
        this.Er = er;
        this.Es = es;
        this.Et = et;
        this.Eu = eu;
        this.Ev = ev;
        this.Ew = ew;
        this.Ex = ex;
        this.Ey = ey;
        this.Ez = ez;
        this.EA = ea;
        this.EB = eb;
        this.EC = ec;
        this.ED = ed;
        this.EE = ee;
        this.EF = ef;
        this.EG = eg;
        this.EH = eh;
        this.EI = ei;
        this.EJ = ej;
        this.EK = ek;
        this.EL = el;
        this.sJ = sj;
        this.EM = em;
        this.EN = en;
        this.EO = eo;
        this.EP = ep;
        this.EQ = eq2;
        this.ER = er2;
        this.ES = es2;
        this.ET = et2;
        this.uS = us;
        this.EU = eu2;
        this.EV = ev2;
        this.xw = xw;
        this.EW = ew2;
        this.xx = xx;
        this.mName = mName;
        this.EX = ex2;
        this.EY = ey2;
        this.EZ = ez2;
        this.Fa = fa;
        this.Fb = fb;
        this.Fc = fc;
        this.Fd = fd;
        this.Fe = fe;
        this.Ff = ff;
        this.Fg = fg;
        this.Fh = fh;
        this.Fi = fi;
        this.Fj = fj;
        this.AI = ai;
        this.iH = ih;
        this.Fk = fk;
        this.Fl = fl;
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
    protected boolean a(final a a) {
        return this.Eq.contains(a.cq());
    }
    
    @Override
    protected Object b(final a a) {
        switch (a.cq()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + a.cq());
            }
            case 2: {
                return this.Er;
            }
            case 3: {
                return this.Es;
            }
            case 4: {
                return this.Et;
            }
            case 5: {
                return this.Eu;
            }
            case 6: {
                return this.Ev;
            }
            case 7: {
                return this.Ew;
            }
            case 8: {
                return this.Ex;
            }
            case 9: {
                return this.Ey;
            }
            case 10: {
                return this.Ez;
            }
            case 11: {
                return this.EA;
            }
            case 12: {
                return this.EB;
            }
            case 13: {
                return this.EC;
            }
            case 14: {
                return this.ED;
            }
            case 15: {
                return this.EE;
            }
            case 16: {
                return this.EF;
            }
            case 17: {
                return this.EG;
            }
            case 18: {
                return this.EH;
            }
            case 19: {
                return this.EI;
            }
            case 20: {
                return this.EJ;
            }
            case 21: {
                return this.EK;
            }
            case 22: {
                return this.EL;
            }
            case 23: {
                return this.sJ;
            }
            case 24: {
                return this.EM;
            }
            case 25: {
                return this.EN;
            }
            case 26: {
                return this.EO;
            }
            case 27: {
                return this.EP;
            }
            case 28: {
                return this.EQ;
            }
            case 29: {
                return this.ER;
            }
            case 30: {
                return this.ES;
            }
            case 31: {
                return this.ET;
            }
            case 32: {
                return this.uS;
            }
            case 33: {
                return this.EU;
            }
            case 34: {
                return this.EV;
            }
            case 36: {
                return this.xw;
            }
            case 37: {
                return this.EW;
            }
            case 38: {
                return this.xx;
            }
            case 39: {
                return this.mName;
            }
            case 40: {
                return this.EX;
            }
            case 41: {
                return this.EY;
            }
            case 42: {
                return this.EZ;
            }
            case 43: {
                return this.Fa;
            }
            case 44: {
                return this.Fb;
            }
            case 45: {
                return this.Fc;
            }
            case 46: {
                return this.Fd;
            }
            case 47: {
                return this.Fe;
            }
            case 48: {
                return this.Ff;
            }
            case 49: {
                return this.Fg;
            }
            case 50: {
                return this.Fh;
            }
            case 51: {
                return this.Fi;
            }
            case 52: {
                return this.Fj;
            }
            case 53: {
                return this.AI;
            }
            case 54: {
                return this.iH;
            }
            case 55: {
                return this.Fk;
            }
            case 56: {
                return this.Fl;
            }
        }
    }
    
    @Override
    public HashMap<String, a<?, ?>> cj() {
        return ib.Ep;
    }
    
    public int describeContents() {
        final ic creator = ib.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ib)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ib ib = (ib)o;
        for (final a a : com.google.android.gms.internal.ib.Ep.values()) {
            if (this.a((a)a)) {
                if (!ib.a((a)a)) {
                    return false;
                }
                if (!this.b((a)a).equals(ib.b((a)a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ib.a((a)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    Set<Integer> fa() {
        return this.Eq;
    }
    
    ib fb() {
        return this.Er;
    }
    
    ib fc() {
        return this.Et;
    }
    
    List<ib> fd() {
        return this.Ex;
    }
    
    List<ib> fe() {
        return this.Ez;
    }
    
    ib ff() {
        return this.EA;
    }
    
    List<ib> fg() {
        return this.EB;
    }
    
    ib fh() {
        return this.EE;
    }
    
    List<ib> fi() {
        return this.EI;
    }
    
    ib fj() {
        return this.ER;
    }
    
    ib fk() {
        return this.EV;
    }
    
    ib fl() {
        return this.EW;
    }
    
    ib fm() {
        return this.EX;
    }
    
    List<ib> fn() {
        return this.EY;
    }
    
    ib fo() {
        return this.Fd;
    }
    
    ib fp() {
        return this.Fh;
    }
    
    public ib fq() {
        return this;
    }
    
    @Override
    public ItemScope getAbout() {
        return this.Er;
    }
    
    @Override
    public List<String> getAdditionalName() {
        return this.Es;
    }
    
    @Override
    public ItemScope getAddress() {
        return this.Et;
    }
    
    @Override
    public String getAddressCountry() {
        return this.Eu;
    }
    
    @Override
    public String getAddressLocality() {
        return this.Ev;
    }
    
    @Override
    public String getAddressRegion() {
        return this.Ew;
    }
    
    @Override
    public List<ItemScope> getAssociated_media() {
        return (List<ItemScope>)(ArrayList)this.Ex;
    }
    
    @Override
    public int getAttendeeCount() {
        return this.Ey;
    }
    
    @Override
    public List<ItemScope> getAttendees() {
        return (List<ItemScope>)(ArrayList)this.Ez;
    }
    
    @Override
    public ItemScope getAudio() {
        return this.EA;
    }
    
    @Override
    public List<ItemScope> getAuthor() {
        return (List<ItemScope>)(ArrayList)this.EB;
    }
    
    @Override
    public String getBestRating() {
        return this.EC;
    }
    
    @Override
    public String getBirthDate() {
        return this.ED;
    }
    
    @Override
    public ItemScope getByArtist() {
        return this.EE;
    }
    
    @Override
    public String getCaption() {
        return this.EF;
    }
    
    @Override
    public String getContentSize() {
        return this.EG;
    }
    
    @Override
    public String getContentUrl() {
        return this.EH;
    }
    
    @Override
    public List<ItemScope> getContributor() {
        return (List<ItemScope>)(ArrayList)this.EI;
    }
    
    @Override
    public String getDateCreated() {
        return this.EJ;
    }
    
    @Override
    public String getDateModified() {
        return this.EK;
    }
    
    @Override
    public String getDatePublished() {
        return this.EL;
    }
    
    @Override
    public String getDescription() {
        return this.sJ;
    }
    
    @Override
    public String getDuration() {
        return this.EM;
    }
    
    @Override
    public String getEmbedUrl() {
        return this.EN;
    }
    
    @Override
    public String getEndDate() {
        return this.EO;
    }
    
    @Override
    public String getFamilyName() {
        return this.EP;
    }
    
    @Override
    public String getGender() {
        return this.EQ;
    }
    
    @Override
    public ItemScope getGeo() {
        return this.ER;
    }
    
    @Override
    public String getGivenName() {
        return this.ES;
    }
    
    @Override
    public String getHeight() {
        return this.ET;
    }
    
    @Override
    public String getId() {
        return this.uS;
    }
    
    @Override
    public String getImage() {
        return this.EU;
    }
    
    @Override
    public ItemScope getInAlbum() {
        return this.EV;
    }
    
    @Override
    public double getLatitude() {
        return this.xw;
    }
    
    @Override
    public ItemScope getLocation() {
        return this.EW;
    }
    
    @Override
    public double getLongitude() {
        return this.xx;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    @Override
    public ItemScope getPartOfTVSeries() {
        return this.EX;
    }
    
    @Override
    public List<ItemScope> getPerformers() {
        return (List<ItemScope>)(ArrayList)this.EY;
    }
    
    @Override
    public String getPlayerType() {
        return this.EZ;
    }
    
    @Override
    public String getPostOfficeBoxNumber() {
        return this.Fa;
    }
    
    @Override
    public String getPostalCode() {
        return this.Fb;
    }
    
    @Override
    public String getRatingValue() {
        return this.Fc;
    }
    
    @Override
    public ItemScope getReviewRating() {
        return this.Fd;
    }
    
    @Override
    public String getStartDate() {
        return this.Fe;
    }
    
    @Override
    public String getStreetAddress() {
        return this.Ff;
    }
    
    @Override
    public String getText() {
        return this.Fg;
    }
    
    @Override
    public ItemScope getThumbnail() {
        return this.Fh;
    }
    
    @Override
    public String getThumbnailUrl() {
        return this.Fi;
    }
    
    @Override
    public String getTickerSymbol() {
        return this.Fj;
    }
    
    @Override
    public String getType() {
        return this.AI;
    }
    
    @Override
    public String getUrl() {
        return this.iH;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public String getWidth() {
        return this.Fk;
    }
    
    @Override
    public String getWorstRating() {
        return this.Fl;
    }
    
    @Override
    public boolean hasAbout() {
        return this.Eq.contains(2);
    }
    
    @Override
    public boolean hasAdditionalName() {
        return this.Eq.contains(3);
    }
    
    @Override
    public boolean hasAddress() {
        return this.Eq.contains(4);
    }
    
    @Override
    public boolean hasAddressCountry() {
        return this.Eq.contains(5);
    }
    
    @Override
    public boolean hasAddressLocality() {
        return this.Eq.contains(6);
    }
    
    @Override
    public boolean hasAddressRegion() {
        return this.Eq.contains(7);
    }
    
    @Override
    public boolean hasAssociated_media() {
        return this.Eq.contains(8);
    }
    
    @Override
    public boolean hasAttendeeCount() {
        return this.Eq.contains(9);
    }
    
    @Override
    public boolean hasAttendees() {
        return this.Eq.contains(10);
    }
    
    @Override
    public boolean hasAudio() {
        return this.Eq.contains(11);
    }
    
    @Override
    public boolean hasAuthor() {
        return this.Eq.contains(12);
    }
    
    @Override
    public boolean hasBestRating() {
        return this.Eq.contains(13);
    }
    
    @Override
    public boolean hasBirthDate() {
        return this.Eq.contains(14);
    }
    
    @Override
    public boolean hasByArtist() {
        return this.Eq.contains(15);
    }
    
    @Override
    public boolean hasCaption() {
        return this.Eq.contains(16);
    }
    
    @Override
    public boolean hasContentSize() {
        return this.Eq.contains(17);
    }
    
    @Override
    public boolean hasContentUrl() {
        return this.Eq.contains(18);
    }
    
    @Override
    public boolean hasContributor() {
        return this.Eq.contains(19);
    }
    
    @Override
    public boolean hasDateCreated() {
        return this.Eq.contains(20);
    }
    
    @Override
    public boolean hasDateModified() {
        return this.Eq.contains(21);
    }
    
    @Override
    public boolean hasDatePublished() {
        return this.Eq.contains(22);
    }
    
    @Override
    public boolean hasDescription() {
        return this.Eq.contains(23);
    }
    
    @Override
    public boolean hasDuration() {
        return this.Eq.contains(24);
    }
    
    @Override
    public boolean hasEmbedUrl() {
        return this.Eq.contains(25);
    }
    
    @Override
    public boolean hasEndDate() {
        return this.Eq.contains(26);
    }
    
    @Override
    public boolean hasFamilyName() {
        return this.Eq.contains(27);
    }
    
    @Override
    public boolean hasGender() {
        return this.Eq.contains(28);
    }
    
    @Override
    public boolean hasGeo() {
        return this.Eq.contains(29);
    }
    
    @Override
    public boolean hasGivenName() {
        return this.Eq.contains(30);
    }
    
    @Override
    public boolean hasHeight() {
        return this.Eq.contains(31);
    }
    
    @Override
    public boolean hasId() {
        return this.Eq.contains(32);
    }
    
    @Override
    public boolean hasImage() {
        return this.Eq.contains(33);
    }
    
    @Override
    public boolean hasInAlbum() {
        return this.Eq.contains(34);
    }
    
    @Override
    public boolean hasLatitude() {
        return this.Eq.contains(36);
    }
    
    @Override
    public boolean hasLocation() {
        return this.Eq.contains(37);
    }
    
    @Override
    public boolean hasLongitude() {
        return this.Eq.contains(38);
    }
    
    @Override
    public boolean hasName() {
        return this.Eq.contains(39);
    }
    
    @Override
    public boolean hasPartOfTVSeries() {
        return this.Eq.contains(40);
    }
    
    @Override
    public boolean hasPerformers() {
        return this.Eq.contains(41);
    }
    
    @Override
    public boolean hasPlayerType() {
        return this.Eq.contains(42);
    }
    
    @Override
    public boolean hasPostOfficeBoxNumber() {
        return this.Eq.contains(43);
    }
    
    @Override
    public boolean hasPostalCode() {
        return this.Eq.contains(44);
    }
    
    @Override
    public boolean hasRatingValue() {
        return this.Eq.contains(45);
    }
    
    @Override
    public boolean hasReviewRating() {
        return this.Eq.contains(46);
    }
    
    @Override
    public boolean hasStartDate() {
        return this.Eq.contains(47);
    }
    
    @Override
    public boolean hasStreetAddress() {
        return this.Eq.contains(48);
    }
    
    @Override
    public boolean hasText() {
        return this.Eq.contains(49);
    }
    
    @Override
    public boolean hasThumbnail() {
        return this.Eq.contains(50);
    }
    
    @Override
    public boolean hasThumbnailUrl() {
        return this.Eq.contains(51);
    }
    
    @Override
    public boolean hasTickerSymbol() {
        return this.Eq.contains(52);
    }
    
    @Override
    public boolean hasType() {
        return this.Eq.contains(53);
    }
    
    @Override
    public boolean hasUrl() {
        return this.Eq.contains(54);
    }
    
    @Override
    public boolean hasWidth() {
        return this.Eq.contains(55);
    }
    
    @Override
    public boolean hasWorstRating() {
        return this.Eq.contains(56);
    }
    
    @Override
    public int hashCode() {
        final Iterator<a<?, ?>> iterator = ib.Ep.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final a a = (a)iterator.next();
            if (this.a((a)a)) {
                n = this.b((a)a).hashCode() + (n + a.cq());
            }
        }
        return n;
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ic creator = ib.CREATOR;
        ic.a(this, parcel, n);
    }
}
