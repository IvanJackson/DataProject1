package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uprm.ece.icom4035.polynomial.TermImp;

public class TermIterator<Term> implements Iterator<Term> {
	private String string;
	private char current;
	private String tempC="";
	private String tempE="";
	public TermIterator(String string) {
		this.string=string;
	}
	@Override
	public boolean hasNext() {
		return this.current < this.string.length();
	}

	@Override
	public Term next() {
		if(this.hasNext()) {
			TermImp term = new TermImp();
			for(int i=0;i<this.string.length();) {
				if(string.charAt(i)=='x') {
					tempC=string.substring(0, i-1);
				}
				if(string.charAt(i)=='^') {
					while(string.charAt(i)!='+'||string.charAt(i)!='-'||string.charAt(i)!='*'||string.charAt(i)!='/') {
						i++;
						tempE+=string.charAt(i);
					}
				}
				current=this.string.charAt(i+1);
				break;
			}
			term.setCoefficient(Double.parseDouble(tempC));
			term.setExponent(Integer.parseInt(tempE));
			return (Term)term;
		}
		throw new NoSuchElementException();
	}
}
