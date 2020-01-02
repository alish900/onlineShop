<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Comfortaa&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).on('change', '.quantity', function(){
		var field = $(this);
		var id = field.attr('id');
		var priceCell = field.parent().parent().find('td#'+id);
		var price = priceCell.attr('class');
		var dataStr = "id="+id+"&quantity="+field.val(); 
		$.ajax({
			type : "POST",
			url : "Basket",
			data : dataStr,
			success : function() {
				priceCell.text(price*field.val());
			}
		});
	});
</script>
<link rel="stylesheet" type="text/css" href="styles/styles.css">
<title>Корзина</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
    <div class='header'>
    <nav>
    	ПРИПРАВЫ.НСК | 
    	☎ +7-913-321-2322  |
        <a href="/shop/Goods">Товары</a> 
        <a href="/shop/Basket">Корзина</a> |
        <a  href="/shop/Auth">Вход</a>
     </nav>   
    </div>
    <div class='content'>
	   <div id='basket'>
		<h1>Корзина</h1>
			<table id='basket'>
			<c:set var="sum" scope="session" value="0" />
				<c:forEach var = "basket" items = "${basket}" varStatus="num">
				<c:if test="${num.count%2==0}"><tr class="even" height="40px"></c:if>
				<c:if test="${num.count%2!=0}"><tr class="odd"  height="40px"></c:if>
						<td width="10%">${basket.sku}</td><td width="55%">${basket.name}</td>
						<td width="10%" align="right">
						<input type="number" class="quantity" id="${basket.good}" min="1" max="999" value="${basket.quantity}">
						</td>
						<td width="10%" class="${basket.price}" id="${basket.good}" align="right">${basket.price*basket.quantity}₽</td>
						<td width="15%" align="right">
						<button class="delete" id="${basket.good}">Удалить из корзины</button>
						</td>
						<c:set var="sum" value="${sum+basket.price*basket.quantity}" /> 
					</tr>
				</c:forEach>
				<tr>
				<td colspan="3">Итого:</td>
				<td align="right"><c:out value="${sum}" />₽</td>
				</tr>
				<tr>
				<td colspan="3" align="right">
				<button>Очистить корзину</button><button style="left-margin: 40px">Оформить заказ</button>
				</td>
				</tr>
	   		</table>
	   </div>
    </div>
    <div class='footer'></div>
</body>
</html>