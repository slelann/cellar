
<%@ page import="com.mycellar.conditions.measure.Serie" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'serie.label', default: 'Serie')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-serie" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-serie" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list serie">
			
				<g:if test="${serieInstance?.serieName}">
				<li class="fieldcontain">
					<span id="serieName-label" class="property-label"><g:message code="serie.serieName.label" default="Serie Name" /></span>
					
						<span class="property-value" aria-labelledby="serieName-label"><g:fieldValue bean="${serieInstance}" field="serieName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serieInstance?.place}">
				<li class="fieldcontain">
					<span id="place-label" class="property-label"><g:message code="serie.place.label" default="Place" /></span>
					
						<span class="property-value" aria-labelledby="place-label"><g:fieldValue bean="${serieInstance}" field="place"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${serieInstance?.measures}">
				<li class="fieldcontain">
					<span id="measures-label" class="property-label"><g:message code="serie.measures.label" default="Measures" /></span>
					
						<g:each in="${serieInstance.measures}" var="m">
						<span class="property-value" aria-labelledby="measures-label"><g:link controller="measure" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${serieInstance?.id}" />
					<g:link class="edit" action="edit" id="${serieInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
