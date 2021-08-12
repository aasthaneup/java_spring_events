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
<title>Edit Event</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container editcont">
	<form action="/events/${event.id}" method="post">
			<input type="hidden" name="_method" value="delete"> <a
				href="/events" class="float-right mt-3 mr-4 btn btn-primary">Home</a>
			<button class="btn btn-danger float-right mt-3 mr-3">Delete</button>
		</form>
		<div class="row bg-secondary">
			<h3 class="mb-3">
				<c:out value="${event.name}" />
			</h3>
			</div>
			<div class="editbody col-sm-7 bg-dark text-light">
			<h5 class="text-warning">Edit Event</h5>
			<p class="text-danger err">
				<form:errors path="event.*" />
			</p>
			<form:form class="form-group col" method="post" action="/events/${event.id}/edit"
				modelAttribute="event">
				<input type="hidden" name="_method" value="put">
				<div class="row mb-2">
					<form:label class="col-sm-6 col-form-label" path="name">Name:</form:label>
					<form:input class="col-sm-6" type="text" path="name" />
				</div>
				<div class="row mb-2">
					<form:label class="col-sm-6 col-form-label" path="date">Date:</form:label>
					<form:input class="col-sm-6" type="date" path="date" />
					<p class="col-sm-6"></p>
					<small class="text-warning col-sm-6">The event date is <c:out value="${event.date}"></c:out></small>
				</div>
				<div class="row mb-2">
					<form:label class="col-sm-6 col-form-label" path="location">Location:</form:label>
					<form:input class="col-sm-2.5 mr-3" type="text" path="location" />
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
				<form:input type="hidden" path="host" value="${logged_user.id}"/>
				<div class="row">
					<input class="btn btn-warning butnn mt-3 mb-3 ml-5" type="submit"
						value="Edit" />
				</div>
		</form:form>
			</div>
	</div>
</body>
</html>