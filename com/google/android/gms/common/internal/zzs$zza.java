// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzs$zza extends Binder implements zzs
{
    public static zzs zzaK(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzs) {
            return (zzs)queryLocalInterface;
        }
        return new zzs$zza$zza(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final Bundle bundle = null;
        final Bundle bundle2 = null;
        final Bundle bundle3 = null;
        final Bundle bundle4 = null;
        final Bundle bundle5 = null;
        final Bundle bundle6 = null;
        final Bundle bundle7 = null;
        final Bundle bundle8 = null;
        final Bundle bundle9 = null;
        final Bundle bundle10 = null;
        final Bundle bundle11 = null;
        final Bundle bundle12 = null;
        final Bundle bundle13 = null;
        final Bundle bundle14 = null;
        final Bundle bundle15 = null;
        final Bundle bundle16 = null;
        final Bundle bundle17 = null;
        final Bundle bundle18 = null;
        final Bundle bundle19 = null;
        final GetServiceRequest getServiceRequest = null;
        final ValidateAccountRequest validateAccountRequest = null;
        Bundle bundle20 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.common.internal.IGmsServiceBroker");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string = parcel.readString();
                final String string2 = parcel.readString();
                final String[] stringArray = parcel.createStringArray();
                final String string3 = parcel.readString();
                Bundle bundle21;
                if (parcel.readInt() != 0) {
                    bundle21 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle21 = null;
                }
                this.zza(zzaJ, n, string, string2, stringArray, string3, bundle21);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ2 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string4 = parcel.readString();
                if (parcel.readInt() != 0) {
                    bundle20 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zza(zzaJ2, n, string4, bundle20);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zza(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zza(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ3 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string5 = parcel.readString();
                Bundle bundle22 = bundle;
                if (parcel.readInt() != 0) {
                    bundle22 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzb(zzaJ3, n, string5, bundle22);
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ4 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string6 = parcel.readString();
                Bundle bundle23 = bundle2;
                if (parcel.readInt() != 0) {
                    bundle23 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzc(zzaJ4, n, string6, bundle23);
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ5 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string7 = parcel.readString();
                Bundle bundle24 = bundle3;
                if (parcel.readInt() != 0) {
                    bundle24 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzd(zzaJ5, n, string7, bundle24);
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ6 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string8 = parcel.readString();
                Bundle bundle25 = bundle4;
                if (parcel.readInt() != 0) {
                    bundle25 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zze(zzaJ6, n, string8, bundle25);
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ7 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string9 = parcel.readString();
                final String string10 = parcel.readString();
                final String[] stringArray2 = parcel.createStringArray();
                final String string11 = parcel.readString();
                final IBinder strongBinder = parcel.readStrongBinder();
                final String string12 = parcel.readString();
                Bundle bundle26;
                if (parcel.readInt() != 0) {
                    bundle26 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle26 = null;
                }
                this.zza(zzaJ7, n, string9, string10, stringArray2, string11, strongBinder, string12, bundle26);
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zza(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ8 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string13 = parcel.readString();
                Bundle bundle27 = bundle5;
                if (parcel.readInt() != 0) {
                    bundle27 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzf(zzaJ8, n, string13, bundle27);
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ9 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string14 = parcel.readString();
                Bundle bundle28 = bundle6;
                if (parcel.readInt() != 0) {
                    bundle28 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzg(zzaJ9, n, string14, bundle28);
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ10 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string15 = parcel.readString();
                Bundle bundle29 = bundle7;
                if (parcel.readInt() != 0) {
                    bundle29 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzh(zzaJ10, n, string15, bundle29);
                parcel2.writeNoException();
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ11 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string16 = parcel.readString();
                Bundle bundle30 = bundle8;
                if (parcel.readInt() != 0) {
                    bundle30 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzi(zzaJ11, n, string16, bundle30);
                parcel2.writeNoException();
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ12 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string17 = parcel.readString();
                Bundle bundle31 = bundle9;
                if (parcel.readInt() != 0) {
                    bundle31 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzj(zzaJ12, n, string17, bundle31);
                parcel2.writeNoException();
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ13 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string18 = parcel.readString();
                Bundle bundle32 = bundle10;
                if (parcel.readInt() != 0) {
                    bundle32 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzk(zzaJ13, n, string18, bundle32);
                parcel2.writeNoException();
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ14 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string19 = parcel.readString();
                Bundle bundle33 = bundle11;
                if (parcel.readInt() != 0) {
                    bundle33 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzl(zzaJ14, n, string19, bundle33);
                parcel2.writeNoException();
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ15 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string20 = parcel.readString();
                Bundle bundle34 = bundle12;
                if (parcel.readInt() != 0) {
                    bundle34 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzm(zzaJ15, n, string20, bundle34);
                parcel2.writeNoException();
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ16 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string21 = parcel.readString();
                final IBinder strongBinder2 = parcel.readStrongBinder();
                Bundle bundle35;
                if (parcel.readInt() != 0) {
                    bundle35 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle35 = null;
                }
                this.zza(zzaJ16, n, string21, strongBinder2, bundle35);
                parcel2.writeNoException();
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ17 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string22 = parcel.readString();
                final String[] stringArray3 = parcel.createStringArray();
                final String string23 = parcel.readString();
                Bundle bundle36;
                if (parcel.readInt() != 0) {
                    bundle36 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle36 = null;
                }
                this.zza(zzaJ17, n, string22, stringArray3, string23, bundle36);
                parcel2.writeNoException();
                return true;
            }
            case 21: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzb(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 22: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzc(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 23: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ18 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string24 = parcel.readString();
                Bundle bundle37 = bundle13;
                if (parcel.readInt() != 0) {
                    bundle37 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzn(zzaJ18, n, string24, bundle37);
                parcel2.writeNoException();
                return true;
            }
            case 24: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzd(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 25: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ19 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string25 = parcel.readString();
                Bundle bundle38 = bundle14;
                if (parcel.readInt() != 0) {
                    bundle38 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzo(zzaJ19, n, string25, bundle38);
                parcel2.writeNoException();
                return true;
            }
            case 26: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zze(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 27: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ20 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string26 = parcel.readString();
                Bundle bundle39 = bundle15;
                if (parcel.readInt() != 0) {
                    bundle39 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzp(zzaJ20, n, string26, bundle39);
                parcel2.writeNoException();
                return true;
            }
            case 28: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzpp();
                parcel2.writeNoException();
                return true;
            }
            case 30: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ21 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string27 = parcel.readString();
                final String string28 = parcel.readString();
                final String[] stringArray4 = parcel.createStringArray();
                Bundle bundle40;
                if (parcel.readInt() != 0) {
                    bundle40 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle40 = null;
                }
                this.zza(zzaJ21, n, string27, string28, stringArray4, bundle40);
                parcel2.writeNoException();
                return true;
            }
            case 31: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzf(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 32: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzg(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 33: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zza(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.createStringArray());
                parcel2.writeNoException();
                return true;
            }
            case 34: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zza(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 35: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzh(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 36: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzi(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 37: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ22 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string29 = parcel.readString();
                Bundle bundle41 = bundle16;
                if (parcel.readInt() != 0) {
                    bundle41 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzq(zzaJ22, n, string29, bundle41);
                parcel2.writeNoException();
                return true;
            }
            case 38: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ23 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string30 = parcel.readString();
                Bundle bundle42 = bundle17;
                if (parcel.readInt() != 0) {
                    bundle42 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzr(zzaJ23, n, string30, bundle42);
                parcel2.writeNoException();
                return true;
            }
            case 40: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzj(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 41: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ24 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string31 = parcel.readString();
                Bundle bundle43 = bundle18;
                if (parcel.readInt() != 0) {
                    bundle43 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzs(zzaJ24, n, string31, bundle43);
                parcel2.writeNoException();
                return true;
            }
            case 42: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzk(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 43: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ25 = zzr$zza.zzaJ(parcel.readStrongBinder());
                n = parcel.readInt();
                final String string32 = parcel.readString();
                Bundle bundle44 = bundle19;
                if (parcel.readInt() != 0) {
                    bundle44 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.zzt(zzaJ25, n, string32, bundle44);
                parcel2.writeNoException();
                return true;
            }
            case 44: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzl(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 45: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                this.zzm(zzr$zza.zzaJ(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 46: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ26 = zzr$zza.zzaJ(parcel.readStrongBinder());
                GetServiceRequest getServiceRequest2 = getServiceRequest;
                if (parcel.readInt() != 0) {
                    getServiceRequest2 = (GetServiceRequest)GetServiceRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(zzaJ26, getServiceRequest2);
                parcel2.writeNoException();
                return true;
            }
            case 47: {
                parcel.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                final zzr zzaJ27 = zzr$zza.zzaJ(parcel.readStrongBinder());
                ValidateAccountRequest validateAccountRequest2 = validateAccountRequest;
                if (parcel.readInt() != 0) {
                    validateAccountRequest2 = (ValidateAccountRequest)ValidateAccountRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(zzaJ27, validateAccountRequest2);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
