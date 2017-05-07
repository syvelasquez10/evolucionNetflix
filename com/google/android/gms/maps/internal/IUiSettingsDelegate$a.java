// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class IUiSettingsDelegate$a extends Binder implements IUiSettingsDelegate
{
    public static IUiSettingsDelegate bo(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
        if (queryLocalInterface != null && queryLocalInterface instanceof IUiSettingsDelegate) {
            return (IUiSettingsDelegate)queryLocalInterface;
        }
        return new IUiSettingsDelegate$a$a(binder);
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final boolean b = false;
        final boolean b2 = false;
        final boolean b3 = false;
        final boolean b4 = false;
        final boolean b5 = false;
        final boolean b6 = false;
        final boolean b7 = false;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = 0;
        final int n7 = 0;
        final int n8 = 0;
        final int n9 = 0;
        final boolean b8 = false;
        final int n10 = 0;
        boolean zoomControlsEnabled = false;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                if (parcel.readInt() != 0) {
                    zoomControlsEnabled = true;
                }
                this.setZoomControlsEnabled(zoomControlsEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean compassEnabled = b;
                if (parcel.readInt() != 0) {
                    compassEnabled = true;
                }
                this.setCompassEnabled(compassEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean myLocationButtonEnabled = b2;
                if (parcel.readInt() != 0) {
                    myLocationButtonEnabled = true;
                }
                this.setMyLocationButtonEnabled(myLocationButtonEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean scrollGesturesEnabled = b3;
                if (parcel.readInt() != 0) {
                    scrollGesturesEnabled = true;
                }
                this.setScrollGesturesEnabled(scrollGesturesEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean zoomGesturesEnabled = b4;
                if (parcel.readInt() != 0) {
                    zoomGesturesEnabled = true;
                }
                this.setZoomGesturesEnabled(zoomGesturesEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean tiltGesturesEnabled = b5;
                if (parcel.readInt() != 0) {
                    tiltGesturesEnabled = true;
                }
                this.setTiltGesturesEnabled(tiltGesturesEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean rotateGesturesEnabled = b6;
                if (parcel.readInt() != 0) {
                    rotateGesturesEnabled = true;
                }
                this.setRotateGesturesEnabled(rotateGesturesEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean allGesturesEnabled = b7;
                if (parcel.readInt() != 0) {
                    allGesturesEnabled = true;
                }
                this.setAllGesturesEnabled(allGesturesEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                final boolean zoomControlsEnabled2 = this.isZoomControlsEnabled();
                parcel2.writeNoException();
                n = n3;
                if (zoomControlsEnabled2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                final boolean compassEnabled2 = this.isCompassEnabled();
                parcel2.writeNoException();
                n = n4;
                if (compassEnabled2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                final boolean myLocationButtonEnabled2 = this.isMyLocationButtonEnabled();
                parcel2.writeNoException();
                n = n5;
                if (myLocationButtonEnabled2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                final boolean scrollGesturesEnabled2 = this.isScrollGesturesEnabled();
                parcel2.writeNoException();
                n = n6;
                if (scrollGesturesEnabled2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                final boolean zoomGesturesEnabled2 = this.isZoomGesturesEnabled();
                parcel2.writeNoException();
                n = n7;
                if (zoomGesturesEnabled2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                final boolean tiltGesturesEnabled2 = this.isTiltGesturesEnabled();
                parcel2.writeNoException();
                n = n8;
                if (tiltGesturesEnabled2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                final boolean rotateGesturesEnabled2 = this.isRotateGesturesEnabled();
                parcel2.writeNoException();
                n = n9;
                if (rotateGesturesEnabled2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                boolean indoorLevelPickerEnabled = b8;
                if (parcel.readInt() != 0) {
                    indoorLevelPickerEnabled = true;
                }
                this.setIndoorLevelPickerEnabled(indoorLevelPickerEnabled);
                parcel2.writeNoException();
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
                final boolean indoorLevelPickerEnabled2 = this.isIndoorLevelPickerEnabled();
                parcel2.writeNoException();
                n = n10;
                if (indoorLevelPickerEnabled2) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
        }
    }
}
