// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.app.Activity;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.app.Dialog;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnCancelListener;

public class DialogFragment extends Fragment implements DialogInterface$OnCancelListener, DialogInterface$OnDismissListener
{
    int mBackStackId;
    boolean mCancelable;
    Dialog mDialog;
    boolean mDismissed;
    boolean mShownByMe;
    boolean mShowsDialog;
    int mStyle;
    int mTheme;
    boolean mViewDestroyed;
    
    public DialogFragment() {
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
    }
    
    void dismissInternal(final boolean b) {
        if (this.mDismissed) {
            return;
        }
        this.mDismissed = true;
        this.mShownByMe = false;
        if (this.mDialog != null) {
            this.mDialog.dismiss();
            this.mDialog = null;
        }
        this.mViewDestroyed = true;
        if (this.mBackStackId >= 0) {
            this.getFragmentManager().popBackStack(this.mBackStackId, 1);
            this.mBackStackId = -1;
            return;
        }
        final FragmentTransaction beginTransaction = this.getFragmentManager().beginTransaction();
        beginTransaction.remove(this);
        if (b) {
            beginTransaction.commitAllowingStateLoss();
            return;
        }
        beginTransaction.commit();
    }
    
    @Override
    public LayoutInflater getLayoutInflater(final Bundle bundle) {
        if (!this.mShowsDialog) {
            return super.getLayoutInflater(bundle);
        }
        this.mDialog = this.onCreateDialog(bundle);
        switch (this.mStyle) {
            case 3: {
                this.mDialog.getWindow().addFlags(24);
            }
            case 1:
            case 2: {
                this.mDialog.requestWindowFeature(1);
                break;
            }
        }
        if (this.mDialog != null) {
            return (LayoutInflater)this.mDialog.getContext().getSystemService("layout_inflater");
        }
        return (LayoutInflater)this.mActivity.getSystemService("layout_inflater");
    }
    
    public int getTheme() {
        return this.mTheme;
    }
    
    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mShowsDialog) {
            final View view = this.getView();
            if (view != null) {
                if (view.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                this.mDialog.setContentView(view);
            }
            this.mDialog.setOwnerActivity((Activity)this.getActivity());
            this.mDialog.setCancelable(this.mCancelable);
            this.mDialog.setOnCancelListener((DialogInterface$OnCancelListener)this);
            this.mDialog.setOnDismissListener((DialogInterface$OnDismissListener)this);
            if (bundle != null) {
                bundle = bundle.getBundle("android:savedDialogState");
                if (bundle != null) {
                    this.mDialog.onRestoreInstanceState(bundle);
                }
            }
        }
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        if (!this.mShownByMe) {
            this.mDismissed = false;
        }
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.mShowsDialog = (this.mContainerId == 0);
        if (bundle != null) {
            this.mStyle = bundle.getInt("android:style", 0);
            this.mTheme = bundle.getInt("android:theme", 0);
            this.mCancelable = bundle.getBoolean("android:cancelable", true);
            this.mShowsDialog = bundle.getBoolean("android:showsDialog", this.mShowsDialog);
            this.mBackStackId = bundle.getInt("android:backStackId", -1);
        }
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        return new Dialog((Context)this.getActivity(), this.getTheme());
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mDialog != null) {
            this.mViewDestroyed = true;
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        if (!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
    }
    
    public void onDismiss(final DialogInterface dialogInterface) {
        if (!this.mViewDestroyed) {
            this.dismissInternal(true);
        }
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mDialog != null) {
            final Bundle onSaveInstanceState = this.mDialog.onSaveInstanceState();
            if (onSaveInstanceState != null) {
                bundle.putBundle("android:savedDialogState", onSaveInstanceState);
            }
        }
        if (this.mStyle != 0) {
            bundle.putInt("android:style", this.mStyle);
        }
        if (this.mTheme != 0) {
            bundle.putInt("android:theme", this.mTheme);
        }
        if (!this.mCancelable) {
            bundle.putBoolean("android:cancelable", this.mCancelable);
        }
        if (!this.mShowsDialog) {
            bundle.putBoolean("android:showsDialog", this.mShowsDialog);
        }
        if (this.mBackStackId != -1) {
            bundle.putInt("android:backStackId", this.mBackStackId);
        }
    }
    
    @Override
    public void onStart() {
        super.onStart();
        if (this.mDialog != null) {
            this.mViewDestroyed = false;
            this.mDialog.show();
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        if (this.mDialog != null) {
            this.mDialog.hide();
        }
    }
    
    public void setShowsDialog(final boolean mShowsDialog) {
        this.mShowsDialog = mShowsDialog;
    }
    
    public void show(final FragmentManager fragmentManager, final String s) {
        this.mDismissed = false;
        this.mShownByMe = true;
        final FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(this, s);
        beginTransaction.commit();
    }
}
