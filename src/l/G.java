
package l;

import static java.lang.System.out;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class G {
    public static double
    decimales(double numero, int decimales) {
        BigDecimal bigDecimal = new BigDecimal(numero).setScale(decimales, RoundingMode.HALF_UP);
        
        return bigDecimal.doubleValue();
    }
}
