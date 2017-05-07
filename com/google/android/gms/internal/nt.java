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

public final class nt extends jj implements ItemScope
{
    public static final nu CREATOR;
    private static final HashMap<String, a<?, ?>> alQ;
    String BL;
    final int BR;
    String Tg;
    double adZ;
    double aea;
    final Set<Integer> alR;
    nt alS;
    List<String> alT;
    nt alU;
    String alV;
    String alW;
    String alX;
    List<nt> alY;
    int alZ;
    String amA;
    String amB;
    String amC;
    nt amD;
    String amE;
    String amF;
    String amG;
    nt amH;
    String amI;
    String amJ;
    String amK;
    String amL;
    List<nt> ama;
    nt amb;
    List<nt> amc;
    String amd;
    String ame;
    nt amf;
    String amg;
    String amh;
    List<nt> ami;
    String amj;
    String amk;
    String aml;
    String amm;
    String amn;
    String amo;
    String amp;
    String amq;
    nt amr;
    String ams;
    String amt;
    String amu;
    nt amv;
    nt amw;
    nt amx;
    List<nt> amy;
    String amz;
    String mName;
    String ol;
    String uO;
    String uR;
    
    static {
        CREATOR = new nu();
        (alQ = new HashMap<String, a<?, ?>>()).put("about", a.a("about", 2, (Class<?>)nt.class));
        nt.alQ.put("additionalName", (a<?, ?>)a.m("additionalName", 3));
        nt.alQ.put("address", a.a("address", 4, (Class<?>)nt.class));
        nt.alQ.put("addressCountry", (a<?, ?>)a.l("addressCountry", 5));
        nt.alQ.put("addressLocality", (a<?, ?>)a.l("addressLocality", 6));
        nt.alQ.put("addressRegion", (a<?, ?>)a.l("addressRegion", 7));
        nt.alQ.put("associated_media", (a<?, ?>)a.b("associated_media", 8, nt.class));
        nt.alQ.put("attendeeCount", (a<?, ?>)a.i("attendeeCount", 9));
        nt.alQ.put("attendees", (a<?, ?>)a.b("attendees", 10, nt.class));
        nt.alQ.put("audio", a.a("audio", 11, (Class<?>)nt.class));
        nt.alQ.put("author", (a<?, ?>)a.b("author", 12, nt.class));
        nt.alQ.put("bestRating", (a<?, ?>)a.l("bestRating", 13));
        nt.alQ.put("birthDate", (a<?, ?>)a.l("birthDate", 14));
        nt.alQ.put("byArtist", a.a("byArtist", 15, (Class<?>)nt.class));
        nt.alQ.put("caption", (a<?, ?>)a.l("caption", 16));
        nt.alQ.put("contentSize", (a<?, ?>)a.l("contentSize", 17));
        nt.alQ.put("contentUrl", (a<?, ?>)a.l("contentUrl", 18));
        nt.alQ.put("contributor", (a<?, ?>)a.b("contributor", 19, nt.class));
        nt.alQ.put("dateCreated", (a<?, ?>)a.l("dateCreated", 20));
        nt.alQ.put("dateModified", (a<?, ?>)a.l("dateModified", 21));
        nt.alQ.put("datePublished", (a<?, ?>)a.l("datePublished", 22));
        nt.alQ.put("description", (a<?, ?>)a.l("description", 23));
        nt.alQ.put("duration", (a<?, ?>)a.l("duration", 24));
        nt.alQ.put("embedUrl", (a<?, ?>)a.l("embedUrl", 25));
        nt.alQ.put("endDate", (a<?, ?>)a.l("endDate", 26));
        nt.alQ.put("familyName", (a<?, ?>)a.l("familyName", 27));
        nt.alQ.put("gender", (a<?, ?>)a.l("gender", 28));
        nt.alQ.put("geo", a.a("geo", 29, (Class<?>)nt.class));
        nt.alQ.put("givenName", (a<?, ?>)a.l("givenName", 30));
        nt.alQ.put("height", (a<?, ?>)a.l("height", 31));
        nt.alQ.put("id", (a<?, ?>)a.l("id", 32));
        nt.alQ.put("image", (a<?, ?>)a.l("image", 33));
        nt.alQ.put("inAlbum", a.a("inAlbum", 34, (Class<?>)nt.class));
        nt.alQ.put("latitude", (a<?, ?>)a.j("latitude", 36));
        nt.alQ.put("location", a.a("location", 37, (Class<?>)nt.class));
        nt.alQ.put("longitude", (a<?, ?>)a.j("longitude", 38));
        nt.alQ.put("name", (a<?, ?>)a.l("name", 39));
        nt.alQ.put("partOfTVSeries", a.a("partOfTVSeries", 40, (Class<?>)nt.class));
        nt.alQ.put("performers", (a<?, ?>)a.b("performers", 41, nt.class));
        nt.alQ.put("playerType", (a<?, ?>)a.l("playerType", 42));
        nt.alQ.put("postOfficeBoxNumber", (a<?, ?>)a.l("postOfficeBoxNumber", 43));
        nt.alQ.put("postalCode", (a<?, ?>)a.l("postalCode", 44));
        nt.alQ.put("ratingValue", (a<?, ?>)a.l("ratingValue", 45));
        nt.alQ.put("reviewRating", a.a("reviewRating", 46, (Class<?>)nt.class));
        nt.alQ.put("startDate", (a<?, ?>)a.l("startDate", 47));
        nt.alQ.put("streetAddress", (a<?, ?>)a.l("streetAddress", 48));
        nt.alQ.put("text", (a<?, ?>)a.l("text", 49));
        nt.alQ.put("thumbnail", a.a("thumbnail", 50, (Class<?>)nt.class));
        nt.alQ.put("thumbnailUrl", (a<?, ?>)a.l("thumbnailUrl", 51));
        nt.alQ.put("tickerSymbol", (a<?, ?>)a.l("tickerSymbol", 52));
        nt.alQ.put("type", (a<?, ?>)a.l("type", 53));
        nt.alQ.put("url", (a<?, ?>)a.l("url", 54));
        nt.alQ.put("width", (a<?, ?>)a.l("width", 55));
        nt.alQ.put("worstRating", (a<?, ?>)a.l("worstRating", 56));
    }
    
    public nt() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    nt(final Set<Integer> alR, final int br, final nt alS, final List<String> alT, final nt alU, final String alV, final String alW, final String alX, final List<nt> alY, final int alZ, final List<nt> ama, final nt amb, final List<nt> amc, final String amd, final String ame, final nt amf, final String amg, final String amh, final String ol, final List<nt> ami, final String amj, final String amk, final String aml, final String tg, final String amm, final String amn, final String amo, final String amp, final String amq, final nt amr, final String ams, final String amt, final String bl, final String amu, final nt amv, final double adZ, final nt amw, final double aea, final String mName, final nt amx, final List<nt> amy, final String amz, final String amA, final String amB, final String amC, final nt amD, final String amE, final String amF, final String amG, final nt amH, final String amI, final String amJ, final String uo, final String ur, final String amK, final String amL) {
        this.alR = alR;
        this.BR = br;
        this.alS = alS;
        this.alT = alT;
        this.alU = alU;
        this.alV = alV;
        this.alW = alW;
        this.alX = alX;
        this.alY = alY;
        this.alZ = alZ;
        this.ama = ama;
        this.amb = amb;
        this.amc = amc;
        this.amd = amd;
        this.ame = ame;
        this.amf = amf;
        this.amg = amg;
        this.amh = amh;
        this.ol = ol;
        this.ami = ami;
        this.amj = amj;
        this.amk = amk;
        this.aml = aml;
        this.Tg = tg;
        this.amm = amm;
        this.amn = amn;
        this.amo = amo;
        this.amp = amp;
        this.amq = amq;
        this.amr = amr;
        this.ams = ams;
        this.amt = amt;
        this.BL = bl;
        this.amu = amu;
        this.amv = amv;
        this.adZ = adZ;
        this.amw = amw;
        this.aea = aea;
        this.mName = mName;
        this.amx = amx;
        this.amy = amy;
        this.amz = amz;
        this.amA = amA;
        this.amB = amB;
        this.amC = amC;
        this.amD = amD;
        this.amE = amE;
        this.amF = amF;
        this.amG = amG;
        this.amH = amH;
        this.amI = amI;
        this.amJ = amJ;
        this.uO = uo;
        this.uR = ur;
        this.amK = amK;
        this.amL = amL;
    }
    
    public nt(final Set<Integer> alR, final nt alS, final List<String> alT, final nt alU, final String alV, final String alW, final String alX, final List<nt> alY, final int alZ, final List<nt> ama, final nt amb, final List<nt> amc, final String amd, final String ame, final nt amf, final String amg, final String amh, final String ol, final List<nt> ami, final String amj, final String amk, final String aml, final String tg, final String amm, final String amn, final String amo, final String amp, final String amq, final nt amr, final String ams, final String amt, final String bl, final String amu, final nt amv, final double adZ, final nt amw, final double aea, final String mName, final nt amx, final List<nt> amy, final String amz, final String amA, final String amB, final String amC, final nt amD, final String amE, final String amF, final String amG, final nt amH, final String amI, final String amJ, final String uo, final String ur, final String amK, final String amL) {
        this.alR = alR;
        this.BR = 1;
        this.alS = alS;
        this.alT = alT;
        this.alU = alU;
        this.alV = alV;
        this.alW = alW;
        this.alX = alX;
        this.alY = alY;
        this.alZ = alZ;
        this.ama = ama;
        this.amb = amb;
        this.amc = amc;
        this.amd = amd;
        this.ame = ame;
        this.amf = amf;
        this.amg = amg;
        this.amh = amh;
        this.ol = ol;
        this.ami = ami;
        this.amj = amj;
        this.amk = amk;
        this.aml = aml;
        this.Tg = tg;
        this.amm = amm;
        this.amn = amn;
        this.amo = amo;
        this.amp = amp;
        this.amq = amq;
        this.amr = amr;
        this.ams = ams;
        this.amt = amt;
        this.BL = bl;
        this.amu = amu;
        this.amv = amv;
        this.adZ = adZ;
        this.amw = amw;
        this.aea = aea;
        this.mName = mName;
        this.amx = amx;
        this.amy = amy;
        this.amz = amz;
        this.amA = amA;
        this.amB = amB;
        this.amC = amC;
        this.amD = amD;
        this.amE = amE;
        this.amF = amF;
        this.amG = amG;
        this.amH = amH;
        this.amI = amI;
        this.amJ = amJ;
        this.uO = uo;
        this.uR = ur;
        this.amK = amK;
        this.amL = amL;
    }
    
    @Override
    protected boolean a(final a a) {
        return this.alR.contains(a.hm());
    }
    
    @Override
    protected Object b(final a a) {
        switch (a.hm()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + a.hm());
            }
            case 2: {
                return this.alS;
            }
            case 3: {
                return this.alT;
            }
            case 4: {
                return this.alU;
            }
            case 5: {
                return this.alV;
            }
            case 6: {
                return this.alW;
            }
            case 7: {
                return this.alX;
            }
            case 8: {
                return this.alY;
            }
            case 9: {
                return this.alZ;
            }
            case 10: {
                return this.ama;
            }
            case 11: {
                return this.amb;
            }
            case 12: {
                return this.amc;
            }
            case 13: {
                return this.amd;
            }
            case 14: {
                return this.ame;
            }
            case 15: {
                return this.amf;
            }
            case 16: {
                return this.amg;
            }
            case 17: {
                return this.amh;
            }
            case 18: {
                return this.ol;
            }
            case 19: {
                return this.ami;
            }
            case 20: {
                return this.amj;
            }
            case 21: {
                return this.amk;
            }
            case 22: {
                return this.aml;
            }
            case 23: {
                return this.Tg;
            }
            case 24: {
                return this.amm;
            }
            case 25: {
                return this.amn;
            }
            case 26: {
                return this.amo;
            }
            case 27: {
                return this.amp;
            }
            case 28: {
                return this.amq;
            }
            case 29: {
                return this.amr;
            }
            case 30: {
                return this.ams;
            }
            case 31: {
                return this.amt;
            }
            case 32: {
                return this.BL;
            }
            case 33: {
                return this.amu;
            }
            case 34: {
                return this.amv;
            }
            case 36: {
                return this.adZ;
            }
            case 37: {
                return this.amw;
            }
            case 38: {
                return this.aea;
            }
            case 39: {
                return this.mName;
            }
            case 40: {
                return this.amx;
            }
            case 41: {
                return this.amy;
            }
            case 42: {
                return this.amz;
            }
            case 43: {
                return this.amA;
            }
            case 44: {
                return this.amB;
            }
            case 45: {
                return this.amC;
            }
            case 46: {
                return this.amD;
            }
            case 47: {
                return this.amE;
            }
            case 48: {
                return this.amF;
            }
            case 49: {
                return this.amG;
            }
            case 50: {
                return this.amH;
            }
            case 51: {
                return this.amI;
            }
            case 52: {
                return this.amJ;
            }
            case 53: {
                return this.uO;
            }
            case 54: {
                return this.uR;
            }
            case 55: {
                return this.amK;
            }
            case 56: {
                return this.amL;
            }
        }
    }
    
    public int describeContents() {
        final nu creator = nt.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof nt)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final nt nt = (nt)o;
        for (final a a : com.google.android.gms.internal.nt.alQ.values()) {
            if (this.a((a)a)) {
                if (!nt.a((a)a)) {
                    return false;
                }
                if (!this.b((a)a).equals(nt.b((a)a))) {
                    return false;
                }
                continue;
            }
            else {
                if (nt.a((a)a)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    @Override
    public ItemScope getAbout() {
        return this.alS;
    }
    
    @Override
    public List<String> getAdditionalName() {
        return this.alT;
    }
    
    @Override
    public ItemScope getAddress() {
        return this.alU;
    }
    
    @Override
    public String getAddressCountry() {
        return this.alV;
    }
    
    @Override
    public String getAddressLocality() {
        return this.alW;
    }
    
    @Override
    public String getAddressRegion() {
        return this.alX;
    }
    
    @Override
    public List<ItemScope> getAssociated_media() {
        return (List<ItemScope>)(ArrayList)this.alY;
    }
    
    @Override
    public int getAttendeeCount() {
        return this.alZ;
    }
    
    @Override
    public List<ItemScope> getAttendees() {
        return (List<ItemScope>)(ArrayList)this.ama;
    }
    
    @Override
    public ItemScope getAudio() {
        return this.amb;
    }
    
    @Override
    public List<ItemScope> getAuthor() {
        return (List<ItemScope>)(ArrayList)this.amc;
    }
    
    @Override
    public String getBestRating() {
        return this.amd;
    }
    
    @Override
    public String getBirthDate() {
        return this.ame;
    }
    
    @Override
    public ItemScope getByArtist() {
        return this.amf;
    }
    
    @Override
    public String getCaption() {
        return this.amg;
    }
    
    @Override
    public String getContentSize() {
        return this.amh;
    }
    
    @Override
    public String getContentUrl() {
        return this.ol;
    }
    
    @Override
    public List<ItemScope> getContributor() {
        return (List<ItemScope>)(ArrayList)this.ami;
    }
    
    @Override
    public String getDateCreated() {
        return this.amj;
    }
    
    @Override
    public String getDateModified() {
        return this.amk;
    }
    
    @Override
    public String getDatePublished() {
        return this.aml;
    }
    
    @Override
    public String getDescription() {
        return this.Tg;
    }
    
    @Override
    public String getDuration() {
        return this.amm;
    }
    
    @Override
    public String getEmbedUrl() {
        return this.amn;
    }
    
    @Override
    public String getEndDate() {
        return this.amo;
    }
    
    @Override
    public String getFamilyName() {
        return this.amp;
    }
    
    @Override
    public String getGender() {
        return this.amq;
    }
    
    @Override
    public ItemScope getGeo() {
        return this.amr;
    }
    
    @Override
    public String getGivenName() {
        return this.ams;
    }
    
    @Override
    public String getHeight() {
        return this.amt;
    }
    
    @Override
    public String getId() {
        return this.BL;
    }
    
    @Override
    public String getImage() {
        return this.amu;
    }
    
    @Override
    public ItemScope getInAlbum() {
        return this.amv;
    }
    
    @Override
    public double getLatitude() {
        return this.adZ;
    }
    
    @Override
    public ItemScope getLocation() {
        return this.amw;
    }
    
    @Override
    public double getLongitude() {
        return this.aea;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    @Override
    public ItemScope getPartOfTVSeries() {
        return this.amx;
    }
    
    @Override
    public List<ItemScope> getPerformers() {
        return (List<ItemScope>)(ArrayList)this.amy;
    }
    
    @Override
    public String getPlayerType() {
        return this.amz;
    }
    
    @Override
    public String getPostOfficeBoxNumber() {
        return this.amA;
    }
    
    @Override
    public String getPostalCode() {
        return this.amB;
    }
    
    @Override
    public String getRatingValue() {
        return this.amC;
    }
    
    @Override
    public ItemScope getReviewRating() {
        return this.amD;
    }
    
    @Override
    public String getStartDate() {
        return this.amE;
    }
    
    @Override
    public String getStreetAddress() {
        return this.amF;
    }
    
    @Override
    public String getText() {
        return this.amG;
    }
    
    @Override
    public ItemScope getThumbnail() {
        return this.amH;
    }
    
    @Override
    public String getThumbnailUrl() {
        return this.amI;
    }
    
    @Override
    public String getTickerSymbol() {
        return this.amJ;
    }
    
    @Override
    public String getType() {
        return this.uO;
    }
    
    @Override
    public String getUrl() {
        return this.uR;
    }
    
    @Override
    public String getWidth() {
        return this.amK;
    }
    
    @Override
    public String getWorstRating() {
        return this.amL;
    }
    
    @Override
    public boolean hasAbout() {
        return this.alR.contains(2);
    }
    
    @Override
    public boolean hasAdditionalName() {
        return this.alR.contains(3);
    }
    
    @Override
    public boolean hasAddress() {
        return this.alR.contains(4);
    }
    
    @Override
    public boolean hasAddressCountry() {
        return this.alR.contains(5);
    }
    
    @Override
    public boolean hasAddressLocality() {
        return this.alR.contains(6);
    }
    
    @Override
    public boolean hasAddressRegion() {
        return this.alR.contains(7);
    }
    
    @Override
    public boolean hasAssociated_media() {
        return this.alR.contains(8);
    }
    
    @Override
    public boolean hasAttendeeCount() {
        return this.alR.contains(9);
    }
    
    @Override
    public boolean hasAttendees() {
        return this.alR.contains(10);
    }
    
    @Override
    public boolean hasAudio() {
        return this.alR.contains(11);
    }
    
    @Override
    public boolean hasAuthor() {
        return this.alR.contains(12);
    }
    
    @Override
    public boolean hasBestRating() {
        return this.alR.contains(13);
    }
    
    @Override
    public boolean hasBirthDate() {
        return this.alR.contains(14);
    }
    
    @Override
    public boolean hasByArtist() {
        return this.alR.contains(15);
    }
    
    @Override
    public boolean hasCaption() {
        return this.alR.contains(16);
    }
    
    @Override
    public boolean hasContentSize() {
        return this.alR.contains(17);
    }
    
    @Override
    public boolean hasContentUrl() {
        return this.alR.contains(18);
    }
    
    @Override
    public boolean hasContributor() {
        return this.alR.contains(19);
    }
    
    @Override
    public boolean hasDateCreated() {
        return this.alR.contains(20);
    }
    
    @Override
    public boolean hasDateModified() {
        return this.alR.contains(21);
    }
    
    @Override
    public boolean hasDatePublished() {
        return this.alR.contains(22);
    }
    
    @Override
    public boolean hasDescription() {
        return this.alR.contains(23);
    }
    
    @Override
    public boolean hasDuration() {
        return this.alR.contains(24);
    }
    
    @Override
    public boolean hasEmbedUrl() {
        return this.alR.contains(25);
    }
    
    @Override
    public boolean hasEndDate() {
        return this.alR.contains(26);
    }
    
    @Override
    public boolean hasFamilyName() {
        return this.alR.contains(27);
    }
    
    @Override
    public boolean hasGender() {
        return this.alR.contains(28);
    }
    
    @Override
    public boolean hasGeo() {
        return this.alR.contains(29);
    }
    
    @Override
    public boolean hasGivenName() {
        return this.alR.contains(30);
    }
    
    @Override
    public boolean hasHeight() {
        return this.alR.contains(31);
    }
    
    @Override
    public boolean hasId() {
        return this.alR.contains(32);
    }
    
    @Override
    public boolean hasImage() {
        return this.alR.contains(33);
    }
    
    @Override
    public boolean hasInAlbum() {
        return this.alR.contains(34);
    }
    
    @Override
    public boolean hasLatitude() {
        return this.alR.contains(36);
    }
    
    @Override
    public boolean hasLocation() {
        return this.alR.contains(37);
    }
    
    @Override
    public boolean hasLongitude() {
        return this.alR.contains(38);
    }
    
    @Override
    public boolean hasName() {
        return this.alR.contains(39);
    }
    
    @Override
    public boolean hasPartOfTVSeries() {
        return this.alR.contains(40);
    }
    
    @Override
    public boolean hasPerformers() {
        return this.alR.contains(41);
    }
    
    @Override
    public boolean hasPlayerType() {
        return this.alR.contains(42);
    }
    
    @Override
    public boolean hasPostOfficeBoxNumber() {
        return this.alR.contains(43);
    }
    
    @Override
    public boolean hasPostalCode() {
        return this.alR.contains(44);
    }
    
    @Override
    public boolean hasRatingValue() {
        return this.alR.contains(45);
    }
    
    @Override
    public boolean hasReviewRating() {
        return this.alR.contains(46);
    }
    
    @Override
    public boolean hasStartDate() {
        return this.alR.contains(47);
    }
    
    @Override
    public boolean hasStreetAddress() {
        return this.alR.contains(48);
    }
    
    @Override
    public boolean hasText() {
        return this.alR.contains(49);
    }
    
    @Override
    public boolean hasThumbnail() {
        return this.alR.contains(50);
    }
    
    @Override
    public boolean hasThumbnailUrl() {
        return this.alR.contains(51);
    }
    
    @Override
    public boolean hasTickerSymbol() {
        return this.alR.contains(52);
    }
    
    @Override
    public boolean hasType() {
        return this.alR.contains(53);
    }
    
    @Override
    public boolean hasUrl() {
        return this.alR.contains(54);
    }
    
    @Override
    public boolean hasWidth() {
        return this.alR.contains(55);
    }
    
    @Override
    public boolean hasWorstRating() {
        return this.alR.contains(56);
    }
    
    @Override
    public int hashCode() {
        final Iterator<a<?, ?>> iterator = nt.alQ.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final a a = (a)iterator.next();
            if (this.a((a)a)) {
                n = this.b((a)a).hashCode() + (n + a.hm());
            }
        }
        return n;
    }
    
    @Override
    public HashMap<String, a<?, ?>> hf() {
        return nt.alQ;
    }
    
    @Override
    public boolean isDataValid() {
        return true;
    }
    
    public nt np() {
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final nu creator = nt.CREATOR;
        nu.a(this, parcel, n);
    }
}
