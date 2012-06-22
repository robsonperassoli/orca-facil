package br.com.orcafacil.mobile.util;

import java.text.DecimalFormat;

public class ConverterUtils {
	public String toString(double number) {
		return new DecimalFormat("###,###,##0.00").format(number);
	}
}
