package com.company;

import java.util.Hashtable;

public class Search {

        public int count=0;
        public Hashtable ShiftMyTable(String pattern, String text)
        {
            Hashtable table=new Hashtable(); //CREATING HASHTABLE FOR STORE SHIFT TABLE
            boolean flag = false;

            for (int i = 0; i < text.length(); i++)
            {
                if (!table.containsKey(text.charAt(i))) //IF THE EXISTING VARIABLE IN THE TEXT HAS NOT ALREADY BEEN PUT IN TABLE
                {
                    String s=new String();
                    s.valueOf(text.charAt(i));
                        if (!pattern.contains(s))   //IF THE LETTER IS NOT ON THE PATTERN
                            table.put(text.charAt(i), pattern.length());   //FOR THAT LETTER WRITE PATTERN SIZE INTO SHIFT TABLE

                        else { //IF LETTER IN THE TEXT EXIST IN THE PATTERN
                            for (int j = pattern.length() - 2; j >= 0; j--) { //STARTING FROM BEFORE THE LAST LETTER UNTIL FIRST WORD
                                if (pattern.charAt(j) == text.charAt(i)) {  //IF THE LETTER ARE MATCHED
                                    flag = true;
                                    table.put(text.charAt(i), pattern.length() - j - 1); //FOR THAT LETTER STARTING FROM THE END ACCORDING TO
                                                                                         // POSITION OF THE LETTER IN PATTERN
                                                                                         // PUT SHIFT VALUE INTO TABLE
                                    break;
                                }
                            }
                            if (flag == false)
                                table.put(text.charAt(i), pattern.length());  //IF THE LETTER IN THE TEXT CORRESPONDS TO LAST LETTER IN THE PATTERN
                                                                              //PUT TO VALUE INTO SHIFT TABLE ACCORDING TO LENGTH OF THE PATTERN
                            else
                                flag = false;   //IN ORDER TO CHECK OTHER WORDS MAKE FLAG FALSE AGAIN FOR REPEATING THE PROCESS
                        }
                }
            }
            return table;
        }

    public int HorspoolSearch(String pattern, String text,Hashtable table) {

        int i = pattern.length() - 1;

        while (i <= text.length() - 1) {  //PATTERN CONTINUES AS LONG AS ITS LESS THAN EQUAL TO THE LENGTH OF THE TEXT
            int k = 0;
            while (k <= pattern.length() - 1 && pattern.charAt(pattern.length() - 1 - k) == text.charAt(i - k)) //IF THE LETTERS WHICH COMPARED ARE MATCHED
            {
                k++;   //KEY COMPARISON
            }
            if (k == pattern.length()) //PATTERN HAS BEEN FOUND
            {
                return i - pattern.length() + 1; //RETURNING THE POSITION OF THE MATCH
            } else {
                i=i+Integer.parseInt(table.get(text.charAt(i)).toString()); //IF THERE IS NO MATCH,THE INDEX SHIFTS ACCORDING TO SHIFT TABLE
            }
        }
        return -1; //IF THERE IS NO MATCH FOR PATTERN RETURN -1
    }
}
