package org.springframework.cache.memcached;

public class CacheConfiguration {

	private String name;

	private int timeToLiveSeconds;

	public static final int defaultTimeToLiveSeconds = 1000;

	public CacheConfiguration() {

		setDeafultTimeToLiveSeconds();
	}

	public CacheConfiguration(String name, int timeToLiveSeconds) {
		this.name = name;
		this.timeToLiveSeconds = timeToLiveSeconds;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeToLiveSeconds() {
		return timeToLiveSeconds;
	}

	public void setTimeToLiveSeconds(int timeToLiveSeconds) {
		this.timeToLiveSeconds = timeToLiveSeconds;
	}

	public void setDeafultTimeToLiveSeconds() {
		this.timeToLiveSeconds = defaultTimeToLiveSeconds;
	}

}
