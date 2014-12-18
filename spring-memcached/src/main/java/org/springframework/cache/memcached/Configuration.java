package org.springframework.cache.memcached;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Configuration {

	private long connectTimeout;
	private int connectionPoolSize;
	private boolean failureMode;

	private final Map<String, CacheConfiguration> cacheConfigurations = new ConcurrentHashMap<String, CacheConfiguration>();

	private final List<HostConfiguration> hostConfigurations = new ArrayList<HostConfiguration>();

	public final Map<String, CacheConfiguration> getCacheConfigurations() {
		return this.cacheConfigurations;
	}

	public final List<HostConfiguration> getHostConfigurations() {
		return this.hostConfigurations;
	}

	public long getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(long connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getConnectionPoolSize() {
		return connectionPoolSize;
	}

	public void setConnectionPoolSize(int connectionPoolSize) {
		this.connectionPoolSize = connectionPoolSize;
	}

	public boolean isFailureMode() {
		return failureMode;
	}

	public void setFailureMode(boolean failureMode) {
		this.failureMode = failureMode;
	}

}
