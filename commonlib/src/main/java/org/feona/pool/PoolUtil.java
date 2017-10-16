package org.feona.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PoolUtil {

	private static class PoolUtilHolder {
		private static final PoolUtil INSTANCE = new PoolUtil();
	}

	private PoolUtil() {
		init();
	};

	private void init() {
		fixedThreadPool = Executors.newFixedThreadPool(3);
	}

	public static final PoolUtil getInstance() {
		return PoolUtilHolder.INSTANCE;
	}

	private ExecutorService fixedThreadPool;

	public void submit(Runnable r) {
		fixedThreadPool.execute(r);
	}

	public Future<?> submitTask(Runnable r) {
		return fixedThreadPool.submit(r);
	}
}
