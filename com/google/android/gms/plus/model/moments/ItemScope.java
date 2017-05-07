// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.internal.ib;
import java.util.List;
import com.google.android.gms.common.data.Freezable;

public interface ItemScope extends Freezable<ItemScope>
{
    ItemScope getAbout();
    
    List<String> getAdditionalName();
    
    ItemScope getAddress();
    
    String getAddressCountry();
    
    String getAddressLocality();
    
    String getAddressRegion();
    
    List<ItemScope> getAssociated_media();
    
    int getAttendeeCount();
    
    List<ItemScope> getAttendees();
    
    ItemScope getAudio();
    
    List<ItemScope> getAuthor();
    
    String getBestRating();
    
    String getBirthDate();
    
    ItemScope getByArtist();
    
    String getCaption();
    
    String getContentSize();
    
    String getContentUrl();
    
    List<ItemScope> getContributor();
    
    String getDateCreated();
    
    String getDateModified();
    
    String getDatePublished();
    
    String getDescription();
    
    String getDuration();
    
    String getEmbedUrl();
    
    String getEndDate();
    
    String getFamilyName();
    
    String getGender();
    
    ItemScope getGeo();
    
    String getGivenName();
    
    String getHeight();
    
    String getId();
    
    String getImage();
    
    ItemScope getInAlbum();
    
    double getLatitude();
    
    ItemScope getLocation();
    
    double getLongitude();
    
    String getName();
    
    ItemScope getPartOfTVSeries();
    
    List<ItemScope> getPerformers();
    
    String getPlayerType();
    
    String getPostOfficeBoxNumber();
    
    String getPostalCode();
    
    String getRatingValue();
    
    ItemScope getReviewRating();
    
    String getStartDate();
    
    String getStreetAddress();
    
    String getText();
    
    ItemScope getThumbnail();
    
    String getThumbnailUrl();
    
    String getTickerSymbol();
    
    String getType();
    
    String getUrl();
    
    String getWidth();
    
    String getWorstRating();
    
    boolean hasAbout();
    
    boolean hasAdditionalName();
    
    boolean hasAddress();
    
    boolean hasAddressCountry();
    
    boolean hasAddressLocality();
    
    boolean hasAddressRegion();
    
    boolean hasAssociated_media();
    
    boolean hasAttendeeCount();
    
    boolean hasAttendees();
    
    boolean hasAudio();
    
    boolean hasAuthor();
    
    boolean hasBestRating();
    
    boolean hasBirthDate();
    
    boolean hasByArtist();
    
    boolean hasCaption();
    
    boolean hasContentSize();
    
    boolean hasContentUrl();
    
    boolean hasContributor();
    
    boolean hasDateCreated();
    
    boolean hasDateModified();
    
    boolean hasDatePublished();
    
    boolean hasDescription();
    
    boolean hasDuration();
    
    boolean hasEmbedUrl();
    
    boolean hasEndDate();
    
    boolean hasFamilyName();
    
    boolean hasGender();
    
    boolean hasGeo();
    
    boolean hasGivenName();
    
    boolean hasHeight();
    
    boolean hasId();
    
    boolean hasImage();
    
    boolean hasInAlbum();
    
    boolean hasLatitude();
    
    boolean hasLocation();
    
    boolean hasLongitude();
    
    boolean hasName();
    
    boolean hasPartOfTVSeries();
    
    boolean hasPerformers();
    
    boolean hasPlayerType();
    
    boolean hasPostOfficeBoxNumber();
    
    boolean hasPostalCode();
    
    boolean hasRatingValue();
    
    boolean hasReviewRating();
    
    boolean hasStartDate();
    
    boolean hasStreetAddress();
    
    boolean hasText();
    
    boolean hasThumbnail();
    
    boolean hasThumbnailUrl();
    
    boolean hasTickerSymbol();
    
    boolean hasType();
    
    boolean hasUrl();
    
    boolean hasWidth();
    
    boolean hasWorstRating();
    
    public static class Builder
    {
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
        private String mName;
        private String sJ;
        private String uS;
        private double xw;
        private double xx;
        
        public Builder() {
            this.Eq = new HashSet<Integer>();
        }
        
        public ItemScope build() {
            return new ib(this.Eq, this.Er, this.Es, this.Et, this.Eu, this.Ev, this.Ew, this.Ex, this.Ey, this.Ez, this.EA, this.EB, this.EC, this.ED, this.EE, this.EF, this.EG, this.EH, this.EI, this.EJ, this.EK, this.EL, this.sJ, this.EM, this.EN, this.EO, this.EP, this.EQ, this.ER, this.ES, this.ET, this.uS, this.EU, this.EV, this.xw, this.EW, this.xx, this.mName, this.EX, this.EY, this.EZ, this.Fa, this.Fb, this.Fc, this.Fd, this.Fe, this.Ff, this.Fg, this.Fh, this.Fi, this.Fj, this.AI, this.iH, this.Fk, this.Fl);
        }
        
        public Builder setAbout(final ItemScope itemScope) {
            this.Er = (ib)itemScope;
            this.Eq.add(2);
            return this;
        }
        
        public Builder setAdditionalName(final List<String> es) {
            this.Es = es;
            this.Eq.add(3);
            return this;
        }
        
        public Builder setAddress(final ItemScope itemScope) {
            this.Et = (ib)itemScope;
            this.Eq.add(4);
            return this;
        }
        
        public Builder setAddressCountry(final String eu) {
            this.Eu = eu;
            this.Eq.add(5);
            return this;
        }
        
        public Builder setAddressLocality(final String ev) {
            this.Ev = ev;
            this.Eq.add(6);
            return this;
        }
        
        public Builder setAddressRegion(final String ew) {
            this.Ew = ew;
            this.Eq.add(7);
            return this;
        }
        
        public Builder setAssociated_media(final List<ItemScope> ex) {
            this.Ex = (List<ib>)ex;
            this.Eq.add(8);
            return this;
        }
        
        public Builder setAttendeeCount(final int ey) {
            this.Ey = ey;
            this.Eq.add(9);
            return this;
        }
        
        public Builder setAttendees(final List<ItemScope> ez) {
            this.Ez = (List<ib>)ez;
            this.Eq.add(10);
            return this;
        }
        
        public Builder setAudio(final ItemScope itemScope) {
            this.EA = (ib)itemScope;
            this.Eq.add(11);
            return this;
        }
        
        public Builder setAuthor(final List<ItemScope> eb) {
            this.EB = (List<ib>)eb;
            this.Eq.add(12);
            return this;
        }
        
        public Builder setBestRating(final String ec) {
            this.EC = ec;
            this.Eq.add(13);
            return this;
        }
        
        public Builder setBirthDate(final String ed) {
            this.ED = ed;
            this.Eq.add(14);
            return this;
        }
        
        public Builder setByArtist(final ItemScope itemScope) {
            this.EE = (ib)itemScope;
            this.Eq.add(15);
            return this;
        }
        
        public Builder setCaption(final String ef) {
            this.EF = ef;
            this.Eq.add(16);
            return this;
        }
        
        public Builder setContentSize(final String eg) {
            this.EG = eg;
            this.Eq.add(17);
            return this;
        }
        
        public Builder setContentUrl(final String eh) {
            this.EH = eh;
            this.Eq.add(18);
            return this;
        }
        
        public Builder setContributor(final List<ItemScope> ei) {
            this.EI = (List<ib>)ei;
            this.Eq.add(19);
            return this;
        }
        
        public Builder setDateCreated(final String ej) {
            this.EJ = ej;
            this.Eq.add(20);
            return this;
        }
        
        public Builder setDateModified(final String ek) {
            this.EK = ek;
            this.Eq.add(21);
            return this;
        }
        
        public Builder setDatePublished(final String el) {
            this.EL = el;
            this.Eq.add(22);
            return this;
        }
        
        public Builder setDescription(final String sj) {
            this.sJ = sj;
            this.Eq.add(23);
            return this;
        }
        
        public Builder setDuration(final String em) {
            this.EM = em;
            this.Eq.add(24);
            return this;
        }
        
        public Builder setEmbedUrl(final String en) {
            this.EN = en;
            this.Eq.add(25);
            return this;
        }
        
        public Builder setEndDate(final String eo) {
            this.EO = eo;
            this.Eq.add(26);
            return this;
        }
        
        public Builder setFamilyName(final String ep) {
            this.EP = ep;
            this.Eq.add(27);
            return this;
        }
        
        public Builder setGender(final String eq) {
            this.EQ = eq;
            this.Eq.add(28);
            return this;
        }
        
        public Builder setGeo(final ItemScope itemScope) {
            this.ER = (ib)itemScope;
            this.Eq.add(29);
            return this;
        }
        
        public Builder setGivenName(final String es) {
            this.ES = es;
            this.Eq.add(30);
            return this;
        }
        
        public Builder setHeight(final String et) {
            this.ET = et;
            this.Eq.add(31);
            return this;
        }
        
        public Builder setId(final String us) {
            this.uS = us;
            this.Eq.add(32);
            return this;
        }
        
        public Builder setImage(final String eu) {
            this.EU = eu;
            this.Eq.add(33);
            return this;
        }
        
        public Builder setInAlbum(final ItemScope itemScope) {
            this.EV = (ib)itemScope;
            this.Eq.add(34);
            return this;
        }
        
        public Builder setLatitude(final double xw) {
            this.xw = xw;
            this.Eq.add(36);
            return this;
        }
        
        public Builder setLocation(final ItemScope itemScope) {
            this.EW = (ib)itemScope;
            this.Eq.add(37);
            return this;
        }
        
        public Builder setLongitude(final double xx) {
            this.xx = xx;
            this.Eq.add(38);
            return this;
        }
        
        public Builder setName(final String mName) {
            this.mName = mName;
            this.Eq.add(39);
            return this;
        }
        
        public Builder setPartOfTVSeries(final ItemScope itemScope) {
            this.EX = (ib)itemScope;
            this.Eq.add(40);
            return this;
        }
        
        public Builder setPerformers(final List<ItemScope> ey) {
            this.EY = (List<ib>)ey;
            this.Eq.add(41);
            return this;
        }
        
        public Builder setPlayerType(final String ez) {
            this.EZ = ez;
            this.Eq.add(42);
            return this;
        }
        
        public Builder setPostOfficeBoxNumber(final String fa) {
            this.Fa = fa;
            this.Eq.add(43);
            return this;
        }
        
        public Builder setPostalCode(final String fb) {
            this.Fb = fb;
            this.Eq.add(44);
            return this;
        }
        
        public Builder setRatingValue(final String fc) {
            this.Fc = fc;
            this.Eq.add(45);
            return this;
        }
        
        public Builder setReviewRating(final ItemScope itemScope) {
            this.Fd = (ib)itemScope;
            this.Eq.add(46);
            return this;
        }
        
        public Builder setStartDate(final String fe) {
            this.Fe = fe;
            this.Eq.add(47);
            return this;
        }
        
        public Builder setStreetAddress(final String ff) {
            this.Ff = ff;
            this.Eq.add(48);
            return this;
        }
        
        public Builder setText(final String fg) {
            this.Fg = fg;
            this.Eq.add(49);
            return this;
        }
        
        public Builder setThumbnail(final ItemScope itemScope) {
            this.Fh = (ib)itemScope;
            this.Eq.add(50);
            return this;
        }
        
        public Builder setThumbnailUrl(final String fi) {
            this.Fi = fi;
            this.Eq.add(51);
            return this;
        }
        
        public Builder setTickerSymbol(final String fj) {
            this.Fj = fj;
            this.Eq.add(52);
            return this;
        }
        
        public Builder setType(final String ai) {
            this.AI = ai;
            this.Eq.add(53);
            return this;
        }
        
        public Builder setUrl(final String ih) {
            this.iH = ih;
            this.Eq.add(54);
            return this;
        }
        
        public Builder setWidth(final String fk) {
            this.Fk = fk;
            this.Eq.add(55);
            return this;
        }
        
        public Builder setWorstRating(final String fl) {
            this.Fl = fl;
            this.Eq.add(56);
            return this;
        }
    }
}
