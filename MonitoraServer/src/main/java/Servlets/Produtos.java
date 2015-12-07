package Servlets;

import com.google.gson.Gson;
import conexaoBanco.GerenciarProduto;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Produtos", urlPatterns = {"/Produtos"})
public class Produtos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GerenciarProduto gerenciarProduto = new GerenciarProduto();
        ArrayList produtos = null;
        String produtosString="";
        try {
            gerenciarProduto.criarConexao();
            Integer vendedor = (Integer)request.getSession().getAttribute("codigoVendedor");
            produtos = gerenciarProduto.getProdutos(vendedor);
            //produtosString = gerenciarProduto.teste();

            Gson gson = new Gson();
            //out.println("{\"Messages\":"+messages+"}");
            produtosString = gson.toJson(produtos);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().write(produtosString);
        //out.println(produtosString);
        gerenciarProduto.fecharConexao();
    }

}
