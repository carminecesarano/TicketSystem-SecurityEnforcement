<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="content-header">
    <h1>Lista Utenti</h1>
    <ol class="breadcrumb">
      <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Home</a></li>
      <li class="active">Lista Utenti</li>
    </ol>
</section>

<section class="content">
  <div class="row">
    <div class="col-xs-12">
      <div class="box">
        <div class="box-body">
        	        
          
          <table id="example2" class="table table-bordered table-hover">
            <thead>
            <tr>
              <th>Username</th>
              <th>Full Name</th>
              <th>Email</th>
              <th>Phone</th>
              <th>Stato</th>
              <th>Ultimo Login</th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            	<c:forEach var="user" items="${users}">
          			<tr>
	                	<td>${user.username}</td>
	                	<td>${user.fullName}</td>	
	                	<td>${user.email}</td>	
	                	<td>${user.phone}</td>
	                	<c:choose>
							  <c:when test="${user.status == 1}">
							    	<td><span class="label label-success">Abilitato</span></td>
							  </c:when>
							  <c:when test="${user.status == 0}">
							    	<td><span class="label label-warning">Revocato</span></td>
							  </c:when>
						</c:choose>	
						<td><fmt:formatDate var="lastLogin" value="${user.lastLogin}" pattern="dd/MM/yyyy"/>${lastLogin}</td>					
	                	<td>
	                		<a href="${pageContext.request.contextPath}/user/profile/${user.username}">Dettagli</a>
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