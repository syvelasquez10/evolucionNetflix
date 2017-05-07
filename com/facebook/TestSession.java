// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Date;
import java.util.HashMap;
import com.facebook.model.GraphUser;
import android.text.TextUtils;
import java.util.Iterator;
import com.facebook.model.GraphObject;
import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import com.facebook.internal.Utility;
import android.os.Bundle;
import com.facebook.internal.Validate;
import android.content.Context;
import android.app.Activity;
import java.util.List;
import java.util.Map;

public class TestSession extends Session
{
    private static final String LOG_TAG = "FacebookSDK.TestSession";
    private static Map<String, TestSession$TestAccount> appTestAccounts;
    private static final long serialVersionUID = 1L;
    private static String testApplicationId;
    private static String testApplicationSecret;
    private final TestSession$Mode mode;
    private final List<String> requestedPermissions;
    private final String sessionUniqueUserTag;
    private String testAccountId;
    private String testAccountUserName;
    private boolean wasAskedToExtendAccessToken;
    
    TestSession(final Activity activity, final List<String> requestedPermissions, final TokenCachingStrategy tokenCachingStrategy, final String sessionUniqueUserTag, final TestSession$Mode mode) {
        super((Context)activity, TestSession.testApplicationId, tokenCachingStrategy);
        Validate.notNull(requestedPermissions, "permissions");
        Validate.notNullOrEmpty(TestSession.testApplicationId, "testApplicationId");
        Validate.notNullOrEmpty(TestSession.testApplicationSecret, "testApplicationSecret");
        this.sessionUniqueUserTag = sessionUniqueUserTag;
        this.mode = mode;
        this.requestedPermissions = requestedPermissions;
    }
    
    public static TestSession createSessionWithPrivateUser(final Activity activity, final List<String> list) {
        return createTestSession(activity, list, TestSession$Mode.PRIVATE, null);
    }
    
    public static TestSession createSessionWithSharedUser(final Activity activity, final List<String> list) {
        return createSessionWithSharedUser(activity, list, null);
    }
    
    public static TestSession createSessionWithSharedUser(final Activity activity, final List<String> list, final String s) {
        return createTestSession(activity, list, TestSession$Mode.SHARED, s);
    }
    
    private TestSession$TestAccount createTestAccountAndFinishAuth() {
        final Bundle bundle = new Bundle();
        bundle.putString("installed", "true");
        bundle.putString("permissions", this.getPermissionsString());
        bundle.putString("access_token", getAppAccessToken());
        if (this.mode == TestSession$Mode.SHARED) {
            bundle.putString("name", String.format("Shared %s Testuser", this.getSharedTestAccountIdentifier()));
        }
        final Response executeAndWait = new Request(null, String.format("%s/accounts/test-users", TestSession.testApplicationId), bundle, HttpMethod.POST).executeAndWait();
        final FacebookRequestError error = executeAndWait.getError();
        final TestSession$TestAccount testSession$TestAccount = executeAndWait.getGraphObjectAs(TestSession$TestAccount.class);
        if (error != null) {
            this.finishAuthOrReauth(null, error.getException());
            return null;
        }
        assert testSession$TestAccount != null;
        if (this.mode == TestSession$Mode.SHARED) {
            testSession$TestAccount.setName(bundle.getString("name"));
            storeTestAccount(testSession$TestAccount);
        }
        this.finishAuthWithTestAccount(testSession$TestAccount);
        return testSession$TestAccount;
    }
    
    private static TestSession createTestSession(final Activity activity, List<String> list, final TestSession$Mode testSession$Mode, final String s) {
        synchronized (TestSession.class) {
            if (Utility.isNullOrEmpty(TestSession.testApplicationId) || Utility.isNullOrEmpty(TestSession.testApplicationSecret)) {
                throw new FacebookException("Must provide app ID and secret");
            }
        }
        if (Utility.isNullOrEmpty((Collection<Object>)list)) {
            list = Arrays.asList("email", "publish_actions");
        }
        final Activity activity2;
        // monitorexit(TestSession.class)
        return new TestSession(activity2, list, new TestSession$TestTokenCachingStrategy(null), s, testSession$Mode);
    }
    
    private void deleteTestAccount(final String s, final String s2) {
        final Bundle bundle = new Bundle();
        bundle.putString("access_token", s2);
        final Response executeAndWait = new Request(null, s, bundle, HttpMethod.DELETE).executeAndWait();
        final FacebookRequestError error = executeAndWait.getError();
        final GraphObject graphObject = executeAndWait.getGraphObject();
        if (error != null) {
            Log.w("FacebookSDK.TestSession", String.format("Could not delete test account %s: %s", s, error.getException().toString()));
        }
        else if (graphObject.getProperty("FACEBOOK_NON_JSON_RESULT") == Boolean.valueOf(false) || graphObject.getProperty("success") == Boolean.valueOf(false)) {
            Log.w("FacebookSDK.TestSession", String.format("Could not delete test account %s: unknown reason", s));
        }
    }
    
    private void findOrCreateSharedTestAccount() {
        final TestSession$TestAccount testAccountMatchingIdentifier = findTestAccountMatchingIdentifier(this.getSharedTestAccountIdentifier());
        if (testAccountMatchingIdentifier != null) {
            this.finishAuthWithTestAccount(testAccountMatchingIdentifier);
            return;
        }
        this.createTestAccountAndFinishAuth();
    }
    
    private static TestSession$TestAccount findTestAccountMatchingIdentifier(final String s) {
        synchronized (TestSession.class) {
            retrieveTestAccountsForAppIfNeeded();
            for (final TestSession$TestAccount testSession$TestAccount : TestSession.appTestAccounts.values()) {
                if (testSession$TestAccount.getName().contains(s)) {
                    return testSession$TestAccount;
                }
            }
            return null;
        }
    }
    
    private void finishAuthWithTestAccount(final TestSession$TestAccount testSession$TestAccount) {
        this.testAccountId = testSession$TestAccount.getId();
        this.testAccountUserName = testSession$TestAccount.getName();
        this.finishAuthOrReauth(AccessToken.createFromString(testSession$TestAccount.getAccessToken(), this.requestedPermissions, AccessTokenSource.TEST_USER), null);
    }
    
    static final String getAppAccessToken() {
        return TestSession.testApplicationId + "|" + TestSession.testApplicationSecret;
    }
    
    private String getPermissionsString() {
        return TextUtils.join((CharSequence)",", (Iterable)this.requestedPermissions);
    }
    
    private String getSharedTestAccountIdentifier() {
        final long n = this.getPermissionsString().hashCode();
        long n2;
        if (this.sessionUniqueUserTag != null) {
            n2 = (this.sessionUniqueUserTag.hashCode() & 0xFFFFFFFFL);
        }
        else {
            n2 = 0L;
        }
        return this.validNameStringFromInteger(n2 ^ (n & 0xFFFFFFFFL));
    }
    
    public static String getTestApplicationId() {
        synchronized (TestSession.class) {
            return TestSession.testApplicationId;
        }
    }
    
    public static String getTestApplicationSecret() {
        synchronized (TestSession.class) {
            return TestSession.testApplicationSecret;
        }
    }
    
    private static void populateTestAccounts(final Collection<TestSession$TestAccount> collection, final GraphObject graphObject) {
        synchronized (TestSession.class) {
            for (final TestSession$TestAccount testSession$TestAccount : collection) {
                testSession$TestAccount.setName(graphObject.getPropertyAs(testSession$TestAccount.getId(), GraphUser.class).getName());
                storeTestAccount(testSession$TestAccount);
            }
        }
    }
    // monitorexit(TestSession.class)
    
    private static void retrieveTestAccountsForAppIfNeeded() {
        while (true) {
            synchronized (TestSession.class) {
                if (TestSession.appTestAccounts != null) {
                    return;
                }
                TestSession.appTestAccounts = new HashMap<String, TestSession$TestAccount>();
                Request.setDefaultBatchApplicationId(TestSession.testApplicationId);
                final Bundle bundle = new Bundle();
                bundle.putString("access_token", getAppAccessToken());
                final Request request = new Request(null, "app/accounts/test-users", bundle, null);
                request.setBatchEntryName("testUsers");
                request.setBatchEntryOmitResultOnSuccess(false);
                final Bundle bundle2 = new Bundle();
                bundle2.putString("access_token", getAppAccessToken());
                bundle2.putString("ids", "{result=testUsers:$.data.*.id}");
                bundle2.putString("fields", "name");
                final Request request2 = new Request(null, "", bundle2, null);
                request2.setBatchEntryDependsOn("testUsers");
                final List<Response> executeBatchAndWait = Request.executeBatchAndWait(request, request2);
                if (executeBatchAndWait == null || executeBatchAndWait.size() != 2) {
                    throw new FacebookException("Unexpected number of results from TestUsers batch query");
                }
            }
            final List<Response> list;
            populateTestAccounts(list.get(0).getGraphObjectAs(TestSession$TestAccountsResponse.class).getData(), list.get(1).getGraphObject());
        }
    }
    
    public static void setTestApplicationId(final String s) {
        synchronized (TestSession.class) {
            if (TestSession.testApplicationId != null && !TestSession.testApplicationId.equals(s)) {
                throw new FacebookException("Can't have more than one test application ID");
            }
        }
        final String testApplicationId;
        TestSession.testApplicationId = testApplicationId;
    }
    // monitorexit(TestSession.class)
    
    public static void setTestApplicationSecret(final String s) {
        synchronized (TestSession.class) {
            if (TestSession.testApplicationSecret != null && !TestSession.testApplicationSecret.equals(s)) {
                throw new FacebookException("Can't have more than one test application secret");
            }
        }
        final String testApplicationSecret;
        TestSession.testApplicationSecret = testApplicationSecret;
    }
    // monitorexit(TestSession.class)
    
    private static void storeTestAccount(final TestSession$TestAccount testSession$TestAccount) {
        synchronized (TestSession.class) {
            TestSession.appTestAccounts.put(testSession$TestAccount.getId(), testSession$TestAccount);
        }
    }
    
    private String validNameStringFromInteger(final long n) {
        final String string = Long.toString(n);
        final StringBuilder sb = new StringBuilder("Perm");
        final char[] charArray = string.toCharArray();
        final int length = charArray.length;
        int i = 0;
        char c = '\0';
        while (i < length) {
            final char c2 = charArray[i];
            char c3;
            if ((c3 = c2) == c) {
                c3 = (char)(c2 + '\n');
            }
            sb.append((char)(c3 + 'a' - '0'));
            ++i;
            c = c3;
        }
        return sb.toString();
    }
    
    @Override
    void authorize(final Session$AuthorizationRequest session$AuthorizationRequest) {
        if (this.mode == TestSession$Mode.PRIVATE) {
            this.createTestAccountAndFinishAuth();
            return;
        }
        this.findOrCreateSharedTestAccount();
    }
    
    @Override
    void extendAccessToken() {
        this.wasAskedToExtendAccessToken = true;
        super.extendAccessToken();
    }
    
    void fakeTokenRefreshAttempt() {
        this.setCurrentTokenRefreshRequest(new Session$TokenRefreshRequest(this));
    }
    
    void forceExtendAccessToken(final boolean b) {
        final AccessToken tokenInfo = this.getTokenInfo();
        this.setTokenInfo(new AccessToken(tokenInfo.getToken(), new Date(), tokenInfo.getPermissions(), tokenInfo.getDeclinedPermissions(), AccessTokenSource.TEST_USER, new Date(0L)));
        this.setLastAttemptedTokenExtendDate(new Date(0L));
    }
    
    public final String getTestUserId() {
        return this.testAccountId;
    }
    
    public final String getTestUserName() {
        return this.testAccountUserName;
    }
    
    boolean getWasAskedToExtendAccessToken() {
        return this.wasAskedToExtendAccessToken;
    }
    
    @Override
    void postStateChange(final SessionState sessionState, final SessionState sessionState2, final Exception ex) {
        final String testAccountId = this.testAccountId;
        super.postStateChange(sessionState, sessionState2, ex);
        if (sessionState2.isClosed() && testAccountId != null && this.mode == TestSession$Mode.PRIVATE) {
            this.deleteTestAccount(testAccountId, getAppAccessToken());
        }
    }
    
    @Override
    boolean shouldExtendAccessToken() {
        final boolean shouldExtendAccessToken = super.shouldExtendAccessToken();
        this.wasAskedToExtendAccessToken = false;
        return shouldExtendAccessToken;
    }
    
    @Override
    public final String toString() {
        return "{TestSession" + " testUserId:" + this.testAccountId + " " + super.toString() + "}";
    }
}
