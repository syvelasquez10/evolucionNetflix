// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

public class Property
{
    public static boolean INDEXED;
    public static boolean PRIMARY_KEY;
    public static boolean REQUIRED;
    private final long nativePtr;
    
    static {
        Property.PRIMARY_KEY = true;
        Property.REQUIRED = true;
        Property.INDEXED = true;
    }
    
    protected Property(final long nativePtr) {
        this.nativePtr = nativePtr;
    }
    
    public Property(final String s, final RealmFieldType realmFieldType, final RealmObjectSchema realmObjectSchema) {
        this.nativePtr = nativeCreateProperty(s, realmFieldType.getNativeValue(), realmObjectSchema.getClassName());
    }
    
    public Property(final String s, final RealmFieldType realmFieldType, final boolean b, final boolean b2, final boolean b3) {
        this.nativePtr = nativeCreateProperty(s, realmFieldType.getNativeValue(), b, b2, !b3);
    }
    
    private static native void nativeClose(final long p0);
    
    private static native long nativeCreateProperty(final String p0, final int p1, final String p2);
    
    private static native long nativeCreateProperty(final String p0, final int p1, final boolean p2, final boolean p3, final boolean p4);
    
    public void close() {
        if (this.nativePtr != 0L) {
            nativeClose(this.nativePtr);
        }
    }
    
    protected long getNativePtr() {
        return this.nativePtr;
    }
}
