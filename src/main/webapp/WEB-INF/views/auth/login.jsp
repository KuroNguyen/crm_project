<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <!-- Meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container">
    <div class="row mt-5">
        <div class="col-5 m-auto">
            <div class="mx-auto">
                <h3 class="mb-5">Đăng nhập hệ thống</h3>
                <p>${ message }</p>
            </div>

            <form method="post" action="#">

                <div class="form-group">
                    <label for="email">Email address:</label> <input type="email"
                                                                     class="form-control" placeholder="Enter email" name="email" id="email">
                </div>
                <div class="form-group">
                    <label for="pwd">Password:</label> <input type="password"
                                                              class="form-control" placeholder="Enter password" name="password" id="pwd">
                </div>
                <div class="form-group form-check">
                    <label class="form-check-label"> <input
                            class="form-check-input" type="checkbox"> Remember me
                    </label>
                </div>

                <button type="submit" class="btn btn-primary mb-2">Submit</button>
            </form>
        </div>

    </div>

</div>
</body>
</html>