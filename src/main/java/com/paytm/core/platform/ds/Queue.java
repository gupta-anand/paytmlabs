package com.paytm.core.platform.ds;

public interface Queue<T> {
	public Queue<T> enQueue(T t);

	public Queue<T> deQueue();

	public T head();

	public boolean isEmpty();
}