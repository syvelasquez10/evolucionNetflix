// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.IntentSender;
import android.support.v4.util.SimpleArrayMap;
import android.view.MenuItem;
import android.view.Menu;
import android.os.Parcelable;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;
import android.content.Intent;
import android.app.Activity;
import android.support.v4.media.session.MediaControllerCompat;
import android.os.Build$VERSION;
import java.io.FileDescriptor;
import android.util.AttributeSet;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources$NotFoundException;
import android.view.ViewGroup;
import android.view.View;
import java.io.PrintWriter;
import android.support.v4.util.SparseArrayCompat;
import android.os.Handler;

public class FragmentActivity extends BaseFragmentActivityJB implements ActivityCompat$OnRequestPermissionsResultCallback, ActivityCompatApi23$RequestPermissionsRequestCodeValidator
{
    static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    final FragmentController mFragments;
    final Handler mHandler;
    int mNextCandidateRequestIndex;
    boolean mOptionsMenuInvalidated;
    SparseArrayCompat<String> mPendingFragmentActivityResults;
    boolean mReallyStopped;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;
    
    public FragmentActivity() {
        this.mHandler = new FragmentActivity$1(this);
        this.mFragments = FragmentController.createController(new FragmentActivity$HostCallbacks(this));
    }
    
    private int allocateRequestIndex(final Fragment fragment) {
        if (this.mPendingFragmentActivityResults.size() >= 65534) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        while (this.mPendingFragmentActivityResults.indexOfKey(this.mNextCandidateRequestIndex) >= 0) {
            this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % 65534;
        }
        final int mNextCandidateRequestIndex = this.mNextCandidateRequestIndex;
        this.mPendingFragmentActivityResults.put(mNextCandidateRequestIndex, fragment.mWho);
        this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % 65534;
        return mNextCandidateRequestIndex;
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
                    Label_0580_Outer:Label_0592_Outer:Label_0604_Outer:
                    while (true) {
                        Label_0261: {
                            while (true) {
                                Label_0244: {
                                    while (true) {
                                        Label_0220: {
                                            while (true) {
                                                Label_0203: {
                                                    while (true) {
                                                        Label_0186: {
                                                            Label_0556_Outer:Label_0574_Outer:
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
                                                                                                                            c3 = '.';
                                                                                                                            break Label_0186;
                                                                                                                            c4 = '.';
                                                                                                                            break Label_0118;
                                                                                                                            c5 = '.';
                                                                                                                            break Label_0220;
                                                                                                                            resourcePackageName = "android";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                            c6 = '.';
                                                                                                                            break Label_0244;
                                                                                                                            c7 = '.';
                                                                                                                            break Label_0203;
                                                                                                                            c8 = '.';
                                                                                                                            break Label_0261;
                                                                                                                            c9 = 'D';
                                                                                                                            break Label_0152;
                                                                                                                            sb.append('V');
                                                                                                                            break;
                                                                                                                            sb.append('G');
                                                                                                                            break;
                                                                                                                            c10 = '.';
                                                                                                                            break Label_0169;
                                                                                                                            resourcePackageName = "app";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                        }
                                                                                                                        c11 = '.';
                                                                                                                        break Label_0135;
                                                                                                                        sb.append('I');
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    catch (Resources$NotFoundException ex) {
                                                                                                                        continue Label_0580_Outer;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            }
                                                                                                            case 4: {
                                                                                                                continue;
                                                                                                            }
                                                                                                            case 8: {
                                                                                                                continue Label_0574_Outer;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                                if (!view.isFocusable()) {
                                                                                                    continue Label_0592_Outer;
                                                                                                }
                                                                                                break;
                                                                                            }
                                                                                            c4 = 'F';
                                                                                        }
                                                                                        sb.append(c4);
                                                                                        if (!view.isEnabled()) {
                                                                                            continue;
                                                                                        }
                                                                                        break;
                                                                                    }
                                                                                    c11 = 'E';
                                                                                }
                                                                                sb.append(c11);
                                                                                if (!view.willNotDraw()) {
                                                                                    continue Label_0574_Outer;
                                                                                }
                                                                                break;
                                                                            }
                                                                            c9 = '.';
                                                                        }
                                                                        sb.append(c9);
                                                                        if (!view.isHorizontalScrollBarEnabled()) {
                                                                            continue Label_0610_Outer;
                                                                        }
                                                                        break;
                                                                    }
                                                                    c10 = 'H';
                                                                }
                                                                sb.append(c10);
                                                                if (!view.isVerticalScrollBarEnabled()) {
                                                                    continue Label_0556_Outer;
                                                                }
                                                                break;
                                                            }
                                                            c3 = 'V';
                                                        }
                                                        sb.append(c3);
                                                        if (!view.isClickable()) {
                                                            continue Label_0604_Outer;
                                                        }
                                                        break;
                                                    }
                                                    c7 = 'C';
                                                }
                                                sb.append(c7);
                                                if (!view.isLongClickable()) {
                                                    continue Label_0616_Outer;
                                                }
                                                break;
                                            }
                                            c5 = 'L';
                                        }
                                        sb.append(c5);
                                        sb.append(' ');
                                        if (!view.isFocused()) {
                                            continue Label_0604_Outer;
                                        }
                                        break;
                                    }
                                    c6 = c;
                                }
                                sb.append(c6);
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
                            continue Label_0580_Outer;
                        }
                        sb.append(" #");
                        sb.append(Integer.toHexString(id));
                        resources = view.getResources();
                        if (id == 0 || resources == null) {
                            continue Label_0580_Outer;
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
        else if (mRetaining) {
            this.mFragments.doLoaderStart();
            this.mFragments.doLoaderStop(true);
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
    
    @Deprecated
    public final MediaControllerCompat getSupportMediaController() {
        return MediaControllerCompat.getMediaController(this);
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        this.mFragments.noteStateNotSaved();
        final int n3 = n >> 16;
        if (n3 == 0) {
            super.onActivityResult(n, n2, intent);
            return;
        }
        final int n4 = n3 - 1;
        final String s = this.mPendingFragmentActivityResults.get(n4);
        this.mPendingFragmentActivityResults.remove(n4);
        if (s == null) {
            Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            return;
        }
        final Fragment fragmentByWho = this.mFragments.findFragmentByWho(s);
        if (fragmentByWho == null) {
            Log.w("FragmentActivity", "Activity result no fragment exists for who: " + s);
            return;
        }
        fragmentByWho.onActivityResult(0xFFFF & n, n2, intent);
    }
    
    public void onAttachFragment(final Fragment fragment) {
    }
    
    public void onBackPressed() {
        if (!this.mFragments.getSupportFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
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
            FragmentManagerNonConfig fragments;
            if (fragmentActivity$NonConfigurationInstances != null) {
                fragments = fragmentActivity$NonConfigurationInstances.fragments;
            }
            else {
                fragments = null;
            }
            mFragments.restoreAllState(parcelable, fragments);
            if (bundle.containsKey("android:support:next_request_index")) {
                this.mNextCandidateRequestIndex = bundle.getInt("android:support:next_request_index");
                final int[] intArray = bundle.getIntArray("android:support:request_indicies");
                final String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                }
                else {
                    this.mPendingFragmentActivityResults = new SparseArrayCompat<String>(intArray.length);
                    for (int i = 0; i < intArray.length; ++i) {
                        this.mPendingFragmentActivityResults.put(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.mPendingFragmentActivityResults == null) {
            this.mPendingFragmentActivityResults = new SparseArrayCompat<String>();
            this.mNextCandidateRequestIndex = 0;
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
    
    public void onMultiWindowModeChanged(final boolean b) {
        this.mFragments.dispatchMultiWindowModeChanged(b);
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
    
    public void onPictureInPictureModeChanged(final boolean b) {
        this.mFragments.dispatchPictureInPictureModeChanged(b);
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
        final int n2 = n >> 16 & 0xFFFF;
        if (n2 != 0) {
            final int n3 = n2 - 1;
            final String s = this.mPendingFragmentActivityResults.get(n3);
            this.mPendingFragmentActivityResults.remove(n3);
            if (s == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            }
            else {
                final Fragment fragmentByWho = this.mFragments.findFragmentByWho(s);
                if (fragmentByWho == null) {
                    Log.w("FragmentActivity", "Activity result no fragment exists for who: " + s);
                    return;
                }
                fragmentByWho.onRequestPermissionsResult(n & 0xFFFF, array, array2);
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
        final FragmentManagerNonConfig retainNestedNonConfig = this.mFragments.retainNestedNonConfig();
        final SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig = this.mFragments.retainLoaderNonConfig();
        if (retainNestedNonConfig == null && retainLoaderNonConfig == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        final FragmentActivity$NonConfigurationInstances fragmentActivity$NonConfigurationInstances = new FragmentActivity$NonConfigurationInstances();
        fragmentActivity$NonConfigurationInstances.custom = onRetainCustomNonConfigurationInstance;
        fragmentActivity$NonConfigurationInstances.fragments = retainNestedNonConfig;
        fragmentActivity$NonConfigurationInstances.loaders = retainLoaderNonConfig;
        return fragmentActivity$NonConfigurationInstances;
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final Parcelable saveAllState = this.mFragments.saveAllState();
        if (saveAllState != null) {
            bundle.putParcelable("android:support:fragments", saveAllState);
        }
        if (this.mPendingFragmentActivityResults.size() > 0) {
            bundle.putInt("android:support:next_request_index", this.mNextCandidateRequestIndex);
            final int[] array = new int[this.mPendingFragmentActivityResults.size()];
            final String[] array2 = new String[this.mPendingFragmentActivityResults.size()];
            for (int i = 0; i < this.mPendingFragmentActivityResults.size(); ++i) {
                array[i] = this.mPendingFragmentActivityResults.keyAt(i);
                array2[i] = this.mPendingFragmentActivityResults.valueAt(i);
            }
            bundle.putIntArray("android:support:request_indicies", array);
            bundle.putStringArray("android:support:request_fragment_who", array2);
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
    
    void requestPermissionsFromFragment(final Fragment fragment, final String[] array, final int n) {
        if (n == -1) {
            ActivityCompat.requestPermissions(this, array, n);
            return;
        }
        BaseFragmentActivityGingerbread.checkForValidRequestCode(n);
        try {
            this.mRequestedPermissionsFromFragment = true;
            ActivityCompat.requestPermissions(this, array, (this.allocateRequestIndex(fragment) + 1 << 16) + (0xFFFF & n));
        }
        finally {
            this.mRequestedPermissionsFromFragment = false;
        }
    }
    
    public void setEnterSharedElementCallback(final SharedElementCallback sharedElementCallback) {
        ActivityCompat.setEnterSharedElementCallback(this, sharedElementCallback);
    }
    
    public void setExitSharedElementCallback(final SharedElementCallback sharedElementCallback) {
        ActivityCompat.setExitSharedElementCallback(this, sharedElementCallback);
    }
    
    @Deprecated
    public final void setSupportMediaController(final MediaControllerCompat mediaControllerCompat) {
        MediaControllerCompat.setMediaController(this, mediaControllerCompat);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        if (!this.mStartedActivityFromFragment && n != -1) {
            BaseFragmentActivityGingerbread.checkForValidRequestCode(n);
        }
        super.startActivityForResult(intent, n);
    }
    
    public void startActivityFromFragment(final Fragment fragment, final Intent intent, final int n) {
        this.startActivityFromFragment(fragment, intent, n, null);
    }
    
    public void startActivityFromFragment(final Fragment fragment, final Intent intent, final int n, final Bundle bundle) {
        this.mStartedActivityFromFragment = true;
        Label_0024: {
            if (n != -1) {
                break Label_0024;
            }
            try {
                ActivityCompat.startActivityForResult(this, intent, -1, bundle);
                return;
                BaseFragmentActivityGingerbread.checkForValidRequestCode(n);
                ActivityCompat.startActivityForResult(this, intent, (this.allocateRequestIndex(fragment) + 1 << 16) + (0xFFFF & n), bundle);
            }
            finally {
                this.mStartedActivityFromFragment = false;
            }
        }
    }
    
    public void startIntentSenderFromFragment(final Fragment fragment, final IntentSender intentSender, final int n, final Intent intent, final int n2, final int n3, final int n4, final Bundle bundle) {
        this.mStartedIntentSenderFromFragment = true;
        Label_0032: {
            if (n != -1) {
                break Label_0032;
            }
            try {
                ActivityCompat.startIntentSenderForResult(this, intentSender, n, intent, n2, n3, n4, bundle);
                return;
                BaseFragmentActivityGingerbread.checkForValidRequestCode(n);
                ActivityCompat.startIntentSenderForResult(this, intentSender, (this.allocateRequestIndex(fragment) + 1 << 16) + (0xFFFF & n), intent, n2, n3, n4, bundle);
            }
            finally {
                this.mStartedIntentSenderFromFragment = false;
            }
        }
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
        if (!this.mRequestedPermissionsFromFragment && n != -1) {
            BaseFragmentActivityGingerbread.checkForValidRequestCode(n);
        }
    }
}
