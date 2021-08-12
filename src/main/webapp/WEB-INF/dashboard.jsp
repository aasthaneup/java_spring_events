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
<title>Events</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container dashcont">
		<div class="eventheader">
			<a href="/logout" class="btn btn-secondary float-right">Logout</a>
			<h1 class="text-warning mt-4">
				Welcome,
				<c:out value="${logged_user.firstName}"></c:out>
				!
			</h1>
		</div>
		<div class="eventBody mt-4">
			<div class="instate mt-4 mb-4">
				<h5 class="text-warning">Here are some of the events in your
					state:</h5>
				<table class="table table-striped table-secondary text-dark">
					<thead class="table-dark">
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Date</th>
							<th scope="col">Location</th>
							<th scope="col">Host</th>
							<th scope="col">Action / Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${eventsInState}" var="inevent">
							<tr>
								<td scope="row"><a href="/events/${inevent.id}">${inevent.name}</a></td>
								<td><c:out value="${inevent.date}" /></td>
								<td><c:out value="${inevent.location}" /></td>
								<td><c:out value="${inevent.host.firstName}" /></td>
								<td><c:set var="logged" value="${logged_user.id}" /> <c:set
										var="here" value="${inevent.host.id}" /> 
										<c:if
										test="${logged==here}">
										<form action="/events/${inevent.id}" method="post">
											<a class="btn btn-primary" href="/events/${inevent.id}/edit">Edit</a>
											<input type="hidden" name="_method" value="delete">
											<button class="btn btn-danger">Delete</button>
										</form>
									</c:if> 
									<c:if test="${logged!=here}">
										<c:set var="attendeelist" value="${inevent.attendees}" />
										<c:if test="${attendeelist.contains(logged_user)}">
											<form action="/events/${inevent.id}/cancel" method="post">
												<input type="hidden" name="attendeeId"
													value="${logged_user.id}"> 
													<input type="hidden" name="eventId" value="${inevent.id}"> 
													Joining |    
													<input class="btn btn-warning" type="submit" value="Cancel">
											</form>
										</c:if>
										<c:if test="${!attendeelist.contains(logged_user)}">
											<form action="/events/${inevent.id}/join" method="post">
												<input type="hidden" name="attendeeId"
													value="${logged_user.id}"> <input type="hidden"
													name="eventId" value="${inevent.id}"> <input
													class="btn btn-warning" type="submit" value="Join">
											</form>
										</c:if>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="outstate mt-4 mb-4">
				<h5 class="text-warning">Here are some of the events in other
					states:</h5>
				<table class="table table-striped table-secondary text-dark">
					<thead class="table-dark">
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Date</th>
							<th scope="col">Location</th>
							<th scope="col">State</th>
							<th scope="col">Host</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${eventsOutState}" var="outevent">
							<tr>
								<td scope="row"><a href="/events/${outevent.id}">${outevent.name}</a></td>
								<td><c:out value="${outevent.date}" /></td>
								<td><c:out value="${outevent.location}" /></td>
								<td><c:out value="${outevent.state}" /></td>
								<td><c:out value="${outevent.host.firstName}" /></td>
								<td><c:set var="logged" value="${logged_user.id}" /> <c:set
										var="this" value="${outevent.host.id}" /> 
									<c:if test="${logged==this}">
										<form action="/events/${outevent.id}" method="post">
											<a class="btn btn-primary" href="/events/${outevent.id}/edit">Edit</a>
											<input type="hidden" name="_method" value="delete">
											<button class="btn btn-danger">Delete</button>
										</form>
									</c:if> 
									<c:if test="${logged!=this}">
										<c:set var="attendeelist" value="${outevent.attendees}" />
										<c:if test="${attendeelist.contains(logged_user)}">
											<form action="/events/${outevent.id}/cancel" method="post">
												<input type="hidden" name="attendeeId"
													value="${logged_user.id}"> <input type="hidden"
													name="eventId" value="${outevent.id}"> 
													Joining |   
													<input
													class="btn btn-warning" type="submit" value="Cancel">
											</form>
										</c:if>
										<c:if test="${!attendeelist.contains(logged_user)}">
											<form action="/events/${outevent.id}/join" method="post">
												<input type="hidden" name="attendeeId"
													value="${logged_user.id}"> <input type="hidden"
													name="eventId" value="${outevent.id}"> <input
													class="btn btn-warning" type="submit" value="Join">
											</form>
										</c:if>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- FORM HERE -->
		<h5 class="text-warning">Create an event:</h5>
		<div class="neweve mt-4 mb-4 bg-secondary">
			<p class="text-danger err">
				<form:errors path="event.*" />
			</p>
			<form:form class="form-group col" method="POST" action="/events"
				modelAttribute="event">
				<div class="row mb-2">
					<form:label class="col-sm-6 col-form-label" path="name">Name:</form:label>
					<form:input class="col-sm-6" type="text" path="name" />
				</div>
				<div class="row mb-2">
					<form:label class="col-sm-6 col-form-label" path="date">Date:</form:label>
					<form:input class="col-sm-6" type="date" path="date" />
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
				<form:input type="hidden" path="host" value="${logged_user.id}" />
				<div class="row">
					<input class="btn btn-warning butnn mt-3" type="submit"
						value="Create" />
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>