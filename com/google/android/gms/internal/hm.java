// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class hm implements SafeParcelable
{
    public static final hn CREATOR;
    public static final hm OH;
    public static final hm OI;
    public static final hm OJ;
    public static final hm OK;
    public static final hm OL;
    public static final hm OM;
    public static final hm ON;
    public static final hm OO;
    public static final hm OP;
    public static final hm OQ;
    public static final hm OR;
    public static final hm OS;
    public static final hm OT;
    public static final hm OU;
    public static final hm OV;
    public static final hm OW;
    public static final hm OX;
    public static final hm OY;
    public static final hm OZ;
    public static final hm PA;
    public static final hm PB;
    public static final hm PC;
    public static final hm PD;
    public static final hm PE;
    public static final hm PF;
    public static final hm PG;
    public static final hm PH;
    public static final hm PI;
    public static final hm PJ;
    public static final hm PK;
    public static final hm PL;
    public static final hm PM;
    public static final hm PN;
    public static final hm PO;
    public static final hm PP;
    public static final hm PQ;
    public static final hm PR;
    public static final hm PS;
    public static final hm PT;
    public static final hm PU;
    public static final hm PV;
    public static final hm PW;
    public static final hm PX;
    public static final hm PY;
    public static final hm PZ;
    public static final hm Pa;
    public static final hm Pb;
    public static final hm Pc;
    public static final hm Pd;
    public static final hm Pe;
    public static final hm Pf;
    public static final hm Pg;
    public static final hm Ph;
    public static final hm Pi;
    public static final hm Pj;
    public static final hm Pk;
    public static final hm Pl;
    public static final hm Pm;
    public static final hm Pn;
    public static final hm Po;
    public static final hm Pp;
    public static final hm Pq;
    public static final hm Pr;
    public static final hm Ps;
    public static final hm Pt;
    public static final hm Pu;
    public static final hm Pv;
    public static final hm Pw;
    public static final hm Px;
    public static final hm Py;
    public static final hm Pz;
    public static final hm QA;
    public static final hm QB;
    public static final hm QC;
    public static final hm QD;
    public static final hm QE;
    public static final hm QF;
    public static final hm QG;
    public static final hm QH;
    public static final hm QI;
    public static final hm QJ;
    public static final hm QK;
    public static final hm QL;
    public static final hm QM;
    public static final hm QN;
    public static final hm QO;
    public static final hm QP;
    public static final hm QQ;
    public static final hm QR;
    public static final hm QS;
    public static final hm QT;
    public static final hm QU;
    public static final hm QV;
    public static final hm QW;
    public static final hm QX;
    public static final hm QY;
    public static final hm QZ;
    public static final hm Qa;
    public static final hm Qb;
    public static final hm Qc;
    public static final hm Qd;
    public static final hm Qe;
    public static final hm Qf;
    public static final hm Qg;
    public static final hm Qh;
    public static final hm Qi;
    public static final hm Qj;
    public static final hm Qk;
    public static final hm Ql;
    public static final hm Qm;
    public static final hm Qn;
    public static final hm Qo;
    public static final hm Qp;
    public static final hm Qq;
    public static final hm Qr;
    public static final hm Qs;
    public static final hm Qt;
    public static final hm Qu;
    public static final hm Qv;
    public static final hm Qw;
    public static final hm Qx;
    public static final hm Qy;
    public static final hm Qz;
    public static final hm Ra;
    public static final hm Rb;
    public static final hm Rc;
    final String Rd;
    final int xH;
    
    static {
        OH = aZ("accounting");
        OI = aZ("airport");
        OJ = aZ("amusement_park");
        OK = aZ("aquarium");
        OL = aZ("art_gallery");
        OM = aZ("atm");
        ON = aZ("bakery");
        OO = aZ("bank");
        OP = aZ("bar");
        OQ = aZ("beauty_salon");
        OR = aZ("bicycle_store");
        OS = aZ("book_store");
        OT = aZ("bowling_alley");
        OU = aZ("bus_station");
        OV = aZ("cafe");
        OW = aZ("campground");
        OX = aZ("car_dealer");
        OY = aZ("car_rental");
        OZ = aZ("car_repair");
        Pa = aZ("car_wash");
        Pb = aZ("casino");
        Pc = aZ("cemetery");
        Pd = aZ("church");
        Pe = aZ("city_hall");
        Pf = aZ("clothing_store");
        Pg = aZ("convenience_store");
        Ph = aZ("courthouse");
        Pi = aZ("dentist");
        Pj = aZ("department_store");
        Pk = aZ("doctor");
        Pl = aZ("electrician");
        Pm = aZ("electronics_store");
        Pn = aZ("embassy");
        Po = aZ("establishment");
        Pp = aZ("finance");
        Pq = aZ("fire_station");
        Pr = aZ("florist");
        Ps = aZ("food");
        Pt = aZ("funeral_home");
        Pu = aZ("furniture_store");
        Pv = aZ("gas_station");
        Pw = aZ("general_contractor");
        Px = aZ("grocery_or_supermarket");
        Py = aZ("gym");
        Pz = aZ("hair_care");
        PA = aZ("hardware_store");
        PB = aZ("health");
        PC = aZ("hindu_temple");
        PD = aZ("home_goods_store");
        PE = aZ("hospital");
        PF = aZ("insurance_agency");
        PG = aZ("jewelry_store");
        PH = aZ("laundry");
        PI = aZ("lawyer");
        PJ = aZ("library");
        PK = aZ("liquor_store");
        PL = aZ("local_government_office");
        PM = aZ("locksmith");
        PN = aZ("lodging");
        PO = aZ("meal_delivery");
        PP = aZ("meal_takeaway");
        PQ = aZ("mosque");
        PR = aZ("movie_rental");
        PS = aZ("movie_theater");
        PT = aZ("moving_company");
        PU = aZ("museum");
        PV = aZ("night_club");
        PW = aZ("painter");
        PX = aZ("park");
        PY = aZ("parking");
        PZ = aZ("pet_store");
        Qa = aZ("pharmacy");
        Qb = aZ("physiotherapist");
        Qc = aZ("place_of_worship");
        Qd = aZ("plumber");
        Qe = aZ("police");
        Qf = aZ("post_office");
        Qg = aZ("real_estate_agency");
        Qh = aZ("restaurant");
        Qi = aZ("roofing_contractor");
        Qj = aZ("rv_park");
        Qk = aZ("school");
        Ql = aZ("shoe_store");
        Qm = aZ("shopping_mall");
        Qn = aZ("spa");
        Qo = aZ("stadium");
        Qp = aZ("storage");
        Qq = aZ("store");
        Qr = aZ("subway_station");
        Qs = aZ("synagogue");
        Qt = aZ("taxi_stand");
        Qu = aZ("train_station");
        Qv = aZ("travel_agency");
        Qw = aZ("university");
        Qx = aZ("veterinary_care");
        Qy = aZ("zoo");
        Qz = aZ("administrative_area_level_1");
        QA = aZ("administrative_area_level_2");
        QB = aZ("administrative_area_level_3");
        QC = aZ("colloquial_area");
        QD = aZ("country");
        QE = aZ("floor");
        QF = aZ("geocode");
        QG = aZ("intersection");
        QH = aZ("locality");
        QI = aZ("natural_feature");
        QJ = aZ("neighborhood");
        QK = aZ("political");
        QL = aZ("point_of_interest");
        QM = aZ("post_box");
        QN = aZ("postal_code");
        QO = aZ("postal_code_prefix");
        QP = aZ("postal_town");
        QQ = aZ("premise");
        QR = aZ("room");
        QS = aZ("route");
        QT = aZ("street_address");
        QU = aZ("sublocality");
        QV = aZ("sublocality_level_1");
        QW = aZ("sublocality_level_2");
        QX = aZ("sublocality_level_3");
        QY = aZ("sublocality_level_4");
        QZ = aZ("sublocality_level_5");
        Ra = aZ("subpremise");
        Rb = aZ("transit_station");
        Rc = aZ("other");
        CREATOR = new hn();
    }
    
    hm(final int xh, final String rd) {
        fq.ap(rd);
        this.xH = xh;
        this.Rd = rd;
    }
    
    public static hm aZ(final String s) {
        return new hm(0, s);
    }
    
    public int describeContents() {
        final hn creator = hm.CREATOR;
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof hm && this.Rd.equals(((hm)o).Rd);
    }
    
    @Override
    public int hashCode() {
        return this.Rd.hashCode();
    }
    
    @Override
    public String toString() {
        return this.Rd;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final hn creator = hm.CREATOR;
        hn.a(this, parcel, n);
    }
}
