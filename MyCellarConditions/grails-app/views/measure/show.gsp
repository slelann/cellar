
<%@ page import="com.mycellar.conditions.measure.Measure" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'measure.label', default: 'Measure')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-measure" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-measure" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list measure">
			
				<g:if test="${measureInstance?.measureDate}">
				<li class="fieldcontain">
					<span id="measureDate-label" class="property-label"><g:message code="measure.measureDate.label" default="Measure Date" /></span>
					
						<span class="property-value" aria-labelledby="measureDate-label"><g:formatDate date="${measureInstance?.measureDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${measureInstance?.humidity}">
				<li class="fieldcontain">
					<span id="humidity-label" class="property-label"><g:message code="measure.humidity.label" default="Humidity" /></span>
					
						<span class="property-value" aria-labelledby="humidity-label"><g:fieldValue bean="${measureInstance}" field="humidity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${measureInstance?.celsiusTemperature}">
				<li class="fieldcontain">
					<span id="celsiusTemperature-label" class="property-label"><g:message code="measure.celsiusTemperature.label" default="Celsius Temperature" /></span>
					
						<span class="property-value" aria-labelledby="celsiusTemperature-label"><g:fieldValue bean="${measureInstance}" field="celsiusTemperature"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${measureInstance?.serie}">
				<li class="fieldcontain">
					<span id="serie-label" class="property-label"><g:message code="measure.serie.label" default="Serie" /></span>
					
						<span class="property-value" aria-labelledby="serie-label"><g:link controller="serie" action="show" id="${measureInstance?.serie?.id}">${measureInstance?.serie?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${measureInstance?.id}" />
					<g:link class="edit" action="edit" id="${measureInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
