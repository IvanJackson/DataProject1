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
			current = newNode;
			length++;
		}
		else {
			Iterator<E> iter = this.iterator();
//			current = (Node<E>) iter.next();
			current = DH.getNext();
			while(current.getNext()!=null) {
				current=current.getNext();
			}
			current.setNext(new Node<E>(obj, null));
			length++;
			
		} 
	}

	@Override
	public void add(int index, E obj) throws IndexOutOfBoundsException{
		if(index<0 || index>this.size()) throw new IndexOutOfBoundsException("Index is bigger than the size of the List.");
		if(index==0) {
			DH.setNext(new Node<E>(obj,null));
		}
		int counter =1;
		Node<E> newNode = new Node<E>(obj,null);
		Iterator<E> iter = this.iterator();
		current = (Node<E>) iter.next();
		Node<E> prev = DH;
		while(iter.hasNext()) {
			if(counter==index) {
				prev.setNext(newNode);
				newNode.setNext(current.getNext());
			}
			prev=current;
			current = (Node<E>) iter.next();
			counter++;
		}
		this.length++;
	}

	@Override
	public boolean remove(E obj) {
		Iterator<E> iter = this.iterator();
		Node<E> prev = DH;
		if(this.length==0) return false;
		current = (Node<E>) iter.next();
		while(iter.hasNext()) {
			if(current.getElement()==obj) {
				prev.setNext(current.getNext());
				this.length--;
				return true;
			}
			prev = current;
			current = (Node<E>) iter.next();
		}
		if(current.getElement()==obj) {
			prev.setNext(current.getNext());
			this.length--;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(int index) throws IndexOutOfBoundsException{
//		Iterator<E> iter = this.iterator();
//		current =(Node<E>) iter.next();
//		Node<E> prev = current;
//		if(index<0||index>this.size()) throw new IndexOutOfBoundsException("This index is not valid");
//		if(index==0) {
//			DH.setNext(current.getNext());
//			iter.remove();
//			this.length--;
//			return true;
//		}
//		int counter = 0;
//		while(iter.hasNext()) {
//			if(counter==index) {
//				prev.setNext(current.getNext());
//				iter.remove();
//				this.length--;
//				return true;
//			}
//			prev=current;
//			current=(Node<E>) iter.next();
//		}
//		return false;
		if (this.isEmpty()) {
			return false;
		}
		if(index<0||index>this.size()) throw new IndexOutOfBoundsException("This index is not valid");
		if (index == 0) {
			Node<E> temp = this.DH;
			E result = temp.getElement();
			this.DH = this.DH.getNext();
			temp.setNext(null);
			temp.setElement(null);
			this.length--;
			return true;
		}
		else {
			Node<E> temp1=this.DH;
			int counter =0;
			for(int i=0;i<index;i++) {
				temp1=temp1.getNext();
				counter++;
			}
			Node<E> temp2 = temp1.getNext();
			E result = temp2.getElement();
			temp1.setNext(temp2.getNext());
			temp2.setNext(null);
			temp2.setElement(null);
			this.length--;
			return true;
		}
	}

	@Override
	public int removeAll(E obj) {
		Iterator<E> iter = this.iterator();
		Node<E> prev = DH;
		if(this.length==0) return 0;
		current = (Node<E>) iter.next();
		int counter =0;
		while(iter.hasNext()) {
			if(current.getElement()==obj) {
				prev.setNext(current.getNext());
				this.length--;
				counter++;
			}
			prev = current;
			current = (Node<E>) iter.next();
		}
		if(current.getElement()==obj) {
			prev.setNext(current.getNext());
			this.length--;
			counter++;
		}
		return counter;
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
