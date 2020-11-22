<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${err_ticket != null}">
	<div class="pad margin">
		<div class="alert alert-danger alert-dismissible" style="margin-bottom: 0!important;">
	        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	        <h4><i class="icon fa fa-ban"></i> Errore!</h4>
	        ${err} 
	    </div>  
	</div>
</c:if>

<section class="content-header">
    <h1>Lista Ticket</h1>
    <ol class="breadcrumb">
      <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Home</a></li>
      <li class="active">Lista Ticket</li>
    </ol>
</section>

<section class="content">
  <div class="row">
    <div class="col-xs-12">
      <div class="box">
        <div class="box-body">
        	
          <c:if test="${err != null}">
          	<div class="callout callout-danger">
               	<h4>Eliminazione fallita!</h4>
               	<p>Esiste già una categoria con il nome scelto. Scegliere un altro nome per proseguire.</p>
           	</div>
          </c:if>
        
          <table id="example2" class="table table-bordered table-hover">
            <thead>
            <tr>
              <th>Id</th>
              <th>Titolo</th>
              <th>Creazione</th>
              <th>Stato</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            	<c:forEach var="ticket" items="${tickets}">
          			<tr>
	                	<td>${ticket.id}</td>
	                	<td>${ticket.title}</td>
	                	<td><fmt:formatDate var="created" value="${ticket.createdDate}" pattern="dd/MM/yyyy"/>${created}</td>
	                	<c:if test="${err != null}">
		          			<div class="callout callout-danger">
			                	<h4>Errore!</h4>
			                	<p>Presa in carico del ticket non riuscita!.</p>
			            	</div>
			            </c:if>
			            
			            <c:choose>
							  <c:when test="${ticket.statoTicket.id == 1}">
							    	<td><span class="label label-success">Aperto</span></td>
							  </c:when>
							  <c:when test="${ticket.statoTicket.id == 2}">
							    	<td><span class="label label-warning">In lavorazione</span></td>
							  </c:when>
							  <c:when test="${ticket.statoTicket.id == 3}">
							    	<td><span class="label label-danger">Chiuso</span></td>
							  </c:when>
						</c:choose>
						
	                	<td>
	                		<a href="${pageContext.request.contextPath}/ticket/details/${ticket.id}">Dettagli</a>
	                	</td>
	              	</tr>
          		</c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</section>
    
    
<!-- page script -->
<script>
  $(function () {
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>