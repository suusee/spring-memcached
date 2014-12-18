package org.springframework.cache.memcached;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.Assert;

public class MemCachedCache implements Cache {
	

	private final MemCache cache;

	public MemCachedCache(MemCache memcache) {
		 Assert.notNull(memcache, "MemCache must not be null");
		 Assert.notNull(memcache.getStatus(), "MemCache status must not be null");
		 Assert.isTrue(memcache.getStatus().equals(MemCache.Status.alive), "An 'alive' Memcache is required - current cache is  " + memcache.getStatus().toString());
		this.cache = memcache;
	}

	public void clear() {
	
		cache.clear();

	}

	public void evict(Object key) {

		cache.remove(key);
	}

	public ValueWrapper get(Object key) {

		Object value = this.cache.get(key);

		return ((value != null) ? new SimpleValueWrapper(value) : null);
	}

	public String getName() {
		return cache.getName();
	}

	public MemCache getNativeCache() {
		return cache;
	}

	public void put(Object key, Object value) {

		cache.put(key, value);

	}

	@SuppressWarnings("unchecked")
	public <T> T get(Object key, Class<T> type) {
	
		Object value = this.cache.get(key);
		   if(value != null){
			  if ((type != null) && (!(type.isInstance(value)))) {
		      throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);
		      }
		    return (T) value;
		   }
		return null;
	}
}
