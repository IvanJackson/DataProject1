package edu.uprm.ece.icom4035.list;

import java.util.Iterator;

public class SingleLinkedList<E> implements List<E>{

	private int length;
	private Node<E> DH, current;
	

	
	public SingleLinkedList() {
		DH = new Node<E>();
		current = new Node<E>();
		length = 0;
	}
	
	
	@Override
	public Iterator<E> iterator() {
		return new ListForwardIterator<E>(this);
	}

	@Override
	public void add(E obj) {
		if(this.length==0) {
			Node<E> newNode = new Node<E>(obj,null);
			DH.setNext(newNode);
		}
		else {
			Iterator<E> iter = this.iterator();
			while(iter.hasNext()) current = (Node<E>) iter.next();
			current.setNext(new Node<E>(obj, null));
			length++;
			
		} 
	}

	@Override
	public void add(int index, E obj) {
		if(index==0) {
			this.add(obj);
		}
		else {
			
			}
		}

	@Override
	public boolean remove(E obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int removeAll(E obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E get(int index) {
		current = DH.getNext();
		int counter =0;
		while(counter!=index) {
			current=current.getNext();
			counter++;
		}
		return current.getElement();
	}

	@Override
	public E set(int index, E obj) throws IndexOutOfBoundsException{
		if(this.size()<index) throw new IndexOutOfBoundsException("You have reached the end of the list.");
		E vtr = this.get(index);
		int counter =0;
		while(counter!=index) {
			current=current.getNext();
			counter++;
		}
		current.setElement(obj);
		return vtr;
	}

	@Override
	public E first() {
		return DH.getNext().getElement();
	}

	@Override
	public E last() {
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()) current = (Node<E>) iter.next();
		return current.getElement();
	}

	@Override
	public int firstIndex(E obj) {
		Iterator<E> iter = this.iterator();
		int counter =-1;
		while(iter.hasNext()) {
			current = (Node<E>) iter.next();
			counter++;
			if(current.getElement()==obj) {
				return counter;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		Iterator<E> iter = this.iterator();
		int counter =-1;
		int vtr =0; 
		while(iter.hasNext()) {
			current = (Node<E>) iter.next();
			counter++;
			if(current.getElement()==obj) {
				vtr = counter;
			}
		}
		if(vtr!=-1) return vtr;
		else return -1;
	}

	@Override
	public int size() {
		return this.length;
	}

	@Override
	public boolean isEmpty() {
		return this.size()==0;
	}

	@Override
	public boolean contains(E obj) {
		return (this.firstIndex(obj)!=-1 ?  true :  false);
	}

	@Override
	public void clear() {
		DH.setNext(null);
	}
	
	private static class Node<E> {
		private E element; 
		private Node<E> next; 
		public Node() { 
			element = null; 
			next = null; 
		}
		public Node(E data, Node<E> next) { 
			this.element = data; 
			this.next = next; 
		}
		public Node(E data)  { 
			this.element = data; 
			next = null; 
		}
		public E getElement() {
			return element;
		}
		public void setElement(E data) {
			this.element = data;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
	}
}
