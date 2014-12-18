package org.springframework.cache.memcached;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public final class ConfigurationFactory {

	protected static final Log logger = LogFactory
			.getLog(ConfigurationFactory.class);
	private static final String DEFAULT_CLASSPATH_CONFIGURATION_FILE = "/memcached.xml";
	private static final String FAILSAFE_CLASSPATH_CONFIGURATION_FILE = "/memcached-failsafe.xml";

	public static Configuration parse(File file) {

		if (file == null) {
			throw new CacheException("Attempt to configure memcached from null file.");
		}
		Configuration configuration = null;
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
			configuration = parse(input);
		} catch (Exception e) {
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				logger.error("IOException while closing configuration input stream. Error was "+ e.getMessage());
			}
		}

		return configuration;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Configuration parse(InputStream input) {
		logger.debug("Configuring memcached from InputStream");
		Configuration configuration = new Configuration();
		try {

			Document document = new SAXReader().read(input);
			
			Node configNode = document.selectSingleNode("/memcached/config");
			
			if(configNode!=null)
			{
				Node poolSize=configNode.selectSingleNode("connectionPoolSize");
				if(poolSize!=null){
				  String connectionPoolSize=poolSize.getText();
				 if(connectionPoolSize!=null&&(connectionPoolSize.equals("")==false)){
				  configuration.setConnectionPoolSize(Integer.valueOf(connectionPoolSize));
				 }
				}
				
				Node mode=configNode.selectSingleNode("failureMode");
				if(mode!=null){
				  String failureMode=mode.getText();
				  if(failureMode!=null&&(failureMode.equals("")==false)){
				    configuration.setFailureMode(Boolean.valueOf(failureMode));
					
				  }
				}

				
			}

			List nodeList = document.selectNodes("/memcached/cache");
			Iterator<Element> iterator = nodeList.iterator();
			while (iterator.hasNext()) {
				Element element = iterator.next();
				CacheConfiguration cacheConfig = new CacheConfiguration();
				String name = element.attributeValue("name");
				cacheConfig.setName(name);
				String timeToLiveSeconds = element.attributeValue("timeToLiveSeconds");
				if (timeToLiveSeconds != null) {
				cacheConfig.setTimeToLiveSeconds(Integer.valueOf(timeToLiveSeconds));
				}

				configuration.getCacheConfigurations().put(cacheConfig.getName(), cacheConfig);
			}
			
			List serverList = document.selectNodes("/memcached/server/host");
			Iterator<Element> iter = serverList.iterator();
			while (iter.hasNext()) {
				Element element = iter.next();
				HostConfiguration hostConfig = new HostConfiguration();
				String address = element.attributeValue("address");
				hostConfig.setAddress(address);
				String port = element.attributeValue("port");
				if (port != null) {
					hostConfig.setPort(Integer.valueOf(port));
				}
				String weight = element.attributeValue("weight");
				if (weight != null) {
					hostConfig.setWeight(Integer.valueOf(weight));
				}

				configuration.getHostConfigurations().add(hostConfig);
			}

		} catch (Exception e) {
			throw new CacheException("Error configuring from input stream. Initial cause was "+ e.getMessage(), e);
		}
		return configuration;
	}

	public static Configuration parse() throws CacheException {
		ClassLoader standardClassloader = ClassLoaderUtil.getStandardClassLoader();
		URL url = null;
		if (standardClassloader != null) {
			url = standardClassloader.getResource(DEFAULT_CLASSPATH_CONFIGURATION_FILE);
		}
		if (url == null) {
			url = ConfigurationFactory.class.getResource(DEFAULT_CLASSPATH_CONFIGURATION_FILE);
		}
		if (url == null) {
			
			url = ConfigurationFactory.class.getResource(FAILSAFE_CLASSPATH_CONFIGURATION_FILE);
		}

		Configuration configuration = parse(url);
		return configuration;
	}

	public static Configuration parse(URL url) {

		InputStream input = null;
		Configuration configuration = null;
		try {
			input = url.openStream();
			configuration = parse(input);
		} catch (Exception e) {
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				logger.error("IOException while closing configuration input stream. Error was "
						+ e.getMessage());
			}
		}
		return configuration;
	}

}
