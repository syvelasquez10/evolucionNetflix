// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.request.z;
import com.google.android.gms.fitness.request.t;
import com.google.android.gms.fitness.request.UnclaimBleDeviceRequest;
import com.google.android.gms.fitness.request.b;
import com.google.android.gms.fitness.request.ac;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.x;
import com.google.android.gms.fitness.request.v;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.i;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataInsertRequest;
import com.google.android.gms.fitness.request.l;
import com.google.android.gms.fitness.request.ah;
import com.google.android.gms.fitness.request.ae;
import com.google.android.gms.fitness.request.p;
import com.google.android.gms.fitness.request.n;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class ko$a extends Binder implements ko
{
    public static ko as(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
        if (queryLocalInterface != null && queryLocalInterface instanceof ko) {
            return (ko)queryLocalInterface;
        }
        return new ko$a$a(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final n n3 = null;
        final p p4 = null;
        final ae ae = null;
        final ah ah = null;
        final l l = null;
        final DataInsertRequest dataInsertRequest = null;
        final DataDeleteRequest dataDeleteRequest = null;
        final DataTypeCreateRequest dataTypeCreateRequest = null;
        final i i = null;
        final DataReadRequest dataReadRequest = null;
        final SessionInsertRequest sessionInsertRequest = null;
        final SessionReadRequest sessionReadRequest = null;
        final v v = null;
        final x x = null;
        final StartBleScanRequest startBleScanRequest = null;
        final ac ac = null;
        final b b = null;
        final UnclaimBleDeviceRequest unclaimBleDeviceRequest = null;
        final t t = null;
        final z z = null;
        final DataSourcesRequest dataSourcesRequest = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                DataSourcesRequest dataSourcesRequest2 = dataSourcesRequest;
                if (parcel.readInt() != 0) {
                    dataSourcesRequest2 = (DataSourcesRequest)DataSourcesRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(dataSourcesRequest2, km$a.aq(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                n n4 = n3;
                if (parcel.readInt() != 0) {
                    n4 = (n)n.CREATOR.createFromParcel(parcel);
                }
                this.a(n4, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                p p5 = p4;
                if (parcel.readInt() != 0) {
                    p5 = (p)p.CREATOR.createFromParcel(parcel);
                }
                this.a(p5, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                ae ae2 = ae;
                if (parcel.readInt() != 0) {
                    ae2 = (ae)com.google.android.gms.fitness.request.ae.CREATOR.createFromParcel(parcel);
                }
                this.a(ae2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                ah ah2 = ah;
                if (parcel.readInt() != 0) {
                    ah2 = (ah)com.google.android.gms.fitness.request.ah.CREATOR.createFromParcel(parcel);
                }
                this.a(ah2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                l j = l;
                if (parcel.readInt() != 0) {
                    j = (l)com.google.android.gms.fitness.request.l.CREATOR.createFromParcel(parcel);
                }
                this.a(j, kp$a.at(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                DataInsertRequest dataInsertRequest2 = dataInsertRequest;
                if (parcel.readInt() != 0) {
                    dataInsertRequest2 = (DataInsertRequest)DataInsertRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(dataInsertRequest2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                DataDeleteRequest dataDeleteRequest2 = dataDeleteRequest;
                if (parcel.readInt() != 0) {
                    dataDeleteRequest2 = (DataDeleteRequest)DataDeleteRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(dataDeleteRequest2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                DataTypeCreateRequest dataTypeCreateRequest2 = dataTypeCreateRequest;
                if (parcel.readInt() != 0) {
                    dataTypeCreateRequest2 = (DataTypeCreateRequest)DataTypeCreateRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(dataTypeCreateRequest2, kn$a.ar(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                i k = i;
                if (parcel.readInt() != 0) {
                    k = (i)com.google.android.gms.fitness.request.i.CREATOR.createFromParcel(parcel);
                }
                this.a(k, kn$a.ar(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                DataReadRequest dataReadRequest2 = dataReadRequest;
                if (parcel.readInt() != 0) {
                    dataReadRequest2 = (DataReadRequest)DataReadRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(dataReadRequest2, kl$a.ap(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                SessionInsertRequest sessionInsertRequest2 = sessionInsertRequest;
                if (parcel.readInt() != 0) {
                    sessionInsertRequest2 = (SessionInsertRequest)SessionInsertRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(sessionInsertRequest2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                SessionReadRequest sessionReadRequest2 = sessionReadRequest;
                if (parcel.readInt() != 0) {
                    sessionReadRequest2 = (SessionReadRequest)SessionReadRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(sessionReadRequest2, kq$a.au(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                v v2 = v;
                if (parcel.readInt() != 0) {
                    v2 = (v)com.google.android.gms.fitness.request.v.CREATOR.createFromParcel(parcel);
                }
                this.a(v2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                x x2 = x;
                if (parcel.readInt() != 0) {
                    x2 = (x)com.google.android.gms.fitness.request.x.CREATOR.createFromParcel(parcel);
                }
                this.a(x2, kr$a.av(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                StartBleScanRequest startBleScanRequest2 = startBleScanRequest;
                if (parcel.readInt() != 0) {
                    startBleScanRequest2 = (StartBleScanRequest)StartBleScanRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(startBleScanRequest2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                ac ac2 = ac;
                if (parcel.readInt() != 0) {
                    ac2 = (ac)com.google.android.gms.fitness.request.ac.CREATOR.createFromParcel(parcel);
                }
                this.a(ac2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                b b2 = b;
                if (parcel.readInt() != 0) {
                    b2 = (b)com.google.android.gms.fitness.request.b.CREATOR.createFromParcel(parcel);
                }
                this.a(b2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                UnclaimBleDeviceRequest unclaimBleDeviceRequest2 = unclaimBleDeviceRequest;
                if (parcel.readInt() != 0) {
                    unclaimBleDeviceRequest2 = (UnclaimBleDeviceRequest)UnclaimBleDeviceRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(unclaimBleDeviceRequest2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                t t2 = t;
                if (parcel.readInt() != 0) {
                    t2 = (t)com.google.android.gms.fitness.request.t.CREATOR.createFromParcel(parcel);
                }
                this.a(t2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 21: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                z z2 = z;
                if (parcel.readInt() != 0) {
                    z2 = (z)com.google.android.gms.fitness.request.z.CREATOR.createFromParcel(parcel);
                }
                this.a(z2, ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 22: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                this.a(ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 23: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                this.b(ks$a.aw(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
            case 24: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                this.a(le$a.ax(parcel.readStrongBinder()), parcel.readString());
                return true;
            }
        }
    }
}
