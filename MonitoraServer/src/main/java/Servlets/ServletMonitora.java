/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import conexaoBanco.GerenciarProduto;
import conexaoBanco.ConexaoMySQL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Phiin-PC
 */
@WebServlet(name = "ServletMonitora", urlPatterns = {"/ServletMonitora"})
public class ServletMonitora extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Produtos.html").forward(request, response);
        //response.sendRedirect("Login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        StringBuffer sb = new StringBuffer();
        JSONParser parser = new JSONParser();
        JSONObject produto = null;
        GerenciarProduto gerenciarProduto = new GerenciarProduto();

        switch (opcao) {
            case "cadastrar":
                try {
                    BufferedReader reader = request.getReader();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    produto = (JSONObject) parser.parse(sb.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    String nomeProduto = (String) produto.get("name");
                    String descricaoProduto = (String) produto.get("description");
                    String imagemProduto = (String) produto.get("image");
                    Long l = new Long((long) produto.get("price"));
                    double precoProdutoModificado = l.doubleValue();
                    Integer codigoVendedor = (Integer)request.getSession().getAttribute("codigoVendedor");
                    //criando conexao
                    gerenciarProduto.criarConexao();
                    gerenciarProduto.adicionarProduto(nomeProduto, precoProdutoModificado, imagemProduto, descricaoProduto, codigoVendedor);
                    gerenciarProduto.fecharConexao();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "atualizar":
                try {
                    BufferedReader reader = request.getReader();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    produto = (JSONObject) parser.parse(sb.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    Long longID = new Long((long) produto.get("id"));
                    int id = longID.intValue();
                    String nomeProduto = (String) produto.get("name");
                    String descricaoProduto = (String) produto.get("description");
                    String imagemProduto = (String) produto.get("image");
                    Long l = new Long((long) produto.get("price"));
                    double precoProdutoModificado = l.doubleValue();

                    Integer codigoVendedor = (Integer)request.getSession().getAttribute("codigoVendedor");
                    //criando conexao
                    gerenciarProduto.criarConexao();
                    gerenciarProduto.atualizarProduto(id, nomeProduto, precoProdutoModificado, imagemProduto, descricaoProduto,codigoVendedor);
                    gerenciarProduto.fecharConexao();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "deletar":
                try {
                    BufferedReader reader = request.getReader();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    produto = (JSONObject) parser.parse(sb.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    Long longID = new Long((long) produto.get("id"));
                    int id = longID.intValue();

                    //criando conexao
                    gerenciarProduto.criarConexao();
                    gerenciarProduto.deletarProduto(id);
                    gerenciarProduto.fecharConexao();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
