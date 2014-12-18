package org.springframework.cache.memcached;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.util.Assert;

public class MemcachedCacheCacheManager extends AbstractTransactionSupportingCacheManager {

	private CacheManager cacheManager;

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}


	protected Collection<? extends Cache> loadCaches() {

		Assert.notNull(cacheManager, "A backing Memcached CacheManager is required");
		 String[] names = cacheManager.getCacheNames();
		Collection<Cache> caches = new LinkedHashSet<Cache>(names.length);
		 for (String name : names) {
			caches.add(new MemCachedCache(cacheManager.getMemCache(name)));
		}


		return caches;
	}

	public Cache getCache(String name) {
		Cache cache = super.getCache(name);
		if (cache == null) {
			MemCache memcache = cacheManager.getMemCache(name);
			if (memcache != null) {
				addCache(cache);
			    cache = super.getCache(name);
			}
		}
		return cache;
	}

}
