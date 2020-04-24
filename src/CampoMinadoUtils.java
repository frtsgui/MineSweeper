import java.util.*;
public class CampoMinadoUtils {
    public static int regiao(int[][] campo, int i, int j){
        if(i==0){
            if(j==0) return 1;
            else if(j==campo[0].length-1) return 3;
            else return 2;
        }else if(i==campo.length-1){
            if(j==0) return 7;
            else if(j==campo[0].length-1) return 9;
            else return 8;
        }else{
            if(j==0) return 4;
            else if(j==campo[0].length-1) return 6;
            else return 5;
        }
        
    }
     
    public static int[][] geraCampo(int numMinas, int numLinhas, int numColunas){
        int [][]campo= new int[numLinhas][numColunas];
        if(numMinas<=numLinhas*numColunas){
            for(int i=0;i<numMinas;i++){
                int m = (int) Math.round((numColunas-1)*Math.random());
                int n = (int) Math.round((numLinhas-1)*Math.random());
            
                if (campo[n][m]==0){ 
                    campo[n][m]= 9;
                }else i--;
            }
            
            for(int i=0;i<numLinhas;i++) for(int j=0;j<numColunas;j++) if(campo[i][j]!=9){
                int soma = 0;
                if(regiao(campo,i,j)==1)
                for(int k=0;k<2;k++)
                    for(int l=0;l<2;l++)
                        if(campo[i+k][j+l]==9) soma++;
                        
                if(regiao(campo,i,j)==3)
                for(int k=0;k<2;k++)
                    for(int l=-1;l<1;l++)
                        if(campo[i+k][j+l]==9) soma++;
                        
                if(regiao(campo,i,j)==2)
                for(int k=0;k<2;k++)
                    for(int l=-1;l<2;l++)
                        if(campo[i+k][j+l]==9) soma++;
                
                if(regiao(campo,i,j)==7)
                for(int k=-1;k<1;k++)
                    for(int l=0;l<2;l++)
                        if(campo[i+k][j+l]==9) soma++;
                        
                if(regiao(campo,i,j)==9)
                for(int k=-1;k<1;k++)
                    for(int l=-1;l<1;l++)
                        if(campo[i+k][j+l]==9) soma++;
                    
                if(regiao(campo,i,j)==8)
                for(int k=-1;k<1;k++)
                    for(int l=-1;l<2;l++)
                        if(campo[i+k][j+l]==9) soma++;
                
                if(regiao(campo,i,j)==4)
                for(int k=-1;k<2;k++)
                    for(int l=0;l<2;l++)
                        if(campo[i+k][j+l]==9) soma++;
                        
                if(regiao(campo,i,j)==6)
                for(int k=-1;k<2;k++)
                    for(int l=-1;l<1;l++)
                        if(campo[i+k][j+l]==9) soma++;
                
                if(regiao(campo,i,j)==5)
                for(int k=-1;k<2;k++)
                    for(int l=-1;l<2;l++)
                        if(campo[i+k][j+l]==9) soma++;

                campo[i][j]=soma;
            }
            return campo;
        }
        System.out.println("Entrada inválida");
        return campo;
    }
    
    public static boolean temBombaNoEntorno(int [][]campo, int linha, int coluna){
        if(campo[linha][coluna]%10==9) return true;
        return false;
    }

    public static int numBombasNoEntorno(int [][]campo, int linha, int coluna){        
        if(campo[linha][coluna]%10!=9) return campo[linha][coluna]%10;
        return -1;
    }

    public static void revela(int [][]campo, int linha, int coluna){
        if(campo[linha][coluna]==10){
            if(regiao(campo,linha,coluna)==1)
                for(int i=0;i<2;i++) for(int j=0;j<2;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                }    
            else if(regiao(campo,linha,coluna)==2)
                for(int i=0;i<2;i++) for(int j=-1;j<2;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                }
            else if(regiao(campo,linha,coluna)==3)
                for(int i=0;i<2;i++) for(int j=-1;j<1;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                }
            else if(regiao(campo,linha,coluna)==4)
                for(int i=-1;i<2;i++) for(int j=0;j<2;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                }
            else if(regiao(campo,linha,coluna)==5)
                for(int i=-1;i<2;i++) for(int j=-1;j<2;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                }
            else if(regiao(campo,linha,coluna)==6)
                for(int i=-1;i<2;i++) for(int j=-1;j<1;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                }
            else if(regiao(campo,linha,coluna)==7)
                for(int i=-1;i<1;i++) for(int j=0;j<2;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                }
            else if(regiao(campo,linha,coluna)==8)
                for(int i=-1;i<1;i++) for(int j=-1;j<2;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                    
                }
            else if(regiao(campo,linha,coluna)==9)
                for(int i=-1;i<1;i++) for(int j=-1;j<1;j++){
                    if(campo[linha+i][coluna+j]<10) campo[linha+i][coluna+j]+=10;
                }
                
        }
    }

    public static void marcou(int [][]campo, int linha, int coluna) {
        if(campo[linha][coluna]<10) campo[linha][coluna]+=20;
        else if(campo[linha][coluna]>19) campo[linha][coluna]-=20;
    }

    public static void escolheu(int [][]campo, int linha, int col) {
        if(campo[linha][col]<10) campo[linha][col]+=10;
        
        boolean checa;
        boolean [][] jaChecado=new boolean [campo.length][campo[0].length];
        
        do{      
            checa=false;
            
            for(int i=0;i<campo.length;i++){
                for(int j=0;j<campo[0].length;j++){
                    if(campo[i][j]==10 && jaChecado[i][j]==false){
                        revela(campo,i,j);
                        jaChecado[i][j]=true;
                    }
                }
            }
            
            for(int i=0;i<campo.length;i++){
                for(int j=0;j<campo[0].length;j++){
                    if(jaChecado[i][j]==false && campo[i][j]==10){
                        checa=true;
                        break;
                    }
                }
                if(checa) break;
            }
            
        }while(checa);
    }

    public static int[] escolheAuto(int [][]campo) {
        int []escolha = new int[2];
        return escolha;
    }

    public static boolean fimJogo(int [][]campo){
        int numMinas = 0;
        int numMarcacoes = 0;
        int numNaoReveladas = 0;
        
        for(int i=0;i<campo.length;i++) for(int j=0;j<campo[0].length;j++){
            if(campo[i][j]%10==9) numMinas++;
            if(campo[i][j]/10==2) numMarcacoes++;
            if(campo[i][j]<10) numNaoReveladas++;
            if(campo[i][j]==19) return true;
        }
        
        if(numMinas==numMarcacoes) return true;
        else if(numNaoReveladas+numMarcacoes==numMinas) return true;
        else return false;
    }
}