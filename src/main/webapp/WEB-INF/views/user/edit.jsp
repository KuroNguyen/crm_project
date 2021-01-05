<%@page import="com.myclass.dto.UserDto"%>
<%@page import="com.myclass.dto.RoleDto"%>
<%@page import="java.util.List"%>
<%@ page import="com.myclass.common.UrlConstant" %>
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
    <!-- animation CSS -->
    <link href="<%=contextPath%>/resources/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%=contextPath%>/resources/css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="<%=contextPath%>/resources/css/colors/blue-dark.css"
          id="theme" rel="stylesheet">
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
                    <h4 class="page-title">Cập Nhật Thông Tin Thành Viên</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form class="form-horizontal form-material" method="post" action="#">
                            <input hidden=true name="id" value="${ user.id }">
                            <div class="form-group">
                                <label class="col-md-12">Họ Và Tên</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="Johnathan Doe"
                                           class="form-control form-control-line" name="fullName" value="${ user.fullName }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="example-email" class="col-md-12">Email</label>
                                <div class="col-md-12">
                                    <input type="email" placeholder="johnathan@admin.com"
                                           class="form-control form-control-line" name="email"
                                           id="example-email" value="${ user.email }" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Mật Khẩu</label>
                                <div class="col-md-12">
                                    <input type="password" value=""
                                           class="form-control form-control-line" name="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ảnh Đại Diện</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="123 456 7890"
                                           class="form-control form-control-line" name="avatar" value="${ user.avatar }">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-12">Quyền</label>
                                <div class="col-sm-12">
                                    <select class="form-control form-control-line" name="roleId">
                                        <% UserDto userDto = (UserDto) request.getAttribute("user"); %>
                                        <% List<RoleDto> roles = (List<RoleDto>) request.getAttribute("roles"); %>
                                        <% String selected; %>
                                        <% for (RoleDto role:roles) { %>
                                        <% selected = (role.getId() == userDto.getRoleId()) ? "selected":""; %>
                                        <option value="<%= role.getId() %>" <%= selected %>><%= role.getDescription() %></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-success">Cập nhật</button>
                                    <a href="<%= contextPath + UrlConstant.URL_USER %>" class="btn btn-primary">Quay lại</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-2 col-12"></div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com
        </footer>
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
<script
        src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<!--Wave Effects -->
<script src="<%=contextPath%>/resources/js/waves.js"></script>
<!-- Custom Theme JavaScript -->
<script src="<%=contextPath%>/resources/js/custom.min.js"></script>
</body>
</html>