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
	<c:if test='${numberOfUnshippableOrders>0}'>
		<span class='error'>
			Shipping failed for order(s) 
			<c:forEach items='${unshippableOrders}' var='unshippableOrder' varStatus='status'>
				<c:if test='${numberOfUnshippableOrders==1}'>${unshippableOrder}</c:if>
				<c:if test='${numberOfUnshippableOrders==2}'>
					<c:if test='${status.first}'>${unshippableOrder}</c:if>
					<c:if test='${status.last}'>aand ${unshippableOrder}</c:if>
				</c:if>
				<c:if test='${numberOfUnshippableOrders>2}'>
					<c:choose>
						<c:when test='${status.first}'>${unshippableOrder}</c:when>
						<c:when test='${status.last}'>and ${unshippableOrder}</c:when>
						<c:otherwise>, ${unshippableOrder}</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
			, not enough stock.
		</span>
	</c:if>	
	
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
							<td>${order.formattedOrderDateWithHyphen}</td>
							<td>${order.formattedRequiredDateWithHyphen}</td>
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