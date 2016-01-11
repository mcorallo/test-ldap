<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Signin Template for Bootstrap</title>


<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

</head>

<body>

	<div class="container">


		<h2 class="form-signin-heading text-center">Please sign in</h2>

		<form class="form-horizontal col-sm-6 col-sm-offset-3 well" method="post">
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<input class="form-control" name="username" placeholder="Username">
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" name="password" placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Sign in</button>
				</div>
			</div>

			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>

		<c:if test="${!empty msg}">
			<div class="alert alert-warning col-sm-6 col-sm-offset-3" role="alert">${msg}</div>
		</c:if>
		<c:if test="${!empty error}">
			<div class="alert alert-danger col-sm-6 col-sm-offset-3" role="alert">${error}</div>
		</c:if>

	</div>


</body>
</html>


<script>
	
</script>