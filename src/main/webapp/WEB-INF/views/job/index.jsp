<%@page import="com.myclass.common.UrlConstant"%>
<%@page import="java.util.List"%>
<%@ page import="com.myclass.dto.JobDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath();%>
<% List<JobDto> jobs = (List<JobDto>) request.getAttribute("jobs");%>

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
                    <h4 class="page-title">Danh sách công việc</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                    <a href="<%= contextPath + UrlConstant.URL_JOB_ADD %>" class="btn btn-sm btn-success">Thêm
                        mới</a>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <div class="table-responsive">
                            <table class="table" id="example">
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên Dự Án</th>
                                    <th>Ngày Bắt Đầu</th>
                                    <th>Ngày Kết Thúc</th>
                                    <th>Hành Động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% int i = 1; %>
                                <% for (JobDto job:jobs) {%>
                                <tr>
                                    <td><%= i %></td>
                                    <td><%= job.getName() %></td>
                                    <td><%= job.getStartDate() %></td>
                                    <td><%= job.getEndDate() %></td>
                                    <td>
                                        <a href="<%= contextPath + UrlConstant.URL_JOB_EDIT + "?id=" + job.getId() %>" class="btn btn-sm btn-primary">Sửa</a>
                                        <a href="<%= contextPath + UrlConstant.URL_JOB_DELETE + "?id=" + job.getId() %>" class="btn btn-sm btn-danger">Xóa</a>
                                        <a href="<%= contextPath + UrlConstant.URL_JOB_INFO + "?id=" + job.getId() %>" class="btn btn-sm btn-info">Xem</a></td>
                                </tr>
                                <% i++;} %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com
        </footer>
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