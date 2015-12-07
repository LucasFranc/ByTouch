package Servlets;

import conexaoBanco.GerenciarVendedor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import conexaoBanco.ConexaoMySQL;
import java.io.BufferedReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Phiin-PC
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/index.html", "/Login"})
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        GerenciarVendedor gerenciarCliente = new GerenciarVendedor();
        String login;
        String password;
        String nomeCadastro;
        String emailCadastro;
        String passwordCadastro;
        String passwordConfircacao;
        //request.getSession().setAttribute("key", request.getParameter("key"));
        switch (opcao) {
            case "login":
                Integer valor;
                login = request.getParameter("username");
                password = request.getParameter("password");

                gerenciarCliente.criarConexao();
                valor = gerenciarCliente.verificarCliente(login, password);
                gerenciarCliente.fecharConexao();
                if (valor != null) {
                    //request.getRequestDispatcher("/WEB-INF/Produtos.html").forward(request, response);
                    // request.getRequestDispatcher("ServletMonitora?opcao=login").forward(request, response);
                    request.getSession().setAttribute("codigoVendedor", valor);
                    response.sendRedirect("ServletMonitora");
                } else {
                    request.getRequestDispatcher("/WEB-INF/PaginaLogin.jsp").forward(request, response);
                }
                break;
            case "cadastro":
                nomeCadastro = request.getParameter("usernameCadastro");
                emailCadastro = request.getParameter("emailCadastro");
                passwordCadastro = request.getParameter("passwordCadastro");
                passwordConfircacao = request.getParameter("confirm-password");

                if (passwordCadastro.compareTo(passwordConfircacao) == 0) {
                    //criando conexao
                    /*produtoAdicionar.criarConexao();
                     produtoAdicionar.adicionarProduto(nomeProduto, precoProdutoModificado, imagemProduto, descricaoProduto);
                     produtoAdicionar.fecharConexao();*/
                    gerenciarCliente.criarConexao();

                    String hashed = BCrypt.hashpw(passwordCadastro, BCrypt.gensalt());

                    gerenciarCliente.adicionarCliente(nomeCadastro, emailCadastro, hashed);
                    gerenciarCliente.fecharConexao();
                    request.getRequestDispatcher("/WEB-INF/PaginaLogin.jsp").forward(request, response);
                } else {
                    String comp = "dif";
                    request.getRequestDispatcher("/WEB-INF/PaginaLogin.jsp").forward(request, response);
                }
                break;
        }
        /* String login, senha;
         login = request.getParameter("username");
         senha = request.getParameter("password");
         request.getRequestDispatcher("/WEB-INF/Produtos.html").forward(request, response);
         ConexaoMySQL.getConexaoMySQL();
         ConexaoMySQL.statusConection();*/

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/PaginaLogin.jsp").forward(request, response);
    }
}
