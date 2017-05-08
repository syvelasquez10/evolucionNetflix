// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.Date;
import java.nio.ByteBuffer;
import io.realm.internal.Keep;

@Keep
public enum RealmFieldType
{
    BINARY(4), 
    BOOLEAN(1), 
    DATE(8), 
    DOUBLE(10), 
    FLOAT(9), 
    INTEGER(0), 
    LIST(13), 
    OBJECT(12), 
    STRING(2), 
    UNSUPPORTED_DATE(7), 
    UNSUPPORTED_MIXED(6), 
    UNSUPPORTED_TABLE(5);
    
    private static RealmFieldType[] typeList;
    private final int nativeValue;
    
    static {
        int i = 0;
        RealmFieldType.typeList = new RealmFieldType[15];
        for (RealmFieldType[] values = values(); i < values.length; ++i) {
            RealmFieldType.typeList[values[i].nativeValue] = values[i];
        }
    }
    
    private RealmFieldType(final int nativeValue) {
        this.nativeValue = nativeValue;
    }
    
    public static RealmFieldType fromNativeValue(final int n) {
        if (n >= 0 && n < RealmFieldType.typeList.length) {
            final RealmFieldType realmFieldType = RealmFieldType.typeList[n];
            if (realmFieldType != null) {
                return realmFieldType;
            }
        }
        throw new IllegalArgumentException("Invalid native Realm type: " + n);
    }
    
    public int getNativeValue() {
        return this.nativeValue;
    }
    
    public boolean isValid(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        switch (this.nativeValue) {
            default: {
                throw new RuntimeException("Unsupported Realm type:  " + this);
            }
            case 0: {
                if (!(o instanceof Long) && !(o instanceof Integer) && !(o instanceof Short)) {
                    b2 = b;
                    if (!(o instanceof Byte)) {
                        return b2;
                    }
                }
                b2 = true;
            }
            case 12:
            case 13:
            case 14: {
                return b2;
            }
            case 1: {
                return o instanceof Boolean;
            }
            case 2: {
                return o instanceof String;
            }
            case 4: {
                if (!(o instanceof byte[])) {
                    b2 = b;
                    if (!(o instanceof ByteBuffer)) {
                        return b2;
                    }
                }
                return true;
            }
            case 5: {
                if (o != null) {
                    b2 = b;
                    if (!(o instanceof Object[][])) {
                        return b2;
                    }
                }
                return true;
            }
            case 7: {
                return o instanceof Date;
            }
            case 8: {
                return o instanceof Date;
            }
            case 9: {
                return o instanceof Float;
            }
            case 10: {
                return o instanceof Double;
            }
        }
    }
}
