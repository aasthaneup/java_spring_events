<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Events</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<div class="text-danger err">
			<h4>
				<c:out value="${accessError}" />
			</h4>
		</div>
		<div class="cont">
			<div class="reg">
				<h1 class="text-center">Register</h1>
				<p class="text-danger err">
					<form:errors path="user.*" />
				</p>
				<form:form class="form-group col" method="POST"
					action="/registration" modelAttribute="user">
					<div class="row mb-2">
						<form:label class="col-sm-6 col-form-label" path="firstName">First Name:</form:label>
						<form:input class="col-sm-6" type="text" path="firstName" />
					</div>
					<div class="row mb-2">
						<form:label class="col-sm-6 col-form-label" path="lastName">Last Name:</form:label>
						<form:input class="col-sm-6" type="text" path="lastName" />
					</div>
					<div class="row mb-2">
						<form:label class="col-sm-6 col-form-label" path="email">Email:</form:label>
						<form:input class="col-sm-6" type="email" path="email" />
					</div>
					<div class="row mb-2">
						<form:label class="col-sm-6 col-form-label" path="location">Location:</form:label>
						<form:input class="col-sm-2.5 mr-2" type="text" path="location" />
						<form:label path="state"></form:label>
						<form:select class="col-sm-1.5" path="state">
							<form:option value="AL">AL</form:option>
							<form:option value="AK">AK</form:option>
							<form:option value="AZ">AZ</form:option>
							<form:option value="AR">AR</form:option>
							<form:option value="CA">CA</form:option>
							<form:option value="CO">CO</form:option>
							<form:option value="CT">CT</form:option>
							<form:option value="DE">DE</form:option>
							<form:option value="DC">DC</form:option>
							<form:option value="FL">FL</form:option>
							<form:option value="GA">GA</form:option>
							<form:option value="HI">HI</form:option>
							<form:option value="ID">ID</form:option>
							<form:option value="IL">IL</form:option>
							<form:option value="IN">IN</form:option>
							<form:option value="IA">IA</form:option>
							<form:option value="KS">KS</form:option>
							<form:option value="KY">KY</form:option>
							<form:option value="LA">LA</form:option>
							<form:option value="ME">ME</form:option>
							<form:option value="MD">MD</form:option>
							<form:option value="MA">MA</form:option>
							<form:option value="MI">MI</form:option>
							<form:option value="MN">MN</form:option>
							<form:option value="MS">MS</form:option>
							<form:option value="MO">MO</form:option>
							<form:option value="MT">MT</form:option>
							<form:option value="NE">NE</form:option>
							<form:option value="NV">NV</form:option>
							<form:option value="NH">NH</form:option>
							<form:option value="NJ">NJ</form:option>
							<form:option value="NM">NM</form:option>
							<form:option value="NY">NY</form:option>
							<form:option value="NC">NC</form:option>
							<form:option value="ND">ND</form:option>
							<form:option value="OH">OH</form:option>
							<form:option value="OK">OK</form:option>
							<form:option value="OR">OR</form:option>
							<form:option value="PA">PA</form:option>
							<form:option value="RI">RI</form:option>
							<form:option value="SC">SC</form:option>
							<form:option value="SD">SD</form:option>
							<form:option value="TN">TN</form:option>
							<form:option value="TX">TX</form:option>
							<form:option value="UT">UT</form:option>
							<form:option value="VT">VT</form:option>
							<form:option value="VA">VA</form:option>
							<form:option value="WA">WA</form:option>
							<form:option value="WV">WV</form:option>
							<form:option value="WI">WI</form:option>
							<form:option value="WY">WY</form:option>
						</form:select>
					</div>
					<div class="row mb-2">
						<form:label class="col-sm-6 col-form-label" path="password">Password:</form:label>
						<form:password class="col-sm-6" path="password" />
					</div>
					<div class="row mb-2">
						<form:label class="col-sm-6 col-form-label"
							path="passwordConfirmation">Password Confirmation:</form:label>
						<form:password class="col-sm-6" path="passwordConfirmation" />
					</div>
					<div class="row">
						<input class="btn btn-warning butnn mt-3" type="submit"
							value="Register!" />
					</div>
				</form:form>
			</div>
			<div class="line"></div>
			<div class="log">
				<h1 class="text-center">Login</h1>
				<p class="text-danger err">
					<c:out value="${error}" />
				</p>
				<form class="form-group col" method="post" action="/login">
					<div class="row mb-2">
						<label class="col-sm-6 col-form-label" for="email">Email</label> <input
							class="col-sm-6" type="text" id="email" name="email" />
					</div>
					<div class="row mb-2">
						<label class="col-sm-6 col-form-label" for="password">Password</label>
						<input class="col-sm-6" type="password" id="password"
							name="password" /> <input class="btn btn-warning butnn mt-5"
							type="submit" value="Login!" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>