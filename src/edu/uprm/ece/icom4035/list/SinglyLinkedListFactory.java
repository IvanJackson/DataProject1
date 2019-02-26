package edu.uprm.ece.icom4035.list;

import edu.uprm.ece.icom4035.list.SingleLinkedList;

public class SinglyLinkedListFactory<E> implements ListFactory<E> {

	@Override
	public List<E> newInstance() {
		return new SingleLinkedList<E>();
	}
}
