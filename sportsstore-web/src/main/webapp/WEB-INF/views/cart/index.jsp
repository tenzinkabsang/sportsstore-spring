<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>Sports Store: Your Cart</title>

<h2>Your cart</h2>

<table class="table">
    <thead>
    <tr>
        <th>Quantity</th>
        <th>Item</th>
        <th>Price</th>
        <th>Subtotal</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="line" items="${cartModel.cart.cartLines}">
        <tr>
            <td>${line.quantity}</td>
            <td>${line.product.name}</td>
            <td>
                <fmt:formatNumber value="${line.product.price}" type="Currency" />
            </td>
            <td>
                <fmt:formatNumber value="${line.lineTotal}" type="Currency" />
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="3">Total:</td>
        <td>
            <fmt:formatNumber value="${cartModel.cart.computeTotalValue()}" type="Currency" />
        </td>
    </tr>
    </tfoot>
</table>
<p>
    <a href="${cartModel.returnUrl}" class="btn btn-default">Continue shopping</a>
</p>