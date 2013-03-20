<%@ page import="com.mycellar.conditions.measure.Serie" %>



<div class="fieldcontain ${hasErrors(bean: serieInstance, field: 'serieName', 'error')} ">
	<label for="serieName">
		<g:message code="serie.serieName.label" default="Serie Name" />
		
	</label>
	<g:textField name="serieName" value="${serieInstance?.serieName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serieInstance, field: 'place', 'error')} ">
	<label for="place">
		<g:message code="serie.place.label" default="Place" />
		
	</label>
	<g:textField name="place" value="${serieInstance?.place}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: serieInstance, field: 'measures', 'error')} ">
	<label for="measures">
		<g:message code="serie.measures.label" default="Measures" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${serieInstance?.measures?}" var="m">
    <li><g:link controller="measure" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="measure" action="create" params="['serie.id': serieInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'measure.label', default: 'Measure')])}</g:link>
</li>
</ul>

</div>

