// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.util.SimpleArrayMap;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.view.Menu;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.res.Configuration;
import java.util.List;
import java.util.ArrayList;
import android.util.Log;
import android.content.Intent;
import android.os.Build$VERSION;
import java.io.FileDescriptor;
import android.util.AttributeSet;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources$NotFoundException;
import android.app.Activity;
import android.view.ViewGroup;
import android.view.View;
import java.io.PrintWriter;
import android.os.Handler;

public class FragmentActivity extends BaseFragmentActivityHoneycomb implements ActivityCompat$OnRequestPermissionsResultCallback, ActivityCompatApi23$RequestPermissionsRequestCodeValidator
{
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private static final int HONEYCOMB = 11;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    final FragmentController mFragments;
    final Handler mHandler;
    boolean mOptionsMenuInvalidated;
    boolean mReallyStopped;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;
    
    public FragmentActivity() {
        this.mHandler = new FragmentActivity$1(this);
        this.mFragments = FragmentController.createController(new FragmentActivity$HostCallbacks(this));
    }
    
    private void dumpViewHierarchy(String string, final PrintWriter printWriter, final View view) {
        printWriter.print(string);
        if (view == null) {
            printWriter.println("null");
        }
        else {
            printWriter.println(viewToString(view));
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                final int childCount = viewGroup.getChildCount();
                if (childCount > 0) {
                    string += "  ";
                    for (int i = 0; i < childCount; ++i) {
                        this.dumpViewHierarchy(string, printWriter, viewGroup.getChildAt(i));
                    }
                }
            }
        }
    }
    
    private void requestPermissionsFromFragment(final Fragment fragment, final String[] array, final int n) {
        if (n == -1) {
            ActivityCompat.requestPermissions(this, array, n);
            return;
        }
        if ((n & 0xFFFFFF00) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 8 bits for requestCode");
        }
        this.mRequestedPermissionsFromFragment = true;
        ActivityCompat.requestPermissions(this, array, (fragment.mIndex + 1 << 8) + (n & 0xFF));
    }
    
    private static String viewToString(final View view) {
        final char c = 'F';
        final char c2 = '.';
        final StringBuilder sb = new StringBuilder(128);
        sb.append(view.getClass().getName());
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(view)));
        sb.append(' ');
        Resources resources;
        int id = 0;
        String resourcePackageName;
        String resourceTypeName;
        String resourceEntryName;
        char c3 = '\0';
        char c4 = '\0';
        char c5 = '\0';
        char c6 = '\0';
        char c7 = '\0';
        char c8 = '\0';
        char c9 = '\0';
        char c10 = '\0';
        char c11 = '\0';
        char c12;
        Label_0509_Outer:Label_0616_Outer:
        while (true) {
        Label_0610_Outer:
            while (true) {
                while (true) {
                    Label_0586_Outer:Label_0592_Outer:Label_0604_Outer:
                    while (true) {
                        Label_0261: {
                        Label_0598_Outer:
                            while (true) {
                                Label_0244: {
                                    while (true) {
                                        Label_0220: {
                                            while (true) {
                                                Label_0203: {
                                                    while (true) {
                                                        Label_0186: {
                                                            Label_0568_Outer:Label_0574_Outer:
                                                            while (true) {
                                                                Label_0169: {
                                                                    while (true) {
                                                                        Label_0152: {
                                                                            while (true) {
                                                                                Label_0135: {
                                                                                    while (true) {
                                                                                        Label_0118: {
                                                                                            while (true) {
                                                                                                while (true) {
                                                                                                    while (true) {
                                                                                                        switch (view.getVisibility()) {
                                                                                                            default: {
                                                                                                                sb.append('.');
                                                                                                                break;
                                                                                                            }
                                                                                                            case 0: {
                                                                                                                Label_0523: {
                                                                                                                    break Label_0523;
                                                                                                                    try {
                                                                                                                        resourcePackageName = resources.getResourcePackageName(id);
                                                                                                                        while (true) {
                                                                                                                            resourceTypeName = resources.getResourceTypeName(id);
                                                                                                                            resourceEntryName = resources.getResourceEntryName(id);
                                                                                                                            sb.append(" ");
                                                                                                                            sb.append(resourcePackageName);
                                                                                                                            sb.append(":");
                                                                                                                            sb.append(resourceTypeName);
                                                                                                                            sb.append("/");
                                                                                                                            sb.append(resourceEntryName);
                                                                                                                            sb.append("}");
                                                                                                                            return sb.toString();
                                                                                                                            resourcePackageName = "android";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                            c3 = '.';
                                                                                                                            break Label_0186;
                                                                                                                            c4 = '.';
                                                                                                                            break Label_0203;
                                                                                                                            c5 = '.';
                                                                                                                            break Label_0220;
                                                                                                                            sb.append('G');
                                                                                                                            break;
                                                                                                                            sb.append('V');
                                                                                                                            break;
                                                                                                                            c6 = 'D';
                                                                                                                            break Label_0152;
                                                                                                                            c7 = '.';
                                                                                                                            break Label_0169;
                                                                                                                            c8 = '.';
                                                                                                                            break Label_0261;
                                                                                                                            resourcePackageName = "app";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                        }
                                                                                                                        c9 = '.';
                                                                                                                        break Label_0135;
                                                                                                                        c10 = '.';
                                                                                                                        break Label_0244;
                                                                                                                        c11 = '.';
                                                                                                                        break Label_0118;
                                                                                                                        sb.append('I');
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    catch (Resources$NotFoundException ex) {
                                                                                                                        continue Label_0616_Outer;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            }
                                                                                                            case 4: {
                                                                                                                continue;
                                                                                                            }
                                                                                                            case 8: {
                                                                                                                continue Label_0568_Outer;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                                if (!view.isFocusable()) {
                                                                                                    continue;
                                                                                                }
                                                                                                break;
                                                                                            }
                                                                                            c11 = 'F';
                                                                                        }
                                                                                        sb.append(c11);
                                                                                        if (!view.isEnabled()) {
                                                                                            continue Label_0598_Outer;
                                                                                        }
                                                                                        break;
                                                                                    }
                                                                                    c9 = 'E';
                                                                                }
                                                                                sb.append(c9);
                                                                                if (!view.willNotDraw()) {
                                                                                    continue Label_0574_Outer;
                                                                                }
                                                                                break;
                                                                            }
                                                                            c6 = '.';
                                                                        }
                                                                        sb.append(c6);
                                                                        if (!view.isHorizontalScrollBarEnabled()) {
                                                                            continue Label_0604_Outer;
                                                                        }
                                                                        break;
                                                                    }
                                                                    c7 = 'H';
                                                                }
                                                                sb.append(c7);
                                                                if (!view.isVerticalScrollBarEnabled()) {
                                                                    continue Label_0586_Outer;
                                                                }
                                                                break;
                                                            }
                                                            c3 = 'V';
                                                        }
                                                        sb.append(c3);
                                                        if (!view.isClickable()) {
                                                            continue Label_0592_Outer;
                                                        }
                                                        break;
                                                    }
                                                    c4 = 'C';
                                                }
                                                sb.append(c4);
                                                if (!view.isLongClickable()) {
                                                    continue Label_0604_Outer;
                                                }
                                                break;
                                            }
                                            c5 = 'L';
                                        }
                                        sb.append(c5);
                                        sb.append(' ');
                                        if (!view.isFocused()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    c10 = c;
                                }
                                sb.append(c10);
                                if (!view.isSelected()) {
                                    continue Label_0610_Outer;
                                }
                                break;
                            }
                            c8 = 'S';
                        }
                        sb.append(c8);
                        c12 = c2;
                        if (view.isPressed()) {
                            c12 = 'P';
                        }
                        sb.append(c12);
                        sb.append(' ');
                        sb.append(view.getLeft());
                        sb.append(',');
                        sb.append(view.getTop());
                        sb.append('-');
                        sb.append(view.getRight());
                        sb.append(',');
                        sb.append(view.getBottom());
                        id = view.getId();
                        if (id == -1) {
                            continue Label_0616_Outer;
                        }
                        sb.append(" #");
                        sb.append(Integer.toHexString(id));
                        resources = view.getResources();
                        if (id == 0 || resources == null) {
                            continue Label_0616_Outer;
                        }
                        break;
                    }
                    switch (0xFF000000 & id) {
                        default: {
                            continue Label_0509_Outer;
                        }
                        case 2130706432: {
                            continue;
                        }
                        case 16777216: {
                            continue Label_0610_Outer;
                        }
                    }
                    break;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    final View dispatchFragmentsOnCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        return this.mFragments.onCreateView(view, s, context, set);
    }
    
    void doReallyStop(final boolean mRetaining) {
        if (!this.mReallyStopped) {
            this.mReallyStopped = true;
            this.mRetaining = mRetaining;
            this.mHandler.removeMessages(1);
            this.onReallyStop();
        }
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        if (Build$VERSION.SDK_INT >= 11) {}
        printWriter.print(s);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        final String string = s + "  ";
        printWriter.print(string);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print("mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.mReallyStopped);
        this.mFragments.dumpLoaders(string, fileDescriptor, printWriter, array);
        this.mFragments.getSupportFragmentManager().dump(s, fileDescriptor, printWriter, array);
        printWriter.print(s);
        printWriter.println("View Hierarchy:");
        this.dumpViewHierarchy(s + "  ", printWriter, this.getWindow().getDecorView());
    }
    
    public Object getLastCustomNonConfigurationInstance() {
        final FragmentActivity$NonConfigurationInstances fragmentActivity$NonConfigurationInstances = (FragmentActivity$NonConfigurationInstances)this.getLastNonConfigurationInstance();
        if (fragmentActivity$NonConfigurationInstances != null) {
            return fragmentActivity$NonConfigurationInstances.custom;
        }
        return null;
    }
    
    public FragmentManager getSupportFragmentManager() {
        return this.mFragments.getSupportFragmentManager();
    }
    
    public LoaderManager getSupportLoaderManager() {
        return this.mFragments.getSupportLoaderManager();
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        this.mFragments.noteStateNotSaved();
        final int n3 = n >> 16;
        if (n3 == 0) {
            super.onActivityResult(n, n2, intent);
            return;
        }
        final int n4 = n3 - 1;
        final int activeFragmentsCount = this.mFragments.getActiveFragmentsCount();
        if (activeFragmentsCount == 0 || n4 < 0 || n4 >= activeFragmentsCount) {
            Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(n));
            return;
        }
        final Fragment fragment = this.mFragments.getActiveFragments(new ArrayList<Fragment>(activeFragmentsCount)).get(n4);
        if (fragment == null) {
            Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(n));
            return;
        }
        fragment.onActivityResult(0xFFFF & n, n2, intent);
    }
    
    public void onAttachFragment(final Fragment fragment) {
    }
    
    public void onBackPressed() {
        if (!this.mFragments.getSupportFragmentManager().popBackStackImmediate()) {
            this.supportFinishAfterTransition();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.dispatchConfigurationChanged(configuration);
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        this.mFragments.attachHost(null);
        super.onCreate(bundle);
        final FragmentActivity$NonConfigurationInstances fragmentActivity$NonConfigurationInstances = (FragmentActivity$NonConfigurationInstances)this.getLastNonConfigurationInstance();
        if (fragmentActivity$NonConfigurationInstances != null) {
            this.mFragments.restoreLoaderNonConfig(fragmentActivity$NonConfigurationInstances.loaders);
        }
        if (bundle != null) {
            final Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            final FragmentController mFragments = this.mFragments;
            List<Fragment> fragments;
            if (fragmentActivity$NonConfigurationInstances != null) {
                fragments = fragmentActivity$NonConfigurationInstances.fragments;
            }
            else {
                fragments = null;
            }
            mFragments.restoreAllState(parcelable, fragments);
        }
        this.mFragments.dispatchCreate();
    }
    
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        if (n == 0) {
            final boolean onCreatePanelMenu = super.onCreatePanelMenu(n, menu);
            final boolean dispatchCreateOptionsMenu = this.mFragments.dispatchCreateOptionsMenu(menu, this.getMenuInflater());
            return Build$VERSION.SDK_INT < 11 || (onCreatePanelMenu | dispatchCreateOptionsMenu);
        }
        return super.onCreatePanelMenu(n, menu);
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.doReallyStop(false);
        this.mFragments.dispatchDestroy();
        this.mFragments.doLoaderDestroy();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (Build$VERSION.SDK_INT < 5 && n == 4 && keyEvent.getRepeatCount() == 0) {
            this.onBackPressed();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.dispatchLowMemory();
    }
    
    public boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        if (super.onMenuItemSelected(n, menuItem)) {
            return true;
        }
        switch (n) {
            default: {
                return false;
            }
            case 0: {
                return this.mFragments.dispatchOptionsItemSelected(menuItem);
            }
            case 6: {
                return this.mFragments.dispatchContextItemSelected(menuItem);
            }
        }
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.noteStateNotSaved();
    }
    
    public void onPanelClosed(final int n, final Menu menu) {
        switch (n) {
            case 0: {
                this.mFragments.dispatchOptionsMenuClosed(menu);
                break;
            }
        }
        super.onPanelClosed(n, menu);
    }
    
    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            this.onResumeFragments();
        }
        this.mFragments.dispatchPause();
    }
    
    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        this.onResumeFragments();
        this.mFragments.execPendingActions();
    }
    
    protected boolean onPrepareOptionsPanel(final View view, final Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }
    
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        if (n == 0 && menu != null) {
            if (this.mOptionsMenuInvalidated) {
                this.mOptionsMenuInvalidated = false;
                menu.clear();
                this.onCreatePanelMenu(n, menu);
            }
            return this.onPrepareOptionsPanel(view, menu) | this.mFragments.dispatchPrepareOptionsMenu(menu);
        }
        return super.onPreparePanel(n, view, menu);
    }
    
    void onReallyStop() {
        this.mFragments.doLoaderStop(this.mRetaining);
        this.mFragments.dispatchReallyStop();
    }
    
    @Override
    public void onRequestPermissionsResult(final int n, final String[] array, final int[] array2) {
        final int n2 = n >> 8 & 0xFF;
        if (n2 != 0) {
            final int n3 = n2 - 1;
            final int activeFragmentsCount = this.mFragments.getActiveFragmentsCount();
            if (activeFragmentsCount == 0 || n3 < 0 || n3 >= activeFragmentsCount) {
                Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(n));
            }
            else {
                final Fragment fragment = this.mFragments.getActiveFragments(new ArrayList<Fragment>(activeFragmentsCount)).get(n3);
                if (fragment == null) {
                    Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(n));
                    return;
                }
                fragment.onRequestPermissionsResult(n & 0xFF, array, array2);
            }
        }
    }
    
    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }
    
    protected void onResumeFragments() {
        this.mFragments.dispatchResume();
    }
    
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }
    
    public final Object onRetainNonConfigurationInstance() {
        if (this.mStopped) {
            this.doReallyStop(true);
        }
        final Object onRetainCustomNonConfigurationInstance = this.onRetainCustomNonConfigurationInstance();
        final List<Fragment> retainNonConfig = this.mFragments.retainNonConfig();
        final SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig = this.mFragments.retainLoaderNonConfig();
        if (retainNonConfig == null && retainLoaderNonConfig == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        final FragmentActivity$NonConfigurationInstances fragmentActivity$NonConfigurationInstances = new FragmentActivity$NonConfigurationInstances();
        fragmentActivity$NonConfigurationInstances.custom = onRetainCustomNonConfigurationInstance;
        fragmentActivity$NonConfigurationInstances.fragments = retainNonConfig;
        fragmentActivity$NonConfigurationInstances.loaders = retainLoaderNonConfig;
        return fragmentActivity$NonConfigurationInstances;
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final Parcelable saveAllState = this.mFragments.saveAllState();
        if (saveAllState != null) {
            bundle.putParcelable("android:support:fragments", saveAllState);
        }
    }
    
    protected void onStart() {
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(1);
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }
        this.mFragments.noteStateNotSaved();
        this.mFragments.execPendingActions();
        this.mFragments.doLoaderStart();
        this.mFragments.dispatchStart();
        this.mFragments.reportLoaderStart();
    }
    
    public void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }
    
    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        this.mHandler.sendEmptyMessage(1);
        this.mFragments.dispatchStop();
    }
    
    public void setEnterSharedElementCallback(final SharedElementCallback sharedElementCallback) {
        ActivityCompat.setEnterSharedElementCallback(this, sharedElementCallback);
    }
    
    public void setExitSharedElementCallback(final SharedElementCallback sharedElementCallback) {
        ActivityCompat.setExitSharedElementCallback(this, sharedElementCallback);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        if (n != -1 && (0xFFFF0000 & n) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, n);
    }
    
    public void startActivityFromFragment(final Fragment fragment, final Intent intent, final int n) {
        if (n == -1) {
            super.startActivityForResult(intent, -1);
            return;
        }
        if ((0xFFFF0000 & n) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, (fragment.mIndex + 1 << 16) + (0xFFFF & n));
    }
    
    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }
    
    public void supportInvalidateOptionsMenu() {
        if (Build$VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.invalidateOptionsMenu(this);
            return;
        }
        this.mOptionsMenuInvalidated = true;
    }
    
    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }
    
    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(this);
    }
    
    @Override
    public final void validateRequestPermissionsRequestCode(final int n) {
        if (this.mRequestedPermissionsFromFragment) {
            this.mRequestedPermissionsFromFragment = false;
        }
        else if ((n & 0xFFFFFF00) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 8 bits for requestCode");
        }
    }
}
