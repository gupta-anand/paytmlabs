package com.paytm.core.platform.ds;

import java.util.NoSuchElementException;

/**
 * 
 * Its an immutable first-in-first-out (FIFO) queue. 
 * This implementation maintains two immutable stack behind the scene.
 * enqueueStack is used to enqueue element and dequeueStack is used to dequeue element. 
 * When dequeueStack is empty, reverse the enqueueStack and replace dequeueStack with it, 
 * replace enqueueStack with an empty stack. 
 * Performance : Operation on Queue ideally should be constant time o(1) 
 * the reverse operation which is O(n) is amortised over all the dequeues, so it's close to O(1), 
 * thereby giving  around ~O(1) queue implementation with immutable data structures.
 * 
 * @author Anand
 * @param <T> Type of element in Queue
 */

public final class ImmutableQueue<T> implements Queue<T> {
	
	private ImmutableStack<T> enqueueStack;
	private ImmutableStack<T> dequeueStack;
	
	@SuppressWarnings("unchecked")
	public ImmutableQueue() {
		this.enqueueStack = ImmutableStack.empty();
		this.dequeueStack = ImmutableStack.empty();
	}

	private ImmutableQueue(ImmutableStack<T> order, ImmutableStack<T> reverse) {
		this.enqueueStack = order;
		this.dequeueStack = reverse;
	}
	

	/**
	 * 
	 * Enqueue operation on queue to add element to the tail 
	 * @param T element to be added
	 * @return Queue with element added into tail without changing state of original queue
	 */
	public ImmutableQueue<T> enQueue(T element) {
		if (element == null)
			throw new IllegalArgumentException();
		return new ImmutableQueue<T>(this.enqueueStack.push(element), this.dequeueStack);
	}

	/**	  
	 *	
	 * Dequeue operation on queue to remove head element  
	 * @return Queue with element removed from head, its a new object returned.
	 * @exception  If this queue is empty, throws java.util.NoSuchElementException.
	 */
	@SuppressWarnings("unchecked")
	public ImmutableQueue<T> deQueue() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		
		if (!this.dequeueStack.isEmpty()) 
			return new ImmutableQueue<T>(this.enqueueStack, this.dequeueStack.getTail());
		else 			
			return new ImmutableQueue<T>(ImmutableStack.empty(), this.enqueueStack.reverse().getTail());
		
	}

	
	@SuppressWarnings("unchecked")
	private void normalizeQueue() {
		this.dequeueStack = this.enqueueStack.reverse();
		this.enqueueStack = ImmutableStack.empty();
	}

	/**
	 * Head of queue
	 * @return Returns head of queue without removing it from data structure
	 * @exception If this queue is empty, throws java.util.NoSuchElementException.
	 */
	public T head() {
		if (this.isEmpty())
			throw new NoSuchElementException();
		if (this.dequeueStack.isEmpty())
			normalizeQueue();
		return this.dequeueStack.getHead();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return this.enqueueStack.getSize() + this.dequeueStack.getSize();
	}

}
