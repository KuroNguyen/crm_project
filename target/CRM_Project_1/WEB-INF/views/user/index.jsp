
<%@page import="java.util.List"%>
<%@ page import="com.myclass.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
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
    <link href="<%=contextPath%>/resources/css/colors/blue-dark.css"
          id="theme" rel="stylesheet">
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/custom.css">
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
                    <h4 class="page-title">Danh sách thành viên</h4>
                </div>
                <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                    <a href="<%= contextPath %>/user/add" class="btn btn-sm btn-success">Thêm
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
                                    <th>Họ và Tên</th>
                                    <th>Email</th>
                                    <th>Avatar</th>
                                    <th>Role</th>
                                    <th>#</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <% List<UserDto> users = (List<UserDto>) request.getAttribute("users"); %>
                                    <% int count = 1; %>
                                    <% for (UserDto user:users) { %>
                                    <tr>
                                        <td><%=count%></td>
                                        <td><%=user.getFullName()%></td>
                                        <td><%=user.getEmail()%></td>
                                        <td><%=user.getAvatar() != null ? user.getAvatar() : ""%></td>
                                        <td><%=user.getRoleDescription()%></td>
                                        <td><a
                                                href="<%=contextPath%>/user/edit?id=<%=user.getId()%>"
                                                class="btn btn-sm btn-primary">Sửa</a> <a
                                                href="<%=contextPath%>/user/delete?id=<%=user.getId()%>"
                                                class="btn btn-sm btn-danger">Xóa</a> <a
                                                href="<%=contextPath%>/user/info?id=<%=user.getId()%>"
                                                class="btn btn-sm btn-info">Xem</a></td>
                                    </tr>
                                    <% count++;} %>
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
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
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
<script src="<%=contextPath%>/resources/js/jquery.dataTables.js"></script>
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