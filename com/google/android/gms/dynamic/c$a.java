// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.content.Intent;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class c$a extends Binder implements c
{
    public c$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.dynamic.IFragmentWrapper");
    }
    
    public static c al(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
        if (queryLocalInterface != null && queryLocalInterface instanceof c) {
            return (c)queryLocalInterface;
        }
        return new c$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final IBinder binder = null;
        final IBinder binder2 = null;
        final IBinder binder3 = null;
        final IBinder binder4 = null;
        final Intent intent = null;
        final Intent intent2 = null;
        final IBinder binder5 = null;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = 0;
        final int n7 = 0;
        final int n8 = 0;
        final int n9 = 0;
        boolean hasOptionsMenu = false;
        final boolean b = false;
        final boolean b2 = false;
        final boolean b3 = false;
        final int n10 = 0;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.dynamic.IFragmentWrapper");
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final d iu = this.iu();
                parcel2.writeNoException();
                IBinder binder6 = binder5;
                if (iu != null) {
                    binder6 = iu.asBinder();
                }
                parcel2.writeStrongBinder(binder6);
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final Bundle arguments = this.getArguments();
                parcel2.writeNoException();
                if (arguments != null) {
                    parcel2.writeInt(1);
                    arguments.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                n = this.getId();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final c iv = this.iv();
                parcel2.writeNoException();
                IBinder binder7 = binder;
                if (iv != null) {
                    binder7 = iv.asBinder();
                }
                parcel2.writeStrongBinder(binder7);
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final d iw = this.iw();
                parcel2.writeNoException();
                IBinder binder8 = binder2;
                if (iw != null) {
                    binder8 = iw.asBinder();
                }
                parcel2.writeStrongBinder(binder8);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean retainInstance = this.getRetainInstance();
                parcel2.writeNoException();
                if (retainInstance) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final String tag = this.getTag();
                parcel2.writeNoException();
                parcel2.writeString(tag);
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final c ix = this.ix();
                parcel2.writeNoException();
                IBinder binder9 = binder3;
                if (ix != null) {
                    binder9 = ix.asBinder();
                }
                parcel2.writeStrongBinder(binder9);
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                n = this.getTargetRequestCode();
                parcel2.writeNoException();
                parcel2.writeInt(n);
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean userVisibleHint = this.getUserVisibleHint();
                parcel2.writeNoException();
                n = n10;
                if (userVisibleHint) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final d view = this.getView();
                parcel2.writeNoException();
                IBinder binder10 = binder4;
                if (view != null) {
                    binder10 = view.asBinder();
                }
                parcel2.writeStrongBinder(binder10);
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean added = this.isAdded();
                parcel2.writeNoException();
                n = n3;
                if (added) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 14: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean detached = this.isDetached();
                parcel2.writeNoException();
                n = n4;
                if (detached) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 15: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean hidden = this.isHidden();
                parcel2.writeNoException();
                n = n5;
                if (hidden) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 16: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean inLayout = this.isInLayout();
                parcel2.writeNoException();
                n = n6;
                if (inLayout) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 17: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean removing = this.isRemoving();
                parcel2.writeNoException();
                n = n7;
                if (removing) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 18: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean resumed = this.isResumed();
                parcel2.writeNoException();
                n = n8;
                if (resumed) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 19: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                final boolean visible = this.isVisible();
                parcel2.writeNoException();
                n = n9;
                if (visible) {
                    n = 1;
                }
                parcel2.writeInt(n);
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                this.d(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 21: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                if (parcel.readInt() != 0) {
                    hasOptionsMenu = true;
                }
                this.setHasOptionsMenu(hasOptionsMenu);
                parcel2.writeNoException();
                return true;
            }
            case 22: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                boolean menuVisibility = b;
                if (parcel.readInt() != 0) {
                    menuVisibility = true;
                }
                this.setMenuVisibility(menuVisibility);
                parcel2.writeNoException();
                return true;
            }
            case 23: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                boolean retainInstance2 = b2;
                if (parcel.readInt() != 0) {
                    retainInstance2 = true;
                }
                this.setRetainInstance(retainInstance2);
                parcel2.writeNoException();
                return true;
            }
            case 24: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                boolean userVisibleHint2 = b3;
                if (parcel.readInt() != 0) {
                    userVisibleHint2 = true;
                }
                this.setUserVisibleHint(userVisibleHint2);
                parcel2.writeNoException();
                return true;
            }
            case 25: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                Intent intent3 = intent;
                if (parcel.readInt() != 0) {
                    intent3 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                }
                this.startActivity(intent3);
                parcel2.writeNoException();
                return true;
            }
            case 26: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                Intent intent4 = intent2;
                if (parcel.readInt() != 0) {
                    intent4 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                }
                this.startActivityForResult(intent4, parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 27: {
                parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                this.e(d$a.am(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
