<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<title>Toys for boys - Order ${order.id}</title>
	<link rel='icon' href='images/toysforboys.ico' type='image/x-icon'>
	<meta name='viewport' content='width=device-width,initial-scale=1'>
	<link rel='stylesheet' href='<c:url value="/css/toysforboys.css"/>'> 
</head>
<body>
<h1>Order ${order.id}</h1>
<dl>
	<dt>Ordered:</dt>
	<dd>${order.formattedOrderDate}</dd> 
	<dt>Required:</dt>
	<dd>${order.formattedRequiredDate}</dd> 
	<dt>Customer:</dt>
	<dd>${order.customer.name} <br> 
		${order.customer.adress.streetAndNumber} <br>
		${order.customer.adress.postalCode} ${order.customer.adress.city} ${order.customer.adress.state} <br> 
		${order.customer.country.name}</dd>
	<dt>Comments:</dt>
	<dd>${order.comments}</dd> 
	<dt>Details:</dt>
			<table class='details'>
				<thead>
					<tr>
						<th>Product</th>
						<th>Price each</th>
						<th>Quantity</th>
						<th>Value</th>
						<th>Deliverable</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items='${order.orderDetails}' var='orderdetail'>
							<tr>
								<td>${orderdetail.product.name}</td> 
								<td><spring:eval expression='orderdetail.priceEach'/></td>
								<td>${orderdetail.quantityOrdered}</td>
								<td><spring:eval expression='orderdetail.formattedValue.value'/></td>
								<td>
										<c:choose>
											<c:when test='${orderdetail.deliverable}'>&check;</c:when>
											<c:otherwise>&cross;</c:otherwise>
										</c:choose>
								</td>
							</tr>
					</c:forEach>
					</tbody>
			</table>
</dl>
<%-- Total value: ${order.totalValue} --%>
Total value: <spring:eval expression = 'totalValue.value'/> 

</body>
</html>