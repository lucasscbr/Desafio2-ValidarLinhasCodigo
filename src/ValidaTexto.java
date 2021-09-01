import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class ValidaTexto {

    private static void write(final String s) {
        try(FileWriter fw = new FileWriter("out.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print(s);
            //more code
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo de dados");
        }
    }

    private static Stack pilha = new Stack();
    private static ArrayList<TextoValidado> listaValidada  = new ArrayList<TextoValidado>();

    public static void analisarParenteses(ArrayList<String> lista) throws IOException {
        for (String linha: lista
             ) {
                    String valido = "OK";
            for(int i = 0; i<linha.length();i++){
                if(!inserePilha(linha.charAt(i))){
                    pilha.clear();
                    valido = "Inválido";
                    break;
                }

            }
            if(!pilha.empty())
                valido = "Inválido";

            TextoValidado text = new TextoValidado();
            text.testo = linha;
            text.validacao = valido;
            listaValidada.add(text);
        }
                salvarDados(listaValidada);
    }

    private static boolean inserePilha(char token){
        if("{[(<".indexOf(token) != -1){
            pilha.push(token);
            return true;
        }
        else{
            if(pilha.empty()){
                return false;
            }
            if(!verParentesesFecha(token))
                return false;
        }

        return true;
    }
    private static boolean verParentesesFecha(char token){
        switch (token) {
            case ')':
                //char teste = pilha.pop().toString().charAt(0);
                if(pilha.pop().toString().charAt(0) != '('){
                    return false;
                }
                break;
            case ']':
                if(pilha.pop().toString().charAt(0) != '['){
                    return false;
                }
                break;
            case '}':
                if(pilha.pop().toString().charAt(0) != '{'){
                    return false;
                }
                break;
            case '>':
                if(pilha.pop().toString().charAt(0) != '<'){
                    return false;
                }
                break;
        }

        return  true;
    }

    private static void salvarDados(ArrayList<TextoValidado> lista) throws IOException {


        FileWriter arq = new FileWriter("prog-check.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        arq.write("");
        for (TextoValidado texto: lista
             ) {
            System.out.println(texto.testo + " - " + texto.validacao);
            gravarArq.println(texto.testo + " - " + texto.validacao);
        }

        arq.close();

        listaValidada.clear();

    }
}
