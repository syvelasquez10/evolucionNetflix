// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.io.FileDescriptor;
import java.io.Writer;
import java.io.PrintWriter;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.support.v4.view.ViewCompat;
import android.view.View;
import java.lang.reflect.Modifier;
import android.os.Build$VERSION;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements FragmentManager$BackStackEntry, FragmentManagerImpl$OpGenerator
{
    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final boolean SUPPORTS_TRANSITIONS;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    boolean mAllowOptimization;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    ArrayList<BackStackRecord$Op> mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;
    
    static {
        SUPPORTS_TRANSITIONS = (Build$VERSION.SDK_INT >= 21);
    }
    
    public BackStackRecord(final FragmentManagerImpl mManager) {
        this.mOps = new ArrayList<BackStackRecord$Op>();
        this.mAllowAddToBackStack = true;
        this.mIndex = -1;
        this.mAllowOptimization = true;
        this.mManager = mManager;
    }
    
    private void doAddOp(final int n, final Fragment fragment, final String mTag, final int cmd) {
        final Class<? extends Fragment> class1 = fragment.getClass();
        final int modifiers = class1.getModifiers();
        if (class1.isAnonymousClass() || !Modifier.isPublic(modifiers) || (class1.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + class1.getCanonicalName() + " must be a public static class to be  properly recreated from" + " instance state.");
        }
        fragment.mFragmentManager = this.mManager;
        if (mTag != null) {
            if (fragment.mTag != null && !mTag.equals(fragment.mTag)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + mTag);
            }
            fragment.mTag = mTag;
        }
        if (n != 0) {
            if (n == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + mTag + " to container view with no id");
            }
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != n) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + n);
            }
            fragment.mFragmentId = n;
            fragment.mContainerId = n;
        }
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = cmd;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
    }
    
    private static boolean isFragmentPostponed(final BackStackRecord$Op backStackRecord$Op) {
        final Fragment fragment = backStackRecord$Op.fragment;
        return fragment.mAdded && fragment.mView != null && !fragment.mDetached && !fragment.mHidden && fragment.isPostponed();
    }
    
    @Override
    public FragmentTransaction add(final int n, final Fragment fragment) {
        this.doAddOp(n, fragment, null, 1);
        return this;
    }
    
    @Override
    public FragmentTransaction add(final int n, final Fragment fragment, final String s) {
        this.doAddOp(n, fragment, s, 1);
        return this;
    }
    
    @Override
    public FragmentTransaction add(final Fragment fragment, final String s) {
        this.doAddOp(0, fragment, s, 1);
        return this;
    }
    
    void addOp(final BackStackRecord$Op backStackRecord$Op) {
        this.mOps.add(backStackRecord$Op);
        backStackRecord$Op.enterAnim = this.mEnterAnim;
        backStackRecord$Op.exitAnim = this.mExitAnim;
        backStackRecord$Op.popEnterAnim = this.mPopEnterAnim;
        backStackRecord$Op.popExitAnim = this.mPopExitAnim;
    }
    
    @Override
    public FragmentTransaction addSharedElement(final View view, final String s) {
        if (BackStackRecord.SUPPORTS_TRANSITIONS) {
            final String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.mSharedElementSourceNames == null) {
                this.mSharedElementSourceNames = new ArrayList<String>();
                this.mSharedElementTargetNames = new ArrayList<String>();
            }
            this.mSharedElementSourceNames.add(transitionName);
            this.mSharedElementTargetNames.add(s);
        }
        return this;
    }
    
    @Override
    public FragmentTransaction addToBackStack(final String mName) {
        if (!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = mName;
        return this;
    }
    
    @Override
    public FragmentTransaction attach(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 7;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    void bumpBackStackNesting(final int n) {
        if (this.mAddToBackStack) {
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + n);
            }
            for (int size = this.mOps.size(), i = 0; i < size; ++i) {
                final BackStackRecord$Op backStackRecord$Op = this.mOps.get(i);
                if (backStackRecord$Op.fragment != null) {
                    final Fragment fragment = backStackRecord$Op.fragment;
                    fragment.mBackStackNesting += n;
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Bump nesting of " + backStackRecord$Op.fragment + " to " + backStackRecord$Op.fragment.mBackStackNesting);
                    }
                }
            }
        }
    }
    
    @Override
    public int commit() {
        return this.commitInternal(false);
    }
    
    @Override
    public int commitAllowingStateLoss() {
        return this.commitInternal(true);
    }
    
    int commitInternal(final boolean b) {
        if (this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Commit: " + this);
            this.dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        this.mCommitted = true;
        if (this.mAddToBackStack) {
            this.mIndex = this.mManager.allocBackStackIndex(this);
        }
        else {
            this.mIndex = -1;
        }
        this.mManager.enqueueAction(this, b);
        return this.mIndex;
    }
    
    @Override
    public void commitNow() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(this, false);
    }
    
    @Override
    public void commitNowAllowingStateLoss() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(this, true);
    }
    
    @Override
    public FragmentTransaction detach(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 6;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    @Override
    public FragmentTransaction disallowAddToBackStack() {
        if (this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        this.dump(s, printWriter, true);
    }
    
    public void dump(final String s, final PrintWriter printWriter, final boolean b) {
        if (b) {
            printWriter.print(s);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(s);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter.print(s);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.mExitAnim));
            }
            if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter.print(s);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.mBreadCrumbTitleText);
            }
            if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (!this.mOps.isEmpty()) {
            printWriter.print(s);
            printWriter.println("Operations:");
            new StringBuilder().append(s).append("    ").toString();
            for (int size = this.mOps.size(), i = 0; i < size; ++i) {
                final BackStackRecord$Op backStackRecord$Op = this.mOps.get(i);
                String string = null;
                switch (backStackRecord$Op.cmd) {
                    default: {
                        string = "cmd=" + backStackRecord$Op.cmd;
                        break;
                    }
                    case 0: {
                        string = "NULL";
                        break;
                    }
                    case 1: {
                        string = "ADD";
                        break;
                    }
                    case 2: {
                        string = "REPLACE";
                        break;
                    }
                    case 3: {
                        string = "REMOVE";
                        break;
                    }
                    case 4: {
                        string = "HIDE";
                        break;
                    }
                    case 5: {
                        string = "SHOW";
                        break;
                    }
                    case 6: {
                        string = "DETACH";
                        break;
                    }
                    case 7: {
                        string = "ATTACH";
                        break;
                    }
                }
                printWriter.print(s);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(string);
                printWriter.print(" ");
                printWriter.println(backStackRecord$Op.fragment);
                if (b) {
                    if (backStackRecord$Op.enterAnim != 0 || backStackRecord$Op.exitAnim != 0) {
                        printWriter.print(s);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(backStackRecord$Op.enterAnim));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(backStackRecord$Op.exitAnim));
                    }
                    if (backStackRecord$Op.popEnterAnim != 0 || backStackRecord$Op.popExitAnim != 0) {
                        printWriter.print(s);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(backStackRecord$Op.popEnterAnim));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(backStackRecord$Op.popExitAnim));
                    }
                }
            }
        }
    }
    
    void executeOps() {
        for (int size = this.mOps.size(), i = 0; i < size; ++i) {
            final BackStackRecord$Op backStackRecord$Op = this.mOps.get(i);
            final Fragment fragment = backStackRecord$Op.fragment;
            fragment.setNextTransition(this.mTransition, this.mTransitionStyle);
            switch (backStackRecord$Op.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + backStackRecord$Op.cmd);
                }
                case 1: {
                    fragment.setNextAnim(backStackRecord$Op.enterAnim);
                    this.mManager.addFragment(fragment, false);
                    break;
                }
                case 3: {
                    fragment.setNextAnim(backStackRecord$Op.exitAnim);
                    this.mManager.removeFragment(fragment);
                    break;
                }
                case 4: {
                    fragment.setNextAnim(backStackRecord$Op.exitAnim);
                    this.mManager.hideFragment(fragment);
                    break;
                }
                case 5: {
                    fragment.setNextAnim(backStackRecord$Op.enterAnim);
                    this.mManager.showFragment(fragment);
                    break;
                }
                case 6: {
                    fragment.setNextAnim(backStackRecord$Op.exitAnim);
                    this.mManager.detachFragment(fragment);
                    break;
                }
                case 7: {
                    fragment.setNextAnim(backStackRecord$Op.enterAnim);
                    this.mManager.attachFragment(fragment);
                    break;
                }
            }
            if (!this.mAllowOptimization && backStackRecord$Op.cmd != 1) {
                this.mManager.moveFragmentToExpectedState(fragment);
            }
        }
        if (!this.mAllowOptimization) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }
    
    void executePopOps() {
        for (int i = this.mOps.size() - 1; i >= 0; --i) {
            final BackStackRecord$Op backStackRecord$Op = this.mOps.get(i);
            final Fragment fragment = backStackRecord$Op.fragment;
            fragment.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle);
            switch (backStackRecord$Op.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + backStackRecord$Op.cmd);
                }
                case 1: {
                    fragment.setNextAnim(backStackRecord$Op.popExitAnim);
                    this.mManager.removeFragment(fragment);
                    break;
                }
                case 3: {
                    fragment.setNextAnim(backStackRecord$Op.popEnterAnim);
                    this.mManager.addFragment(fragment, false);
                    break;
                }
                case 4: {
                    fragment.setNextAnim(backStackRecord$Op.popEnterAnim);
                    this.mManager.showFragment(fragment);
                    break;
                }
                case 5: {
                    fragment.setNextAnim(backStackRecord$Op.popExitAnim);
                    this.mManager.hideFragment(fragment);
                    break;
                }
                case 6: {
                    fragment.setNextAnim(backStackRecord$Op.popEnterAnim);
                    this.mManager.attachFragment(fragment);
                    break;
                }
                case 7: {
                    fragment.setNextAnim(backStackRecord$Op.popExitAnim);
                    this.mManager.detachFragment(fragment);
                    break;
                }
            }
            if (!this.mAllowOptimization && backStackRecord$Op.cmd != 1) {
                this.mManager.moveFragmentToExpectedState(fragment);
            }
        }
        if (!this.mAllowOptimization) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }
    
    void expandReplaceOps(final ArrayList<Fragment> list) {
        int i = 0;
    Label_0078_Outer:
        while (i < this.mOps.size()) {
            final BackStackRecord$Op backStackRecord$Op = this.mOps.get(i);
            int n = i;
            while (true) {
                switch (backStackRecord$Op.cmd) {
                    default: {
                        n = i;
                        break Label_0078;
                    }
                    case 3:
                    case 6: {
                        list.remove(backStackRecord$Op.fragment);
                        n = i;
                        break Label_0078;
                    }
                    case 1:
                    case 7: {
                        list.add(backStackRecord$Op.fragment);
                        n = i;
                    }
                    case 4:
                    case 5: {
                        i = n + 1;
                        continue Label_0078_Outer;
                    }
                    case 2: {
                        final Fragment fragment = backStackRecord$Op.fragment;
                        final int mContainerId = fragment.mContainerId;
                        int j = list.size() - 1;
                        int n2 = 0;
                        while (j >= 0) {
                            final Fragment fragment2 = list.get(j);
                            int n3;
                            int n4;
                            if (fragment2.mContainerId == mContainerId) {
                                if (fragment2 == fragment) {
                                    final boolean b = true;
                                    n3 = i;
                                    n4 = (b ? 1 : 0);
                                }
                                else {
                                    final BackStackRecord$Op backStackRecord$Op2 = new BackStackRecord$Op();
                                    backStackRecord$Op2.cmd = 3;
                                    backStackRecord$Op2.fragment = fragment2;
                                    backStackRecord$Op2.enterAnim = backStackRecord$Op.enterAnim;
                                    backStackRecord$Op2.popEnterAnim = backStackRecord$Op.popEnterAnim;
                                    backStackRecord$Op2.exitAnim = backStackRecord$Op.exitAnim;
                                    backStackRecord$Op2.popExitAnim = backStackRecord$Op.popExitAnim;
                                    this.mOps.add(i, backStackRecord$Op2);
                                    list.remove(fragment2);
                                    final int n5 = i + 1;
                                    n4 = n2;
                                    n3 = n5;
                                }
                            }
                            else {
                                final int n6 = i;
                                n4 = n2;
                                n3 = n6;
                            }
                            final int n7 = j - 1;
                            final int n8 = n3;
                            n2 = n4;
                            i = n8;
                            j = n7;
                        }
                        if (n2 != 0) {
                            this.mOps.remove(i);
                            n = i - 1;
                            continue;
                        }
                        backStackRecord$Op.cmd = 1;
                        list.add(fragment);
                        n = i;
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    @Override
    public boolean generateOps(final ArrayList<BackStackRecord> list, final ArrayList<Boolean> list2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }
        list.add(this);
        list2.add(false);
        if (this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }
        return true;
    }
    
    @Override
    public CharSequence getBreadCrumbShortTitle() {
        if (this.mBreadCrumbShortTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes);
        }
        return this.mBreadCrumbShortTitleText;
    }
    
    @Override
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }
    
    @Override
    public CharSequence getBreadCrumbTitle() {
        if (this.mBreadCrumbTitleRes != 0) {
            return this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes);
        }
        return this.mBreadCrumbTitleText;
    }
    
    @Override
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }
    
    @Override
    public int getId() {
        return this.mIndex;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    public int getTransition() {
        return this.mTransition;
    }
    
    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }
    
    @Override
    public FragmentTransaction hide(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 4;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    boolean interactsWith(final int n) {
        for (int size = this.mOps.size(), i = 0; i < size; ++i) {
            if (this.mOps.get(i).fragment.mContainerId == n) {
                return true;
            }
        }
        return false;
    }
    
    boolean interactsWith(final ArrayList<BackStackRecord> list, final int n, final int n2) {
        if (n2 == n) {
            return false;
        }
        final int size = this.mOps.size();
        int n3 = -1;
        for (int i = 0; i < size; ++i) {
            final int mContainerId = this.mOps.get(i).fragment.mContainerId;
            if (mContainerId != 0 && mContainerId != n3) {
                for (int j = n; j < n2; ++j) {
                    final BackStackRecord backStackRecord = list.get(j);
                    for (int size2 = backStackRecord.mOps.size(), k = 0; k < size2; ++k) {
                        if (backStackRecord.mOps.get(k).fragment.mContainerId == mContainerId) {
                            return true;
                        }
                    }
                }
                n3 = mContainerId;
            }
        }
        return false;
    }
    
    @Override
    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }
    
    @Override
    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }
    
    boolean isPostponed() {
        final boolean b = false;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= this.mOps.size()) {
                break;
            }
            if (isFragmentPostponed(this.mOps.get(n))) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    @Override
    public FragmentTransaction remove(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 3;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    @Override
    public FragmentTransaction replace(final int n, final Fragment fragment) {
        return this.replace(n, fragment, null);
    }
    
    @Override
    public FragmentTransaction replace(final int n, final Fragment fragment, final String s) {
        if (n == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        this.doAddOp(n, fragment, s, 2);
        return this;
    }
    
    @Override
    public FragmentTransaction setAllowOptimization(final boolean mAllowOptimization) {
        this.mAllowOptimization = mAllowOptimization;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbShortTitle(final int mBreadCrumbShortTitleRes) {
        this.mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbShortTitle(final CharSequence mBreadCrumbShortTitleText) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbTitle(final int mBreadCrumbTitleRes) {
        this.mBreadCrumbTitleRes = mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = null;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbTitle(final CharSequence mBreadCrumbTitleText) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = mBreadCrumbTitleText;
        return this;
    }
    
    @Override
    public FragmentTransaction setCustomAnimations(final int n, final int n2) {
        return this.setCustomAnimations(n, n2, 0, 0);
    }
    
    @Override
    public FragmentTransaction setCustomAnimations(final int mEnterAnim, final int mExitAnim, final int mPopEnterAnim, final int mPopExitAnim) {
        this.mEnterAnim = mEnterAnim;
        this.mExitAnim = mExitAnim;
        this.mPopEnterAnim = mPopEnterAnim;
        this.mPopExitAnim = mPopExitAnim;
        return this;
    }
    
    void setOnStartPostponedListener(final Fragment$OnStartEnterTransitionListener onStartEnterTransitionListener) {
        for (int i = 0; i < this.mOps.size(); ++i) {
            final BackStackRecord$Op backStackRecord$Op = this.mOps.get(i);
            if (isFragmentPostponed(backStackRecord$Op)) {
                backStackRecord$Op.fragment.setOnStartEnterTransitionListener(onStartEnterTransitionListener);
            }
        }
    }
    
    @Override
    public FragmentTransaction setTransition(final int mTransition) {
        this.mTransition = mTransition;
        return this;
    }
    
    @Override
    public FragmentTransaction setTransitionStyle(final int mTransitionStyle) {
        this.mTransitionStyle = mTransitionStyle;
        return this;
    }
    
    @Override
    public FragmentTransaction show(final Fragment fragment) {
        final BackStackRecord$Op backStackRecord$Op = new BackStackRecord$Op();
        backStackRecord$Op.cmd = 5;
        backStackRecord$Op.fragment = fragment;
        this.addOp(backStackRecord$Op);
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mName != null) {
            sb.append(" ");
            sb.append(this.mName);
        }
        sb.append("}");
        return sb.toString();
    }
}
