package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Comparator;


public class Main extends Search  {

    public static void main(String[] args) throws IOException {

        BufferedReader check = new BufferedReader(new FileReader("check1.txt"));
        String text=check.readLine();                                                        //READ FROM THE TEXT WILL BE CHECKED AND
        ArrayList<String> checklist=new ArrayList<String>(Arrays.asList(text.split("\\."))); //SPLIT BY "." FOR SEPARATE BY SENTENCES
        //for (String element:mylist){System.out.println(element);}                          //AND PUT INTO ARRAY LIST

        BufferedReader source = new BufferedReader(new FileReader("source.txt"));
        String text2=source.readLine();                                                    //READ FROM THE MAIN TEXT AND
        String[] words = text2.split(" ");                                          //SPLIT BY " " TO GET THE WORDS AND PUT INTO ARRAY
        ArrayList<String> pairslist = new ArrayList<String>();                                //AND INITIALIZE AN ARRAY LIST NAMED PAIRS

        for (int i = 0; i < words.length-1; ++i) {          //PAIR THE WORDS FROM THE 'WORDS' ARRAY WHICH INCLUDES MAIN TEXT WORDS
            pairslist.add(words[i] + " " + words[i+1]);      //PAIR THE WORDS AND PUT INTO ARRAY LIST NAMED 'PAIRSLIST'
        }

        for (String x:pairslist) {
            System.out.println(x);  //PRINTING PAIRS FOR MAIN TEXT
        }

        int count=0;      //CREATING COUNT VARIABLE FOR COUNT TO PAIRS

        LinkedHashMap<Integer, String > counter = new LinkedHashMap<>();  //CREATING LINKEDHASMAP FOR SORT TO SENTENCES BY THEIR NUMBER OF SAME PAIRS
        Map<Integer,String> unsortedMap=new HashMap<>();            //CREATING MAP FOR KEEP SENTENCES AS KEYS AND THEIR COUNT AS VALUES

        for (int i=0;i<checklist.size();i++){                                //FOR EACH OF THE SENTENCES OF TEXT WHICH WILL BE CHECKED
            for (int j=0;j<pairslist.size();j++) {                            //STARTING FROM THE FIRST PAIR UNTIL THE LAST PAIR OF MAIN TEXT
            Search shift=new Search();
            Hashtable table=shift.ShiftMyTable(pairslist.get(j),checklist.get(i));   //SEARCHING EVERY PAIR FOR EACH SENTENCES,AND CREATING SHIFT TABLE
            int match =shift.HorspoolSearch(pairslist.get(j),checklist.get(i),table); //AND USE THE TABLE FOR SEARCHING ALGORITHM

            if (match!=-1){      //IF A MATCH FOUND COUNT FOR PAIRS IS INCREASED BY ONE
                   count++;
            }
        }
            unsortedMap.put(count,checklist.get(i));  // FOR SORTING PURPOSE,PUT SENTENCES AND THEIR SAME PAIRS COUNT INTO MAP NAMED 'UNSORTEDMAP'
            count=0;              //AFTER JOBS ARE DONE FOR ONE SENTENCE WE RESET COUNT AND BACK TO FOR STATEMENT
        }
        unsortedMap.entrySet().stream().
                sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))   //IN ORDER TO SORT BY COUNT IN DESCENDING ORDER PUT INTO KEYS AND VALUES
                .forEachOrdered(x->counter.put(x.getKey(),x.getValue()));       //INTO LINKEDHASHMAP NAMED 'COUNTER'

        for (Map.Entry<Integer, String > e : counter.entrySet()) {
            System.out.println("Number of identical pairs: "+e.getKey() + " / " + "SENTENCE: " + e.getValue()+ "\n");   //PRINTING LINKEDHASHMAP
        }

        int sum=0;
        for (int x:counter.keySet()){
            sum+=x;                    //IN ORDER TO FIND SIMILARITY RATE,MATCHING PAIRS FOR ALL SENTENCES IN CHECK TEXT ARE SUMMED
        }

        int c = 0;
        Iterator<Integer> itr = counter.keySet().iterator();
        while (itr.hasNext() && c < 5) {        //IN ORDER TO PRINT FIRST 5 MOST SIMILAR SENTENCE IN MAIN TEXT CREATING AN ITERATOR AND WHILE STATEMENT
            System.out.println("MOST SIMILAR "+(c+1)+"th SENTENCE\n"+counter.get(itr.next())+"\n"); //PRINTING SENTENCES ACCORDING TO THEIR SIMILARITY
            c++;
        }
        System.out.println("Similarity rate between texts = %"+(sum*100)/pairslist.size());  //PRINTING SIMILARITY RATE FOR TEXTS
    }
}
