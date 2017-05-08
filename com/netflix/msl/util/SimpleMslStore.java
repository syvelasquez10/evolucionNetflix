// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import java.util.Collection;
import java.util.Iterator;
import com.netflix.msl.MslException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import java.util.HashSet;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.ServiceToken;
import java.util.Set;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.tokens.MasterToken;
import java.util.Map;

public class SimpleMslStore implements MslStore
{
    private final Map<MasterToken, ICryptoContext> cryptoContexts;
    private final Map<Long, Set<ServiceToken>> mtServiceTokens;
    private final Map<Long, Long> nonReplayableIds;
    private final Map<Long, Set<ServiceToken>> uitServiceTokens;
    private final Set<ServiceToken> unboundServiceTokens;
    private final Map<String, UserIdToken> userIdTokens;
    
    public SimpleMslStore() {
        this.cryptoContexts = new ConcurrentHashMap<MasterToken, ICryptoContext>();
        this.userIdTokens = new ConcurrentHashMap<String, UserIdToken>();
        this.nonReplayableIds = new HashMap<Long, Long>();
        this.unboundServiceTokens = new HashSet<ServiceToken>();
        this.mtServiceTokens = new HashMap<Long, Set<ServiceToken>>();
        this.uitServiceTokens = new HashMap<Long, Set<ServiceToken>>();
    }
    
    private static long incrementNonReplayableId(final long n) {
        if (n < 0L || n > 9007199254740992L) {
            throw new MslInternalException("Non-replayable ID " + n + " is outside the valid range.");
        }
        if (n == 9007199254740992L) {
            return 0L;
        }
        return 1L + n;
    }
    
    @Override
    public void addServiceTokens(final Set<ServiceToken> set) {
    Label_0009_Outer:
        while (true) {
            Label_0218: {
                while (true) {
                Label_0009:
                    while (true) {
                        final ServiceToken serviceToken;
                        synchronized (this) {
                            final Iterator<ServiceToken> iterator = set.iterator();
                            if (!iterator.hasNext()) {
                                break Label_0218;
                            }
                            serviceToken = iterator.next();
                            if (serviceToken.isMasterTokenBound()) {
                                final Iterator<MasterToken> iterator2 = this.cryptoContexts.keySet().iterator();
                                Block_16: {
                                    while (iterator2.hasNext()) {
                                        if (serviceToken.isBoundTo(iterator2.next())) {
                                            break Block_16;
                                        }
                                    }
                                    break Label_0009;
                                }
                                final int n = 1;
                                if (n == 0) {
                                    throw new MslException(MslError.SERVICETOKEN_MASTERTOKEN_NOT_FOUND, "st mtserialnumber " + serviceToken.getMasterTokenSerialNumber());
                                }
                            }
                        }
                        if (serviceToken.isUserIdTokenBound()) {
                            final Iterator<UserIdToken> iterator3 = this.userIdTokens.values().iterator();
                            while (true) {
                                while (iterator3.hasNext()) {
                                    if (serviceToken.isBoundTo(iterator3.next())) {
                                        final int n2 = 1;
                                        if (n2 == 0) {
                                            throw new MslException(MslError.SERVICETOKEN_USERIDTOKEN_NOT_FOUND, "st uitserialnumber " + serviceToken.getUserIdTokenSerialNumber());
                                        }
                                        continue Label_0009;
                                    }
                                }
                                final int n2 = 0;
                                continue;
                            }
                        }
                        continue Label_0009;
                    }
                    final int n = 0;
                    continue;
                }
            }
            final Set<ServiceToken> set2;
            for (final ServiceToken serviceToken2 : set2) {
                if (serviceToken2.isUnbound()) {
                    this.unboundServiceTokens.add(serviceToken2);
                }
                else {
                    if (serviceToken2.isMasterTokenBound()) {
                        Set<ServiceToken> set3;
                        if ((set3 = this.mtServiceTokens.get(serviceToken2.getMasterTokenSerialNumber())) == null) {
                            set3 = new HashSet<ServiceToken>();
                            this.mtServiceTokens.put(serviceToken2.getMasterTokenSerialNumber(), set3);
                        }
                        set3.add(serviceToken2);
                    }
                    if (!serviceToken2.isUserIdTokenBound()) {
                        continue Label_0009_Outer;
                    }
                    Set<ServiceToken> set4;
                    if ((set4 = this.uitServiceTokens.get(serviceToken2.getUserIdTokenSerialNumber())) == null) {
                        set4 = new HashSet<ServiceToken>();
                        this.uitServiceTokens.put(serviceToken2.getUserIdTokenSerialNumber(), set4);
                    }
                    set4.add(serviceToken2);
                }
            }
        }
        // monitorexit(this)
    }
    
    @Override
    public void addUserIdToken(final String s, final UserIdToken userIdToken) {
        final Iterator<MasterToken> iterator = this.cryptoContexts.keySet().iterator();
        while (true) {
            while (iterator.hasNext()) {
                if (userIdToken.isBoundTo(iterator.next())) {
                    final int n = 1;
                    if (n == 0) {
                        throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_NOT_FOUND, "uit mtserialnumber " + userIdToken.getMasterTokenSerialNumber());
                    }
                    this.userIdTokens.put(s, userIdToken);
                    return;
                }
            }
            final int n = 0;
            continue;
        }
    }
    
    @Override
    public void clearCryptoContexts() {
        synchronized (this) {
            this.cryptoContexts.clear();
            this.nonReplayableIds.clear();
            this.userIdTokens.clear();
            this.uitServiceTokens.clear();
            this.mtServiceTokens.clear();
        }
    }
    
    @Override
    public void clearServiceTokens() {
        synchronized (this) {
            this.unboundServiceTokens.clear();
            this.mtServiceTokens.clear();
            this.uitServiceTokens.clear();
        }
    }
    
    @Override
    public void clearUserIdTokens() {
        for (final UserIdToken userIdToken : this.userIdTokens.values()) {
            try {
                this.removeServiceTokens(null, null, userIdToken);
                continue;
            }
            catch (MslException ex) {
                throw new MslInternalException("Unexpected exception while removing user ID token bound service tokens.", ex);
            }
            break;
        }
        this.userIdTokens.clear();
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MasterToken masterToken) {
        return this.cryptoContexts.get(masterToken);
    }
    
    @Override
    public MasterToken getMasterToken() {
        MasterToken masterToken = null;
        for (MasterToken masterToken3 : this.cryptoContexts.keySet()) {
            final MasterToken masterToken2 = masterToken3;
            if (masterToken != null) {
                if (masterToken2.isNewerThan(masterToken)) {
                    masterToken3 = masterToken2;
                }
                else {
                    masterToken3 = masterToken;
                }
            }
            masterToken = masterToken3;
        }
        return masterToken;
    }
    
    @Override
    public long getNonReplayableId(final MasterToken masterToken) {
        synchronized (this) {
            final long serialNumber = masterToken.getSerialNumber();
            long longValue;
            if (this.nonReplayableIds.containsKey(serialNumber)) {
                longValue = this.nonReplayableIds.get(serialNumber);
            }
            else {
                longValue = 0L;
            }
            final long incrementNonReplayableId = incrementNonReplayableId(longValue);
            this.nonReplayableIds.put(serialNumber, incrementNonReplayableId);
            return incrementNonReplayableId;
        }
    }
    
    @Override
    public Set<ServiceToken> getServiceTokens(final MasterToken masterToken, final UserIdToken userIdToken) {
        // monitorenter(this)
        if (userIdToken != null) {
            if (masterToken == null) {
                try {
                    throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_NULL);
                }
                finally {
                }
                // monitorexit(this)
            }
            if (!userIdToken.isBoundTo(masterToken)) {
                throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_MISMATCH, "uit mtserialnumber " + userIdToken.getMasterTokenSerialNumber() + "; mt " + masterToken.getSerialNumber());
            }
        }
        final HashSet<ServiceToken> set = new HashSet<ServiceToken>();
        set.addAll((Collection<?>)this.unboundServiceTokens);
        if (masterToken != null) {
            final Set<ServiceToken> set2 = this.mtServiceTokens.get(masterToken.getSerialNumber());
            if (set2 != null) {
                for (final ServiceToken serviceToken : set2) {
                    if (!serviceToken.isUserIdTokenBound()) {
                        set.add(serviceToken);
                    }
                }
            }
        }
        if (userIdToken != null) {
            final Set<ServiceToken> set3 = this.uitServiceTokens.get(userIdToken.getSerialNumber());
            if (set3 != null) {
                for (final ServiceToken serviceToken2 : set3) {
                    if (serviceToken2.isBoundTo(masterToken)) {
                        set.add(serviceToken2);
                    }
                }
            }
        }
        // monitorexit(this)
        return set;
    }
    
    @Override
    public UserIdToken getUserIdToken(final String s) {
        return this.userIdTokens.get(s);
    }
    
    @Override
    public void removeCryptoContext(final MasterToken masterToken) {
        while (true) {
            Label_0146: {
                synchronized (this) {
                    if (this.cryptoContexts.remove(masterToken) != null) {
                        final long serialNumber = masterToken.getSerialNumber();
                        final Iterator<MasterToken> iterator = this.cryptoContexts.keySet().iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().getSerialNumber() == serialNumber) {
                                return;
                            }
                        }
                        this.nonReplayableIds.remove(serialNumber);
                        for (final UserIdToken userIdToken : this.userIdTokens.values()) {
                            if (userIdToken.isBoundTo(masterToken)) {
                                this.removeUserIdToken(userIdToken);
                            }
                        }
                        break Label_0146;
                    }
                    return;
                }
                try {
                    final MasterToken masterToken2;
                    this.removeServiceTokens(null, masterToken2, null);
                }
                catch (MslException ex) {
                    throw new MslInternalException("Unexpected exception while removing master token bound service tokens.", ex);
                }
            }
        }
    }
    
    @Override
    public void removeServiceTokens(final String s, final MasterToken masterToken, final UserIdToken userIdToken) {
        // monitorenter(this)
        if (userIdToken != null && masterToken != null) {
            try {
                if (!userIdToken.isBoundTo(masterToken)) {
                    throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_MISMATCH, "uit mtserialnumber " + userIdToken.getMasterTokenSerialNumber() + "; mt " + masterToken.getSerialNumber());
                }
            }
            finally {
            }
            // monitorexit(this)
        }
        if (s != null && masterToken == null && userIdToken == null) {
            final Iterator<ServiceToken> iterator = this.unboundServiceTokens.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getName().equals(s)) {
                    iterator.remove();
                }
            }
            for (final Map.Entry<Long, Set<ServiceToken>> entry : this.mtServiceTokens.entrySet()) {
                final Long n = entry.getKey();
                final Set<ServiceToken> set = entry.getValue();
                final Iterator<ServiceToken> iterator3 = set.iterator();
                while (iterator3.hasNext()) {
                    if (iterator3.next().getName().equals(s)) {
                        iterator3.remove();
                    }
                }
                this.mtServiceTokens.put(n, set);
            }
            for (final Map.Entry<Long, Set<ServiceToken>> entry2 : this.uitServiceTokens.entrySet()) {
                final Long n2 = entry2.getKey();
                final Set<ServiceToken> set2 = entry2.getValue();
                final Iterator<ServiceToken> iterator5 = set2.iterator();
                while (iterator5.hasNext()) {
                    if (iterator5.next().getName().equals(s)) {
                        iterator5.remove();
                    }
                }
                this.uitServiceTokens.put(n2, set2);
            }
        }
        if (masterToken != null && userIdToken == null) {
            final Set<ServiceToken> set3 = this.mtServiceTokens.get(masterToken.getSerialNumber());
            if (set3 != null) {
                final Iterator<ServiceToken> iterator6 = set3.iterator();
                while (iterator6.hasNext()) {
                    final ServiceToken serviceToken = iterator6.next();
                    if (s == null || serviceToken.getName().equals(s)) {
                        iterator6.remove();
                    }
                }
            }
            for (final Map.Entry<Long, Set<ServiceToken>> entry3 : this.uitServiceTokens.entrySet()) {
                final Long n3 = entry3.getKey();
                final Set<ServiceToken> set4 = entry3.getValue();
                final Iterator<ServiceToken> iterator8 = set4.iterator();
                while (iterator8.hasNext()) {
                    final ServiceToken serviceToken2 = iterator8.next();
                    if ((s == null || serviceToken2.getName().equals(s)) && serviceToken2.isBoundTo(masterToken)) {
                        iterator8.remove();
                    }
                }
                this.uitServiceTokens.put(n3, set4);
            }
        }
        if (userIdToken != null) {
            final Set<ServiceToken> set5 = this.uitServiceTokens.get(userIdToken.getSerialNumber());
            if (set5 != null) {
                final Iterator<ServiceToken> iterator9 = set5.iterator();
                while (iterator9.hasNext()) {
                    final ServiceToken serviceToken3 = iterator9.next();
                    if ((s == null || serviceToken3.getName().equals(s)) && (masterToken == null || serviceToken3.isBoundTo(masterToken))) {
                        iterator9.remove();
                    }
                }
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void removeUserIdToken(final UserIdToken userIdToken) {
        while (true) {
            for (final MasterToken masterToken : this.cryptoContexts.keySet()) {
                if (userIdToken.isBoundTo(masterToken)) {
                    Block_3: {
                        for (final Map.Entry<String, UserIdToken> entry : this.userIdTokens.entrySet()) {
                            if (entry.getValue().equals(userIdToken)) {
                                break Block_3;
                            }
                        }
                        return;
                    }
                    final Map.Entry<String, UserIdToken> entry;
                    this.userIdTokens.remove(entry.getKey());
                    try {
                        this.removeServiceTokens(null, masterToken, userIdToken);
                        return;
                    }
                    catch (MslException ex) {
                        throw new MslInternalException("Unexpected exception while removing user ID token bound service tokens.", ex);
                    }
                    break;
                }
            }
            MasterToken masterToken = null;
            continue;
        }
    }
    
    @Override
    public void setCryptoContext(final MasterToken masterToken, final ICryptoContext cryptoContext) {
        if (cryptoContext == null) {
            this.removeCryptoContext(masterToken);
            return;
        }
        this.cryptoContexts.put(masterToken, cryptoContext);
    }
}
