package conexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;

public class GerenciarVendedor {

    Connection con;

    public void criarConexao() {
        con = ConexaoMySQL.getConexaoMySQL();
    }

    public String statusConexao() {
        String status = ConexaoMySQL.statusConection();
        return status;
    }

    public void adicionarCliente(String nome, String email, String senha) {
        if (con != null) {
            //INSERT INTO tbl_name (col1,col2) VALUES(15,col1*2);
            String sql = "insert into vendedor(nome, email, senha) values (" + arrumarString(nome) + "," + arrumarString(email) + "," + arrumarString(senha) + ")";
            try {
                PreparedStatement valores = con.prepareStatement(sql);
                valores.execute();
                //ResultSet result = (ResultSet) valores.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer verificarCliente(String email, String senha) {
        Integer code = 0;
        if (con != null) {
            //INSERT INTO tbl_name (col1,col2) VALUES(15,col1*2);
            //select email,senha from vendedor where email = 'bruno.lucas14@hotmail.com' and senha='122'
            String sql = "select senha, codigo from vendedor where email = " + arrumarString(email);
            try {
                Statement valores = con.createStatement();
                ResultSet rs = valores.executeQuery(sql);
                //ResultSet result = (ResultSet) valores.executeQuery();
                if (rs.next()) {
                    if (BCrypt.checkpw(senha, rs.getString("senha"))) {
                        code = rs.getInt("codigo");
                    } else {
                        code = null;
                    }
                    rs.close();
                } else {
                    code = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return code;
    }

    public void fecharConexao() {
        ConexaoMySQL.FecharConexao();
    }

    public String arrumarString(String arrumar) {
        String texto = "'" + arrumar + "'";
        return texto;
    }

}
