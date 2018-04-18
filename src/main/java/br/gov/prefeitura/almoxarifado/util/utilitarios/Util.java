package br.gov.prefeitura.almoxarifado.util.utilitarios;

import org.joda.time.DateTime;

public class Util {

	public static String gerarNumeroSolicitacao(int entidade_id){
		
		StringBuilder sb = new StringBuilder();
		
		DateTime dateTime = DateTime.now();
		
		sb.append(dateTime.getYear());
		sb.append(dateTime.getMonthOfYear());
		sb.append(dateTime.getDayOfMonth());
		sb.append(dateTime.getHourOfDay());
		sb.append(dateTime.getMinuteOfHour());
		sb.append(dateTime.getSecondOfMinute());
		sb.append(dateTime.getMillisOfSecond());
		sb.append(entidade_id);
		
		return sb.toString();
		
	}
}
