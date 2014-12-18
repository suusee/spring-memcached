package org.springframework.cache.memcached;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientCallable;
import net.rubyeye.xmemcached.exception.MemcachedException;

public class MemCache {

	public static final String split = "_";

	private final String name;
	private final int expire;

	private Status status;

	private final MemcachedClient memcachedClient;

	public MemCache(String name, int expire, MemcachedClient memcachedClient) {
		this.name = name;
		this.expire = expire;
		this.memcachedClient = memcachedClient;

	}

	private String getKey(String key) {
		return name + split + key;
	}

	public void clear() {

		try {
			memcachedClient.invalidateNamespace(name);
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

	public void remove(Object key) {

		try {

			final String keys = this.getKey(key.toString());
			memcachedClient.withNamespace(name,
					new MemcachedClientCallable<Void>() {

						public Void call(MemcachedClient client)
								throws MemcachedException,
								InterruptedException, TimeoutException {
							memcachedClient.deleteWithNoReply(keys);
							return null;
						}
					});
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	public void put(Object key, final Object value) {

		if (value == null)
			return;
		try {

			final String keys = this.getKey(key.toString());

			memcachedClient.withNamespace(name,
					new MemcachedClientCallable<Void>() {

						public Void call(MemcachedClient client)
								throws MemcachedException,
								InterruptedException, TimeoutException {
							memcachedClient.setWithNoReply(keys, expire, value);
							return null;
						}
					});
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

	}

	public Object get(Object key) {

		try {
			final String keys = this.getKey(key.toString());
			return memcachedClient.withNamespace(name,
					new MemcachedClientCallable<Object>() {
						public Object call(MemcachedClient client)
								throws MemcachedException,
								InterruptedException, TimeoutException {
							return client.get(keys);
						}
					});
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		uninitialised, alive, shudown;
	}

}
