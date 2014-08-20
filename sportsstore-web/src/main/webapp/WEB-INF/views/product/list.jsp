<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>Products</title>

<div>
    <c:forEach var="p" items="${viewModel.products}">
        <%@include file="productSummary.jsp"%>
    </c:forEach>
</div>

<div>
    <ul class="pagination">
        <c:forEach begin="1" end="${viewModel.pagingInfo.totalPages}" varStatus="counter">
            <li class="${viewModel.pagingInfo.getCssClass(counter.index)}">
                <a href="${viewModel.currentCategory}/page${counter.index}">${counter.index}</a>
            </li>
        </c:forEach>
    </ul>
</div>