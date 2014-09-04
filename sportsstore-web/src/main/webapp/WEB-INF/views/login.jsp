<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="container" style="margin-top: 50px;">

    <p>
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
            <span class="error">
                Login unsuccessful - ${SPRING_SECURITY_LAST_EXCEPTION.message}
            </span>
        </c:if>
    </p>

    <form method="post" action="<s:url value="/static/j_spring_security_check" />" class="form-horizontal" role="form">
        <div class="form-group">
            <label for="j_username" class="col-sm-2 control-label">Username</label>

            <div class="col-sm-5">
                <input id="j_username" name="j_username" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label for="j_password" class="col-sm-2 control-label">Password</label>

            <div class="col-sm-5">
                <input id="j_password" name="j_password" type="password" class="form-control">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-5">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" id="remember_me" name="_spring_security_remember_me"> Remember me
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 com-sm-5">
                <button type="submit" class="btn btn-default">Sign in</button>
            </div>
        </div>
    </form>
</div>
