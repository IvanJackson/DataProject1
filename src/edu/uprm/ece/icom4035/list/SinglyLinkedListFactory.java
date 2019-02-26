package edu.uprm.ece.icom4035.list;

import edu.uprm.ece.icom4035.polynomial.Term;
import edu.uprm.ece.icom4035.list.SingleLinkedList;

public class SinglyLinkedListFactory<T> implements ListFactory<Term> {

	@Override
	public List<Term> newInstance() {
		// TODO Auto-generated method stub
		return new SingleLinkedList<Term>();
	}
	

}
