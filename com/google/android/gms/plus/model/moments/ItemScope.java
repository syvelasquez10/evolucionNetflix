// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.model.moments;

import java.util.HashSet;
import com.google.android.gms.internal.ic;
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
        
        public Builder() {
            this.UJ = new HashSet<Integer>();
        }
        
        public ItemScope build() {
            return new ic(this.UJ, this.UK, this.UL, this.UM, this.UN, this.UO, this.UP, this.UQ, this.UR, this.US, this.UT, this.UU, this.UV, this.UW, this.UX, this.UY, this.UZ, this.lY, this.Va, this.Vb, this.Vc, this.Vd, this.HD, this.Ve, this.Vf, this.Vg, this.Vh, this.Vi, this.Vj, this.Vk, this.Vl, this.wp, this.Vm, this.Vn, this.NX, this.Vo, this.NY, this.mName, this.Vp, this.Vq, this.Vr, this.Vs, this.Vt, this.Vu, this.Vv, this.Vw, this.Vx, this.Vy, this.Vz, this.VA, this.VB, this.Rd, this.ro, this.VC, this.VD);
        }
        
        public Builder setAbout(final ItemScope itemScope) {
            this.UK = (ic)itemScope;
            this.UJ.add(2);
            return this;
        }
        
        public Builder setAdditionalName(final List<String> ul) {
            this.UL = ul;
            this.UJ.add(3);
            return this;
        }
        
        public Builder setAddress(final ItemScope itemScope) {
            this.UM = (ic)itemScope;
            this.UJ.add(4);
            return this;
        }
        
        public Builder setAddressCountry(final String un) {
            this.UN = un;
            this.UJ.add(5);
            return this;
        }
        
        public Builder setAddressLocality(final String uo) {
            this.UO = uo;
            this.UJ.add(6);
            return this;
        }
        
        public Builder setAddressRegion(final String up) {
            this.UP = up;
            this.UJ.add(7);
            return this;
        }
        
        public Builder setAssociated_media(final List<ItemScope> uq) {
            this.UQ = (List<ic>)uq;
            this.UJ.add(8);
            return this;
        }
        
        public Builder setAttendeeCount(final int ur) {
            this.UR = ur;
            this.UJ.add(9);
            return this;
        }
        
        public Builder setAttendees(final List<ItemScope> us) {
            this.US = (List<ic>)us;
            this.UJ.add(10);
            return this;
        }
        
        public Builder setAudio(final ItemScope itemScope) {
            this.UT = (ic)itemScope;
            this.UJ.add(11);
            return this;
        }
        
        public Builder setAuthor(final List<ItemScope> uu) {
            this.UU = (List<ic>)uu;
            this.UJ.add(12);
            return this;
        }
        
        public Builder setBestRating(final String uv) {
            this.UV = uv;
            this.UJ.add(13);
            return this;
        }
        
        public Builder setBirthDate(final String uw) {
            this.UW = uw;
            this.UJ.add(14);
            return this;
        }
        
        public Builder setByArtist(final ItemScope itemScope) {
            this.UX = (ic)itemScope;
            this.UJ.add(15);
            return this;
        }
        
        public Builder setCaption(final String uy) {
            this.UY = uy;
            this.UJ.add(16);
            return this;
        }
        
        public Builder setContentSize(final String uz) {
            this.UZ = uz;
            this.UJ.add(17);
            return this;
        }
        
        public Builder setContentUrl(final String ly) {
            this.lY = ly;
            this.UJ.add(18);
            return this;
        }
        
        public Builder setContributor(final List<ItemScope> va) {
            this.Va = (List<ic>)va;
            this.UJ.add(19);
            return this;
        }
        
        public Builder setDateCreated(final String vb) {
            this.Vb = vb;
            this.UJ.add(20);
            return this;
        }
        
        public Builder setDateModified(final String vc) {
            this.Vc = vc;
            this.UJ.add(21);
            return this;
        }
        
        public Builder setDatePublished(final String vd) {
            this.Vd = vd;
            this.UJ.add(22);
            return this;
        }
        
        public Builder setDescription(final String hd) {
            this.HD = hd;
            this.UJ.add(23);
            return this;
        }
        
        public Builder setDuration(final String ve) {
            this.Ve = ve;
            this.UJ.add(24);
            return this;
        }
        
        public Builder setEmbedUrl(final String vf) {
            this.Vf = vf;
            this.UJ.add(25);
            return this;
        }
        
        public Builder setEndDate(final String vg) {
            this.Vg = vg;
            this.UJ.add(26);
            return this;
        }
        
        public Builder setFamilyName(final String vh) {
            this.Vh = vh;
            this.UJ.add(27);
            return this;
        }
        
        public Builder setGender(final String vi) {
            this.Vi = vi;
            this.UJ.add(28);
            return this;
        }
        
        public Builder setGeo(final ItemScope itemScope) {
            this.Vj = (ic)itemScope;
            this.UJ.add(29);
            return this;
        }
        
        public Builder setGivenName(final String vk) {
            this.Vk = vk;
            this.UJ.add(30);
            return this;
        }
        
        public Builder setHeight(final String vl) {
            this.Vl = vl;
            this.UJ.add(31);
            return this;
        }
        
        public Builder setId(final String wp) {
            this.wp = wp;
            this.UJ.add(32);
            return this;
        }
        
        public Builder setImage(final String vm) {
            this.Vm = vm;
            this.UJ.add(33);
            return this;
        }
        
        public Builder setInAlbum(final ItemScope itemScope) {
            this.Vn = (ic)itemScope;
            this.UJ.add(34);
            return this;
        }
        
        public Builder setLatitude(final double nx) {
            this.NX = nx;
            this.UJ.add(36);
            return this;
        }
        
        public Builder setLocation(final ItemScope itemScope) {
            this.Vo = (ic)itemScope;
            this.UJ.add(37);
            return this;
        }
        
        public Builder setLongitude(final double ny) {
            this.NY = ny;
            this.UJ.add(38);
            return this;
        }
        
        public Builder setName(final String mName) {
            this.mName = mName;
            this.UJ.add(39);
            return this;
        }
        
        public Builder setPartOfTVSeries(final ItemScope itemScope) {
            this.Vp = (ic)itemScope;
            this.UJ.add(40);
            return this;
        }
        
        public Builder setPerformers(final List<ItemScope> vq) {
            this.Vq = (List<ic>)vq;
            this.UJ.add(41);
            return this;
        }
        
        public Builder setPlayerType(final String vr) {
            this.Vr = vr;
            this.UJ.add(42);
            return this;
        }
        
        public Builder setPostOfficeBoxNumber(final String vs) {
            this.Vs = vs;
            this.UJ.add(43);
            return this;
        }
        
        public Builder setPostalCode(final String vt) {
            this.Vt = vt;
            this.UJ.add(44);
            return this;
        }
        
        public Builder setRatingValue(final String vu) {
            this.Vu = vu;
            this.UJ.add(45);
            return this;
        }
        
        public Builder setReviewRating(final ItemScope itemScope) {
            this.Vv = (ic)itemScope;
            this.UJ.add(46);
            return this;
        }
        
        public Builder setStartDate(final String vw) {
            this.Vw = vw;
            this.UJ.add(47);
            return this;
        }
        
        public Builder setStreetAddress(final String vx) {
            this.Vx = vx;
            this.UJ.add(48);
            return this;
        }
        
        public Builder setText(final String vy) {
            this.Vy = vy;
            this.UJ.add(49);
            return this;
        }
        
        public Builder setThumbnail(final ItemScope itemScope) {
            this.Vz = (ic)itemScope;
            this.UJ.add(50);
            return this;
        }
        
        public Builder setThumbnailUrl(final String va) {
            this.VA = va;
            this.UJ.add(51);
            return this;
        }
        
        public Builder setTickerSymbol(final String vb) {
            this.VB = vb;
            this.UJ.add(52);
            return this;
        }
        
        public Builder setType(final String rd) {
            this.Rd = rd;
            this.UJ.add(53);
            return this;
        }
        
        public Builder setUrl(final String ro) {
            this.ro = ro;
            this.UJ.add(54);
            return this;
        }
        
        public Builder setWidth(final String vc) {
            this.VC = vc;
            this.UJ.add(55);
            return this;
        }
        
        public Builder setWorstRating(final String vd) {
            this.VD = vd;
            this.UJ.add(56);
            return this;
        }
    }
}
