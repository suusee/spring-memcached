package org.springframework.cache.memcached;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.cache.Cache;

public class CacheManager {

	private MemcachedClient memcachedClient;

	private ConcurrentMap<String, MemCache> memecaches = new ConcurrentHashMap<String, MemCache>();

	public CacheManager(Configuration configuration) {
		super();
		init(configuration);
	}

	public CacheManager(Configuration configuration, MemcachedClient memcachedClient) {
		super();
		this.memcachedClient = memcachedClient;
		init(configuration);
	}

	private void init(Configuration configLocation) {

		init(configLocation, null, null, null);
	}

	protected synchronized void init(Configuration initialConfiguration,String configurationFileName, URL configurationURL,InputStream configurationInputStream) {
		Configuration configuration;
		if (initialConfiguration == null)
			configuration = parse(configurationFileName, configurationURL,configurationInputStream);
		else {
			configuration = initialConfiguration;
		}

		try {
			doInit(configuration);
		} catch (CacheException e) {
			e.printStackTrace();

		}
	}

	private void doInit(Configuration configuration) {
		Map<String,CacheConfiguration> cacheConfigs = configuration.getCacheConfigurations();
	     for (CacheConfiguration config : cacheConfigs.values()) {
	        
	    	 MemCache memcache=new MemCache(config.getName(),config.getTimeToLiveSeconds(),memcachedClient);
	    	 memcache.setStatus(MemCache.Status.alive);
	    	 memecaches.put(config.getName(),memcache);
	      }		
	}

	private synchronized Configuration parse(String configurationFileName,
			URL configurationURL, InputStream configurationInputStream)
			throws CacheException {

		Configuration parsedConfig;
		if (configurationFileName != null) {

			parsedConfig = ConfigurationFactory.parse(new File(
					configurationFileName));
		} else if (configurationURL != null) {
			parsedConfig = ConfigurationFactory.parse(configurationURL);
		} else if (configurationInputStream != null) {
			parsedConfig = ConfigurationFactory.parse(configurationInputStream);
		} else {

			parsedConfig = ConfigurationFactory.parse();
		}
		return parsedConfig;
	}

	public void shutdown() {
		try {
			memcachedClient.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	public Cache getCache(String name) throws IllegalStateException,
			ClassCastException {
		MemCache memcache = (MemCache) this.memecaches.get(name);
		return ((memcache instanceof Cache) ? (Cache) memcache : null);
	}

	public MemCache getMemCache(String name) {
		return ((MemCache) this.memecaches.get(name));
	}

	public String[] getCacheNames() {
		return ((String[]) this.memecaches.keySet().toArray(new String[0]));

	}

}
