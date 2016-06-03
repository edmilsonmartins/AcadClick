package br.edu.ifgoiano.acadclick.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teste {

	public static void main(String[] args) {
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    Date dataDe = sdf.parse("2014-02-05");
		    Date dataAte = sdf.parse("2014-12-05");
		 
		    long diferencaSegundos = (dataAte.getTime() - dataDe.getTime()) / (1000);
		    long diferencaMinutos = (dataAte.getTime() - dataDe.getTime()) / (1000*60);
		    long diferencaHoras = (dataAte.getTime() - dataDe.getTime()) / (1000*60*60);
		    long diferencaDias = (dataAte.getTime() - dataDe.getTime()) / (1000*60*60*24);
		    long diferencaMeses = (dataAte.getTime() - dataDe.getTime()) / (1000*60*60*24) / 30;
		    long diferencaAnos = ((dataAte.getTime() - dataDe.getTime()) / (1000*60*60*24) / 30) / 12;
		 
		    System.out.println(String.format("Diferença em Segundos:  "+ diferencaSegundos));
		    System.out.println(String.format("Diferença em Minutos: "+ diferencaMinutos));
		    System.out.println(String.format("Diferença em Horas: "+ diferencaHoras));
		    System.out.println(String.format("Diferença em Dias: "+ diferencaDias));
		    System.out.println(String.format("Diferença em Meses: "+ diferencaMeses));
		    System.out.println(String.format("Diferença em Anos: "+ diferencaAnos));
		} catch (ParseException e) {
		    e.printStackTrace();
		}

	}

}
