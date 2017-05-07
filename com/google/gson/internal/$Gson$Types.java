// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.util.NoSuchElementException;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Properties;
import java.util.Collection;
import java.util.Arrays;
import java.lang.reflect.TypeVariable;
import java.io.Serializable;
import java.lang.reflect.WildcardType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public final class $Gson$Types
{
    static final Type[] EMPTY_TYPE_ARRAY;
    
    static {
        EMPTY_TYPE_ARRAY = new Type[0];
    }
    
    public static GenericArrayType arrayOf(final Type type) {
        return new $Gson$Types$GenericArrayTypeImpl(type);
    }
    
    public static Type canonicalize(final Type type) {
        if (type instanceof Class) {
            Serializable s = (Class)type;
            if (((Class)s).isArray()) {
                s = new $Gson$Types$GenericArrayTypeImpl(canonicalize(((Class)s).getComponentType()));
            }
            return (Class<?>)s;
        }
        if (type instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType)type;
            return new $Gson$Types$ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        }
        if (type instanceof GenericArrayType) {
            return new $Gson$Types$GenericArrayTypeImpl(((GenericArrayType)type).getGenericComponentType());
        }
        if (type instanceof WildcardType) {
            final WildcardType wildcardType = (WildcardType)type;
            return new $Gson$Types$WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
        }
        return type;
    }
    
    private static void checkNotPrimitive(final Type type) {
        $Gson$Preconditions.checkArgument(!(type instanceof Class) || !((Class)type).isPrimitive());
    }
    
    private static Class<?> declaringClassOf(final TypeVariable<?> typeVariable) {
        final Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class<?>)genericDeclaration;
        }
        return null;
    }
    
    static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static boolean equals(final Type type, final Type type2) {
        final boolean b = true;
        final boolean b2 = true;
        final boolean b3 = true;
        final boolean b4 = false;
        boolean b5;
        if (type == type2) {
            b5 = true;
        }
        else {
            if (type instanceof Class) {
                return type.equals(type2);
            }
            if (type instanceof ParameterizedType) {
                b5 = b4;
                if (type2 instanceof ParameterizedType) {
                    final ParameterizedType parameterizedType = (ParameterizedType)type;
                    final ParameterizedType parameterizedType2 = (ParameterizedType)type2;
                    return equal(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments()) && b3;
                }
            }
            else if (type instanceof GenericArrayType) {
                b5 = b4;
                if (type2 instanceof GenericArrayType) {
                    return equals(((GenericArrayType)type).getGenericComponentType(), ((GenericArrayType)type2).getGenericComponentType());
                }
            }
            else if (type instanceof WildcardType) {
                b5 = b4;
                if (type2 instanceof WildcardType) {
                    final WildcardType wildcardType = (WildcardType)type;
                    final WildcardType wildcardType2 = (WildcardType)type2;
                    return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds()) && b;
                }
            }
            else {
                b5 = b4;
                if (type instanceof TypeVariable) {
                    b5 = b4;
                    if (type2 instanceof TypeVariable) {
                        final TypeVariable typeVariable = (TypeVariable)type;
                        final TypeVariable typeVariable2 = (TypeVariable)type2;
                        return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName()) && b2;
                    }
                }
            }
        }
        return b5;
    }
    
    public static Type getArrayComponentType(final Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType)type).getGenericComponentType();
        }
        return ((Class)type).getComponentType();
    }
    
    public static Type getCollectionElementType(Type supertype, final Class<?> clazz) {
        final Type type = supertype = getSupertype(supertype, clazz, Collection.class);
        if (type instanceof WildcardType) {
            supertype = ((WildcardType)type).getUpperBounds()[0];
        }
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType)supertype).getActualTypeArguments()[0];
        }
        return Object.class;
    }
    
    static Type getGenericSupertype(final Type type, Class<?> clazz, final Class<?> clazz2) {
        if (clazz2 == clazz) {
            return type;
        }
        if (clazz2.isInterface()) {
            final Class<?>[] interfaces = clazz.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i) {
                if (interfaces[i] == clazz2) {
                    return clazz.getGenericInterfaces()[i];
                }
                if (clazz2.isAssignableFrom(interfaces[i])) {
                    return getGenericSupertype(clazz.getGenericInterfaces()[i], interfaces[i], clazz2);
                }
            }
        }
        if (!clazz.isInterface()) {
            while (clazz != Object.class) {
                final Class<? super Object> superclass = clazz.getSuperclass();
                if (superclass == clazz2) {
                    return clazz.getGenericSuperclass();
                }
                if (clazz2.isAssignableFrom(superclass)) {
                    return getGenericSupertype(clazz.getGenericSuperclass(), superclass, clazz2);
                }
                clazz = (Class<Object>)superclass;
            }
        }
        return clazz2;
    }
    
    public static Type[] getMapKeyAndValueTypes(Type supertype, final Class<?> clazz) {
        if (supertype == Properties.class) {
            return new Type[] { String.class, String.class };
        }
        supertype = getSupertype(supertype, clazz, Map.class);
        if (supertype instanceof ParameterizedType) {
            return ((ParameterizedType)supertype).getActualTypeArguments();
        }
        return new Type[] { Object.class, Object.class };
    }
    
    public static Class<?> getRawType(Type rawType) {
        if (rawType instanceof Class) {
            return (Class<?>)rawType;
        }
        if (rawType instanceof ParameterizedType) {
            rawType = ((ParameterizedType)rawType).getRawType();
            $Gson$Preconditions.checkArgument(rawType instanceof Class);
            return (Class<?>)rawType;
        }
        if (rawType instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType)rawType).getGenericComponentType()), 0).getClass();
        }
        if (rawType instanceof TypeVariable) {
            return Object.class;
        }
        if (rawType instanceof WildcardType) {
            return getRawType(((WildcardType)rawType).getUpperBounds()[0]);
        }
        String name;
        if (rawType == null) {
            name = "null";
        }
        else {
            name = rawType.getClass().getName();
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + rawType + "> is of type " + name);
    }
    
    static Type getSupertype(final Type type, final Class<?> clazz, final Class<?> clazz2) {
        $Gson$Preconditions.checkArgument(clazz2.isAssignableFrom(clazz));
        return resolve(type, clazz, getGenericSupertype(type, clazz, clazz2));
    }
    
    private static int hashCodeOrZero(final Object o) {
        if (o != null) {
            return o.hashCode();
        }
        return 0;
    }
    
    private static int indexOf(final Object[] array, final Object o) {
        for (int i = 0; i < array.length; ++i) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
    
    public static ParameterizedType newParameterizedTypeWithOwner(final Type type, final Type type2, final Type... array) {
        return new $Gson$Types$ParameterizedTypeImpl(type, type2, array);
    }
    
    public static Type resolve(Type type, final Class<?> clazz, Type type2) {
        Type type3;
        for (type3 = type2; type3 instanceof TypeVariable; type3 = type2) {
            final TypeVariable<?> typeVariable = (TypeVariable<?>)type3;
            type2 = resolveTypeVariable(type, clazz, typeVariable);
            if (type2 == typeVariable) {
                return type2;
            }
        }
        if (type3 instanceof Class && ((Class)type3).isArray()) {
            type2 = type3;
            final Class<?> componentType = ((Class)type2).getComponentType();
            type = resolve(type, clazz, componentType);
            if (componentType != type) {
                return arrayOf(type);
            }
            return type2;
        }
        else if (type3 instanceof GenericArrayType) {
            type2 = type3;
            final Type genericComponentType = ((GenericArrayType)type2).getGenericComponentType();
            type = resolve(type, clazz, genericComponentType);
            if (genericComponentType != type) {
                return arrayOf(type);
            }
            return type2;
        }
        else if (type3 instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType)type3;
            type2 = parameterizedType.getOwnerType();
            final Type resolve = resolve(type, clazz, type2);
            int n;
            if (resolve != type2) {
                n = 1;
            }
            else {
                n = 0;
            }
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type[] array;
            int n2;
            for (int length = actualTypeArguments.length, i = 0; i < length; ++i, actualTypeArguments = array, n = n2) {
                final Type resolve2 = resolve(type, clazz, actualTypeArguments[i]);
                array = actualTypeArguments;
                n2 = n;
                if (resolve2 != actualTypeArguments[i]) {
                    array = actualTypeArguments;
                    if ((n2 = n) == 0) {
                        array = actualTypeArguments.clone();
                        n2 = 1;
                    }
                    array[i] = resolve2;
                }
            }
            type2 = parameterizedType;
            if (n != 0) {
                return newParameterizedTypeWithOwner(resolve, parameterizedType.getRawType(), actualTypeArguments);
            }
            return type2;
        }
        else {
            type2 = type3;
            if (!(type3 instanceof WildcardType)) {
                return type2;
            }
            final WildcardType wildcardType = (WildcardType)type3;
            final Type[] lowerBounds = wildcardType.getLowerBounds();
            final Type[] upperBounds = wildcardType.getUpperBounds();
            if (lowerBounds.length == 1) {
                type = resolve(type, clazz, lowerBounds[0]);
                type2 = wildcardType;
                if (type != lowerBounds[0]) {
                    return supertypeOf(type);
                }
                return type2;
            }
            else {
                type2 = wildcardType;
                if (upperBounds.length != 1) {
                    return type2;
                }
                type = resolve(type, clazz, upperBounds[0]);
                type2 = wildcardType;
                if (type != upperBounds[0]) {
                    return subtypeOf(type);
                }
                return type2;
            }
        }
    }
    
    static Type resolveTypeVariable(Type genericSupertype, final Class<?> clazz, final TypeVariable<?> typeVariable) {
        final Class<?> declaringClass = declaringClassOf(typeVariable);
        if (declaringClass != null) {
            genericSupertype = getGenericSupertype(genericSupertype, clazz, declaringClass);
            if (genericSupertype instanceof ParameterizedType) {
                return ((ParameterizedType)genericSupertype).getActualTypeArguments()[indexOf(declaringClass.getTypeParameters(), typeVariable)];
            }
        }
        return typeVariable;
    }
    
    public static WildcardType subtypeOf(final Type type) {
        return new $Gson$Types$WildcardTypeImpl(new Type[] { type }, $Gson$Types.EMPTY_TYPE_ARRAY);
    }
    
    public static WildcardType supertypeOf(final Type type) {
        return new $Gson$Types$WildcardTypeImpl(new Type[] { Object.class }, new Type[] { type });
    }
    
    public static String typeToString(final Type type) {
        if (type instanceof Class) {
            return ((Class)type).getName();
        }
        return type.toString();
    }
}
