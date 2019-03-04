package edu.uprm.ece.icom4035.list;

import java.util.Iterator;

public class ArrayList<E> implements List<E>{

	private E arr[];
	private int size;
	
	public ArrayList() {
		this.arr=(E[]) new Object[5];
		this.size=0;
	}
	public ArrayList(int initCap) {
		if(initCap<1) throw new IllegalArgumentException("Initial capacity has to be one or more.");
		this.arr=(E[]) new Object[initCap];
		this.size=0;
	}
	@Override
	public Iterator<E> iterator() {
		return new ListForwardIterator<E>(this);
	}

	@Override
	public void add(E obj) {
		if(this.size()==this.arr.length) this.changeCapacity(this.size()*2);
		this.arr[this.size()]=obj;
		this.size++;
//		if(this.size()<this.arr.length) {
//			E[] temp = (E[]) new Object[this.size()];
//			for(int i =0;i<this.size();i++) {
//				temp[i]=arr[i];
//			}
//			arr=temp;
//		}

	}

	@Override
	public void add(int index, E obj) throws IndexOutOfBoundsException{
		if(index<0||index>=this.size()) throw new IndexOutOfBoundsException("The index is bigger than the size of the array.");
		if(this.size()==this.arr.length) this.changeCapacity(this.size()*2);
		if(index==this.size()) this.add(obj);
		for(int i =this.size();i>index;i--) {
			this.arr[i]=this.arr[i-1];
		}
		this.arr[index]=obj;
		this.size++;
//		if(this.size()<this.arr.length) {
//			E[] temp = (E[]) new Object[this.size()];
//			for(int i =0;i<this.size();i++) {
//				temp[i]=arr[i];
//			}
//			arr=temp;
//		}
	}

	@Override
	public boolean remove(E obj) {
		if(this.size()==0) return false;
		for(int i =0;i<this.size();i++) {
			if(this.arr[i]==obj) {
				for(int j=i;j<this.size()-1;j++) {
					this.arr[j]=this.arr[j+1];
				}
				this.arr[this.size-1]=null;
				this.size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if (index < 0 || index >= this.size()) return false;
		for (int i=index; i < this.size() - 1;++i) {
			this.arr[i] = this.arr[i+1];
		}
		this.arr[this.size()-1] = null;
		this.size--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		if(this.size()==0) return 0;
		int counter=0;
		for(int i =0;i<this.size();i++) {
			if(this.arr[i]==obj) {
				for(int j=i;j<this.size()-1;j++) {
					this.arr[j]=this.arr[j+1];
				}
				this.arr[this.size-1]=null;
				this.size--;
				counter++;
			}
		}
		return counter;
	}

	@Override
	public E get(int index) {
		if(index<0||index>=this.size()) throw new IndexOutOfBoundsException("The index is bigger than the size of the array");
		return this.arr[index];
	}

	@Override
	public E set(int index, E obj) throws IndexOutOfBoundsException{
		if(index<0||index>=this.size()) throw new IndexOutOfBoundsException("The index is bigger than the size of the array");
		E etr = this.arr[index];
		this.arr[index]=obj;
		return etr;
	}

	@Override
	public E first() {
		return this.arr[0];
	}

	@Override
	public E last() {
		return this.arr[this.size()];
	}

	@Override
	public int firstIndex(E obj) {
		for(int i =0;i<this.size();i++) {
			if(this.arr[i]==obj) return i;
		}
		return -1;
	}

	@Override
	public int lastIndex(E obj) {
		for(int i =this.size();i>=0;i--) {
			if(this.arr[i]==obj) return i;
		}
		return -1;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	@Override
	public boolean contains(E obj) {
		for(int i =0;i<this.size();i++) {
			if(this.arr[i]==obj) return true;
		}
		return false;
	}

	@Override
	public void clear() {
		for(int i =0;i<this.size();i++) {
			this.arr[i]=null;
		}
	}
	private void changeCapacity(int newCap) {
		E [] newArr = (E[]) new Object[newCap];
		for (int i=0; i < this.size(); ++i) {
			newArr[i] = this.arr[i];
		}
		this.arr = newArr;
	}

}
