package org.springframework.cache.memcached;

public class HostConfiguration {

	private String address;
	private int port;
	private int weight;

	public static final int defaultWeight = 3;

	public HostConfiguration() {

		this.setWeight(defaultWeight);
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}


}
