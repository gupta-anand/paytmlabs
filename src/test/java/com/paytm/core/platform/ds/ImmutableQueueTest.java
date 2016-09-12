package com.paytm.core.platform.ds;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ImmutableQueueTest {

	@Test
	public void testEnQueue() {
		ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>();
		ImmutableQueue<Integer> newQueue = queue.enQueue(1);
		assertNotSame("should not be same Object", queue, newQueue);
		assertSame("Values should be same", newQueue.head(), 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnQueueForException() {
		ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>();
		queue.enQueue(null);
	}

	@Test
	public void testDeQueue() {
		ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>();
		ImmutableQueue<Integer> newQueue1 = queue.enQueue(1);
		assertNotSame("should not be same Object", queue, newQueue1);

		ImmutableQueue<Integer> newQueue2 = newQueue1.deQueue();
		assertNotSame("should not be same Object", queue, newQueue1);
		assertNotSame("should not be same Object", queue, newQueue2);
		assertTrue(newQueue2.isEmpty());
	}

	@Test(expected = NoSuchElementException.class)
	public void testDeQueueForException() {
		ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>();
		queue.deQueue();
	}

	@Test
	public void testHead() {
		ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>();
		ImmutableQueue<Integer> finalQueue = queue.enQueue(1);
		finalQueue = finalQueue.enQueue(2);
		finalQueue = finalQueue.deQueue();
		finalQueue = finalQueue.enQueue(3);

		assertNotSame("should not be same Object", queue, finalQueue);
		assertTrue(finalQueue.size() == 2);
		assertTrue(finalQueue.head() == 2);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testHeadForException() {
		ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>();
		queue.head();
	}
	
	@Test
	public void testSize(){
		ImmutableQueue<Integer> queue = new ImmutableQueue<Integer>();
		assertTrue(queue.size() == 0);
		
		ImmutableQueue<Integer> finalQueue = queue.enQueue(1);
		finalQueue = finalQueue.enQueue(2);
		finalQueue = finalQueue.deQueue();
		finalQueue = finalQueue.enQueue(3);
		assertTrue(finalQueue.size() == 2);
	}

}
