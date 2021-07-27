package datapad;

public class IdentifyDate {
    
    int month, day;
    
    protected IdentifyDate(String date){
        String[] arrayDate = date.split(" ");
        identifyMonth(arrayDate);
        identifyDay(arrayDate);
    }

    protected void identifyMonth(String[] str){
        for(int i=0; i<str.length-1; i++){
            switch(str[i]){
                case "january":
                    month = 1;
                    break;
                
                case "february":
                    month = 2;
                    break;
                
                case "march":
                    month = 3;
                    break;
                
                case "april":
                    month = 4;
                    break;
                
                case "may":
                    month = 5;
                    break;
                
                case "june":
                    month = 6;
                    break;
                
                case "july":
                    month = 7;
                    break;
                
                case "august":
                    month = 8;
                    break;
                
                case "september":
                    month = 9;
                    break;
                
                case "october":
                    month = 10;
                    break;
                
                case "november":
                    month = 11;
                    break;
                
                case "december":
                    month = 12;
                    break;
            }
        }
    }
    
    protected void identifyDay(String[] str){
        for(int i=0; i<str.length; i++){
            switch(str[i]){
                case "one":
                    day += 1;
                    continue;
                
                case "two":
                    day += 2;
                    continue;
                
                case "three":
                    day += 3;
                    continue;
                
                case "four":
                    day += 4;
                    continue;
                
                case "five":
                    day += 5;
                    continue;
                
                case "six":
                    day += 6;
                    continue;
                
                case "seven":
                    day += 7;
                    continue;
                
                case "eight":
                    day += 8;
                    continue;
                
                case "nine":
                    day += 9;
                    continue;
                
                case "ten":
                    day += 10;
                    continue;
                
                case "eleven":
                    day += 11;
                    continue;
                
                case "twelve":
                    day += 12;
                    continue;
                
                case "thirteen":
                    day += 13;
                    continue;
                
                case "fourteen":
                    day += 14;
                    continue;
                
                case "fifteen":
                    day += 15;
                    continue;
                
                case "sixteen":
                    day += 16;
                    continue;
                
                case "seventeen":
                    day += 17;
                    continue;
                
                case "eighteen":
                    day += 18;
                    continue;
                
                case "nineteen":
                    day += 19;
                    continue;
                
                case "twenty":
                    day += 20;
                    continue;
                
                case "thirty":
                    day += 30;
                    continue;
            }
        }
    }
}
