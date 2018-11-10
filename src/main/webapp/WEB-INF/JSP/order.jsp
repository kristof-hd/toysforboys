<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<title>${order.id}</title>
</head>
<body>
<h1>${order.id}</h1>

${order.id} 
${order.orderDate} 
${order.requiredDate} 
${order.customer.name} 
${order.customer.adress.streetAndNumber}
${order.customer.adress.postalCode} 
${order.customer.adress.city} 
${order.customer.adress.state} 
${order.customer.country.name}
${order.comments} 

<%-- ${order.orderDetails.product.name} --%>

<ul>
	<c:forEach var="orderdetail" items="${order.orderDetails}">
		<li>${orderdetail.product.name} ${orderdetail.quantityOrdered}</li>
	</c:forEach>
</ul>



<!-- <ul> -->
<%-- <c:forEach var="track" items="${album.tracks}"> --%>
<%-- <li>${track.naam} ${track.tijd}</li> --%>
<%-- </c:forEach> --%>
<!-- </ul> -->

</body>
</html>