<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
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
	<dd>${order.orderDate}</dd> 
	<dt>Required:</dt>
	<dd>${order.requiredDate}</dd> 
	<dt>Customer:</dt>
	<dd>${order.customer.name} <br> 
		${order.customer.adress.streetAndNumber} <br>
		${order.customer.adress.postalCode} ${order.customer.adress.city} ${order.customer.adress.state} <br> 
		${order.customer.country.name}</dd>
	<dt>Comments:</dt>
	<dd>${order.comments}</dd> 
	<dt>Details:</dt>
	<ul>
		<c:forEach var="orderdetail" items="${order.orderDetails}">
			<li>${orderdetail.product.name} ${orderdetail.priceEach} ${orderdetail.quantityOrdered} ${orderdetail.value} 
							<c:choose>
								<c:when test='${orderdetail.deliverable}'>&check;</c:when>
								<c:otherwise>&cross;</c:otherwise>
							</c:choose>
			</li>
		</c:forEach>
	</ul>
</dl>
Total value: ${order.totalValue}

</body>
</html>