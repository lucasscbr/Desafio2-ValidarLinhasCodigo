import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
   static ArrayList<String> lista  = new ArrayList<String>();
    public static void main(String[] args) throws Exception {
        FileWriter arq = new FileWriter("prog-check.txt");
        arq.write("");

       int resposta;
        do{
            exibeMenu();
            resposta = lerReposta();
            switch (resposta) {
                case 1:
                    readTxt(true);
                    break;
                case 2:
                    readTxt(false);
                    break;
                case 3:
                    System.out.println("Saindo.... ");
                    break;
            }
        }while(resposta != 3);
    }

    public static void readTxt(boolean soLer)
    {
        String path = "prog.txt";
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            String linha = "";
            while (true) {
                if (linha != null) {

                } else
                    break;

                linha = buffRead.readLine();
                if( linha != null && linha != "")
                    lista.add(linha);


            }
            buffRead.close();

            if(soLer) {
                ValidaTexto.analisarParenteses(lista);
                lista.clear();
            }
            else {
                AdicionarTexto();
                lista.clear();
            }


        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            System.out.println("Arquivo de texto não encontrado");
            e1.printStackTrace();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Erro de leitura");
            e.printStackTrace();
        }


    }

    static int lerReposta(){
        Scanner sc = new Scanner(System.in);
        int resposta;

        do{
            System.out.println("Escolha uma opção: ");
            resposta = tryParseInt(sc.nextLine().trim(), -1);
        }while (resposta == -1);

        System.out.println();
        return resposta;
    }

    static int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }

    }

    static void exibeMenu()
    {
        System.out.println("1- Ler Arquivo");
        System.out.println("2- Escrever no Arquivo");
        System.out.println("3- Sair");
        System.out.println();
    }

    static void AdicionarTexto() throws IOException {
        boolean valido;
        String texto;
        do {
            Scanner sc = new Scanner(System.in);
            valido = true;
            System.out.println("Digite o texto (Apenas ()-{}-[]-<> ): ");
            texto = sc.nextLine().trim();

            for(int i = 0;i<texto.length();i++){

                if ("abcdefghijklmnopqrstuvwxyz".indexOf(texto.toLowerCase(Locale.ROOT).charAt(i)) != -1)
                    valido = false;

                if ("0123456789".indexOf(texto.charAt(i)) != -1)
                    valido = false;
            }

        }while (!valido);

        lista.add(texto);

        FileWriter arqProg = new FileWriter("prog.txt");
        PrintWriter gravarArqProg = new PrintWriter(arqProg);
        arqProg.write("");

        for (String linha: lista
        ) {
            gravarArqProg.println(linha);
        }

        arqProg.close();

        ValidaTexto.analisarParenteses(lista);
    }


}
