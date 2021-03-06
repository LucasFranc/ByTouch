<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Grupo Monitora</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <link href='http://fonts.googleapis.com/css?family=Open+Sans|Maven+Pro:500' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/scriptMenu.js"></script>
        <script type="text/javascript" src="js/ValidarLogin.js"></script>
        <link rel="stylesheet" type="text/css" href="css/styleMenu.css">
        <link data-require="bootstrap@*" data-semver="3.3.5" rel="stylesheet" href="http://bootswatch.com/bower_components/bootstrap/dist/css/bootstrap.min.css" />
    </head>
    <body>
        <header>

            <div><img class="img-circle ri" src="img/touch1.jpg" alt=""></div>
            <div class="titulo text-center">
                <h1>Buytouch</h1>
                <h3>
                    Quer vender seus produtos de maneira fácil e rápida ??? Sem burocracias, e sem
                    complicações ByTouch oferece a você, um sistema de gerenciamento de vendas simples e
                    agradável, onde você pode acessar pedidos e feedbacks a qualquer hora e em qualquer lugar,
                    anuncie e venda muito mais, em minutos seu produto vai estar em nosso aplicativo visualizado por
                    muitos clientes, comprove com ByTouch, você vende mais.
                </h3>
                <!--a href="http://www.robsawyer.me/blog/2013/09/17/scroll-indicator/">robsawyer.me</a-->
            </div>

        </header>

        <section class="main">
            <a class="arrow-wrap " href="#content" style="outline:none">
                <span class="glyphicon glyphicon-menu-up" style="margin-bottom:50px"></span>
            </a>
            <div class="container" style="margin-top:45px" id="content">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="panel panel-login" style="border-color:#A8A8A8">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <a href="" class="active" id="login-form-link">Entrar</a>
                                    </div>
                                    <div class="col-xs-6">
                                        <a href="" id="register-form-link">Cadastrar</a>
                                    </div>
                                </div>
                                <hr>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <form id="login-form" name="reg" action="Login" method="post" style="display: block;">
                                            <input type="hidden" name="opcao" value="login"> 
                                            <img class="profile-img box-shadow" src="img/userAvatar.png" alt="">
                                            <div class="form-group">
                                                <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Email" value="">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Senha">
                                            </div>
                                            <!--div class="form-group text-center">
                                                <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                                <label for="remember"> Remember Me</label>
                                            </div-->
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-sm-offset-3">
                                                        <button name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-info">Entrar</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--div class="form-group">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="text-center">
                                                            <a href="" tabindex="5" class="forgot-password">Esqueceu a senha?</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div-->
                                        </form>
                                        <form id="register-form" action="Login" method="post" role="form" style="display: none;">
                                            <input type="hidden" name="opcao" value="cadastro"> 
                                            <div class="form-group">
                                                <input type="text" name="usernameCadastro" id="username" tabindex="1" class="form-control" placeholder="Nome" value="" required >
                                            </div>
                                            <div class="form-group">
                                                <input type="email" name="emailCadastro" id="email" tabindex="1" class="form-control" placeholder="Email" value="" required>
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="passwordCadastro" id="password" tabindex="2" class="form-control" placeholder="Senha" required>
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirmação de senha" required>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-sm-offset-3">
                                                        <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-success" value="Cadastrar">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
