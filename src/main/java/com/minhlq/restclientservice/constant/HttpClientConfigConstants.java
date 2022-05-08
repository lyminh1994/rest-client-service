package com.minhlq.restclientservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpClientConfigConstants {

    // Socket factory registry
    public static final String HTTPS_SCHEMA = "https";
    public static final String HTTP_SCHEMA = "http";

    // Connection pool
    public static final int MAX_ROUTE_CONNECTIONS = 40;
    public static final int MAX_TOTAL_CONNECTIONS = 40;
    public static final int MAX_LOCALHOST_CONNECTIONS = 80;

    // Keep alive
    public static final int DEFAULT_KEEP_ALIVE_TIME = 20 * 1000; // 20 sec

    // Idle connection monitor
    public static final int IDLE_CONNECTION_WAIT_TIME = 30 * 1000; // 30 sec

    // Scheduler thread pool task
    public static final String THREAD_NAME_PREFIX = "idle-monitor-";
    public static final int DEFAULT_POOL_SIZE = 5;

    // Timeouts
    public static final int CONNECTION_TIMEOUT =
        30 * 1000; // 30 sec, the time for waiting until a connection is established
    public static final int REQUEST_TIMEOUT =
        30 * 1000; // 30 sec, the time for waiting for a connection from connection pool
    public static final int SOCKET_TIMEOUT = 60 * 1000; // 60 sec, the time for waiting for data
}
