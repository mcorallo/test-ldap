<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Maven + Spring MVC + @JavaConfig</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Spring 3 MVC Project @JavaConfig</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li>
				<a href="<c:url value="/logout" />">Logout</a>
			</li>
			<li>
				<select id="locale" class="form-control">
					<option value="it_IT">Italian</option>
					<option value="en_US">English (US)</option>
				</select>
			</li>
		</ul>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h1>${title}</h1>
		<p>
			<c:if test="${not empty name}">
			Hello ${name}
		</c:if>

			<c:if test="${empty name}">
			Welcome Welcome!
		</c:if>
		</p>
		<p>
			<a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
		</p>
	</div>
</div>

<div class="container">

	<div class="row">
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>

		<sec:authorize access="hasRole('ADMIN')">
			<div class="col-md-4">
				<h2>Heading</h2>
				<p>ABC</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details</a>
				</p>
			</div>
		</sec:authorize>



	</div>


	<hr>
	<footer>
		<p>${labels.get('username')}</p>
	</footer>
</div>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/core/js/jquery-1.12.0.min.js" var="jquery" />
<spring:url value="/locale" var="locale" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jquery}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script>
	$(function() {
		var currentLocale = '${session_lang}';
		if (currentLocale) {
			$('#locale option[value=' + currentLocale + ']').prop('selected', true);
		}
		$('#locale').change(function() {
			var args = {};
			args['lang'] = $(this).val();
			args['${_csrf.parameterName}'] = '${_csrf.token}';

			$.post('${locale}', args).done(function() {
				location.reload();
			});
		});
	});
</script>

</body>
</html>