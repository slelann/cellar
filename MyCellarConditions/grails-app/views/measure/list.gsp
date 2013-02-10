
<%@ page import="com.mycellar.conditions.measure.Measure" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'measure.label', default: 'Measure')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-measure" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-measure" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="measureDate" title="${message(code: 'measure.measureDate.label', default: 'Measure Date')}" />
					
						<g:sortableColumn property="humidity" title="${message(code: 'measure.humidity.label', default: 'Humidity')}" />
					
						<g:sortableColumn property="celsiusTemperature" title="${message(code: 'measure.celsiusTemperature.label', default: 'Celsius Temperature')}" />
					
						<th><g:message code="measure.serie.label" default="Serie" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${measureInstanceList}" status="i" var="measureInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${measureInstance.id}">${fieldValue(bean: measureInstance, field: "measureDate")}</g:link></td>
					
						<td>${fieldValue(bean: measureInstance, field: "humidity")}</td>
					
						<td>${fieldValue(bean: measureInstance, field: "celsiusTemperature")}</td>
					
						<td>${fieldValue(bean: measureInstance, field: "serie")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${measureInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
