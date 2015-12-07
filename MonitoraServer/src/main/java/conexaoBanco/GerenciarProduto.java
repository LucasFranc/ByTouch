/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaoBanco;

import classes.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Phiin-PC
 */
public class GerenciarProduto {

    Connection con;

    public void criarConexao() {
        con = ConexaoMySQL.getConexaoMySQL();
    }

    public String statusConexao() {
        String status = ConexaoMySQL.statusConection();
        return status;
    }

    public void adicionarProduto(String nome, double preco, String imagem, String descricao, Integer codigoVendedor) {
        if (con != null) {
            //INSERT INTO tbl_name (col1,col2) VALUES(15,col1*2);
            String sql = "insert into produtos(nome, preco, imagem,descricao,vendedor) values (" + arrumarString(nome) + "," + preco + "," + arrumarString(imagem) + "," + arrumarString(descricao) + "," + codigoVendedor + ")";
            try {
                PreparedStatement valores = con.prepareStatement(sql);
                valores.execute();
                //ResultSet result = (ResultSet) valores.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList getProdutos(Integer vendedor) {
        ArrayList produtos = new ArrayList();
        if (con != null) {
            try {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM produtos where vendedor = "+vendedor);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setCodigo(rs.getInt("codigo"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setImagem(rs.getString("imagem"));
                    produto.setDescricao(rs.getString("descricao"));
                    produtos.add(produto);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return produtos;
    }

    public void atualizarProduto(int id, String nome, double preco, String imagem, String descricao,Integer codigoVendedor) {
        if (con != null) {
            //INSERT INTO tbl_name (col1,col2) VALUES(15,col1*2);
            String sql = "UPDATE produtos SET nome = "+arrumarString(nome)+", preco = "+preco+", imagem = "+arrumarString(imagem)+", descricao = "+ arrumarString(descricao)+" where codigo = "+id+" and vendedor = "+codigoVendedor;
            try {
                PreparedStatement valores = con.prepareStatement(sql);
                valores.execute();
                //ResultSet result = (ResultSet) valores.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void deletarProduto(int id){
        if (con != null) {
            //INSERT INTO tbl_name (col1,col2) VALUES(15,col1*2);
            String sql =  "delete from produtos where codigo = "+id;
            try {
                PreparedStatement valores = con.prepareStatement(sql);
                valores.execute();
                //ResultSet result = (ResultSet) valores.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void fecharConexao() {
        ConexaoMySQL.FecharConexao();
    }

    public String arrumarString(String arrumar) {
        String texto = "'" + arrumar + "'";
        return texto;
    }

}
