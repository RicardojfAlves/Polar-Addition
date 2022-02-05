
package soma.polar;
import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.sqrt;
import java.util.Scanner;

/**
 *
 * @author Ricardo
 */
public class SomaPolar {

    /**
     * @param args the command line arguments
     */
    
    public static double[] converterPRect(double[] polar){ //covnerte para retangular (angulo tem de estar em graus)
        double[] separado = new double[2]; 
        separado[0] = polar[0] * Math.cos(Math.toRadians(polar[1]));
        separado[1] = polar[0] * Math.sin(Math.toRadians(polar[1]));
        
        return separado;
    }
    public static int quadrante(double[] rect){ //determina o quadrante de duas posiçoes, os eixos estao a ser considerados parte de quadrantes, exeto (0,0)
        int quad = 0;                           //mas pode mudar caso seja um problema no futuro
        
        if (rect[0]>0 && rect[1]>= 0)
            quad = 1;
        else if (rect[0] <= 0 && rect[1]>0)
            quad = 2;
        else if (rect[0] < 0 && rect[1]<=0)
            quad = 3;
        else if (rect[0] >= 0 && rect[1]<0)
            quad = 4;
        
        return quad;
    }
    public static double[] converterPPolar(double[] rect){
        double[] numPolar = new double[2];
        numPolar[0] = sqrt(rect[0]*rect[0] + rect[1]*rect[1]);
        numPolar[1] = Math.toDegrees(atan(rect[1]/rect[0]));
        
        int quad = quadrante(rect);
        if (quad == 2 || quad == 3)
            numPolar[1] += 180;
        else if (quad == 4)
            numPolar[1] = 360 - numPolar[1];
        return numPolar;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        double[][]polares = new double[n][2];
        for(int i=0; i<n; i++){ //leitura de numeros polares
            System.out.println("Numero " + (i+1));
            polares[i][0] = sc.nextDouble();
            polares[i][1] = sc.nextDouble();
        }
        double[][] ret = new double[n][2];
        for(int i=0; i<n; i++){
            ret[i] = converterPRect(polares[i]);
        }
        double[] soma = new double[2];
        soma[0] = 0; soma[1] = 0;
        for(int i=0; i<n; i++){
           soma[0] += ret[i][0];
           soma[1] += ret[i][1];
       }
        double[] somaPolar = converterPPolar(soma);
        
        System.out.println(somaPolar[0] + " " + somaPolar[1] + "º");
    }
    
}
