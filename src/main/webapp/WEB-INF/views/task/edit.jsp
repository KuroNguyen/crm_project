<%@page import="com.myclass.common.UrlConstant"%>
<%@page import="com.myclass.entity.Status"%>
<%@page import="com.myclass.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.myclass.entity.Job"%>
<%@ page import="com.myclass.dto.TaskDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%
    TaskDto task = (TaskDto) request.getAttribute("task");
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
                    <h4 class="page-title">Cập nhật công việc</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form method="post" action="<%= contextPath + UrlConstant.URL_TASK_EDIT %>"
                              class="form-horizontal form-material">
                            <p>${ message }</p>
                            <div class="form-group">
                                <div class="col-md-12">
                                    <input type="hidden" value="<%=task.getId()%>"
                                           name="id">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Dự án</label>
                                <div class="col-md-12">
                                    <select class="form-control form-control-line" name="jobId">
                                        <%
                                            List<Job> jobs = (List<Job>) request.getAttribute("jobs");
                                        %>
                                        <%
                                            for (Job job : jobs) {
                                        %>
                                        <%
                                            String selected = task.getJobId() == job.getId() ? "selected" : "";
                                        %>
                                        <option value="<%=job.getId()%>" <%=selected%>><%=job.getName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Tên công việc</label>
                                <div class="col-md-12">
                                    <input type="text" value="<%=task.getName()%>"
                                           class="form-control form-control-line" name="name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Người thực hiện</label>
                                <div class="col-md-12">
                                    <select class="form-control form-control-line" name="userId">
                                        <%
                                            List<User> users = (List<User>) request.getAttribute("users");
                                        %>
                                        <%
                                            for (User user : users) {
                                        %>
                                        <%
                                            String selected = task.getUserId() == user.getId() ? "selected" : "";
                                        %>
                                        <option value="<%=user.getId()%>" <%=selected%>><%=user.getFullName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày bắt đầu</label>
                                <div class="col-md-12">
                                    <input type="date" value="<%=task.getStartDate()%>"
                                           class="form-control form-control-line" name="startDate">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày kết thúc</label>
                                <div class="col-md-12">
                                    <input type="date" value="<%=task.getEndDate()%>"
                                           class="form-control form-control-line" name="endDate">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Trạng thái</label>
                                <div class="col-md-12">
                                    <select class="form-control form-control-line" name="statusId">
                                        <%
                                            List<Status> statuses = (List<Status>) request.getAttribute("statuses");
                                        %>
                                        <%
                                            for (Status status : statuses) {
                                        %>
                                        <%
                                            String selected = task.getStatusId() == status.getId() ? "selected" : "";
                                        %>
                                        <option value="<%=status.getId()%>" <%=selected%>><%=status.getName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-success">Lưu lại</button>
                                    <a href="<%= contextPath + UrlConstant.URL_TASK %>" class="btn btn-primary">Quay lại</a>
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