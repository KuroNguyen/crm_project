<%@page import="com.myclass.common.UrlConstant"%>
<%@page import="java.util.List"%>
<%@ page import="com.myclass.dto.JobDto" %>
<%@ page import="com.myclass.dto.statistic.StatusStatisticDto" %>
<%@ page import="com.myclass.dto.TaskDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath();%>
<% StatusStatisticDto statistic = (StatusStatisticDto) request.getAttribute("statistic"); %>
<% List<TaskDto> todoTasks = (List<TaskDto>) request.getAttribute("todo"); %>
<% List<TaskDto> inProgressTasks = (List<TaskDto>) request.getAttribute("inProgress"); %>
<% List<TaskDto> doneTasks = (List<TaskDto>) request.getAttribute("done"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16"
          href="<%=contextPath%>/resources/plugins/images/favicon.png">
    <title>Pixel Admin</title>
    <!-- Bootstrap Core CSS -->
    <link
            href="<%=contextPath%>/resources/bootstrap/dist/css/bootstrap.min.css"
            rel="stylesheet">
    <!-- Menu CSS -->
    <link
            href="<%=contextPath%>/resources/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
            rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <!-- animation CSS -->
    <link href="<%=contextPath%>/resources/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%=contextPath%>/resources/css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="<%=contextPath%>/resources/css/colors/blue-dark.css" id="theme"
          rel="stylesheet">
    <link rel="stylesheet" href="./css/custom.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>

<!-- #page-wrapper -->
<div id="wrapper">
    <!-- Navigation -->
    <!-- Navigation bar -->
    <jsp:include page="../layouts/navbar.jsp"></jsp:include>
    <!-- Left navbar-header -->
    <jsp:include page="../layouts/sidebar.jsp"></jsp:include>
    <!-- /Navigation -->

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Chi tiết dự án</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    <ol class="breadcrumb">
                        <li><a href="#">Dashboard</a></li>
                        <li class="active">Blank Page</li>
                    </ol>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- BEGIN THỐNG KÊ -->
            <div class="row">
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-md-6 col-sm-6 col-xs-6"> <i data-icon="E"
                                                                        class="linea-icon linea-basic"></i>
                                <h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-6">
                                <h3 class="counter text-right m-t-15 text-danger"><%=statistic.getTodoPercentage()%>%</h3>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-danger" role="progressbar"
                                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: <%=statistic.getTodoPercentage()%>%">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-md-6 col-sm-6 col-xs-6"> <i class="linea-icon linea-basic"
                                                                        data-icon="&#xe01b;"></i>
                                <h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-6">
                                <h3 class="counter text-right m-t-15 text-megna"><%=statistic.getInProgressPercentage()%>%</h3>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-megna" role="progressbar"
                                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: <%=statistic.getInProgressPercentage()%>%">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
                <!--col -->
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="white-box">
                        <div class="col-in row">
                            <div class="col-md-6 col-sm-6 col-xs-6"> <i class="linea-icon linea-basic"
                                                                        data-icon="&#xe00b;"></i>
                                <h5 class="text-muted vb">HOÀN THÀNH</h5>
                            </div>
                            <div class="col-md-6 col-sm-6 col-xs-6">
                                <h3 class="counter text-right m-t-15 text-primary"><%=statistic.getDonePercentage()%>%</h3>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="progress">
                                    <div class="progress-bar progress-bar-primary" role="progressbar"
                                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: <%=statistic.getDonePercentage()%>%">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.col -->
            </div>
            <!-- END THỐNG KÊ -->

            <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
            <div class="row">
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Chưa thực hiện</h3>
                        <div class="message-center">
                            <% for (TaskDto task:todoTasks) { %>
                            <a href="#">
                                <div class="mail-contnet">
                                    <h5><%= task.getUserName() %></h5>
                                    <span class="mail-desc"><%= task.getName() %></span>
                                    <span class="time">Bắt đầu: <%= task.getStartDate() %></span>
                                    <span class="time">Kết thúc: <%= task.getEndDate() %></span>
                                </div>
                            </a>
                            <% } %>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Đang thực hiện</h3>
                        <div class="message-center">
                            <% for (TaskDto task:inProgressTasks) { %>
                            <a href="#">
                                <div class="mail-contnet">
                                    <h5><%= task.getUserName() %></h5>
                                    <span class="mail-desc"><%= task.getName() %></span>
                                    <span class="time">Bắt đầu: <%= task.getStartDate() %></span>
                                    <span class="time">Kết thúc: <%= task.getEndDate() %></span>
                                </div>
                            </a>
                            <% } %>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="white-box">
                        <h3 class="box-title">Đã hoàn thành</h3>
                        <div class="message-center">
                            <% for (TaskDto task:doneTasks) { %>
                            <a href="#">
                                <div class="mail-contnet">
                                    <h5><%= task.getUserName() %></h5>
                                    <span class="mail-desc"><%= task.getName() %></span>
                                    <span class="time">Bắt đầu: <%= task.getStartDate() %></span>
                                    <span class="time">Kết thúc: <%= task.getEndDate() %></span>
                                </div>
                            </a>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END DANH SÁCH CÔNG VIỆC -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
    </div>
    <!-- /#page-content -->

</div>
<!-- /#page-wrapper -->

<!-- jQuery -->
<script
        src="<%=contextPath%>/resources/plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script
        src="<%=contextPath%>/resources/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Menu Plugin JavaScript -->
<script
        src="<%=contextPath%>/resources/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<!--slimscroll JavaScript -->
<script src="<%=contextPath%>/resources/js/jquery.slimscroll.js"></script>
<script
        src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<!--Wave Effects -->
<script src="<%=contextPath%>/resources/js/waves.js"></script>
<!-- Custom Theme JavaScript -->
<script src="<%=contextPath%>/resources/js/custom.min.js"></script>
<script>
    $(document).ready(function() {
        $('#example').DataTable();
    });
</script>

</body>
</html>