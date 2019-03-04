package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;

import edu.uprm.ece.icom4035.list.List;
import edu.uprm.ece.icom4035.list.ListForwardIterator;
import edu.uprm.ece.icom4035.polynomial.TermListFactory;

public class PolynomialImp implements Polynomial{
	List<Term> PolyList = (List<Term>) TermListFactory.newListFactory().newInstance();
	private static char operator = '+';

	public PolynomialImp(String string) {
		int size = this.numOfTerms(string);
		int place =0;
		TermImp[] arr= new TermImp[size];
		arr= this.PolyStringToPolyList(size, string, arr, place);
		for(int i =0;i<arr.length;i++) {
			PolyList.add(arr[i]);
		}
		Iterator<Term> iter = this.iterator();
		Term current = iter.next();
		while(iter.hasNext()) {
			if(current.getCoefficient()==0) iter.remove();
			current = iter.next();
		}
		if(current.getCoefficient()==0) iter.remove();
	}

	@Override
	public Iterator<Term> iterator() {
		return new ListForwardIterator<Term>((List<Term>) this.PolyList);
	}

	public List<Term> getPolyList() {
		return this.PolyList;
	}

	public void setPolyList(List<Term> polyList) {
		PolyList = polyList;
	}

	@Override
	public Polynomial add(Polynomial P2) {
		String finalPoly ="";
		if(this.getPolyList().isEmpty()) return P2;
		if(((PolynomialImp)P2).getPolyList().isEmpty()) return this;
		Iterator<Term> iter1 = this.getPolyList().iterator();
		Iterator<Term> iter2 = P2.iterator();
		Term term1 = iter1.next();
		Term term2 = iter2.next();
		while(iter1.hasNext() && iter2.hasNext()) {
			if(term1.getExponent()>term2.getExponent()) {
				finalPoly = join(finalPoly, term1.getCoefficient()+"x^"+term1.getExponent());
				term1=iter1.next();
			} else if(term1.getExponent()<term2.getExponent()) {
				finalPoly = join(finalPoly, term2.getCoefficient()+"x^"+term2.getExponent());
				term2=iter2.next();
			}
			else {
				double sumCoefficient = term1.getCoefficient()+term2.getCoefficient();
				TermImp term = new TermImp();
				term.setCoefficient(sumCoefficient);
				term.setExponent(term1.getExponent());
				finalPoly = join(finalPoly,term.getCoefficient()+"x^"+term.getExponent());
				term1 = iter1.next();
				term2 = iter2.next();
			}
		}
//		if(!(iter1.hasNext()&&iter2.hasNext())){
		
		while(iter1.hasNext()) {
			if(term1.getExponent()>term2.getExponent()) {
//				finalPoly+=term1+"+";
				finalPoly = join(finalPoly, term1.getCoefficient()+"x^"+term1.getExponent());
				term1=iter1.next();
			} else if(term1.getCoefficient()<term2.getExponent()) {
				finalPoly = join(finalPoly, term2.getCoefficient()+"x^"+term2.getExponent());
				while(term1!=null) {
					finalPoly = join(finalPoly, term1.getCoefficient()+"x^"+term1.getExponent());
					term1 = iter1.next();
				}
			}
			else {
				double sumCoefficient = term1.getCoefficient()+term2.getCoefficient();
				TermImp term = new TermImp();
				term.setCoefficient(sumCoefficient);
				term.setExponent(term1.getExponent());
				finalPoly = join(finalPoly,term.getCoefficient()+"x^"+term.getExponent());
				term1 = iter1.next();
			}
		}
		while(iter2.hasNext()) {
			if(term1.getExponent()>term2.getExponent()) {
				finalPoly = join(finalPoly, term1.getCoefficient()+"x^"+term1.getExponent());
				while(term2!=null) {
					finalPoly = join(finalPoly, term2.getCoefficient()+"x^"+term2.getExponent());
					term2 = iter2.next();
				}
			} else if(term1.getCoefficient()<term2.getExponent()) {
				finalPoly = join(finalPoly, term2.getCoefficient()+"x^"+term2.getExponent());
				term2 = iter2.next();
			}
			else {
				double sumCoefficient = term1.getCoefficient()+term2.getCoefficient();
				TermImp term = new TermImp();
				term.setCoefficient(sumCoefficient);
				term.setExponent(term1.getExponent());
				finalPoly = join(finalPoly,term.getCoefficient()+"x^"+term.getExponent());
				term2 = iter2.next();
			}
		}
		if(!iter1.hasNext()&&!iter2.hasNext()) {
			if(term1.getExponent()>term2.getExponent()) {
				finalPoly = join(finalPoly, term1.getCoefficient()+"x^"+term1.getExponent());
			} else if(term1.getCoefficient()<term2.getExponent()) {
				finalPoly = join(finalPoly, term2.getCoefficient()+"x^"+term2.getExponent());
			}else {
				double sumCoefficient = term1.getCoefficient()+term2.getCoefficient();
				TermImp term = new TermImp();
				term.setCoefficient(sumCoefficient);
				term.setExponent(term1.getExponent());
				finalPoly = join(finalPoly,term.getCoefficient()+"x^"+term.getExponent());
			}
		}
		for(int i=0;i<finalPoly.length();i++) {
			if(finalPoly.charAt(i)=='0') {
//				finalPoly.charAt(i+1)=='x'&&
//				if(finalPoly.charAt(i-1)!='.') {
////					String temp = finalPoly.substring(i-1, finalPoly.indexOf(operator));
//					System.out.println(temp);
////					finalPoly=finalPoly.substring(0, i)+finalPoly.substring(finalPoly.indexOf('+'));
//				}
				if(finalPoly.charAt(i-1)=='.') {
					finalPoly=finalPoly.substring(0, i-1)+finalPoly.substring(i+1);
				}
				if(finalPoly.charAt(i-1)=='^') {
					finalPoly=finalPoly.substring(0, i-2)+finalPoly.substring(i+1);
				}
			}
		}
		Polynomial PolyToReturn = new PolynomialImp(finalPoly);
		return PolyToReturn;
	}
	private static String join(String poly1, String poly2) {
		String joined ="";
		if(poly1.length()==0) joined = poly2;
		else joined = poly1+"+"+poly2;
		return joined;
	}

	@Override
	public Polynomial subtract(Polynomial P2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial multiply(Polynomial P2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial multiply(double c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial indefiniteIntegral() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double definiteIntegral(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int degree() {
		int degree=0;
		Term current = new TermImp();
		Iterator<Term> iter = this.iterator();
		current = iter.next();
		degree = current.getExponent();
		while(iter.hasNext()) {
			current = iter.next();
			if(degree<current.getExponent()) degree = current.getExponent();
		}
		if(degree<current.getExponent()) degree = current.getExponent();
		return degree;
	}

	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Polynomial P) {
		if(this.getPolyList().size()!=((PolynomialImp)P).getPolyList().size()) return false;
		for(int i=0;i<this.getPolyList().size();i++) {
			if(this.getPolyList().get(i).equals(((PolynomialImp)P).getPolyList().get(i))) {}
			else return false;
		}
		return true;
	}
	
	private int numOfTerms(String poly){
		if(poly.isEmpty()) return 0;
		else {
			int counter =0;
			for(int i =0;i<poly.length();i++) {
				if(poly.charAt(i)==operator) counter++;
			}
			return counter+1;
		}
	}
	
	private TermImp[] PolyStringToPolyList(int numOfTerms, String poly, TermImp[] arr, int place) {
		TermImp terms = new TermImp();
		String termInString;
		int BoT = 0;
		int EoT = 0;
		String coefficient="";
		String exponent="";
		if(poly.contains("x")) poly=poly+"+";
		if(numOfTerms==0) return arr;
		if(poly.contains("x")) {
				EoT = poly.indexOf(operator);
				termInString = poly.substring(BoT, EoT);
				if(termInString.indexOf('x')!=0) {
					for(int j=0;j<termInString.indexOf('x');j++) {
						coefficient+=termInString.charAt(j);
					}
					if(termInString.indexOf('x')+1!=termInString.length()) {
						for(int j=termInString.indexOf('x')+2;j<termInString.length();j++) {
							exponent+=termInString.charAt(j);
						}
					}
					else exponent+=1;
				}
				else {
					coefficient+=1;
					if(termInString.indexOf('x')!=termInString.length()-1) {
						for(int j=termInString.indexOf('x')+2;j<termInString.length();j++) {
							exponent+=termInString.charAt(j);
						}
					}
					else exponent+=1;
				}
				terms.setCoefficient(Double.parseDouble(coefficient));
				terms.setExponent(Integer.parseInt(exponent));
		}
		else {
			terms.setCoefficient(Double.parseDouble(poly));
			terms.setExponent(0);
		}
		arr[place]=terms;
		place++;
		if(numOfTerms==1) return arr;
		return this.PolyStringToPolyList(--numOfTerms, poly.substring(EoT+1, poly.length()-1), arr, place);
	}

}
