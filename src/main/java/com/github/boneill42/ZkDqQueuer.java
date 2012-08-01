package com.github.boneill42;

import com.netflix.curator.ensemble.fixed.FixedEnsembleProvider;
import com.netflix.curator.framework.CuratorFramework;
import com.netflix.curator.framework.CuratorFrameworkFactory;
import com.netflix.curator.framework.recipes.queue.DistributedQueue;
import com.netflix.curator.framework.recipes.queue.QueueBuilder;
import com.netflix.curator.retry.RetryOneTime;

public class ZkDqQueuer {
	DistributedQueue<ZkDqWork> queue;

	public ZkDqQueuer() throws Exception {
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.retryPolicy(new RetryOneTime(10)).namespace("ZkDqTest")
				.ensembleProvider(new FixedEnsembleProvider("localhost:2181"))
				.build();
		client.start();

		ZkDqConsumer consumer = new ZkDqConsumer();
		ZkDqSerializer serializer = new ZkDqSerializer();
		QueueBuilder<ZkDqWork> builder = QueueBuilder.builder(client,
				consumer, serializer, "/com/zq/test");
		queue = builder.buildQueue();
		queue.start();

	}

	public void queueMessages() throws Exception {
		for (int i = 0; i < 10; i++) {
			ZkDqWork work = new ZkDqWork("testWork [" + i + "]");
			this.queue.put(work);
			System.out.println("Queued [" + i + "]");
		}
		Thread.sleep(5000);
	}

}
