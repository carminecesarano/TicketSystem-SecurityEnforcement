<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- Content Header (Page header) -->
<section class="content-header">
  <div class="container-fluid">
    <div class="row mb-2">
      <div class="col-sm-6">
        <h1>Aggiungi Utente</h1>
      </div>
    </div>
  </div><!-- /.container-fluid -->
</section>


<c:if test="${err != null}">
	<div class="pad margin">
		<div class="alert alert-danger alert-dismissible" style="margin-bottom: 0!important;">
	        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	        <h4><i class="icon fa fa-ban"></i> Errore nell'aggiunta dell'utente!</h4>
	        ${err} 
	    </div>  
	</div>
</c:if>
   
<c:if test="${success != null}">
	<div class="pad margin">
		<div class="alert alert-success alert-dismissible" style="margin-bottom: 0!important;">
	        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	        <h4><i class="icon fa fa-check"></i> Operazione effettuata!</h4>
	        ${success} 
		</div>  
	</div>
</c:if>


<section class="content">
      <div class="row">
        
        <div class="col-md-12">
          <div class="box box-primary">
          
          	
            <s:form method="post" modelAttribute="user" enctype="multipart/form-data"
            		action="${pageContext.request.contextPath}/user/add" role="form" >
              <div class="box-body">
                
                <div class="form-group">
                  <label for="username">Username</label>
                  <s:input path="username" cssClass="form-control" id="username" required="required" minlength="3" maxlength="15"/>
                </div>
                
                <div class="form-group">
                  <label for="fullName">Full Name</label>
                  <s:input path="fullName" cssClass="form-control" id="fullName" required="required"/>
                </div>
                                
                <div class="form-group">
                  <label for="email">Email</label>
                  <s:input path="email" cssClass="form-control" id="email" required="required"/>
                </div>
                
                <div class="form-group">
                  <label for="phone">Phone</label>
                  <s:input path="phone" cssClass="form-control" id="phone" required="required"/>
                </div>
                
                <div class="form-group">
                  <label for="password">Password</label>
                  <s:input type="password" path="password" cssClass="form-control" id="password" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" title="Please include at least 1 uppercase character, 1 lowercase character, and 1 number." minlength="8" maxlength="30" required="required"/>
                </div>
                
                <div class="form-group">
                	<label for="role">Ruolo</label>
					<form:select path="role">
					    <form:option cssClass="form-control" value="clienti" label="Cliente" />
					    <form:option cssClass="form-control" value="operatori" label="Operatore" />
					</form:select>
				</div>
				
              </div>

              <div class="box-footer" style="text-align: right">
                <sec:csrfInput /> 
                <button type="submit" class="btn btn-primary">Aggiungi utente <i class="fa fa-fw fa-send"></i></button>
              </div>
            </s:form>
          </div>
 
      </div>
   	  
   	  </div>
    </section>