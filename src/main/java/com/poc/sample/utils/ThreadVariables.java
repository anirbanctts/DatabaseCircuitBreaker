package com.poc.sample.utils;

public class ThreadVariables {
	private static ThreadLocal<String> localStoreId = new ThreadLocal<String>();

	private static ThreadLocal<String> localDeviceId = new ThreadLocal<String>();

	private static ThreadLocal<String> localCorrelationId = new ThreadLocal<String>();

	private ThreadVariables() {

	}

	public static String getTenantId() {
		return localStoreId.get();
	}

	public static void setTenantId(String tenantId) {
		localStoreId.set(tenantId);
	}

	public static String getDeviceId() {
		return localDeviceId.get();
	}

	public static void setDeviceId(String deviceId) {
		localDeviceId.set(deviceId);
	}

	public static String getCorrelationId() {
		return localCorrelationId.get();
	}

	public static void setCorrelationId(String correlationId) {
		localCorrelationId.set(correlationId);
	}

	public static void clear() {
		localStoreId.remove();
		localDeviceId.remove();
		localCorrelationId.remove();
	}
}
