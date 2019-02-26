package edu.uprm.ece.icom4035.polynomial;

public class TermImp implements Term{
	
	double coefficient;
	int exponent;
	
	public TermImp() {
		coefficient=0.0;
		exponent=0;
	}
	
	@Override
	public double getCoefficient() {
		return this.coefficient;
	}

	@Override
	public int getExponent() {
		return this.exponent;
	}

	@Override
	public double evaluate(double x) {
		return Math.pow(x, this.getExponent())*this.getCoefficient();
	}
	
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

}
