<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
	<title>Toys for boys</title>
	<link rel='icon' href='images/toysforboys.ico' type='image/x-icon'>
	<meta name='viewport' content='width=device-width,initial-scale=1'>
	<link rel='stylesheet' href='<c:url value="/css/toysforboys.css"/>'> 
</head>
<body>
	<h1>Unshipped orders</h1>
	
	<c:forEach items='${unshippableOrders}' var='unshippableOrder' varStatus='status'>
		<p class="error">Shipping failed for order ${unshippableOrder}, not enough stock.</p> 
	</c:forEach> 

	<c:url value='/' var='url'/>
	<form:form action='${url}' method='post'>
		<input type='submit' value='Set as shipped'><br><br>
		<table class='zebra'>
			<thead>
				<tr>
					<th>ID</th>
					<th>Ordered</th>
					<th>Required</th>
					<th>Customer</th>
					<th>Comments</th>																						
					<th>Status</th>
					<th>Ship</th>									
				</tr>
			</thead>
			<tbody>
				<c:forEach items='${orders}' var='order'>
						<spring:url var='url' value='/orders/{id}'>
							<spring:param name='id' value='${order.id}'/>
						</spring:url>
						<tr>
							<td><a href='${url}'>${order.id}</a></td> 
							<td><spring:eval expression='order.orderDate'/></td>
							<td><spring:eval expression='order.requiredDate'/></td>
							<td>${order.customer.name}</td>																
							<td>${order.comments}</td>
							<td><img src='images/${order.status}.png' alt='{order.status}'> ${order.statusAsString}</td>
							<td>
								<input type='checkbox' name='shipid' value='${order.id}'>						
							</td>
						</tr>
				</c:forEach>

			</tbody>
		</table>
	</form:form>

</body>
</html>