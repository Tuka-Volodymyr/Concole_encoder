package chucknorris;
import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    protected static void main(String[] args) {
        StringBuilder binary = new StringBuilder();
        menu(binary);

    }
        protected static void coder(StringBuilder binary ){
            System.out.println("Input string:");
            String value = scanner.nextLine();
            int n = value.length();
            for(int i=0;i<n;i++){
                binary.append(String.format("%7s",Integer.toBinaryString(value.charAt(i))).replace(" ","0"));
            }
            char lastNum =' ';
            String result="";
            String[] codeBinary = new String[] {" 00 0"," 0 0"};
            for(int i =0 ;i<binary.length();i++){
                if (binary.charAt(i)!=lastNum){
                    lastNum = binary.charAt(i);
                    if(binary.charAt(i)=='0'){
                        result+=codeBinary[0];
                    } else if (binary.charAt(i)=='1') {
                        result+=codeBinary[1];
                    }
                }else {
                    result+='0';
                }
            }
            System.out.println("Encoded string:");
            System.out.println(result.substring(1));
        }
    protected static void decoder(StringBuilder binary) {
        boolean end=true;
        System.out.println("Input encoded string:");
        String value = scanner.nextLine();
        int n = value.length();
        for(int i =0;i<n;i++){
            if (value.charAt(i) != '0' && value.charAt(i) != ' ') {
                end = false;
                break;
            }
        }
        int len=0;
        String result="";
        String[] binCod = value.split(" ");
        if ((binCod.length)%2!=0)end=false;
        if(end){
            for (int i = 0; i < binCod.length; i+=2) {
                if (binCod[i].equals("0")) {
                    len = binCod[i + 1].length();
                    while (len != 0) {
                        result+="1";
                        len--;
                    }
                } else if (binCod[i].equals("00")) {
                    len = binCod[i + 1].length();
                    while (len != 0) {
                        result+="0";
                        len--;
                    }
                }
            }
        }
        for(int i=0;i<result.length();i+=8){
            if(result.charAt(i)!='0'&&result.charAt(i)!='1'){
                end=false;
                break;
            }
        }
        if(result.length()%7!=0)end=false;
        if(!end){
            System.out.println("Encoded string is not valid.");
        }else {
            System.out.println();
            System.out.println("Decoded string:");
            for (String s : result.split("(?<=\\G.{7})")) {
                binary.append((char) Integer.parseInt(s, 2));
            }
            System.out.println(binary);
        }
    }

    protected static void menu(StringBuilder binary){
        String choose;
        boolean loop=true;
        while (loop){
            System.out.println();
            System.out.println("Please input operation (encode/decode/exit):");
            choose=scanner.nextLine();
            switch (choose) {
                case "encode" -> coder(binary);
                case "decode" -> decoder(binary);
                case "exit" -> {
                    System.out.println("Bye!");
                    loop = false;
                }
                default -> {
                    System.out.printf("There is no '%s' operation",choose);
                    System.out.println();
                }
            }
        }
    }
}