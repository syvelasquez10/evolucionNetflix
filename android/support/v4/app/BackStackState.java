// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.List;
import android.util.Log;
import android.text.TextUtils;
import android.os.Parcel;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

final class BackStackState implements Parcelable
{
    public static final Parcelable$Creator<BackStackState> CREATOR;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int[] mOps;
    final ArrayList<String> mSharedElementSourceNames;
    final ArrayList<String> mSharedElementTargetNames;
    final int mTransition;
    final int mTransitionStyle;
    
    static {
        CREATOR = (Parcelable$Creator)new BackStackState$1();
    }
    
    public BackStackState(final Parcel parcel) {
        this.mOps = parcel.createIntArray();
        this.mTransition = parcel.readInt();
        this.mTransitionStyle = parcel.readInt();
        this.mName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mBreadCrumbTitleRes = parcel.readInt();
        this.mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mBreadCrumbShortTitleRes = parcel.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSharedElementSourceNames = (ArrayList<String>)parcel.createStringArrayList();
        this.mSharedElementTargetNames = (ArrayList<String>)parcel.createStringArrayList();
    }
    
    public BackStackState(final BackStackRecord backStackRecord) {
        BackStackRecord$Op backStackRecord$Op = backStackRecord.mHead;
        int n = 0;
        while (backStackRecord$Op != null) {
            int n2 = n;
            if (backStackRecord$Op.removed != null) {
                n2 = n + backStackRecord$Op.removed.size();
            }
            backStackRecord$Op = backStackRecord$Op.next;
            n = n2;
        }
        this.mOps = new int[n + backStackRecord.mNumOp * 7];
        if (!backStackRecord.mAddToBackStack) {
            throw new IllegalStateException("Not on back stack");
        }
        BackStackRecord$Op backStackRecord$Op2 = backStackRecord.mHead;
        int n3 = 0;
        while (backStackRecord$Op2 != null) {
            final int[] mOps = this.mOps;
            final int n4 = n3 + 1;
            mOps[n3] = backStackRecord$Op2.cmd;
            final int[] mOps2 = this.mOps;
            final int n5 = n4 + 1;
            int mIndex;
            if (backStackRecord$Op2.fragment != null) {
                mIndex = backStackRecord$Op2.fragment.mIndex;
            }
            else {
                mIndex = -1;
            }
            mOps2[n4] = mIndex;
            final int[] mOps3 = this.mOps;
            final int n6 = n5 + 1;
            mOps3[n5] = backStackRecord$Op2.enterAnim;
            final int[] mOps4 = this.mOps;
            final int n7 = n6 + 1;
            mOps4[n6] = backStackRecord$Op2.exitAnim;
            final int[] mOps5 = this.mOps;
            final int n8 = n7 + 1;
            mOps5[n7] = backStackRecord$Op2.popEnterAnim;
            final int[] mOps6 = this.mOps;
            final int n9 = n8 + 1;
            mOps6[n8] = backStackRecord$Op2.popExitAnim;
            if (backStackRecord$Op2.removed != null) {
                final int size = backStackRecord$Op2.removed.size();
                final int[] mOps7 = this.mOps;
                n3 = n9 + 1;
                mOps7[n9] = size;
                for (int i = 0; i < size; ++i, ++n3) {
                    this.mOps[n3] = backStackRecord$Op2.removed.get(i).mIndex;
                }
            }
            else {
                final int[] mOps8 = this.mOps;
                n3 = n9 + 1;
                mOps8[n9] = 0;
            }
            backStackRecord$Op2 = backStackRecord$Op2.next;
        }
        this.mTransition = backStackRecord.mTransition;
        this.mTransitionStyle = backStackRecord.mTransitionStyle;
        this.mName = backStackRecord.mName;
        this.mIndex = backStackRecord.mIndex;
        this.mBreadCrumbTitleRes = backStackRecord.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = backStackRecord.mBreadCrumbTitleText;
        this.mBreadCrumbShortTitleRes = backStackRecord.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = backStackRecord.mBreadCrumbShortTitleText;
        this.mSharedElementSourceNames = backStackRecord.mSharedElementSourceNames;
        this.mSharedElementTargetNames = backStackRecord.mSharedElementTargetNames;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public BackStackRecord instantiate(final FragmentManagerImpl fragmentManagerImpl) {
        final BackStackRecord backStackRecord = new BackStackRecord(fragmentManagerImpl);
        int n = 0;
        int i = 0;
        while (i < this.mOps.length) {
            final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
            final int[] mOps = this.mOps;
            final int n2 = i + 1;
            backStackRecord$Op.cmd = mOps[i];
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Instantiate " + backStackRecord + " op #" + n + " base fragment #" + this.mOps[n2]);
            }
            final int[] mOps2 = this.mOps;
            final int n3 = n2 + 1;
            final int n4 = mOps2[n2];
            if (n4 >= 0) {
                backStackRecord$Op.fragment = fragmentManagerImpl.mActive.get(n4);
            }
            else {
                backStackRecord$Op.fragment = null;
            }
            final int[] mOps3 = this.mOps;
            final int n5 = n3 + 1;
            backStackRecord$Op.enterAnim = mOps3[n3];
            final int[] mOps4 = this.mOps;
            final int n6 = n5 + 1;
            backStackRecord$Op.exitAnim = mOps4[n5];
            final int[] mOps5 = this.mOps;
            final int n7 = n6 + 1;
            backStackRecord$Op.popEnterAnim = mOps5[n6];
            final int[] mOps6 = this.mOps;
            final int n8 = n7 + 1;
            backStackRecord$Op.popExitAnim = mOps6[n7];
            final int[] mOps7 = this.mOps;
            int n9 = n8 + 1;
            final int n10 = mOps7[n8];
            i = n9;
            if (n10 > 0) {
                backStackRecord$Op.removed = new ArrayList<Fragment>(n10);
                int n11 = 0;
                while (true) {
                    i = n9;
                    if (n11 >= n10) {
                        break;
                    }
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Instantiate " + backStackRecord + " set remove fragment #" + this.mOps[n9]);
                    }
                    backStackRecord$Op.removed.add(fragmentManagerImpl.mActive.get(this.mOps[n9]));
                    ++n11;
                    ++n9;
                }
            }
            backStackRecord.addOp(backStackRecord$Op);
            ++n;
        }
        backStackRecord.mTransition = this.mTransition;
        backStackRecord.mTransitionStyle = this.mTransitionStyle;
        backStackRecord.mName = this.mName;
        backStackRecord.mIndex = this.mIndex;
        backStackRecord.mAddToBackStack = true;
        backStackRecord.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        backStackRecord.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        backStackRecord.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        backStackRecord.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        backStackRecord.mSharedElementSourceNames = this.mSharedElementSourceNames;
        backStackRecord.mSharedElementTargetNames = this.mSharedElementTargetNames;
        backStackRecord.bumpBackStackNesting(1);
        return backStackRecord;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeIntArray(this.mOps);
        parcel.writeInt(this.mTransition);
        parcel.writeInt(this.mTransitionStyle);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList((List)this.mSharedElementSourceNames);
        parcel.writeStringList((List)this.mSharedElementTargetNames);
    }
}
