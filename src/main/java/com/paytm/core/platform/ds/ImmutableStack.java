package com.paytm.core.platform.ds;

public final class ImmutableStack<T> {

	private T head;
	private ImmutableStack<T> tail;
	private int size;

	private ImmutableStack(T element, ImmutableStack<T> tail) {
		this.head = element;
		this.tail = tail;
		this.size = tail.size + 1;
	}

	private ImmutableStack() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	@SuppressWarnings("rawtypes")
	public static ImmutableStack empty() {
		return new ImmutableStack();
	}

	public ImmutableStack<T> reverse() {
		ImmutableStack<T> reverseStack = new ImmutableStack<T>();
		ImmutableStack<T> tail = this;
		while (!tail.isEmpty()) {
			reverseStack = reverseStack.push(tail.head);
			tail = tail.tail;
		}
		return reverseStack;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public ImmutableStack<T> push(T element) {
		return new ImmutableStack<T>(element, this);
	}

	public ImmutableStack<T> getTail() {
		return this.tail;
	}

	public T getHead() {
		return this.head;
	}

	public int getSize() {
		return this.size;
	}
}