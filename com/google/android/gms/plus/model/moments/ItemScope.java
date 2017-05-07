// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import java.util.HashSet;
import com.google.android.gms.internal.nt;
import java.util.Set;
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
        private String BL;
        private String Tg;
        private double adZ;
        private double aea;
        private final Set<Integer> alR;
        private nt alS;
        private List<String> alT;
        private nt alU;
        private String alV;
        private String alW;
        private String alX;
        private List<nt> alY;
        private int alZ;
        private String amA;
        private String amB;
        private String amC;
        private nt amD;
        private String amE;
        private String amF;
        private String amG;
        private nt amH;
        private String amI;
        private String amJ;
        private String amK;
        private String amL;
        private List<nt> ama;
        private nt amb;
        private List<nt> amc;
        private String amd;
        private String ame;
        private nt amf;
        private String amg;
        private String amh;
        private List<nt> ami;
        private String amj;
        private String amk;
        private String aml;
        private String amm;
        private String amn;
        private String amo;
        private String amp;
        private String amq;
        private nt amr;
        private String ams;
        private String amt;
        private String amu;
        private nt amv;
        private nt amw;
        private nt amx;
        private List<nt> amy;
        private String amz;
        private String mName;
        private String ol;
        private String uO;
        private String uR;
        
        public Builder() {
            this.alR = new HashSet<Integer>();
        }
        
        public ItemScope build() {
            return new nt(this.alR, this.alS, this.alT, this.alU, this.alV, this.alW, this.alX, this.alY, this.alZ, this.ama, this.amb, this.amc, this.amd, this.ame, this.amf, this.amg, this.amh, this.ol, this.ami, this.amj, this.amk, this.aml, this.Tg, this.amm, this.amn, this.amo, this.amp, this.amq, this.amr, this.ams, this.amt, this.BL, this.amu, this.amv, this.adZ, this.amw, this.aea, this.mName, this.amx, this.amy, this.amz, this.amA, this.amB, this.amC, this.amD, this.amE, this.amF, this.amG, this.amH, this.amI, this.amJ, this.uO, this.uR, this.amK, this.amL);
        }
        
        public Builder setAbout(final ItemScope itemScope) {
            this.alS = (nt)itemScope;
            this.alR.add(2);
            return this;
        }
        
        public Builder setAdditionalName(final List<String> alT) {
            this.alT = alT;
            this.alR.add(3);
            return this;
        }
        
        public Builder setAddress(final ItemScope itemScope) {
            this.alU = (nt)itemScope;
            this.alR.add(4);
            return this;
        }
        
        public Builder setAddressCountry(final String alV) {
            this.alV = alV;
            this.alR.add(5);
            return this;
        }
        
        public Builder setAddressLocality(final String alW) {
            this.alW = alW;
            this.alR.add(6);
            return this;
        }
        
        public Builder setAddressRegion(final String alX) {
            this.alX = alX;
            this.alR.add(7);
            return this;
        }
        
        public Builder setAssociated_media(final List<ItemScope> alY) {
            this.alY = (List<nt>)alY;
            this.alR.add(8);
            return this;
        }
        
        public Builder setAttendeeCount(final int alZ) {
            this.alZ = alZ;
            this.alR.add(9);
            return this;
        }
        
        public Builder setAttendees(final List<ItemScope> ama) {
            this.ama = (List<nt>)ama;
            this.alR.add(10);
            return this;
        }
        
        public Builder setAudio(final ItemScope itemScope) {
            this.amb = (nt)itemScope;
            this.alR.add(11);
            return this;
        }
        
        public Builder setAuthor(final List<ItemScope> amc) {
            this.amc = (List<nt>)amc;
            this.alR.add(12);
            return this;
        }
        
        public Builder setBestRating(final String amd) {
            this.amd = amd;
            this.alR.add(13);
            return this;
        }
        
        public Builder setBirthDate(final String ame) {
            this.ame = ame;
            this.alR.add(14);
            return this;
        }
        
        public Builder setByArtist(final ItemScope itemScope) {
            this.amf = (nt)itemScope;
            this.alR.add(15);
            return this;
        }
        
        public Builder setCaption(final String amg) {
            this.amg = amg;
            this.alR.add(16);
            return this;
        }
        
        public Builder setContentSize(final String amh) {
            this.amh = amh;
            this.alR.add(17);
            return this;
        }
        
        public Builder setContentUrl(final String ol) {
            this.ol = ol;
            this.alR.add(18);
            return this;
        }
        
        public Builder setContributor(final List<ItemScope> ami) {
            this.ami = (List<nt>)ami;
            this.alR.add(19);
            return this;
        }
        
        public Builder setDateCreated(final String amj) {
            this.amj = amj;
            this.alR.add(20);
            return this;
        }
        
        public Builder setDateModified(final String amk) {
            this.amk = amk;
            this.alR.add(21);
            return this;
        }
        
        public Builder setDatePublished(final String aml) {
            this.aml = aml;
            this.alR.add(22);
            return this;
        }
        
        public Builder setDescription(final String tg) {
            this.Tg = tg;
            this.alR.add(23);
            return this;
        }
        
        public Builder setDuration(final String amm) {
            this.amm = amm;
            this.alR.add(24);
            return this;
        }
        
        public Builder setEmbedUrl(final String amn) {
            this.amn = amn;
            this.alR.add(25);
            return this;
        }
        
        public Builder setEndDate(final String amo) {
            this.amo = amo;
            this.alR.add(26);
            return this;
        }
        
        public Builder setFamilyName(final String amp) {
            this.amp = amp;
            this.alR.add(27);
            return this;
        }
        
        public Builder setGender(final String amq) {
            this.amq = amq;
            this.alR.add(28);
            return this;
        }
        
        public Builder setGeo(final ItemScope itemScope) {
            this.amr = (nt)itemScope;
            this.alR.add(29);
            return this;
        }
        
        public Builder setGivenName(final String ams) {
            this.ams = ams;
            this.alR.add(30);
            return this;
        }
        
        public Builder setHeight(final String amt) {
            this.amt = amt;
            this.alR.add(31);
            return this;
        }
        
        public Builder setId(final String bl) {
            this.BL = bl;
            this.alR.add(32);
            return this;
        }
        
        public Builder setImage(final String amu) {
            this.amu = amu;
            this.alR.add(33);
            return this;
        }
        
        public Builder setInAlbum(final ItemScope itemScope) {
            this.amv = (nt)itemScope;
            this.alR.add(34);
            return this;
        }
        
        public Builder setLatitude(final double adZ) {
            this.adZ = adZ;
            this.alR.add(36);
            return this;
        }
        
        public Builder setLocation(final ItemScope itemScope) {
            this.amw = (nt)itemScope;
            this.alR.add(37);
            return this;
        }
        
        public Builder setLongitude(final double aea) {
            this.aea = aea;
            this.alR.add(38);
            return this;
        }
        
        public Builder setName(final String mName) {
            this.mName = mName;
            this.alR.add(39);
            return this;
        }
        
        public Builder setPartOfTVSeries(final ItemScope itemScope) {
            this.amx = (nt)itemScope;
            this.alR.add(40);
            return this;
        }
        
        public Builder setPerformers(final List<ItemScope> amy) {
            this.amy = (List<nt>)amy;
            this.alR.add(41);
            return this;
        }
        
        public Builder setPlayerType(final String amz) {
            this.amz = amz;
            this.alR.add(42);
            return this;
        }
        
        public Builder setPostOfficeBoxNumber(final String amA) {
            this.amA = amA;
            this.alR.add(43);
            return this;
        }
        
        public Builder setPostalCode(final String amB) {
            this.amB = amB;
            this.alR.add(44);
            return this;
        }
        
        public Builder setRatingValue(final String amC) {
            this.amC = amC;
            this.alR.add(45);
            return this;
        }
        
        public Builder setReviewRating(final ItemScope itemScope) {
            this.amD = (nt)itemScope;
            this.alR.add(46);
            return this;
        }
        
        public Builder setStartDate(final String amE) {
            this.amE = amE;
            this.alR.add(47);
            return this;
        }
        
        public Builder setStreetAddress(final String amF) {
            this.amF = amF;
            this.alR.add(48);
            return this;
        }
        
        public Builder setText(final String amG) {
            this.amG = amG;
            this.alR.add(49);
            return this;
        }
        
        public Builder setThumbnail(final ItemScope itemScope) {
            this.amH = (nt)itemScope;
            this.alR.add(50);
            return this;
        }
        
        public Builder setThumbnailUrl(final String amI) {
            this.amI = amI;
            this.alR.add(51);
            return this;
        }
        
        public Builder setTickerSymbol(final String amJ) {
            this.amJ = amJ;
            this.alR.add(52);
            return this;
        }
        
        public Builder setType(final String uo) {
            this.uO = uo;
            this.alR.add(53);
            return this;
        }
        
        public Builder setUrl(final String ur) {
            this.uR = ur;
            this.alR.add(54);
            return this;
        }
        
        public Builder setWidth(final String amK) {
            this.amK = amK;
            this.alR.add(55);
            return this;
        }
        
        public Builder setWorstRating(final String amL) {
            this.amL = amL;
            this.alR.add(56);
            return this;
        }
    }
}
