<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>TicketSystem | Log in</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/static/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/static/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/static/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/static/plugins/iCheck/square/blue.css">


  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>

<body class="hold-transition login-page">
	<div class="login-box">
	  <div class="login-logo">
	    <a href="#"><b>Ticket</b>System</a>
	  </div>
	  
	  	<c:if test="${msg != null}">
			<div class="pad margin">
				<div class="alert alert-danger alert-dismissible" style="margin-bottom: 0!important;">
			        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			        <h4><i class="icon fa fa-ban"></i> Errore!</h4>
			        ${msg} 
			    </div>  
			</div>
		</c:if>
		
		<c:if test="${success != null}">
			<div class="pad margin">
				<div class="alert alert-success alert-dismissible" style="margin-bottom: 0!important;">
			        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			        <h4><i class="icon fa fa-check"></i> Arrivederci !</h4>
			        ${success} 
				</div>  
			</div>
		</c:if>
	  
	  <!-- /.login-logo -->
	  <div class="login-box-body">
	    <p class="login-box-msg">Inserisci le credenziali per accedere</p>
	
	    <form action="${pageContext.request.contextPath}/login/process-login" method="post">
	      <div class="form-group has-feedback">
	        <input type="text" class="form-control" name="username" placeholder="Username">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	      </div>
	      <div class="form-group has-feedback">
	        <input type="password" class="form-control" name="password" placeholder="Password">
	        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	      </div>
	      <div class="row">
	        <div class="col-xs-8">
	          <div class="checkbox icheck">
	          </div>
	        </div>
	        <div class="col-xs-4">
	          <sec:csrfInput /> 
	          <button type="submit" class="btn btn-primary btn-block btn-flat">Entra</button>
	        </div>
	      </div>
	    </form>
		
	  </div>
	  
	</div>
	
	
	<!-- jQuery 3 -->
	<script src="${pageContext.request.contextPath}/resources/public/static/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="${pageContext.request.contextPath}/resources/public/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	
	
</body>
</html>
    