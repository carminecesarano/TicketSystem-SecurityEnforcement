<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags/form"%>

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
	        <h4><i class="icon fa fa-check"></i> Operazione eseguita correttamente!</h4>
	        ${success} 
		</div>  
	</div>
</c:if>

<sec:authorize access="hasRole('ROLE_CLIENTE')">
	<c:if test="${ticket.statoTicket.id == 1}">
		<div class="pad margin no-print">
			<div class="callout callout-info" style="margin-bottom: 0!important;">
			     <h4><i class="fa fa-info"></i> Note:</h4>
			     Il ticket è stato inserito correttamente. La preghiamo di attendere che la richiesta di assistenza venga presa in carico da un nostro operatore.
		 	</div>
		</div>
	</c:if>
</sec:authorize>


<!--  DETTAGLI TICKET -->    
<section class="content">

    <div class="row">
        <div class="col-md-12">
          <!-- DIRECT CHAT DANGER -->
          <div class="box box-danger direct-chat direct-chat-danger">
            <div class="box-header with-border">
              <h3 class="box-title">Dettagli Ticket</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
          <!--  <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button> -->
              </div>
            </div>
            
            
            <div class="box-body">
              
              <section class="invoice" style="margin:0">
      
					<div class="row">
					 <div class="col-xs-12">
					 
					   <h2 class="page-header">
					     	<i class="fa fa-globe"></i> ${ticket.title}
							<c:choose>
							<c:when test="${ticket.statoTicket.id == 1}">
							 	<button type="button" class="btn btn-success btn-not-clickable pull-right">
							      <i class="fa fa-fw fa-check-square"></i> 
							      Aperto
							    </button>
							</c:when>
							<c:when test="${ticket.statoTicket.id == 2}">
								<button type="button" class="btn btn-warning btn-not-clickable pull-right">
							      <i class="fa fa-fw fa-gears"></i> 
							      In lavorazione
							    </button>
							</c:when>
							<c:when test="${ticket.statoTicket.id == 3}">
								<button type="button" class="btn btn-danger btn-not-clickable pull-right">
							      <i class="fa fa-fw fa-close"></i> 
							      Chiuso
							    </button>
							</c:when>
							</c:choose>
					   </h2>
					   <strong>Descrizione</strong>
					   <br>${ticket.description}
					   <br>
					   <br>
					   <br>
					 </div>
					</div>
					
					<div class="row invoice-info">
				        <div class="col-sm-4 invoice-col">
				          <strong>Cliente:</strong>
				          <address>
				            ${ticket.cliente.fullName}
				            <br>Phone: ${ticket.cliente.phone}
				            <br>Email: ${ticket.cliente.email}
				          </address>
				        </div>
				        <div class="col-sm-4 invoice-col">
				          <strong>Operatore assistente:</strong>
				          <address>
				            ${ticket.operatore.fullName}
				            <br>Phone: ${ticket.operatore.phone}
				            <br>Email: ${ticket.operatore.email}
				          </address>
				        </div>
				        <div class="col-sm-4 invoice-col">
				          <b>Ticket ID:</b> #${ticket.id}<br>
				          <fmt:formatDate var="created" value="${ticket.createdDate}" pattern="dd/MM/yyyy"/>
				          <b>Data creazione:</b> ${created}<br>
				        </div>
				     </div>
				       
					<sec:authorize access="hasRole('ROLE_OPERATORE')">
					 <div class="row">
					   <div class="col-xs-12">
					   	 <c:choose>
							<c:when test="${ticket.statoTicket.id == 1}">
							   <a href="${pageContext.request.contextPath}/ticket/aggiorna_stato/${ticket.id}">
							        <button type="button" class="btn btn-primary pull-right">
							         <i class="fa fa-fw fa-wrench"></i> 
							         Prendi in carico ticket
							        </button>
							      </a>
							</c:when>
							<c:when test="${ticket.statoTicket.id == 2}">
							  	<a href="${pageContext.request.contextPath}/ticket/aggiorna_stato/${ticket.id}">
							        <button type="button" class="btn btn-danger pull-right">
							         <i class="fa fa-fw fa-close"></i> 
							         Chiudi ticket
							        </button>
							      </a>
							</c:when>
						</c:choose>
					   </div>
					 </div>
					</sec:authorize>
					
				</section>
            </div>
          </div>
        </div>
      </div>
     
	</section> 

    

