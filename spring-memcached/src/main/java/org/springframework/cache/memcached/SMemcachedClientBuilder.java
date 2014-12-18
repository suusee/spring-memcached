package org.springframework.cache.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import net.rubyeye.xmemcached.CommandFactory;
import net.rubyeye.xmemcached.KeyProvider;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.MemcachedClientStateListener;
import net.rubyeye.xmemcached.MemcachedSessionLocator;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.buffer.BufferAllocator;
import net.rubyeye.xmemcached.transcoders.Transcoder;

import com.google.code.yanf4j.config.Configuration;
import com.google.code.yanf4j.core.SocketOption;

public class SMemcachedClientBuilder implements MemcachedClientBuilder
{

	public void addAuthInfo(InetSocketAddress arg0, AuthInfo arg1) {
		// TODO Auto-generated method stub
		
	}

	public void addStateListener(MemcachedClientStateListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public MemcachedClient build() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<InetSocketAddress, AuthInfo> getAuthInfoMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public BufferAllocator getBufferAllocator() {
		// TODO Auto-generated method stub
		return null;
	}

	public CommandFactory getCommandFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getConnectTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getOpTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	public MemcachedSessionLocator getSessionLocator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<SocketOption, Object> getSocketOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	public Transcoder getTranscoder() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isFailureMode() {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeAuthInfo(InetSocketAddress arg0) {
		// TODO Auto-generated method stub
		
	}

	public void removeStateListener(MemcachedClientStateListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setAuthInfoMap(Map<InetSocketAddress, AuthInfo> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setBufferAllocator(BufferAllocator arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setCommandFactory(CommandFactory arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setConfiguration(Configuration arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setConnectTimeout(long arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setConnectionPoolSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setEnableHealSession(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setFailureMode(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setHealSessionInterval(long arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setKeyProvider(KeyProvider arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setMaxQueuedNoReplyOperations(int arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setOpTimeout(long arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSanitizeKeys(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSessionLocator(MemcachedSessionLocator arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSocketOption(SocketOption arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setStateListeners(List<MemcachedClientStateListener> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setTranscoder(Transcoder arg0) {
		// TODO Auto-generated method stub
		
	}

}