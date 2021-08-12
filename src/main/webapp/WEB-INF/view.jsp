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
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class="container bg-dark text-light">
		<a href="/events" class="float-right mt-3 mr-4 btn btn-primary">Home</a>
		<div class="row mt-3 mb-3 ml-3">
			<h3 class="mb-3">
				<c:out value="${event.name}" />
			</h3>
		</div>
		<div class="row ml-5">
			<div class="col">
				<p>
					Host:
					<c:out value="${event.host.firstName}" />
					<c:out value="${event.host.lastName}"></c:out>
				</p>
				<p>
					Date:
					<c:out value="${event.date}" />
				</p>
				<p>
					Location:
					<c:out value="${event.host.location}" />
					,
					<c:out value="${event.state}" />
				</p>
				<p>
					People who are attending this event:
					<c:out value="${count}"></c:out>
				</p>
				<table class="table table-striped table-secondary text-dark">
					<thead class="table-dark">
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Location</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${event.attendees}" var="attendee">
							<tr>
								<td scope="row"><c:out value="${attendee.firstName}" /> <c:out
										value="${attendee.lastName}" /></td>
								<td><c:out value="${attendee.location}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col ml-5">
				<h3 class="text-light">Message Wall</h3>
				<div class="msgwall bg-light col-sm-12" style="height: 100px; overflow: scroll;">
				<c:forEach items="${messages}" var="msg">
				<p class="text-dark"><span class="text-info"><c:out value="${msg.commenter.firstName}"></c:out> <c:out value="${msg.commenter.lastName}"></c:out>:</span> <c:out value="${msg.comment}"/></p>
				<p class="text-dark">_______________________</p>
				</c:forEach>
				</div>
				<form:form action="/message/${event.id}" method="post" modelAttribute="message">
				<p class="text-danger err">
				<form:errors path="message.*" />
				</p>
				<form:label  path="comment">Add Comment:</form:label>
				<div class="row mb-2">
				<form:textarea class="col-sm-10" path="comment"></form:textarea>
				</div>
				<form:input path="commenter" type="hidden" value="${logged_user.id}"/>
				<form:input path="event" type="hidden" value="${event.id}"/>
				<form:button class="btn btn-primary float-right mr-5 mb-5">Submit</form:button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
