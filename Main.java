import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine())
        {
            try {
                System.out.println(calc(scan.nextLine()));
            } catch (Exception e) {
                System.out.println("throws Exception");
                break;
            }
        }
        scan.close();
    }
    public static String calc(String input) throws IOException {
        String [] rom = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        String [] romM = {"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};
        int a=0;
        int b=0;
        boolean indicator = false;
        int pab=0;
        String[] ab = null;
        int p = input.indexOf("+");
        int m = input.indexOf("-");
        int mm = input.indexOf("*");
        int d = input.indexOf("/");
        if (p!=-1)/*1. Проверить +  */
        {
            ab = input.split("\\+");
        }
        else if (m!=-1)/*1. Проверить - */
        {
            ab = input.split("-");
        }
        else if (mm!=-1)/*1. Проверить * */
        {
            ab = input.split("\\*");
        }
        else if (d!=-1)/*1. Проверить / */
        {
            ab = input.split("/");
        }

        for (int i = 0; i<rom.length; i++) //1.1. Проверить является ли элементы римскими через сравнение
        {
            if(ab[0].trim().equals(rom[i]))
            {
                a=i+1;
            }

            if(ab[1].trim().equals(rom[i]))
            {
                b=i+1;
            }
        }
        if (a==0 || b==0) // парсим арабские элементы массива если они не были изменены
        {
            a = Integer.parseInt(ab[0].trim());
            b = Integer.parseInt(ab[1].trim());
            indicator =true;
        }
        if (a<=10 && b<=10 && a>=1 && b>=1) //1.3. Арифмитическая магия которую нужно вернуть (a(1)b)
        {

            if (p!=-1)
            {
                pab = a + b;
            }
            else if (m!=-1)
            {
                pab = a - b;
            }
            else if (mm!=-1)
            {
                pab = a * b;
            }
            else if (d!=-1)
            {
                pab = a / b;
            }

        } // операции
        else
        {
            throw new IOException();
        }

        if(indicator==false) //если фолс, то нужно сконвертировать в римские
        {
            int reconvert = pab / 10;
            if(reconvert < 1)//если число делится на 10 и полученное число меньше 1, то это число от 1 до 9
            {
                int i = pab -1;
                input = rom[i];
            }
            if(reconvert >= 1)//Если число делится на 10 и полученное число больше 1, то это число от 10 до 100
            {
               int pervoe = (pab / 10)-1;
               int vtoroe = (pab % 10)-1;
               if(vtoroe<=0)
               {
                   input = romM[pervoe];
               }
               else
               {
                   input = romM[pervoe] + rom[vtoroe];
               }
            }
        }
        else
        {
            input = Integer.toString(pab);
        }

        return input;
    }
}


