<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

	<section class="content-header">
	    <h1>Apri un nuovo Ticket</h1>
	    <ol class="breadcrumb">
	      <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Home</a></li>
	      <li class="active">Nuovo Ticket</li>
	    </ol>
	</section>
    
    
    <c:if test="${err != null}">
		<div class="pad margin">
			<div class="alert alert-danger alert-dismissible" style="margin-bottom: 0!important;">
		        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		        <h4><i class="icon fa fa-ban"></i> Errore!</h4>
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
          
            <s:form method="post" modelAttribute="ticket" enctype="multipart/form-data"
            		action="${pageContext.request.contextPath}/ticket/send" role="form">
              <div class="box-body">
                
                <div class="form-group">
                  <label for="title">Titolo</label>
                  <s:input path="title" cssClass="form-control" id="title"/>
                </div>
                
                <div class="form-group">
                  <label for="description">Descrizione</label>
                  <s:textarea path="description" cols="20" rows="5" cssClass="form-control" id="description"/>
                </div>
                      
              </div>

              <div class="box-footer" style="text-align: right">
                <sec:csrfInput /> 
                <button type="submit" class="btn btn-primary">Invia Ticket <i class="fa fa-fw fa-send"></i></button>
              </div>
            </s:form>
          </div>
 
      </div>
   	  
   	  </div>
    </section>
    