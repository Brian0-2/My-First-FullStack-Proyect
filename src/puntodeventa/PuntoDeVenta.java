package puntodeventa;

import Conexion.conexionSQL;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PuntoDeVenta {

    public static void main(String[] args) {
       Login login = new Login();
       login.setVisible(true);
       
       conexionSQL sql = new conexionSQL();
       sql.conexion();
        
        
    }
    public void vista(){
       try {
              //NoireLookAndFeel()
              //AeroLookAndFeel()
              UIManager.setLookAndFeel(new AeroLookAndFeel() );
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar vista " + e.getMessage());
        }
}

}
