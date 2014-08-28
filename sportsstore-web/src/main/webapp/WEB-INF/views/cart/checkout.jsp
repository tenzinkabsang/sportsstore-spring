<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>SportsStore: Checkout</title>

<h3>Check out now</h3>
<p>Please enter your details, and we'll ship your goods right away!</p>

<form:form modelAttribute="shippingDetails" method="post" cssClass="form-horizontal" role="form">
    <form:errors path="*" cssClass="error" />
    <div class="form-group">
        <form:label path="name" cssClass="col-sm-2 control-label">Name:</form:label>
        <div class="col-sm-5">
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name" cssClass="error" />
        </div>
    </div>

    <div class="form-group">
        <form:label path="line1" cssClass="col-sm-2 control-label">Address Line1:</form:label>
        <div class="col-sm-5">
            <form:input path="line1" cssClass="form-control"/>
            <form:errors path="line1" cssClass="error"/>
        </div>
    </div>

    <div class="form-group">
        <form:label path="city" cssClass="col-sm-2 control-label">City:</form:label>
        <div class="col-sm-5">
            <form:input path="city" cssClass="form-control"/>
            <form:errors path="city" cssClass="error" />
        </div>
    </div>

    <div class="form-group">
        <form:label path="state" cssClass="col-sm-2 control-label">State:</form:label>
        <div class="col-sm-5">
            <form:input path="state" cssClass="form-control"/>
            <form:errors path="state" cssClass="error" />
        </div>
    </div>

    <div class="form-group">
        <form:label path="zip" cssClass="col-sm-2 control-label">Zip:</form:label>
        <div class="col-sm-5">
            <form:input path="zip" cssClass="form-control"/>
            <form:errors path="zip" cssClass="error" />
        </div>
    </div>

    <div class="form-group">
        <form:label path="giftWrap" cssClass="col-sm-2 control-label">Options</form:label>
        <div class="col-sm-5">
            <form:checkbox path="giftWrap"></form:checkbox> Gift wrap these items
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" class="btn btn-default" value="Complete order" />
        </div>
    </div>

</form:form>
