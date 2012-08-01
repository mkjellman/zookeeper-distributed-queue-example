package com.github.boneill42;

import org.junit.Test;

import com.github.boneill42.ZkDqQueuer;

public class ZkDqTest {
    
    @Test
    public void testDistributedQueue() throws Throwable {
    	ZkDqQueuer queuer = new ZkDqQueuer();
    	queuer.queueMessages();
    }

}
