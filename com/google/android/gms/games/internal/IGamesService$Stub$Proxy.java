// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.drive.Contents;
import android.os.Bundle;
import android.os.Parcelable;
import android.net.Uri;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.internal.request.GameRequestCluster;
import com.google.android.gms.games.internal.multiplayer.ZInvitationCluster;
import com.google.android.gms.games.achievement.AchievementEntity;
import android.content.Intent;
import android.os.Parcel;
import android.os.IBinder;

class IGamesService$Stub$Proxy implements IGamesService
{
    private IBinder lb;
    
    IGamesService$Stub$Proxy(final IBinder lb) {
        this.lb = lb;
    }
    
    @Override
    public void N(final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(5068, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void O(final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12026, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void P(final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(13001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int a(final IGamesCallbacks gamesCallbacks, final byte[] array, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeByteArray(array);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(5033, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent a(int n, final int n2, final boolean b) {
        final int n3 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeInt(n);
            obtain.writeInt(n2);
            n = n3;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(9008, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent a(final int n, final byte[] array, final int n2, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeInt(n);
            obtain.writeByteArray(array);
            obtain.writeInt(n2);
            obtain.writeString(s);
            this.lb.transact(10012, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent a(final AchievementEntity achievementEntity) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                if (achievementEntity != null) {
                    obtain.writeInt(1);
                    achievementEntity.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.lb.transact(13005, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public Intent a(final ZInvitationCluster zInvitationCluster, final String s, final String s2) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                if (zInvitationCluster != null) {
                    obtain.writeInt(1);
                    zInvitationCluster.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeString(s);
                obtain.writeString(s2);
                this.lb.transact(10021, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public Intent a(final GameRequestCluster gameRequestCluster, final String s) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                if (gameRequestCluster != null) {
                    obtain.writeInt(1);
                    gameRequestCluster.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeString(s);
                this.lb.transact(10022, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public Intent a(final RoomEntity roomEntity, final int n) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                if (roomEntity != null) {
                    obtain.writeInt(1);
                    roomEntity.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                obtain.writeInt(n);
                this.lb.transact(9011, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public Intent a(final String s, final boolean b, final boolean b2, final int n) {
        final boolean b3 = true;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            int n2;
            if (b) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            obtain.writeInt(n2);
            int n3;
            if (b2) {
                n3 = (b3 ? 1 : 0);
            }
            else {
                n3 = 0;
            }
            obtain.writeInt(n3);
            obtain.writeInt(n);
            this.lb.transact(12001, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent a(final ParticipantEntity[] array, final String s, final String s2, final Uri uri, final Uri uri2) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    obtain.writeTypedArray((Parcelable[])array, 0);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (uri2 != null) {
                        obtain.writeInt(1);
                        uri2.writeToParcel(obtain, 0);
                        this.lb.transact(9031, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            return (Intent)Intent.CREATOR.createFromParcel(obtain2);
                        }
                        return null;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
            return null;
        }
    }
    
    @Override
    public void a(final long n, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            obtain.writeString(s);
            this.lb.transact(8019, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IBinder binder, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeStrongBinder(binder);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5005, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final Contents contents) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            if (contents != null) {
                obtain.writeInt(1);
                contents.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(12019, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            this.lb.transact(10016, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final int n, final int n2, final int n3) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            this.lb.transact(10009, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, int n, final int n2, final boolean b, final boolean b2) {
        final int n3 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n3;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(5044, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final int n, final int n2, final String[] array, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeStringArray(array);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(8004, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(5015, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final int n, final int[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            obtain.writeIntArray(array);
            this.lb.transact(10018, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeLong(n);
            this.lb.transact(5058, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final long n, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeLong(n);
            obtain.writeString(s);
            this.lb.transact(8018, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final Bundle bundle, final int n, final int n2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            obtain.writeInt(n);
            obtain.writeInt(n2);
            this.lb.transact(5021, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final IBinder binder, int n, final String[] array, final Bundle bundle, final boolean b, final long n2) {
    Label_0152_Outer:
        while (true) {
            final int n3 = 1;
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                while (true) {
                    Label_0157: {
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                            IBinder binder2;
                            if (gamesCallbacks != null) {
                                binder2 = gamesCallbacks.asBinder();
                            }
                            else {
                                binder2 = null;
                            }
                            obtain.writeStrongBinder(binder2);
                            obtain.writeStrongBinder(binder);
                            obtain.writeInt(n);
                            obtain.writeStringArray(array);
                            if (bundle != null) {
                                obtain.writeInt(1);
                                bundle.writeToParcel(obtain, 0);
                                break Label_0157;
                            }
                            obtain.writeInt(0);
                            break Label_0157;
                            obtain.writeInt(n);
                            obtain.writeLong(n2);
                            this.lb.transact(5030, obtain, obtain2, 0);
                            obtain2.readException();
                            return;
                        }
                        finally {
                            obtain2.recycle();
                            obtain.recycle();
                        }
                        n = 0;
                        continue Label_0152_Outer;
                    }
                    if (b) {
                        n = n3;
                        continue Label_0152_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final IBinder binder, final String s, final boolean b, final long n) {
        int n2 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder2;
            if (gamesCallbacks != null) {
                binder2 = gamesCallbacks.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            if (b) {
                n2 = 1;
            }
            obtain.writeInt(n2);
            obtain.writeLong(n);
            this.lb.transact(5031, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5014, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            this.lb.transact(10011, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, int n, final int n2, final int n3, final boolean b) {
        final int n4 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            n = n4;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(5019, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final int n, final IBinder binder, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder2;
            if (gamesCallbacks != null) {
                binder2 = gamesCallbacks.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            obtain.writeString(s);
            obtain.writeInt(n);
            obtain.writeStrongBinder(binder);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5025, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b) {
        final int n2 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            n = n2;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(8023, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(5045, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b3) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b4) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(6501, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final int n, final int[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            obtain.writeIntArray(array);
            this.lb.transact(10019, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeLong(n);
            this.lb.transact(5016, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final long n, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeLong(n);
            obtain.writeString(s2);
            this.lb.transact(7002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final IBinder binder, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder2;
            if (gamesCallbacks != null) {
                binder2 = gamesCallbacks.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            obtain.writeString(s);
            obtain.writeStrongBinder(binder);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5023, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final SnapshotMetadataChange snapshotMetadataChange, final Contents contents) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    if (snapshotMetadataChange != null) {
                        obtain.writeInt(1);
                        snapshotMetadataChange.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (contents != null) {
                        obtain.writeInt(1);
                        contents.writeToParcel(obtain, 0);
                        this.lb.transact(12007, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(5038, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final int n, final int n2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            this.lb.transact(8001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final int n, final int n2, final int n3) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            this.lb.transact(10010, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, int n, final int n2, final int n3, final boolean b) {
        final int n4 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            n = n4;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(5039, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(9028, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final Contents contents) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                    IBinder binder;
                    if (gamesCallbacks != null) {
                        binder = gamesCallbacks.asBinder();
                    }
                    else {
                        binder = null;
                    }
                    obtain.writeStrongBinder(binder);
                    obtain.writeString(s);
                    obtain.writeString(s2);
                    if (snapshotMetadataChange != null) {
                        obtain.writeInt(1);
                        snapshotMetadataChange.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (contents != null) {
                        obtain.writeInt(1);
                        contents.writeToParcel(obtain, 0);
                        this.lb.transact(12033, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(6002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final int[] array, int n, final boolean b) {
        final int n2 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeIntArray(array);
            obtain.writeInt(n);
            n = n2;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12015, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final String[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeStringArray(array);
            this.lb.transact(10008, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final String[] array, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeStringArray(array);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12028, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(5054, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeByteArray(array);
            obtain.writeString(s2);
            obtain.writeTypedArray((Parcelable[])array2, 0);
            this.lb.transact(8007, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final byte[] array, final ParticipantResult[] array2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeByteArray(array);
            obtain.writeTypedArray((Parcelable[])array2, 0);
            this.lb.transact(8008, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final int[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeIntArray(array);
            this.lb.transact(8017, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String s, final String[] array, final int n, final byte[] array2, final int n2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeStringArray(array);
            obtain.writeInt(n);
            obtain.writeByteArray(array2);
            obtain.writeInt(n2);
            this.lb.transact(10005, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(6001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final boolean b, final Bundle bundle) {
        int n = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (!b) {
                n = 0;
            }
            obtain.writeInt(n);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5063, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final boolean b, final String[] array) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            obtain.writeStringArray(array);
            this.lb.transact(12031, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final int[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeIntArray(array);
            this.lb.transact(8003, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final int[] array, int n, final boolean b) {
        final int n2 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeIntArray(array);
            obtain.writeInt(n);
            n = n2;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12010, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeStringArray(array);
            this.lb.transact(10006, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final IGamesCallbacks gamesCallbacks, final String[] array, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeStringArray(array);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12029, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void a(final String s, final IBinder binder, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeStrongBinder(binder);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(13002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.lb;
    }
    
    @Override
    public int b(final byte[] array, final String s, final String[] array2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeByteArray(array);
            obtain.writeString(s);
            obtain.writeStringArray(array2);
            this.lb.transact(5034, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent b(int n, final int n2, final boolean b) {
        final int n3 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeInt(n);
            obtain.writeInt(n2);
            n = n3;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(9009, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent b(final int[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeIntArray(array);
            this.lb.transact(12030, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final long n, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            obtain.writeString(s);
            this.lb.transact(8021, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5017, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(5046, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeLong(n);
            this.lb.transact(8012, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final long n, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeLong(n);
            obtain.writeString(s);
            this.lb.transact(8020, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5018, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            this.lb.transact(12023, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, int n, final int n2, final int n3, final boolean b) {
        final int n4 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            n = n4;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(5020, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, final int n, final IBinder binder, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder2;
            if (gamesCallbacks != null) {
                binder2 = gamesCallbacks.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            obtain.writeString(s);
            obtain.writeInt(n);
            obtain.writeStrongBinder(binder);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(7003, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b) {
        final int n2 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            n = n2;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(10017, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(5501, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, final IBinder binder, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder2;
            if (gamesCallbacks != null) {
                binder2 = gamesCallbacks.asBinder();
            }
            else {
                binder2 = null;
            }
            obtain.writeStrongBinder(binder2);
            obtain.writeString(s);
            obtain.writeStrongBinder(binder);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.lb.transact(5024, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(5041, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, final String s2, int n, final int n2, final int n3, final boolean b) {
        final int n4 = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeInt(n3);
            n = n4;
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(5040, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, final String s2, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(12018, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(6506, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(6502, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(6503, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final IGamesCallbacks gamesCallbacks, final String[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeStringArray(array);
            this.lb.transact(10007, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void b(final String s, final String s2, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            this.lb.transact(5051, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String bB(String string) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(string);
            this.lb.transact(5064, obtain, obtain2, 0);
            obtain2.readException();
            string = obtain2.readString();
            return string;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String bC(String string) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(string);
            this.lb.transact(5035, obtain, obtain2, 0);
            obtain2.readException();
            string = obtain2.readString();
            return string;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void bD(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            this.lb.transact(5050, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int bE(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            this.lb.transact(5060, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Uri bF(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            this.lb.transact(5066, obtain, obtain2, 0);
            obtain2.readException();
            Uri uri;
            if (obtain2.readInt() != 0) {
                uri = (Uri)Uri.CREATOR.createFromParcel(obtain2);
            }
            else {
                uri = null;
            }
            return uri;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void bG(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            this.lb.transact(8002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public ParcelFileDescriptor bH(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            this.lb.transact(9030, obtain, obtain2, 0);
            obtain2.readException();
            ParcelFileDescriptor parcelFileDescriptor;
            if (obtain2.readInt() != 0) {
                parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(obtain2);
            }
            else {
                parcelFileDescriptor = null;
            }
            return parcelFileDescriptor;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent bu(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            this.lb.transact(9004, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent bz(final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            this.lb.transact(12034, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final long n, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            obtain.writeString(s);
            this.lb.transact(10004, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5022, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(5048, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeLong(n);
            this.lb.transact(10001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final long n, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeLong(n);
            obtain.writeString(s);
            this.lb.transact(10003, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5032, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final String s, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            this.lb.transact(12024, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(9001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(8011, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final String s, final String s2, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12003, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(6504, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(8027, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final IGamesCallbacks gamesCallbacks, final String[] array) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeStringArray(array);
            this.lb.transact(10020, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void c(final String s, final String s2, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeString(s2);
            obtain.writeInt(n);
            this.lb.transact(8026, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final long n, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            obtain.writeString(s);
            this.lb.transact(12014, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5026, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(6003, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks, final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeLong(n);
            this.lb.transact(12011, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks, final long n, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeLong(n);
            obtain.writeString(s);
            this.lb.transact(12013, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5037, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(9020, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(8015, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(6505, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void d(final IGamesCallbacks gamesCallbacks, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void dC(final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeInt(n);
            this.lb.transact(5036, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void e(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5027, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void e(final IGamesCallbacks gamesCallbacks, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(6004, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void e(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5042, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void e(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(12021, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void e(final IGamesCallbacks gamesCallbacks, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(8016, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void e(final IGamesCallbacks gamesCallbacks, final String s, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12006, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void e(final IGamesCallbacks gamesCallbacks, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12032, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void f(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5047, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void f(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5043, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void f(final IGamesCallbacks gamesCallbacks, final String s, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeInt(n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            if (b2) {
                n = n2;
            }
            else {
                n = 0;
            }
            obtain.writeInt(n);
            this.lb.transact(12022, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void f(final IGamesCallbacks gamesCallbacks, final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(12009, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void f(final IGamesCallbacks gamesCallbacks, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(12016, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Bundle fD() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(5004, obtain, obtain2, 0);
            obtain2.readException();
            Bundle bundle;
            if (obtain2.readInt() != 0) {
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
            }
            else {
                bundle = null;
            }
            return bundle;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void g(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5049, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void g(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5052, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void g(final IGamesCallbacks gamesCallbacks, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(13003, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public ParcelFileDescriptor h(final Uri uri) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
                if (uri != null) {
                    obtain.writeInt(1);
                    uri.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.lb.transact(6507, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(obtain2);
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            return null;
        }
    }
    
    @Override
    public RoomEntity h(final IGamesCallbacks gamesCallbacks, final String s) {
        final RoomEntity roomEntity = null;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5053, obtain, obtain2, 0);
            obtain2.readException();
            RoomEntity roomEntity2 = roomEntity;
            if (obtain2.readInt() != 0) {
                roomEntity2 = (RoomEntity)RoomEntity.CREATOR.createFromParcel(obtain2);
            }
            return roomEntity2;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void h(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5056, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void h(final IGamesCallbacks gamesCallbacks, final boolean b) {
        int n = 0;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            if (b) {
                n = 1;
            }
            obtain.writeInt(n);
            this.lb.transact(13004, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void i(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(5062, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void i(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5061, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void j(final IGamesCallbacks gamesCallbacks) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.lb.transact(11001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void j(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(5057, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String jX() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(5007, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String jY() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(5012, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void k(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(7001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent kA() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(9013, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void kB() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(11002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public boolean kC() {
        boolean b = false;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(12025, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                b = true;
            }
            return b;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent kb() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(9003, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent kc() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(9005, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent kd() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(9006, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent ke() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(9007, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent kj() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(9010, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent kk() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(9012, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int kl() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(9019, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String km() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(5003, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int kn() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(8024, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Intent ko() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(10015, obtain, obtain2, 0);
            obtain2.readException();
            Intent intent;
            if (obtain2.readInt() != 0) {
                intent = (Intent)Intent.CREATOR.createFromParcel(obtain2);
            }
            else {
                intent = null;
            }
            return intent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int kp() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(10013, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int kq() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(10023, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int kr() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(12035, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int ks() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(12036, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void ku() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(5006, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public DataHolder kw() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(5013, obtain, obtain2, 0);
            obtain2.readException();
            DataHolder z;
            if (obtain2.readInt() != 0) {
                z = DataHolder.CREATOR.z(obtain2);
            }
            else {
                z = null;
            }
            return z;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public boolean kx() {
        boolean b = false;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(5067, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                b = true;
            }
            return b;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public DataHolder ky() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(5502, obtain, obtain2, 0);
            obtain2.readException();
            DataHolder z;
            if (obtain2.readInt() != 0) {
                z = DataHolder.CREATOR.z(obtain2);
            }
            else {
                z = null;
            }
            return z;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void kz() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            this.lb.transact(8022, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void l(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(8005, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void m(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(8006, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void n(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(8009, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void n(final String s, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeInt(n);
            this.lb.transact(12017, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void o(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(8010, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void o(final String s, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeInt(n);
            this.lb.transact(5029, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void p(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(8014, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void p(final String s, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeInt(n);
            this.lb.transact(5028, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void q(final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            this.lb.transact(5001, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void q(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(9002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void r(final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            this.lb.transact(5059, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void r(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(12020, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void r(final String s, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeInt(n);
            this.lb.transact(5055, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void s(final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            this.lb.transact(8013, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void s(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(12005, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void s(final String s, final int n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeInt(n);
            this.lb.transact(10014, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void t(final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            this.lb.transact(10002, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void t(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(12027, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void u(final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeLong(n);
            this.lb.transact(12012, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void u(final IGamesCallbacks gamesCallbacks, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            IBinder binder;
            if (gamesCallbacks != null) {
                binder = gamesCallbacks.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            obtain.writeString(s);
            this.lb.transact(12008, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void u(final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(5065, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void v(final String s, final String s2) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.games.internal.IGamesService");
            obtain.writeString(s);
            obtain.writeString(s2);
            this.lb.transact(8025, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
