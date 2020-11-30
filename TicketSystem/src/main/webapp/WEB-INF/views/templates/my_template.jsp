<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title> TicketSystem</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/skins/_all-skins.min.css">
  <!-- Morris chart -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/morris.js/morris.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/jvectormap/jquery-jvectormap.css">
  <!-- Date Picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
  
  <script src="${pageContext.request.contextPath}/resources/plugins/socket/sockjs-0.3.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/plugins/socket/stomp.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/plugins/socket/jquery-1.11.2.min.js"></script>
 

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <a href="${pageContext.request.contextPath}/dashboard" class="logo">
      <span class="logo-mini"><b>T</b>S</span>
      <span class="logo-lg">Ticket System PSSS</span>
    </a>
    <nav class="navbar navbar-static-top">
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          
          
          <sec:authorize access="hasAuthority('ROLE_CLIENTI')">
	          <!-- Notifications: style can be found in dropdown.less -->
	          <li class="dropdown notifications-menu">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	              <i class="fa fa-bell-o"></i>
	              <span class="label label-danger" id="number">${notifiche.size()}</span>
	            </a>
	            <ul class="dropdown-menu">
	              <li class="header">Hai <label id="number1">${notifiche.size()}</label> notifiche non lette</li>
	              <li>
	                <!-- inner menu: contains the actual data -->
	                <ul class="menu">
	                  
	                  <li class="row-notifica">
	                  </li>
	                  
	                  <c:forEach var="notifica" items="${notifiche}">
		                  	<c:choose>
							    <c:when test = "${fn:contains(notifica.content, 'chiuso')}">
							        <li><a href="#"><i class="fa fa-close text-red"></i>${notifica.content}</a></li>
							    </c:when>    
							    <c:otherwise>
							        <li><a href="#"><i class="fa fa-wrench text-yellow"></i>${notifica.content}</a></li>
							    </c:otherwise>
							</c:choose>
	                  </c:forEach>
	                    
	                </ul>
	              </li>
	            </ul>
	          </li>
         </sec:authorize>
         
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${pageContext.request.contextPath}/resources/dist/img/user.png" class="user-image" alt="User Image">
              <span class="hidden-xs">Benvenuto, ${account.username}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${pageContext.request.contextPath}/resources/dist/img/user.png" class="img-circle" alt="User Image">
                <p>
                  ${account.fullName}
                  <small>${account.email}</small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-right">
                  <a href="${pageContext.request.contextPath}/process-logout" class="btn btn-default btn-flat">Log out</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${pageContext.request.contextPath}/resources/dist/img/user.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${pageContext.request.userPrincipal.name}</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">MENU PRINCIPALE</li>
        
        <sec:authorize access="hasAuthority('ROLE_CLIENTI')">
        	<li class="treeview">
	          <a href="#">
	            <i class="fa fa-laptop"></i>
	            <span>Ticket</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	            <li><a href="${pageContext.request.contextPath}/ticket/send"><i class="fa fa-circle-o"></i> Nuovo Ticket</a></li>
	            <li><a href="${pageContext.request.contextPath}/ticket/history_cliente"><i class="fa fa-circle-o"></i> Lista Ticket</a></li>
	          </ul>
	        </li>
        </sec:authorize>
        
        <sec:authorize access="hasAuthority('ROLE_OPERATORI')">
        	<li class="treeview">
	          <a href="#">
	            <i class="fa fa-laptop"></i>
	            <span>Ticket</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	            <li><a href="${pageContext.request.contextPath}/ticket/history_aperti"><i class="fa fa-circle-o"></i> Lista ticket aperti</a></li>
	            <li><a href="${pageContext.request.contextPath}/ticket/history_operatore"><i class="fa fa-circle-o"></i> Lista ticket assegnati</a></li>
	          </ul>
	          
	        </li>
        </sec:authorize>
        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

	
	<!-- BODY -->
  <div class="content-wrapper">
  	
  	<tiles:insertAttribute name="content"></tiles:insertAttribute>
  	
  </div>

  
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.0
    </div>
    <strong>Copyright &copy; 2020 <a>PSSS Project.</a></strong> Alessandro Castaldo, Carmine Cesarano, Roberto Allocca. All rights
    reserved.
  </footer>
  
  		<script>
	
	      /** Open the web socket connection and subscribe the "/notify" channel.*/
	      function connect() {
	
	        // Create and init the SockJS object
	        var socket = new SockJS('/ws');
	        var stompClient = Stomp.over(socket);
	
	        // Subscribe the '/notify' channel
	        stompClient.connect({}, function(frame) {
	          stompClient.subscribe('/user/queue/notify', callback);
	        });
	        
	        return;
	      } 

		  callback = function(notifica) {
	            // Call the notify function when receive a notification
	            notify(JSON.parse(notifica.body).content);
	      }
	      
	      /** Display the notification message.*/
	      function notify(notifica) {
	    	var stato = notifica.search("chiuso");
	    	if (stato == -1) {
	    		var domElement = '<li><a href="#"><i class="fa fa-wrench text-yellow"></i>' + String(notifica) + '</a></li>';
	    	} else {
	    		var domElement = '<li><a href="#"><i class="fa fa-close text-red"></i>' + String(notifica) + '</a></li>';
	    	}
	        $(".row-notifica").eq(-1).after(domElement);

	        var value = parseInt(document.getElementById('number').textContent.match(/\d+$/)[0], 10);
	        value++;
	        document.getElementById('number').textContent = value;
	        document.getElementById('number1').textContent = value;
	        

	        let src = '${pageContext.request.contextPath}/resources/sound/notification.mp3';
	        let audio = new Audio(src);
	        audio.play();
	        
	        return;
	      }
	      
	      /*** Init operations.*/
	      $(document).ready(function() {
	        
	        // Start the web socket connection.
	        connect();
	        
	      });
	
	    </script>


</div>
<!-- ./wrapper -->


<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath}/resources/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="${pageContext.request.contextPath}/resources/bower_components/raphael/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/morris.js/morris.min.js"></script>
<!-- Sparkline -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${pageContext.request.contextPath}/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="${pageContext.request.contextPath}/resources/bower_components/moment/min/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/resources/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/resources/dist/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${pageContext.request.contextPath}/resources/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath}/resources/dist/js/demo.js"></script>

</body>
</html>
