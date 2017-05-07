// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.people.Person$Urls;
import com.google.android.gms.plus.model.people.Person$PlacesLived;
import java.util.ArrayList;
import com.google.android.gms.plus.model.people.Person$Organizations;
import com.google.android.gms.plus.model.people.Person$Name;
import com.google.android.gms.plus.model.people.Person$Image;
import com.google.android.gms.plus.model.people.Person$Cover;
import com.google.android.gms.plus.model.people.Person$AgeRange;
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
    private static final HashMap<String, ji$a<?, ?>> alQ;
    String BL;
    final int BR;
    String Fc;
    String Nz;
    final Set<Integer> alR;
    String amP;
    ny$a amQ;
    String amR;
    String amS;
    int amT;
    ny$b amU;
    String amV;
    ny$c amW;
    boolean amX;
    ny$d amY;
    String amZ;
    int ana;
    List<ny$f> anb;
    List<ny$g> anc;
    int and;
    int ane;
    String anf;
    List<ny$h> ang;
    boolean anh;
    int om;
    String uR;
    
    static {
        CREATOR = new nz();
        (alQ = new HashMap<String, ji$a<?, ?>>()).put("aboutMe", ji$a.l("aboutMe", 2));
        ny.alQ.put("ageRange", ji$a.a("ageRange", 3, (Class<?>)ny$a.class));
        ny.alQ.put("birthday", ji$a.l("birthday", 4));
        ny.alQ.put("braggingRights", ji$a.l("braggingRights", 5));
        ny.alQ.put("circledByCount", ji$a.i("circledByCount", 6));
        ny.alQ.put("cover", ji$a.a("cover", 7, (Class<?>)ny$b.class));
        ny.alQ.put("currentLocation", ji$a.l("currentLocation", 8));
        ny.alQ.put("displayName", ji$a.l("displayName", 9));
        ny.alQ.put("gender", ji$a.a("gender", 12, new jf().h("male", 0).h("female", 1).h("other", 2), false));
        ny.alQ.put("id", ji$a.l("id", 14));
        ny.alQ.put("image", ji$a.a("image", 15, (Class<?>)ny$c.class));
        ny.alQ.put("isPlusUser", ji$a.k("isPlusUser", 16));
        ny.alQ.put("language", ji$a.l("language", 18));
        ny.alQ.put("name", ji$a.a("name", 19, (Class<?>)ny$d.class));
        ny.alQ.put("nickname", ji$a.l("nickname", 20));
        ny.alQ.put("objectType", ji$a.a("objectType", 21, new jf().h("person", 0).h("page", 1), false));
        ny.alQ.put("organizations", ji$a.b("organizations", 22, ny$f.class));
        ny.alQ.put("placesLived", ji$a.b("placesLived", 23, ny$g.class));
        ny.alQ.put("plusOneCount", ji$a.i("plusOneCount", 24));
        ny.alQ.put("relationshipStatus", ji$a.a("relationshipStatus", 25, new jf().h("single", 0).h("in_a_relationship", 1).h("engaged", 2).h("married", 3).h("its_complicated", 4).h("open_relationship", 5).h("widowed", 6).h("in_domestic_partnership", 7).h("in_civil_union", 8), false));
        ny.alQ.put("tagline", ji$a.l("tagline", 26));
        ny.alQ.put("url", ji$a.l("url", 27));
        ny.alQ.put("urls", ji$a.b("urls", 28, ny$h.class));
        ny.alQ.put("verified", ji$a.k("verified", 29));
    }
    
    public ny() {
        this.BR = 1;
        this.alR = new HashSet<Integer>();
    }
    
    public ny(final String nz, final String bl, final ny$c amW, final int ana, final String ur) {
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
    
    ny(final Set<Integer> alR, final int br, final String amP, final ny$a amQ, final String amR, final String amS, final int amT, final ny$b amU, final String amV, final String nz, final int om, final String bl, final ny$c amW, final boolean amX, final String fc, final ny$d amY, final String amZ, final int ana, final List<ny$f> anb, final List<ny$g> anc, final int and, final int ane, final String anf, final String ur, final List<ny$h> ang, final boolean anh) {
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
    protected boolean a(final ji$a ji$a) {
        return this.alR.contains(ji$a.hm());
    }
    
    @Override
    protected Object b(final ji$a ji$a) {
        switch (ji$a.hm()) {
            default: {
                throw new IllegalStateException("Unknown safe parcelable id=" + ji$a.hm());
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
        for (final ji$a<?, ?> ji$a : com.google.android.gms.internal.ny.alQ.values()) {
            if (this.a(ji$a)) {
                if (!ny.a(ji$a)) {
                    return false;
                }
                if (!this.b(ji$a).equals(ny.b(ji$a))) {
                    return false;
                }
                continue;
            }
            else {
                if (ny.a(ji$a)) {
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
    public Person$AgeRange getAgeRange() {
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
    public Person$Cover getCover() {
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
    public Person$Image getImage() {
        return this.amW;
    }
    
    @Override
    public String getLanguage() {
        return this.Fc;
    }
    
    @Override
    public Person$Name getName() {
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
    public List<Person$Organizations> getOrganizations() {
        return (List<Person$Organizations>)(ArrayList)this.anb;
    }
    
    @Override
    public List<Person$PlacesLived> getPlacesLived() {
        return (List<Person$PlacesLived>)(ArrayList)this.anc;
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
    public List<Person$Urls> getUrls() {
        return (List<Person$Urls>)(ArrayList)this.ang;
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
        final Iterator<ji$a<?, ?>> iterator = ny.alQ.values().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final ji$a<?, ?> ji$a = iterator.next();
            if (this.a(ji$a)) {
                n = this.b(ji$a).hashCode() + (n + ji$a.hm());
            }
        }
        return n;
    }
    
    @Override
    public HashMap<String, ji$a<?, ?>> hf() {
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
}
