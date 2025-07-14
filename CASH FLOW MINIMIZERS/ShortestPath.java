package main.java;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FindPath {

    static HashMap parm;
    static List printBill;

    public static void main(String[] args) throws IOException, CsvException
    {
        List<String[]> dataSet = CsvHandler.getDataFromCsv();

        for (String[] data : dataSet)
        {
            String row1 = data[0];
            String row2 = data[1];
            String row3 = data[2];

            int numFriends = Integer.parseInt(row1);
            String[] names = row2.split(" ");
            int amounts[] = new int[numFriends];
            String[] numStrings = row3.split(" ");
            for (int i = 0; i < numFriends; i++)
            {
                amounts[i] = Integer.parseInt(numStrings[i]);
            }

            parm = new HashMap();
            for (int i = 0; i < numFriends; i++)
            {
                String name = names[i];
                double amount = amounts[i];

                parm.put(name, amount);
            }
            //Passing values to findPath Method.
            findPath(parm);
            // System.out.println(printBill.toString());
            System.out.println("\n\nNEXT TESTCASE : \n");
        }
    }


    public static void findPath(HashMap details) {
        printBill = new ArrayList();
        Double Max_Value = (Double) Collections.max(details.values());
        Double Min_Value = (Double) Collections.min(details.values());
        if (Max_Value != Min_Value) {
            String Max_Key = getKeyFromValue(details, Max_Value).toString();
            String Min_Key = getKeyFromValue(details, Min_Value).toString();
            Double result = Max_Value + Min_Value;
            result = round(result, 1);
            if ((result >= 0.0)) {
                //printBill.add(Min_Key + " needs to pay " + Max_Key + ":" + round(Math.abs(Min_Value), 2));
                System.out.println(Min_Key + " needs to pay " + Max_Key + " : " + round(Math.abs(Min_Value), 2));
                details.remove(Max_Key);
                details.remove(Min_Key);
                details.put(Max_Key, result);
                details.put(Min_Key, 0.0);
            } else {
                // printBill.add(Min_Key + " needs to pay " + Max_Key + ":" + round(Math.abs(Max_Value), 2));
                System.out.println(Min_Key + " needs to pay " + Max_Key + " : " + round(Math.abs(Max_Value), 2));


                details.remove(Max_Key);
                details.remove(Min_Key);
                details.put(Max_Key, 0.0);
                details.put(Min_Key, result);
            }
            findPath(details);
        }

    }

    public static Object getKeyFromValue(HashMap hm, Double value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}