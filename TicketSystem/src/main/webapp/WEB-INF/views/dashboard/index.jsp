<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<div class="content-wrapper" style="margin-left: 0px;">

    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>Version 1.0</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      
      
      <sec:authorize access="hasAuthority('ROLE_CLIENTI')">
      		<div class="row">
		        
		        <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div class="small-box bg-green">
		            <div class="inner">
		              <h3>${size_cliente}</h3>
		              <p>Ticket aperti</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-stats-bars"></i>
		            </div>
		            <a href="${pageContext.request.contextPath}/ticket/history_cliente" class="small-box-footer">Lista <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        
	      </div>
      </sec:authorize>
      
      <sec:authorize access="hasAuthority('ROLE_OPERATORI')">
   		<div class="row">
	        
	        <!-- ./col -->
	        <div class="col-lg-3 col-xs-6">
	          <!-- small box -->
	          <div class="small-box bg-green">
	            <div class="inner">
	              <h3>${size_aperti}</h3>
	              <p>Ticket aperti</p>
	            </div>
	            <div class="icon">
	              <i class="ion ion-stats-bars"></i>
	            </div>
	            <a href="${pageContext.request.contextPath}/ticket/history_aperti" class="small-box-footer">Lista <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
	        
	        <!-- ./col -->
	        <div class="col-lg-3 col-xs-6">
	          <!-- small box -->
	          <div class="small-box bg-yellow">
	            <div class="inner">
	              <h3>${size_operatore}</h3>
	              <p>Ticket presi in carico</p>
	            </div>
	            <div class="icon">
	              <i class="ion ion-pie-graph"></i>
	            </div>
	            <a href="${pageContext.request.contextPath}/ticket/history_operatore" class="small-box-footer">Lista <i class="fa fa-arrow-circle-right"></i></a>
	          </div>
	        </div>
	      </div>
      </sec:authorize>
      
      
    </section>
  </div>