/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.template.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author cicco
 */
public class Utils {
    public static final int CPF_DIGITS_DATA_COUNT = 9;
    public static final int CPF_DIGITS_VALIDATE_COUNT = 2;
    public static final int CPF_ALL_DIGITS_COUNT = CPF_DIGITS_DATA_COUNT + CPF_DIGITS_VALIDATE_COUNT;

    public static int comparaDatasSemHoraEMilissegundo(Date date1, Date date2) {

        date1 = retornaDataSemHoraEMilissegundo(date1);
        date2 = retornaDataSemHoraEMilissegundo(date2);

        if (date1.before(date2)) {
            return -1;
        }
        else if (date1.after(date2)) {
            return 1;
        }
        else {
            return 0;
        }

    }

    public static Date retornaDataSemHoraEMilissegundo(Date date) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("pt", "BR"));

        String sDate = df.format(date);

        try {
            date = df.parse(sDate);
        }
        catch (ParseException pe) {
            //O string ser a utilizado no parse é o que foi retornado do próprio DateFormat
        }
        
        return date;
    }

    /**
     * Adiciona as horas em um Date e depois retorna os imcrementos em dias (sem horas ou milisegundos
     * @param date
     * @param horas
     * @return
     */
    public static Date getDataAposHoras(Date date, int horas) {

        Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));

        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, horas);

        return retornaDataSemHoraEMilissegundo(calendar.getTime());

    }

    public static boolean isDataUltrapassada(Date date){
        return Utils.comparaDatasSemHoraEMilissegundo(new Date(), date) == 1;
    }

    /**
     * Retorna o nome do mês.
     * @param intMes. Este valor deve ser entre 0 e 11
     * @return
     */
    public static String getMesComecandoEmZero(int intMes) {
        if (intMes < 0 || intMes > 11) {
            throw new IllegalArgumentException("O valor passado deve estar entre 0 e 11 inclusive.");
        }
        String mes = "";
        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("pt", "BR"));
        String[] meses = dfs.getMonths();
        mes = meses[intMes];
        return mes;
    }

    /**
     * Retorna um date com a hora e segundo final de um dia de acordo com as normas do programa de monitoria.
     * @param data
     * @return
     */
    public static Date getLimiteDataNoMesmoDia(Date data) {

        if (data == null) {
            throw new IllegalArgumentException("A data não pode ser nula.");
        }

        Calendar calendar = Calendar.getInstance(new Locale("pt", "BR"));
        calendar.setTime(data);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        data = calendar.getTime();

        return data;
    }

    /**
     * Método responsável por calcular o número de horas
     * entre as duas datas 'dataInicio' e 'dataFim'.
     *
     * Caso
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public static long getNumeroHorasEntreDatas(Date dataInicio, Date dataFim) {

        if (comparaDatasSemHoraEMilissegundo(dataInicio, dataFim) > 0) {
            return 0L;
        }

        Calendar dataInicial = new GregorianCalendar();
        Calendar dataFinal = new GregorianCalendar();

        dataInicial.setTime(dataInicio);
        dataFinal.setTime(dataFim);

        long numeroHoras = 0;

        if (dataInicial.get(Calendar.MONTH) == dataFinal.get(Calendar.MONTH)
            && dataInicial.get(Calendar.DATE) == dataFinal.get(Calendar.DATE)
            && dataInicial.get(Calendar.YEAR) == dataFinal.get(Calendar.YEAR)
            && dataInicial.get(Calendar.HOUR) == dataFinal.get(Calendar.HOUR)){

            return numeroHoras;
        }


        while (dataInicial.before(dataFinal)) {
            dataInicial.add(Calendar.HOUR, 1);
            numeroHoras++;
        }

        return numeroHoras;
    }

    /**
     * Calcula o numero de dias entre duas datas. 
     * A dataInicio deve ser menor que a dataFim
     * @param dataInicio
     * @param dataFim
     * @return Numero de dias entre as duas datas.
     */
    public static long getNumeroDiasEntreDatas(Date dataInicio, Date dataFim) {

        if (comparaDatasSemHoraEMilissegundo(dataInicio, dataFim) >= 0) {
            return 0L;
        }

        Calendar dataInicial = new GregorianCalendar();
        Calendar dataFinal = new GregorianCalendar();

        dataInicial.setTime(dataInicio);
        dataFinal.setTime(dataFim);

        long numeroDias = 0;

        while (dataInicial.before(dataFinal)) {
            dataInicial.add(Calendar.DAY_OF_MONTH, 1);
            numeroDias++;
        }
        return numeroDias;
    }

    public static String removeCarriageReturnDaString(String textoOriginal) {
        if (textoOriginal != null) {
            return textoOriginal.replaceAll("\\r", "");
        }
        return null;
    }

    /**
     * Verifica se o 'mesSelecionado' e anterior
     * ao mês atual.
     * Caso seja, retorna true.
     * Caso contrário, retorna false.
     *
     * @param mesSelecionado
     * @return
     */
    public static boolean isMesUltrapassado(Integer mesSelecionado) {
        
        return Utils.comparaMesComMesAtual(mesSelecionado) == -1;
    }

    /**
     * Verifica se o 'mesSelecionado' e posterior
     * ao mês atual.
     * Caso seja, retorna true.
     * Caso contrário, retorna false.
     *
     * @param mesSelecionado
     * @return
     */
    public static boolean isMesFuturo(Integer mesSelecionado) {

        return Utils.comparaMesComMesAtual(mesSelecionado) == 1;
    }

    /**
     * Verifica se o 'mesSelecionado' é igual
     * ao mês atual.
     * Caso seja, retorna true.
     * Caso contrário, retorna false.
     *
     * @param mesSelecionado
     * @return
     */
    public static boolean isMesAtual(Integer mesSelecionado) {

        return Utils.comparaMesComMesAtual(mesSelecionado) == 0;
    }

    /**
     *
     * Método responsável por comparar o 'mesSelecionado'
     * com o mes da data atual.
     *
     * Se o 'mesSelecionado' seja menor que o mes atual, retorna -1;
     * Senão, se o 'mesSelecionado' seja maior que o mes atual, retorna 1;
     * Senão, o 'mesSelecionado' é igual que o mes atual, retorna 0.
     *
     * @return -1, Se o 'mesSelecionado' for menor que o mes atual
     *          1, Se o 'mesSelecionado' for maior que o mes atual
     *          0, Se o 'mesSelecionado' for igua que o mes atual
     */
    private static int comparaMesComMesAtual(Integer mesSelecionado) {

        Calendar atual = new GregorianCalendar();
        atual.setTime(new Date());

        if (mesSelecionado - 1 < atual.get(Calendar.MONTH)) {
            return -1;
        } else if (mesSelecionado -1 > atual.get(Calendar.MONTH)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Método responsável por verificar se a
     * 'lista' informada é nula ou vazia.

     * @param lista
     * @return true, caso a lista seja nula ou vazia
     *         false, caso contrário
     */
    public static boolean listaNulaOuVazia(List lista) {
        return lista == null || (lista != null && lista.isEmpty());
    }

    /**
     * Método responsável por retornar uma instância de
     * Date que representa o PRIMEIRO dia do mês, com base
     * no 'mes' e no 'ano' informados.
     * 
     * @param mes
     * @param ano
     * @return Se o 'mes' é 1 (Janeiro) e o 'ano' é 2012,
     *         retorna uma instância de Date representando 
     *         a data e hora 1 de Janeiro de 2012 às 00:00:00.
     */
    public static Date getPrimeiroDiaDoMesNoAno(int mes, int ano) {

        Calendar inicioMes = Calendar.getInstance(new Locale("pt", "BR"));
        inicioMes.set(ano, mes - 1, 1, 0, 0, 0);

        return inicioMes.getTime();
    }

    /**
     * Método responsável por retornar uma instância de
     * Date que representa o ÚLTIMO dia do mês, com base
     * no 'mes' e no 'ano' informados.
     *
     * @param mes
     * @param ano
     * @return Se o 'mes' é 2 (Janeiro) e o 'ano' é 2012,
     *         retorna uma instância de Date representando
     *         a data e hora 31 de Janeiro de 2012 às 23:59:59.
     */
    public static Date getUltimoDiaDoMesNoAno(int mes, int ano) {

        Calendar fimMes = Calendar.getInstance(new Locale("pt", "BR"));
        fimMes.set(ano, mes - 1, fimMes.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);

        return fimMes.getTime();
    }
}