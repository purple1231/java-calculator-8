package calculator;

import java.util.ArrayList;

public class StringCaculator {

    public static int add(String inp){

        //빈 값이 들어오면 0 리턴

        if(inp == null || inp.equals("")){
            return 0;
        }


        ArrayList<String> delimiters = new ArrayList<>();
        delimiters.add(",");
        delimiters.add(":");

//        System.out.println("[DEBUG] 원본 입력: " + inp);
//
//        if (inp.contains("\\n")) {
//            System.out.println("[DEBUG] 문자열 안에 '\\n' 이 포함되어 있습니다 ");
//        } else {
//            System.out.println("[DEBUG] 문자열 안에 '\\n' 이 포함되어 있지 않습니다 ");
//        }


        if(inp.startsWith("//")){

            inp = inp.replace("\\n", "\n");


            int nextLine = inp.indexOf("\n");
            if (nextLine == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다. (//구분자\\n숫자...)");
            }

            String customDelimiter = inp.substring(2,nextLine);
            delimiters.add(customDelimiter);

            inp = inp.substring(nextLine+1);

        }else{
            throw new IllegalArgumentException("커스텀 구분자 형식이 잘못되었습니다. (//구분자\\n숫자...)");
        }

        //지금까지 잘라온 문자열 조각들 저장
        ArrayList<String> tokens = new ArrayList<>();
        tokens.add(inp);


        for(String delimiter : delimiters){
            tokens = splitTokens(tokens, delimiter);
        }


        int sum = 0;
        for(String token : tokens){

            token = token.trim(); // 공백 제거

            if (token.equals("")) continue; // 빈 문자열이라면 무시


            try {
                int n = Integer.parseInt(token);
                if (n < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않습니다: " + n);
                }
                sum += n;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("제대로 입력하시오: '" + token + "'");
            }

        }

    return sum;
    }


    private static ArrayList<String> splitTokens(ArrayList<String> tokens, String delimiter){
        ArrayList<String> result = new ArrayList<>();

        for(String token: tokens){
            String[] tokenParts = token.split(delimiter);
            for(String tokenPart: tokenParts){
                result.add(tokenPart);
            }
        }
        return result;
    }
}
