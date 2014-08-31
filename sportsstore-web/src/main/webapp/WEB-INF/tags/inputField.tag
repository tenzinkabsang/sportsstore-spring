<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="name" required="true" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>

<div class="form-group">
    <form:label path="${name}">${label}</form:label>
    <form:input path="${name}" cssClass="form-control"/>
    <form:errors path="${name}" cssClass="error" />
</div>