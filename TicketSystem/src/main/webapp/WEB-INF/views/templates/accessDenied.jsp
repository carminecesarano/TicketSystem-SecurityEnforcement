<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    

    <!-- Main content -->
    <section class="content">

      <div class="error-page">
        <h2 class="headline text-red">403</h2>

        <div class="error-content">
          <h3><i class="fa fa-warning text-red"></i> Oops! Accesso negato.</h3>
          <p>
            L'accesso alla risorsa non è autorizzato.
            <a href="${pageContext.request.contextPath}/dashboard">Torna alla dashboard</a> 
          </p>
        </div>
      </div>
      <!-- /.error-page -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->