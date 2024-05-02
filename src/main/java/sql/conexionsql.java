package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class conexionsql {
    Connection conn=null;
    String url="jdbc:postgresql://localhost/proyectoFinal";
    String usuario="postgres";
    String clave="Invocador1998";
    
    
    public void conectar(){
        try {
            Class.forName("org.postgresql.Driver");
            conn=DriverManager.getConnection(url,usuario,clave);
        } catch (Exception e) {
        }
    }
    
    public void cerrar(){
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
    public boolean verificarCredenciales(String nombreUsuario, String claveUsuario) {
        try {
            String query = "SELECT * FROM usuarios WHERE nombre = ? AND clave = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, nombreUsuario);
            pst.setString(2, claveUsuario);
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Si hay resultados, las credenciales son válidas
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al verificar credenciales" + e,"Mensaje",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
