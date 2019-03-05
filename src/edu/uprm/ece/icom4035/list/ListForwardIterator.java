package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListForwardIterator<E> implements Iterator<E> {
	private List<E> list;
	private int currentPosition;
	private boolean canRemove;

	public  ListForwardIterator(List<E> list) {
		this.list = list;
		this.currentPosition = 0;
	}

	@Override
	public boolean hasNext() {
		return this.currentPosition < this.list.size();
	}

	@Override
	public E next() {
		if(!hasNext()) throw new NoSuchElementException("Iterator is finished.");
		else {
			canRemove = true;
			return list.get(currentPosition++);
		}
	}
	public void remove() throws IllegalStateException{
		if(!canRemove) throw new IllegalStateException("Invalid to remove.");
		list.remove(--currentPosition);
		canRemove=false;
	}
}
