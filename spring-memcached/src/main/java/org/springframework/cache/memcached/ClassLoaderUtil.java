package org.springframework.cache.memcached;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class ClassLoaderUtil {
	public static ClassLoader getStandardClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static ClassLoader getFallbackClassLoader() {
		return ClassLoaderUtil.class.getClassLoader();
	}

	public static Object createNewInstance(String className)
			throws CacheException {
		return createNewInstance(className, new Class[0], new Object[0]);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object createNewInstance(String className, Class[] argTypes,
			Object[] args) throws CacheException {
		Class clazz;
		try {
			clazz = loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new CacheException("Unable to load class " + className
					+ ". Initial cause was " + e.getMessage(), e);
		}
		Object newInstance;
		try {
			Constructor constructor = clazz.getConstructor(argTypes);
			newInstance = constructor.newInstance(args);
		} catch (IllegalAccessException e) {
			throw new CacheException("Unable to load class " + className
					+ ". Initial cause was " + e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new CacheException("Unable to load class " + className
					+ ". Initial cause was " + e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new CacheException("Unable to load class " + className
					+ ". Initial cause was " + e.getMessage(), e);
		} catch (SecurityException e) {
			throw new CacheException("Unable to load class " + className
					+ ". Initial cause was " + e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new CacheException("Unable to load class " + className
					+ ". Initial cause was " + e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new CacheException("Unable to load class " + className
					+ ". Initial cause was " + e.getCause().getMessage(),
					e.getCause());
		}

		return newInstance;
	}

	@SuppressWarnings("rawtypes")
	public static Class loadClass(String className)
			throws ClassNotFoundException {
		Class clazz;
		try {
			clazz = Class.forName(className, true, getStandardClassLoader());
		} catch (ClassNotFoundException e) {
			clazz = Class.forName(className, true, getFallbackClassLoader());
		}

		return clazz;
	}
}